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
		//num, regdate, cnt는 자동으로 삽입되고, 나머지는 사용자가 삽입한다
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
		//전체 리스트 결과를 num을 기준으로 내림차순으로 조회
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
			/*
			 	------ !!!![주의]!!!! 이 주석 블록 안에 있는 코드는 더이상 쓰지 않음 ------
			 	
			 	[기존 코드]	findById()를 수행할 때마다 조회수를 올리는 updateCnt() 메소드 수행
			 	
			 	[문제점 발견]	게시글 수정 화면 editForm.jsp에서도 findById()를 수행하기때문에
			 			    실제 게시글을 조회한 것이 아닌데도 조회수가 오르는 오작동이 있었다.
			 			    
			 	[해결방법]	updateCnt()를 여기서 빼고, list.jsp의 스크립트릿에 따로 추가했다.
			 	
			 	----- 아래 주석문은 바로 밑에서 updateCnt()를 사용했을 때 작성한 것이므로 실제 프로그램 동작과는 관련이 없다.(참고용) -----
			 	
			 	vo에 조회수를 넘겨줄때 1을 더하는 이유!?
			 	바로 밑에서 updateCnt(num)이 실행되어 실제로 조회수는 증가한다.
			 	
			 	그러나 rs에 담긴 값들은 updateCnt(num)이 실행되기 이전의 값들을 담고 있으므로
			 	이대로 데이터를 클라이언트에 넘긴다면 조회수 업데이트 이전의 데이터를 보내주는 것이 된다.
			 	
			 	그러므로 updateCnt(num)로 조회수가 증가한 것에 맞춰서
			 	vo에 데이터를 담을 때도 num+1을 더해서 보내주는 것이다.
			 	
			 	updateCnt(num); // 조회수 증가
			 	vo = new BoardVO(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						new Date(rs.getDate(5).getTime()),
						rs.getInt(6)+1);
			*/
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
	
	//수정(U)
	public int update(BoardVO vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		//Primary key인 num을 이용하여 제목, 작성자, 내용 수정
		String query = "update board set title=?, writer=?, content=? where num=?";
		int ret = -1;
		
		try {
			con = ju.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getWriter());
			pstmt.setString(3, vo.getContent());
			pstmt.setInt(4, vo.getNum());
			
			ret = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt);
		}
		
		return ret;
	}
	
	//카운터 증가
	public int updateCnt(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		//num으로 게시물을 찾아서 cnt(조회수)를 1 증가한다.
		String query = "update board set cnt=cnt+1 where num=?";
		int ret = -1;
		
		try {
			con = ju.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, num);
			ret = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt);
		}
		
		return ret;
	}
	
	//삭제(D)
	public int delete(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		//primary key인 num을 이용하여 삭제
		String query = "delete from board where num=?";
		//삭제된 행의 수
		int ret = -1;
		
		try {
			con = ju.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, num);
			ret = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt);
		}
		
		return ret;
	}
	
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
