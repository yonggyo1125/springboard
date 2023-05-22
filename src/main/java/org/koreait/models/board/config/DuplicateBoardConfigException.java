package org.koreait.models.board.config;

import org.koreait.commons.CommonException;
import org.springframework.http.HttpStatus;

public class DuplicateBoardConfigException extends CommonException {

    public DuplicateBoardConfigException() {
        super("이미 등록된 게시판 입니다.", HttpStatus.BAD_REQUEST);
    }
}
