package org.koreait.models.board;

import lombok.RequiredArgsConstructor;
import org.koreait.entities.BoardData;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GuestPasswordCheckService {

    private final BoardDataInfoService infoService;
    private final PasswordEncoder passwordEncoder;

    public void check(Long id, String password) {
        check(id, password, "board");
    }

    public void check(Long id, String password, String mode) {
        if (id == null) {
            throw new BoardValidationException("BadRequest");
        }

        mode = mode == null || mode.isBlank() ? "board" : mode;

        if (mode.equals("board") || mode.equals("board_delete")) { // 일반 게시글 수정, 삭제
            BoardData boardData = infoService.get(id, "update");
            String guestPw = boardData.getGuestPw();
            boolean matched = passwordEncoder.matches(password, guestPw);
            if (!matched) {
                throw new GuestPasswordIncorrectException();
            }


        } else if (mode.equals("comment")) { // 댓글

        }
    }
}
