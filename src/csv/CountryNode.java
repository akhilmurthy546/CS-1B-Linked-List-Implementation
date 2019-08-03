package csv;

/**
 * class for creating nodes of country data for singly-linked list
 */
public class CountryNode
{

    private Country data;
    private CountryNode next;

    /**
     * creates a node to be added to the linked list
     * @param data data to be set to the node
     */
    public CountryNode(Country data)
    {
        this.data = data;
        this.next = null;
    }

    /**
     * initializes next and data instance variables
     * @param data data to be stored in node
     * @param node node to be connected to
     */
    public CountryNode(Country data, CountryNode node)
    {
        this.data = data;
        this.next = node;
    }

    /**
     * provides data within node
     * @return data in the node
     */
    public Country getData()
    {
        return data;
    }

    /**
     * points to next node
     * @return next node
     */
    public CountryNode getNext()
    {
        return next;
    }

    /**
     * initializes the next instance variable
     * @param input used to update the next instance variable
     */
    public void setNext(CountryNode input)
    {
        next = input;
    }

    /**
     * provides string representation of data in node
     * @return string output of data instance variable
     */
    public String toString()
    {
        String output = "" + data;
        return output;
    }
}

