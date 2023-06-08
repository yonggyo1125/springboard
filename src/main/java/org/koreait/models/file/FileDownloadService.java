package org.koreait.models.file;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.koreait.entities.FileInfo;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
@RequiredArgsConstructor
public class FileDownloadService {
    private final FileInfoService infoService;
    private final HttpServletResponse response;

    public void download(Long id) {
        FileInfo fileInfo = infoService.get(id);
        String filePath = fileInfo.getFilePath();
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
            OutputStream out = response.getOutputStream();
            String fileName = fileInfo.getFileName();
            response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes(), "ISO8859_1"));
            response.setHeader("Content-Type", "application/octet-stream");
            response.setIntHeader("Expires", 0);
            response.setHeader("Cache-Control", "must-revalidate");
            response.setHeader("Pragma", "public");
            response.setHeader("Content-Length", String.valueOf(file.length()));

            while(bis.available() > 0) {
                out.write(bis.read());
            }

            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
