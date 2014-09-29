package WYHGraph;

import java.util.List;

public class StrongConnectedComponents {

	private int[] id;
	private int count;
	private DirectedGraph G;
	private DirectedGraph RG;
	private List<Integer> temp;
	private boolean[] marked;
	public StrongConnectedComponents(DirectedGraph G) {
		this.G=G;
		id=new int[G.V()];
		RG=G.reverse();
		DChecker dc=new DChecker(RG); 
		temp=dc.dfsOrder();
		count=0;
		for (int i:temp){
			if (!marked[i]){
				dfs(i);
				count++;
			}
		}
	}
	private void dfs(int cur) {
		marked[cur]=true;
		id[cur]=count;
		for (int next : G.adj(cur)){
			if (!marked[next]){
				dfs(next);
			}
		}
	}
	
	public int count(){
		return count;
	}
	
	public int id(int v){
		return id[v];
	}
	
	public boolean strongConnected(int v, int w){
		return id[v]==id[w];
	}
	
	
	
	
}
