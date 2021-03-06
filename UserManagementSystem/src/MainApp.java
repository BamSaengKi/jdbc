import java.util.List;
import java.util.Scanner;

public class MainApp {

	public static void menu() {
		System.out.println("=================== >> 메뉴 << ===================");
		System.out.println("1. 회원가입     2. 로그인    3. 로그아웃   " + "4. 내정보보기  \n" + "5. 모든 회원 정보 보기  " + "6. 연령대별 검색" +  "7. 내 정보 변경  " +  " 100. 종료");
		System.out.println("=================================================");
		System.out.print("\n > 메뉴번호 선택 :  ");
	}
	
	public static void main(String [] args) {
		
		Scanner sc = new Scanner(System.in);
		signin sn = new signin();
		
		String strMenuNo = "";
		UserDTO loginuser = null;
		
		int n = 0;
		do {
			MainApp.menu();
			strMenuNo = sc.nextLine();
			
			switch (strMenuNo) {
			case "1":
				n = sn.insertUser(sc);
				
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
				
			case "5":
				
				List<UserDTO> userList = sn.selectAllUser();
				
								
				if (userList.size() == 0) {
					System.out.println(">> 현재 가입된 회원이 1명도 없습니다. << \n");
					
				}else { 
					printUserInfo(userList);
				}
				
				break;
			case "6":
				int ageLine = 0;
				
				do {
					try {
						System.out.println("\n > 검색하고자 하는 연령대 : ");
						ageLine = Integer.parseInt(sc.nextLine());
						
						if(!(ageLine == 10 || ageLine == 20 || ageLine == 30 || ageLine == 40 || ageLine == 50 || ageLine == 60 || ageLine == 70 || ageLine == 80 || ageLine == 90)) {
							System.out.println(">> 연령대가 20대면 20, 30대면 30으로 입력해주세요. <<");
						}else
							break;
					} catch (NumberFormatException e) {
							System.out.println(">> 숫자만 입력하세요. <<");
					}
					
				} while (true);
				
				userList = sn.selectUserByAgeLine(ageLine);
				
				if (userList == null) {
					System.out.println(">> 현재 가입된 회원 중 연령대가 " + ageLine + " 대인 회원은 1명도 없습니다. << \n");
				}
				else {
					printUserInfo(userList);
				}
				
				break;
				
			case "7":
				if (loginuser == null) { 
					System.out.println(">> 먼저 로그인하세요. <<");
					continue;
				}else {
					System.out.println("\n" + loginuser.showUserInfo());
					n = sn.updateUser(loginuser, sc);
					System.out.println(n);
					
					
					
					if ( n == 1 ) {
						System.out.println(">> 내정보 수정하기 성공했습니다. <<");
					}
					else { 
						System.out.println(">> 내 정보 수정하기 실패 또는 취소했습니다. <<");
					}
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
	
	public static void printUserInfo(List<UserDTO> userList) {
		
		System.out.println("--------------------------------------------------");
		System.out.println("등록번호\t아이디\t암호\t성명\t지역\t나이");
		System.out.println("--------------------------------------------------");
		
		for (UserDTO user : userList) {
			System.out.println(user);
		}
		System.out.println("\n\n");
	}
	
	
}



