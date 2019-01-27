package pack;

import java.util.Random;

public class Bucket {
	public static void main(String[] args) 
    {
        Random randGen = new Random();
        int[] A1 = new int[100];
        int[] A2 = new int[100];
        for (int k = 0; k < A1.length; k++)
        {
            int data = randGen.nextInt(500) + 1;
            A1[k] = data;
            A2[k] = data;
            System.out.printf("%5d", data);
            if ((k+1) % 20 == 0)            System.out.println();            
        }
        
        System.out.println();       
        
        /*int[] solution1 = solution1(A1);
        System.out.printf("Max difference: %5d - %5d = %5d\n", 
                solution1[1], solution1[0], solution1[1] - solution1[0]);
        */
        int[] solution2 = solution2(A2);
        System.out.printf("Max difference: %5d - %5d = %5d\n", 
                solution2[1], solution2[0], solution2[1] - solution2[0]);         
        
        for (int k = 0; k < A1.length; k++)
        {
            System.out.printf("%5d", A1[k]);
            if ((k+1) % 20 == 0)            System.out.println();            
        }
        
        System.out.println("Array is sorted: " + isSorted(A1));
       
    }
	
	 public static int[] solution2(int[] A)
	    {
	        sort(A);
	        int[] result = {-1, -1};
	        int maxDiff = Integer.MIN_VALUE;
	        for (int k = 1; k < A.length; k++)
	            if (A[k] - A[k-1] > maxDiff)
	            {
	                maxDiff = A[k] - A[k-1];
	                result[1] = A[k];
	                result[0] = A[k-1];
	            }
	        return result;
	    }
	 private static void sort(int[] A)
	    {
	        int max = findMax(A);
	        int[] count = new int[max+2];
	        for (int k = 0; k < A.length; k++)      count[A[k] + 1]++;        
	        for (int k = 0; k < max; k++)           count[k+1] += count[k];
	        
	        int[] aux = new int[A.length];
	        for (int k = 0; k < A.length; k++)      aux[count[A[k]]++] = A[k];
	        System.arraycopy(aux, 0, A, 0, A.length);
	    }
	    
	    private static int findMax(int[] A)
	    {
	        int max = Integer.MIN_VALUE;
	        for (int x : A)             
	            if (x > max)            max = x;
	        return max;
	    }
	    
	    private static boolean isSorted(int[] A)
	    {
	        for (int k = 1; k < A.length; k++)
	            if (A[k] < A[k-1])              return false;
	        return true;
	    }
}
