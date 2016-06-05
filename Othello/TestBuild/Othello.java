import java.util.*;

public class Othello{
    
    private Board board;
    private OthelloBot AI;
    private final int height;
    private final int width;
    private final int BLACK = 1;
    private final int WHITE = -1;
    private int turn = 1;
    private int playerMarker = BLACK;

    public Othello(){
	board = new Board();
	AI = new OthelloBot();
	height = board.getHeight();
	width = board.getWidth();
    }

    public Othello(int turn){
	this();
	this.turn = turn;
	this.playerMarker = WHITE;
    }

    public void getBestMove(){
	IntegerPair compMove = AI.getBestMove(board);
	board.add(compMove.first(), compMove.second(), 1);
    }
    
    public String getUserInput(String[] possibleInputs){
	Scanner s = new Scanner(System.in);
	
	String ret = "";
	if (s.hasNext()){
	    ret = s.next();
	    if (ret.equals("q")){
		System.out.println("Player forfeits.  Bot wins!");
		return "q";
	    }
	    for(int i = 0; i < possibleInputs.length; i++){
		if (ret.equals(possibleInputs[i])){
		    return ret;
		}
	    }
	    System.out.println("Not a valid input, fool!");
	    return getUserInput(possibleInputs);
	}
	return "";
    }

    public void run(){
	String[] possibleInputs = {"1", "2", "3", "4", "5", "6", "7", "8"};
	String input = "";
	int winner = 0;
	System.out.println(board);
	while (winner == 0 && !input.equals("q")){
	    if (turn == 1){
		System.out.println("It is the Bot's turn!\n");
		System.out.println("The Bot is thinking...");
		getBestMove();
		turn *= -1;
	    }else{
		System.out.println("Your turn!");
		System.out.print("Enter row: ");
		userInputRow = getUserInput(possibleInputs);
		System.out.print("Enter column: ");
		userInputCol = getuserInput(possibleInputs);
		if (!userInputRow.equals("q") && !userInputcol.equals("q")){
		    int processedRow = Integer.parseInt(input) - 1;
		    int processedCol = Integer.parseInt(input) - 1;
		    if (board.add(processedRow, processedCol, 1)){
			turn *= -1;
		    }else{
			String userPosition = userInputRow + ", " + userInputCol
			System.out.println("Sorry, your chosen position: (" + userPosition + ") is not valid!")
		    }
		}
	    }
	    System.out.println(board);
	    winner = board.checkBoard();
	}
	if (!input.equals("q")){
	    if (winner == 1){
		System.out.println("No Surprise, BotForTheWin!");
	    }else{
		System.out.println("You won!"); // this should not print
	    }
	}
    }
    
}
