package ATMGui;

/**
 * Enum for Action types for the ATM GUI
 *
 * @author Steve Sutton, <ssutton@student.ncmich.edu>
 * @version 0.1 (04/28/2012)
 */
public enum Action
{
    INSERT_CARD("Insert Card"), BALANCE("Balance"), WITHDRAWAL("Withdrawal"),
    DEPOSIT("Deposit"), TRANSFER("Transfer");

    private String action;

    /**
     * Creates objects of enum type Action
     * @param action
     */
    private Action(String action)
    {
        this.action = action;
    }

    /**
     * Return the Action string value
     *
     * @return action
     */
    @Override
    public String toString() {
        return action;
    }

}
