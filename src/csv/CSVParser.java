package csv;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Reads csv file one line at a time and set certain attributes
 */
public class CSVParser
{
    Scanner input;
    String[] countryNames;
    int[] yearLabels;
    double[][] dataTable;
    String tokens[];
    String[] tokensNumCountries;
    String[] tokensYearLabels;
    String[] tokensEnrollmentData;
    int numOfCountries = -1;
    IndicatorType indicatorType;

    /**
     * Constructor to parse csv file and fill instance variables
     * @param filePath path of the file
     * @throws FileNotFoundException throws this when file is not found
     * @throws InvalidFileFormatException file format is invalid exception
     */
    public CSVParser(String filePath) throws FileNotFoundException, InvalidFileFormatException
    {
        try
        {
            File data = new File(filePath);
            input = new Scanner(data);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found.");
            throw new FileNotFoundException();
        }

        do
        {
            String csvFile = input.nextLine();
            tokens = csvFile.split(",");
        }
        while (!tokens[0].equals("Indicator"));
        if (tokens[1].equals("School Enrollment In Primary (% net)"))
        {
            indicatorType = IndicatorType.SCHOOL_ENROLLMENT_PRIMARY;
        }
        if (tokens[1].equals("School Enrollment In Secondary (% net)"))
        {
            indicatorType = IndicatorType.SCHOOL_ENROLLMENT_SECONDARY;
        }
        if (tokens[1].equals("GDP per capita (current US$)"))
        {
            indicatorType = IndicatorType.GDP_PER_CAPITA;
        }

        try
        {
            do
            {
                String csvFile = input.nextLine();
                tokensNumCountries = csvFile.split(",");
            }
            while (!tokensNumCountries[0].equals("Number of Countries"));
            numOfCountries = Integer.parseInt(tokensNumCountries[1]);
            if (numOfCountries == -1)
            {
                throw new InvalidFileFormatException(filePath, "Number of Countries");
            }
        }
        catch (NoSuchElementException e)
        {
            throw new InvalidFileFormatException(filePath, "Number of Countries");
        }


        do
        {
            String csvFile = input.nextLine();
            tokensYearLabels = csvFile.split(",");
        }
        while (!tokensYearLabels[0].equals("Country Name"));
        yearLabels = new int[tokensYearLabels.length - 1];
        for(int j = 0; j < (tokensYearLabels.length - 1); j++)
        {
            yearLabels[j] = Integer.parseInt(tokensYearLabels[j + 1]);
        }

        dataTable = new double[numOfCountries][yearLabels.length];

        countryNames = new String[numOfCountries];
        int counter = 0;
        do
        {
            String csvFile = input.nextLine();
            tokensEnrollmentData = csvFile.split(",");
            if (csvFile.charAt(0) == '"')
            {
                for (int y = 2; y < tokensEnrollmentData.length; y++)
                {
                    if (tokensEnrollmentData[y].equals(""))
                    {
                        dataTable[counter][y-2] = -1;
                    }
                    else
                    {
                        dataTable[counter][y-2] = Double.parseDouble(tokensEnrollmentData[y]);
                    }
                }
                countryNames[counter] = tokensEnrollmentData[0] + ", " + tokensEnrollmentData[1];
            }
            else
            {
                for (int y = 1; y < tokensEnrollmentData.length; y++)
                {
                    if (tokensEnrollmentData[y].equals(""))
                    {
                        dataTable[counter][y-1] = -1;
                    }
                    else
                    {
                        dataTable[counter][y-1] = Double.parseDouble(tokensEnrollmentData[y]);
                    }
                }
                countryNames[counter] = tokensEnrollmentData[0];
            }
            counter++;
        }
        while (input.hasNextLine());
    }

    /**
     * method to return array of country names
     * @return returns country names array
     */
    public String[] getCountryNames()
    {
        return countryNames;
    }

    /**
     * method to return year labels
     * @return returns year labels
     */
    public int[] getYearLabels()
    {
        return yearLabels;
    }

    /**
     * method to return table of enrollment data
     * @return returns enrollment data
     */
    public double[][] getParsedTable()
    {
        return dataTable;
    }

    /**
     * method to return the number of years
     * @return returns the number of years
     */
    public int getNumberOfYears()
    {
        return dataTable[0].length;
    }

    /**
     * method to return the Indicator type
     * @return returns the instance variable indicatorType
     */
    public IndicatorType getIndicatorType()
    {
        return indicatorType;
    }
}
