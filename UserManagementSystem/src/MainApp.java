import java.util.Scanner;

public class MainApp {
	
	private static Object loginuser;

	public static void menu() {
		System.out.println("=================== >> �޴� << ===================");
		System.out.println("1. ȸ������     2. �α���    3. �α׾ƿ� \n" + "4. ���������� " + "100. ����");
		System.out.println("=================================================");
		System.out.print("\n > �޴���ȣ ���� :  ");
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
				
				String msg = (n==1)?">> ȸ������ ����!! <<":">> ȸ������ ��� �Ǵ� ����!! <<";
				System.out.println("\n" + msg + "\n");
				break;
			case "2":
				loginuser = sn.login(sc);				
				msg = (loginuser != null)?">> �α��� ����!! <<" : ">> �α��� ����!! <<";
				System.out.println("\n" + msg + "\n");
				break;
			case "3":
				
				if (loginuser != null) {
					loginuser = null;
					System.out.println(">> �α׾ƿ� �Ǿ����ϴ�. << \n");
				} else {
					System.out.println(">> ���� �α��� ���� �ƴմϴ�. << \n");
				}
				break;
			case "4":
				if (loginuser != null) {
					System.out.println(loginuser.showUserInfo());
				}else {
					System.out.println(">> ���� �α��� �ϼ���. <<\n");
				}
				
				break;
			case "100":
				break;
			default:
				System.out.println(">> �޴��� ���� ��ȣ�� �����ϼ̽��ϴ�. \n");
				break;
					
			}
			
			if(loginuser != null) {
				System.out.println("=== >> " + loginuser.getUser_name()+ "�� ���� �α��� �� === <<");
				
			}
			
		}while (!"100".equals(strMenuNo));
		
		System.out.println(">>> ���α׷��� �����մϴ�. <<<");
		sc.close();
		
		
	}
}