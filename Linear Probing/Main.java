package finalProject.hashing;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Main class for the project
 * Its function is to print the results and read the files for further hashing.
 */

public class Main{

// Array that represents the word of each line in each entry 
private static String[] lines;

// Filepath of the txt file 
private static String filePath = "C:\\Java\\words.txt";

// The total value of the word in the ascii of its letters 
private static int[] ascii;

// The ascii value of each letter seperately in each entry 
private static int[][] asciiletter;

/**
 * Read File method
 * On this method, we read the text file and add all lines to the lines array, creating a list
 * to dynamically change the size for each time a new line that isnt null is read. All further 
 * operations are done in other parts.
 */
private static void readFile(){
	try{
		List<String> lineslist = new ArrayList<>();
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		String word;

		while ((word = reader.readLine()) != null) {
                	lineslist.add(word);
            	}

		reader.close();

		lines = lineslist.toArray(new String[0]);
	} catch (IOException e){
		System.out.println("Error occured");
 	}
}

/**
 * Conversion from Strings to the total ascii of each letter from the word. A string will be passed
 * in a loop to turn first to a char array, after into ascii, then proceeded to be added to a new array.
 * @return an array with integers representing each word.
 */
private static int[] conversionstring(){
	int[] ascii = new int[lines.length];
	asciiletter = new int[lines.length][];
	int sum;
	for(int i = 0; i < lines.length; i++){

		//Turn string into char arrays
		char[] letters = lines[i].toCharArray();
		sum = 0;
		asciiletter[i] = new int[letters.length];

		//Loop where characters get turn into ascii
		for(int j = 0; j < letters.length; j++){
			int value = (int) letters[j];
			sum += value;
			asciiletter[i][j] = value;
		}
		ascii[i] = sum;
	}
	return ascii;
}

/**
 * Only converts a single word into its ascii form
 * mostly used for the search of a word
 */
private static int conversionSingleString(String s){
	int sum = 0;
	char[] letters = s.toCharArray();

	//Loop where characters get turn into ascii
	for(int j = 0; j < letters.length; j++){
		int value = (int) letters[j];
		sum += value;
	}
	return sum;
}



