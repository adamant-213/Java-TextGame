public class Item implements java.io.Serializable
{
protected String name; 

public Item(String name)
{
this.name = name;
}

public String toString()
{
return "Item: " + name + ", ";
}

}