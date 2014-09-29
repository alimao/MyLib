package WYHGraph;

import java.util.List;

public abstract class Graph {

	public static int degree(Graph G, int v){
		List<Integer> de=G.adj(v);
		return de.size();
	}
	public static int maxDegree(Graph G){
		int max=0;
		for (int i=0;i<G.V();i++){
			max=Math.max(max, G.adj(i).size());
		}
		return max;
	}
	
	abstract int V();
	abstract int E();
	abstract void addEdge(int v, int w);
	abstract List<Integer> adj(int v);
	
	
}
