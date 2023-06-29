import java.util.Arrays;
import java.util.Scanner;

// Use the iterative method to implement
public class LinearSearchAlgorithm {
	// searchArray = [23, 12, 11, 34, 45, 65, 33, 10, 11, 19]
	
    public static int firstLinearSearch(int[] searchArray, int key) {
    	
        for (int k = 0; k < searchArray.length; k++) { // Loops through the whole array of length.
        	
            if (searchArray[k] == key) {  // Checks when the element at index = key
            	
                return k; // Returns the index if the condition is met.
            }
        }
        return -1;
    }

    
    
    public static int interpolationSearchAlgorithm(int[] searchArray, int key) {
    	
    	// Index		  0    1   2  3    4   5   6   7  8   9
    	
    	// searchArray = [23, 12, 11, 34, 45, 65, 33, 10, 11, 19]
    	
        int lowerBound = 0;
        
        int higherBound = searchArray.length - 1;

        while (lowerBound <= higherBound && key >= searchArray[lowerBound] && key <= searchArray[higherBound]) {
        	
        	if(lowerBound == higherBound) {
        		
        		if(searchArray[lowerBound] == key) {
        			
        			return lowerBound;
        		}
        		return -1;
        	}
        	
        	// Determine the position as compared to midpoint in binary search
        	
            int pos = lowerBound + ((key - searchArray[lowerBound]) * (higherBound - lowerBound)) / (searchArray[higherBound] - searchArray[lowerBound]); 
            
            if (searchArray[pos] == key) {
            	
                return pos;
            }

             if (searchArray[pos] < key) {
            	
            	lowerBound = pos + 1;
                
            } else {
            	
            	higherBound = pos - 1;
            }
        }

        return -1;
    }
   /*
    * This is improved because it iterated through the array and each elements, it checks if it is equal to he key being searched.
    */
    
    public static int optimizedLinearSearch(int[] searchArray, int key) {
    	
        for (int k = 0; k < searchArray.length; k++) { 
        	
            if (searchArray[k] == key) { //Direct comparison of the index and the target key.
            	
                if (k != 0) {
                	
                    int tempValue = searchArray[k];
                    
                    searchArray[k] = searchArray[k - 1];
                    
                    searchArray[k - 1] = tempValue;
                    
                }
                return k;
            }
        }
        return -1;
    }

    //Main entry of the program 
    public static void main(String[] args) {    	
    	
        Scanner inputValue = new Scanner(System.in);
        
        System.out.print("Enter the number of elements in the array: ");
        
        int numElements = inputValue.nextInt();
        
        int[] arr = new int[numElements];

        System.out.println("Enter the elements in the array:");
        
        for (int i = 0; i < numElements; i++) {
        	
            arr[i] = inputValue.nextInt();
        }

        System.out.print("Enter the search key: ");
        
        int key = inputValue.nextInt();
        
        
        long firstTime = System.nanoTime(); // Set the start time before it enters the function
        
        // Linear Search
        int linearIndex = firstLinearSearch(arr, key);
        
        long finishTime = System.nanoTime(); // Get the finish time after the function is run
        
        long linearTime = finishTime - firstTime;
        
        
        if (linearIndex != -1) {
        	
            System.out.println("Using Linear Search:");
            
            System.out.println("Search key FOUND at index " + linearIndex);
            
        } else {
        	
            System.out.println("Using Linear Search:");
            
            System.out.println("Search key NOT FOUND");
        }

        // Interpolation Search
         firstTime = System.nanoTime();
         
         Arrays.sort(arr);
            
        int interpolationIndex = interpolationSearchAlgorithm(arr, key);
        
        finishTime = System.nanoTime();
        
        long interpolationTime = finishTime - firstTime;
        
        if (interpolationIndex != -1) {
        	
            System.out.println("Using Interpolation Search:");
            
            System.out.println("Search key FOUND at index " + interpolationIndex);
            
        } else {
        	
            System.out.println("Using Interpolation Search:");
            
            System.out.println("Search key NOT FOUND");
        }

        
        firstTime = System.nanoTime();
        
        int improvedLinearIndex = optimizedLinearSearch(arr, key);
        
        finishTime = System.nanoTime();
        
        long improvedLinearTime = finishTime - firstTime;
        
        if (improvedLinearIndex != -1) {
        	
            System.out.println("Using Improved Linear Search:");
            
            System.out.println("Search key FOUND at index " + improvedLinearIndex); 
            
        } else {
        	
            System.out.println("Using Improved Linear Search:");
            
            
            System.out.println("Search key NOT FOUND");
        }
        /*
         * 1. When the arrays are not sorted, the interpolation search performs better as it makes intelligent guesses about the location of the key
         * 2. When arrays were sorted the it becomes slower as a binary search will have been more efficient for sorted arrays.
         * However, since the elements are not uniformly distributed, the linear search tends to perform as faster time.
         * The run time for interpolation search is O(log(log(n))) for average case and O(n) for worst case
         */
        System.out.println("The Linear Time is: " +  linearTime + "ns");
        
        System.out.println("The Interpolation time is: " + interpolationTime + "ns");
        
        System.out.println("The improved Linear Time is: " + improvedLinearTime + "ns");
        
       double time = (linearTime - improvedLinearTime) / linearTime;
        
        System.out.println("The percentage of Improvements " + time);
        
    }
}
