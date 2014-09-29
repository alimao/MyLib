package WYHGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class DFSearcher {

	Graph G;
	int start;
	int[] from;
	boolean[] reached;
	public DFSearcher(Graph G, int s) {
		this.G=G;
		start=s;
		from=new int[G.V()];
		Arrays.fill(from, -1);
		reached=new boolean[G.V()];
		search(start);
	}
	
	private void search(int cur) {
		reached[cur]=true;
		for (int next:G.adj(cur)){
			if (!reached[next]){
				search(next);
				from[next]=cur;
			}
		}
	}

	public List<Integer> searchPathTo(int v){
		List<Integer> ans=new ArrayList<Integer>();
		if (v!=start && from[v]==-1){
			return ans;
		}
		int cur=v;
		Stack<Integer> temp=new Stack<Integer>();
		while(cur!=-1){
			temp.push(cur);
			cur=from[cur];
		}
		while(!temp.isEmpty()){
			ans.add(temp.pop());
		}
		return ans;
	}
	
}
