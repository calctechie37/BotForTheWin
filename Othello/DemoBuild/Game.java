import java.util.*;

public class Game
{
    public static void main(String[] args)
    {
	Othello game;
	if (Math.random() < .5)
	    {
		game = new Othello(-1);
		System.out.println("You go first!\n");
	    }
	else
	    {
		game = new Othello();
		System.out.println("The bot goes first!\n");
	    }
	game.run();
    }
}
