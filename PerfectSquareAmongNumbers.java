/ Java program to find the largest perfect  
// square number among n numbers  
import java.lang.Math;  
import java.io.*;  
  
class GFG {  
  
  
// Function to check if a number  
// is perfect square number or not  
static boolean checkPerfectSquare(double n)  
{  
    // takes the sqrt of the number  
    double d = Math.sqrt(n);  
  
    // checks if it is a perfect  
    // square number  
    if (d * d == n)  
        return true;  
  
    return false;  
}  
  
// Function to find the largest perfect  
// square number in the array  
static int largestPerfectSquareNumber(int a[], double n)  
{  
    // stores the maximum of all  
    // perfect square numbers  
    int maxi = -1;  
  
    // Traverse all elements in the array  
    for (int i = 0; i < n; i++) {  
  
        // store the maximum if current  
        // element is a perfect square  
        if (checkPerfectSquare(a[i]))  
            maxi = Math.max(a[i], maxi);  
    }  
  
    return maxi;  
}  
