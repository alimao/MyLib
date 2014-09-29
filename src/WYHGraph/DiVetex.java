package WYHGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DiVetex {

	int v;
	HashMap<Integer,DiEdge> hm=new HashMap<Integer, DiEdge>();
	public DiVetex(int i) {
		v=i;
	}
	public void addEdge(DiEdge edge) {
		hm.put(edge.to(),edge);
		
	}
	public List<DiEdge> allEdges() {
		List<DiEdge> ans=new ArrayList<DiEdge>();
		for(int v:hm.keySet()){
			ans.add(hm.get(v));
		}
		return ans;
	}
	
	public DiEdge edgeTo(int v){
		if (hm.containsKey(v)){
			return hm.get(v);
		}else{
			return null;
		}
		
	}
	

}
