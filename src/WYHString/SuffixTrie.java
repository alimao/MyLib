package WYHString;

import java.util.HashMap;

public class SuffixTrie {

	String string;
	int size;
	SuffixNode root;
	public SuffixTrie(String string) {
		this.string=string;
		root=new SuffixNode("");
		size=string.length();
		buildSuffixTrie();
	}
	private void buildSuffixTrie() {
		SuffixNode head=new SuffixNode("");
		head.next=root;
		
		for (int i=0;i<size;i++){
			SuffixNode cur=head;
			SuffixNode newHead=new SuffixNode("");
			SuffixNode pre=newHead;
			while(cur.next!=null){
				cur=head.next;
				if (!cur.hm.containsKey(string.charAt(i))){
					cur.hm.put(string.charAt(i), new SuffixNode(cur.key+string.charAt(i)));
				}
				pre.next=cur.hm.get(string.charAt(i));
				pre=pre.next;
			}
			pre.next=root;
			head=newHead;
		}
		
	}
	
	
}

class SuffixNode{
	SuffixNode next;
	HashMap<Character, SuffixNode> hm;
	String key;
	public SuffixNode(String key) {
		next=null;
		hm=new HashMap<Character, SuffixNode>();
		this.key=key;
	}
}
