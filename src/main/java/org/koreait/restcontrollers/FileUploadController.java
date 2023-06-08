package org.koreait.restcontrollers;

import lombok.RequiredArgsConstructor;
import org.koreait.commons.rests.JSONData;
import org.koreait.entities.FileInfo;
import org.koreait.models.file.FileUploadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FileUploadController {

    private final FileUploadService uploadService;

    @PostMapping("/file/upload")
    public ResponseEntity<JSONData<List<FileInfo>>> upload(MultipartFile[] files, String gid, String location) {
        List<FileInfo> items = uploadService.upload(files, gid, location);

        JSONData<List<FileInfo>> jsonData = new JSONData<>();
        jsonData.setSuccess(true);
        jsonData.setData(items);

        return ResponseEntity.ok(jsonData);
    }
}
