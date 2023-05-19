package org.koreait.commons;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MenuDetail {
    private String code;
    private String name;
    private String url;
}
