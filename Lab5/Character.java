import java.util.List;
import java.io.*;
public class Character implements java.io.Serializable
{
    protected String name;
    protected int gold;
    protected int hp;
    protected List<Item> inventory; 

    
    public Character(String name, int gold, int hp, List<Item> inventory)
    {
        this.name = name;
        this.gold = gold;
        this.hp = hp;
        this.inventory = inventory;
    }
    
    public String getName()
    {
        return name;
    }
    
    public int getGold()
    {
        return gold;
    }
        
    public int getHP()
    {
        return hp;
    }    
    
    public List getInventory()
    {
        return inventory;
    }
    
    public int takeDamage(int d)
    {
        hp -= d;
        if (hp < 0)
        {
            hp = 0;
        }
        return hp;
    }
    
    public int Heal(int h)
    {
        hp += h;
        if (hp > 100)
        {
            hp = 100;
        }
        return hp;
    }
    
    public int gainGold(int g)
    {
        gold += g;
        return gold;
    }
    
    public int loseGold(int l)
    {
        gold -= l;
        if (gold < 0)
        {
            gold = 0;
        }
        return gold;
    }
    
    public String toString()
    {
        return "Character Name: " + name + "\n" + 
        "Gold: " + gold + "\n" +
        "HP: " + hp + "\n" + 
        "Inventory: \n" + inventory;
    }
}