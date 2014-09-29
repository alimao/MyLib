package WYHPriorityQueue;

import java.util.HashMap;


public class FibonacciHeap<T, E extends Comparable<E>> extends PriorityQueue<T,E>{

	private final double fib=(1.0+Math.sqrt(5))/2;
	protected FNode<T,E> min;
	private int size;
	private HashMap<T,FNode<T,E>> hm;
	public FibonacciHeap() {
		min=null;
		size=0;
		hm=new HashMap<T, FNode<T,E>>();
	}
	
	public E minKey(){
		return min.key;
	}
	
	@Override
	public void insert(T obj,E key) {
		if (min==null){
			min=new FNode<T,E>(obj,key);
			hm.put(obj, min);
			min.left=min;
			min.right=min;
			size++;
			return;
		}
		FNode<T,E> temp=new FNode<T,E>(obj,key);
		insertRootList(temp);
		hm.put(obj, temp);
		size++;
		if (temp.key.compareTo(min.key)<0){
			min=temp;
		}
		
	}

	private void insertRootList(FNode<T,E> temp) {
		temp.p=null;
		temp.color=false;
		temp.right=this.min.right;
		temp.left=min;
		min.right.left=temp;
		min.right=temp;
	}

	@Override
	public T extractMin() {
		if (min==null){
			return null;
		}
		size--;
		T ans=min.val;
		if (min.child!=null){
			FNode<T,E> temp1=min.child;
			FNode<T,E> temp2=min.child.right;
			while(temp1!=temp2){
				temp1.p=null;
				temp1=temp1.left;
			}
			temp1.p=null;
			temp1=min.child;
			temp1.right=min;
			min.left.right=temp2;
			temp2.left=min.left;
			min.left=temp1;
			min.left.right=min.right;
			min.right.left=min.left;
			min=temp1;
		}else{
			if (min.right==min){
				min=null;
			}else{
				min.left.right=min.right;
				min.right.left=min.left;
				min=min.right;
			}
		}
		consolidate();
		hm.remove(ans);
		return ans;
	}

	@SuppressWarnings("unchecked")
	private void consolidate() {
		if (min==null)return;
		int n=(int)(Math.log(size)/Math.log(fib))+1;
		FNode<T,E>[] axu=new FNode[n];
		FNode<T,E> cur=min.right;
		FNode<T,E> next;
		for (int i=0;i<n;i++)axu[i]=null;

		axu[min.degree]=min;
		while(cur!=min){
			next=cur.right;
			while (axu[cur.degree]!=null){
//				cur.left.right=cur.right;
//				cur.right.left=cur.left;
				if (cur.key.compareTo(axu[cur.degree].key)<0){
					FNode<T,E> temp0=axu[cur.degree];
					axu[cur.degree]=cur;
					cur=temp0;
				}
				if (cur.degree==0){
					cur.p=axu[0];
					axu[0].child=cur;
					cur.right=cur;
					cur.left=cur;
					axu[0].degree++;
					cur=axu[0];
					axu[0]=null;
				}else{
					cur.p=axu[cur.degree];
					cur.left=axu[cur.degree].child;
					cur.right=axu[cur.degree].child.right;
					axu[cur.degree].child.right.left=cur;
					axu[cur.degree].child.right=cur;
					axu[cur.degree].degree++;
					cur=axu[cur.degree];
					axu[cur.degree-1]=null;
				}
			}
			axu[cur.degree]=cur;
			cur=next;
		}
		int k=0;
		for (int i=0;i<n;i++){
			if (axu[i]!=null){
				k=i;
				break;
			}
		}
		min=axu[k];
		min.left=min;
		min.right=min;
		cur=min;
		for (int i=k+1;i<n;i++){
			if (axu[i]!=null){
				insertRootList(axu[i]);
				if (axu[i].key.compareTo(min.key)<0)min=axu[i];
				//System.out.println(cur.val.getKey());
			}
		}
	}

	@Override
	public T min() {
		return min.val;
	}

	
	
	@Override
	public boolean decreaseKey(T x, E y) {
		if (!hm.containsKey(x))return false;
		FNode<T,E> obj=hm.get(x);
		if (obj.key.compareTo(y)<=0)return false;
		
		
		obj.key=y;
		FNode<T,E> par=obj.p;
		
		if (par!=null && par.key.compareTo(obj.key)>0){
			cut(obj);
		}
		if (obj.key.compareTo(min.key)<0)min=obj;
		
		return true;
	}

	private void cut(FNode<T,E> obj) {
		FNode<T,E> par=obj.p;
		if (par==null)return;
		if (obj.right==obj){
			par.child=null;
			par.degree=0;
		}else{
			obj.right.left=obj.left;
			obj.left.right=obj.right;
			par.child=obj.right;
			par.degree--;
		}
		insertRootList(obj);
		if (!par.color){
			par.color=true;
		}else{
			cut(par);
		}
		
	}

	@Override
	public boolean delete(T x) {
		if (!hm.containsKey(x))return false;
		FNode<T,E> obj=hm.get(x);
		if (obj==min){
			extractMin();
			return true;
		}
		hm.remove(x);
		cut(obj);
		if (obj.child==null){
			if (obj.right==obj){
				min=null;
			}else{
				obj.left.right=obj.right;
				obj.right.left=obj.left;
			}
		}else{
			FNode<T,E> temp1=obj.child;
			FNode<T,E> temp2=obj.child.right;
			while(temp1!=temp2){
				temp1.p=null;
				temp1=temp1.left;
			}
			temp1.p=null;
			temp1=obj.child;
			temp1.right=obj;
			obj.left.right=temp2;
			temp2.left=obj.left;
			obj.left=temp1;
			obj.left.right=obj.right;
			obj.right.left=obj.left;
		}
		--size;
		return true;
		
	}

	@Override
	public boolean union(PriorityQueue<T,E> pq) {
		if (!(pq instanceof FibonacciHeap)){
			return false;
		}
		FibonacciHeap<T,E> pq2=(FibonacciHeap<T,E>)pq;
		if (pq2.min==null)return true;
		min.right.left=pq2.min;
		pq2.min.right.left=min;
		FNode<T,E> temp=min.right;
		min.right=pq2.min.right;
		pq2.min.right=temp;
		if (pq2.min.key.compareTo(min.key)<0){
			min=pq2.min;
		}
		size+=pq2.size;
		return true;
	}

	public int size() {
		return size;
	}

	public String toString(){
		String ans="";
		if (min==null)return ans;
		FNode<T,E> cur=min;
		do{
			ans+=cur.toString()+"\n";
			cur=cur.right;
		}while(cur!=min);
		return ans;
		
	}

	@Override
	public boolean isEmpty() {
		if (size>0)return false;
		return true;
	}
}


class FNode<T, E extends Comparable<E>>{
	E key;
	T val;
	int degree;
	boolean color;
	FNode<T,E> p;
	FNode<T,E> child;
	FNode<T,E> left;
	FNode<T,E> right;
	public FNode(T v,E e) {
		degree=0;
		val=v;
		color=false;
		p=null;
		child=null;
		key=e;
	}
	
	public String toString(){
		if (this.val==null)return "";
		String ans=key.toString();
		if (child==null)return ans;
		ans+=" : ";
		FNode<T,E> cur=this.child;
		do{
			ans+=cur.toString()+" & ";
			cur=cur.right;
		}while(cur!=child);
		return ans;
	}
}