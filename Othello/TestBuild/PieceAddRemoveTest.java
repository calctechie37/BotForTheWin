public class PieceAddRemoveTest{

    public static void main(String[] args){
	Board board = new Board();
	System.out.println("Inital Board Setup\n");
	System.out.println(board + "\n");
	System.out.println("Black makes his move...\n");
	board.add(2, 3, 1);
	System.out.println(board);
	System.out.println("Undoing Black's move...\n");
	board.remove(2, 3);
	System.out.println(board);
	System.out.println("White makes her move...\n");
        board.add(2, 4, -1);
	System.out.println(board);
	System.out.println("Undoing White's move...\n");
	board.remove(2, 4);
	System.out.println(board);
	System.out.println("Black makes invalid move at (6, 2)...\n");
	board.add(5, 2, 1);
	System.out.println(board);
	System.out.println("Undoing Black's move (Removing empty square)...\n");
	board.remove(5, 2);
	System.out.println(board);
	System.out.println("White makes invalid move at (6, 6)...\n");
	board.add(5, 5, -1);
	System.out.println(board);
	System.out.println("Undoing Black's move (Removing empty square)...\n");
	board.remove(5, 5);
	System.out.println(board);
	// Test for out of bound add and remove
	System.out.println("Making out of bound moves...");
	board.add(-1, -1, -1);
	board.add(-1, 3, -1);
	board.add(-1, 9, -1);
	board.add(3, -1, -1);
	board.add(3, 9, -1);
	board.add(9, -1, -1);
	board.add(9, 3, -1);
	board.add(9, 9, -1);
	board.remove(-1, 3);
	board.remove(-1, 9);
	board.remove(9, 3);
	board.remove(9, 9);
	System.out.println("Passed out of bound tests!");
	System.out.println(board);
    }

}
