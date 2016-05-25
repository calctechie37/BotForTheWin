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
	ArrayList<IntegerPair> possibleMoves = new ArrayList<IntegerPair>();
	int[] emptySpots = board.getEmptySpots();
	int i = size / 2;
	int j = i--;
	while (i > -1 || j < size)
	    {
		if (i > -1 && emptySpots[i] < size)
		    {
			IntegerPair move = new IntegerPair(emptySpots[i], i);
			possibleMoves.add(move);
		    }
		if (j < size && emptySpots[j] < size)
		    {
			IntegerPair move = new IntegerPair(emptySpots[j], j);
			possibleMoves.add(move);
		    }
		i--;
		j++;
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
		if (board.getEmptyTilesCount() < (size * size - 10))
		    {
			searchDepth = 25;
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
		if (depth == 1)
		    {
			System.out.println("Reached depth 1");
		    }
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
	return linkedRowStrength();
    }

    private boolean sameMarker(int row, int col, String marker)
    {
	return row > -1 && col > -1 && row < size && col < size && (board.getItem(row, col)).equals(marker);
    }

    private int getLength(IntegerPair position, IntegerPair directions, String marker, boolean[][] visited, int count)
    {
	int row = position.first() + count * directions.first();
	int col = position.second() + count * directions.second();
	while (sameMarker(row, col, marker))
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

    private int getRating(IntegerPair lastMove, String marker, boolean[][] checked, int[][] directions)
    {
	int rating = 0;
        for(int i = 0; i < 4; ++i)
            {
                IntegerPair direction = new IntegerPair(directions[0][i], directions[1][i]);
                IntegerPair posDirScore = getLengthPair(lastMove, direction, marker, checked);
                direction = new IntegerPair(-directions[0][i], -directions[1][i]);
                IntegerPair negDirScore = getLengthPair(lastMove, direction, marker, checked);
                if (posDirScore.second() + negDirScore.second() >= 3)
                    {
                        int coefficient = marker.equals(markers[turn]) ? 1 : -1;
                        coefficient = (coefficient == -1 && marker.equals(markers[1 - turn])) ? -1 : 0;
                        rating += coefficient * lineWeights[posDirScore.first() + negDirScore.first()];
                    }
            }
        return rating;
    }

    private int linkedRowStrength()
    {
	int total = 0;
        int[][] directions = {{1,1,0,-1}, {0,1,1,1}};
	boolean[][] checked = new boolean[size][size];
	for(int row = 0; row < size; ++row)
            {
                for(int col = 0; col < size; ++col)
                    {
                        if (checked[row][col])
                            {
                                continue;
                            }
                        String marker = board.getItem(row, col);
                        if (!(marker.equals(" ")))
                            {
                                total += getRating(new IntegerPair(row, col), marker, checked, directions);
			    }
                        checked[row][col] = true;
                    }
            }
        return total;
    }
}
