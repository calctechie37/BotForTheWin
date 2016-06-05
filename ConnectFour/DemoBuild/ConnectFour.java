import java.util.*;

public class ConnectFour
{
    
    private Board board;
    private ConnectFourBot AI;
    private final int height;
    private final int width;
    private int turn = 1;
    private String[] markers = {"X", "O"};

    public ConnectFour()
    {
	board = new Board();
	AI = new ConnectFourBot();
	height = board.getHeight();
	width = board.getWidth();
    }

    public ConnectFour(int turn)
    {
	this();
	this.turn = turn;
    }

    public void getBestMove()
    {
	IntegerPair compMove = AI.getBestMove(board);
	board.add(compMove.first(), compMove.second(), markers[turn]);
    }

    public String getUserInput(String[] possibleInputs)
    {
	System.out.println("Your turn!");
	System.out.print("Choose the column to place your marker: ");
	Scanner s = new Scanner(System.in);
	
	String ret = "";
	if (s.hasNext())
	    {
		ret = s.next();
		if (ret.equals("q"))
		    {
			System.out.println("Player forfeits.  Bot wins!");
			return "q";
		    }
		for(int i = 0; i < possibleInputs.length; i++)
		    {
			if(ret.equals(possibleInputs[i]))
			    {
				return ret;
			    }
		    }
		System.out.println("Not a valid input, fool!");
		return getUserInput(possibleInputs);
	    }
	return "";
    }
    
    public void run()
    {
	String[] possibleInputs = {"1", "2", "3", "4", "5", "6", "7"};
	String input = "";
	String winner = "";
	System.out.println(board);
	while (winner.equals("") && !input.equals("q"))
	    {
		if (turn == 1)
		    {
			System.out.println("It is the Bot's turn!\n");
			System.out.println("The Bot is thinking...");
			getBestMove();
			turn = 1 - turn;
		    }
		else
		    {
			input = getUserInput(possibleInputs);
			if (!input.equals("q"))
			    {
				int processedInput = Integer.parseInt(input) - 1;
				if (board.add(processedInput, "X"))
				    {
					turn = 1 - turn;
				    }
			    }
		    }
		System.out.println(board);
		winner = board.checkBoard();
	    }
	if (!input.equals("q"))
	    {
		if (winner.length() > 1)
		    {
			System.out.println("We have a draw!");
		    }
		else if (winner.equals(markers[1]))
		    {
			System.out.println("No surprise, BotForTheWin!");
		    }
		else
		    {
			System.out.println("You won!"); //Should never print...
		    }
	    }
    }
    
    public String toString()
    {
	String ans = "|";
        for(int i = height - 1; i >= 0; i--)
            {
                for(int j = 0; j < width; j++)
                    {
                        ans += board.getItem(i, j);
                        ans += "|";
                    }
                ans += "\n";
                if (i > 0)
                    {
                        ans += "|";
                    }
            }
        return ans;
    }    
}
