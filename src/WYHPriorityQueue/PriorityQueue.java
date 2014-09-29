package WYHPriorityQueue;

public abstract class PriorityQueue<T,E extends Comparable<E>> {

	protected Object min;
	public abstract void insert(T obj,E key);
	public abstract T extractMin();
	public abstract T min();
	public abstract E minKey();
	/**
	 * if Y<X then decreasing the key of X to the key of Y
	 * 
	 * @param x	origin Object
	 * @param y	new object with a small key
	 * @return	true: X is in this PQ and Y<X; false: X is not in this PQ or Y>=X
	 */
	public abstract boolean decreaseKey(T x, E y);
	public abstract boolean delete(T x);
	public abstract boolean union(PriorityQueue<T,E> pq2);
	public abstract boolean isEmpty();
	
}
