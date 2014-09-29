package WYHGraph;
/**
 * for UnDiGraphs
 * check two color availability
 * check if a cycle in the graph
 * @author Yihan
 *
 */
public class UDChecker {

	private Graph G;
	private boolean hasCycle=false;
	private boolean twoColor=true;
	private int[] color;
	private boolean[] r;
	public UDChecker(Graph G) {
		this.G=G;
		r=new boolean[G.V()];
		color=new int[G.V()];
		for (int i=0;i<G.V();i++){
			if (!r[i]){
				r[i]=true;
				color[i]=1;
				dfs(i,-1);
			}
		}
	}
	
	private void dfs(int cur, int from){
		for (int next:G.adj(cur)){
			if (next!=from && r[next]){
				hasCycle=true;
				if (color[next]==color[cur]){
					twoColor=false;
				}
			}else if(next==from){
			}else{
				r[next]=true;
				color[next]=3-color[cur];
				dfs(next,cur);
			}
		}
	}
	
	public boolean hasCycle(){
		return hasCycle;
	}
	
	public boolean twoColor(){
		return twoColor;
	}
	
	
}
