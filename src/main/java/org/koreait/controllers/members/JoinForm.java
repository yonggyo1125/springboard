package org.koreait.controllers.members;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class JoinForm {
    private String userId; // 아이디
    private String userPw;
    private String userPwRe;
    private String userNm;
    private String email;
    private String mobile;
    private boolean[] agrees;
}
