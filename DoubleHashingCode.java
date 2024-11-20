import java.util.*;
import java.io.*;

public class ProjectPart1 {


public static String[] ScanDocument(){
    try{
        String[] array = new String[466551];
             File aList = new File("words.txt");
        Scanner aScanner = new Scanner(aList);
        int wordCount = 0;

    while (aScanner.hasNextLine()){
        String data = aScanner.nextLine();
        if (wordCount < 466551)
        {
            array[wordCount] = data;

        }
        wordCount++;
    }

    aScanner.close();
    
    return array;
    } catch(FileNotFoundException error) {
    System.out.println("file not found");
    
    return null;
    }
    }

    public static int stringToInt(String input){
        char temp = 'a';
        String str = input;
        int currentInt = 0;
        int total = 0;
        for(int i = 0; i < str.length(); i++){
        temp = str.charAt(i);
        currentInt = Character.getNumericValue(temp);
        total = currentInt + total;
        }
        return total;
        }


        static int[] counting(int table[], int tsize, int arr[],int N, int p)
    {
         int[] arrCount = new int[tsize];
        for (int i = 0; i < N; i++) {
 
            // Computing the hash value using hash function
            int hv = arr[i] % tsize;

            arrCount[hv] = arrCount[hv] + 1;
 
            // Insert the data in the table if there is no collision
            if (table[hv] == -1)
                table[hv] = arr[i];
            else {
                 for (int j = 0; j < tsize; j++) {
                     int y = (arr[i] + j*(p - arr[i] % p)) % tsize; 
                      if (table[y] == -1) {
                       // Break the loop after inserting the data in the table
                           table[y] = arr[i];
                           break;
                          
                    }
                }
            }
        }
        return arrCount;
    }


    static int[] countProbe(int table[], int tsize, int arr[],int N, int p)
    {
         int[] arrCount = new int[tsize];
        for (int i = 0; i < N; i++) {
 
            // Computing the hash value using hash function
            int hv = arr[i] % tsize;

            
 
            // Insert the data in the table if there is no collision
            if (table[hv] == -1)
                table[hv] = arr[i];
            else {
                 for (int j = 0; j < tsize; j++) {
                    arrCount[i] = arrCount[i] + 1;
                     int y = (arr[i] + j*(p - arr[i] % p)) % tsize; 
                      if (table[y] == -1) {
                       // Break the loop after inserting the data in the table
                           table[y] = arr[i];
                           break;
                          
                    }
                }
            }
        }
        return arrCount;
    }

    static int[] hashing(int table[], int tsize, int arr[],int N, int p)
    {
        long startTime = System.nanoTime();
        for (int i = 0; i < N; i++) {
            
            // Computing the hash value using hash function
            int hv = arr[i] % tsize;

 
            // Insert the data in the table if there is no collision
            if (table[hv] == -1)
                table[hv] = arr[i];
            else {
                 for (int j = 0; j < tsize; j++) {
                     int y = (arr[i] + j*(p - arr[i] % p)) % tsize; 
                      if (table[y] == -1) {
                       // Break the loop after inserting the data in the table
                           table[y] = arr[i];
                           break;
                          
                    }
                }
            }
        }
        long endTime = System.nanoTime();
        return table;
        
    }

    static int[] createTable(int tsize){
        int[] hash_table = new int[tsize];
        for(int i=0; i < tsize; i++){
            hash_table[i] = -1;
        }
        return hash_table;
    }

    static boolean searchTable(int[] table, String word, int tsize, int N, int P){

        int valueToSearch = stringToInt(word);

        int probe = valueToSearch % tsize;
        int offset = P - (valueToSearch % P); 
        int initialPos = probe;
        boolean firstItr = true;
        
        while(true){
            if(table[probe] == -1)
                break;
            else if (table[probe] == valueToSearch)
                return true;
            else if (probe == initialPos && !firstItr)
                return false;
            else probe = ((probe + offset) % tsize);
            firstItr = false;

        }
        return false;
        

    }

