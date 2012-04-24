package ATMGui;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * Keeps a log entry of the transactions and start up and shutdown
 * of the ATM.
 *
 * @author (Steve Sutton, ssutton@student.ncmich.edu)
 * @version 0.3 (03.17.2012)
 */
public class Logger
{
    private String logFile;
    private BufferedWriter writer;

    /**
     * Constructor for objects of class Logger
     */
    public Logger()
    {
        logFile = "atmLogFile.txt";
    }

    /**
     * Creates log entry in the logFile. Starting with a Date entry.
     *
     * @param logEntry
     */
    public void createLogEntry(String logEntry)
    {
        try {
            writer = new BufferedWriter(new FileWriter(logFile, true));
            String logMessage = "Date: " + new Date();
            logMessage += " LogEntry: " + logEntry.replaceAll("\n", "");
            writer.write(logMessage + "\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
