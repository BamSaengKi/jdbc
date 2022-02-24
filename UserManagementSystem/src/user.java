
public class user {
	private String user_name;
	private String user_login_id;
	private String user_login_pw;
	private int user_age;
	private String user_location;
	private String created_at;
	public user(String user_name, String user_login_id, String user_login_pw, int user_age, String user_location,
			String created_at) {
		super();
		this.user_name = user_name;
		this.user_login_id = user_login_id;
		this.user_login_pw = user_login_pw;
		this.user_age = user_age;
		this.user_location = user_location;
		this.created_at = created_at;
	}
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
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
}
