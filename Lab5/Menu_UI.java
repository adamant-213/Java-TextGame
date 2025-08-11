import java.util.ArrayList;
import java.util.Scanner;

public class Menu_UI implements Menuable
{
    private ArrayList<String> choices;
    private Scanner scan;
    // empty constructor
    public Menu_UI()
    {
        choices = new ArrayList<String>();
        scan = new Scanner(System.in);
    }
    // overloaded constructor
    public Menu_UI(ArrayList<String> c)
    {
        choices = c;
        scan = new Scanner(System.in);
    }
    // add choice to choices
    public void addChoice(String str)
    {
        choices.add(str);
    }
    // delete choice from choices
    public void removeChoice(int index)
    {
        choices.remove(index);
    }
    // prints menu for user
    public void printMenu()
    {
        for ( int i = 0; i < choices.size(); i++ )
        {
            System.out.println((i+1) + ": " + choices.get(i));
        }
    }
    // recieves input from user
    public int getInput()
    {
        int userChoice = -1;
        while(userChoice < 1 || userChoice > choices.size() )
        {
        printMenu();
        System.out.println("Please enter a choice:");
        String check = scan.nextLine();
        check = TryParseInt(check);
        userChoice = Integer.parseInt(check);
        }
        return userChoice;
    }
    
    public String getUserInput(String msg)
    {
        System.out.println(msg);
        String inputString = scan.nextLine();
        return inputString;
    }
  
    public String print(String msg)
    {
        System.out.println(msg);
        return msg;
    }
    
    public String TryParseInt(String t)
    {
        String result = t;
        try
        {
            Integer.parseInt(t);
        }
        catch (Exception e)
        {
            System.out.println("Please enter a NUMBER corresponding with your choice!");
            result = "-1";
        }
        return result;
    }
}
