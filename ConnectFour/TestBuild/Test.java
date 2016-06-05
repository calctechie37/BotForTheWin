public class Test
{
    public static void main(String[] args)
    {
	ConnectFour game1 = new ConnectFour();
	System.out.println("The test requires Board.java to be in debug mode...");
	boolean debugModeOn = game1.inDebugMode();
	if (!debugModeOn)
	    {
		System.out.println("Board.java is not set in debug mode, exiting now!");
		System.exit(0);
	    }
	else
	    {
		System.out.println("Board.java is in debug mode");
	    }
	game1.add(0, "X");
	game1.add(0, 1, "X");
	game1.add(0, 2, "X");
	game1.add(0, 3, "X");
	System.out.println(game1);
	System.out.println("The winner is " + game1.checkBoard() + "\n");
	game1.remove(3);
	System.out.println(game1);
	System.out.println("The winner is " + game1.checkBoard() + "\n");
	game1.add(2, "X");
	game1.add(2, "X");
	game1.add(2, "X");
	game1.remove(0, 1);
	System.out.println(game1);
	System.out.println("The winner is " + game1.checkBoard() + "\n");
	game1.remove(2);
	game1.remove(2);
	game1.remove(2);
	game1.add(0, "X");
	game1.add(1, "O");
	game1.add(1, "X");
	game1.add(2, "O");
	game1.add(2, "X");
	game1.add(3, "X");
	game1.add(3, "X");
	game1.add(3, "O");
	game1.add(3, "X");
	System.out.println(game1);
	System.out.println("The winner is " + game1.checkBoard(3, 3) + "\n");
	System.out.println("The winner is " + game1.checkBoard(2, 2) + "\n");
	game1.add(0, "O");
	game1.add(0, "X");
	game1.remove(1);
	game1.add(1, "O");
	game1.add(1, "X");
	game1.remove(2);
	game1.remove(2);
	game1.add(2, "X");
	System.out.println(game1);
	System.out.println("The winner is " + game1.checkBoard(0,3) + "\n");
	System.out.println("The winner is " + game1.checkBoard(1,2) + "\n");
	game1.remove(2);
	System.out.println(game1);
	System.out.println("The winner is " + game1.checkBoard() + "\n");
    }
}
