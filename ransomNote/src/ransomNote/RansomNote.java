package ransomNote;

/**
 * The RansomNote class will contain the hash table (String array) and the logic to search if the note can be written with the magazine alphabetic characters (an alphabetic character cannot be used more than once) 
 * @author juandavid2025
 */
public class RansomNote { 
	
	/**
	 * hastTable (list) that will contain each splitted fragment of the magazine
	 */
	private String [] hashTable;
	
	/**
	 * List that will contain, in the same position as it's character in hashTable, the amount of times we can use that specific fragment
	 */
	private Integer [] alphabeticRepeatHelper;
	
	/**
	 * Main method to be call to verify if a note can be done with magazine words, this method will organize the execution order of the others methods
	 * @param magazine - magazine text
	 * @param note - note text
	 */
	public boolean isNoteInMagazine(String magazine, String note) {
		
		placingMagazineHashTable(magazine);
		
		//printHashTable();
		
		return verifyNoteInHashTable(note);
	}
	
	/**
	 * This method will organize the string magazine into the hashTable (initializing the class attributes in the process). To complete this it will be calling other methods: hashValue and linearHashing
	 * @param magazine - magazine text
	 */
	public void placingMagazineHashTable(String magazine) {
		
		String [] magazineSplitted = magazine.split(" ");
	
		hashTable = new String[magazineSplitted.length*2];
		alphabeticRepeatHelper = new Integer[magazineSplitted.length*2];
		
		int placement;
		
		// place all magazine characters in hash table
		for(int i =0;i<magazineSplitted.length;i++) {
			
			placement=hashValue(magazineSplitted[i]);
			
			// if the placement generated in the hashTable is empty
			if(hashTable[placement]==null) {
				hashTable[placement] = magazineSplitted[i];
				alphabeticRepeatHelper[placement] = 1;
			}
			
			// if the placement in the hashTable is not empty it means we have a collision
			else {
				linealHashing(placement, magazineSplitted[i]);
			}
			
		}	
	}
	
	/**
	 * This method is the one that will contrast the note with the magazine in the hashTable, so this method should be called after the process of placingMagazineHashTable
	 * @param note - note text
	 * @return boolean isNote that will be true if the note can be written with the words in magazine
	 */
	public boolean verifyNoteInHashTable(String note){
		boolean isNote = true;
		String[] noteSplitted = note.split(" ");
		
		for(int i=0;i<noteSplitted.length && isNote;i++) {
			
			if(!searchLinearForm(noteSplitted[i])) {
				isNote=false;
			}
			
		}
		
		return isNote;
	}
	
	/**
	 * Method that will return the hashValue of a String calling a method to get the sum of all characters of the String to later apply % with the length of the hashTable to ensure that the value is not out of bounds with the table
	 * @param toHash - String which we want to get the hashValue
	 * @return hash - hash final result of the addition
	 */
	public int hashValue(String toHash) {
		int hash=0;
		
		hash = (StringToCharSum(toHash))%(hashTable.length);
		
		return hash;
	}
	
	/**
	 * Method that will return the addition of chart characters of the param String
	 * @param text - text
	 * @return final value of the sum
	 */
	public int StringToCharSum(String text) {
		int total=0;
		
		for(int i=0;i<text.length();i++) {
			total += text.charAt(i);
		}
		
		return total;
	}
	
	/**
	 * this method will search if an specific word that is passed as param is on the hashTable with the linear approach
	 * @param word
	 * @return true when it was found, false when it was not found
	 */
	public boolean searchLinearForm(String word) {
		boolean isThere=false;
		
		int placeGenerated = hashValue(word);
		
		if(hashTable[placeGenerated] != null) {
			
			if(hashTable[placeGenerated].equals(word)) {
				if(alphabeticRepeatHelper[placeGenerated] == 1) {
					hashTable[placeGenerated]=null;
					alphabeticRepeatHelper[placeGenerated] = 0;
					isThere = true;
				}
				else {
					alphabeticRepeatHelper[placeGenerated]-=1;
					isThere = true;
				}
			}
			else {
				if(placeGenerated>=hashTable.length) {
					placeGenerated=placeGenerated-hashTable.length;
				}
				isThere = searchLinearFormR(placeGenerated+1, word);
			}
		}
	
		
		return isThere;
	}
	
	/**
	 * complementary method of searchLinearForm that will help us with recursion
	 * @param place - place in the hashTable that we are now
	 * @param value - text that we are searching
	 * @return true if it is in the hashTable, false if not
	 */
	public boolean searchLinearFormR(int place, String value) {
		
		if(hashTable[place] != null) {
			if(hashTable[place].equals(value)) {
				if(alphabeticRepeatHelper[place] == 1) {
					hashTable[place]=null;
					alphabeticRepeatHelper[place] = 0;
					return true;
				}
				else {
					alphabeticRepeatHelper[place]-=1;
					return true;
				}
			}
			else {
				if(place>=hashTable.length) {
					place=place-hashTable.length;
				}
				return searchLinearFormR(place+1, value);
			}
		}
		else {
			return false;
		}
	}
	
	/**
	 * method that use linear hashing (if an space in the hashMap is already taken search in the next space) with the little modification that if the position is taken for the same word as current, it will add a counter in alphabeticRepeatHelper at that position
	 * @param place - place in the hashTable that we are now
	 * @param value - text that we are placing
	 */
	public void linealHashing(int place,String value) {
		
		if(hashTable[place]==null) {
			hashTable[place]=value;
			alphabeticRepeatHelper[place] = 1;
		}
		else if(hashTable[place] == value) {
			alphabeticRepeatHelper[place] += 1;
		}
		else {
			linealHashing(place+1, value);
		}
	}
	
	/**
	 * method to print the table (debug option)
	 */
	public void printHashTable() {
		String representation = "";
		
		for(int i=0;i<hashTable.length;i++) {
			representation += "["+ hashTable[i] +"]"; 
		}
		
		System.out.print(representation);
	}
}
