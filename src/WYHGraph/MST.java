package WYHGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import WYHBasic.Pair;
import WYHPriorityQueue.FibonacciHeap;
import WYHPriorityQueue.PriorityQueue;

public class MST {

	private final int MAX=Integer.MAX_VALUE;
	private EdgeWeightUDGraph G;
	private List<UDEdge> edges;
	private int weight;
	boolean[] used;
	public MST(EdgeWeightUDGraph G) {
		this.G=G;
		used=new boolean[G.V()];
		
	}
	/**
	 * O(eloge)
	 * 
	 */
	private void prim1(){
		edges=new ArrayList<UDEdge>();
		if (G.V()<2)return;
		PriorityQueue<UDEdge, Integer> pq=new FibonacciHeap<UDEdge,Integer>();
		used[0]=true;
		for(UDEdge e:G.adjEdges(0)){
			pq.insert(e, e.weight);
		}
			
		while(!pq.isEmpty()){
			UDEdge e;
			do{
				e=pq.extractMin();
			}while(e!=null && used[e.one()] && used[e.other()]);
			if (e==null)break;
			edges.add(e);
			weight+=e.weight;
			if (used[e.one()]){
				
				used[e.other()]=true;
				for(UDEdge newE:G.adjEdges(e.other()))
					pq.insert(newE, newE.weight);
			}else{
				used[e.one()]=true;
				for(UDEdge newE:G.adjEdges(e.one()))
					pq.insert(newE, newE.weight);
			}
		}
	}
	/**
	 * O(e+vlogv)
	 */
	@SuppressWarnings("unchecked")
	private void prim2(){
		PriorityQueue<Pair<Integer,UDEdge>, Integer> pq=new FibonacciHeap<Pair<Integer,UDEdge>, Integer>();
		List<Vetex> vtemp=G.allVetex();
		Pair<Integer,UDEdge>[] pairs=new Pair[G.V()];
		
		for (int v=0;v<G.V();v++){
			pairs[v]=new Pair<Integer, UDEdge>(v, null);
			pq.insert(pairs[v] , MAX);
		}
		HashMap<Integer, UDEdge> c=vtemp.get(0).allConnections();
		for (int vto:c.keySet()){
			pairs[vto].o2=c.get(vto);
			pq.decreaseKey(pairs[vto], c.get(vto).weight);
		}
		pq.delete(pairs[0]);
		edges=new ArrayList<UDEdge>();
		while(!pq.isEmpty()){
			Pair<Integer, UDEdge> pair=pq.extractMin();
			edges.add(pair.o2);
			c=vtemp.get(pair.o1).allConnections();
			for (int vto:c.keySet()){
				if (pq.decreaseKey(pairs[vto], c.get(vto).weight)){
					pairs[vto].o2=c.get(vto);
				}
			}
		}
	}
	
	public List<UDEdge> edges(){
		prim1();
		return edges;
	}
	
	public List<UDEdge> edges1(){
		prim2();
		return edges;
	}
	public int weight(){
		return weight;
	}
}
