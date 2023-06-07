package org.koreait.models.board;

import lombok.RequiredArgsConstructor;
import org.koreait.entities.BoardData;
import org.koreait.repositories.BoardDataRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BoardDataDeleteService {
    private final BoardDataRepository repository;

    /**
     * 게시글 삭제
     *
     * @param id 게시글 번호
     * @param isComplete : false - 소프트 삭제, true - 완전 삭제
     */
    public void delete(Long id, boolean isComplete) {
        BoardData boardData = repository.findById(id).orElseThrow(BoardDataNotExistsException::new);

        if (isComplete) { // 완전 삭제
            repository.delete(boardData);
        } else { // 소프트 삭제
            boardData.setDeletedAt(LocalDateTime.now());
        }

        repository.flush();
    }

    public void delete(Long id) {
        delete(id, false);
    }
}
