package Map;

import java.util.HashMap;

public class BidiHashMap<K,V> {

	HashMap<K,V> hm1=new HashMap<K, V>();
	HashMap<V,K> hm2=new HashMap<V, K>();
	/*
	public boolean put(K key, V val){
		if (hm1.containsKey(key)){
			V v=hm1.get(key);
			hm1.put(key, val);
			hm2.remove(v);
			hm2.put(val, key);
		}else{
			if (hm2.containsKey(val)){
				K k=hm2.get(val);
				hm2.put(val, key);
				hm1.remove(k);
				hm1.put(key, val);
			}else{
				hm1.put(key, val);
				hm2.put(val, key);
			}
		}
		return true;
	}*/
	public boolean put(K key, V val){
		if (contains(key,val)){
			return true;
		}
		if (containsKey(key) || containsVal(val)){
			return false;
		}
		hm1.put(key, val);
		hm2.put(val, key);
		return true;
	}
	
	public boolean remove(K key, V val){
		if (contains(key,val)){
			hm1.remove(key);
			hm2.remove(val);
			return true;
		}
		return false;
	}
	
	public boolean contains(K key, V val){
		if (hm1.containsKey(key) && hm1.get(key).equals(val)){
			return true;
		}
		return false;
	}
	
	public boolean containsKey(K key){
		return hm1.containsKey(key);
	}
	
	public boolean containsVal(V val){
		return hm2.containsKey(val);
	}
	
	
	
}
