import java.util.*;

public class Othello{
    
    private Board board;
    private OthelloBot AI;
    private final int height;
    private final int width;
    private final int BLACK = 1;
    private final int WHITE = -1;
    private int turn = 1;
    private int playerMarker = WHITE;

    public Othello(){
	board = new Board();
	AI = new OthelloBot();
	height = board.getHeight();
	width = board.getWidth();
    }

    public Othello(int turn){
	this();
	this.turn = turn;
	AI = new OthelloBot(WHITE);
	this.playerMarker = BLACK;
    }

    public void getBestMove(){
	//IntegerPair compMove = AI.getBestMove(board);
	ArrayList<IntegerPair> possibleMoves = board.getAvailableMoves(-playerMarker);
	IntegerPair compMove = possibleMoves.get(0);
	board.add(compMove.first(), compMove.second(), -playerMarker);
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
	String userInputRow = "";
	String userInputCol = "";
	int winner = 0;
	System.out.println(board);
	while (winner == 0 && !userInputRow.equals("q") && !userInputCol.equals("q")){
	    if (turn == 1){
		System.out.println("It is the Bot's turn!\n");
		System.out.println("The Bot is thinking...");
		getBestMove();
		turn *= -1;
	    }else{
		System.out.println("Your turn!");
		System.out.print("Enter row: ");
		userInputRow = getUserInput(possibleInputs);
		if (!userInputRow.equals("q")){
		    System.out.print("Enter column: ");
		    userInputCol = getUserInput(possibleInputs);
		}
		if (!userInputRow.equals("q") && !userInputCol.equals("q")){
		    int processedRow = Integer.parseInt(userInputRow) - 1;
		    int processedCol = Integer.parseInt(userInputCol) - 1;
		    if (board.add(processedRow, processedCol, playerMarker)){
			turn *= -1;
		    }else{
			String userPosition = userInputRow + ", " + userInputCol;
			System.out.println("Sorry, your chosen position: (" + userPosition + ") is not valid!");
		    }
		}
	    }
	    System.out.println(board);
	    winner = board.checkBoard();
	}
	if (!userInputRow.equals("q") && !userInputCol.equals("q")){
	    if (winner == 1){
		System.out.println("No Surprise, BotForTheWin!");
	    }else{
		System.out.println("You won!"); // this should not print
	    }
	}
    }
    
}
