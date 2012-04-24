package ATMGui;

/**
 * Enumeration class Type - Represents the transaction types possible
 *
 * @author (Steve Sutton, ssutton@student.ncmich.edu)
 * @version 0.1 (03.18.2012)
 */
public enum Type
{
    DEPOSIT("Deposit"), WITHDRAWAL("Withdrawal"),TRANSFER("Transfer");

    private String type;

    /**
     * Creates an enum of Type
     * 
     * @param type
     */
    private Type(String type)
    {
        this.type = type;
    }

    /**
     * Output the string of the enum
     *
     * @return
     */
    @Override
    public String toString()
    {
        return type;
    }
}
