package WYHPriorityQueue;

public class PQTest {

	public static void main(String[] args){
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
		Tes te = new Tes(10);
		t.insert(te,10);
		Tes te1 = new Tes(11);
		t.extractMin();
		t.delete(te);
		t.insert(te1,11);
		t.extractMin();
		t.decreaseKey(te, 0);
//		t.delete(te);
		t.extractMin();
		t.extractMin();
		System.out.println(t);
		
	}
}
