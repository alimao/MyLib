package WYHTree;
/**
 * segment tree for dynamic storing and querying the sum of points in tree. 
 * @author Yihan
 *
 */
public class SegmentTree1 {

	private int n;
	segTreeNode1 root;
	public SegmentTree1(int len) {
		n = len;
		root = initializeNodes(0, n, null);
	}
	private segTreeNode1 initializeNodes(int start, int end, segTreeNode1 p) {
		segTreeNode1 cur = new segTreeNode1(start, end);
		cur.parent = p;
		int mid = (start + end)/2;
		if (mid > start)cur.left = initializeNodes(start, mid, cur);
		else cur.left = null;
		if (mid < end)cur.right = initializeNodes(mid, end, cur);
		else cur.right = null;
		return cur;
	}
	
	public boolean insert(int node, int val) {
		if (node >= n | node < 0)return false;
		segTreeNode1 temp;
		for (temp = root; temp.startNum != temp.endNum - 1; ){
			int mid = (temp.startNum + temp.endNum) / 2;
			if (node < mid) temp = temp.left;
			else temp = temp.right;
		}
		int dif = val - temp.val;
		
		while (temp != null) {
			temp.val += dif; 
			temp = temp.parent;
		}
		return true;
	}
	
	public int read(int start, int end) {
		if (end <= start) return 0;
		return read(start, end, root);
	}
	
	private int read(int start, int end, segTreeNode1 root){
		if (start <= root.startNum & end >= root.endNum){
			return root.val;
		}
		int mid = (root.startNum + root.endNum) / 2;
		int ans=0;
		if (end > mid){	
			ans += read(start, end, root.right);
		}
		if (start < mid){
			ans += read(start, end, root.left);
		}
		return ans;
	}
}

class segTreeNode1 {
	int startNum;
	int endNum;
	segTreeNode1 left;
	segTreeNode1 right;
	segTreeNode1 parent;
	int val;
	public segTreeNode1(int start, int end) {
		startNum = start;
		endNum = end;
		val = 0;
	}
	
}