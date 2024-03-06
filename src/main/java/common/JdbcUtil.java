package common;

import javax.sql.DataSource;

public class JdbcUtil {
	/*
		싱글톤 처리
		싱글톤 패턴은 정보를 보관하고 공유하고자 하는 클래스를
		한 번만 메모리에 할당하고 객체로 관리하기 위해 사용한다.
	*/
	private static JdbcUtil instance = new JdbcUtil();
	
	/*
		DataSource?
		커넥션풀의 Connection 객체를 관리하기 위해 사용하는 객체.
		필요한 Connection을 빌려주고 반납하는 작업을 한다.
			1. lookup() 메소드로 DataSource 객체를 얻는다.
			2. getConnection 메소드로 Connection Pool에서 Connection 객체를 얻는다.
			3. Connection 객체로 DB 작업
			4. 작업이 끝나면 Connection Pool에 Connection 반납
	*/
	private static DataSource ds;
	
	/*
		static {} ?
		static block(스태틱 블록)
		클래스 변수를 초기화시키는 용도로 사용한다.
		클래스가 로딩되고 클래스 변수가 로딩되면 자동으로 실행된다.
	*/
	static {
		try {
			/*
 				Class.forName()?
 				런타임 동적 로딩 : 실행 중에 JVM이 코드를 만나는 순간 클래스를 로딩
 				밑의 코드는 Oracle Driver를 로드한다.
			*/
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("드라이버 로딩 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private JdbcUtil() {}
	public static JdbcUtil getInstance() {
		return instance;
	}
	
	
	
}
