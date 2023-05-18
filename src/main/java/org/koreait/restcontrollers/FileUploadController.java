package org.koreait.restcontrollers;

import org.koreait.commons.CommonException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileUploadController {
    @GetMapping("/file/upload")
    public void upload() {
       
    }
}
