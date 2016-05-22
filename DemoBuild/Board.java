import java.util.*;

public class Board{
    
    private String[][] board;
    private int[] emptySpots;
    private final int size = 6;
    private int emptyTilesCount = size * size;
    private boolean debug = false;

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
	board[row][col] = " ";
	emptyTilesCount++;
	emptySpots[col]--;
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

    public String toString()
    {
	String ans = "|";
	for(int i = size - 1; i >= 0; i--)
	    {
		for(int j = 0; j < size; j++)
		    {
			ans += board[i][j];
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
