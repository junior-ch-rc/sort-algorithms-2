package sort;


public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arranjo = { 8, 6, 1, 14, 15, 19, 19, 2, 0 };

		// Sort.countingSort(arranjo, 19);
		// Sort.radixSort(arranjo, 19);
		Sort.bucketSort(arranjo, 19);

		for (int i = 0; i < arranjo.length; i++) {
			System.out.print(arranjo[i] + " ");
		}
	}

}
