package WYHSegmentTree;
/**
 * point input
 * max in segment output
 * @author Yihan
 *
 */
public class SegmentTree3 {

	int size=0;
	int size2=0;
	int[] data=null;
	public SegmentTree3(int size) {
		this.size=size;
		//TODO
		size2=1;
		while(size2<size)size2=size2<<1;
		this.data=new int[size2<<1|1];
	}
	
	public int query(int start, int end){
		if (end<start)return Integer.MIN_VALUE;
		return query(1,size2, start,end,1);
	}

	private int query(int left, int right, int start, int end, int pointer) {
		int mid=(left+right)>>1;
		if (end<left || start>right){
			return Integer.MIN_VALUE;
		}
		if (start<=left && end>=right){
			return data[pointer];
		}
		int l,r;
		l=r=Integer.MIN_VALUE;
		if (start<=mid){
			l=query(left,mid,start,end,pointer<<1);
		}
		if (end>mid){
			r=query(mid+1,right,start,end,pointer<<1|1);
		}
		return Math.max(l, r);
	}
	
	public void insert(int position, int value){
		if (position<1 || position>size){
			return;
		}
		insert(1,size2,position,value,1);
	}

	private void insert(int left, int right, int position, int value, int pointer) {
		
		if (left==right){
			data[pointer]=value;
			return;
		}
		int mid=(left+right)>>1;
		if (position<=mid){
			insert(left,mid,position,value,pointer<<1);
		}else{
			insert(mid+1,right,position,value,pointer<<1|1);
		}
		data[pointer]=Math.max(data[pointer<<1],data[pointer<<1|1]);
		
		
	}
	
	//test
	public static void main(String[] args) {
		SegmentTree3 st=new SegmentTree3(10);
		st.insert(1, 6);
		st.insert(2, 4);
		st.insert(3, 1);
		st.insert(4, 8);
		st.insert(5, 9);
		st.insert(6, 2);
		st.insert(7, 3);
		st.insert(8, 4);
		st.insert(9, 5);
		st.insert(10, 1);
		st.insert(5, 1);
		System.out.println(st.query(1, 8));
	}
	
}
