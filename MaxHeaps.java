
public class MaxHeaps {
	  private int[] theHeap;
	    private int capacity;
	    private int pos;
	    public MaxHeaps() {
	        
	        capacity = 10;
	        theHeap = new int[capacity];

	    }
	    public void insert(int value){
	        if(pos > capacity) {
	            //Increases the capacity
	        } else {
	            theHeap[pos++] = value;
	            
	            int child = pos - 1;
	            int parent = child/2;
	             while(theHeap[parent]< theHeap[child] && parent > 0) {
	            	 int tmp = theHeap[parent];
	            	 theHeap[parent] = theHeap[child];
	            	 theHeap[child]= tmp;
	            	 
	            	 child = parent;
	            	 parent = child/2;
	            	 
	            		
	             }
	        }

	    }
	    public void print() {
	    	

	        for (int i = 0; i < pos; i++) {
	        System.out.println("Heap built using sequential insertions:" + theHeap[i]);
	        
	    	}
	    }
     }


