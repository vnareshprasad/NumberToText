package com.naresh.numbertotext;

//import org.gauner.jSpellCorrect.spi.ToySpellingCorrector;;

/**
 * Convert number to word
 * long numbers
 * 
 * @author naresh
 *
 */
public class NumberToWord {
	static long number;
	static byte language = 1;
	
	static public void main(String args[]) {
		
		
		/*ToySpellingCorrector sc = new ToySpellingCorrector();
		// train some data from a text file
		sc.trainFile("/home/naresh/tamilnumber.txt");
		// train a single word
		sc.trainSingle("some word");
		// get the best suggestion
		System.out.println(sc.correct("à®‡à®°à¯�"));
		System.out.println(sc.correct("Dok"));
		System.out.println(sc.correct("Speling"));
*/
		//for(String arg : args) {
		for(int i =0; i<=0; i++) {
			number = Long.parseLong(""+i);
			NumberToWordConverter nwc;
			
			switch (language) {
			case 0:
				nwc = new EnglishConverter();
				break;
			case 1:
				nwc = new TamilConverter();
				break;
			
			default:
				nwc = new EnglishConverter();
				break;
			}
			
			sop(nwc.convertToWord(number));
			sop(nwc.convertToWord(524220428l));
			nwc.toString();
		}
	}
	
	static public void sop(String msg) {
		System.out.println(msg);
	}
}
