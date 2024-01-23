package mainClasses;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ImplIterable implements Iterator<Integer> {
	int[] arr;
	int current = 0;
	public ImplIterable(int[] er) {
		arr = er;
	}
	@Override
	public boolean hasNext() {
		
		return current < arr.length;
	}

	@Override
	public Integer next() {
		if(hasNext()) {
		return arr[current++];
		} else {
			throw new NoSuchElementException();
		}
	}

}
