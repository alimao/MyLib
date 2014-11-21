package Sorter;

public class Sorter {

	public static void sort(Comparable[] data) {
		quickSort(data, 0, data.length - 1);
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
		String[] a = { "2", "3", "4", "5", "1", "6", "9", "8", "7" };

		sort(a);
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
	}
}
