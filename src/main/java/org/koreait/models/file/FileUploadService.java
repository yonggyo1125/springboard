package org.koreait.models.file;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.koreait.entities.FileInfo;
import org.koreait.repositories.FileInfoRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUploadService {
    @Value("${file.upload.path}")
    private String fileUploadPath;
    private final FileInfoRepository repository;
    private final HttpServletRequest request;

    /**
     * 
     * @param files
     * @param gid - null 값이거나 "" 일때는 랜덤하게 하나 생성
     * @param location
     */
    public List<FileInfo> upload(MultipartFile[] files, String gid, String location) {
        gid = gid == null || gid.isBlank() ? UUID.randomUUID().toString() : gid;

        List<FileInfo> items = new ArrayList<>();
        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            String extension = fileName.lastIndexOf('.') != -1 ? fileName.substring(fileName.lastIndexOf('.') + 1) : null;

            FileInfo item = FileInfo.builder()
                    .gid(gid)
                    .location(location)
                    .fileName(fileName)
                    .extension(extension)
                    .contentType(file.getContentType())
                    .build();
            item = repository.saveAndFlush(item);

            Long id = item.getId();
            String folder = "" + id % 10;
            String path = fileUploadPath + folder + "/" + id;
            String url = request.getContextPath() + "/uploads/" + folder + "/" + id;
            if (extension != null && !extension.isBlank()) {
                path += "." + extension;
                url += "." + extension;
            }

            item.setFilePath(path);
            item.setFileUrl(url);

            try {
                file.transferTo(new File(path));
            } catch (IOException e) {
                e.printStackTrace();
            }

            items.add(item);
        }

        return items;
    }

    public void upload(MultipartFile[] files, String gid) {
        upload(files, gid, null);
    }

    public void upload(MultipartFile[] files) {
        upload(files, null);
    }
}
