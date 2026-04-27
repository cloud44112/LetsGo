package UserTest;

import static org.junit.Assert.*;
import org.junit.Test;

import com.letsgo.place.model.UserDAO;
import com.letsgo.place.model.UserVO;

public class 유저테스트 {
	@Test
	public void 로그인() {
		
		String userID = "user01";
		String password = "pass123";
		
		UserDAO userDao = new UserDAO(); 
		UserVO user = userDao.login(userID, password);
		
		assertNotNull(user);
		assertEquals(userID, user.getUserID());
		
		System.out.println("로그인 성공 :" + user.getName() );
	}
	
	
	public void 회원가입() {

	    UserDAO userDao = new UserDAO();
	    boolean result = userDao.signup("user11", "user11@test.com", "김철수", "pass456");
	    
	    assertTrue("회원가입 성공", result);
	    System.out.println("성공");

	}
}
