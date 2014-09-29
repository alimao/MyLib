package WYHGraph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EdgeWeightedDiGraph extends DirectedGraph{

	List<DiVetex> vetex;
	public EdgeWeightedDiGraph(int v) {
		super(v);
		vetex=new ArrayList<DiVetex>();
		for (int i=0;i<v;i++){
			vetex.add(new DiVetex(i));
		}
	}
	
	public void addEdge(int f,int t,int w){
		addEdge(new DiEdge(f, t, w));
	}

	public void addEdge(DiEdge edge){
		super.addEdge(edge.from(), edge.to());
		vetex.get(edge.from()).addEdge(edge);
	}
	
	public List<DiEdge> adjEdges(int v){
		return vetex.get(v).allEdges();
	}
	
	public Set<DiEdge> allEdges(){
		Set<DiEdge> ans=new HashSet<DiEdge>();
		for (int i=0;i<this.V();i++){
			ans.addAll(vetex.get(i).allEdges());
		}
		return ans;
	}
	
	public List<DiVetex> allVetex(){
		return vetex;
	}
	
}
