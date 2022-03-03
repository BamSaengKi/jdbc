import java.util.List;
import java.util.Scanner;

public interface InterUserDAO {
	
	int insertUser(UserDTO userdto, Scanner sc);
	
	List<UserDTO> selectAllUser();
	
	List<UserDTO> selectUserByAgeLine(int ageLine);
	

}
