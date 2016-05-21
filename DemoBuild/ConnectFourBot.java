import java.util.*;

public class ConnectFourBot
{

    private Board board;
    private IntegerPair lastMove;
    private IntegerPair compMove;
    private int turn = 1;
    private int size;
    private int searchDepth = 11;
    private String[] markers = {"X", "O"};
    private int[][] boardValues = {{3, 4, 5, 7, 5, 4, 3},
				   {4, 6, 8, 10, 8, 6, 4},
				   {5, 8, 11, 13, 11, 8, 5}, 
				   {5, 8, 11, 13, 11, 8, 5},
				   {4, 6, 8, 10, 8, 6, 4},
				   {3, 4, 5, 7, 5, 4, 3}};
    private int[] lineWeights = {0, 10, 100};

    public ConnectFourBot()
    {
    }

    public ConnectFourBot(int turn)
    {
	this.turn = turn;
    }

    public IntegerPair getBestMove(Board board)
    {
	this.board = board;
	size = board.getSize();
	int score = minimax(turn, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
	return compMove;
    }

    private ArrayList<IntegerPair> availableMoves()
    {
	ArrayList<IntegerPair> temp = new ArrayList<IntegerPair>();
	ArrayList<IntegerPair> possibleMoves = new ArrayList<IntegerPair>();
	for(int i = 0; i < size; i++)
	    {
		int j;
		for(j = size - 1; j > -1 && (board.getItem(j, i)).equals(" "); j--);
		if (++j >= size)
		    {
			continue;
		    }
		IntegerPair move = new IntegerPair(j, i);
	        temp.add(move);
	    }
	int tempSize = temp.size();
	int i = tempSize / 2;
	int j = i--;
	while (i > -1 || j < tempSize)
	    {
		if (i > -1)
		    {
			possibleMoves.add(temp.get(i--));
		    }
		if (j < tempSize)
		    {
			possibleMoves.add(temp.get(j++));
		    }
	    }
	return possibleMoves;
    }

    private int minimax(int turncopy, int depth, int alpha, int beta)
    {
	if (depth > 0)
	    {
		String winner = board.checkBoard(lastMove.first(), lastMove.second());
		if (!winner.equals("") || depth > searchDepth)
		    {
			int HeuristicScore = evaluate(winner, depth);
			return HeuristicScore;
		    }
	    }
	else
	    {
		if (board.getEmptyTilesCount() < (size * size - 8))
		    {
			searchDepth = 15;
		    }
	    }
	depth++;
	ArrayList<Integer> scores = new ArrayList<Integer>();
	ArrayList<IntegerPair> moves = new ArrayList<IntegerPair>();
	for(IntegerPair move: availableMoves())
	    {
		lastMove = new IntegerPair(move.first(), move.second());
		board.add(move.first(), move.second(), markers[turncopy]);
		scores.add(minimax(1 - turncopy, depth, alpha, beta));
		moves.add(move);
		if (turncopy == 1)
		    {
			int maxScoreIndex = scores.indexOf(Collections.max(scores));
			compMove = moves.get(maxScoreIndex);
			int score = scores.get(maxScoreIndex);
			if (score > alpha)
			    {
				alpha = score;
			    }
		    }
		else
		    {
			int minScoreIndex = scores.indexOf(Collections.min(scores));
			int score = scores.get(minScoreIndex);
			if (score < beta)
			    {
				beta = score;
			    }
		    }
		board.remove(move.first(), move.second());
		if (alpha >= beta)
		    {
			break;
		    }
	    }
	return (turncopy == 1) ? alpha : beta;
    }

    private int evaluate(String winner, int depth)
    {
	if (winner.equals(markers[turn]))
	    {
		return Integer.MAX_VALUE - depth;
	    }
	else if (winner.equals(markers[1 - turn]))
	    {
		return depth - Integer.MAX_VALUE;
	    }
	else if (winner.equals("draw"))
	    {
		return 0;
	    }
	int total = 0;
	total += boardStrength(turn);
	total += linkedRowStrength();
	return total;
    }

    private int boardStrength(int turncopy)
    {
	int total = 0;
	for(int row = 0; row < size; row++)
	    {
		for(int col = 0; col < size; col++)
		    {
			int coefficient = 0;
			if ((board.getItem(row, col)).equals(markers[turn]))
			    {
				coefficient = 1;
			    }
			if ((board.getItem(row, col)).equals(markers[1 - turn]))
			    {
				coefficient = -1;
			    }
			total += coefficient * boardValues[row][col];
		    }
	    }
	return total;
    }

    private boolean isValidMarkerPosition(int row, int col, String marker)
    {
	return row > -1 && col > -1 && row < size && col < size && (board.getItem(row, col)).equals(marker);
    }

    private int getLength(IntegerPair position, IntegerPair directions, String marker, boolean[][] visited, int count)
    {
	int row = position.first() + count * directions.first();
	int col = position.second() + count * directions.second();
	while (isValidMarkerPosition(row, col, marker))
	    {
		visited[row][col] = true;
		count++;
		row += directions.first();
		col += directions.second();
	    }
	return count;
    }

    private IntegerPair getLengthPair(IntegerPair lastMove, IntegerPair direction, String marker, boolean[][] visited)
    {
	IntegerPair position = new IntegerPair(lastMove.first() + direction.first(),
					       lastMove.second() + direction.second());
	int playerLength = getLength(position, direction, marker, visited, 0);
	int possibleLength = getLength(position, direction, " ", visited, playerLength);
	return new IntegerPair(playerLength, possibleLength);
    }

    private int getStrength(IntegerPair lastMove, String marker, boolean[][] visited, int[][] directions)
    {
	int strength = 0;
        for(int i = 0; i < 4; ++i)
            {
                IntegerPair direction = new IntegerPair(directions[0][i], directions[1][i]);
                IntegerPair posDirScore = getLengthPair(lastMove, direction, marker, visited);
                direction = new IntegerPair(-directions[0][i], -directions[1][i]);
                IntegerPair negDirScore = getLengthPair(lastMove, direction, marker, visited);
                if (posDirScore.second() + negDirScore.second() >= 3)
                    {
                        int coefficient = marker.equals(markers[turn]) ? 1 : -1;
                        coefficient = (coefficient == -1 && marker.equals(markers[1 - turn])) ? -1 : 0;
                        strength += coefficient * lineWeights[posDirScore.first() + negDirScore.first()];
                    }
            }
        return strength;

    }

    private int linkedRowStrength()
    {
	int total = 0;
        int[][] directions = {{1,1,0,-1}, {0,1,1,1}};
	boolean[][] visited = new boolean[size][size];
	for(int row = 0; row < size; ++row)
            {
                for(int col = 0; col < size; ++col)
                    {
                        if (visited[row][col])
                            {
                                continue;
                            }
                        String marker = board.getItem(row, col);
                        if (!(marker.equals(" ")))
                            {
                                total += getStrength(new IntegerPair(row, col), marker, visited, directions);
			    }
                        visited[row][col] = true;
                    }
            }
        return total;
    }
}
