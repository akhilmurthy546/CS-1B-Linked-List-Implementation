package csv;

/**
 * abstract class to define methods used in GDP and School Enrollment indicator classes
 */
public abstract class Indicator
{
    int year;
    final int INVALID_DATA = -1;

    /**
     * constructor to initialize instance variable year
     * @param dataYear input parameter of the year
     */
    public Indicator (int dataYear)
    {
        this.year = dataYear;
    }

    /**
     * method to return instance variable year
     * @return returns the instance variable year
     */
    public int getYear()
    {
        return year;
    }

    /**
     * method used to initialize instance variables
     * @param input a 1D array of double values
     */
    public abstract void setData(double[] input);

    /**
     * method to access certain data
     * @return returns a 1D array of double values
     */
    public abstract double[] getData();

    /**
     * method to return a string object representing the data
     * @return returns a string object representing the data
     */
    public  abstract String toString();
}
