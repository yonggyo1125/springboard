package org.koreait.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity @Data
@IdClass(BoardViewId.class)
public class BoardView {
    @Id
    private Long id; // 게시글 번호

    @Id
    @Column(length=40, name="uid_")
    private String uid; // IP + UA + 회원번호
}
