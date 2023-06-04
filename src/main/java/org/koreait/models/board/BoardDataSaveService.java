package org.koreait.models.board;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.koreait.commons.MemberUtil;
import org.koreait.commons.validators.Validator;
import org.koreait.controllers.boards.BoardForm;
import org.koreait.entities.Board;
import org.koreait.entities.BoardData;
import org.koreait.models.board.config.BoardConfigInfoService;
import org.koreait.repositories.BoardDataRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardDataSaveService {

    private final BoardValidator validator;
    private final MemberUtil memberUtil;
    private final BoardConfigInfoService configInfoService;
    private final BoardDataRepository repository;
    private final HttpServletRequest request;
    private final PasswordEncoder passwordEncoder;

    public void save(BoardForm boardForm) {
        validator.check(boardForm);

        // 게시글 저장 처리 - 추가, 수정
        /**
         * 1. 게시판 설정 - 글 작성, 수정 권한 체크
         *              - 수정 -> 본인이 작성한 글인지
         * 2. 게시글 저장, 수정
         * 3. 회원 정보 - 게시글 등록시에만 저장
         */
        Long id = boardForm.getId();
        Board board = configInfoService.get(boardForm.getBId(), id == null ? "write":"update");

        BoardData boardData = null;
        if (id == null) { // 게시글 추가
            String ip = request.getRemoteAddr();
            String ua = request.getHeader("User-Agent");
            boardData = BoardData.builder()
                            .gid(boardForm.getGid())
                            .board(board)
                            .category(boardForm.getCategory())
                            .poster(boardForm.getPoster())
                            .subject(boardForm.getSubject())
                            .content(boardForm.getContent())
                            .ip(ip)
                            .ua(ua)
                            .build();

            if (memberUtil.isLogin()) { // 로그인 시 - 회원 데이터
                boardData.setMember(memberUtil.getEntity());
            } else { // 비회원 비밀번호
                boardData.setGuestPw(passwordEncoder.encode(boardForm.getGuestPw()));
            }

        } else { // 게시글 수정
            boardData = repository.findById(boardForm.getId()).orElseThrow(BoardDataNotExistsException::new);
            boardData.setPoster(boardForm.getPoster());
            boardData.setSubject(boardForm.getSubject());
            boardData.setContent(boardForm.getContent());
            boardData.setCategory(boardForm.getCategory());
            String guestPw = boardForm.getGuestPw();
            if (boardData.getMember() == null && guestPw != null && !guestPw.isBlank()) {
                boardData.setGuestPw(passwordEncoder.encode(guestPw));
            }
        }


        boardData = repository.saveAndFlush(boardData);
        boardForm.setId(boardData.getId());
    }
}
