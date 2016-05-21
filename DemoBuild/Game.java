import java.util.*;

public class Game
{
    public static void main(String[] args)
    {
	ConnectFour game;
	if (Math.random() < .5)
	    {
		game = new ConnectFour(0);
		System.out.println("You go first!\n");
	    }
	else
	    {
		game = new ConnectFour();
		System.out.println("The bot goes first!\n");
	    }
	game.run();
    }
}
