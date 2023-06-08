package org.koreait.models.file;

import lombok.RequiredArgsConstructor;
import org.koreait.commons.AuthorizationException;
import org.koreait.commons.MemberUtil;
import org.koreait.entities.FileInfo;
import org.koreait.repositories.FileInfoRepository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileDeleteService {
    private final FileInfoRepository repository;
    private final FileInfoService infoService;
    private final MemberUtil memberUtil;

    /**
     * 파일 등록번호로 파일 정보, 업로드 파일 삭제
     *
     * @param id : 파일 등록 번호
     */
    public void delete(Long id) {
        FileInfo fileInfo = infoService.get(id);

        /** 관리자 및 파일을 등록한 사용자만 삭제 가능 여부 체크 S */
        String createdBy = fileInfo.getCreatedBy();
        if (fileInfo.isDone() && createdBy != null && !memberUtil.isAdmin() &&
                (!memberUtil.isLogin() || !memberUtil.getMember().getUserId().equals(createdBy))) {
            throw new AuthorizationException("File.notYours");
        }
        /** 관리자 및 파일을 등록한 사용자만 삭제 가능 여부 체크 E */

        /**
         * 1. 정보 삭제
         * 2. 실제 파일 삭제
         */
        String filePath = fileInfo.getFilePath();
        repository.delete(fileInfo);
        repository.flush();

        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * GID로 파일 정보, 업로드된 파일 삭제
     *
     * @param gid : 그룹 ID
     */
    public void delete(String gid) {
        List<FileInfo> files = infoService.gets(gid);

        files.stream().forEach(f -> delete(f.getId()));
    }
}
