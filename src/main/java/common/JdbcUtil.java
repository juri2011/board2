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
	
	private JdbcUtil() {}
	public static JdbcUtil getInstance() {
		return instance;
	}
	
	
	
}
