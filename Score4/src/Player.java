import java.util.Scanner;

public class Player {
	
	private String name;
	private char token;
	
	public Player(int i){
		setName(i);
		setToken(i);	
	}
	
	public Player(int i , char token){
		setName(i);
		if (token == 'x')
			this.token = 'o';
		else
			this.token = 'x';
		System.out.println(name + ", your chip is: " + this.token);
	}
	
	private void setName(int i){
		Scanner input = new Scanner(System.in);
		if (i==1)
			System.out.print("Please enter the name of the first player: ");
		else
			System.out.print("Please enter the name of the second player: ");
		name = input.nextLine();	
	}
	
	private void setToken(int i) {
		Scanner input = new Scanner(System.in);
		Boolean flag = false;
		while (!flag) {
			System.out.print(name + ", please select your chip: ");
			String line = input.nextLine();
			if (line.length()>1)
				System.out.println("Please enter X or O!");
			else
			{
				token = line.charAt(0);
				if (token == 'x' || token == 'o' )
					flag = true;
				else 
					System.out.println("Please enter x or o!");
			}
		}
	}

	public String getName() {
		return name;
	}

	public char getToken() {
		return token;
	}
}
