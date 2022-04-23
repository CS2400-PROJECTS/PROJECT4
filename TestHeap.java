import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class TestHeap {

public static void main(String[] args) throws FileNotFoundException  {

final int MAX_CAP = 101;


/**file input*/
File file = new File("data_sorted.txt");
Scanner reader = new Scanner(file);


//array to store the ints from the file.

Integer [] readEntry = new Integer[MAX_CAP];

//reading the data into the array
int i =0;
while (reader.hasNextInt()) {
	readEntry [i+1] = reader.nextInt();
i++;
}


/**Sequential Insertion*/
MaxHeap  <Integer> myHeap = new MaxHeap<Integer>();
//sequentially adding elements to the heap.
for (Integer p: readEntry) 
	{
	myHeap.add(p);
	}
myHeap.reheap(1);


try {
	    PrintWriter pFile=new PrintWriter("output2.txt");
	
	/** sequential insertions */
	pFile.write("Heap built using sequential insertions: " + Arrays.toString(myHeap.getFirstTen()) + "\n");
	pFile.write("Number of swaps in the heap creation: " +myHeap.getCountOfReheaps() + "\n");
	pFile.write("Heap after 10 removals: "  + "\n");
	
	pFile.write( Arrays.toString(myHeap.getHeap()) + "\n");
	
	
	//space
	pFile.write("...\n\n");
	
	
	/** Optimal Method --> smart way*/
	MaxHeap  <Integer> myHeap2 = new MaxHeap<Integer>(readEntry);
	
	/** optimal method */
	/** the smart way*/
	
	pFile.write("Heap built using optimal method: " + Arrays.toString(myHeap2.getFirstTen()) + "\n");
	pFile.write("Number of swaps in the heap creation: " + myHeap2.getCountOfReheaps() + "\n");
	pFile.write("Heap after 10 removals: " + "\n" );
	for (int i1 =0;i1<10;i1++)
		{
			myHeap2.removeMax();
		}
	pFile.write( Arrays.toString(myHeap2.getHeap()) + "\n");
	
	   pFile.close();
	}
catch(FileNotFoundException e)
	{
	        System.out.println("File not opened for writing");
	}
}
}