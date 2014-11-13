package WYHTree;

import java.util.Stack;

public class SplayTree {

	SplayNode root;
	public SplayNode splay(SplayNode node, int key){
		if (node==null)return null;
		Stack<SplayNode> s=new Stack<SplayNode>();
		while (node!=null && key!=node.key){
			s.push(node);
			if (node.key<key){
				node=node.left;
			}else{
				node=node.right;
			}
		}
		if (node==null){
			while(!s.isEmpty() && s.peek().key>key){
				s.pop();
			}
		}
		while(true){
			SplayNode cur=s.pop();
			if (s.isEmpty()){
				return cur;
			}
			SplayNode par=s.pop();
			SplayNode anc=s.isEmpty()?null:s.peek();
			SplayNode newCur=null;
			if (cur==par.left){
				newCur=rotateRight(par,cur,anc);
			}else{
				newCur=rotateLeft(par,cur,anc);
			}
			s.push(newCur);
		}
		
	}
		
	private SplayNode rotateLeft(SplayNode par, SplayNode cur, SplayNode anc) {
		par.right=cur.left;
		cur.left=par;
		if (anc==null){
			root=cur;
			return root;
		}
		if (anc.left==par){
			anc.left=cur;
		}else{
			anc.right=cur;
		}
		return cur;
		
	}

	private SplayNode rotateRight(SplayNode par, SplayNode cur, SplayNode anc) {
		par.left=cur.right;
		cur.right=par;
		if (anc==null){
			root=cur;
			return root;
		}
		if (anc.left==par){
			anc.left=cur;
		}else{
			anc.right=cur;
		}
		return cur;
		
	}

	public boolean insert(int key, int val){
		root=splay(root, key);
		if (root.key==key){
			root.val=val;
			return true;
		}
		SplayNode temp=new SplayNode(key, val);
		temp.left=root;
		temp.right=root.right;
		root.right=null;
		root=temp;
		return true;
	}
	
	public boolean delete(int key){
		root=splay(root, key);
		if (root.key!=key){
			return false;
		}
		SplayNode newRoot=splay(root.left, Integer.MAX_VALUE);
		newRoot.right=root.right;
		root=newRoot;
		return true;
	}
	
	public int search(int key){
		root=splay(root,key);
		if (root.key==key){
			return root.val;
		}
		return root.val;
	}
	
	
}

class SplayNode{
	int val;
	int key;
	SplayNode left;
	SplayNode right;
	public SplayNode(int key, int val) {
		this.val=val;
		this.key=key;
	}
	
}