	 public static void writeFile(int[] results, String filename){
	 	try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){
	 		for(int i : results){
	 			writer.write(String.valueOf(i));
	 			writer.newLine();
	 		}
	 	} catch (IOException e) {
	 		System.out.println("Failure!");
	 	}
		System.out.println("Print complete!");
		
	 }
	 static int searchTable(int[] table, String word, int tsize) {
		int valueToSearch = conversionSingleString(word); // Convert the string to an integer representation
		int probe = valueToSearch % tsize;    // Compute the hash value (initial position)
		int initialPos = probe;              // Store the initial position for cycle detection
		boolean firstItr = true;             // Flag to detect the first iteration
		int probes = 0;                      // Count of probes for this search
	
		while (true) {
			probes++; // Increment the probe count for this word
	
			if (table[probe] == -1) {        // If the slot is empty, the element is not in the table
				return probes;              // Return total probes for this search
			} else if (table[probe] == valueToSearch) { // If the element is found
				return probes;              // Return total probes for this search
			} else if (probe == initialPos && !firstItr) { // If we've cycled back to the starting position
				return probes;              // Return total probes for this search
			}
	
			// Linear probing: move to the next slot
			probe = (probe + 1) % tsize;
			firstItr = false;
		}
	}
	
	static long searchTime(int[] table, String word, int tsize) {
		long startTime = System.nanoTime();       // Start time measurement
		searchTable(table, word, tsize);          // Perform the search (not needed to return here)
		long endTime = System.nanoTime();         // End time measurement
		return endTime - startTime;               // Return the total time in nanoseconds
	}
	
	static void multiSearch(int[] table, String[] words, int tsize) {
		System.out.println("Searching multiple words:");
		long totalSearchTime = 0;      // Total time for all searches
		int totalProbes = 0;           // Total probes for all searches
	
		for (String word : words) {
			long time = searchTime(table, word, tsize); // Measure search time
			int probes = searchTable(table, word, tsize); // Get the probes for this word
			boolean found = searchTable(table, word, tsize) == probes; // Reuse the probes call for result
			System.out.println("Word: \"" + word + "\", Found: " + found + ", Time: " + time + " nanoseconds, Probes: " + probes);
			totalSearchTime += time; // Accumulate the total time
			totalProbes += probes;   // Accumulate total probes
		}
	
		System.out.println("\nTotal Words Searched: " + words.length);
		System.out.println("Total Probes: " + totalProbes);
		System.out.println("Total Search Time: " + totalSearchTime + " nanoseconds");
	}
	
	
	public static void main(String args[]) throws IOException{
		readFile();
		int[] ascii = conversionstring();
		//double[] factors = {2, 10/7};
	
		Hashing fnp = new Hashing(933102);
		Hashing snp = new Hashing(666502);
		Hashing fp = new Hashing(933151);
		Hashing sp = new Hashing(666511);
	
		//Frequency Tables
		// System.out.println("Hashing Strategy: Linear Probing");
	// System.out.println("Table Size: Non prime number");
	// System.out.println("Load Factor: 0.5");
	// String filename = "5NONPRIME.txt";
	// int[] fivenonPrimeh = fnp.hashing(ascii, 466551);
	// writeFile(fivenonPrimeh, filename);
	//fnp.hashing(ascii, 466551);

	// // // System.out.println("Hashing Strategy: Linear Probing");
	// System.out.println("Table Size: Non prime number");
	// System.out.println("Load Factor: 0.7");
	// String file2 = "7NONPRIME.txt";
	// int[] sevnonPrimeh = snp.hashing(ascii, 466551);
	// writeFile(sevnonPrimeh, file2);
	//snp.hashing(ascii, 466551);

	// // // System.out.println("Hashing Strategy: Linear Probing");
	// System.out.println("Table Size: Prime number");
	// System.out.println("Load Factor: 0.5");
	// String file3 = "5PRIME.txt";
	// int[] fivePrimeh = fp.hashing(ascii, 466551);
	// writeFile(fivePrimeh, file3);
	//fp.hashing(ascii, 466551);

	// // // System.out.println("Hashing Strategy: Linear Probing");
	// // // System.out.println("Table Size: Prime number");
	// System.out.println("Load Factor: 0.7");
	// String file4 = "7PRIME.txt";
	// int[] sevPrimeh = sp.hashing(ascii, 466551);
	// writeFile(sevPrimeh, file4);
	//sp.hashing(ascii, 466551);

	// //Probes per insertion
	// System.out.println("Hashing Strategy: Linear Probing");
	// System.out.println("Table Size: Non prime number");
	// System.out.println("Load Factor: 0.5");
	// String file5 = "5NPPROBE.txt";
	// int[] fivenpProbe = fnp.probeCount(ascii, 466551, lines);
	// writeFile(fivenpProbe, file5);
	//fnp.probeCount(ascii, 466551, lines);
	
	// System.out.println("Hashing Strategy: Linear Probing");
	// System.out.println("Table Size: Non prime number");
	// System.out.println("Load Factor: 0.7");
	// snp.probeCount(ascii, 466551, lines);

	// String file6 = "7NPPROBE.txt";
	// int[] sevnpProbe = snp.probeCount(ascii, 466551, lines);
	// writeFile(sevnpProbe, file6);

	// System.out.println("Hashing Strategy: Linear Probing");
	// System.out.println("Table Size: Prime number");
	// System.out.println("Load Factor: 0.5");
	//fp.probeCount(ascii, 466551, lines);

	// String file7 = "5PROBE.txt";
	// int[] fiveProbe = fp.probeCount(ascii, 466551, lines);
	// writeFile(fiveProbe, file7);
	
	// System.out.println("Hashing Strategy: Linear Probing");
	// System.out.println("Table Size: Prime number");
	// System.out.println("Load Factor: 0.7");
    // sp.probeCount(ascii, 466551, lines);

	// String file8 = "7PROBE.txt";
	// int[] sevenProbe = sp.probeCount(ascii, 466551, lines);
	// writeFile(sevenProbe, file8);
	// //Search
	String[] search1 = {"bagel"};
	String[] search10 = {"bagel", "repurify", "10th", "3D", "ZZZ", "nonrated", "formable", "Batman", "Beach", "farfel"};
    String[] search20 = {"bagel", "repurify", "10th", "3D", "ZZZ", "nonrated", "formable", "Batman", "Beach", "farfel", "Farida", "Massachusetts", "specify", "kneehole", "harmonize", "five-year", "equipped", "equates", "climatometer", "chancels"};
    String[] search30 = {"bagel", "repurify", "10th", "3D", "ZZZ", "nonrated", "formable", "Batman", "Beach", "farfel", "Farida", "Massachusetts", "specify", "kneehole", "harmonize", "five-year", "equipped", "equates", "climatometer", "chancels", "7th", "Encrata", "encrusted", "Perley", "reoperates", "reoiled", "unprenticed", "unpreparedly", "unprolix", "zoogeographer"};
    String[] search40 = {"bagel", "repurify", "10th", "3D", "ZZZ", "nonrated", "formable", "Batman", "Beach", "farfel", "Farida", "Massachusetts", "specify", "kneehole", "harmonize", "five-year", "equipped", "equates", "climatometer", "chancels", "7th", "Encrata", "encrusted", "Perley", "reoperates", "reoiled", "unprenticed", "unpreparedly", "unprolix", "zoogeographer", "strath", "purposely", "mischief", "horologies", "doorsill", "convenience", "authority", "authored", "antialbumid", "11-point"};
    String[] search50 = {"bagel", "repurify", "10th", "3D", "ZZZ", "nonrated", "formable", "Batman", "Beach", "farfel", "Farida", "Massachusetts", "specify", "kneehole", "harmonize", "five-year", "equipped", "equates", "climatometer", "chancels", "7th", "Encrata", "encrusted", "Perley", "reoperates", "reoiled", "unprenticed", "unpreparedly", "unprolix", "zoogeographer", "strath", "purposely", "mischief", "horologies", "doorsill", "convenience", "authority", "authored", "antialbumid", "11-point", "Congo", "congratulated", "Oligocene", "scissors", "steradian", "swivel", "rill", "nondeferable", "forfeits", "Cyclades"};

	// System.out.println("Looking for the word: bagel");
	// int searchKey = conversionSingleString("bagel");
	// fnp.search(searchKey);
    // snp.search(searchKey);
	// fp.search(searchKey);
	// sp.search(searchKey);
	// System.out.println("Done!");
	int fnpn = 933102;
	int snpn = 666502;
	int fpn = 933151;
	int spn = 666511;
	int[] fivenonPrimeh = fnp.hashing(ascii, 466551);
	int[] sevnonPrimeh = snp.hashing(ascii, 466551);
	int[] fivePrimeh = fp.hashing(ascii, 466551);
	int[] sevPrimeh = sp.hashing(ascii, 466551);
	//System.out.println("\nSearching for 1, fivenonPrimeh:");
	//multiSearch(fivenonPrimeh, search1, fnpn);
	//System.out.println("\nSearching for 1, sevnonPrimeh:");
	//multiSearch(sevnonPrimeh, search1, snpn);
	//System.out.println("\nSearching for 1, fivePrimeh:");
	//multiSearch(fivePrimeh, search1, fpn);
	//System.out.println("\nSearching for 1, sevPrimeh:");
	//multiSearch(sevPrimeh, search1, spn);

	System.out.println("\nSearching for 10, fivenonPrimeh:");
    multiSearch(fivenonPrimeh, search10, fnpn);
	

	System.out.println("\nSearching for 10, sevnonPrimeh:");
    multiSearch(sevnonPrimeh, search10, snpn);
	
	System.out.println("\nSearching for 10, fivePrimeh:");
    multiSearch(fivePrimeh, search10, fpn);

	System.out.println("\nSearching for 10, sevPrimeh:");
    multiSearch(sevPrimeh, search10, spn);

	System.out.println("\nSearching for 20, fivenonPrimeh:");
    multiSearch(fivenonPrimeh, search20, fnpn);

	System.out.println("\nSearching for 20, sevnonPrimeh:");
    multiSearch(sevnonPrimeh, search20, snpn);
	
	System.out.println("\nSearching for 20, fivePrimeh:");
    multiSearch(fivePrimeh, search20, fpn);

	System.out.println("\nSearching for 20, sevPrimeh:");
    multiSearch(sevPrimeh, search20, spn);

	System.out.println("\nSearching for 30, fivenonPrimeh:");
    multiSearch(fivenonPrimeh, search30, fnpn);

	System.out.println("\nSearching for 30, sevnonPrimeh:");
    multiSearch(sevnonPrimeh, search30, snpn);
	
	System.out.println("\nSearching for 30, fivePrimeh:");
    multiSearch(fivePrimeh, search30, fpn);

	System.out.println("\nSearching for 30, sevPrimeh:");
    multiSearch(sevPrimeh, search30, spn);

	System.out.println("\nSearching for 40, fivenonPrimeh:");
    multiSearch(fivenonPrimeh, search40, fnpn);

	System.out.println("\nSearching for 40, sevnonPrimeh:");
    multiSearch(sevnonPrimeh, search40, snpn);
	
	System.out.println("\nSearching for 40, fivePrimeh:");
     multiSearch(fivePrimeh, search40, fpn);

	System.out.println("\nSearching for 40, sevPrimeh:");
    multiSearch(sevPrimeh, search40, spn);

	System.out.println("\nSearching for 50, fivenonPrimeh:");
    multiSearch(fivenonPrimeh, search50, fnpn);

	System.out.println("\nSearching for 50, sevnonPrimeh:");
    multiSearch(sevnonPrimeh, search50, snpn);
	
	System.out.println("\nSearching for 50, fivePrimeh:");
    multiSearch(fivePrimeh, search50, fpn);

	System.out.println("\nSearching for 50, sevPrimeh:");
    multiSearch(sevPrimeh, search50, spn);
	}
}