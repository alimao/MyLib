package WYHGraph;

public class DiEdge implements Comparable<DiEdge>{

	private int we;
	private int fr;
	private int to;
	public DiEdge(int f,int t,int w) {
		we=w;
		fr=f;
		to=t;
	}
	
	public int from(){
		return fr;
	}
	
	public int to(){
		return to;
	}
	
	public int weight(){
		return we;
	}

	public String toString(){
		String ans=""+fr+" to "+to+" : "+we;
		return ans;
	}
	
	@Override
	public int compareTo(DiEdge o) {
		if (we<o.we)return -1;
		if (we>o.we)return 1;
		return 0;
	}
}
