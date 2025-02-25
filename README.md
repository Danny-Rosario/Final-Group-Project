# Final-Group-Project
About the project
This is a program that uses separate chaining to hash the ascii values of words and keep track of their values so that they can be searched for. For my project I took a list of around 500,000 words and hashed them to give each one a unique value.

Installation 
You need to install something that can execute java code in order for you to run this.

Usage
In order to get the best effect out of my code you will need to have a text file with each word being on its own separate line. The code will then read each line of code and give them a unique value and put them into the separate chain object you create. The “insertItem” method will be used to add each word into the chain and if you want any words gone then you can use the “deleteItem” to remove any word you want. Lastly, you can use the “searchHash” method to find any word in particular and where it has been hashed so you can keep track of them. “displayHash” can also do this, however, instead of it finding one word it will display every word.

Contributing
People are free to take my code and use it with their own groups of words to see the results for themselves, and I encourage them to share their own data to give examples of what this code outputs.

EXAMPLES
Search for 10 Words:

Time for the entire list to hash in Milliseconds: 2369663

Time in Milliseconds: 6
bagel was hashed to 32189
Probe Count: 0

Time in Milliseconds: 5
repurify was hashed to 336414
Probe Count: 0

Time in Milliseconds: 4
10th was hashed to 317
Probe Count: 0

Time in Milliseconds: 2
3D was hashed to 119
Probe Count: 0

Time in Milliseconds: 2
ZZZ was hashed to 86
Probe Count: 1

Time in Milliseconds: 2
nonrated was hashed to 261623
Probe Count: 0

Time in Milliseconds: 2
formable was hashed to 144975
Probe Count: 0

Time in Milliseconds: 1
Batman was hashed to 35751
Probe Count: 0

Time in Milliseconds: 1
Beach was hashed to 36187
Probe Count: 0

Time in Milliseconds: 1
farfel was hashed to 135361
Probe Count: 0

Searching for 20 Words:

Time for the entire list to hash in Milliseconds: 2552236

Time in Milliseconds: 7
bagel was hashed to 32189
Probe Count: 0

Time in Milliseconds: 2
repurify was hashed to 336414
Probe Count: 0

Time in Milliseconds: 6
10th was hashed to 317
Probe Count: 0

Time in Milliseconds: 4
3D was hashed to 119
Probe Count: 0

Time in Milliseconds: 2
ZZZ was hashed to 86
Probe Count: 1

Time in Milliseconds: 3
nonrated was hashed to 261623
Probe Count: 0

Time in Milliseconds: 2
formable was hashed to 144975
Probe Count: 0

Time in Milliseconds: 2
Batman was hashed to 35751
Probe Count: 0

Time in Milliseconds: 2
Beach was hashed to 36187
Probe Count: 0

Time in Milliseconds: 2
farfel was hashed to 135361
Probe Count: 0

Time in Milliseconds: 2
Farida was hashed to 135394
Probe Count: 0

Time in Milliseconds: 2
Massachusetts was hashed to 229951
Probe Count: 0

Time in Milliseconds: 2
specify was hashed to 377247
Probe Count: 0

Time in Milliseconds: 2
kneehole was hashed to 208337
Probe Count: 0

Time in Milliseconds: 2
harmonize was hashed to 167897
Probe Count: 0

Time in Milliseconds: 2
five-year was hashed to 140347
Probe Count: 0

Time in Milliseconds: 2
equipped was hashed to 127367
Probe Count: 0

Time in Milliseconds: 2
equates was hashed to 127192
Probe Count: 0

Time in Milliseconds: 2
climatometer was hashed to 74824
Probe Count: 0

Time in Milliseconds: 2
chancels was hashed to 65677
Probe Count: 0

Conclusion
In conclusion, my code is great for organization, and can be used for very large portions of data. I hope everyone who uses my code can get the intended use out of it.
