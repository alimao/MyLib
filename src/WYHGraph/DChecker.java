package WYHGraph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class DChecker {

	DirectedGraph G;
	DirectedGraph RG;
	private Stack<Integer> topo;
	//private int[] from;
	private boolean[] onStack;
	private boolean hasCycle=false;
	private boolean[] reached;
	public DChecker(DirectedGraph G) {
		this.G=G;
		RG=G.reverse();
		topo=new Stack<Integer>();
		onStack=new boolean[G.V()];
		reached=new boolean[G.V()];
		//from=new int[G.V()];
		for (int i=0;i<G.V();i++){
			//from[i]=
			if (!reached[i]){
				dfs(i);
			}
		}
	}
	
	private void dfs(int cur) {
		onStack[cur]=true;
		reached[cur]=true;
		for (int next:G.adj(cur)){
			if (onStack[next]){
				hasCycle=true;
			}else{
				//from[next]=
				if (!reached[next])dfs(next);
			}
		}
		onStack[cur]=false;
		topo.push(cur);
	}
	
	public boolean hasCycle(){
		return hasCycle;
	}
	
	
	
	public List<Integer> topoSort(){
		if (hasCycle)return null;
		return dfsOrder();
	}
		
	public List<Integer> dfsOrder(){
		List<Integer> ans=new ArrayList<Integer>();
		while(!topo.isEmpty())ans.add(topo.pop());
		return ans;
	}
	
}
