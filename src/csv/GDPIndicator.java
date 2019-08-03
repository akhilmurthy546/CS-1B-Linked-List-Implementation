package csv;

/**
 * child class of Indicator that manages the School Enrollment indicator type
 */
public class GDPIndicator extends Indicator
{
    double gdpPerCapita;

    /**
     * constructor to initialize the year variable
     * @param year input parameter of the year
     */
    public GDPIndicator (int year)
    {
        super(year);
        gdpPerCapita = INVALID_DATA;
    }

    /**
     * constructor to initialize year and gdpPerCapita
     * @param year the year input
     * @param gdpPerCapita the input GDP per capita
     */
    public GDPIndicator (int year, double gdpPerCapita)
    {
        super(year);
        this.gdpPerCapita = gdpPerCapita;
    }

    /**
     * inherited method to set data
     * @param input a 1D array of double values
     */
    @Override
    public void setData(double[] input)
    {
        gdpPerCapita = input[0];
    }

    /**
     * inherited method to return GDP per capita
     * @return returns gdpPerCapita
     */
    @Override
    public double[] getData()
    {
        return new double[]{gdpPerCapita};
    }

    /**
     * inherited method to output a string that represents gdpPerCapita
     * @return returns a string to represent GDP Per Capita
     */
    @Override
    public String toString()
    {
        String output = String.format("(%.02f)", gdpPerCapita).replace("-1.00", " ");
        return output;
    }
}
