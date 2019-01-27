package pack;

import java.util.Random;
import java.util.Scanner;



public class quicksort {
	
	public static void main(String[] args) {
		
		

		int lenghtOfArray;
		System.out.println("Enter the size of array:");
		Scanner sc = new Scanner(System. in );
		lenghtOfArray = sc.nextInt();
		
		int array[] = new int[lenghtOfArray];
		
		System.out.println("Enter the array elements:");
		for (int i = 0; i < lenghtOfArray; i++) {
			array[i] = sc.nextInt();
		}
	
		
		sc.close();
		
		randomQuickSort(array, 0, array.length - 1);
		System.out.println(" The sorted form of the array is " + java.util.Arrays.toString(array));
		
	}
	
	
	public static void randomQuickSort(int a[], int left, int right) {
		Random rand = new Random();
		int pivot = left + rand.nextInt(right - left + 1);

		System.out.println("pivot selected is at position "+pivot+"having value "+a[pivot]);
		swap(a,pivot,right);
		System.out.println("Array now is" + java.util.Arrays.toString(a));
		pivot = a[right];
		System.out.println("pivot value placing at last position");
		int index = partition(a, left, right, pivot);
		
		System.out.println("index now is"+index);
		if (left < index - 1) randomQuickSort(a, left, index - 1);

		if (index < right) randomQuickSort(a, index, right);

	}
	
	
	public static int partition(int a[], int left, int right, int pivot) {
		int i = left;
		for (int j = left; j < right; j++) {
			if (a[j] <= pivot) {
				System.out.println("in partition loop - swapping "+a[j]+"and"+a[i]);
				System.out.println("i="+i+"j="+j);
				swap(a, i, j);
				System.out.println("Array now is" + java.util.Arrays.toString(a));
				i++;
			}
		}
		System.out.println("out of loop = swap i="+i+"right="+right);
		swap(a, i, right);
		System.out.println("Array now is" + java.util.Arrays.toString(a));
		return i;
	}

	public static void swap(int a[], int i, int j) {
		int temp;
		temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}