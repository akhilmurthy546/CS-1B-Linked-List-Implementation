package csv;

import java.io.IOException;

/**
 * an object of this type is thrown to indicate invalid file format
 */
public class InvalidFileFormatException extends IOException
{
    String message;
    String filename;

    /**
     * constructor thrown as an exception when file format is invalid
     * @param name name of the file
     * @param outputMessage message to be outputted
     */
    public  InvalidFileFormatException(String name, String outputMessage)
    {
        filename = name;
        message = outputMessage;
        String exception = "Invalid line at:\n" + outputMessage;
    }

    /**
     * returns exception message when InvalidFileFormatException is thrown
     * @return an output message for when file format is invalid
     */
    public  String getMessage()
    {
        String output = "Invalid file format in " + filename + "." + " Expecting \"" + message + "\"";
        return output;
    }
}
