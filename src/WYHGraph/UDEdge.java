package WYHGraph;

public class UDEdge implements Comparable<UDEdge>{

	private int w;
	private int v;
	int weight;
	public UDEdge(int v, int w, int weight) {
		this.v=v;
		this.w=w;
		this.weight=weight;
	}
	
	public int one(){
		return v;
	}
	public int other(){
		return w;
	}
	
	public String toString(){
		String ans=""+v+" to "+w+" : "+weight;
		return ans;
	}

	@Override
	public int compareTo(UDEdge o) {
		if (this.weight>o.weight)return 1;
		if (this.weight<o.weight)return -1;
		return 0;
	}
}
