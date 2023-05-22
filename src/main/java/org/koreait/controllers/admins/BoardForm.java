package org.koreait.controllers.admins;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.koreait.commons.constants.Role;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class BoardForm {
    private String bId; // 게시판 ID

    private String bName; // 게시판명

    private boolean use; // 사용 여부

    private int rowsOfPage = 20; // 1페이지당 게시글 수

    private boolean showViewList; // 게시글 하단 목록 노출

    private String category; // 게시판 분류


    private String listAccessRole = "ALL";

    // 글보기 접근 권한
    private String ViewAccessRole = "ALL";

    // 글쓰기 접근 권한
    private String writeAccessRole = "ALL";

    // 답글 접근 권한
    private String replyAccessRole = "ALL";

    // 댓글 접근 권한
    private String commentAccessRole = "ALL";

    // 에디터 사용 여부
    private boolean useEditor;

    // 파일 첨부 사용여부
    private boolean useAttachFile;

    // 이미지 첨부 사용여부
    private boolean useAttachImage;

    // 글작성 후 이동
    @Column(length=10, nullable=false)
    private String locationAfterWriting = "view";

    // 답글 사용 여부
    private boolean useReply;

    // 댓글 사용 여부
    private boolean useComment;

    // 게시판 스킨
    @Column(length=20, nullable=false)
    private String skin = "default";
}
