public class Test3
{
    public static void main(String[] args)
    {
	ConnectFour game = new ConnectFour();
	System.out.println("This test requires Board.java to not be in debug mode...");
	if (game.inDebugMode())
	    {
		System.out.println("Board.java is in debug mode, exiting now!");
		System.exit(0);
	    }
	else
	    {
		System.out.println("Board.java is not in debug mode!");
	    }
	game.getBestMove();
	System.out.println(game);
    }
}
