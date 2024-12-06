package finalProject.hashing;

/**
 * Responsible for creating the table itself, and doing all operations of hashing. It receives the
 * values needed to hash to do its job.
 */
public class Hashing {

    /* The hash table */
    private int table[];

    /* Size of the table */
    private int tsize;

    public Object multiSearch;
    public int[] getTable(){
        return table;
    }

    /**
     * Constructor for the Hashing class, we initialize the table itself by receiving a size
     * and then create it.
     * @param hashsize the array's size to be used.
     */
    public Hashing(int hashsize) {
        table = new int[hashsize];
        for (int i = 0; i < hashsize; i++) {
            table[i] = -1;
        }
        tsize = hashsize;
    }

    private void resettable(){
	for (int i = 0; i < tsize; i++) {
            table[i] = -1;
        }
    }

    /**
     * Hashing using linear probing, counts the number of collisions per position.
     */
    public int[] hashing(int arr[], int N) {
	resettable();
        int[] collisionCounts = new int[tsize]; 

        for (int i = 0; i < N; i++) {
            // Computing the hash value using hash function
            int hv = arr[i] % tsize;

            // Insert the data in the table if there is no collision
            if (table[hv] == -1) {
                table[hv] = arr[i];
            } else {
                collisionCounts[hv]++; // Increment collision count for the initial collision
                // Handle collision using linear probing
                for (int j = 1; j < tsize; j++) {
                    // Computing the new hash value
                    int t = (hv + j) % tsize;
                    if (table[t] == -1) {
                        table[t] = arr[i];
                        break;
                    } else {
                        collisionCounts[t]++; // Increment collision count for subsequent probes
                    }
                }
            }
        }

        //System.out.println("Collisions per Position:");
        //printArray(collisionCounts);
        //printHashTable(table);
        return table;
    }

    /**
     * Hashing using linear probing, counts the number of probes per element (attempts to insert).
     */
    public void probeCount(int arr[], int N, String lines[]) {
        int[] probesPerElement = new int[N]; 
	resettable();

        for (int i = 0; i < N; i++) {
            // Computing the hash value using hash function
            int hv = arr[i] % tsize;

            // Attempt to insert the element, counting probes
            int probes = 1; // Initial probe
            if (table[hv] == -1) {
                table[hv] = arr[i];
            } else {
                // Handle collision using linear probing
                for (int j = 1; j < tsize; j++) {
                    probes++; // Increment probe count for each attempt
                    int t = (hv + j) % tsize;
                    if (table[t] == -1) {
                        table[t] = arr[i];
                        break;
                    }
                }
            }
            probesPerElement[i] = probes; // Store the number of probes for this element
        }

        
        printProbes(probesPerElement);
        // change to return table, remove void, int[] 
        //return table;

    }

     /**
     * Perform a search in the hash table using linear probing.
     * 
     * @param key the element to search for
     * @return the index of the element if found, -1 if not found
     */
    public int search(int key) {
        // Start time measurement
        long startTime = System.nanoTime();

        // Compute the hash value
        int hv = key % tsize;

        // Check the initial computed index
        if (table[hv] == key) {
            // End time measurement
            long endTime = System.nanoTime();
            long searchTime = endTime - startTime;
            System.out.println("Element found at index " + hv + " in " + searchTime + " nanoseconds.");
            return hv; // Element found at the hashed index
        } else {
            // Handle collision using linear probing
            for (int j = 1; j < tsize; j++) {
                int t = (hv + j) % tsize;
                if (table[t] == key) {
                    // End time measurement
                    long endTime = System.nanoTime();
                    long searchTime = endTime - startTime;
                    System.out.println("Element found at index " + t + " in " + searchTime + " nanoseconds.");
                    return t; // Element found at this position
                }
                // If we encounter an empty slot, stop searching
                if (table[t] == -1) {
                    break;
                }
            }
        }

        // Element not found after probing the table
        long endTime = System.nanoTime();
        long searchTime = endTime - startTime;
        System.out.println("Element not found in " + searchTime + " nanoseconds.");
        return -1; // Element not found
    }

   
    
    


    static void printHashTable(int arr[]) {
        System.out.println("Index  " + "Data");
        for (int i = 0; i < 500; i++) {
            System.out.println("       " + arr[i]);
        }
    }

    static void printArray(int arr[]) {
        System.out.println("Index  " + "Collisions");
        for (int i = 0; i < 500; i++) {
            System.out.println("       " + arr[i]);
        }
    }

    static void printProbes(int probes[]) {
        System.out.println("Word  " + "Probe Counts");
        for (int i = 0; i < 500; i++) {
            System.out.println( probes[i]);
        }
    }


    
}