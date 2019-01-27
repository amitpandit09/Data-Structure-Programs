package pack;

import java.util.Scanner;

public class insertion {
	
	public static void main(String args[])
	{
		int n;
		System.out.println("Enter the size of array");
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		int arr[]=new int[n];
		int key=0;
		int i;
		for(int k=0;k<n;k++)
		{
			arr[k]=sc.nextInt();
		}
		
		for(int j=1;j<n;j++)
		{
			System.out.println("Sort Pass Number "+(j));
			key=arr[j];
			System.out.println("now key is="+key);
			i = j-1;
			
			while(i>-1 && arr[i] > key)
			{
				System.out.println("i"+i);
				arr[i+1] = arr[i];
				i--;
			}
			arr[i+1] = key;
			
			for(int p=0;p<n;p++)
			{
				System.out.println(arr[p]);
			}
		}
		
		
		sc.close();
	}

}
