package pack;

import java.util.Scanner;

/*
 * Write a program to count the number of prime numbers less than a given non negative number n
 * Input : 15
 * Output : 6
You are given 2 sorted arrays  A and B of sizes m and n respectively ,write a program to find the median of new
 sorted array formed by combining the given 2 sorted Arrays
 Input : [1,3] , [2,4]
 Output = (2+3)/2 = 2.5
 Input = [3] ,[1,2]
 Output: 2
 * 
 */
public class prime {

	public static void main(String args[])
	{
		int number;
		Scanner sc=new Scanner(System.in);
		number=sc.nextInt();
		sc.close();
		prime obj=new prime();
		int n=obj.checkPrim(number);
		System.out.println("result="+n);
	}
	public int checkPrim(int number)
	{
		int count=0;
		if(number < 2)
		{
			return 0;
		}
		else 
		{
			count++;
		}
		for(int i=3;i<number;i++)
		{
			for(int j=2;j<i;j++)
			{
				if(i%j==0)
				{
					break;
				}
				else if(j==i-1)
				{
					count++;
				}
			}
		}
		return count;
		
	}
}
