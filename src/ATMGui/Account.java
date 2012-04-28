package ATMGui;

import java.util.HashSet;
import java.util.Random;

/**
 * Holds the account data. Can make a withdraw from the account only if the
 * balance is 0 or greater. Must have a starting balance given when creating
 * the Account object. Each account will have a unique account number given to
 * it. This will be auto generated 8 number.
 *
 * @author (Steve Sutton, ssutton@student.ncmich.edu)
 * @version 0.4 (03.17.2012)
 */
public class Account
{
    private String lname, fname;
    private int balance;
    private long accountNumber;

    private Random random;
    private static HashSet<Long> generatedAccountNumbers;
    private static final int ACCOUNT_NUMBER_LENGTH = 8;

    /**
     * Constructor for objects of class Account
     *
     * @param lname           The last name for account
     * @param fname           The first name for account
     * @param startingBalance The starting balance for account
     */
    public Account(String lname, String fname, int startingBalance)
    {
        this.lname = lname;
        this.fname = fname;
        balance = startingBalance;

        random = new Random();
        generatedAccountNumbers = new HashSet<Long>();
        generateUniqueAccountNumber();
    }

    /**
     * Returns the Last Name for the account
     *
     * @return lname Last Name for the account
     */
    public String getLname()
    {
        return lname;
    }

    /**
     * Returns the First Name for the account
     *
     * @return fname First Name for the account
     */
    public String getFname()
    {
        return fname;
    }

    /**
     * Return the account balance
     *
     * @return balance Amount of money in the account
     */
    public int getBalance()
    {
        return balance;
    }

    /**
     * Returns accountNumber
     *
     * @return accountNumber The account number
     */
    public long getAccountNumber()
    {
        return accountNumber;
    }

    /**
     * Make a deposit amount to the balance
     *
     * @param amount Amount to add to the balance
     */
    public void makeDeposit(int amount)
    {
        balance += amount;
    }

    /**
     * Attempt a withdraw if the balance is negative then don't allow it.
     * Withdrawals must also be multiples of $20.00
     *
     * @param amount Amount of money to withdraw
     * @return amount or 0.00 if not enough money in account
     */
    public int makeWithdrawal(int amount)
    {
        if (amount < 20) {
            return 0;
        }
        if (amount % 20 == 0) {
            if (balance - amount >= 0) {
                balance -= amount;
                return amount;
            }
        }
        return 0;
    }

    /**
     * Generate the customer number making sure that it is
     * unique. This is done by storing the generated numbers
     * in a HashSet and checking the HashSet for the number to
     * make sure it hasn't already been added to the HashSet.
     */
    private void generateUniqueAccountNumber()
    {
        long number = generateRandom(ACCOUNT_NUMBER_LENGTH);
        while (generatedAccountNumbers.contains(number)) {
            number = generateRandom(ACCOUNT_NUMBER_LENGTH);
        }
        generatedAccountNumbers.add(number);
        accountNumber = number;
    }

    /**
     * Generate a random number at given length
     *
     * @param length Length of the number to generate
     * @return long of random generated number
     */
    private long generateRandom(int length) {
        char[] digits = new char[length];
        digits[0] = (char) (random.nextInt(9) + '1');
        for (int i = 1; i < length; i++) {
            digits[i] = (char) (random.nextInt(10) + '0');
        }
        return Long.parseLong(new String(digits));
    }

    /**
     * Represents state of the object in a string
     *
     * @return string
     */
    public String toString()
    {
        return accountNumber + " " +
                fname + " " + lname;
    }
}
