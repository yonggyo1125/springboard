package org.koreait.commons.validators;

public interface PasswordValidator {

    /**
     * 비밀번호 복잡성 체크 - 알파벳 체크
     *
     * @param password
     * @param caseIncentive
     *          false : 소문자 + 대문자가 반드시 포함되는 패턴
     *          true : 대소문자 상관없이 포함되는 패턴
     * @return
     */
    default boolean alphaCheck(String password, boolean caseIncentive) {

        return false;
    }

    /**
     * 숫자가 포함된 패턴인지 체크
     *
     * @param password
     * @return
     */
    default boolean numberCheck(String password) {

        return false;
    }

    /**
     * 특수문자가 포함된 패턴인지 체크 
     * @param password
     * @return
     */
    default boolean specialCharsCheck(String password) {

        return false;
    }
}
