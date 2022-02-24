import java.util.Scanner;

public class MainApp {
	
	private static Object loginuser;

	public static void menu() {
		System.out.println("=================== >> 메뉴 << ===================");
		System.out.println("1. 회원가입     2. 로그인    3. 로그아웃 \n" + "4. 내정보보기 " + "100. 종료");
		System.out.println("=================================================");
		System.out.print("\n > 메뉴번호 선택 :  ");
	}
	
	public static void main(String [] args) {
		
		Scanner sc = new Scanner(System.in);
		signin sn = new signin();
		String strMenuNo = "";
		UserDTO loginuser = null;
		do {
			MainApp.menu();
			strMenuNo = sc.nextLine();
			
			switch (strMenuNo) {
			case "1":
				int n = sn.insertUser(sc);
				
				String msg = (n==1)?">> 회원가입 성공!! <<":">> 회원가입 취소 또는 실패!! <<";
				System.out.println("\n" + msg + "\n");
				break;
			case "2":
				loginuser = sn.login(sc);				
				msg = (loginuser != null)?">> 로그인 성공!! <<" : ">> 로그인 실패!! <<";
				System.out.println("\n" + msg + "\n");
				break;
			case "3":
				
				if (loginuser != null) {
					loginuser = null;
					System.out.println(">> 로그아웃 되었습니다. << \n");
				} else {
					System.out.println(">> 현재 로그인 중이 아닙니다. << \n");
				}
				break;
			case "4":
				if (loginuser != null) {
					System.out.println(loginuser.showUserInfo());
				}else {
					System.out.println(">> 먼저 로그인 하세요. <<\n");
				}
				
				break;
			case "100":
				break;
			default:
				System.out.println(">> 메뉴에 없는 번호를 선택하셨습니다. \n");
				break;
					
			}
			
			if(loginuser != null) {
				System.out.println("=== >> " + loginuser.getUser_name()+ "님 현재 로그인 중 === <<");
				
			}
			
		}while (!"100".equals(strMenuNo));
		
		System.out.println(">>> 프로그램을 종료합니다. <<<");
		sc.close();
		
		
	}
}
