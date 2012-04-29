package ATMGui;

import java.util.ArrayList;

/**
 * Reads a Card and send the account data and PIN to bank
 * for validation. Allows for customers to use a valid card and PIN
 * to withdraw cash amounts in $20 dollar increments. Accepts cash
 * deposits, print receipts. Retains an internal log of transactions.
 *
 * @author (Steve Sutton, ssutton@student.ncmich.edu)
 * @version 0.9 (03.17.2012)
 */
public class ATM
{
    private Bank bank;
    private Card card;
    private String pin;
    private int invalidCardAttempts;
    private Logger logger;
    private String location;
    private ArrayList<Envelope> cashEnvelopes;
    private Transaction currentTransaction;
    private String lastMessage;
    private boolean toManyAttempts;

    private final int MAX_INVALID_ATTEMPTS = 3;

    /**
     * Constructor for objects of class ATM
     *
     * @param bank Bank attached to the ATM
     * @param location
     */
    public ATM(Bank bank, String location)
    {
        this.bank = bank;
        this.location = location;
        card = null;
        pin = "";
        invalidCardAttempts = 0;
        logger = new Logger();
        cashEnvelopes = new ArrayList<Envelope>();
        lastMessage = "";
        toManyAttempts = false;
        currentTransaction = null;
        startUp();
    }

    /**
     * Returns the Last Message
     *
     * @return
     */
    public String getLastMessage()
    {
        return lastMessage;
    }

    /**
     * Return toManyAttempts boolean value
     *
     * @return toManyAttempts
     */
    public boolean getToManyAttempts()
    {
        return toManyAttempts;
    }

    /**
     * Return the card in the ATM Machine
     * @return
     */
    public Card getCard()
    {
        return card;
    }

    /**
     * Log ATM startup
     */
    private void startUp()
    {
        logger.createLogEntry("ATM starting up");
    }

    /**
     * Log ATM shutdown
     */
    public void shutDown()
    {
        logger.createLogEntry("ATM shutting down");
    }

    /**
     * Enter the card to ATM
     *
     * @param card Customers ATM Card
     * @param pin  PIN for the ATM Card
     * @return
     */
    public boolean enterCard(Card card, String pin)
    {
        this.card = card;
        this.pin = pin;
        toManyAttempts = false;
        return validateCard();
    }

    /**
     * Sets pin for ATM
     *
     * @param pin
     */
    public void enterPin(String pin)
    {
        this.pin = pin;
    }

    /**
     * Removes the Card from ATM
     */
    public void removeCard()
    {
        card = null;
        pin = "";
        invalidCardAttempts = 0;
    }

    /**
     * Cancel transaction
     */
    public void cancel()
    {
        currentTransaction = null;
        lastMessage = "Current transaction is aborted";
    }

    /**
     * Start the transaction with a card and pin

     * @return boolean true if bank validates the card false if not
     */
    private boolean validateCard()
    {
        if(card == null || pin == null) {
            System.out.println("You need to enter your card");
            return false;
        }
        boolean success = bank.isValidCard(card, pin);
        logger.createLogEntry("Card Validation " + card +
                " Response: " + success);
        if(!success) {
            lastMessage = "Invalid PIN";
            if(invalidCardAttempts >= MAX_INVALID_ATTEMPTS) {
                printTooManyPinEntryAttempts();
                removeCard();
                return false;
            }
            invalidCardAttempts++;
            lastMessage = "You must re-enter your PIN";
        }
        return success;
    }

    /**
     * Make a deposit to an account on the card in the ATM. Bank must
     * validate the card before transaction can take place.
     *
     * @param index  Index of the account to make deposit to
     * @param amount Amount to deposit
     */
    public void makeDeposit(int index, int amount)
    {
        if(validateCard()) {
            Account account = card.getAccount(index);
            if(account != null) {
                currentTransaction = new Transaction(Type.DEPOSIT, amount, account);
                lastMessage = "Please enter your envelope";
            }
            else {
                printInvalidAccount();
            }
        }
    }

    /**
     * Deposits envelope and verifies that the amount in envelope is correct
     * per the transaction amount of the deposit
     *
     * @param envelope
     */
    public void depositEnvelope(Envelope envelope)
    {
        lastMessage = "";
        if(    envelope.getAccountNumber() == currentTransaction.getAccountTo().getAccountNumber()
            && envelope.getAmount() == currentTransaction.getAmount()) {
            bank.processTransaction(currentTransaction);
            cashEnvelopes.add(envelope);
            logger.createLogEntry("Deposit: " +
                    currentTransaction.toString());
            printReceipt(currentTransaction);
            currentTransaction = null;
        }
        else {
            lastMessage = "Envelope amount is different than what you entered" +
                    "Your envelope has been returned \n" +
                    "cancel or try another envelope";
        }
    }

    /**
     * Make a withdrawal from the account at the given index.
     *
     * @param index  Index of the account ArrayList
     * @param amount Amount to withdrawal
     * @return
     */
    public int makeWithdrawal(int index, int amount)
    {
        if(validateCard()) {
            Account account = card.getAccount(index);
            if(account != null) {
                currentTransaction = new Transaction(Type.WITHDRAWAL, amount, account);
                logger.createLogEntry("Withdrawal: " +
                        currentTransaction.toString());
                bank.processTransaction(currentTransaction);
                printReceipt(currentTransaction);
                currentTransaction = null;
                return amount;
            }
            else {
                printInvalidAccount();
            }
        }
        return 0;
    }

    /**
     * Transfer an amount from one account an card to another
     *
     * @param indexFrom Account index from
     * @param indexTo   Account index to
     * @param amount    Amount to transfer
     */
    public void makeTransfer(int indexFrom, int indexTo, int amount)
    {
        System.out.println("indexfrom: " + indexFrom);
        System.out.println("indexto: " + indexTo);
        if(validateCard()) {
            Account accountFrom = card.getAccount(indexFrom);
            Account accountTo = card.getAccount(indexTo);
            if (accountFrom == null || accountTo == null) {
                printInvalidAccount();
            }
            else {
                currentTransaction = new Transaction(amount, accountFrom, accountTo);
                boolean success = bank.processTransaction(currentTransaction);
                logger.createLogEntry("Transfer:" +
                        currentTransaction.toString() + "\n" +
                        "Response: " + success);
                if (!success) {
                    lastMessage = "Transfer failed";
                }
                else {
                    lastMessage = "Transfer was successsful";
                    printReceipt(currentTransaction);
                    currentTransaction = null;
                }
            }
        }
    }

    /**
     * Return the balance for an account on the card
     *
     * @param index Index number of account to get balance of
     * @return balance of account
     */
    public int getBalanceForAccount(int index)
    {
        if(validateCard()) {
            Account account = card.getAccount(index);
            if(account != null) {
                lastMessage = "";
                return account.getBalance();
            }
        }
        printInvalidAccount();
        return 0;
    }

    /**
     * Print system message too many attempts made for PIN entry
     */
    private void printTooManyPinEntryAttempts()
    {
        lastMessage = new StringBuilder()
            .append("You have tried too many times...\n")
            .append("You're card will not be returned.\n")
            .append("Please contact the bank to get your\n")
            .append("card back.")
                .toString();
        toManyAttempts = true;
    }

    /**
     * Print system message that the account does not exists
     */
    private void printInvalidAccount()
    {
        lastMessage = "Account does not exists";
    }

    /**
     * Print out a receipt of the transaction
     *
     * @param transaction Transaction to print out
     */
    public void printReceipt(Transaction transaction)
    {
        System.out.println("ATM location: " + location);
        System.out.println(transaction);
    }
}
