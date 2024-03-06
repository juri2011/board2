package board;

import java.sql.Connection;
import java.sql.PreparedStatement;

import common.JdbcUtil;

public class BoardDAO {
	
	private JdbcUtil ju;
	
	//DI(의존주입)
	public BoardDAO() {
		ju = JdbcUtil.getInstance();
	}
	
	//삽입(C) - 게시글 작성
	public int insert (BoardVO vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "insert into board(num, title, writer, content, regdate, cnt) "
				+ "values(board_seq.nextval, ?, ?, ?, sysdate, 0)";
		
		int ret = -1;
		
		return ret;
	}
}
