package org.koreait.models.file;

import org.koreait.commons.CommonException;
import org.springframework.http.HttpStatus;

public class FileNotFoundException extends CommonException {
    public FileNotFoundException() {
        super(bundleError.getString("File.notExists"), HttpStatus.BAD_REQUEST);
    }
}
