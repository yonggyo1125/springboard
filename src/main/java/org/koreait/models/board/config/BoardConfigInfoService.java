package org.koreait.models.board.config;

import lombok.RequiredArgsConstructor;
import org.koreait.commons.MemberUtil;
import org.koreait.entities.Board;
import org.koreait.repositories.BoardRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardConfigInfoService {

    private final BoardRepository boardRepository;
    private final MemberUtil memberUtil;

    public Board get(String bId, String location) { // 프론트, 접근 권한 체크
        return get(bId, false, location);
    }

    /**
     * 게시판 설정 조회
     *
     * @param bId
     * @param isAdmin : true - 권한 체크 X
     *                : false - 권한 체크, location으로 목록, 보기, 글쓰기, 답글, 댓글
     *
     * @param location : 기능 위치(list, view, write, reply, comment)
     *
     * @return
     */
    public Board get(String bId, boolean isAdmin, String location) {


        if (!isAdmin) { // 권한 체크

        }

        return null;
    }
}
