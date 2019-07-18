package csv;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *  Tests the Country class, which holds Indicator data for each year in 
 *  chronological order.
 *
 * @author Foothill College, [YOUR NAME HERE]
 */
public class TestCountry
{	
	/**
	 * Displays the names of countries. 
	 * @param countries  array of Country objects
	 */
	protected static void displayCountryNames(Country [] countries)
	{
		String countryNames = "";
		int counter = 0;

		for (int i = 0; i < countries.length; i++)
		{
			// Concatenates the name of countries
			countryNames += " " + countries[i].getName();
			// uses a ternary operator to prettify the output
			countryNames += (counter+1) % 50 == 0 ? "\n" : ",";
			counter++;
		}
		System.out.println("\nCountry names:\n" + countryNames + "\n");
	}

	/**
	 * Display the entire data table of the country such that it is readable.
	 * Note: This requires that you experiment with the formatting of the output
	 * @param countries  array of Country objects
	 */
	protected static void displayCountries(Country [] countries)
	{
		StringBuilder countryInfo = new StringBuilder();
		int startingYear = countries[0].getStartYear();
		int numYears = countries[0].getEndYear()-startingYear+1;

		for (int countryIndex = 0; countryIndex < countries.length; countryIndex++)
		{
			// appends year header
			if (countryIndex % 5 == 0)
			{
				countryInfo.append(String.format("\n%20s", "Country Name"));
				for(int yearIndex = 0; yearIndex < numYears; yearIndex++)
					countryInfo.append(String.format("%18d  ", (startingYear+yearIndex)));
				countryInfo.append("\n");
			}
			// appends indicator data
			countryInfo.append(countries[countryIndex].toString());
		}

		System.out.println("\nCountries:\n" + countryInfo + "\n");
	}

	/**
	 * Performs a linear search of the array of Country objects based on the name of the Country.
	 * @param requestedCountryName  Name of the Country object to search for
	 * @param countries	One-dimensional array of Country objects.
	 * @return	The Country object if found. Otherwise, null.
	 */
	private static Country findCountry(String requestedCountryName, Country[] countries) 
	{
		for (int i = 0; i < countries.length; i++)
		{				
			// TODO: In class Country override the equals method such that it returns
			//       true if the current object has the same name.
			if (countries[i] != null && countries[i].equals(requestedCountryName))
			{
				return countries[i];
			}
		}
		return null;
	}

	/**
	 * Prints out the contents of a double array
	 * @param requestedEnrollmentPeriod   one-dimensional array of enrollment data.
	 */
	private static void displayEnrollmentPeriod(String countryName, Indicator[] requestedEnrollmentPeriod) 
	{
		System.out.printf("%20s ", countryName);
		for(int i = 0; i < requestedEnrollmentPeriod.length; i++)
		{
			if (requestedEnrollmentPeriod[i] == null)
				System.out.printf("     ," + "   ");
			else
				System.out.printf("%s   ", requestedEnrollmentPeriod[i].toString());
		}
		System.out.println("");
	}

	/**
	 * Displays the menu option for different indicators.
	 * Note: Update the menu option if you add additional input files or categories.
	 */
	private static void printMenu()
	{
		System.out.print("Select an indicator to parse. ");
		System.out.println("Available indicators:\n");
		System.out.println("0. Invalid data (for debugging)");
		System.out.println("1. GDP per Capita (short for debugging)");
		System.out.println("2. School Enrollment (short for debugging)");
		System.out.println("3. GDP per Capita");
		System.out.println("4. School Enrollment");
	}
	
