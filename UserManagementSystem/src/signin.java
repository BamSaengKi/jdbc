import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class signin implements Intersignin {
	
	private UserDAO userDao = new UserDAO();
	private UserDTO userDto = new UserDTO();
	
	
	@Override
	public int insertUser(Scanner sc) {
		
		int result = 0;
		
		System.out.println("> 이름 :  ");
		String user_name = sc.nextLine();
		
		System.out.println("> ID :  ");
		String user_id = sc.nextLine();
		
		System.out.println("> PW :  ");
		String user_pw = sc.nextLine();
		
		System.out.println("> 나이 :  ");
		String user_age = sc.nextLine();
		
		System.out.println("> 지역 :  ");
		String user_location = sc.nextLine();
		
		UserDTO userdto = new UserDTO();
		
		userdto.setUser_name(user_name);
		userdto.setUser_login_id(user_id);
		userdto.setUser_login_pw(user_pw);
		userdto.setUser_age(Integer.parseInt(user_age));
		userdto.setUser_location(user_location);
		
		result = userDao.insertUser(userdto, sc);
		
		return result;
		
		
		
		
		
		
		
	}


	@Override
	public UserDTO login(Scanner sc) {
		UserDTO loginuser = null;
		
		System.out.println("> ID : ");
		String user_login_id = sc.nextLine();
		
		System.out.println("> PW : ");
		String user_login_pw = sc.nextLine();
		
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("user_login_id", user_login_id);
		paraMap.put("user_login_pw", user_login_pw);
		
		loginuser = userDao.login(paraMap);

		return loginuser;
	}
	
	
	
	public List<UserDTO> selectAllUser(){
		List<UserDTO> userList = null;
		userList = userDao.selectAllUser();
			
		return userList;
		
		
	}


	@Override
	public List<UserDTO> selectUserByAgeLine(int ageLine) {
			List<UserDTO> userList = null;
			
			userList = userDao.selectUserByAgeLine(ageLine);
					
		return userList;
	}


	@Override
	public int updateUser(UserDTO loginuser, Scanner sc) {
           int n = 0;
           System.out.println("> 암호 :   ");
           String user_login_pw = sc.nextLine();
           
           if(!"".equals(user_login_pw.trim())) {
        	   loginuser.setUser_login_pw(user_login_pw);
           }
           
           System.out.println("> 성명 :   ");
           String user_name = sc.nextLine();
           
           if(!"".equals(user_name)) {
        	   loginuser.setUser_name(user_name);
           }
           
           System.out.println("> 주소 :   ");
           String user_loaction = sc.nextLine();
           
           if(!"".equals(user_loaction)) {
        	   loginuser.setUser_location(user_loaction);
           }
           
           n = userDao.updateUser(loginuser, sc);
		return n;
	}
	
	
	
	
	

}
