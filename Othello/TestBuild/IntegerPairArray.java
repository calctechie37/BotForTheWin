import java.util.*;

public class IntegerPairArray implements Iterable<IntegerPair>{
    
    private ArrayList<IntegerPair> IntegerPairArray;

    public IntegerPairArray(){
	IntegerPairArray = new ArrayList<IntegerPair>();
    }

    public IntegerPairArray(ArrayList<IntegerPair> array){
	this();
	for(int i = 0; i < array.size(); i++){
	    IntegerPairArray.add(array.get(i));
	}
    }

    public void add(IntegerPair intPair){
	IntegerPairArray.add(intPair);
    }

    public Iterator<IntegerPair> iterator(){
	return IntegerPairArray.iterator();
    }

    public String toString(){
	String ans = "This is an IntegerPairArray: ";
	for(IntegerPair pair: IntegerPairArray){
	    ans += pair;
	}
	return ans;
    }
}
