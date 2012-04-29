package ATMGui;

import java.util.ArrayList;

/**
 * Handles the input of the ATM GUI
 *
 * @author Steve Sutton, <ssutton@student.ncmich.edu>
 * @version 0.1 (04/28/2012)
 */
public final class Input
{
    private ArrayList<Integer> inputArray;

    /**
     * Creates objects of type Input
     */
    public Input()
    {
        clearInput();
    }

    /**
     * Add inputKey to inputArray
     *
     * @param inputKey number of key pressed
     */
    public void addInput(int inputKey)
    {
        inputArray.add(inputKey);
    }

    /**
     * Returns the input ArrayList as an Integer
     *
     * @return input
     */
    public int getInputAsInteger()
    {
        String input = "";
        for(Integer integer : inputArray) {
            input += integer;
        }
        return Integer.parseInt(input);
    }

    /**
     * Returns the input ArrayList as a String
     *
     * @return input
     */
    public String getInputAsString()
    {
        String input = "";
        for(Integer integer : inputArray) {
            input += integer;
        }
        return input;
    }

    /**
     * 
     */
    public void clearInput()
    {
        inputArray = new ArrayList<Integer>();
    }
}
