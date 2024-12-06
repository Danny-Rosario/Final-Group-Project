Hashing with Linear Probing in Java
Danny Rosario
Overview
This project demonstrates hashing using linear probing, a collision resolution technique. It includes methods for hashing, collision detection, probe counting, and searching elements within a hash table. The program is tailored to process text files, converting words into ASCII-based hash values.

Features
1. Hash Table Creation
The hash table is initialized with a specified size, and all slots are initially empty (-1).
Collisions are resolved using linear probing.
2. Collision Detection
Tracks the number of collisions per index when elements are inserted.
3. Probe Counting
Records the number of probes required for each element to find an empty slot during insertion.
4. Search Functionality
Searches for a specific word in the hash table.
Provides time statistics for the search operation.
5. ASCII Conversion
Converts words into their total ASCII values for hashing.
Handles multi-letter ASCII breakdown.
Project Structure
Classes
1. Hashing
Handles all operations related to the hash table.
Key Methods:
hashing(): Inserts elements and tracks collisions.
probeCount(): Counts probes for each element during insertion.
search(): Finds elements and reports search time.
Helper methods:
resettable(): Resets the table to its initial state.
printHashTable(): Displays the hash table.
printArray(): Displays collisions for each index.
printProbes(): Shows probe counts for each word.
2. Main
Reads input files and orchestrates operations.
Key Methods:
readFile(): Reads a text file and stores each line as a word.
conversionstring(): Converts each word to its total ASCII value.
conversionSingleString(): Converts a single word to its ASCII value for searching.
main(): Executes the hashing operations.
How to Use
1. Input File
Prepare a .txt file with one word per line.
Specify the file path in filePath inside the Main class.
2. Compilation and Execution
Input the desired word to search at the "conversionSingleString("bagel");" call/
3. Output
Collision Table: Number of collisions per hash index.
Probe Count Table: Number of probes for each word.
Search Results: Index and time taken to find specific words.
Configuration
Table Sizes
The program uses different table sizes (prime and non-prime) for comparison:
Load Factor 0.5: Table size is twice the number of elements.
Load Factor 0.7: Table size accommodates ~70% fill.
Words to Search
Modify the 	int searchKey = conversionSingleString("bagel"); call in the main() method to search for custom words.
Notes
Ensure the file path in filePath is correct and accessible.
Use a prime-sized table for better distribution.
Performance varies based on the input data and table size.