package ATMGui;

import java.util.Date;

/**
 * Holds the data for each transaction. account from and to and the amount.
 * for deposits account to and amount will be used
 * for withdrawals account from and amount will be used
 * for transfers account from to and amount will be used
 *
 * @author (Steve Sutton, ssutton@student.ncmich.edu)
 * @version 0.1 (03.18.2012)
 */
public class Transaction
{
    private Date date;
    private Account accountFrom;
    private Account accountTo;
    private int amount;
    private Type type;

    /**
     * Constructor for objects of class Transaction
     *
     * @param type    Type of Transaction (enum)
     * @param amount  Amount of transaction
     * @param account Account used for transaction
     */
    public Transaction(Type type, int amount, Account account)
    {
        date = new Date();
        this.type = type;
        this.amount = amount;
        switch(type) {
            case DEPOSIT:
                accountTo = account;
                break;
            case WITHDRAWAL:
                accountFrom = account;
                break;
        }
    }

    /**
     * Constructor for objects of class Transaction
     *
     * @param amount      Amount of transfer
     * @param accountFrom Account transfer is from
     * @param accountTo   Account transfer is to
     */
    public Transaction(int amount, Account accountFrom, Account accountTo)
    {
        date = new Date();
        this.amount = amount;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        type = Type.TRANSFER;
    }

    /**
     * Returns account from
     *
     * @return accountFrom
     */
    public Account getAccountFrom()
    {
        return accountFrom;
    }

    /**
     * Returns account to
     *
     * @return accountTo
     */
    public Account getAccountTo()
    {
        return accountTo;
    }

    /**
     * Return the transaction type
     *
     * @return type Type of transaction
     */
    public Type getType()
    {
        return type;
    }

    /**
     * Return the Amount of the transaction
     *
     * @return amount Amount of the transaction
     */
    public int getAmount()
    {
        return amount;
    }

    /**
     * Returns the transaction converted to a string
     *
     * @return transaction as a string
     */
    @Override
    public String toString()
    {
        String transaction = "Date: " + date + "\n";
        transaction += "Type: " + type + " ";
        switch(type) {
            case DEPOSIT:
                transaction += "Account To: " + accountTo + "\n" +
                "Deposit Amount: $" + amount + ".00\n" +
                "Available Balance: " + accountTo.getBalance() + ".00\n";
                break;
            case WITHDRAWAL:
                transaction += "Account From: " + accountFrom + "\n" +
                "Withdrawal Amount: $" + amount + ".00\n" +
                "Available Balance: " + accountFrom.getBalance() + ".00\n";
                break;
            case TRANSFER:
                transaction += "Account From: " + accountFrom + "\n" +
                "Account To: " + accountTo + "\n" +
                "Transfer Amount: $" + amount + ".00\n" +
                "Available Balance (to account): " + accountTo.getBalance() + ".00\n";
                break;
        }
        return transaction;
    }

}
