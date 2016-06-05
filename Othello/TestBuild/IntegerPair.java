public class IntegerPair
{
    private int first;
    private int second;
    
    public IntegerPair(int first, int second)
    {
	this.first = first;
	this.second = second;
    }

    public int first()
    {
	return first;
    }

    public int second()
    {
	return second;
    }
    
    @Override
    public boolean equals(Object object){
	boolean isSame = false;
	if (object != null && object instanceof IntegerPair){
	    isSame = (this.first() == ((IntegerPair) object).first() && this.second() == ((IntegerPair) object).second());
	}
	return isSame;
    }
}
