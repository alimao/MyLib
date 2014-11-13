package WYHSegmentTree;
/**
 * interval - input
 * point - query
 * @author Yihan
 *
 */
public class SegmentTree2 {

	int size=0;
	int size2=0;
	int[] data=null;
	public SegmentTree2(int size) {
		this.size=size;
		//TODO
		size2=1;
		while(size2<size)size2=size2<<1;
		this.data=new int[size2<<1|1];
	}
	
	public void insert(int start, int end){
		insert(1,size2, start,end,1);
	}

	private void insert(int left, int right, int start, int end, int pointer) {
		int mid=(left+right)>>1;
		if (end<left || start>right){
			return;
		}
		if (start<=left && end>=right){
			data[pointer]++;
			return;
		}
		if (start<=mid){
			insert(left,mid,start,end,pointer<<1);
		}
		if (end>mid){
			insert(mid+1,right,start,end,pointer<<1|1);
		}
	}
	
	public int query(int position){
		if (position<1 || position>size){
			return 0;
		}
		return query(1,size2,position,1);
	}

	private int query(int left, int right, int position, int pointer) {
		if (left==right){
			return data[pointer];
		}
		int mid=(left+right)>>1;
		if (position<=mid){
			return query(left,mid,position,pointer<<1)+data[pointer];
		}else{
			return query(mid+1,right,position,pointer<<1|1)+data[pointer];
		}
	}
	
	//test
	public static void main(String[] args) {
		SegmentTree2 st=new SegmentTree2(10);
		st.insert(1, 10);st.insert(5, 6);st.insert(6, 8);
		System.out.print(st.query(6));
	}
}
