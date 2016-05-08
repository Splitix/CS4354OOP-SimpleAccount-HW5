package Model;

/**
 * Created by Splitix on 5/6/16.
 */
public class AgentModel extends Thread
{
    public int ID;
    public Account account;
    public AccountModel model;
    public double amount;
    public int option;

    public int deposit = 0;
    public int withdraw = 1;
    public boolean going = true;
    public int opsFinished = 0;
    public double moneyTransferred = 0;
    public double operations;

    public AgentModel(int ID, double amount, double ops, int option, Account account, AccountModel model)
    {
        this.ID = ID;
        this.account = account;
        this.model = model;
        this.amount = amount;
        this.option = option;
        this.operations = ops;
    }

    public void run()
    {
        int milSecs = 1000 / (int)operations;
        if(option == 0)
        {
            try{

                while(this.going)
                {
                    account.funds += amount;
                    opsFinished++;
                    moneyTransferred += amount;
                    ModelEvent current = new ModelEvent(this, 1, "", account, account.funds);
                    model.notifyChanged(current);
                    Thread.sleep(milSecs);
                }
            }catch(InterruptedException e) {
                System.out.println("Interrupted.");
            }
        }
        else
        {
            try{
                while(this.going)
                {
                    account.funds -= amount;
                    opsFinished++;
                    moneyTransferred -= amount;
                    ModelEvent current = new ModelEvent(this, 1, "", account, account.funds);
                    model.notifyChanged(current);
                    Thread.sleep(milSecs);
                }
            }catch(InterruptedException e){
                System.out.println("Interrupted.");
            }
        }
    }
}
