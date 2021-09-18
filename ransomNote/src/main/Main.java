package main;

import java.util.ArrayList;

import ransomNote.RansomNote;

/**
 * Main class
 * @author juandavid2025
 */
public class Main {
 
	/**
	 * Association with the class RansomNote in package ransomNote
	 */
	private RansomNote ransom;
	
	/**
	 * Main constructor
	 */
	public Main() {
		ransom = new RansomNote();
		
		System.out.print(runTests());
	}
	
	/**
	 * Set up and run some tests to verify if the solution is correct
	 * @return true - if all test were successful, false - if at least one failed
	 */
	public boolean runTests() {
		
		boolean allCorrect = true;
		
		ArrayList<String> magazinesTestArrayList = new ArrayList<String>();
		ArrayList<String> notesTestArrayList = new ArrayList<String>();
		ArrayList<Boolean> expectedResult = new ArrayList<Boolean>();
		setUpArrayListTests(magazinesTestArrayList, notesTestArrayList, expectedResult);
		
		for(int i=0; i<magazinesTestArrayList.size() && allCorrect; i++) {
			
			boolean result = ransom.isNoteInMagazine(magazinesTestArrayList.get(i), notesTestArrayList.get(i));
			
			if(!(result==expectedResult.get(i))) {
				allCorrect=false;
				System.out.print("\n Failed test #" + i +"\n");
			}
			
		}
		
		return allCorrect;
	}
	
	/**
	 * Set up some tests in the ArrayLists to verify if the solution is correct
	 * @param magazines - magazines ArrayList to add tests
	 * @param notes - notes ArrayList to add tests
	 * @param expected - expected result ArrayList to contrast tests
	 */
	public void setUpArrayListTests(ArrayList<String> magazines, ArrayList<String> notes, ArrayList<Boolean> expected) {
		magazines.add("give me one grand today night");
		notes.add("give one grand today");
		expected.add(true);
		
		magazines.add("two times three is not four");
		notes.add("two times two is four");
		expected.add(false);
		
		magazines.add("ive got a lovely bunch of coconuts");
		notes.add("ive got some coconuts");
		expected.add(false);
	}
	
	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		
		Main m = new Main();

	}

}
