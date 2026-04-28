package UserTest;

import static org.junit.Assert.*;
import org.junit.Test;

import com.letsgo.place.model.UserDAO;
import com.letsgo.place.model.UserVO;

public class 유저테스트 {
    
    
    public void 로그인테스트() throws Exception {
        String userID = "user01";
        String password = "pass123";
        
        UserDAO userDao = new UserDAO();
        UserVO user = userDao.login(userID, password);
        
        assertNotNull(user);
        assertEquals(userID, user.getUserID());
        
        System.out.println("로그인 성공: " + user.getName());
    }
    
  
    public void 회원가입테스트() throws Exception {
        UserDAO userDao = new UserDAO();
        boolean result = userDao.signup("user11", "user11@test.com", "홍길동", "pass456");
        
        assertTrue("회원가입이 성공좀.", result);
        System.out.println("회원가입 성공");
        
    }
    
    @Test
    public void 아이디체크() throws Exception {
    	
        UserDAO userDao = new UserDAO();
        boolean exists = userDao.idcheck("user01");
        assertTrue("존재하는 아이디", exists);
        System.out.println("아이디 존재 여부: " + exists);

    }
    
}