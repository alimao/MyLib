import static org.junit.Assert.*;

import org.junit.Test;

import WYHPriorityQueue.FibonacciHeap;
import WYHPriorityQueue.Key;
import WYHPriorityQueue.PriorityQueue;


public class TestFibonacci {

	@Test public void wordFormat4DBNormal(){
		class Tes implements Key<Integer>, Comparable<Tes>{

			int value;
			
			public Tes(int v) {
				value=v;
			}
			@Override
			public Integer getKey() {

				return value;
			}

			@Override
			public void setKey(Integer key) {
				value=key;
				
			}
			@Override
			public int compareTo(Tes o) {
				if (value<o.value)return -1;
				if (value>o.value)return 1;
				return 0;
			}
			
		}
		PriorityQueue<Tes,Integer> t=new FibonacciHeap<Tes,Integer>();
		for (int i=0;i<10;i++){
			t.insert(new Tes(i),i);
		}
		FibonacciHeap<Tes,Integer> t2=new FibonacciHeap<Tes,Integer>();
		for (int i=12;i<22;i++){
			t2.insert(new Tes(i),i);
		}
		t.union(t2);
		assertEquals(t.minKey(),new Integer(0));
	}
}
