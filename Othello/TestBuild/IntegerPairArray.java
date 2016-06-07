import java.util.*;

public class IntegerPairArray implements Iterable<IntegerPair>{
    
    private ArrayList<IntegerPair> IntegerPairArray;

    public IntegerPairArray(){
	IntegerPairArray = new ArrayList<IntegerPair>();
    }

    public IntegerPairArray(ArrayList<IntegerPair> array){
	IntegerPairArray = array;	
    }

    public void add(IntegerPair intPair){
	IntegerPairArray.add(intPair);
    }

    public Iterator<IntegerPair> iterator(){
	return IntegerPairArray.iterator();
    }

    public String toString(){
	String ans = "";
	for(IntegerPair pair: IntegerPairArray){
	    ans += pair;
	}
	return ans;
    }
}
