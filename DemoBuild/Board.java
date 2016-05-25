import java.util.*;

public class Board{
    
    private String[][] board;
    private int[] emptySpots;
    private final int size = 6;
    private int emptyTilesCount = size * size;
    private IntegerPair lastMove;
    private boolean debug = false;
    private int[][] boardValues = {{3, 4, 7, 7, 4, 3},
				   {4, 6, 10, 10, 6, 4},
				   {5, 8, 13, 13, 8, 5}, 
				   {5, 8, 13, 13, 8, 5},
				   {4, 6, 10, 10, 6, 4},
				   {3, 4, 7, 7, 4, 3}};

    public Board()
    {
	board = new String[size][size];
	emptySpots = new int[size];
	for(int i = 0; i < size; i++)
	    {
		for(int j = 0; j < size; board[i][j] = " ", j++);
	    }
	for(int j = 0; j < size; emptySpots[j] = 0, j++);
    }

    public int getSize()
    {
	return size;
    }

    public int getEmptyTilesCount()
    {
	return emptyTilesCount;
    }

    public int[] getEmptySpots()
    {
	return emptySpots;
    }

    public String getItem(int row, int col)
    {
	return board[row][col];
    }

    /**                                                                     
     * Add the marker to the specified column of the board                  
     *                                                                      
     * @param row the row to add the marker to                              
     * @param column the column to add the marker to                        
     * @param marker the character representing the player                  
     * @return whether the operation was successful or not                  
     **/
    public boolean add(int row, int col, String marker)
    {
	if (board[row][col].equals(" "))
	    {
		board[row][col] = marker;
		emptyTilesCount--;
		emptySpots[col]++;
		lastMove = new IntegerPair(row, col);
		return true;
	    }
	return false;
    }

    /**                                                                     
     * Add the marker to the specified column of the board                  
     *                                                                      
     * @param column the column to add the marker to                        
     * @param marker the character representing the player                  
     * @return whether the operation was successful or not                  
     **/
    public boolean add(int col, String marker)
    {
	int row;
	for(row = 0; row < size && !(board[row][col].equals(" ")); row++);
	if (row >= size)
	    {
		return false;
	    }
	else
	    {
		board[row][col] = marker;
		emptyTilesCount--;
		emptySpots[col]++;
		lastMove = new IntegerPair(row, col);
		return true;
	    }
    }

    /**                                                                     
     * Remove the marker at the specified row and specified column          
     *                                                                      
     * @param row the row to remove the marker from                         
     * @param col the column to remove the marker from                      
     **/
    public void remove(int row, int col)
    {
	if (!board[row][col].equals(" "))
	    {
		String marker = board[row][col];
		board[row][col] = " ";
		emptyTilesCount++;
		emptySpots[col]--;
	    }
    }
	
    /**                                                                     
     * Remove the marker at the specified row and specified column          
     *                                                                      
     * @param col the column to remove the marker from                      
     **/
    public void remove(int col)
    {
	int row;
	for(row = size - 1; row > -1 && board[row][col].equals(" "); row--);
	if (row >= 0)
	    {
		String marker = board[row][col];
		board[row][col] = " ";
		emptyTilesCount++;
		emptySpots[col]--;
	    }
    }

    /**
     * Check if the board has reached a game over state
     *
     * @return the marker of the winning player if such player exists
     * @return "draw" when no moves can be made
     * @return an empty string when the game is not over                    
     **/
    public String checkBoard()
    {                                              
        for(int i = 0; i < size; i++)
            {                                                               
                for(int j = 0; j < size; j++)
                    {
                        String result = checkBoard(i, j);
                        if (result.length() > 0)
                            {
				return result;
                            }
                    }
            }                                                          
        return "";
    }

    /**                                                                     
     * Check if the board has reached a game over state given specific row and column
     *                                                                      
     * @param row the row the marker belongs to                             
     * @param col the column the marker belongs to                          
     * @return the marker of the winning player if such player exists       
     * @return "draw" when no moves can be made                             
     * @return an empty string when the game is not over                    
     **/
    public String checkBoard(int row, int col)
    {
        if (board[row][col].equals(" ") || (emptyTilesCount > (size * size - 7) && !debug))
	    {
		return "";
	    }
	String marker = board[row][col];
	int count = 0;
	for(int j = row; j < size && board[j][col].equals(marker); j++, count++);
	for(int j = row - 1; j > -1 && board[j][col].equals(marker); j--, count++);
	if (count > 3)
	    {
		return marker;
	    }
	count = 0;
	for(int i = col; i < size && board[row][i].equals(marker); i++, count++);
	for(int i = col - 1; i > -1 && board[row][i].equals(marker); i--, count++);
	if (count > 3)
	    {
		return marker;
	    }
	count = 0;
	int j = col;
	for(int i = row;i < size && j < size && board[i][j].equals(marker); i++, j++, count++);
	j = col - 1;
	for(int i = row - 1; i > -1 && j > -1 && board[i][j].equals(marker); i--, j--, count++);
	if (count > 3)
	    {
		return marker;
	    }
	count = 0;
	j = col;
	for(int i = row; i < size && j > -1 && board[i][j].equals(marker); i++, j--, count++);
	j = col + 1;
	for(int i = row - 1; i > -1 && j < size && board[i][j].equals(marker); i--, j++, count++);
	if (count > 3)
	    {
		return marker;
	    }
	return (emptyTilesCount == 0) ? "draw" : "";
    }

    private String color(int i, int j)
    {
	String marker = board[i][j];
	if (lastMove != null && lastMove.first() == i && lastMove.second() == j)
	    {
		return ("\u001B[32m" + marker + "\u001B[0m");
	    }
	if (marker.equals("X"))
	    {
		return ("\u001B[31m" + marker + "\u001B[0m");
	    }
	else if (marker.equals("O"))
	    {
		return ("\u001B[34m" + marker + "\u001B[0m");
	    }
	return marker;
    }
    
    public String toString()
    {
	String header = "Welcome to Connect Four by Team BotForTheWin!\n\nIn this version of Connect Four, "
	    + "you will be playing against a bot.  Choose the column to drop the marker in by\nentering the "
	    + "corresponding column number which are conveniently displayed on both the top and bottom of the"
	    + "\nboard.  The most recent move would be colored green.  Your marker is a red 'X' while the "
	    + "bot's marker is a blue 'O'.\n\nGood luck and enjoy!\n\n";
	
	String ans = "\033[2J\033[0;0H" + header + " 1 2 3 4 5 6\n|";
	for(int i = size - 1; i >= 0; i--)
	    {
		for(int j = 0; j < size; j++)
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
	ans += " 1 2 3 4 5 6";
	return ans;
    }
}
