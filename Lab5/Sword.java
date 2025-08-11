public class Sword extends Item implements java.io.Serializable
{
protected String grade;
protected int damage;

public Sword(String name, String grade, int damage)
{
    super(name);
    this.grade = grade;
    this.damage = damage;
}

public String setGrade(String g)
{
    grade = g;
    return grade;
}

public String getGrade()
{
    return grade;
}

public int setDamage(int d)
{
    damage = d;
    return damage;
}

public int getDamage()
{
    return damage; 
}

public String toString()
{
    return super.toString() + "Grade: " + grade + ", "+ "Damage: " + damage + " \n";
}

}