    static long searchTime(int[] table, String word, int tsize, int N, int P){
        long startTime = System.nanoTime();
        searchTable(table, word, tsize, N, P);
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        return totalTime;
    
    }

public static void main(String args[]){
    String[] wordArray = ScanDocument();

    
    int[] numArray = new int[466551];
    int N = 466551;
    int P = 7;

    int fiveSize = 933102;
    int fiveSizePrime = 933151;
    int sevenSize = 666502;
    int sevenSizePrime = 666511;




    for(int i = 0; i<466551; i++){
        numArray[i] = stringToInt(wordArray[i]);
    }
  
    int[] arrFive = createTable(fiveSize);
    int[] arrFiveP = createTable(fiveSizePrime);
    int[] arrSeven = createTable(sevenSize);
    int[] arrSevenP = createTable(sevenSizePrime);


    arrFive = hashing(arrFive, fiveSize, numArray, N, P);
    arrFiveP = hashing(arrFiveP, fiveSizePrime, numArray, N, P);
    arrSeven = hashing(arrSeven, sevenSize, numArray, N, P);
    arrSevenP = hashing(arrSevenP, sevenSizePrime, numArray, N, P);


    //int[] countFive = counting(arrFive, fiveSize, numArray, N, P);
    //int[] countFiveP = counting(arrFiveP, fiveSizePrime, numArray, N, P);
    //int[] countSeven = counting(arrSeven, sevenSize, numArray, N, P);
    //int[] countSevenP = counting(arrSevenP, sevenSizePrime, numArray, N, P);

    //int[] countProbeFive = countProbe(arrFive, fiveSize, numArray, N, P);
    //int[] countProbeFiveP = countProbe(arrFiveP, fiveSizePrime, numArray, N, P);
    //int[] countProbeSeven = countProbe(arrSeven, sevenSize, numArray, N, P);
    //int[] countProbeSevenP = countProbe(arrSevenP, sevenSizePrime, numArray, N, P);

    

    String[] arrTen = {"bagel", "repurify", "10th", "3D", "ZZZ", "nonrated", "formable", "Batman", "Beach", "farfel"};
    String[] arrTwenty = {"bagel", "repurify", "10th", "3D", "ZZZ", "nonrated", "formable", "Batman", "Beach", "farfel", "Farida", "Massachusetts", "specify", "kneehole", "harmonize", "five-year", "equipped", "equates", "climatometer", "chancels"};
    String[] arrThirty = {"bagel", "repurify", "10th", "3D", "ZZZ", "nonrated", "formable", "Batman", "Beach", "farfel", "Farida", "Massachusetts", "specify", "kneehole", "harmonize", "five-year", "equipped", "equates", "climatometer", "chancels", "7th", "Encrata", "encrusted", "Perley", "reoperates", "reoiled", "unprenticed", "unpreparedly", "unprolix", "zoogeographer"};
    String[] arrForty = {"bagel", "repurify", "10th", "3D", "ZZZ", "nonrated", "formable", "Batman", "Beach", "farfel", "Farida", "Massachusetts", "specify", "kneehole", "harmonize", "five-year", "equipped", "equates", "climatometer", "chancels", "7th", "Encrata", "encrusted", "Perley", "reoperates", "reoiled", "unprenticed", "unpreparedly", "unprolix", "zoogeographer", "strath", "purposely", "mischief", "horologies", "doorsill", "convenience", "authority", "authored", "antialbumid", "11-point"};
    String[] arrFifty = {"bagel", "repurify", "10th", "3D", "ZZZ", "nonrated", "formable", "Batman", "Beach", "farfel", "Farida", "Massachusetts", "specify", "kneehole", "harmonize", "five-year", "equipped", "equates", "climatometer", "chancels", "7th", "Encrata", "encrusted", "Perley", "reoperates", "reoiled", "unprenticed", "unpreparedly", "unprolix", "zoogeographer", "strath", "purposely", "mischief", "horologies", "doorsill", "convenience", "authority", "authored", "antialbumid", "11-point", "Congo", "congratulated", "Oligocene", "scissors", "steradian", "swivel", "rill", "nondeferable", "forfeits", "Cyclades"};
    
    //0.5 composite

    long timeTaken5 = searchTime(arrFive, "bagel", fiveSize, N, P);
    System.out.println("Time taken for load factor 0.5, composite number, 'bagel':" + timeTaken5);

    long totalTime105 = 0;
    for(int i = 0; i<10; i++){
       totalTime105 = totalTime105 + searchTime(arrFive, arrTen[i], fiveSize, N, P); }
    System.out.println("Time taken for load factor 0.5, composite number, 19 words:" + totalTime105);


    long totalTime205 = 0;
    for(int i = 0; i<20; i++){
       totalTime205 = totalTime205 + searchTime(arrFive, arrTwenty[i], fiveSize, N, P);}
    System.out.println("Time taken for load factor 0.5, composite number, 20 words:" + totalTime205);


    long totalTime305 = 0;
    for(int i = 0; i<30; i++){
       totalTime305 = totalTime305 + searchTime(arrFive, arrThirty[i], fiveSize, N, P);
    }
    System.out.println("Time taken for load factor 0.5, composite number, 30 words:" + totalTime305);


    long totalTime405 = 0;
    for(int i = 0; i<40; i++){
       totalTime405 = totalTime405 + searchTime(arrFive, arrForty[i], fiveSize, N, P);
    }
    System.out.println("Time taken for load factor 0.5, composite number, 40 words:" + totalTime405);


    long totalTime505 = 0;
    for(int i = 0; i<50; i++){
       totalTime505 = totalTime505 + searchTime(arrFive, arrFifty[i], fiveSize, N, P);
    }
    System.out.println("Time taken for load factor 0.5, prime number, 50 words:" + totalTime505);


    // 0.5 prime

    long timeTaken5p = searchTime(arrFiveP, "bagel", fiveSizePrime, N, P);
    System.out.println("Time taken for load factor 0.5, prime number, 'bagel':" + timeTaken5p);

    long totalTime10P5 = 0;
    for(int i = 0; i<10; i++){
       totalTime10P5 = totalTime10P5 + searchTime(arrFiveP, arrTen[i], fiveSizePrime, N, P); }
    System.out.println("Time taken for load factor 0.5, prime number, 19 words:" + totalTime10P5);


    long totalTime20P5 = 0;
    for(int i = 0; i<20; i++){
       totalTime20P5 = totalTime20P5 + searchTime(arrFiveP, arrTwenty[i], fiveSizePrime, N, P);}
    System.out.println("Time taken for load factor 0.5, prime number, 20 words:" + totalTime20P5);


    long totalTime30P5 = 0;
    for(int i = 0; i<30; i++){
       totalTime30P5 = totalTime30P5 + searchTime(arrFiveP, arrThirty[i], fiveSizePrime, N, P);
    }
    System.out.println("Time taken for load factor 0.5, prime number, 30 words:" + totalTime30P5);


    long totalTime40P5 = 0;
    for(int i = 0; i<40; i++){
       totalTime40P5 = totalTime40P5 + searchTime(arrFiveP, arrForty[i], fiveSizePrime, N, P);
    }
    System.out.println("Time taken for load factor 0.5, prime number, 40 words:" + totalTime40P5);


    long totalTime50P5 = 0;
    for(int i = 0; i<50; i++){
       totalTime50P5 = totalTime50P5 + searchTime(arrFiveP, arrFifty[i], fiveSizePrime, N, P);
    }
    System.out.println("Time taken for load factor 0.5, prime number, 50 words:" + totalTime50P5);


    //0.7 composite

    long timeTaken7 = searchTime(arrSeven, "bagel", sevenSize, N, P);
    System.out.println("Time taken for load factor 0.7, composite number, 'bagel':" + timeTaken7);

    long totalTime107 = 0;
    for(int i = 0; i<10; i++){
       totalTime107 = totalTime107 + searchTime(arrSeven, arrTen[i], sevenSize, N, P); }
    System.out.println("Time taken for load factor 0.7, composite number, 19 words:" + totalTime107);


    long totalTime207 = 0;
    for(int i = 0; i<20; i++){
       totalTime207 = totalTime207 + searchTime(arrSeven, arrTwenty[i], sevenSize, N, P);}
    System.out.println("Time taken for load factor 0.7, composite number, 20 words:" + totalTime207);


    long totalTime307 = 0;
    for(int i = 0; i<30; i++){
       totalTime307 = totalTime307 + searchTime(arrSeven, arrThirty[i], sevenSize, N, P);
    }
    System.out.println("Time taken for load factor 0.7, composite number, 30 words:" + totalTime307);


    long totalTime407 = 0;
    for(int i = 0; i<40; i++){
       totalTime407 = totalTime407 + searchTime(arrSeven, arrForty[i], sevenSize, N, P);
    }
    System.out.println("Time taken for load factor 0.7, composite number, 40 words:" + totalTime407);

    long totalTime507 = 0;
    for(int i = 0; i<50; i++){
       totalTime507 = totalTime507 + searchTime(arrSeven, arrFifty[i], sevenSize, N, P);
    }
    System.out.println("Time taken for load factor 0.7, composite number, 50 words:" + totalTime507);


    //0.7 prime

    long timeTakenP7 = searchTime(arrSevenP, "bagel", sevenSizePrime, N, P);
    System.out.println("Time taken for load factor 0.7, prime number, 'bagel':" + timeTakenP7);

    long totalTime10P7 = 0;
    for(int i = 0; i<10; i++){
       totalTime10P7 = totalTime10P7 + searchTime(arrSevenP, arrTen[i], sevenSizePrime, N, P); }
    System.out.println("Time taken for load factor 0.7, prime number, 19 words:" + totalTime10P7);


    long totalTime20P7 = 0;
    for(int i = 0; i<20; i++){
       totalTime20P7 = totalTime20P7 + searchTime(arrSevenP, arrTwenty[i], sevenSizePrime, N, P);}
    System.out.println("Time taken for load factor 0.7, prime number, 20 words:" + totalTime20P7);


    long totalTime30P7 = 0;
    for(int i = 0; i<30; i++){
       totalTime30P7 = totalTime30P7 + searchTime(arrSevenP, arrThirty[i], sevenSizePrime, N, P);
    }
    System.out.println("Time taken for load factor 0.7, prime number, 30 words:" + totalTime30P7);


    long totalTime40P7 = 0;
    for(int i = 0; i<40; i++){
       totalTime40P7 = totalTime40P7 + searchTime(arrSevenP, arrForty[i], sevenSizePrime, N, P);
    }
    System.out.println("Time taken for load factor 0.7, prime number, 40 words:" + totalTime40P7);

    long totalTime50P7 = 0;
    for(int i = 0; i<50; i++){
       totalTime50P7 = totalTime50P7 + searchTime(arrSevenP, arrFifty[i], sevenSizePrime, N, P);
    }
    System.out.println("Time taken for load factor 0.7, prime number, 50 words:" + totalTime50P7);
    



}
}