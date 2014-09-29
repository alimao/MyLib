package WYHGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import WYHPriorityQueue.FibonacciHeap;
import WYHPriorityQueue.PriorityQueue;

public class ShortestPath {

	EdgeWeightedDiGraph G;
	int source;
	int end;
	int[] len;
	int[] edgeTo;
	
	final int MAX=Integer.MAX_VALUE/2;
	public ShortestPath(EdgeWeightedDiGraph eg, int s,int method) {
		if (s<0 || s>=eg.V())s=0;
		G=eg;
		source=s;
		len=new int[eg.V()];
		Arrays.fill(len, MAX);
		len[source]=0;
		edgeTo=new int[eg.V()];
		Arrays.fill(edgeTo, -1);
		edgeTo[s]=s;
		if (method==1)floyd();
		if (method==2)dijkstra();
	}
	
	private void dijkstra(){
		PriorityQueue<Integer, Integer> pq=new FibonacciHeap<Integer,Integer>();
		for (int i=0;i<G.V();i++){
			pq.insert(i, MAX);
		}
		pq.decreaseKey(source, 0);
		while(!pq.isEmpty()){
			//int key=pq.minKey();
			int next=pq.extractMin();
			//len[next]=key;
			for (DiEdge ee:G.adjEdges(next)){
				relaxD(pq,ee);
			}
		}
	}
	
	private void relaxD(PriorityQueue<Integer, Integer> pq, DiEdge ee) {
		if (len[ee.to()]>len[ee.from()]+ee.weight()){
			len[ee.to()]=len[ee.from()]+ee.weight();
			edgeTo[ee.to()]=ee.from();
			pq.decreaseKey(ee.to(), len[ee.to()]);
		}
	}

	private void floyd(){
		for (int i=0;i<G.V();i++){
			for (int j=0;j<G.V();j++){
				for (DiEdge e: G.adjEdges(j)){
					relax(e);
				}
			}
		}
	}
	
	public int distTo(int v){
		return len[v];
	}
	
	private void relax(DiEdge e){
		if (len[e.to()]>len[e.from()]+e.weight()){
			len[e.to()]=len[e.from()]+e.weight();
			edgeTo[e.to()]=e.from();
		}
	}
	
	public List<Integer> pathTo(int e){
		Stack<Integer> path=new Stack<Integer>();
		ArrayList<Integer> ans=new ArrayList<Integer>();
		int t=e;
		while(t!=source){
			path.push(t);
			t=edgeTo[t];
		}
		ans.add(source);
		while(!path.isEmpty()){
			ans.add(path.pop());
		}
		return ans;
	}
	
	public boolean hasPathTo(int e){
		return len[e]!=MAX;
	}
	
	
	
	
}
