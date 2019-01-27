package pack;

import java.util.Random;
import java.util.Scanner;
import java.util.logging.Logger;

/*
 * 
 * <h1>Find the length of longest continous subarray</h1>
 * Given an array of N integers, find the length of the longest continuous subarray so
that the median of the elements in that sub array is greater than or equal to a
given number x.

@author Amit Pandit
@version 1.0
@since   10-05-2018

 */

public class LongestSubArray {
	
	private static final Logger LOGGER = Logger.getLogger(LongestSubArray.class.getName());
	
	/*
	 * This is the main method which calls two methods
	 * 1.randomQuickSort()  - To sort the unsorted array.
	 * 2.findLongestSubArray() - To find the longest sub array from the sorted array.
	 * @param args Unused.
	 * @return Nothing.
	 */
	public static void main(String[] args) {
		
		LOGGER.info("Logger Name: "+LOGGER.getName());
		int lenghtOfArray;
		System.out.println("Enter the size of array:");
		Scanner sc = new Scanner(System. in );
		lenghtOfArray = sc.nextInt();
		
		int array[] = new int[lenghtOfArray];
		
		System.out.println("Enter the array elements:");
		for (int i = 0; i < lenghtOfArray; i++) {
			array[i] = sc.nextInt();
		}
		
		System.out.println("Enter the value of x");
		int x = sc.nextInt();
		
		sc.close();
		
		randomQuickSort(array, 0, array.length - 1);
		LOGGER.info(">> Array has been sorted");
		System.out.println(" The sorted form of the array is " + java.util.Arrays.toString(array));
		
		LOGGER.info(">> Finding sub-array started!");
		findLongestSubArray(array, x);
		
	}
	
	/*
	 * This method is used to find the Longest Sub Array from the sorted array
	 * @param array This is the sorted array
	 * @param x Value of x (median should be greater than x)
	 * @return nothing
	 */
	public static void findLongestSubArray(int[] array, int x) {
		double median = 0;
		int arrayLength = array.length;
		if (arrayLength % 2 == 0) {
			int sumOfMiddleElements = array[arrayLength / 2] + array[arrayLength / 2 - 1];
			median = ((double) sumOfMiddleElements) / 2;
		} else {
			median = (double) array[array.length / 2];
		}

		if (median >= x) {
			LOGGER.info(">> Sub-array finding completed..!");
			System.out.println("Longest sub-array is of length " + array.length);
		}
		else {
			
			int[] subArray = new int[array.length - 1];
			int j = 1;
			for (int i = 0; i < subArray.length; i++) {
				subArray[i] = array[j];
				j++;
			}
			LOGGER.info(">> Removed first element from array");
			findLongestSubArray(subArray, x);
			
		}
	}
	/*
	 * This method is use to sort the unsorted array.
	 * @param array Unsorted array given as input from user
	 * @param left First element index of the array
	 * @param right Last element index of the array
	 * @return nothing
	 */
	public static void randomQuickSort(int a[], int left, int right) {
		Random rand = new Random();
		int pivot = left + rand.nextInt(right - left + 1);

		LOGGER.info(">> Random pivot element picked");
		
		swap(a,pivot,right);
		pivot = a[right];

		int index = partition(a, left, right, pivot);

		if (left < index - 1) randomQuickSort(a, left, index - 1);

		if (index < right) randomQuickSort(a, index, right);

	}
	
	/*
	 * This method is used as a part of above RandomQuickSort() to perform partioning on the sub arrays.
	 * @param a - array/sub-array where sorting/swapping needs to be performed
	 * @param left - First element index of array/subarray
	 * @param right - Last element index of array/subarray
	 * @param pivot - pivot element selected using randmon fashion used for performing swapping
	 * @param return - index of new pivot element
	 */
	public static int partition(int a[], int left, int right, int pivot) {
		int i = left;
		for (int j = left; j < right; j++) {
			if (a[j] <= pivot) {
				swap(a, i, j);
				i++;
			}
		}
		swap(a, i, right);
		return i;
	}

	/*
	 * This method is used for swapping elements
	 * @param a  array elements where swapping needs to be performed.
	 * @param i  index position where swapping needs to be performed.
	 * @param j  index position where swapping needs to be performed.
	 */
	public static void swap(int a[], int i, int j) {
		int temp;
		temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}