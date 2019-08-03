package csv;

/**
 * class that implements singly-linked list of country indicator data
 */
public class CountryList
{

    CountryNode head;
    int size;

    /**
     * creates an empty list
     */
    public CountryList()
    {
        head = null;
        size = 0;
    }

    /**
     * adds node to the end of the list
     * @param data data to be stored in new node
     */
    public void add(Country data)
    {
        CountryNode current = new CountryNode(data);
        if (this.head == null)
        {
            head = current;
            this.size++;
            return;
        }

        CountryNode walker = head;

        while(walker.getNext() != null)
        {
            walker = walker.getNext();
        }

        walker.setNext(current);
        size++;
    }

    /**
     * seraches for a country object within list
     * @param search object being searched for in list
     * @return the country data in the node of interest
     */
    public Country contains(Country search)
    {
        CountryNode walker = head;

        while (walker != null)
        {
            if (walker.getData().equals(search.getName()))
            {
                return walker.getData();
            }
            walker = walker.getNext();
        }
        return null;
    }

    /**
     * provides data at an index in the list
     * @param index index to be accessed
     * @return the data at requested index
     */
    public Country getIndex(int index)
    {
        CountryNode walker = head;

        if (index < 0 || index >= this.size)
        {
            throw new IndexOutOfBoundsException();
        }
        for (int i = 0; i < index; i++)
        {
            walker = walker.getNext();
        }
        return walker.getData();
    }

    /**
     * provides size of list
     * @return the size of the list
     */
    public int size()
    {
        return size;
    }

    /**
     * provides string representation of data in the list
     * @return string output of data in the list
     */
    public String toString()
    {
        CountryNode walker = head;
        String output = "";
        while(walker != null)
        {
            output += walker.toString() + "\n";
            walker = walker.getNext();
        }
        return output;
    }
}
