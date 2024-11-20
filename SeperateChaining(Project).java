/*Seperate chaining: In hashing there is a hash function that maps keys to some values. But these hashing functions may lead to a collision that is two or more keys are mapped to same value. Chain hashing avoids collision. The idea is to make each cell of hash table point to a linked list of records that have same hash function value.*/
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

 
class SeperateChaining {
    
    private final int size; //size of hash table
    private final ArrayList<Integer>[] hashtable;// Hash table 
    private final String[] words;
    private final int[] values;
    private final int[] hashrepeats;
 
    public SeperateChaining(int size) //constructor to initialize size and hashtable
    {
        this.size = size;
        this.hashtable = new ArrayList[size];
        this.words = new String[size];
        this.values = new int[size];
        this.hashrepeats = new int[size];
        for (int i = 0; i < size; i++) {
            hashtable[i] = new ArrayList<>();
        }
    }

    public int AsciiVal(String key){

        int val = 0;
        int dif = 0;
        int plus = 0;


        char[] chars = key.toCharArray();
 
        for(char c : chars){

            int ascii = (int) c;
            val += ascii;

        }

        while(dif == 0){

            for (int x : hashtable[val%size]) {

                if(x == val){

                    plus++;
                    break;

                }

            }

            if(plus > 0){

                val += plus;
                plus = 0;

            }

            else{

                dif++;

            }

        }

            return val;

    }
 
    // hash function to map values to key
    public int hashFunction(String key)
    {

        int val = 0;
        int turn = 0;
        int dif = 0;
        int plus = 0;


        char[] chars = key.toCharArray();
 
        for(char c : chars){

            if(turn%2 == 0){

                int ascii = (int) c;
                val += ascii;

            }

            else{

                double ascii = (int) c;
                val *= ascii;

            }

        }

        while(dif == 0){

            for (int x : hashtable[val%size]) {

                if(x == val){

                    plus++;
                    break;

                }

            }

            if(plus > 0){

                val += plus;
                plus = 0;

            }

            else{

                dif++;

            }

        }

            return (val%size);

    }
 
    public void insertItem(String key) //insert key into hash table
    {

        System.out.println(key);
        int set = 0;
        while(words[set] != null){
            set++;

        }

        words[set] = key;
        values[set] = AsciiVal(key);
        int index = hashFunction(key); // find the hash index for key
        hashtable[index].add(AsciiVal(key)); // insert key into hash table at that index
        hashrepeats[index]++;

    }
 
    public void deleteItem(String key) //delete key from hash table
    {
        
        int pos = 0;
        while(!(words[pos].equals(key))){

            if(pos == size){
                pos++;
                break;
            }
            pos++;

        }

        if(pos>size){
            return;
        }

        int index = values[pos];// get the hash index of key
 
        // Check if key is in hash table
        if (!hashtable[index%size].contains(index)) {
            return;
        }
 
        // delete the key from hash table
        hashtable[index%size].remove(index);

    }
 
    // function to display hash table
    public void displayHash()
    {

        for (int i = 0; i < size; i++) {
            System.out.print(i);
            for (int x : hashtable[i]) {
                System.out.print(" --> " + x);
            }
            System.out.println();
        }

    }

    public void searchHash(String key){

        long startTime2 = System.nanoTime();
        int set = -1;
        int pos = 0;

        for(int i = 0; i < words.length; i++){

            if(words[i] == null){

                break;

            }
        
            if(words[i].equals(key)){

                set = i;

            }

        }

        if(set == -1){

            System.out.println("Sorry, that word doesn't exist");
            return;

        }

        for(int x : hashtable[values[set]%size]){

            if(!(values[set] == x)){

                pos++;

            }

            else{

                break;

            }

        }

        long endTime2 = System.nanoTime();
        System.out.println("Time in Milliseconds: " + ((endTime2 - startTime2) / 1000000));
        System.out.println(key + " was the " + pos + " word hashed to " + values[set]%size);

    }
 
    // Drive Program
    public static void main(String[] args) throws IOException
    {

        long startTime1 = System.nanoTime();
        
        //String[] arr = {"Hi", "Hello", "Howdy"};
        String arr[];
        arr = new String[466551];

        // Create a empty hash table of given size 
        SeperateChaining h = new SeperateChaining(933102);

        File file = new File("C:\\Users\\drago\\OneDrive\\Desktop\\DATA JAVA FILES\\Group Project\\words.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        try{

        String st; 
        int p = -1;

        while((st = br.readLine()) != null){

            p++;
            arr[p] = st;
            h.insertItem(st);
    
        }

    }

        finally {

            br.close();
            long endTime1 = System.nanoTime();
            System.out.println("Time in Milliseconds: " + ((endTime1 - startTime1) / 1000000));

        }
       
        h.searchHash("bagel");
        
    }
    
}
