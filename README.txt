LZW Compression/Decompression Implementation 03/12/2017
Programming language: Java 
Compiler version: javac 1.8.0_121	

Description:
------------
This project involves the implementation of the LZW Compression and Decompression algorithms. ArrayList data structure provided in java.util.ArrayList is used to implement the Ascii table and the table that holds final codes generated after encoding.

Compression design:
-> We first populate our ArrayList table to contain the list of all the standard Ascii characters.
-> We read the file passed as a part of the arguments during execution and store the contents of the file in a temporary variable in order to carry out the compression.
-> We loop over the entire content of the file that was obtained and implement the LZWCompression algorithm and generate the encoded Codes.
-> The generated codes are sent to the output and also written to a file and stored.
-> We generate the compressed .lzw file based on UTF-16BE charset, which will be used by the decompression algorithm in order to decode and obtain the initial input.

Decompression design:
-> We first populate our ArrayList table to contain the list of all the standard Ascii characters.
-> Read the contents from the .lzw file and extract contents based on the UTF-16BE charset. The contents are then stored in a temporary variable for decoding.
-> We loop over all the codes present in the input and implement the LZWDecompression algorithm to obtain the initial input/result.
-> The result obtained after decompression is displayed on the output terminal and written to a file.


How to Run the Program:
-----------------------

Compression/Encoding:

>> Compile the LZWCompression.java file present in the Compression -> src directory using javac.
>> Run the LZWCompression.class file by passing the input.txt file and any number in the range 9-16(bit length) as parameters.
>> An input.lzw file will be generated in the file path specified.
 
Decompression/Decoding:

>> Copy the above generated input.lzw file into the Decompression -> src directory.
>> Compile the LZWDecompression.java file present in Decompression -> src directory using javac. This generates the LZWDecompression.class file. 
>> Run the above generated LZWDecompression.class file by passing the input.lzw file and any number in the range 9-16(bit length) as parameters.

Limitations:
------------
The total number of unique character sequences generated must be less than or equal to 2^(bit_length) - 2^8. If this condition fails then the algorithm implementation breaks.

Contact Information:
--------------------

Name: Sharath Chandra BS
Email: sharath8saha@gmail.com
