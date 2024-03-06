package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	
	// finally{} 에 공통으로 들어갈 .close()부분을 메소드로 생성
	//메소드에선 PreparedStatement를 쓰지만, 상위 인터페이스인 Statement의 타입으로 사용할 수도 있다
	private void close(Connection con, Statement stmt, ResultSet rs) {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	
	//메소드 오버로딩
	private void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
		//close()메소드가 parameter로 Statement 타입을 받으므로 pstmt를 형변환함
		close(con, (Statement)pstmt, rs);
	}
	
	//메소드 오버로딩
	private void close(Connection con, PreparedStatement pstmt) {
		//pstmt는 상위 인터페이스 Statement의 타입으로 사용할 수 있다
		close(con, pstmt, null);
	}
}
