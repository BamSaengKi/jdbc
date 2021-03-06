import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserDAO implements InterUserDAO{

	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	private String url = "jdbc:mysql://localhost:3306/user_management?serverTimezone=UTC";
	private String user = "root";
	private String password = "785643";
	private String driverName = "com.mysql.cj.jdbc.Driver";
	
	
	
	
	
	@Override
	public int insertUser(UserDTO userdto, Scanner sc) {
		
		
		
		int result = 0;
		
		try {
			Class.forName(driverName);			
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
			
			String sql = ("INSERT INTO t_user (user_name, user_login_id, user_login_pw, user_age, user_location)VALUES(?,?,?,?,?);");
			
			   stmt = conn.prepareStatement(sql);
			   
			   stmt.setString(1, userdto.getUser_name());
			   stmt.setString(2, userdto.getUser_login_id());
			   stmt.setString(3, userdto.getUser_login_pw());
			   stmt.setInt(4, userdto.getUser_age());
			   stmt.setString(5, userdto.getUser_location());
			   result = stmt.executeUpdate();
//			   rs = stmt.getGeneratedKeys();
			   
			   do {
				   System.out.println(">> 정말로 회원 가입을 하시겠습니까? [Y/N]");
				   String yn = sc.nextLine();
				   
				   if(result != 0 && ("Y".equals(yn) || "y".equals(yn))) {
					   conn.commit();
					   break;
				   } else if(result != 0 && ("N".equals(yn) || "n".equals(yn))) {
					   conn.rollback();
					   result = 0;
					   break;
				   }
				   
			   }while(true); 
				   
			   
			
		} catch (ClassNotFoundException e) {
			System.out.println(">> ojdbc6.jar 파일이 없습니다. <<");
			e.printStackTrace();
			// TODO: handle exception
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
		
		return result;
		

		
		
	}



	public UserDTO login(Map<String, String> paraMap) {
		
		UserDTO userdto = null;
		
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, user, password);
			String sql = "SELECT user_id, user_name, user_login_id, user_login_pw, user_age,user_location FROM t_user WHERE user_login_id = ? and user_login_pw = ?;";
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, paraMap.get("user_login_id"));
			stmt.setString(2, paraMap.get("user_login_pw"));
			
			rs = stmt.executeQuery();
			
			
			if(rs.next()) {
				int user_id = rs.getInt("user_id");
				String user_name = rs.getString("user_name");
				String user_login_id = rs.getString("user_login_id");
				String user_login_pw = rs.getString("user_login_pw");
				String user_age = rs.getString("user_age");
				String user_location = rs.getString("user_location");
				
				userdto = new UserDTO();
				userdto.setUser_id(user_id);
				userdto.setUser_name(user_name);
				userdto.setUser_login_id(user_login_id);
				userdto.setUser_login_pw(user_login_pw);
				userdto.setUser_age(user_id);
				userdto.setUser_location(user_location);
				
				
				
			}
			
			} catch (ClassNotFoundException e) {
				System.out.println(">> 애미가 없습니다. <<");
				e.printStackTrace();
			}catch (SQLException e ) {
				e.printStackTrace();
			}finally {
				try {
					if (rs != null) rs.close();
					if (stmt != null) stmt.close();
					if (conn != null) conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		
		return userdto;
	}



	@Override
	public List<UserDTO> selectAllUser() {
			
		List<UserDTO> userList = new ArrayList<UserDTO>();
		
		try {
			Class.forName(driverName);
			
			conn = DriverManager.getConnection(url, user, password);
			
			String sql = "SELECT user_id, user_login_id, user_login_pw, user_name, user_location , user_age FROM t_user";
			
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			int cnt = 0;
			while (rs.next()) {
				cnt++;
				if ( cnt == 1 ) 
				userList = new ArrayList<UserDTO>();
				int user_id = rs.getInt("user_id");
				String user_login_id = rs.getString("user_login_id");
				String user_login_pw = rs.getString("user_login_pw");
				String user_name = rs.getString("user_name");
				String user_location = rs.getString("user_location");
				int user_age = rs.getInt("user_age");
				
			
					
					
				
				
				UserDTO userdto = new UserDTO();
				
				userdto.setUser_id(user_id);
				userdto.setUser_login_id(user_login_id);
				userdto.setUser_login_pw(user_login_pw);
				userdto.setUser_name(user_name);
				userdto.setUser_location(user_location);
				userdto.setUser_age(user_age);
				

				userList.add(userdto);

				
				
				
				
				
				
				
			}
					
		} catch (ClassNotFoundException e) {
			System.out.println(">> 뭐가... 없다는데요? <<");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if ( rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
				
			} catch (SQLException e) {
					e.printStackTrace();
					
			}
		}
		return userList;
	}



	@Override
	public List<UserDTO> selectUserByAgeLine(int ageLine) {
			List<UserDTO> userList = null;
			
			try {
				Class.forName(driverName);
				
				conn = DriverManager.getConnection(url , user, password);
				
				String sql = "SELECT user_id , user_name ,  user_login_id, user_login_pw, user_location, user_age From t_user WHERE LEFT(user_age, 1) = LEFT(?,1)  ORDER BY user_id DESC";
				
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, ageLine);
				
				rs = stmt.executeQuery();
				
				int cnt = 0;
				while (rs.next()) {
					
					cnt++;
					if(cnt == 1)
					userList = new ArrayList<UserDTO>();
					
					
					int user_id = rs.getInt("user_id");
					String user_login_id = rs.getString("user_login_id");
					String user_login_pw = rs.getString("user_login_pw");
					String user_name = rs.getString("user_name");
					String user_location = rs.getString("user_location");
					int user_age = rs.getInt("user_age");
					
					UserDTO userdto = new UserDTO();
					
					userdto.setUser_id(user_id);
					userdto.setUser_name(user_name);
					userdto.setUser_login_id(user_login_id);
					userdto.setUser_login_pw(user_login_pw);
					userdto.setUser_location(user_location);
					userdto.setUser_age(user_age);
					userList.add(userdto);
				}
				
			} catch (ClassNotFoundException e) {
				System.out.println(">> 이게 없을리가 없지 ㅅㅂ");
				e.printStackTrace();	
			} catch (SQLException e) {
				e.printStackTrace();
				
			} finally {
				try {
					if ( rs != null)
						rs.close();
					if ( stmt != null)
						stmt.close();
					if ( conn != null)
						conn.close();
					
				} catch (SQLException e) {
						e.printStackTrace();
				}
			}
			
			
		return userList;
	}



	@Override
	public int updateUser(UserDTO loginuser, Scanner sc) {
		int result = 0;
		
		try {
			Class.forName(driverName);
			
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit( false );
			
			String sql = " UPDATE t_user SET user_login_pw = ? , user_name = ? , user_location = ? WHERE user_login_id = ?";
			
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, loginuser.getUser_login_pw());
			stmt.setString(2, loginuser.getUser_name());
			stmt.setString(3, loginuser.getUser_location());
			stmt.setString(4, loginuser.getUser_login_id());
			
			result = stmt.executeUpdate();
			
			do {
				System.out.println(">> 정말로 회원 정보 수정을 하시겠습니까? [Y/N] = >   ");
				String yn = sc.nextLine();
				
				if ( result ==1 && ("Y".equals(yn) || "y".equals(yn))) {
					conn.commit();
					break;
				} else if ( result == 1 && ("N".equals(yn)|| "n".equals(yn))) {
					conn.rollback();
					result = 0;
					break;
				}
			}while(true) ;			
		} catch (ClassNotFoundException e) {
			System.out.println("섹스");
			e.printStackTrace();
		} catch (SQLException e ) {
			e.printStackTrace();
		} finally {
			try {
				if ( stmt != null) stmt.close();
				if ( conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	

}


