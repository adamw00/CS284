package anagrams;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * @author Adam Woo
 * I pledge my honor that I have abided by the Stevens Honor System.
 * CS 284 B 5/9/2021
 *
 */
public class Anagrams {
	final Integer[] primes =  
			{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 
			31, 37, 41, 43, 47, 53, 59, 61, 67, 
			71, 73, 79, 83, 89, 97, 101};
	Map<Character,Integer> letterTable;
	Map<Long,ArrayList<String>> anagramTable;

	public void buildLetterTable() {
		// Complete
		// initializes letterTable and adds the chars matched to the primes.
		// x+96 to convert 1-27 to ASCII a-z (lower-case)
		letterTable = new HashMap<Character,Integer>();
		for (int x = 1; x<27; x++) {
			letterTable.put((char) (x + 96), primes[x-1]);
		}
	}

	Anagrams() {
		buildLetterTable();
		anagramTable = new HashMap<Long,ArrayList<String>>();
	}

	public void addWord(String s) {
		// Complete
		// if does not have current hashCode, adds a new entry
		// if a matching key already contains s, throws exception
		// otherwise will replace entry with matching hashCode with an updated arrayList
		long add = myHashCode(s);
		if (null == anagramTable.get(add)) {
			ArrayList<String> a = new ArrayList<String>();
			a.add(s);
			anagramTable.put(add, a);
			return;
		}
		if (anagramTable.get(add).contains(s)) {
			throw new IllegalArgumentException("addWord: duplicate value");
		}
		else {
			ArrayList<String> temp = anagramTable.get(add);
			temp.add(s);
			anagramTable.put(add, temp);
		}
	}
	
	public long myHashCode(String s) {
		// Complete
		// multiplies the value of each key that matches each char in s  and returns the final product
		if (s == "") {
			throw new IllegalArgumentException("String is Empty");
		}
		long result = 1;
		for (int x=0; x<s.length(); x++) {
			result *= letterTable.get(s.charAt(x));
		}
		return result;

	}
	
	public void processFile(String s) throws IOException {
		FileInputStream fstream = new FileInputStream(s);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		while ((strLine = br.readLine()) != null)   {
		  this.addWord(strLine);
		}
		br.close();
	}
	
	public ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries() {
	    // Complete
		// goes through each key and sees if size of array is bigger than the biggest thus far.
		// if it is bigger it clears the final arrayList and adds the new longest.
		ArrayList<Map.Entry<Long,ArrayList<String>>> p = new ArrayList<Map.Entry<Long,ArrayList<String>>>();
		int max = 0;
		for (long key : anagramTable.keySet()) {
			if (anagramTable.get(key).size() > max) {
				p.clear();
				for (Map.Entry e : anagramTable.entrySet()) {
					if (e.getKey().equals(key)) {
						p.add(e);
					}
				}
				max = anagramTable.get(key).size();
			} else if (anagramTable.get(key).size() == max) {
				for (Map.Entry e : anagramTable.entrySet()) {
					if (e.getKey().equals(key)) {
						p.add(e);
					}
				}
			}
		}
		return p;
	}
	
	public static void main(String[] args) {
		Anagrams a = new Anagrams();

		final long startTime = System.nanoTime();
		
		try {
			a.processFile("words_alpha.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = a.getMaxEntries();
		final long estimatedTime = System.nanoTime() - startTime;
		final double seconds = ((double) estimatedTime/1000000000);
		System.out.println("Time: "+ seconds);
		System.out.println("List of max anagrams: "+ maxEntries);
	}
}
