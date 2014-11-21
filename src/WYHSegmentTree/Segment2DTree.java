package WYHSegmentTree;

public class Segment2DTree {

	int row;
	int col;
	Seg2DNode root;
	public Segment2DTree(int row, int col) {
		this.row=row;
		this.col=col;
		root=new Seg2DNode(1, 1, row, col);
	}
	
	public void insert(int x, int y, int val){
		if (x<1 || x>row || y<1 || y>col)
			return;
		insert(x,y,val,root);
	}

	private void insert(int x, int y, int val, Seg2DNode cur) {
		if (cur.x1==cur.x2 && cur.y1==cur.y2){
			cur.max=val;
			return;
		}
		int mx=(cur.x1+cur.x2)>>1;
		int my=(cur.y1+cur.y2)>>1;
		if (x<=mx && y<=my){
			if (cur.ul==null){
				cur.ul=new Seg2DNode(cur.x1, cur.y1, mx, my);
			}
			insert(x,y,val,cur.ul);
		}else if (x>mx && y<=my){
			if (cur.dl==null){
				cur.dl=new Seg2DNode(mx+1, cur.y1, cur.x2, my);
			}
			insert(x,y,val,cur.dl);
		}else if (x<=mx && y>my){
			if (cur.ur==null){
				cur.ur=new Seg2DNode(cur.x1, my+1, mx, cur.y2);
			}
			insert(x,y,val,cur.ur);
		}else{
			if (cur.dr==null){
				cur.dr=new Seg2DNode(mx+1, my+1, cur.x2, cur.y2);
			}
			insert(x,y,val,cur.dr);
		}
		cur.max=Math.max(Math.max(cur.ul.max, cur.ur.max),Math.max(cur.dl.max, cur.dr.max));
	}
	
	public int query(int x1,int y1, int x2, int y2){
		return query(x1,y1,x2,y2,root);
	}

	private int query(int x1, int y1, int x2, int y2, Seg2DNode cur) {
		if (x1<=cur.x1 && y1<=cur.y1 && x2>=cur.x2 && y2>=cur.y2){
			return cur.max;
		}
		int mx=(cur.x1+cur.x2)>>1;
		int my=(cur.y1+cur.y2)>>1;
		int max=Integer.MIN_VALUE;
		if (x1<=mx && y1<=my){
			max=Math.max(max,query(x1,y1,x2,y2,cur.ul));
		}
		if (x1<=mx && y2>my){
			max=Math.max(max,query(x1,y1,x2,y2,cur.ur));
		}
		if (x2>mx && y1<=my){
			max=Math.max(query(x1,y1,x2,y2,cur.dl),max);
		}
		if (x2>mx && y2>my){
			max=Math.max(query(x1,y1,x2,y2,cur.dr),max);
		}
		return max;
	}
	
	
	
}
class Seg2DNode{
	int x1,x2,y1,y2;
	Seg2DNode ul,ur,dl,dr;
	int max;
	public Seg2DNode(int x1, int y1, int x2, int y2) {
		this.x1=x1;
		this.x2=x2;
		this.y1=y1;
		this.y2=y2;
	}
}

