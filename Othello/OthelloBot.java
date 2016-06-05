import java.util.*;

public class OthelloBot{

    private Board board;
    private IntegerPair lastMove;
    private IntegerPair compMove;
    private int turn = 1;
    private int height;
    private int width;
    private int searchDepth = 8;
    
    public OthelloBot(){
    }

    public OthelloBot(int turn){
	this.turn = turn;
    }

    public IntegerPair getBestMove(Board board){
	this.board = board;
	height = board.getHeight();
	width = board.getWidth();
	int score = minimax(turn, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
	return compMove;
    }

    private ArrayList<IntegerPair> availableMoves(){
	return board.getAvailableMoves();
    }

    private int minimax(int turncopy, int depth, int alpha, int beta){
    }
    
    private int evaluate(String winner, int depth){
    }

}
