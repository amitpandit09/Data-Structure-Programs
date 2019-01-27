/**
 * 
 */
package pack;

public class HeapSor 
{ 

    public static void main(String args[]) 
    { 
        int arr[] = {12, 11, 13, 5, 6}; 
        int n = arr.length; 
  
        HeapSor ob = new HeapSor(); 
        ob.sort(arr); 
  
        System.out.println("Sorted array is");  
    	System.out.println(" The sorted form of the array is " + java.util.Arrays.toString(arr));
    } 
    
    public void heap(int a[],int n,int i)
    {
    	int largest=i;
    	int l= i*2+1;
    	int r= i*2+2;
    	
    	if(l<n && a[l] > a[largest])
    		largest = l;
    	
    	if(r<n && a[r] > a[largest])
    		largest = r;
    	
    	if(largest!=i)
    	{
    		int tmp=a[largest];
    		a[largest]=a[i];
    		a[i]=tmp;
    		
    		heap(a,n,largest);
    	}
    	
    }
    public void sort(int a[])
    {
    	int n=a.length;
    	
    	for(int i=n/2-1;i>=0;i--)
    	{
    			heap(a,n,i);
    	}
    	
    	for(int j=n-1;j>=0;j--)
    	{
    		int tmp=a[0];
    		a[0]=a[j];
    		a[j]=tmp;
    		heap(a,j,0);
    	}
    }

} 
