package org.koreait.controllers.members;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class JoinForm {
    @NotBlank
    @Size(min=6, max=20)
    private String userId; // 아이디

    @NotBlank
    @Size(min=8)
    private String userPw;

    @NotBlank
    private String userPwRe;

    @NotBlank
    private String userNm;

    @NotBlank @Email
    private String email;
    private String mobile;

    private boolean[] agrees;
}
