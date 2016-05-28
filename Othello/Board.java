import java.util.*;
public class Board
{
    private int[][] board;
    private final int height = 8;
    private final int width = 8;
    private int emptyTilesCount = height * width;
    private boolean debug = true;

    public Board()
    {
	board = new int[height][width];
	for(int i = 0; i < height; i++)
	    {
		for(int j = 0; j < width; board[i][j] = 0, j++);
	    }
    }

    public int getHeight()
    {
	return height;
    }

    public int getWidth()
    {
	return width;
    }

    public int getEmptyTilesCount()
    {
	return emptyTilesCount;
    }

    public int getItem (int row, int col)
    {
	return board[row][col];
    }

    /**
     * Add the marker to the specified row and column of the board
     * 
     * @param row the row to add the marker to
     * @param col the column to add the marker to
     * @return whether the operation was successful or not
     **/
    public boolean add(int row, int col, int marker)
    {
	if (row < height && col < width && board[row][col] == 0)
	    {
		board[row][col] = marker;
		emptyTilesCount--;
		return true;
	    }
	return false;
    }

    /**
     * Remove the marker at the specified row and column of the board
     *
     * @param row the row to remove the marker from
     * @param col the column to remove the marker from
     **/
    public void remove(int row, int col)
    {
	if (row < height && col < width && board[row][col] != 0)
	    {
		board[row][col] = 0;
		emptyTilesCount--;
	    }
    }
    
    /**
     * Check who has won
     *
     * @return the marker of the winning player if such player exists
     * @return zero when the game is not over
     **/
    public int checkBoard()
    {
	if (emptyTilesCount > 0)
	    {
		return 0;
	    }
	else
	    {
		int playerOneCount = 0;
		int playerTwoCount = 0;
		for(int i = 0; i < height; i++)
		    {
			for(int j = 0; j < width; j++)
			    {
				if (board[i][j] == 1)
				    {
					playerOneCount++;
				    }
				else if (board[i][j] == -1)
				    {
					playerTwoCount++;
				    }
			    }
		    }
		return (playerOneCount > playerTwoCount) ? 1 : -1;
	    }

    }	

    private String color(int row, int col)
    {
	int marker = board[row][col];
	if (marker == 1)
	    {
		return ("\u001B[31m" + marker + "\u001B[0m");
	    }
	else if (marker == -1)
	    {
		return ("\u001B[34m" + marker + "\u001B[0m");
	    }
	else
	    {
		return " ";
	    }
    }

    public String toString()
    {
	String numString = "";
	for(int i = 0; i < width; i++, numString += " " + i);
	String ans = "\033[2J\033[0;0H" + numString + "\n|";
	if (debug)
	    {
		ans = numString + "\n|";
	    }
	for(int i = height - 1; i >= 0; i--)
	    {
		for(int j = 0; j < width; j++)
		    {
			ans += color(i, j);
			ans += "|";
		    }
		ans += "\n";
		if (i > 0)
		    {
			ans += "|";
		    }
	    }
	ans += numString;
	return ans;
    }
}
