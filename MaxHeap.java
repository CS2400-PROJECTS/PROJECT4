import java.util.Arrays;
/**
   A class that implements the ADT maxheap by using an array.
 
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 5.0
*/


public final class MaxHeap<T extends Comparable<? super T>>
             implements MaxHeapInterface<T>
{
   private T[] heap;      // Array of heap entries; ignore heap[0]
   private int lastIndex; // Index of last entry and number of entries
   private boolean integrityOK = false;
   

   private static final int DEFAULT_CAPACITY = 25;
   private static final int MAX_CAPACITY = 10000;
   private int countReheaps=0; // add counter for Reheaps
   
   
   
   public MaxHeap()
   {
      this(DEFAULT_CAPACITY); // Call next constructor
   } // end default constructor
   
   public MaxHeap(int initialCapacity)
   {
      // Is initialCapacity too small?
      if (initialCapacity < DEFAULT_CAPACITY)
         initialCapacity = DEFAULT_CAPACITY;
      else // Is initialCapacity too big?
         checkCapacity(initialCapacity);
      
      // The cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      T[] tempHeap = (T[])new Comparable[initialCapacity + 1];
      heap = tempHeap;
      lastIndex = 0;
      integrityOK = true;
   } // end constructor
   
   // Implementing smart way constructor 
   public MaxHeap(T[] entries)
   {
	   this(entries.length);
	   lastIndex=entries.length;
	   //assert integrityOK=true;
	   countReheaps=0;
	   
	   for (int index = 0; index < entries.length; index++)
		   heap [index+1] = entries[index];
	   
	   for (int rootIndex = lastIndex/2 ; rootIndex>1 ; --rootIndex)
		   reheap(rootIndex);
	   
   }
   
   public void add(T newEntry)
   {
   // See Segment 27.8.
	   
	  checkIntegrity();        // Ensure initialization of data fields
	  ensureCapacity();
	   int newIndex = lastIndex;
	   int parentIndex = newIndex / 2;
	   
	   while ( (parentIndex > 0) && newEntry.compareTo(heap[parentIndex]) > 0)
	   {
	      heap[newIndex] = heap[parentIndex];
	      newIndex = parentIndex;
	      parentIndex = newIndex / 2;
	      countReheaps++;
	   } // end while

	   heap[newIndex] = newEntry;
	   lastIndex++;
	   
	  
	 
   } // end add

   


   public T removeMax()
   {
   // See Segment 27.12.  
	   checkIntegrity();             // Ensure initialization of data fields
	   T root = null;
	   countReheaps=0;
	   if (!isEmpty())
	   {
	      root = heap[1];            // Return value
	      heap[1] = heap[lastIndex]; // Form a semiheap
	      lastIndex--;               // Decrease size
	      reheap(1);   
	      // Transform to a heap
	   } // end if

	   return root;
   } // end removeMax
   
   
   public void reheap(int rootIndex)
   {
	   boolean done = false;
	   T orphan = heap[rootIndex];
	   int leftChildIndex = 2 * rootIndex+1;

	   while (!done && (leftChildIndex <= lastIndex) )
	   {
	      int largerChildIndex = leftChildIndex; // Assume larger
	      int rightChildIndex = leftChildIndex + 1;

	      if ( (rightChildIndex <= lastIndex) &&
	            heap[rightChildIndex].compareTo(heap[largerChildIndex]) > 0)
	      {
	         largerChildIndex = rightChildIndex;
	      } // end if

	      if (orphan.compareTo(heap[largerChildIndex]) < 0)
	      {
	         heap[rootIndex] = heap[largerChildIndex];
	         rootIndex = largerChildIndex;
	         leftChildIndex = 2 * rootIndex;
	         countReheaps++;
	      }
	      else
	         done = true;
	   } // end while

	   heap[rootIndex] = orphan;
	   
   } // end reheap
  
   
   public T getMax()
   {
	  checkIntegrity();
      T root = null;
      if (!isEmpty())
         root = heap[1];
      return root;
   } // end getMax

   public boolean isEmpty()
   {
      return lastIndex < 1;
   } // end isEmpty

   public int getSize()
   {
      return lastIndex;
   } // end getSize

   public void clear()
   {
		checkIntegrity();
      while (lastIndex > -1)
      {
         heap[lastIndex] = null;
         lastIndex--;
      } // end while
      lastIndex = 0;
   } // end clear
   public int getCountOfReheaps(){
	   return countReheaps;
  
   }
   
   
   
// Private methods
// add check integrity and ensure capacity
   private void checkIntegrity()
   {
	   if (!integrityOK)
	   {
		   throw new IllegalStateException("The heap was not properly initialized.");
	   }
   }
   private void checkCapacity (int initialCapacity){
		  
		   {
			   if ( initialCapacity>MAX_CAPACITY )
				   throw new ArrayIndexOutOfBoundsException("The capacity is too large.");
		   }
	   }
   private void ensureCapacity() 
   {
	   if (lastIndex ==heap.length-1)
	   {
		   @SuppressWarnings("unchecked")
		T[] tempHeap=(T[]) new Comparable[heap.length*2];
		   for (int i =0; i < heap.length; i ++)
           {
               tempHeap[i] = heap[i];
           }
           heap = tempHeap;
	   }
   }

 
  
//Other methods for the testing
		public T[] toArray ()
	   {
		checkIntegrity();
		@SuppressWarnings("unchecked")
	   T[] result = (T[])new Integer[heap.length];
	   for (int index = 1; index <heap.length; index++)
	      {
		   result[index] = heap[index];
	      } 
	   return result;
	   }
	   
	   
	   public T[] getFirstTen (){
		   @SuppressWarnings("unchecked")
	   T []firstTen = (T[])new Integer[10];
	   T [] result = toArray();
	   for (int i = 0; i < 10 ; i++) 
	   {
	   firstTen[i] = result [i];
	   }
	   return firstTen;
	   }
	   public T[] getHeap (){
		   @SuppressWarnings("unchecked")
	   T []allHeap = (T[])new Integer[heap.length];
	   T [] result = toArray();
	   for (int i = 0; i < heap.length ; i++) 
	   {
		   allHeap[i] = result [i];
	   }
	   return allHeap;
	   }

	  T getData(int index)
	   {
		   return heap[index];
	   }
	
// . . .
} // end MaxHeap
