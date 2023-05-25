package org.koreait.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.koreait.commons.configs.ConfigSaveService;
import org.koreait.controllers.admins.ConfigForm;
import org.koreait.controllers.boards.BoardForm;
import org.koreait.controllers.members.JoinForm;
import org.koreait.entities.Board;
import org.koreait.entities.Member;
import org.koreait.models.board.BoardDataSaveService;
import org.koreait.models.board.BoardValidationException;
import org.koreait.models.board.config.BoardConfigInfoService;
import org.koreait.models.board.config.BoardConfigSaveService;
import org.koreait.models.member.MemberSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:application-test.properties")
@DisplayName("게시글 등록, 수정 테스트")
@Transactional
public class BoardSaveTests {

    @Autowired
    private BoardDataSaveService saveService;

    @Autowired
    private BoardConfigSaveService configSaveService;

    @Autowired
    private BoardConfigInfoService configInfoService;

    @Autowired
    private MemberSaveService memberSaveService;

    @Autowired
    private ConfigSaveService siteConfigSaveService;

    private Board board;

    private JoinForm joinForm;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    @Transactional
    void init() {
        // 게시판 설정 추가
        org.koreait.controllers.admins.BoardForm boardForm = new org.koreait.controllers.admins.BoardForm();
        boardForm.setBId("freetalk1000");
        boardForm.setBName("자유게시판");
        configSaveService.save(boardForm);
        board = configInfoService.get(boardForm.getBId(), true);

        // 회원 가입 추가
        joinForm = JoinForm.builder()
                .userId("user01")
                .userPw("aA!123456")
                .userPwRe("aA!123456")
                .email("user01@test.org")
                .userNm("사용자01")
                .mobile("01000000000")
                .agrees(new boolean[]{true})
                .build();
        memberSaveService.save(joinForm);
    }



    private BoardForm getGuestBoardForm() {

        BoardForm boardForm = getCommonBoardForm();

        boardForm.setGuestPw("12345678");

        return boardForm;
    }

    private BoardForm getCommonBoardForm() {
        return BoardForm.builder()
                .bId(board.getBId())
                .gid(UUID.randomUUID().toString())
                .poster(joinForm.getUserNm())
                .subject("제목!")
                .content("내용!")
                .category(board.getCategories() == null ? null : board.getCategories()[0])
                .build();
    }

    @Test
    @DisplayName("게시글 등록(비회원) 성공시 예외 없음")
    @WithAnonymousUser
    void registerGuestSuccessTest() {
        assertDoesNotThrow(() -> {
            saveService.save(getGuestBoardForm());
        });
    }
    
    @Test
    @DisplayName("게시글 등록(회원) 성공시 예외 없음")
    @WithMockUser(username="user01", password="aA!123456")
    @Disabled
    void registerMemberSuccessTest() {
        assertDoesNotThrow(() -> {
            saveService.save(getCommonBoardForm());
        });
    }

    // 공통(회원, 비회원) 유효성 검사 체크
    private void commonRequiredFieldsTest() {
        assertAll(
                // bId - null
                () -> assertThrows(BoardValidationException.class, () -> {
                    BoardForm boardForm = getCommonBoardForm();
                    boardForm.setBId(null);
                    saveService.save(boardForm);
                }),
                // bId - 공백
                () -> assertThrows(BoardValidationException.class, () -> {
                    BoardForm boardForm = getCommonBoardForm();
                    boardForm.setBId("    ");
                    saveService.save(boardForm);
                }),
                // gid - null
                () -> assertThrows(BoardValidationException.class, () -> {
                    BoardForm boardForm = getCommonBoardForm();
                    boardForm.setGid(null);
                    saveService.save(boardForm);
                }),
                // gid - 공백
                () -> assertThrows(BoardValidationException.class, () -> {
                    BoardForm boardForm = getCommonBoardForm();
                    boardForm.setGid("    ");
                    saveService.save(boardForm);
                }),
                // poster - null
                () -> assertThrows(BoardValidationException.class, () -> {
                    BoardForm boardForm = getCommonBoardForm();
                    boardForm.setPoster(null);
                    saveService.save(boardForm);
                }),
                // poster - 공백
                () -> assertThrows(BoardValidationException.class, () -> {
                    BoardForm boardForm = getCommonBoardForm();
                    boardForm.setPoster("      ");
                    saveService.save(boardForm);
                }),
                // subject - null
                () -> assertThrows(BoardValidationException.class, () -> {
                    BoardForm boardForm = getCommonBoardForm();
                    boardForm.setSubject(null);
                    saveService.save(boardForm);
                }),
                // subject - 공백
                () -> assertThrows(BoardValidationException.class, () -> {
                    BoardForm boardForm = getCommonBoardForm();
                    boardForm.setSubject("       ");
                    saveService.save(boardForm);
                }),
                // content - null
                () -> assertThrows(BoardValidationException.class, () -> {
                    BoardForm boardForm = getCommonBoardForm();
                    boardForm.setContent(null);
                    saveService.save(boardForm);
                }),
                // content - 공백
                () -> assertThrows(BoardValidationException.class, () -> {
                    BoardForm boardForm = getCommonBoardForm();
                    boardForm.setContent("     ");
                    saveService.save(boardForm);
                })

        );
    }

    @Test
    @DisplayName("필수 항목 검증(비회원) - bId, gid, poster, subject, content, guestPw(자리수는 6자리 이상), BoardValidationException이 발생")
    @WithAnonymousUser
    void requiredFieldsGuestTest() {
        commonRequiredFieldsTest();

        assertAll(
                () -> assertThrows(BoardValidationException.class, () -> {
                    BoardForm boardForm = getGuestBoardForm();
                    boardForm.setGuestPw(null);
                    saveService.save(boardForm);
                }),
                () -> assertThrows(BoardValidationException.class, () -> {
                    BoardForm boardForm = getGuestBoardForm();
                    boardForm.setGuestPw("   ");
                    saveService.save(boardForm);
                }),
                () -> assertThrows(BoardValidationException.class, () -> {
                    BoardForm boardForm = getGuestBoardForm();
                    boardForm.setGuestPw("1234");
                    saveService.save(boardForm);
                })
        );


    }

    @Test
    @DisplayName("필수 항목 검증(회원) - bId, gid, poster, subject, content, BoardValidationException 발생")
    @WithMockUser(username="user01", password="aA!123456")
    void requiredFieldsMemberTest() {

        commonRequiredFieldsTest();
    }

    @Test
    @DisplayName("통합테스트 - 비회원 게시글 작성 유효성 검사")
    @Disabled
    void requiredFieldsGuestControllerTest() throws Exception {
        BoardForm boardForm = getGuestBoardForm();
        String body = mockMvc.perform(post("/board/save")
                    .param("bId", boardForm.getBId())
                    .param("gid", boardForm.getGid())
                            .with(csrf().asHeader()))
                    .andDo(print())
                    .andReturn()
                    .getResponse()
                    .getContentAsString();

        ResourceBundle bundle = ResourceBundle.getBundle("messages.validations");
        String[] messages = {
            bundle.getString("NotBlank.boardForm.poster"),
                bundle.getString("NotBlank.boardForm.subject"),
                bundle.getString("NotBlank.boardForm.content"),
                bundle.getString("NotBlank.boardForm.guestPw"),
                bundle.getString("Size.boardForm.guestPw")
        };
        for (String message : messages) {
            assertTrue(body.contains(message));
        }
    }
}
