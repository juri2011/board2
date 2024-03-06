package board;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
	
	//조회(R) - 게시글 리스트
	public List<BoardVO> selectAll(){
		Connection con = null;
		Statement stmt = null;
		//전체 리스트 결과를 ResultSet에 담는다
		ResultSet rs = null;
		String query = "select num, title, writer, content, regdate, cnt from board order by num desc";
		
		List<BoardVO> ls = new ArrayList<>();
		
		try {
			con = ju.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				//list에 담을 vo 객체 생성
				BoardVO vo = new BoardVO(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						new Date(rs.getDate(5).getTime()),
						rs.getInt(6));
				//vo 객체에 담아 list에 추가
				ls.add(vo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(con, stmt, rs);
		}
		
		//전체 검색된 행 리스트 반환
		return ls;
	}
	
	//상세조회 - 게시글 하나 조회
	public BoardVO selectOne(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//primary key인 num으로 조회
		String query = "select num, title, writer, content, regdate, cnt from board where num = ?";
		
		BoardVO vo = null;
		
		try {
			con = ju.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo = new BoardVO(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						new Date(rs.getDate(5).getTime()),
						rs.getInt(6));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt, rs);
		}
		
		return vo;
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
