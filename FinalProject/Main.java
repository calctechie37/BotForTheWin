import java.util.*;

public class Main{
    public static void main(String[]args){
	ConnectFour2 board = new ConnectFour2();
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
	System.out.println("The winner is: " + board.checkBoard() + "\n");
	board.remove(0);
	System.out.println(board + "\n");
	System.out.println("The winner is: " + board.checkBoard() + "\n");
	board.add(0, "O");
	board.add(0, "0");
	board.add(0, "0");
	board.add(0, "X");
	board.add(1, "O");
	board.add(1, "X");
	board.add(2, "X");
	System.out.println(board + "\n");
	System.out.println("The winner is: " + board.checkBoard() + "\n");
	board.remove(3);
	System.out.println(board + "\n");
	System.out.println("The winner is: " + board.checkBoard() + "\n");
	board.remove(0);
	board.add(3, "O");
	board.add(3, "O");
	board.add(3, "X");
	/**
	board.add(4, "O");
	board.add(4, "0");
	board.add(4, "O");
	board.add(4, "X");
	System.out.println(board + "\n");
	System.out.println("The winner is: " + board.checkBoard() + "\n");
	**/
	board.remove(2);
	System.out.println(board + "\n");
	System.out.println("The winner is: " + board.checkBoard() + "\n");
	ConnectFour2 boardTwo = new ConnectFour2();
	boardTwo.add(3, "X");
	boardTwo.add(3, "X");
	boardTwo.add(3, "X");
	boardTwo.add(3, "X");
	System.out.println(boardTwo + "\n");
	System.out.println("The winner is: " + boardTwo.checkBoard() + "\n");
	boardTwo.remove(3);
	boardTwo.add(3, "O");
	System.out.println(boardTwo + "\n");
	System.out.println("The winner is: " + boardTwo.checkBoard() + "\n");
	Random r = new Random();
	/**
	for(int i = 0; i < 6; i++){
	    for(int j = 0; j < 6; j++){
		boardTwo.add(i, "" + (r.nextInt(26)));
	    }
	}
	**/
	System.out.println(boardTwo + "\n");
	System.out.println("The state of the board is: " + boardTwo.checkBoard() + "\n");
	ConnectFour2 boardThree = new ConnectFour2();
	boardThree.getBestMove();
	System.out.println(boardThree + "\n");
    }
}
