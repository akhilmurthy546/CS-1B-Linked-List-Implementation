package csv;

/**
 * class to store the indicator information of each country
 */
public class Country
{
    String name;
    Indicator[] indicators;

    /**
     * constructor to initialize instance variables
     * @param instanceName the name of the country instance
     * @param numYears number of year for which data is to be stored
     */
    public Country (String instanceName, int numYears)
    {
        name = instanceName;
        indicators = new Indicator[numYears];
    }

    /**
     * constructor to initialize instance variable name and used to search for name of a country within data structure
     * @param instanceName name of the country instance
     */
    public Country (String instanceName)
    {
        name = instanceName;
    }


    /**
     * method to compare input name with name of this instance
     * @param instanceName input of a name
     * @return a boolean for if input matches name of instance
     */
    public boolean equals(String instanceName)
    {
        if (name.equals(instanceName))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * to provide name of country instance
     * @return name of this instance
     */
    public String getName()
    {
        return name;
    }

    /**
     * to provide start year
     * @return the starting year
     */
    public int getStartYear()
    {
        return indicators[0].getYear();
    }

    /**
     * to provide end year
     * @return the end year
     */
    public int getEndYear()
    {
        return indicators[indicators.length - 1].getYear();
    }

    /**
     * returns indicator data of year provided
     * @param requestedYear year for which data is requested
     * @return indicator data from indicators array
     */
    public Indicator getIndicatorForYear(int requestedYear)
    {
        int incrementYear;
        if (indicators[0] == null)
        {
            return null;
        }
        incrementYear = indicators[0].getYear();
        for (int i = 0; i < indicators.length; i++)
        {
            if (incrementYear == requestedYear)
            {
                return indicators[i];
            }
            incrementYear++;
        }
        throw new IllegalArgumentException();
    }

    /**
     * sets the indicator data for a certain year
     * @param requestedYear year for which data is being set
     * @param data indicator data being set
     */
    public void setIndicatorForYear(int requestedYear, Indicator data)
    {
        int incrementYear;
        if (indicators[0] == null)
        {
            indicators[0] = data;
            return;
        }
        incrementYear = indicators[0].getYear();
        for (int i = 0; i < indicators.length; i++)
        {
            if (incrementYear == requestedYear)
            {
                indicators[i] = data;
                return;
            }
            incrementYear++;
        }
        throw new IllegalArgumentException();
    }

    /**
     * provides indicator data for provided range of years
     * @param start start year
     * @param end end year
     * @return array of indicator data for range provided
     */
    public Indicator[] getIndicatorForPeriod(int start, int end)
    {
        int index = 0;
        int validStart = indicators[0].getYear();
        int validEnd = indicators[indicators.length - 1].getYear();
        int oldStart = start, oldEnd = end;
        int startIndex = start - validStart;
        int counter = 0;

        if (start > end || (start < validStart && end < validStart) || (start > validEnd && end > validEnd))
        {
            throw new IllegalArgumentException("Invalid request of start and end year " + start + ", " + end +
                    ". Valid period for " + name + " is " + validStart + " to " + validEnd);
        }

        boolean changed = false;
        if (start < indicators[0].getYear())
        {
            changed = true;
            start = indicators[0].getYear();
        }

        if (end > indicators[indicators.length - 1].getYear())
        {
            changed = true;
            end = indicators[indicators.length - 1].getYear();
        }

        if (changed)
        {
            System.out.println("Invalid request of start and end year " +  oldStart + "," +  oldEnd +
                    ". Using valid subperiod for " + name + " is " + start + " to " + end);
        }

        int numberOfYears = (end - start)+1;
        Indicator[] outputArray = new Indicator[numberOfYears];

        for (int i = startIndex; i < numberOfYears; i++)
        {
            outputArray[counter] = indicators[i];
            counter++;
        }
        return outputArray;
    }

    /**
     * provides string object that represents the indicator data of a country instance
     * @return string representation of indicator data
     */
    public String toString()
    {
        String output = getName();
        for(int i = 0; i < indicators.length; i++)
        {
            output += " " + getIndicatorForYear(indicators[i].getYear());
        }
        return output;
    }
}
