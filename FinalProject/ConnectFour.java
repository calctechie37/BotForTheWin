import java.util.*;

public class ConnectFour{

    private String[][] board;
    private String[][] boardT;  //board tranposed
    private static final int size = 6;
    private static int emptyTilesCount = size * size;

    /**
     * Initialize the board filling every cells with spaces
     **/
    public ConnectFour()
    {
	board = new String[size][size];
	boardT = new String[size][size];
	for(int i = 0; i < size; i++)
	    {
		for(int j = 0; j < size; j++)
		    {
			board[i][j] = " ";
			boardT[i][j] = " ";
		    }
	    }
    }

    /**
     * Add the marker to the specified column of the board
     *
     * @param col the column to add the marker to
     * @param marker the character representing the player
     * @return whether the operation was successful or not
     */
    public boolean add(int col, String marker)
    {
	String[] column = board[col];
	int i;
	for(i = 0; i < column.length && !(column[i].equals(" ")); i++);
	if (i >= column.length)
	    {
		return false;
	    }
	else
	    {
		column[i] = marker;
		board[col] = column;
		boardT[i][col] = marker;
		emptyTilesCount--;
		return true;
	    }
    }

    /**
     * Remove the highest marker at the specified column from the board
     *
     * @param col the column to remove a marker from
     */
    public void remove(int col)
    {
	String[] column = board[col];
	int i;
	for(i = size - 1; i >= 0 && column[i].equals(" "); i--);
	if (i >= 0)
	    {
		column[i] = " ";
		board[col] = column;
		boardT[i][col] = " ";
		emptyTilesCount++;
	    }
    }

    /**
     * Check if the game is over: win, draw, or lose
     * 
     * @param col the column whose highest marker would be selected when testing for game over conditions
     * @return the marker of the winning player when one player has won
     * @return "draw" when no moves can be made
     * @return an empty string when the game is not over
     */
    public String checkBoard(int col){
	String[] column = board[col];
	int i;
	for(i = size - 1; i >= 0 && column[i].equals(" "); i--);
	int count = 0;
	for(int j = i; j < size && column[i].equals(column[j]); j++, count++);
	if (count == 4)
	    {
		return column[i];
	    }
	count = 0;
	for(int j = i; j > -1 && column[i].equals(column[j]); j--, count++);
	if (count == 4)
	    {
		return column[i];
	    }
	String[] row = boardT[i];
	count = 0;
	for(int j = i; j < size && row[col].equals(row[j]); j++, count++);
	if (count == 4)
	    {
		return row[col];
	    }
	count = 0;
	for(int j = i; j > -1 && row[col].equals(row[j]); j--, count++);
	if (count == 4)
	    {
		return row[col];
	    }
	if (emptyTilesCount == 0)
	    {
		return "draw";
	    }
	return "";
    }

    /**
     * Read in user input from the terminal and validate the user input
     * 
     * @params possibleInputs an array of acceptable user inputs
     */
    public static String getUserInput(String[] possibleInputs)
    {
	String ret = "";
	Scanner s = new Scanner(System.in);
	int i =0;
	if(s.next()=="exit")
	    {
		return "Player forfeits";
	    }
	while(s.hasNext())
	    {
		ret = s.next(possibleInputs[i]);
		i++;
	    }
	if(ret=="")
	    {
		System.out.println("Not a valid input fool");
		return getUserInput(possibleInputs);
	    } 
	else
	    {
		return ret;
	    }

    }
    
    /**
     * The main game engine that takes user input, update the board, and display the results
     */
    //this is the main game engine
    //call getUserInput to figure who goes first
    //call getUserInput to figure out the marker for player 1
    //call getUserInput to figure out the marker for player 2
    //main while loop, loop while not gameover
    //display board
    //call getUserInput to recieve user move
    //check if valid move and update board
    //if valid move call getUserInput for second player
    //otherwise repeat until move is valid
    public static void main(String[]args)
    {
	
	String[] possibleInputs = {"1","2","3","4"}; 
	if(true)//Math.random()*2==0)
	    {
		System.out.println("You start!");
		String input  = getUserInput(possibleInputs);
		board.add(Integer.parseInt(input) - 1
			  }
		else
		    {
			System.out.println("Computer starts!");
		    }
	    }

    /**
     * Return the string representation of the board
     *
     * @return the string representation of the board
     */
    public String toString(){
        for(int i = size - 1; i >= 0; i--)
	    {
		System.out.println(Arrays.toString(boardT[i]));
	    }
	String[][] copy = tranposeArray();
	String ans = "|";
	for(int i = size - 1; i >= 0; i--)
	    {
		for(int j = 0; j < size; j++)
		    {
			ans += copy[i][j];
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
