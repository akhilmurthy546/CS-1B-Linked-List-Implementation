package csv;

/**
 * child class of Indicator that manages the School Enrollment indicator type
 */
public class SchoolEnrollmentIndicator extends Indicator
{
    double netPrimary;
    double netSecondary;

    /**
     * constructor to initialize the year
     * @param year input of the year
     */
    public SchoolEnrollmentIndicator (int year)
    {
        super(year);
        netPrimary = INVALID_DATA;
    }

    /**
     * constructor to initialize year, netPrimary and netSecondary
     * @param year input of the year
     * @param primaryEnrollment input of the enrollment in primary school
     * @param secondaryEnrollment input of the enrollment in secondary school
     */
    public SchoolEnrollmentIndicator (int year, double primaryEnrollment, double secondaryEnrollment)
    {
        super(year);
        netPrimary = primaryEnrollment;
        netSecondary = secondaryEnrollment;
    }

    /**
     * inherited method to set data
     * @param input a 1D array of double values
     */
    @Override
    public void setData(double[] input)
    {
        netPrimary = input[0];
        netSecondary = input[1];
    }

    /**
     * method to set netPrimary to the input of primary enrollment
     * @param primaryEnrollment input of the enrollment in primary school
     */
    public void setPrimaryEnrollment(double primaryEnrollment)
    {
        netPrimary = primaryEnrollment;
    }

    /**
     * method to set netSecondary to the input of secondary enrollment
     * @param secondaryEnrollment input of the enrollment in secondary school
     */
    public void setSecondaryEnrollment(double secondaryEnrollment) {netSecondary = secondaryEnrollment;}

    /**
     * method to output primary enrollment
     * @return returns netPrimary instance variable
     */
    public double getPrimaryEnrollment()
    {
        return netPrimary;
    }

    /**
     * method to output secondary enrollment
     * @return returns netSecondary instance variable
     */
    public double getSecondaryEnrollment() {return netSecondary;}

    /**
     * inherited method to return primary and secondary enrollment
     * @return returns a double array that contains netPrimary as the first index and netSecondary as the second index
     */
    @Override
    public double[] getData()
    {
        return new double[]{netPrimary, netSecondary};
    }

    /**
     * inherited method to output a string that represents netPrimary and netSecondary data
     * @return returns a string that represents netPrimary and netSecondary data
     */
    @Override
    public String toString()
    {
        String output = String.format("(%.02f,%.02f)", netPrimary, netSecondary).replace("-1.00", " ");
        return output;
    }
}
