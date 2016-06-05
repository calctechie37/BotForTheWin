import java.util.*;

public class Board{

    private int[][] board;
    private ArrayList<IntegerPair> boardChangesTracker;
    private final int height = 8;
    private final int width = 8;
    private int WHITE = -1;
    private int BLACK = 1;
    private int emptyTilesCount = height * width - 4;
    private IntegerPair lastMove;
    private static final int[] rowOffset = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static final int[] colOffset = {-1, 0, 1, -1, 1, -1, 0, 1};

    public Board(){
	boardChangesTracker = new ArrayList<IntegerPair>();
	board = new int[height][width];
	for(int i = 0; i < height; i++){
	    for(int j = 0; j < width; board[i][j] = 0, j++);
	}
	int topLeft = (height - 1) / 2;
	board[topLeft][topLeft] = WHITE;
	board[topLeft][topLeft+1] = BLACK;
	board[topLeft+1][topLeft] = BLACK;
	board[topLeft+1][topLeft+1] = WHITE;
    }

    public int getHeight(){
	return height;
    }

    public int getWidth(){
	return width;
    }

    public int getEmptyTilesCount(){
	return emptyTilesCount;
    }

    public int getItem (int row, int col){
	return board[row][col];
    }

    public boolean isValidPosition(int row, int col, int turn){	
	boolean isValid = false;
	if (board[row][col] != 0){
	    return isValid;
	}
	int opponent = -turn;
	for(int i = 0; i < 8; i++){
	    int currentRow = row + rowOffset[i];
	    int currentCol = col + colOffset[i];
	    boolean sawOppPiece = false;
	    while (currentRow > -1 && currentRow < 8 && currentCol > -1 && currentCol < 8){
		if (board[currentRow][currentCol] == opponent){
		    sawOppPiece = true;
		}else if (board[currentRow][currentCol] == turn && sawOppPiece){
		    isValid = true;
		    break;
		}else {
		    break;
		}
		currentRow += rowOffset[i];
		currentCol += colOffset[i];
	    }
	    if (isValid){
		break;
	    }
	}
	return isValid;
    }

    public ArrayList<IntegerPair> getAvailableMoves(int turn){
	ArrayList<IntegerPair> availableMoves = new ArrayList<IntegerPair>();
	for(int i = 0; i < height; i++){
	    for(int j = 0; j < width; j++){
		if (isValidPosition(i, j, turn)){
		    	IntegerPair move = new IntegerPair(i, j);
		        availableMoves.add(move);
		}
	    }
	}
	return availableMoves;
    }
    
    /**
     * Add the marker to the specified row and column of the board and flip the necessary pieces
     * 
     * @param row the row to add the marker to
     * @param col the column to add the marker to
     * @return whether the operation was successful or not
     **/
    public boolean add(int row, int col, int marker){
	if (row > -1 && row < height && col > -1 && col < width && isValidPosition(row, col, marker)){
	    boardChangesTracker.clear();
	    lastMove = new IntegerPair(row, col);
	    board[row][col] = marker;
	    // flip all the pieces as necessary
	    for (int i = 0; i < 8; ++i) {
		int currentRow = row + rowOffset[i];
		int currentCol = col + colOffset[i];
		boolean sawOppPiece = false;
		while (currentRow > -1 && currentRow < 8 && currentCol > -1 && currentCol < 8) {
		    if (board[currentRow][currentCol] == 0){
			break;
		    }
		    if (board[currentRow][currentCol] != marker){
			sawOppPiece = true;
		    }
		    if ((board[currentRow][currentCol] == marker) && sawOppPiece){
			int changedPieceRow = row + rowOffset[i];
			int changedPieceCol = col + colOffset[i];
			while (changedPieceRow != currentRow || changedPieceCol != currentCol){
			    boardChangesTracker.add(new IntegerPair(changedPieceRow, changedPieceCol));
			    board[changedPieceRow][changedPieceCol] = marker;
			    changedPieceRow += rowOffset[i];
			    changedPieceCol += colOffset[i];
			}
			break;
		    }
		    currentRow += rowOffset[i];
		    currentCol += colOffset[i];
		}
	    }

	    emptyTilesCount--;
	    return true;
	}
	return false;
    }

    /**
     * Remove the marker at the specified row and column of the board and flip the necessary pieces
     *
     * @param row the row to remove the marker from
     * @param col the column to remove the marker from
     **/
    public void remove(int row, int col){
	if (row > -1 && row < height && col > -1 && col < width && board[row][col] != 0){
	    board[row][col] = 0;
	    //unflip all necessary pieces
	    for(IntegerPair Point: boardChangesTracker){
		board[Point.first()][Point.second()] *= -1;
	    }
	    emptyTilesCount++;
	}
    }
    
    /**
     * Check who has won
     *
     * @return the marker of the winning player if such player exists
     * @return zero when the game is not over
     **/
    public int checkBoard(){
	int playerOneCount = 0;
	int playerTwoCount = 0;
	for(int i = 0; i < height; i++){
	    for(int j = 0; j < width; j++){
		if (board[i][j] == BLACK){
		    playerOneCount++;
		}else if (board[i][j] == WHITE){
		    playerTwoCount++;
		}
		if (emptyTilesCount > 0 && playerOneCount > 0 && playerTwoCount > 0){
		    return 0;
		}
	    }
	}
	if (playerOneCount == playerTwoCount){
	    return 0;
	}
	return (playerOneCount > playerTwoCount) ? BLACK : WHITE;
    }

    private String color(int row, int col){
	int marker = board[row][col];
	String piece = (marker == BLACK) ? "O" : "X";
	if (marker != 0 && lastMove != null && row == lastMove.first() && col == lastMove.second()){
	    return ("\u001B[34m" + piece + "\u001B[0m");
	}
	IntegerPair point = new IntegerPair(row, col);
	if (boardChangesTracker != null && boardChangesTracker.contains(point)){
	    return ("\u001B[32m" + piece + "\u001B[0m");
	}
	if (marker == BLACK){
	    return ("\u001B[30mO\u001B[0m");
	}else if (marker == WHITE){
	    return ("\u001B[37mX\u001B[0m");
	}else{
	    return " ";
	}
    }
    
    public String toString(){
	String numString = "  ";
	for(int i = 0; i < width; i++, numString += " " + i);
	String ans = "\033[2J\033[0;0H" + numString + "\n";
	for(int i = height - 1; i >= 0; i--){
	    ans += (i + 1) + " |";
	    for(int j = 0; j < width; j++){
		ans += color(i, j);
		ans += "|";
	    }
	    ans += " " + (i + 1) + " \n";
	}
	ans += numString + "\n";
	return ans;
    }
}
