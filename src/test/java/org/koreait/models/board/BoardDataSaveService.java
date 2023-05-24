package org.koreait.models.board;

import lombok.RequiredArgsConstructor;
import org.koreait.commons.validators.Validator;
import org.koreait.controllers.boards.BoardForm;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardDataSaveService {

    private final BoardValidator validator;

    public void save(BoardForm boardForm) {
        validator.check(boardForm);
    }
}
