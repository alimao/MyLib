package WYHGraph;

import java.util.ArrayList;
import java.util.List;

public class DirectedGraph extends Graph{

	private final int v;
	private int e;
	private List<Integer>[] bag;
	
	
	
	
	@SuppressWarnings("unchecked")
	public DirectedGraph(int v) {
		this.v=v;
		bag=new List[v];
		for(int i=0;i<v;i++){
			bag[i]=new ArrayList<Integer>();
		}
	}
	
	public DirectedGraph reverse(){
		DirectedGraph ans=new DirectedGraph(this.v);
		for (int i=0;i<v;i++){
			for (int j:this.bag[i]){
				ans.addEdge(j, i);
			}
		}
		return ans;
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
		e++;
	}

	@Override
	public List<Integer> adj(int v) {
		return bag[v];
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
	
}
