package pack;

import java.util.Scanner;

public class bubble {

	public static void main(String args[])
	{
		System.out.println("Enter the size of array");
		Scanner sc = new Scanner(System.in);
		int n=sc.nextInt();
		int arr[]=new int[n];
		int temp;
		int no=n;
		boolean swap = true;
		for(int i=0;i<n;i++)
		{
			arr[i]=sc.nextInt();
		}
		
		
		while(swap)
		{
			swap  = false;
		for(int i=0;i<n-1;i++)
		{
			if(arr[i]>arr[i+1])
			{
				temp=arr[i];
				arr[i]=arr[i+1];
				arr[i+1]=temp;
				swap = true;
			}
		
		}
		n--;
		}
		for(int i=0;i<no;i++)
		{
			System.out.println(arr[i]);
		}
		sc.close();
	}
}
