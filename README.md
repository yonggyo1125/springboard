# 게시판 포트폴리오
* * * 
## 1일차 
* 스프링 부트 기본 설정
* 타임리프 설정 
* <a href="https://drive.google.com/drive/folders/16CVJZAod0Uo5pZeHQfCjfA0tLs5vWpwS?usp=share_link">동영상 강의</a>


## 2일차
* 타임리프 레이아웃 템플릿 완성 
* 스프링 시큐리티 설정
	- 회원가입 엔티티, 레포지토리
	
		

* <a href="https://drive.google.com/drive/folders/1Eu3wl9GIVdIxFUaxLTGVIAgqpr3ZsCZ0?usp=share_link">동영상 강의</a>

## 3일차
* 스프링 시큐리티 설정
	- 로그인 양식 
	- UserDetails, UserDetailsService 인터페이스 구현 클래스
	- Spring Data JPA + Spring Security -  수정자(AwareAuditor 인터페이스 구현체)
	- 스프링 시큐리에서 회원 정보 조회 방법 
		- 요청 처리 메서드 주입 
			- Principal principal  - String getName() : 아이디
			- @AuthenticationPrincipal UserDetails 구현 클래스의 객체
			
		- 직접 회원정보 가져오기
			- SecurityContextHolder
				- getContext().getAuthentication()
				-  Object getPrincipal() : 비회원 (String) : anonymousUser, 회원 : UserDetails 구현 객체
					
		
		
* 기본 에러 응답 코드 처리 
	- 템플릿 경로 /error/응답코드.html
		- timestamp - 오류 발생 시각 
		- status - HTTP 상태 코드 
		- error - 오류 발생 원인 
		- exception - 예외 객체 
		- errors - Errors 객체
		- trace - printStackTrace()
		- path - 오류의 유입 URL
	
	
* 공통 오류 페이지
	- @ExceptionHandler, @ControllerAdvice, @RestControllerAdvice
	
* <a href="https://drive.google.com/drive/folders/1zrk-y8QL5K8pUa7uJnKUfWY_AzHHlFRv?usp=share_link">동영상 강의</a>

## 4일차
* 공통 오류 페이지 처리 
	- 일반 컨트롤러(@ControllerAdvice)
	- REST 컨트롤러(@RestControllerAdvice)
		- 일반 요청 응답과 오류 통일성 있게 처리 (JSONData)
		
		
* 관리자페이지 
	- 사이트 설정 
		- 추후에 설정이 많이 추가됨을 고려 
		- CodeValue 엔티티  code(PK), value - JSON 
			
	- 게시판 설정

* <a href="https://drive.google.com/drive/folders/1zTuyIVdIy99BLSqrN5p1cDODtbmVUU7Q?usp=share_link">동영상 강의</a>


## 5일차
* 관리자페이지 
	- 사이트 설정 
	- 게시판 설정

* <a href="https://drive.google.com/drive/folders/1BUuxfIcPO32JGlaC9XZ-Zd_kG7KZ3eaN?usp=share_link">동영상 강의</a>

## 6일차
* 관리자페이지 
	- 게시판 설정 
		- Board : 게시판 설정 엔티티
		- BoardData : 게시글 데이터
		
	- 게시판 목록
* <a href="https://drive.google.com/drive/folders/12Jz7WuVcgG7epBAe3n_JQhdJ35GS39uN?usp=share_link">동영상 강의</a>

## 7일차
* 관리자페이지 
	- 게시판 목록 

* 프론트페이지 
	- 게시글 쓰기
* <a href="https://drive.google.com/drive/folders/1jYTOep60XCkm0_B4M4MMkTdyKc76tgjQ?usp=share_link">동영상 강의</a>

## 8일차
* 프론트페이지 
	- 게시글 쓰기
	- 게시글 보기
* <a href="https://drive.google.com/drive/folders/1OCTZ0FyKgg_svNs_H6KmSon7Z_CNa_id?usp=drive_link">동영상 강의</a>
	
	
## 9일차 
* 프론트페이지 
	- 게시글 보기
	- 파일업로드
	
* <a href="https://drive.google.com/drive/folders/1PX33O0f1cMDI3IuaUuM1LPGKwpxHFKq9?usp=drive_link">동영상 강의</a>

## 10일차 
* 프론트페이지 
	- 게시글 보기
	- 조회수 구현
		- IP + 브라우저 정보(UserAgent) + 회원번호(없으면 비회원, 있으면 회원) -> hashcode -> Objects.hashcode
	
* <a href="https://drive.google.com/drive/folders/1nRs2vpJZgz-EA9v4tg-xSyI6AdpkKZ1y?usp=drive_link">동영상 강의</a>

## 11일차
* 프론트페이지 
	- 조회수 구현
    - 게시글 수정, 게시글 삭제
		- 관리자 - 가능, 회원 - 동일 회원, 비회원 - 비밀번호 인증 성공(세션에 기록)
* <a href="https://drive.google.com/drive/folders/1_oiiVUbebTtmN1minI1yiR51cSxK8gl6?usp=drive_link">동영상 강의</a>

## 12일차 
* 프론트페이지
  - 파일 업로드 구현 
* <a href="https://drive.google.com/drive/folders/1cpVuWH04cqvhqXZnMtBtrrVBEghZ5JRS?usp=drive_link">동영상 강의</a>
