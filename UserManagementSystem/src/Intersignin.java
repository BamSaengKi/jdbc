import java.util.List;
import java.util.Scanner;

public interface Intersignin {
	
	int insertUser(Scanner sc);
	
//	int interLogin(Scanner sc);
	UserDTO login(Scanner sc);
	
	List<UserDTO> selectAllUser();
	
	List<UserDTO> selectUserByAgeLine(int ageLine);
	
	int updateUser ( UserDTO loginuser, Scanner sc);
}
