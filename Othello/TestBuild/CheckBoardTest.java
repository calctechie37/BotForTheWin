public class CheckBoardTest{
    
    public static void main(String[] args){
	Board game = new Board();
	System.out.println("Initial Board Setup");
	System.out.println(game);
	game.add(4, 5, 1);
	System.out.println(game);
	System.out.println("Winner: " + game.checkBoard());
	game.add(2, 3, 1);
	System.out.println(game);
	System.out.println("Winner: " + game.checkBoard()); 
	Board game2 = new Board();
	System.out.println("Initial Board setup");
	System.out.println(game2);
	game2.add(5, 3, -1);
	System.out.println(game2);
	System.out.println("Winner: " + game2.checkBoard());
	game2.add(3, 5, -1);
	System.out.println(game2);
	System.out.println("Winner: " + game2.checkBoard());
	// Add in a test for a full board
	

    }
}
