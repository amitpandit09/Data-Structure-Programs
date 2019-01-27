package pack;

import java.util.Random;
import java.util.Scanner;

public class Problem1R {

	public static void main(String[] args) {
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
			
			sc.close();
		
		if(!checkArrayElementsCount(a))
		{
			System.out.println("Array contains less than 2 elements. Please provide more elements!!");
		}
		else {
						
						Randomquicksort(a,0,a.length-1);
						System.out.println(" The sorted form of the array is "+java.util.Arrays.toString(a));
						minGap(a);
						 
			}
	}
	
	public static void minGap(int a[])
	{
		int minimum1=Integer.MAX_VALUE;
		int minimum2 = Integer.MAX_VALUE;
		
		int minimum1Value1=0;
		int minimum1Value2=0;
		
		int minimum2Value1=0;
		int minimum2Value2=0;
		
		for(int i=0;i<a.length-1;i++)
		{
			int currentDifference = a[i+1] - a[i];
				if(currentDifference < minimum1) {
					
					minimum1 = currentDifference;
					minimum1Value1 = a[i];
					minimum1Value2 = a[i+1];
					
				}
				if(currentDifference > minimum1 && currentDifference < minimum2)
				{
					
					minimum2 = currentDifference;
					minimum2Value1 = a[i];
					minimum2Value2 = a[i+1];
					
				}
		}
		
		if(minimum2 ==Integer.MAX_VALUE ) {
			System.out.println("innn");
			minimum2= minimum1;
			minimum2Value1 = minimum1Value1;
			minimum2Value2 = minimum1Value2;
		}
		System.out.println("First Minimum – ["+minimum1Value1+","+minimum1Value2+"]"+minimum1);
		System.out.println("Second Minimum – ["+minimum2Value1+","+minimum2Value2+"]"+minimum2);
	}
	
	public static boolean checkArrayElementsCount(int a[])
	{
		if(a.length>0 && a.length<2)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public static void Randomquicksort(int a[],int left,int right)
	{
		Random rand=new Random();
		int pivot = left + rand.nextInt(right-left+1);
		
		int temp;
		temp=a[pivot];
		a[pivot]=a[right];
		a[right]=temp;
		
		pivot = a[right];
		
		int index = partition(a,left,right,pivot);
		
		if(left < index -1)
			Randomquicksort(a,left,index-1);
		
		
		if (index < right)
			Randomquicksort(a,index,right);
		
		
	}
	
	public static int partition(int a[],int left,int right,int pivot)
	{
		int i=left;		
		for(int j=left;j<right;j++)
		{
			if(a[j]<=pivot)
			{
				swap(a,i,j);
				i++;
			}
		}
		swap(a,i,right);
		return i;
	}
	
	public static void swap(int a[],int i,int j)
	{
		int temp;
		temp=a[i];
		a[i]=a[j];
		a[j]=temp;
	}
}
