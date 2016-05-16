import java.util.*;

public class Main{
    public static void main(String[]args){
	Board board = new Board();
	System.out.println(board + "\n");
	board.add(2, "X");
	System.out.println(board + "\n");
	board.remove(2);
	System.out.println(board + "\n");
	board.add(0, "X");
	board.add(1, "X");
	board.add(2, "X");
	board.add(3, "X");
	System.out.println(board + "\n");
	System.out.println("The winner is: " + board.checkBoard(3) + "\n");
	board.remove(0);
	System.out.println(board + "\n");
	System.out.println("The winner is: " + board.checkBoard(3) + "\n");
	Board boardTwo = new Board();
	boardTwo.add(3, "X");
	boardTwo.add(3, "X");
	boardTwo.add(3, "X");
	boardTwo.add(3, "X");
	System.out.println(boardTwo + "\n");
	System.out.println("The winner is: " + boardTwo.checkBoard(3) + "\n");
	boardTwo.remove(3);
	boardTwo.add(3, "O");
	System.out.println(boardTwo + "\n");
	System.out.println("The winner is: " + boardTwo.checkBoard(3) + "\n");
	Random r = new Random();
	for(int i = 0; i < 6; i++){
	    for(int j = 0; j < 6; j++){
		boardTwo.add(i, "" + (r.nextInt(26)));
	    }
	}
	System.out.println(boardTwo + "\n");
	System.out.println("The state of the board is: " + boardTwo.checkBoard(2) + "\n");
    }
}
