package main;

import ransomNote.RansomNote;

public class Main {
 
	private RansomNote ran;
	
	public Main() {
	 ran=new RansomNote();
	
	 String k="hola 8 8 8 8 8 8 8 8 8 8 ";
	 String L="hola xd y u";
	 
	 String[] magazine = k.split(" ");
	 String[] note= L.split(" ");
	 
	 ran.checkMagazine(magazine, note);
	
	}
	
	public static void main(String[] args) {
		
		Main m = new Main();

	}

}
