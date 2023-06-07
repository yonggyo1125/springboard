package org.koreait.models.file;

import lombok.RequiredArgsConstructor;
import org.koreait.entities.FileInfo;
import org.koreait.repositories.FileInfoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileInfoSaveService {
    private final FileInfoRepository repository;

    /**
     * 
     * @param files
     * @param gid - null 값이거나 "" 일때는 랜덤하게 하나 생성
     * @param location
     */
    public List<FileInfo> save(MultipartFile[] files, String gid, String location) {
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
            items.add(item);
        }

        items = repository.saveAllAndFlush(items);

        return items;
    }

    public void save(MultipartFile[] files, String gid) {
        save(files, gid, null);
    }

    public void save(MultipartFile[] files) {
        save(files, null);
    }
}
