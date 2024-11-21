import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class QuadraticProbingProject {
    public static void main(String[] args) {
        // Table sizes for Load Factor 0.5
        int nonprimelf5 = 933102;
        int primelf5 = 933151;
        
        // Table sizes for Load Factor 0.7
        int nonprimelf7 = 666502;
        int primelf7 = 666511;
        
        // Reads the words into the hash table and searches for bagel
        String[] hashTable = readWords(primelf5);
        search(hashTable, "bagel", primelf5);

        String[] hashTable2 = readWords(nonprimelf5);
        search(hashTable2, "bagel", nonprimelf5);

        String[] hashTable3 = readWords(primelf7);
        search(hashTable3, "bagel", primelf7);

        String[] hashTable4 = readWords(nonprimelf7);
        search(hashTable4, "bagel", nonprimelf7);
    }



    private static int wordToAsciiSum(String word) {
        int asciiSum = 0;
        for (int i = 0; i < word.length(); i++) {
            // Get the ASCII value of each character and accumulate the sum
            asciiSum += (int) word.charAt(i);
        }
        return asciiSum;
    }



    // Method to convert words to their ascii value and 
    private static int hash(String word, int tableSize) {
        int asciiSum = wordToAsciiSum(word);
        return asciiSum % tableSize;
    }



    // Method to read the words from the file and hash them all into the table
    public static String[] readWords(int tableSize) {
        // Initializes the hashtable
        String[] hashTable = new String[tableSize];

        try (BufferedReader reader = new BufferedReader(new FileReader("words.txt"))) {
            String line;
            // Checks every line and inserts into the table
            while ((line = reader.readLine()) != null) {
                insert(hashTable, line, tableSize);
            }
        // Error catch
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        return hashTable;
    }



    private static void search(String[] hashTable, String word, int tableSize) {
        // Starts the timer
        long startTime = System.nanoTime();
        // Gets the index of the word to be searched for
        int index = hash(word, tableSize);
        int probes = 0;
        
        // Attempts to match the search word with its index in the table
        int i = 0;
        while (hashTable[index] != null) {
            if (hashTable[index].equals(word)) {
                // Stops the timer when the word is found and finds the time
                long endTime = System.nanoTime();
                long duration = endTime - startTime;
                System.out.println("Word '" + word + "' found in " + duration + " nanoseconds.");
                return;
            }
            // Performs quadratic probing if necessary
            i++;
            index = (index + i * i) % tableSize;
            probes++;
        }
        // Case for when the word is not found; stops the timer, finds the duration, and displays that the word was not found
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println("Word '" + word + "' not found; search took " + duration + " nanoseconds.");
        System.out.println("Probes used: " + probes);
    }



    // Method to insert into the hashtable
    private static void insert(String[] hashTable, String word, int tableSize) {
        int index = hash(word, tableSize);  // Initial hash value
        int i = 0;  // Quadratic probing starts with i = 0
        // int probes = 0;

        // Quadratic probing: check for collisions
        while (hashTable[index] != null) {
            i++;
            index = (index + i * i) % tableSize;
            // probes++;
        }
        // Indexes the word
        hashTable[index] = word;
    }
}