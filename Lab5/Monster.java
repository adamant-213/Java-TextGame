import java.util.List;
public class Monster
{
    protected String name;
    protected int hp;   
    protected int damage;
    public Monster(String name, int hp, int damage)
    {
        this.name = name;
        this.hp = hp;
        this.damage = damage;
    }
    
    public String getName()
    {
        return name;
    }
        
    public int getHP()
    {
        return hp;
    }    
    
    public int getDamage()
    {
        return damage;
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
    
    public String toString()
    {
        return "Character Name: " + name + "\n" + 
        "HP: " + hp + "\n";
    }
}