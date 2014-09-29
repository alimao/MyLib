package WYHGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class BFSearcher {

	Graph G;
	int start;
	int[] from;
	boolean[] reached;
	public BFSearcher(Graph G, int s) {
		this.G=G;
		start=s;
		from=new int[G.V()];
		Arrays.fill(from, -1);
		reached=new boolean[G.V()];
		search(start);
	}
	
	private void search(int start) {
		Queue<Integer> queue=new LinkedList<Integer>();
		queue.add(start);
		reached[start]=true;
		while(!queue.isEmpty()){
			int cur=queue.remove();
			reached[cur]=true;
			for (int next: G.adj(cur)){
				if (!reached[next]){
					queue.add(next);
					from[next]=cur;
					reached[next]=true;
					
				}
			}
		}
	}
	public List<Integer> searchPathTo(int v) {
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
