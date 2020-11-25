import java.util.Random;
import java.util.Scanner;

public class Board {
	
	private Player p1;
	private Player p2;
	
	private int rows;
	private int cols;
	private char board[][];
	
	private int turn; //Player1=0 , Player2=1
	private boolean flagAvalaible;
	
	public Board(Player p1, Player p2) {
		this.p1 = p1;
		this.p2 = p2;
		turn = getRandomTurn();
		
		rows = getRows_Cols("rows");
		cols = getRows_Cols("columns");
		board = new char[rows][cols];
		
		int i,j;
		for ( i=0; i<rows; i++)
			for ( j=0; j<cols; j++)
				board[i][j] = '-';
		
		printBoard();	
	}
	
	private int getRandomTurn() {
		Random r = new Random();
		return r.nextInt(2);	
	}
	

	private int getRows_Cols(String s) {
		Scanner input = new Scanner(System.in);
		int x = 0;
		String line;
		
		System.out.print("Please enter the number of " + s + ": ");
		while (x < 4 || x > 15) {
			line = input.nextLine();
			try {
				x = Integer.parseInt(line);
			}catch (NumberFormatException e){}
			if (x <4 || x >15)
				System.out.print("Incorrect input. Please enter a number between [4,15] : ");
		}
		return x;
	}
	
	private void printBoard() {
		int i,j;
		for (i=0; i<rows; i++) {
			System.out.print("|");
			for ( j=0; j<cols; j++) 
				System.out.print(" " + board[i][j]);
			System.out.print( " | \n");
		}	
		
		for ( j=0; j< 2*cols + 3; j++) 
			System.out.print("-");
		System.out.println();
		
		System.out.print("  ");
		for ( j=0; j< cols; j++) 
			System.out.print(j+1 + " ");
		System.out.println();	
	}
	
	public void playTurn() {
		Scanner input = new Scanner(System.in);
		String col;
		int r,c;
		flagAvalaible = false;
		
		if (turn == 0)
			System.out.print(p1.getName() + " your turn. Select column: ");
		else 
			System.out.print(p2.getName() + " your turn. Select column: ");
		col = input.nextLine();
		
		while (!flagAvalaible) {
			while (!checkColInput(col)) {
				System.out.print("Please insert a number between 1 and " + cols + ": ");
				col = input.nextLine();
			}
			c = Integer.parseInt(col);
			r = checkColAvailability(c);
			if (r != -1)
				makeTheMove(r,c);
			else {
				System.out.println("The column you have chosen has no empty space!");
				System.out.println("Please choose another column: ");
				col = input.nextLine();
			}
		}
	}

	
	private boolean checkColInput(String s) {
		if (s.length()== 1 && Character.isDigit(s.charAt(0)) &&
				Integer.parseInt(s)>=1 && Integer.parseInt(s)<=cols)
			return true;
		else if (s.length() == 2 && Character.isDigit(s.charAt(0)) &&
				Character.isDigit(s.charAt(1))&& Integer.parseInt(s)>=1 && Integer.parseInt(s)<=cols)
			return true;
		return false;
	}
	
	private int checkColAvailability(int j) {
		for (int i=rows-1; i>=0; i--){
			if (board[i][j-1] == '-')
				return i;
		}
		return -1;
	}
	
	private void makeTheMove(int r,int c) {
		if (turn == 0)
			board[r][c-1] = p1.getToken();
		else
			board[r][c-1] = p2.getToken();
		flagAvalaible = true;
		turn = (turn + 1)%2;
		printBoard();	
	}

	private boolean checkAcross() {
		int i,j;
		for (i=0;i<rows; i++)
			for (j=0;j<cols - 3;j++)
				if (board[i][j]!= '-' &&board[i][j] == board[i][j+1] && 
						board[i][j+1] == board[i][j+2] &&
						board[i][j+2] == board[i][j+3])
					return true;
		return false;
	}
	
	private boolean checkUpAndDown() {
		int i,j;
		for (j=0;j<cols; j++)
			for (i=0;i<rows - 3;i++) 
				if (board[i][j]!= '-' && board[i][j] == board[i+1][j] &&
						board[i+1][j] == board[i+2][j] && 
						board[i+2][j] == board[i+3][j])
					return true;
		return false;
	}

	private boolean checkDiagonallyLeft() {
		int i,j;
		for (i=0;i<rows-3;i++)
			for (j=0;j<cols-3;j++) 
				if (board[i][j] != '-' && board[i][j] == board[i+1][j+1] &&
						board[i+1][j+1] == board[i+2][j+2] &&
						board[i+2][j+2] == board[i+3][j+3] )
					return true;
		return false;
	}

	private boolean checkDiagonallyRight() {
		int i,j;
		for (i=3;i<rows;i++)
			for (j=0;j<cols - 3;j++)
				if (board[i][j]!='-' && board[i][j] == board[i-1][j+1] &&
						board[i-1][j+1] == board[i-2][j+2] &&
						board[i-2][j+2] == board[i-3][j+3])
					return true;
		return false;
	}

	public boolean checkForWinner() {
		if (checkAcross() || checkUpAndDown() || 
				checkDiagonallyLeft() || checkDiagonallyRight())
			return true;
		return false;
			
	}

	public void announceWinner() {
		if (turn == 0)
			System.out.println("GAME OVER. THE WINNER IS : " + p2.getName());
		else 
			System.out.println("GAME OVER. THE WINNER IS : " + p1.getName());
	}
		
	public boolean checkFullBoard() {
		int i,j;
		for (i=0;i<rows;i++)
			for (j=0;j<cols;j++)
				if (board[i][j] == '-')
					return false;
		return true;
	}
}


