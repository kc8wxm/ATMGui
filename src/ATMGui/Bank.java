package ATMGui;

import java.util.HashMap;

/**
 * Validates the ATMCard and the PIN with it.
 *
 * @author (Steve Sutton, ssutton@student.ncmich.edu)
 * @version 0.1 (03.15.2012)
 */
public class Bank
{
    private HashMap<String, Card> cards;

    /**
     * Constructor for objects of class Bank
     */
    public Bank()
    {
        cards = new HashMap<String, Card>();
    }

    /**
     * Adds a Card to the bank records
     *
     * @param pin  ATM Card's PIN
     * @param card ATM Card
     */
    public void addCard(String pin, Card card)
    {
        cards.put(pin, card);
    }

    /**
     * Compares the given Card and PIN to see if they match
     * what is in the Banks records
     *
     * @param card
     * @param pin
     * @return true if valid false if not valid
     */
    public boolean isValidCard(Card card, String pin)
    {
        boolean isValid = false;
        Card bankCard = cards.get(pin);
        if(bankCard != null && bankCard.equals(card)) {
            isValid = true;
        }
        return isValid;
    }

    /**
     * Handles processing the transaction from the ATM
     *
     * @param transaction Transaction from ATM
     * @return 
     */
    public boolean processTransaction(Transaction transaction)
    {
        boolean success = false;
        int amount = 0;
        switch(transaction.getType()) {
            case WITHDRAWAL:
                if (transaction.getAccountFrom().getBalance() >= transaction.getAmount()) {
                    transaction.getAccountFrom().makeWithdrawal(transaction.getAmount());
                    success = true;
                }
                break;
            case DEPOSIT:
                transaction.getAccountTo().makeDeposit(transaction.getAmount());
                success = true;
                break;
            case TRANSFER:
                if(transaction.getAccountFrom().getBalance() >= transaction.getAmount()) {
                    transaction.getAccountFrom().makeWithdrawal(transaction.getAmount());
                    transaction.getAccountTo().makeDeposit(transaction.getAmount());
                    success = true;
                }
                break;
        }
        return success;
    }

}
