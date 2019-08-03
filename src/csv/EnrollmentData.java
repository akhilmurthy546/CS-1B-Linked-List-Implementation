package csv;

/**
 * Class that represents the enrollment data of different countries.
 */
public class EnrollmentData
{
    int yearLabels[];
    String countryNames[];
    Indicator table[][];
    private int lastFilled = 0;
    final int MISSING_DATA = -1;

    /**
     * EnrollmentData constructor used to initialize instance variables.
     * @param numCountries The number of countries in the array.
     * @param numYears Number of years being indexed.
     * @param startingYear The first year that is being indexed.
     */
    public EnrollmentData(int numCountries, int numYears, int startingYear)
    {
        yearLabels = new int [numYears];
        yearLabels[0] = startingYear;
        for (int i = 1; i < numYears; i++)
        {
            yearLabels[i] = startingYear + i;
        }

        countryNames = new String [numCountries];
        table = new Indicator [numCountries][numYears];
    }


    /**
     * addCountry adds the data of a country to table array.
     * @param countryName The name of the country.
     * @param countryData The enrollment data of the country.
     */
    public void addCountry(String countryName, Indicator countryData[])
    {

        countryNames [lastFilled] = countryName;
        for (int i = 0; i < countryData.length; i++)
        {
            table[lastFilled][i] = countryData[i];
        }
        lastFilled++;
    }

    /**
     * toString constructs a table of enrollment data.
     * @return Returns a table of enrollment data by country.
     */
    @Override
    public String toString()
    {
        String output = String.format("%20s","Country Name");
        for (int i = 0; i < yearLabels.length; i++)
        {
            output += String.format("%15d", yearLabels[i]);
        }
        output += "\n";

        for (int j = 0; j < countryNames.length; j++)
        {
            output += String.format("%20s",countryNames[j]);
            for (int k = 0; k < table[j].length; k++)
            {
                output += String.format("%15s",this.table[j][k].toString());
            }
            output += "\n";
        }
        return output;
    }

    /**
     *
     * @param name The country name to search for.
     * @param start The starting year of the index.
     * @param end The ending year of the index.
     * @return Returns an array of enrollment data by country.
     */
    public Indicator[] getEnrollmentInCountryForPeriod(String name, int start, int end)
    {
        int index = 0;
        int validStart = yearLabels[0];
        int validEnd = yearLabels[yearLabels.length - 1];
        int oldStart = start, oldEnd = end;

        if (start > end || (start < validStart && end < validStart) || (start > validEnd && end > validEnd))
        {
            throw new IllegalArgumentException("Invalid request of start and end year " + start + ", " + end +
                    ". Valid period for " + name + " is " + validStart + " to " + validEnd);
        }

        boolean changed = false;
        if (start < yearLabels[0])
        {
            changed = true;
            start = yearLabels[0];
        }

        if (end > yearLabels[yearLabels.length - 1])
        {
            changed = true;
            end = yearLabels[yearLabels.length - 1];
        }

        if (changed)
        {
            System.out.println("Invalid request of start and end year " +  oldStart + "," +  oldEnd +
                    ". Using valid subperiod for " + name + " is " + start + " to " + end);
        }

        int numberOfYears = (end - start)+1;
        Indicator[] outputArray = new Indicator[numberOfYears];

        for (int i = 0; i < countryNames.length; i++)
        {
            if (countryNames[i].equals(name))
            {
                index = i;
                break;
            }
        }
        int yearIndex = 0;
        for (int i = 0; i < yearLabels.length; i++)
        {
            if (yearLabels[i] == start)
            {
                yearIndex = i;
                break;
            }
        }
        for (int i = 0,j = yearIndex; i < numberOfYears; j++,i++)
        {
            outputArray[i] = table[index][j];
        }
        return outputArray;
    }

}
