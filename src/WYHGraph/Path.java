package WYHGraph;

import java.util.List;

public class Path {

	Graph G;
	public Path(Graph G) {
		this.G=G;
	}
	
	public boolean hasPath(int from, int to){
		List<Integer> t=onePath(from, to);
		if (t.size()>0)return true;
		return false;
	}
	
	public List<Integer> onePath(int from, int to){
		DFSearcher dfs=new DFSearcher(G, from);
		return dfs.searchPathTo(to);
	}
	
	public List<Integer> oneShortestPath(int from, int to){
		BFSearcher bfs=new BFSearcher(G, from);
		return bfs.searchPathTo(to);
	}
}
