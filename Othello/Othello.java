import java.util.*;

public class Othello{
    
    private Board board;
    private OthelloBot AI;
    private final int height;
    private final int width;
    private int turn = 1;
   
    public Othello(){
	board = new Board();
	AI = new OthelloBot();
	height = board.getHeight();
	width = board.getWidth();
    }

    public Othello(int turn){
	this();
	this.turn = turn;
    }

    public void getBestMove(){
	IntegerPair compMove = AI.getBestMove(board);
	board.add(compMove.first(), compMove.second(), 1);
    }
    
    public String getUserInput(String[] possibleInputs){
	System.out.println("Your turn!");
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
		input = getUserInput(possibleInputs);
		if (!input.equals("q")){
		    int processedInput = Integer.parseInt(input) - 1;
		    if (board.add(procesedInput, 1)){
			turn *= -1;
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
