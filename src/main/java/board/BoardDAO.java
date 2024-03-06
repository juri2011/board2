package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
		
		//전체 삽입된 행 수
		int ret = -1;
		
		try {
			//커넥션풀로부터 Connection 객체를 받아옴
			con = ju.getConnection();
			//pstmt에 query 등록
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getWriter());
			pstmt.setString(3, vo.getContent());
			
			//전체 삽입 된 행 수 리턴
			ret = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt);
		}//end of finally
		
		return ret;
	}
}
