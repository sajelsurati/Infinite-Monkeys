/**
 * Infinite Monkeys Theorem
 * 
 * This class creates a hash map assigning a character to a value of the number of times it occurs
 * in the file, cycles through a string of all the last characters to count how many times each
 * character occurs, and returns a random character.
 * 
 * Methods
 * 1. cycleThruChars runs through a string of characters and calls the addChar method
 * 2. addChar checks whether freqOfChars contains the character and adds 1 if it does and adds the
 * key if not and assigns the value to 1
 * 3. returnRandomChar picks a random number between 0 and the number of characters in the string
 * containing them and runs through the chracter values until it arrives at a number it is less than
 * and a number it is greater than.
 * 
 * @author Sajel Surati
 */

import java.util.Map;
import java.util.HashMap;
import java.util.Random;

public class FrequencyMap {
    
    private Map<Character, Integer> freqOfChars = new HashMap<Character, Integer>();
    private static Random rand = new Random();

    /**
     * addChar method
     * 
     * checks whether freqOfChars contains the character being examined. If it does,then the
     * current value is increased by 1 and the new value is added. If it does not,then the chracter
     * is appended to the hash map and the value assigned is 1.
     * 
     * @param charToCount the character being examined
     * 
     */

    private void addChar(char charToCount) {
        if (freqOfChars.containsKey(charToCount)) {
            int existingFreq = freqOfChars.get(charToCount);
            freqOfChars.put(charToCount, (existingFreq + 1));
        }

        else {
            freqOfChars.put(charToCount, 1);
        }

    }

    /**
     * cycleThruChars method
     * 
     * goes through each character in the allLastChars parameter and calls the
     * addChar method
     * 
     * @param allLastChars a string of the last characters after a given chunk
     * 
     */

    public void cycleThruChars(String allLastChars) {
        for (int i = 0; i < allLastChars.length(); i++) {
            char addingChar = allLastChars.charAt(i);
            addChar(addingChar);
        }

    }

    /**
     * returnRandomChar method
     * 
     * When called, it gets a random number in between 0 and the sum of the values in the hash map.
     * This number is compared to each of the values in the freqOfChars until the number is less
     * than the value added to the values before it.
     * 
     * @return the character whose value added with characters before it is greater than the random
     * integer.
     */

    public char returnRandomChar() {
        int currentCharNumber = 0;
        int totalNumberOfChars = 0;

        for (char key: freqOfChars.keySet() ) {
            totalNumberOfChars += freqOfChars.get(key);
        }

        int randomChar = rand.nextInt(totalNumberOfChars);

        for (char key: freqOfChars.keySet()) {

            currentCharNumber += freqOfChars.get(key);
            if (randomChar < currentCharNumber) {
                 return key;
            }
        }
        return '\0';
    }
    
}
