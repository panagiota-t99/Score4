public class Main {

	public static void main(String[] args) {

		System.out.println("This is Score4");
		Player p1 = new Player(1);
		Player p2 = new Player(2,p1.getToken());
		Board b = new Board(p1,p2);
		
		while (!b.checkFullBoard() && !b.checkForWinner()) 
			b.playTurn();
		
		if (b.checkFullBoard() && !b.checkForWinner())
			System.out.println("GAME OVER. WE HAVE A DRAW.");
		if (b.checkForWinner())
			b.announceWinner();
	}
}
