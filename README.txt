------------------------------------------------------------------------
This is the project README file. Here, you should describe your project.
Tell the reader (someone who does not know anything about this project)
all he/she needs to know. The comments should usually include at least:
------------------------------------------------------------------------

PROJECT TITLE: ATMGui
PURPOSE OF PROJECT:
The software to be designed will control a simulated automated teller machine (ATM) having a
magnetic stripe reader for reading an ATM card, a customer console (keyboard and display) for
interaction with the customer, a slot for depositing envelopes, a dispenser for cash (in multiples of $20),
and a printer for printing customer receipts.
The ATM will service one customer at a time. A customer will be required to insert an ATM card and enter a
personal identification number (PIN) - both of which will be sent to the
bank for validation as part of each transaction. The customer will then be able to perform one or more transactions.
The card will be retained in the machine until the customer indicates that he/she desires no further
 transactions, at which point it will be returned - except as noted below.
The ATM must be able to provide the following services to the customer:
A customer must be able to make a cash withdrawal from any suitable account linked to the card,
in multiples of $20.00. Approval must be obtained from the bank before cash is dispensed.
A customer must be able to make a deposit to any account linked to the card, consisting of cash
and/or checks in an envelope. The customer will enter the amount of the deposit into the ATM,
subject to manual verification when the envelope is removed from the machine by an operator.
Approval must be obtained from the bank before physically accepting the envelope.
A customer must be able to make a transfer of money between any two accounts linked to the card.
A customer must be able to make a balance inquiry of any account linked to the card.
A customer must be able to abort a transaction in progress by pressing the Cancel key instead of responding
to a request from the machine.
The ATM will communicate each transaction to the bank and obtain verification that it was allowed by the bank.
Ordinarily, a transaction will be considered complete by the bank once it has been approved. In the case of a
deposit, a second message will be sent to the bank indicating that the customer has deposited the envelope.
(If the customer fails to deposit the envelope within the timeout period, or presses cancel instead, no second
 message will be sent to the bank and the deposit will not be credited to the customer.)
If the bank determines that the customer's PIN is invalid, the customer will be required to re-enter the PIN
before a transaction can proceed. If the customer is unable to successfully enter the PIN after three tries,
the card will be permanently retained by the machine, and the customer will have to contact the bank to get it back.
If a transaction fails for any reason other than an invalid PIN, the ATM will display an explanation of the
problem, and will then ask the customer whether he/she wants to do another transaction.
The ATM will provide the customer with a printed receipt for each successful transaction, showing the date,
time, machine location, type of transaction, account(s), amount, and ending and available balance(s) of the
affected account ("to" account for transfers).
The ATM will also maintain an internal log of transactions to facilitate resolving ambiguities arising from a
hardware failure in the middle of a transaction. Entries will be made in the log when the ATM is started up
and shut down, for each message sent to the Bank (along with the response back, if one is expected), for the
dispensing of cash, and for the receiving of an envelope. Log entries may contain card numbers and dollar
amounts, but for security will never contain a PIN.
VERSION or DATE: 0.1
HOW TO START THIS PROJECT:
Run the program and select from the Cards menu which card you want to insert. The envelope menu has pre made
envelopes for depositing to Account 1 on Card 1 and both Accounts on Card2.

AUTHORS: Steve Sutton, ssutton@student.ncmich.edu
USER INSTRUCTIONS: