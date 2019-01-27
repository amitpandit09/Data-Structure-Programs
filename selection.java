package pack;

import java.util.Scanner;

public class selection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//int n=0;
		
int n=0;
		
		System.out.println("Enter the size of array");
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		int arr[]=new int[n];
		System.out.println("Enter the array elements");
		for(int i=0;i<n;i++)
		{
			arr[i]=sc.nextInt();
		}
		sc.close();
		int tmp=0;
		int min=0;
		int comp=0;
		for(int i=0;i<arr.length;i++)
		{
			
			min=i;
				for(int j=i+1;j<arr.length;j++)
				{
					comp++;
					if(arr[j]<arr[min]) {
						min=j;
					}
				}
			tmp=arr[i];
			arr[i]=arr[min];
			arr[min]=tmp;
		}
		
		System.out.println("no of comp"+comp);
		for(int i=0;i<n;i++)
		{
			System.out.println(arr[i]);
		}
	}

}
