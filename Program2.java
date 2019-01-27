package pack;

import java.util.Scanner;

public class Program2 {

	public static void main(String args[])
	{
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
		int length=longestSubArray(a,x);
		System.out.println("length"+length);
		
	}
	
	public static int longestSubArray(int a[],int x)
	{
		
		 int length=0 ,temp =0 ; 
		    int max=0,sum=0,i; 
		    for(i=0;i<a.length;i++) 
		    {  
		        sum = sum+a[i]; 
		        temp++;
		        int val= Math.abs(sum/temp);
		        if(sum>max && val>=x && temp>=length) 
		        { 
		            max= sum ; 
		            length =temp; 
		        } 
		        if(sum<0) 
		        { 
		            sum=0; 
		            temp=0; 
		        } 
		          
		    } 
		  
		return length; 
		
	}
}
