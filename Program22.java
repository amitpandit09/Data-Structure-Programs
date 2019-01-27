package pack;

import java.util.Scanner;

public class Program22 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		int n;
		System.out.println("Enter the size of array:");
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		int a[]=new int[n];
		
		System.out.println("Enter the array elements:");
			for(int i=0;i<n;i++)
				{
					a[i]=sc.nextInt();
				}
		System.out.println("Enter the value of x");
		int x=sc.nextInt();
			sc.close();	
		int length=longestsubarray(a,a.length,x);
		System.out.println("length"+length);

	}
	static int maxIndexDiff(int arr[],  
            int n) 
{ 
int maxDiff; 
int i, j; 

int LMin[], RMax[]; 
LMin = new int[n]; 
RMax = new int[n]; 

// Construct LMin[] such that  
// LMin[i] stores the minimum value 
// from (arr[0], arr[1], ... arr[i]) 
LMin[0] = arr[0]; 
for (i = 1; i < n; ++i) 
LMin[i] = Math.min(arr[i], LMin[i - 1]); 

System.out.println("...amittt..Lmin. "+java.util.Arrays.toString(LMin));


// Construct RMax[] such that  
// RMax[j] stores the maximum value 
// from (arr[j], arr[j+1], ..arr[n-1]) 
RMax[n - 1] = arr[n - 1]; 
for (j = n - 2; j >= 0; --j) 
RMax[j] = Math.max(arr[j], RMax[j + 1]); 

System.out.println("...amittt..Rmin. "+java.util.Arrays.toString(RMax));
// Traverse both arrays from left 
// to right to find optimum j - i 
// This process is similar to merge() 
// of MergeSort 
i = 0; 
j = 0; 
maxDiff = -1; 
while (j < n && i < n) 
{ 
if (LMin[i] < RMax[j])  
{ 
maxDiff = Math.max(maxDiff, j - i); 
j = j + 1; 
} 
else
i = i + 1; 
} 

return (maxDiff + 1); 
} 

//utility Function which subtracts X  
//from all the elements in the array 
static void modifyarr(int arr[],  
          int n, int x) 
{ 
for (int i = 0; i < n; i++) 
arr[i] = arr[i] - x; 

} 

//Calculating the prefix sum  
//array of the modified array 
static void calcprefix(int arr[], int n) 
{ 
int s = 0; 
for (int i = 0; i < n; i++)  
{ 
s += arr[i]; 
arr[i] = s; 
} 
//System.out.println("...amittt... "+java.util.Arrays.toString(arr));
} 

//Function to find the length of the  
//longest subarray with average >= x 
static int longestsubarray(int arr[], 
               int n, int x) 
{ 
modifyarr(arr, n, x); 
calcprefix(arr, n); 

return maxIndexDiff(arr, n); 
} 

}
