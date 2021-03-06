
public class UserDTO {
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_login_id() {
		return user_login_id;
	}
	public void setUser_login_id(String user_login_id) {
		this.user_login_id = user_login_id;
	}
	public String getUser_login_pw() {
		return user_login_pw;
	}
	public void setUser_login_pw(String user_login_pw) {
		this.user_login_pw = user_login_pw;
	}
	public int getUser_age() {
		return user_age;
	}
	public void setUser_age(int user_age) {
		this.user_age = user_age;
	}
	public String getUser_location() {
		return user_location;
	}
	public void setUser_location(String user_location) {
		this.user_location = user_location;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	private int user_id;
	private String user_name;
	private String user_login_id;
	private String user_login_pw;
	private int user_age;
	private String user_location;
	
	
	
	
	
	
	public String showUserInfo() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("=== 나의 정보 === \n");
		sb.append("1. 아이디 : " + user_login_id + "\n");
		sb.append("2. 암호 : " + user_login_pw + "\n");
		sb.append("3. 성명 : " + user_name + "\n");
		sb.append("4. 지역 : " + user_location + "\n");
		
		return sb.toString();
		
	}
	
	
	public String toString() { 
		StringBuilder sb = new StringBuilder();
		
		sb.append(user_id + "\t" + user_login_id + "\t" + user_login_pw + "\t" + user_name + "\t"+ user_location + "\t"  + user_age + "\t");
		
		return sb.toString();
	}

}
