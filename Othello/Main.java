public class Main
{
    public static void main(String[] args)
    {
	Board board = new Board();
	System.out.println(board);
	board.add(3, 3, 1);
	board.add(2, 3, 1);
	System.out.println(board);
	board.remove(3, 3);
	System.out.println(board);
	board.add(9, 9, -1);
	board.remove(9, 9);
	System.out.println(board);
    }
}
