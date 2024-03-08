# board2
jsp를 기반으로 국비지원 수업 때 진행된 게시판 CRUD 실습 예제 프로젝트입니다.

## :computer: 개발 환경
* `JAVA11`
* `Eclipse`
* `OracleDB`
* `JSP`

## :memo: 요구사항
### 목록 출력
* list.jsp
### 작성
* regist.jsp
* registForm.jsp
### 상세 페이지 조회
* boardDetail.jsp
### 수정
* edit.jsp
* editForm.jsp
### 삭제
* regist.jsp
* registForm.jsp

## :wrench: 개선사항
* DAO에서 중복되는 코드 리팩토링
* 목록 페이지에서 새로고침 하지 않아도 최신 조회수 반영
* 삭제 버튼 클릭 시 사용자로부터 확인 요청

## :bulb: 알게 된 점
* 반복되는 코드를 메소드 오버로딩을 통해 간결하게 작성 할 수 있다는 것을 배웠다.
* jsp 프로젝트에서 자바빈을 어떻게 활용하는지 살펴볼 수 있었다.
* 새로고침 하지 않아도 최신 데이터를 화면에 띄우기 위해 캐시 방지를 사용할 수 있다는 것을 배웠다.
