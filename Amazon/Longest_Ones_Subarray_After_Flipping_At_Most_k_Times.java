/*
Longest Ones Subarray After Flipping At Most k Times

Given an array of 0s and 1s, and k, Find the longest continuous streak 
of 1s after flipping k 0s to 1s. 

E.x array is {1,1,0,0,1,1,1,0,1,1} 
k = 1 (which means we can flip ‘k’ one 0 to 1) 

Answer: 
6 (if we flip 0 at index 7, we get the longest continuous streak of 1s having length 6)
*/

public static int longestSequence(int [] array, int k){
      //two pointers, time complexity O(n), space complexity O(1)
    	int maxLength = 0;
    	int start = 0;
    	int runningLength = 0;
    	int tmpK = k;
    	
    	for(int i = 0; i < array.length; i++){
    		if(array[i] == 1){
    			runningLength++;
    		}
    		else if(tmpK > 0 && tmpK < k){
    			tmpK--;
    			runningLength++;
    		}
    		else if(tmpK == k){
    			tmpK--;
    			runningLength++;
    			start = i;
    		}
    		else{ //tmpK == 0
    			tmpK = k;
    			maxLength = Math.max(maxLength, runningLength);
    			runningLength = 0;
    			int tmp = i;
    			i = start;
    			start = tmp;
    		}
    	}
    	
    	return Math.max(maxLength, runningLength);
}


public int[] findTheMaximumOneLengthAfterFlipping(int[] arr, int m) {
    int[] result = new int[]{-1, -1};
    if (arr == null || arr.length == 0) {
        return result;
    } 
    
    int left = 0;
    int right = 0; 
    int token = 0;
    int max = 0;
    while (right < arr.length) {
        if (arr[right] == 1) {
            right++;
        } else {
            //arr[right] == 0
            if (token < m) {
                right++;
                token++;
                
            } else if (token == m) {
                if (arr[left] == 0) {
                    token--;
                }
                left++;
            }
        }
        if (right - left + 1 > max) {
            max = right - left + 1;
            result[0] = left;
            result[1] = right;
        }
    }
    return reusult;
}
