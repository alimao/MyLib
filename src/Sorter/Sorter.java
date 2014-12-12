package Sorter;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import WYHBasic.Tuple3;

public class Sorter {

	static final int stringThreshold=100000;
	static final int intThreshold=1000000;
	@SuppressWarnings("rawtypes")
	public static void sort(Comparable[] data) {
		quickSort(data, 0, data.length - 1);
	}
	
	public static void sort(int[] data) {
		int n=data.length;
		if (n<intThreshold){
			quickSort(data, 0, data.length - 1);
		}else{
			radixSort(data);
		}
	}
	
	public static void sort(double[] data) {
		quickSort(data, 0, data.length - 1);
	}
	
	public static void sort(String[] data) {
		int n=data.length;
		if (n<=stringThreshold){
			quickSort(data, 0, data.length - 1);
		}else{
			radixSort(data);
		}
	}

	/**
	 * need review
	 * @param data
	 */
	private static void radixSort(String[] data) {
		int n=data.length;
		Queue<Tuple3<Integer,Integer,Integer>> q=new LinkedList<Tuple3<Integer,Integer,Integer>>();
		q.add(new Tuple3<Integer, Integer, Integer>(0, n, 0));
		String[] temp=new String[n];
		while(!q.isEmpty()){
			Tuple3<Integer,Integer,Integer> t=q.remove();
			int[] digits=new int[257];
			for (int i=t.t1;i<t.t2;i++){
				digits[charAt(data[i],t.t3)]++;
			}
			for (int i=1;i<257;i++){
				digits[i]+=digits[i-1];
			}
			for (int i=t.t2-1;i>=t.t1;i--){
				temp[(--digits[charAt(data[i],t.t3)])+t.t1]=data[i];
			}
			for (int i=t.t1;i<t.t2;i++){
				data[i]=temp[i];
			}
			for (int i=2;i<257;i++){
				if (digits[i]>digits[i-1]+1){
					q.add(new Tuple3<Integer, Integer, Integer>(t.t1+digits[i-1],t.t1+digits[i],t.t3+1));
				}
			}	
		}
		
	}

	private static int charAt(String string, Integer t3) {
		if (t3>=string.length()){
			return 0;
		}
		return string.charAt(t3);
	}

	private static void radixSort(int[] data) {
		int n=data.length;
		int base=0x000000FF;
		int[] temp=new int[n];
		for (int p=0;p<4;p++){
			int[] count=new int[256];
			for (int i=0;i<n;i++){
				count[(data[i]>>(p*8))&base]++;
			}
			for (int i=1;i<256;i++){
				count[i]+=count[i-1];
			}
			for (int i=n-1;i>=0;i--){
				temp[--count[(data[i]>>(p*8))&base]]=data[i];
			}
			for (int i=0;i<n;i++)
				data[i]=temp[i];
		}
		
		for (int i=0;i<n;i++){
			if (data[i]<0){
				//temp=Arrays.copyOfRange(data, i, n);
				for (int j=0;j<n-i;j++){
					temp[j]=data[i+j];
				}
				for (int j=0;j<i;j++){
					temp[n-i+j]=data[j];
				}
				for (int j=0;j<n;j++)
					data[j]=temp[j];
				break;
			}
		}
		
	}
	
	private static void quickSort(double[] data, int s, int e) {
		if (s >= e)
			return;
		double temp = data[e];
		int i = s;
		for (int j = s; j < e; j++) {
			if (data[j] > temp) {

			} else {
				double t = data[j];
				data[j] = data[i];
				data[i++] = t;
			}
		}
		double t = data[e];
		data[e] = data[i];
		data[i] = t;
		quickSort(data, s, i - 1);
		quickSort(data, i + 1, e);
		
	}

	private static void quickSort(int[] data, int s, int e) {
		if (s >= e)
			return;
		int temp = data[e];
		int i = s;
		for (int j = s; j < e; j++) {
			if (data[j] > temp) {

			} else {
				int t = data[j];
				data[j] = data[i];
				data[i++] = t;
			}
		}
		int t = data[e];
		data[e] = data[i];
		data[i] = t;
		quickSort(data, s, i - 1);
		quickSort(data, i + 1, e);
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void quickSort(Comparable[] data, int s, int e) {
		if (s >= e)
			return;
		Comparable temp = data[e];
		int i = s;
		for (int j = s; j < e; j++) {
			if (data[j].compareTo(temp) > 0) {

			} else {
				Comparable t = data[j];
				data[j] = data[i];
				data[i++] = t;
			}
		}
		Comparable t = data[e];
		data[e] = data[i];
		data[i] = t;
		quickSort(data, s, i - 1);
		quickSort(data, i + 1, e);
	}
	

	

	public static void main(String[] args) {
		//String[] a = { "2", "3", "4", "5","2", "3", "4", "5","2", "3", "4", "5", "1", "6", "9", "8", "7", "aaa", "bbb", "ccc" };
		//int[] b= {1,2,3,4,-1,-2,-3,-4,12332,1211,-123132,-12331122,-333333333,-1,2122222222};
		//sort(b);
		int n=100000000;
		String[] test=new String[n];
		for (int i=0;i<n;i++){
			test[i]=new Integer((int)(Math.random()*Integer.MAX_VALUE)).toString();
		}
		long pre=System.currentTimeMillis();
		sort(test);
		long post=System.currentTimeMillis();
		System.out.println(post-pre);
	}
}
