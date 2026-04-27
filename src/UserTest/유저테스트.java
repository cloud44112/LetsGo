//package UserTest;
//
//import static org.junit.Assert.*;
//import org.junit.Test;
//
//import com.letsgo.place.model.UserDAO;
//import com.letsgo.place.model.UserVO;
//
//public class �����׽�Ʈ {
//	@Test
//	public void �α���() {
//		
//		String userID = "user01";
//		String password = "pass123";
//		
//		UserDAO userDao = new UserDAO(); 
//		UserVO user = userDao.login(userID, password);
//		
//		assertNotNull(user);
//		assertEquals(userID, user.getUserID());
//		
//		System.out.println("�α��� ���� :" + user.getName() );
//	}
//	
//	
//	public void ȸ������() {
//
//	    UserDAO userDao = new UserDAO();
//	    boolean result = userDao.signup("user11", "user11@test.com", "��ö��", "pass456");
//	    
//	    assertTrue("ȸ������ ����", result);
//	    System.out.println("����");
//
//	}
//}
