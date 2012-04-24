package ATMGui;

import java.util.ArrayList;

/**
 * Holds the account number for the client. Must be validated
 * with their PIN. Not stored on card.
 *
 * @author (Steve Sutton, ssutton@student.ncmich.edu)
 * @version 0.4 (03.17.2012)
 */
public class Card
{
    private ArrayList<Account> accounts;

    /**
     * Constructor for objects of class Card
     */
    public Card()
    {
        accounts = new ArrayList<Account>();
    }

    /**
     * Adds an account number to the ATM Card
     *
     * @param accountNumber Number for the account
     */
    public void addAccount(Account account)
    {
        accounts.add(account);
    }

    /**
     * Returns the account at given the the index if it exists null if not.
     *
     * @param index
     * @return account Account for given index or null
     */
    public Account getAccount(int index)
    {        
        if (index < accounts.size()) {
            return accounts.get(index);
        }
        else {
            return null;
        }
    }

    /**
     * Return the string representation of the Account object
     *
     * @return Account as string
     */
    public String toString()
    {
        return "" + accounts;
    }

}
