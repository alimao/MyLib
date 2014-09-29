package WYHGraph;

import java.util.ArrayList;
import java.util.List;

public class UndirectedGraph extends Graph{

	private UDChecker cc;
	protected final int v;
	protected int e;
	private List<Integer>[] bag;
	@SuppressWarnings("unchecked")
	public UndirectedGraph(int V) {
		v=V;
		e=0;
		bag=new List[v];
		for (int i=0;i<v;i++){
			bag[i]=new ArrayList<Integer>();
		}
	}
	
	public boolean hasCycle(){
		if (cc==null)cc=new UDChecker(this);
		return cc.hasCycle();
	}
	
	public boolean twoColor(){
		if (cc==null)cc=new UDChecker(this);
		return cc.twoColor();
	}
	
	public static int numberOfSelfLoops(Graph G){
		int res=0;
		for (int i=0;i<G.V();i++){
			for (int j:G.adj(i)){
				if (j==i)res++;
			}
		}
		return res/2;
	}
	
	public String toString(){
		StringBuffer s=new StringBuffer();
		s.append(this.V()).append(" Vertices ").append(this.E()).append(" Edges \n");
		for (int i=0;i<this.V();i++){
			s.append(i).append(" : ");
			for (int j:this.adj(i)){
				s.append(j).append(" ");
			}
			s.append("\n");
		}
		return s.toString();
	}
	@Override
	public int V() {
		return v;
	}

	@Override
	public int E() {
		return e;
	}

	@Override
	public void addEdge(int v, int w) {
		
		bag[v].add(w);
		if (v!=w)bag[w].add(v);
		e++;
		
	}

	@Override
	public List<Integer> adj(int v) {
		return bag[v];
	}

	
}
