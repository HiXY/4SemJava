class OwnArrayList <T>
{
    int size = 12;
    Object[] Array = new Object[size];
    int iter = 0;


    void add (T item)
    {
        if (iter == Array.length)
        {
            enlarge (Array.length * 2);
        }
        Array[iter++] = item;
    }


    void add (int index, T item)
    {
        if (iter == Array.length)
        {
            enlarge (Array.length * 2);
        }
        for (int i = iter; i >= index; i--)
        {
            Array[i] = Array[i - 1];
        }
        Array[index] = item;
    }

    
    void clear ()
    {
        for (int i = 0; i < Array.length; i++)
        {
            Array[i] = null;
        }
        iter = 0;
    }

    
    T get (int index)
    {
        if (iter == 0)
        {
            System.out.println ("Empty Array: unable to get item");
            System.exit (0);
        }
        return (T) Array[index];
    }


    void remove (int index)
    {
        for (int i = index; i < iter; i++)
        {
            Array[i] = Array[i + 1];
        }
        Array[iter--] = null;
    }


    void enlarge (int NewLength)
    {
        Object[] NewArray = new Object[NewLength];
        System.arraycopy (Array, 0, NewArray, 0, Array.length);
        Array = NewArray;
    }

    
    void print (int index)
    {
        System.out.println (get (index));
    }


    void print ()
    {
        for (int i = 0; i < Array.length; i++)
        {
            if (Array[i] != null)
            System.out.println (Array[i]);
        }
    }
}


class MyArrayList 
{
    public static void main (String[] args)
    {
        OwnArrayList <Integer> Arr = new OwnArrayList <Integer> ();
        Arr.add (15);
        Arr.add (16);
        Arr.add (17);
        Arr.print (1);
    }
}