	/**
	 * Creates an object of type CSVParser which parses a CSV file. If
	 * the format of the CSVParser is valid, creates a Country object
	 * from each parsed line. 
	 * Tests the Country class using with various date ranges. For each 
	 * test case, either prints that data for the overlapping years found 
	 * or catches an IllegalArgumentExceptions.
	 */
	public static void main(String[] args) 
	{
		// NOTE: Make sure to use relative path instead of specifying the entire path
		//       such as (/Users/alicew/myworkspace/so_on_and_so_forth).

		// Example of invalid input file
		final String [] INVALID_INPUT= {"resources/childrenEnrolled_invalid.csv"};

		// Example of input file for GDP per capita:
		final String [] GDP_INPUT_SHORT = { "resources/gdpPerCapita_short_12years.csv"};

		// Example of input file for net school enrollment for:
		// [0] primary grade
		// [1] secondary grade
		final String [] ENROLLMENT_INPUT_SHORT = { "resources/childrenEnrolledInPrimary_short_12years.csv", 
				"resources/childrenEnrolledInSecondary_short_12years.csv"
		};

		// Example of input file for GDP per capita:
		final String [] GDP_INPUT = { "resources/gdpPerCapita.csv"};

		// Example of input file for net school enrollment for:
		// [0] primary grade
		// [1] secondary grade
		final String [] ENROLLMENT_INPUT = { "resources/childrenEnrolledInPrimary.csv", 
				"resources/childrenEnrolledInSecondary.csv"
		};

		// Prompts the user and asks for a selection.
		printMenu();
		Scanner input = new Scanner(System.in);
		String line = input.nextLine();
		int selection = Integer.parseInt(line);
		final String [] filenames;

		IndicatorType selectedIndicator;
		if (selection == 1 || selection == 3)
		{
			selectedIndicator = IndicatorType.GDP_PER_CAPITA;
			filenames = selection == 1 ? GDP_INPUT_SHORT : GDP_INPUT;
		}
		else if (selection == 2 || selection == 4)
		{
			// Note: alternatively we can set the selected indicator as SCHOOL_ENROLLMENT_SECONDARY
			selectedIndicator = IndicatorType.SCHOOL_ENROLLMENT_PRIMARY;
			filenames = selection == 2 ? ENROLLMENT_INPUT_SHORT : ENROLLMENT_INPUT;
		}
		else 
		{
			selectedIndicator = IndicatorType.INVALID;
			filenames = INVALID_INPUT;
		}

		// For debugging purposes
		System.out.println("The selected indicator is " + selectedIndicator);

		// TODO: Create the class Country to hold the data for one or more indicators 
		Country [] countries = null;

		for (int currentFileIndex = 0; currentFileIndex < filenames.length; currentFileIndex++)
		{
			// Parse the CSV data file BASED on the input filename.
			CSVParser parser;
			try 
			{
				parser = new CSVParser(filenames[currentFileIndex]);
				// Given the childrenEnrolledInPrimary_short_12years.csv file, the output is:
				// 			School Enrollment In Primary (% net) updated at 7/25/18
				//
				// Given the gdpPerCapita_short_12years.csv file, the output is:
				// 			GDP per capita (current US$) updated at 8/28/18

			} 
			catch (FileNotFoundException e) 
			{
				System.err.printf("File name %s not found.", filenames[currentFileIndex]);
				return;
			}
			catch (InvalidFileFormatException e)
			{
				System.err.printf("Invalid file format %s\n", e.getMessage());
				return;
			}

			// For debugging purposes
			System.out.println("\nParsing filename " + filenames[currentFileIndex]);

			// TODO: Define the accessor method getIndicatorType() to return
			//       the indicator read from the input file.
			// Note: Accessor methods should only return values of instance variables.
			IndicatorType indicatorType = parser.getIndicatorType();

			// NOTE: You can assume that the format of all CSV input files are the same with the same:
			//       Number of countries
			//       Name of countries
			//       Year labels
			String [] countryNames = parser.getCountryNames();
			int [] yearLabels = parser.getYearLabels();
			double [][] parsedTable = parser.getParsedTable();		

			// Check if the array of countries has been initialized by a previous iteration.
			if (countries == null)
			{
				// An array of Country objects.
				// NOTE: Here, we are no longer using the two dimensional array from EnrollmentData class.
				//       Instead, each country will hold it's own data.
				//       So, we no longer need the EnrollmentData class.
				countries = new Country[countryNames.length];				 
			}

			// Note: We are moving two dimensional array of Indicator objects
			//       to a single dimensional array of Country objects where each
			//       Country objects stores a single dimensional array of Indicators.
			// Reference to a Country object
			Country foundCountry;

			// Go through each country name parsed from the CSV file.
			// If the country name has not bee seen before create a new object of
			// type Country to hold an array of Indicator objects.
			for (int countryIndex = 0; countryIndex < countries.length; countryIndex++)
			{
				int numberOfYears = yearLabels.length;   // alternatively numberOfYears = dataTable[countryIndex].length;

				foundCountry = findCountry(countryNames[countryIndex], countries);

				if (foundCountry == null)
				{
					// Create a Country object
					// TODO: Create a class constructor which takes two arguments:
					//       1) a String for the name of the country
					//       2) an integer for the number of data for each country
					// NOTE: Similar to the previous project we'll assume the data is well formed
					//       with the same number of years of data for all countries.
					foundCountry = new Country(countryNames[countryIndex], numberOfYears);
				}

				// Get the parsed array of doubles for the current Country object
				double [] dataForAllYears = parsedTable[countryIndex];

				// Go through each year of data read from the CSV file.
				for (int yearIndex = 0; yearIndex < dataForAllYears.length; yearIndex++)
				{
					// Refers to the current indicator.
					Indicator dataForOneYear = null;

					// Checks if a previous input file has already added the current year.
					// Note: Assume the CSV files are well formed with same number of years
					//       and that the years are consecutive.
					dataForOneYear = foundCountry.getIndicatorForYear(yearLabels[yearIndex]);

					// Recall that each CSV input file we read will provide us one data value.
					switch(indicatorType)
					{
					case GDP_PER_CAPITA:
						// if true then we have not seen this Country before
						if (dataForOneYear == null)
						{
							dataForOneYear = new GDPIndicator(yearLabels[yearIndex]);
						}
						// The overriden method of the abstact class requires an one dimensional array
						// Note: Curly braces specify that we are creating and initializing an array in place.
						double data[] = {dataForAllYears[yearIndex]};
						// Uses the overriden method to set the data
						((GDPIndicator)dataForOneYear).setData(data);
						break;
					case SCHOOL_ENROLLMENT_PRIMARY:
						if (dataForOneYear == null)
						{
							dataForOneYear = new SchoolEnrollmentIndicator(yearLabels[yearIndex]);
						}
						((SchoolEnrollmentIndicator)dataForOneYear).setPrimaryEnrollment(dataForAllYears[yearIndex]);
						break;
					case SCHOOL_ENROLLMENT_SECONDARY:
						if (dataForOneYear == null)
						{
							// TODO: Update the child class SchoolEnrollmentIndicator to hold a second 
							//       piece data for the netSecondary.
							dataForOneYear = new SchoolEnrollmentIndicator(yearLabels[yearIndex]);
						}
						((SchoolEnrollmentIndicator)dataForOneYear).setSecondaryEnrollment(dataForAllYears[yearIndex]);
						break;
					default:
						break; 
					}
					// initialize the year
					foundCountry.setIndicatorForYear(yearLabels[yearIndex], dataForOneYear);	
				}

				// add the newly created country to the 1D array
				countries[countryIndex] = foundCountry;
			} // end of for loop traversing the array of Country objects
		} // end of the for loop parsing the CSV file(s)

		// Displays the name of each Country object
		displayCountryNames(countries);
		// Given the childrenEnrolledIn*_short_12years.csv file(s), the output is:
		// Countries:
		// 		 Argentina, Canada, China, Egypt, Arab Rep., India, Nepal, Syrian Arab Republic, United States,

		// Verbose display of the country information
		displayCountries(countries);

		// -------------------------------------------------------------------
		// Test cases that requests data between different start and end years 
		Indicator [] requestedEnrollmentPeriod;

		try
		{
			int countryNum = 1;
			String countryName = countries[countryNum].getName();;
			int requestedStartYear = 2006;
			int requestedEndYear = 2014;
			System.out.printf("\nRequested enrollment period (%d to %d) for %s:\n", requestedStartYear, requestedEndYear, countryName);
			requestedEnrollmentPeriod = countries[countryNum].getIndicatorForPeriod(requestedStartYear,requestedEndYear);
			displayEnrollmentPeriod(countryName,requestedEnrollmentPeriod);
		}
		catch (IllegalArgumentException exc)
		{
			System.out.println(exc.getMessage());
		}

		try
		{
			int countryNum = countries.length/2;
			String countryName = countries[countryNum].getName();
			int requestedStartYear = 1950;
			int requestedEndYear = 2000;
			System.out.printf("\nRequested enrollment period (%d to %d) for %s:\n", requestedStartYear, requestedEndYear, countryName);
			requestedEnrollmentPeriod = countries[countryNum].getIndicatorForPeriod(requestedStartYear,requestedEndYear);
			displayEnrollmentPeriod(countryName,requestedEnrollmentPeriod);
		}
		catch (IllegalArgumentException exc)
		{
			System.out.println(exc.getMessage());
		}

		try
		{
			int countryNum = 1;
			String countryName = countries[countryNum].getName();
			int requestedStartYear = 2000;
			int requestedEndYear = 2030;
			System.out.printf("\nRequested enrollment period (%d to %d) for %s:\n", requestedStartYear, requestedEndYear, countryName);
			requestedEnrollmentPeriod = countries[countryNum].getIndicatorForPeriod(requestedStartYear,requestedEndYear);
			displayEnrollmentPeriod(countryName,requestedEnrollmentPeriod);
		}
		catch (IllegalArgumentException exc)
		{
			System.out.println(exc.getMessage());
		}

		//
		// TODO: For full credit, include test cases in addition to those provided.
		//
		// TODO: Test your implementation such that it can handle a variety of input files and Indicators.

		System.out.println("\nDone with TestCountry.\n");
	}
}
