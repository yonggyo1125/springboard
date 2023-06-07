package org.koreait.models.file;

import com.querydsl.core.BooleanBuilder;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.koreait.entities.FileInfo;
import org.koreait.entities.QFileInfo;
import org.koreait.repositories.FileInfoRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

import static org.springframework.data.domain.Sort.Order.asc;

@Service
@RequiredArgsConstructor
public class FileInfoService {

    private final FileInfoRepository repository;
    private final HttpServletRequest request;

    @Value("${file.upload.path}")
    private String fileUploadPath;

    /**
     * 파일 등록 번호로 파일 정보 조회
     *
     * @param id : 등록번호
     * @return
     */
    public FileInfo get(Long id) {
        FileInfo fileInfo = repository.findById(id).orElseThrow(FileNotFoundException::new);

        // 파일이 올라간 path, 파일 접근 URL
        String extension = fileInfo.getExtension();
        fileInfo.setFilePath(getFilePath(id, extension));
        fileInfo.setFileUrl(getFileUrl(id, extension));

        return fileInfo;
    }

    /**
     *
     * @param gid : 그룹 ID
     * @param location : 각 파일 용도별 위치
     * @param done : false : 미완료, true : 완료
     * @return
     */
    public List<FileInfo> gets(String gid, String location, boolean done) {
        QFileInfo fileInfo = QFileInfo.fileInfo;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(fileInfo.gid.eq(gid))
                .and(fileInfo.done.eq(done));

        if (location != null && !location.isBlank()) {
            builder.and(fileInfo.location.eq(location));
        }

        List<FileInfo> items = (List<FileInfo>)repository.findAll(builder, Sort.by(asc("createdAt")));

        items.stream().forEach(item -> {
            Long id = item.getId();
            String extension = item.getExtension();
            item.setFilePath(getFilePath(id, extension));
            item.setFileUrl(getFileUrl(id, extension));
        });

        return items;
    }

    public List<FileInfo> gets(String gid) {
        return gets(gid, null, true);
    }

    public List<FileInfo> gets(String gid, boolean done) {
        return gets(gid, null, done);
    }

    public List<FileInfo> gets(String gid, String location) {
        return gets(gid, location, true); // 그룹 작업 완료 파일 목록
    }



    public String getFilePath(Long id, String extension) {
        String folderPath = fileUploadPath + getFolder(id);
        File dir = new File(folderPath);
        if (!dir.exists()) {
            dir.mkdir();
        }

        String path = folderPath + File.separator + id;
        if (extension != null && !extension.isBlank()) path += "." + extension;

        return path;
    }

    public String getFileUrl(Long id, String extension) {
        String url = request.getContextPath() + "/uploads/" + getFolder(id) + "/" + id;
        if (extension != null && !extension.isBlank()) url += "." + extension;

        return url;
    }

    private String getFolder(Long id) {
        return String.valueOf(id % 10);
    }
}
