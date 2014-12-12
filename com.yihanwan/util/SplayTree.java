package util;

import java.util.Stack;

public class SplayTree <K extends Comparable<K>, V>{

	SplayNode<K,V> max;
	SplayNode<K,V> root;
	public SplayTree() {
		root=null;
		max=null;
	}
	public SplayNode<K,V> splay(SplayNode<K,V> node, K key){
		if (node==null)return null;
		Stack<SplayNode<K,V>> s=new Stack<SplayNode<K,V>>();
		while (node!=null && !key.equals(node.key)){
			s.push(node);
			if (node.key.compareTo(key)<0){
				node=node.left;
			}else{
				node=node.right;
			}
		}
		if (node==null){
			while(!s.isEmpty() && s.peek().key.compareTo(key)>0){
				s.pop();
			}
		}
		while(true){
			SplayNode<K,V> cur=s.pop();
			if (s.isEmpty()){
				return cur;
			}
			SplayNode<K,V> par=s.pop();
			SplayNode<K,V> anc=s.isEmpty()?null:s.peek();
			SplayNode<K,V> newCur=null;
			if (cur==par.left){
				newCur=rotateRight(par,cur,anc);
			}else{
				newCur=rotateLeft(par,cur,anc);
			}
			s.push(newCur);
		}
		
	}
		
	private SplayNode<K,V> rotateLeft(SplayNode<K,V> par, SplayNode<K,V> cur, SplayNode<K,V> anc) {
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

	private SplayNode<K,V> rotateRight(SplayNode<K,V> par, SplayNode<K,V> cur, SplayNode<K,V> anc) {
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

	public boolean insert(K key, V val){
		root=splay(root, key);
		if (root.key==key){
			root.val=val;
			return true;
		}
		SplayNode<K,V> temp=new SplayNode<K,V>(key, val);
		if (max==null)
			max=temp;
		else if (max.key.compareTo(key)<0){
			max=temp;
		}
		temp.left=root;
		temp.right=root.right;
		root.right=null;
		root=temp;
		return true;
	}
	
	public boolean delete(K key){
		root=splay(root, key);
		if (root.key!=key){
			return false;
		}
		if (max==root){
			root=root.left;
			SplayNode<K,V> temp=root;
			while(temp.right!=null)
				temp=temp.right;
			max=temp;
			return true;
		}
		SplayNode<K,V> newRoot=splay(root.left, max.key);
		newRoot.right=root.right;
		root=newRoot;
		return true;
	}
	
	public V search(K key){
		root=splay(root,key);
		if (root.key==key){
			return root.val;
		}
		return root.val;
	}
	
	
}

class SplayNode <K extends Comparable<K>, V>{
	V val;
	K key;
	SplayNode<K,V> left;
	SplayNode<K,V> right;
	public SplayNode(K key, V val) {
		this.val=val;
		this.key=key;
	}
	
}
