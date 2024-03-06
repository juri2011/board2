package common;

public class JdbcUtil {
	/*
		싱글톤 처리
		싱글톤 패턴은 정보를 보관하고 공유하고자 하는 클래스를
		한 번만 메모리에 할당하고 객체로 관리하기 위해 사용한다.
	*/
	private static JdbcUtil instance = new JdbcUtil();
	private JdbcUtil() {}
}
