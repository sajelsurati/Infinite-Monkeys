/**
 * Infinite Monkey Theorem
 * 
 * This class creates a hash map with the k length chunk as the key and a frequency map object as 
 * the value.
 * 
 * Methods
 * 1. appendValue to add a chunk to the hash map
 * 2. generateChar to generate a random character
 * 3. testIfLastChunk to test whether the chunk is the last chunk in the file
 * 
 * @author Sajel Surati
 */

import java.util.Map;
import java.util.HashMap;

public class SequenceTable {

    private Map<String, FrequencyMap> totalFrequencyTable = new HashMap<String, FrequencyMap>();

    /**
     * appendValue method
     * 
     * Creates a new FrequencyMap object, uses the FrequencyMap method cycleThruCharacter to make
     * a hash map of the characters and how often they occur, and finally appends the chunk and
     * FrequencyMap object to the totalFrequencyTable.
     * 
     * @param charChunk the character chunk being tracked
     * @param lastChars a string of the characters that appear immediately after the chunk
     */

    public void appendValue(String charChunk, String lastChars) {
        FrequencyMap freqMap = new FrequencyMap();

        freqMap.cycleThruChars(lastChars);

        totalFrequencyTable.put(charChunk, freqMap);
    }

    /**
     * generateChar method
     * 
     * Creates a FrequencyMap object that is equivalent to the value for the chunk, runs the
     * FrequencyMap method returnRandomChar, and returns the character.
     * 
     * @param chunk k length chunk being analyzed
     * @return random character
     */
    
    public char generateChar(String chunk) {
        FrequencyMap mapForChunk = totalFrequencyTable.get(chunk);
        return mapForChunk.returnRandomChar();
    }

    /**
     * testIfPresent method
     * 
     * Tests whether the hash map contains the chunk as a key.
     *  
     * @param chunk chunk to be analyzed
     * @return boolean value whether totalFrequencyTable contains the chunk as a key
     */
    public boolean testIfPresent(String chunk) {
        return totalFrequencyTable.containsKey(chunk);
    }
}
