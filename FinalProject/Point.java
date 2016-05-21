import java.util.*;

public class Point
{

    private ArrayList<Integer> point;

    public Point(int x, int y)
    {
	point = new ArrayList<Integer>();
	point.add(x);
	point.add(y);
    }

    public int getX()
    {
	return point.get(0);
    }

    public int getY()
    {
	return point.get(1);
    }

    public String toString()
    {
	return Arrays.toString(point.toArray());
    }
}
