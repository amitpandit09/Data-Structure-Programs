package pack;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Logger;

/*
 * <h1> Program to find first and second minimum difference</h1>
 * Given an unsorted array, Write a program that prints the sorted Array and then
prints the first minimum and second minimum difference between any successive
elements in its sorted form.
Your Output (sorted array, first and second minimum) should also be redirected
to a text file.
Return 0 if the array contains less than 2 elements.

@author Amit Pandit
@version 1.0
@since   10-05-2018

 */
public class MinimumFirstSecond {

 private static final Logger LOGGER = Logger.getLogger(LongestSubArray.class.getName());

 /*
	 * This is the main method which does below 
	 * 1.Takes input as array size and its elements from user
	 * 2.Calls other method to check if the array size is less then 2
	 * 3.Calls other method to check if the array size is equal to 2
	 * 4.Calls other method to perform sorting on the array
	 * 5.Calls other method to find the differences
	 * 6.Calls other method to write the output to file	
	 * @param args Unused.
	 * @return Nothing.
	 */
 public static void main(String[] args) {
  LOGGER.info("Logger Name: " + LOGGER.getName());
  int sizeOfArray;
  System.out.println("Enter the size of array:");
  Scanner sc = new Scanner(System.in);
  sizeOfArray = sc.nextInt();
  int array[] = new int[sizeOfArray];

  System.out.println("Enter the array elements:");
  for (int i = 0; i < sizeOfArray; i++) {
	  array[i] = sc.nextInt();
  }

  sc.close();

  if (!checkArrayElementsCount(array)) {
   System.out.println("Returning ZERO [0] -!!! Array contains less than 2 elements. Please provide more elements!!");
  } else {
	  randomQuicksort(array, 0, array.length - 1);
   System.out.println(" The sorted form of the array is " + java.util.Arrays.toString(array));
   writeToFile(java.util.Arrays.toString(array), "", "", false);
   if (array.length == 2) {
    twoElementsCheck(array);
   } else {
    minGap(array);
   }
  }
 }

/*
 * This method is used to write output to text file
 * @param sortedArray Array in the sorted form.
 * @param firstMinimum first minimum difference value
 * @param secondMinimum second minimum difference value
 * @param append To check whether function should be used to write sorted array into the file or append first & second minimum to output.txt file
 * @return nothing
 */
 public static void writeToFile(String sortedArray, String firstMinimum, String secondMinimum, boolean append) {
  BufferedWriter writer = null;
  try {
   File file = new File("C:\\Users\\amitp\\Documents\\workspace-sts-3.9.5.RELEASE\\DataStruc\\src\\pack\\output.txt");
   if (!file.exists()) {
    file.createNewFile();
    LOGGER.info(">> File does not exists !! Creating new !! ");
   }
   FileWriter fileName = new FileWriter(file, true);
   writer = new BufferedWriter(fileName);
  } catch (IOException e1) {
   // TODO Auto-generated catch block
   e1.printStackTrace();
  }
  try {
   if (!append) {
    writer.write(sortedArray);
    LOGGER.info(">> Sorted array dumped to text file");
   } else if (append) {
    writer.newLine();
    writer.append(firstMinimum);
    writer.newLine();
    writer.append(secondMinimum);
    LOGGER.info(">> Minimums dumped to text file");
   }
  } catch (IOException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
  try {
   writer.close();
  } catch (IOException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
 }

 /*
  * This method is used to check if the array size is of 2 elements.
  * @param arr Array given by user
  * @return nothing
  */
 public static void twoElementsCheck(int arr[]) {
  LOGGER.info(">> checking if array size is 1");
  if (arr.length - 1 == 2) {
   System.out.println("Array is of " + (arr.length - 1) + "elements");

  }
  nums first = new nums();

  first.diff = arr[1] - arr[0];
  System.out.println(" Minimum difference is single which is :" + first.diff);
 }
 
 /*
  * This method is used to find the first and second minimum difference in the sorted array.
  * @param arr Array in the sorted form.
  * @return nothing
  */
 public static void minGap(int arr[]) {
  if (arr.length - 1 == 2) {
   System.out.println("Array is of " + (arr.length - 1) + "elements");

  }
  //below objects are created for class nums
  nums first = new nums();
  nums second = new nums();
  nums temp = new nums();

  first.diff = arr[1] - arr[0];
  first.num1 = arr[0];
  first.num2 = arr[1];

  second.diff = arr[2] - arr[1];
  second.num1 = arr[1];
  second.num2 = arr[2];

  if (first.diff > second.diff) {
   temp = first;
   first = second;
   second = temp;
  }

  for (int i = 3; i < arr.length; i++) {
   if ((arr[i] - arr[i - 1]) < first.diff) {
    second = first;
    first.diff = arr[i] - arr[i - 1];
    first.num1 = arr[i - 1];
    first.num2 = arr[i];
   }
   if ((arr[i] - arr[i - 1]) > first.diff && (arr[i] - arr[i - 1]) < second.diff) {
    second.diff = arr[i] - arr[i - 1];
    second.num1 = arr[i - 1];
    second.num2 = arr[i];
   }
  }

  System.out.println("First Minimum –" + "[" + first.num1 + "," + first.num2 + "]-" + first.diff);
  String firstM = "First Minimum –" + "[" + first.num1 + "," + first.num2 + "]-" + first.diff;
  System.out.println("Second Minimum –" + "[" + second.num1 + "," + second.num2 + "]-" + second.diff);
  String secondM = "Second Minimum –" + "[" + second.num1 + "," + second.num2 + "]-" + second.diff;
  writeToFile("", firstM, secondM, true);

 }
/*
 * This method is used to check if the array size is less than 2
 * @param a Array given by the user as innput
 * @return boolean value as true/false
 */
 public static boolean checkArrayElementsCount(int a[]) {
  LOGGER.info(">> checking array length > 2 condition");
  if (a.length > 0 && a.length < 2) {
   return false;
  } else {
   return true;
  }
 }

 /*
	 * This method is use to sort the unsorted array.
	 * @param array Unsorted array given as input from user
	 * @param left First element index of the array
	 * @param right Last element index of the array
	 * @return nothing
	 */
 public static void randomQuicksort(int a[], int left, int right) {
  Random rand = new Random();
  int pivot = left + rand.nextInt(right - left + 1);
  LOGGER.info(" >> Sorting in process : random pivot selected ");
  int temp;
  temp = a[pivot];
  a[pivot] = a[right];
  a[right] = temp;

  pivot = a[right];

  int index = partition(a, left, right, pivot);

  if (left < index - 1)
	  randomQuicksort(a, left, index - 1);


  if (index < right)
	  randomQuicksort(a, index, right);


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
};
/*
 * class is created in order to attach a single element of array with difference values.
 */
final class nums {
 int diff;
 int num1;
 int num2;
}