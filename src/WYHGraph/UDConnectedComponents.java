package WYHGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UDConnectedComponents implements CC{

	private Graph G;
	private int tag;
	private int[] compo;
	public UDConnectedComponents(Graph G) {
		this.G=G;
		compo=new int[G.V()];
		Arrays.fill(compo, -1);
		interate();
		
	}
	
	private void interate() {
		tag=0;
		for (int i=0;i<G.V();i++){
			if (compo[i]==-1){
				dfs(i);
				tag++;
			}
		}
	}

	private void dfs(int cur) {
		compo[cur]=tag;
		for (int next:G.adj(cur)){
			if (compo[next]==-1)dfs(next);
		}
	}
	
	public List<List<Integer>> components(){
		List<List<Integer>> ans=new ArrayList<List<Integer>>();
		for (int i=0;i<tag;i++){
			ans.add(new ArrayList<Integer>());
		}
		for (int i=0;i<G.V();i++){
			ans.get(compo[i]).add(i);
		}
		return ans;
	}
	
	
	@Override
	public boolean connected(int v, int w){
		if (compo[v]==compo[w]){
			return true;
		}
		return false;
	}
	@Override
	public int count(){
		return tag;
	}
	@Override
	public int id(int v){
		return compo[v];
	}
	
	@SuppressWarnings("unchecked")
	public String toString(){
		List<Integer>[] ans=new List[tag];
		for (int i=0;i<tag;i++){
			ans[i]=new ArrayList<Integer>();
			
		}
		for (int i=0;i<G.V();i++){
			ans[compo[i]].add(i);
		}
		
		StringBuffer s=new StringBuffer();
		s.append(tag).append(" Connected Components \n");
		for (int i=0;i<tag;i++){
			s.append("Component ").append(i).append(" : ");
			for (int j:ans[i]){
				s.append(j).append(" ");
			}
			s.append("\n");
		}
		return s.toString();
		
	}
	
	
}
