package ATMGui;

/**
 * Holds amount for an account in the ATM
 *
 * @author (Steve Sutton, ssutton@student.ncmich.edu)
 * @version 0.1 (03.17.2012)
 */
public class Envelope
{
    private int amount;
    private long accountNumber;

    /**
     * Constructor for objects of class Envelope
     */
    public Envelope(int cash, long accountNumber)
    {
        this.amount = cash;
        this.accountNumber = accountNumber;
    }

    /**
     * Returns account number
     *
     * @return accountNumber account number on envelope
     */
    public long getAccountNumber()
    {
        return accountNumber;
    }

    /**
     * Returns the envelope amount
     *
     * @return amount Amount in envelope
     */
    public int getAmount()
    {
        return amount;
    }

    /**
     * Returns string for the envelope
     *
     * @return
     */
    @Override
    public String toString()
    {
        return "Account#: " + accountNumber + " cash: " + amount;
    }

}
