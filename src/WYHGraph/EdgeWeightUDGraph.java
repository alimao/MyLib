package WYHGraph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EdgeWeightUDGraph extends UndirectedGraph {

	UDEdge ppp;
	//List<Edge>[] edges;
	List<Vetex> vetex;
	@SuppressWarnings("unchecked")
	public EdgeWeightUDGraph(int V) {
		super(V);
		//edges=new List[V];
		vetex=new ArrayList<Vetex>();
		for (int i=0;i<V;i++){
			//edges[i]=new ArrayList<Edge>();
			vetex.add(new Vetex(i));
		}
		
	}
	
	
	public void addEdge(int v, int w, int weight){
		addEdge(new UDEdge(v, w, weight));
	}
	
	
	public void addEdge(UDEdge e){
		if (checkBound(e.one()) || checkBound(e.other())){
			return;
		}
		super.addEdge(e.one(), e.other());
		//edges[e.one()].add(e);
		vetex.get(e.one()).addEdge(e);
		if (e.one()!=e.other()){
			//edges[e.other()].add(e);
			vetex.get(e.other()).addEdge(e);
		}
		
	}
	
	private boolean checkBound(int one) {
		if (one>=0 && one<v)return false;
		return true;
	}


	public List<UDEdge> adjEdges(int v){
		return vetex.get(v).allEdges();
	}
	
	public Set<UDEdge> allEdges(){
		Set<UDEdge> ans=new HashSet<UDEdge>();
		for (int i=0;i<v;i++){
			ans.addAll(vetex.get(i).allEdges());
			
		}
		return ans;
	}
	
	public List<Vetex> allVetex(){
		return vetex;
	}

	public EdgeWeightedDiGraph toEdgeWeightedDiGraph(){
		EdgeWeightedDiGraph ans=new EdgeWeightedDiGraph(v);
		for (int i=0;i<vetex.size();i++){
			Vetex ve=vetex.get(i);
			for(UDEdge e:ve.allEdges()){
				if (e.one()==i){
					ans.addEdge(i, e.other(), e.weight);
				}else if (e.other()==i){
					ans.addEdge(i, e.one(), e.weight);
				}
			}
		}
		return ans;
	}
}
