package org.koreait.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class FileInfo extends BaseMemberEntity {
    @Id @GeneratedValue
    private Long id; // 파일 등록번호 - 실제 서버 업로드 파일 (파일 등록번호.확장자)

    @Column(length=45, nullable = false)
    private String gid; // 그룹 ID

    @Column(length=45)
    private String location; // 파일 세부 용도 위치

    @Column(length=100, nullable = false)
    private String fileName; // 원본 파일이름

    @Column(length=20, nullable = false)
    private String extension; // 파일 확장자

    @Column(length=120)
    private String contentType; // 파일 형식

    private boolean done; // true - 그룹 작업 완료

    @Transient
    private String filePath; // 파일 업로드 경로

    @Transient
    private String fileUrl; // 파일 URL
}
