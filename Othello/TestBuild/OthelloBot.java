import java.util.*;

public class OthelloBot{

    private Board board;
    private ArrayList<IntegerPairArray> changesTracker;
    private IntegerPair lastMove;
    private IntegerPair compMove;
    private final static int BLACK = 1;
    private final static int WHITE = -1;
    private int myColor = BLACK;
    private int height;
    private int width;
    private int searchDepth = 2;
    private int score[][] = {{20, -3, 11, 8, 8, 11, -3, 20},
			     {-3, -7, -4, 1, 1, -4, -7, -3},
			     {11, -4, 2, 2, 2, 2, -4, 11},
			     {8, 1, 2, -3, -3, 2, 1, 8},
			     {8, 1, 2, -3, -3, 2, 1, 8},
			     {11, -4, 2, 2, 2, 2, -4, 11},
			     {-3, -7, -4, 1, 1, -4, -7, -3},
			     {20, -3, 11, 8, 8, 11, -3, 20}};
    private boolean easyDifficulty = true;

    public OthelloBot(){
	changesTracker = new ArrayList<IntegerPairArray>();
    }

    public OthelloBot(int myColor){
	this();
	this.myColor = myColor;
    }

    public IntegerPair getBestMove(Board board){
	this.board = board;
	height = board.getHeight();
	width = board.getWidth();
	System.out.println(myColor);
	changesTracker.clear();
	double score = minimax(myColor, 0, Double.MIN_VALUE, Double.MAX_VALUE);
	System.out.println(compMove.first() + " " + compMove.second());
	return compMove;
    }

    private double minimax(int turncopy, int depth, double alpha, double beta){
	if (depth > 0){
	    double winner = board.checkBoard();
	    if (winner != 0 || depth > searchDepth){
		double HeuristicScore = evaluate(winner);
		return HeuristicScore;
	    }
	}else{
	    if (board.getEmptyTilesCount() < (height * width - 4 - 18)){
		searchDepth = easyDifficulty ? 15 : 20;
	    }
	}
	depth++;
	ArrayList<Double> scores = new ArrayList<Double>();
	ArrayList<IntegerPair> moves = new ArrayList<IntegerPair>();
	for(IntegerPair move: board.getAvailableMoves(turncopy)){
	    System.out.println("Adding to board: " + move.first() + " " + move.second());
	    lastMove = new IntegerPair(move.first(), move.second());
	    board.add(move.first(), move.second(), turncopy);
	    System.out.println(board);
	    IntegerPairArray temp = new IntegerPairArray(board.getBoardChangesTracker());
	    changesTracker.add(temp);
	    System.out.println(temp);
	    scores.add(minimax(-turncopy, depth, alpha, beta));
	    moves.add(move);
	    if (turncopy == myColor){
		int maxScoreIndex = scores.indexOf(Collections.max(scores));
		compMove = moves.get(maxScoreIndex);
		double score = scores.get(maxScoreIndex);
		if (score > alpha){
		    alpha = score;
		}
	    }else{
		int minScoreIndex = scores.indexOf(Collections.min(scores));
		double score = scores.get(minScoreIndex);
		if (score < beta){
		    beta = score;
		}
	    }
	    System.out.println("Removing from board: " + move.first() + " " + move.second());
	    int index = changesTracker.size() - 1;
	    IntegerPairArray temp2 = changesTracker.remove(index);
	    System.out.println(temp2);
	    board.remove(move.first(), move.second(), temp2);//changesTracker.remove(index));
	    System.out.println(board);
	    if (alpha >= beta){
		break;
	    }
	}
	return (turncopy == myColor) ? alpha : beta;
    }
    
