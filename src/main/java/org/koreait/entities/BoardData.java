package org.koreait.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity @Data @Builder
@AllArgsConstructor @NoArgsConstructor
@Table(indexes={
        @Index(name="idx_boarddata_category", columnList = "category DESC"),
        @Index(name="idx_boarddata_createAt", columnList = "createdAt DESC")
})
public class BoardData extends BaseEntity {
    @Id @GeneratedValue
    private Long id; // 게시글 번호
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="bId")
    private Board board;

    @Column(length=65, nullable = false)
    private String gid = UUID.randomUUID().toString();

    @Column(length=40, nullable = false)
    private String poster; // 작성자

    @Column(length=65)
    private String guestPw; // 비회원 비밀번호

    @Column(length=60)
    private String category; // 게시판 분류

    @Column(nullable = false)
    private String subject; // 제목

    @Lob
    @Column(nullable = false)
    private String content; // 내용
    private int hit; // 조회수

    @Column(length=125)
    private String ua; // User-Agent : 브라우저 정보

    @Column(length=20)
    private String ip; // 작성자 IP

    private int commentCnt; // 댓글 수

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="userNo")
    private Member member; // 작성 회원
}
