package WYHGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Vetex {

	int v;
	HashMap<Integer,UDEdge> hm=new HashMap<Integer, UDEdge>();
	public Vetex(int v) {
		this.v=v;
	}
	
	public void addEdge(UDEdge e){
		if (e.one()==v){
			hm.put(e.other(), e);
		}else if (e.other()==v){
			hm.put(e.one(), e);
		}
	}
	
	public UDEdge edgeTo(int w){
		if (!hm.containsKey(w))return null;
		return hm.get(w);
	}
	
	public List<UDEdge> allEdges(){
		ArrayList<UDEdge> ans=new ArrayList<UDEdge>();
		for (int v:hm.keySet()){
			ans.add(hm.get(v));
		}
		return ans;
	}

	public HashMap<Integer, UDEdge> allConnections() {
		return hm;
	}
}
