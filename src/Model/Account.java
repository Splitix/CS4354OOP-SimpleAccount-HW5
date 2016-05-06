package Model;

/**
 * Created by Joshua Galindo on 4/12/16.
 * Class for holding user account information
 */
public class Account
{
    public int ID;
    public String name;
    public double funds;

    public Account(int ID, String name, double funds)
    {
        this.ID = ID;
        this.name = name;
        this.funds = funds;
    }
}