    private double evaluate(double winner){
	if (winner == -myColor){
	    return Double.MIN_VALUE;
	}else if (winner == myColor){
	    return Double.MAX_VALUE;
	}else if (board.getEmptyTilesCount() == 0 && winner == 0){
	    return 0;
	}
	int myTileCount = 0, oppTileCount = 0, i, j, k, myFrontCount = 0, oppFrontCount = 0, x, y;
	double p = 0, c = 0, l = 0, m = 0, f = 0, d = 0;
	
	int X1[] = {-1, -1, 0, 1, 1, 1, 0, -1};
	int Y1[] = {0, 1, 1, 1, 0, -1, -1, -1};

	for(i = 0; i < 8; i++){
	    for(j = 0; j < 8; j++){
		if (board.getItem(i, j) == myColor){
		    d += score[i][j];
		    myTileCount++;
		}else if (board.getItem(i, j) == -myColor){
		    d -= score[i][j];
		    oppTileCount++;
		}
		if (board.getItem(i, j) != 0){
		    for(k = 0; k < 8; k++){
			x = i + X1[k];
			y = j + Y1[k];
			if (x > -1 && x < 8 && y > -1 && y < 8 && board.getItem(x, y) == 0){
			    if (board.getItem(i, j) == myColor){
				myFrontCount++;
			    }else{
				oppFrontCount++;
			    }
			    break;
			}
		    }
		}
	    }
	}

	if (myTileCount > oppTileCount){
	    p = (100.0 * myTileCount) / (myTileCount + oppTileCount);
	}else if (myTileCount < oppTileCount){
	    p = -(100.0 * oppTileCount) / (myTileCount + oppTileCount);
	}else{
	    p = 0;
	}
	if (myFrontCount > oppFrontCount){
	    f = -(100.0 * myFrontCount) / (myFrontCount + oppFrontCount);
	}else if (myFrontCount < oppFrontCount){
	    f = (100.0 * oppFrontCount) / (myFrontCount + oppFrontCount);
	}else{
	    f = 0;
	}
	    
	myTileCount = 0;
	oppTileCount = 0;

	if (board.getItem(0, 0) == myColor){
	    myTileCount++;
	}else if (board.getItem(0, 0) == -myColor){
	    oppTileCount++;
	}
	if (board.getItem(0, width - 1) == myColor){
	    myTileCount++;
	}else if (board.getItem(0, width - 1) == -myColor){
	    oppTileCount++;
	}
	if (board.getItem(height - 1, 0) == myColor){
	    myTileCount++;
	}else if (board.getItem(height - 1, 0) == -myColor){
	    oppTileCount++;
	}
	if (board.getItem(height - 1, width - 1) == myColor){
	    myTileCount++;
	}else if (board.getItem(height - 1, width - 1) == -myColor){
	    oppTileCount++;
	}
	c = 25 * (myTileCount - oppTileCount);
	    
	myTileCount = 0;
	oppTileCount = 0;
	if (board.getItem(0, 0) == 0){
	    if (board.getItem(0, 1) == myColor){
		myTileCount++;
	    }else if (board.getItem(0, 1) == -myColor){
		oppTileCount++;
	    }
	    if (board.getItem(1, 1) == myColor){
		myTileCount++;
	    }else if (board.getItem(1, 1) == -myColor){
		oppTileCount++;
	    }
	    if (board.getItem(1, 0) == myColor){
		myTileCount++;
	    }else if (board.getItem(1, 0) == -myColor){
		oppTileCount++;
	    }
	}
	if (board.getItem(0, width - 1) == 0){
	    if (board.getItem(0, width - 2) == myColor){
		myTileCount++;
	    }else if (board.getItem(0, width - 2) == -myColor){
		oppTileCount++;
	    }
	    if (board.getItem(1, width - 2) == myColor){
		myTileCount++;
	    }else if (board.getItem(1, width - 2) == -myColor){
		oppTileCount++;
	    }
	    if (board.getItem(1, width - 1) == myColor){
		myTileCount++;
	    }else if (board.getItem(1, width - 1) == -myColor){
		oppTileCount++;
	    }
	}
	if (board.getItem(height - 1, 0) == 0){
	    if (board.getItem(height - 1, 1) == myColor){
		myTileCount++;
	    }else if (board.getItem(height - 1, 1) == -myColor){
		oppTileCount++;
	    }
	    if (board.getItem(height - 2, 1) == myColor){
		myTileCount++;
	    }else if (board.getItem(height - 2, 1) == myColor){
		oppTileCount++;
	    }
	    if (board.getItem(height - 2, 0) == myColor){
		myTileCount++;
	    }else if (board.getItem(height - 2, 0) == -myColor){
		oppTileCount++;
	    }
	}
	if (board.getItem(height - 1, width - 1) == 0){
	    if (board.getItem(height - 2, width - 1) == myColor){
		myTileCount++;
	    }else if (board.getItem(height - 2, width - 1) == -myColor){
		oppTileCount++;
	    }
	    if (board.getItem(height - 2, width - 2) == myColor){
		myTileCount++;
	    }else if (board.getItem(height - 2, width - 2) == -myColor){
		oppTileCount++;
	    }
	    if (board.getItem(height - 1, width - 2) == myColor){
		myTileCount++;
	    }else if (board.getItem(height - 1, width - 2) == -myColor){
		oppTileCount++;
	    }
	}
	l = -12.5 * (myTileCount - oppTileCount);
	    
	myTileCount = (board.getAvailableMoves(myColor)).size();
	oppTileCount = (board.getAvailableMoves(-myColor)).size();
	if (myTileCount > oppTileCount){
	    m = (100.0 * myTileCount) / (myTileCount + oppTileCount);
	}else if (myTileCount < oppTileCount){
	    m = -(100.0 * oppTileCount) / (myTileCount + oppTileCount);
	}else{
	    m = 0;
	}
	
	double score = (10 * p) + (801.724 * c) + (382.026 * l) + (78.922 * m) + (74.396 * f) + (10 * d);
	return score;
    }
    
}
