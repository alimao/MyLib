package WYHGraph;

public class GraphTester {

	public static void main(String[] args){
		int v=10;
		EdgeWeightedDiGraph test=new EdgeWeightedDiGraph(v);
		for (int i=0;i<v;i++){
			for (int j=0;j<v;j++){
				if (Math.random()<0.8){
					test.addEdge(new DiEdge(i, j,(int)(Math.random()*100)));
				}
			}
		}
		
		
		
		
		//DChecker pp=new DChecker(test);
		System.out.println(test);
		System.out.println(test.allEdges());
		
		
		System.out.println("next ps");
		ShortestPath sp=new ShortestPath(test, 0,1);
		ShortestPath sp1=new ShortestPath(test, 0,2);
		System.out.println(sp.pathTo(1));
		System.out.println(sp1.pathTo(1));
	}
}
