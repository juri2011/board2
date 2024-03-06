package board;

import java.util.Date;

/*
	DB 테이블 구조 (컬럼)
	
	num number primary key,
    title varchar2(50) not null,
    writer varchar2(50) not null,
    content varchar2(100),
    regdate date,
    cnt number default 0
*/
public class BoardVO {
	
	//DB 컬럼 타입에 맞추어 멤버변수 선언
	private int num;
	private String title;
	private String writer;
	private String content;
	private Date regdate;
	private int cnt;
	
	//기본 생성자
	public BoardVO() {
		
	}
	
	//필드 생성자
	public BoardVO(int num, String title, String writer, String content, Date regdate, int cnt) {
		this.num = num;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.regdate = regdate;
		this.cnt = cnt;
	}
	
	//Getter, Setter
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
	//toString (오버라이드하여 쓴다)
	@Override
	public String toString() {
		return "BoardVO [num=" + num + ", title=" + title + ", writer=" + writer + ", content=" + content + ", regdate="
				+ regdate + ", cnt=" + cnt + "]";
	}
}
