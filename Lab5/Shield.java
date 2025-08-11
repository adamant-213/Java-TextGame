public class Shield extends Item implements java.io.Serializable
{
protected String grade;
protected int defense; 

public Shield(String name, String grade, int defense)
{
    super(name);
    this.grade = grade;
    this.defense = defense;
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

public int setDefense(int d)
{
    defense = d;
    return defense;
}

public int getDefense()
{
    return defense;
}

public String toString()
{
    return super.toString() + "Grade: " + grade + ", " + "Defense: " + defense + "\n";
}

}