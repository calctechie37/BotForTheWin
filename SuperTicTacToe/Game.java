import java.util.*;
import java.io.*;

public class Game{

    //    ArrayList<Character> board;
    //String board;
    String[] pos;
    ArrayList<Integer> noPut;
    public Game()
    {
	//	board = "";
	noPut = new ArrayList<Integer>();
	pos = new String[81];
	for(int i=0;i<81;i++)
	    {
		pos[i] = " ";
	    }
	//board = toString();
    }

    public boolean add(int outer,int inner,String marker)
    {
	if(pos[outer*9+inner]!="X"||pos[outer*9+inner]!="O")
	    {
		pos[outer*9+inner]=marker;
		return true;
	    }
	return false;
    }

    public String getUserInput(String[] possibleInputs)
    {
        System.out.println("Your turn!");
        Scanner s = new Scanner(System.in);

        String ret = "";
        if (s.hasNext())
            {
                ret = s.next();
                if (ret.equals("q"))
                    {
                        System.out.println("Player forfeits.  Bot wins!");
                        return "q";
                    }
                for(int i = 0; i < possibleInputs.length; i++)
                    {
                        if(ret.equals(possibleInputs[i]))
                            {
                                return ret;
                            }
                    }
                System.out.println("Not a valid input, fool!");
                return getUserInput(possibleInputs);
            }
        return "";
    }

    public String toString()
    {
	String board = "\n";
	try{
	    BufferedReader boardtxt = new BufferedReader(new FileReader("board.txt"));
	    int k=0;
	    for(int i = 0;i < 17;i++)
		{
		    String line = boardtxt.readLine();
		    for(int j=0;j<line.length();j++)
			{
			    // System.out.println(k);
			    if((j-1)%4==0&&i%2==0)
				{
				    board+=pos[k];
				    k++;
				}
			    else
				{
				    board+=line.charAt(j);
				}
			}
		    board+="\n";
		    /*

		    */
		}
	}
	catch(Exception e)
	    {System.out.println(e);}

	return board;
    }
    
    public static void main(String[]args)
    {
	Game test = new Game();
	System.out.println(test);
	for(int i=0;i<9;i++)
	    {
		for(int j=0;j<9;j++)
		    {
			test.add(i,j,"X");
		    }
	    }
        System.out.println(test);
    }
}
