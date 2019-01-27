package pack;

import java.util.Scanner;

public class mergesort {

	public static void main(String args[])
	{
		int n=0;
		System.out.println("Enter size of arrray");
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		
		int arr[]=new int[n];
		System.out.println("Enter the array elements");
		
		for(int i=0;i<n;i++)
		{
			arr[i]=sc.nextInt();
		}
		mergesort obj=new mergesort();
		obj.mergesorts(arr,0,n-1);
		
	}
	
	public void mergesorts(int arr[],int low,int high)
	{
		int mid = 0;
		if(low<high)
		{
		mid=(low+high)/2;
		mergesorts(arr,low,mid);
		mergesorts(arr,mid+1,high);
		}
		merge(arr,low,mid,high);
	}
	
	public void merge(int arr[],int low,int mid, int high)
	{
			int s1 = (mid-low)+1;
			int s2 = high-mid;
			int ts = s1+s2;
			int arr1[]=new int[s1];
			int arr2[]=new int[s2];
			int arr3[]=new int[ts];
			int i=0,j=0,k=0;
			
			
			while(i<s1)
			{
				arr1[i]=arr[k];
				k++;
				i++;
			}
			
			while(j<s1)
			{
				arr2[j]=arr[k];
				k++;
				j++;
			}
			
			while(i<arr1.length && j<arr2.length)
			{
				if(arr[i]>=arr[j]) {
					arr3[k]=arr[j];
					j++;
					
				}
				else {
					arr3[k]=arr[i];
					i++;
					
				}
				k++;
				
				while(i<arr1.length)
				{
					arr3[k]=arr1[i];
					i++;
					k++;
				}
				while(j<arr2.length)
				{
					arr3[k]=arr1[j];
					j++;
					k++;
				}
			}	
			for(int t=0;t<arr3.length;t++)
			{
				System.out.println(arr[t]);
			}	
	}
}
