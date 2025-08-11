public class Potion extends Item implements java.io.Serializable
{
protected int numpot; 
protected int recover;

public Potion(String name, int numpot, int recover)
{
    super(name);
    this.numpot = numpot;
    this.recover = recover;
}

public int getRecover()
{
    return recover;
}

public int getNumPot()
{
    return numpot;
}

public int GainPot(int p)
{
    numpot += p;
    return numpot;
}
    
public int UsePot(int p)
{
    numpot -= p;
    return numpot;
}

public String toString()
{
    return super.toString() + "Recover: " + recover + ", " + "Amont: " + numpot;
}

}