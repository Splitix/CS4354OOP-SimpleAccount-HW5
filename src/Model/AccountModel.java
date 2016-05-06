package Model;

import java.util.ArrayList;

/**
 * Created by Joshua Galindo on 4/14/16.
 * Withdraws or deposits money into the correct users account.
 */

public class AccountModel extends AbstractModel{
    public ArrayList<Account> userAccounts = new ArrayList<>();
    public double amount;



    public void deposit(double toDeposit, String currency, Account account)
    {
        int index = 0;
        while(userAccounts.get(index).ID != account.ID)
        {
            index++;
        }

        switch(currency){
            case "USD":
                amount = userAccounts.get(index).funds + toDeposit;
                amount = Math.round (amount * 100.00) / 100.00;
                userAccounts.get(index).funds = amount;
                break;
            case "EURO":
                amount = (userAccounts.get(index).funds) + (toDeposit / .88);
                amount = Math.round (amount * 100.00) / 100.00;
                userAccounts.get(index).funds = amount;
                break;
            case "YUAN":
                amount = (userAccounts.get(index).funds) + (toDeposit / 6.47);
                amount = Math.round (amount * 100.00) / 100.00;
                userAccounts.get(index).funds = amount;
                break;
            default:
        }

        ModelEvent current = new ModelEvent(this, 1, "", userAccounts.get(index), userAccounts.get(index).funds);
        notifyChanged(current);
    }

    public void withdraw(double toWithdraw, String currency, Account account)
    {

        int index = 0;
        while(userAccounts.get(index).ID != account.ID)
        {
            index++;
        }
        switch(currency){
            case "USD":
                //Allows to withdraw if the funds are more then what wants to be withdrew
                if(userAccounts.get(index).funds >= toWithdraw)
                {
                    amount = (userAccounts.get(index).funds) - (toWithdraw);
                    amount = Math.round (amount * 100.00) / 100.00;
                    userAccounts.get(index).funds = amount;

                }
                break;
            case "EURO":
                toWithdraw = toWithdraw / 0.88;
                if(userAccounts.get(index).funds >= toWithdraw)
                {
                    userAccounts.get(index).funds = userAccounts.get(index).funds - toWithdraw;

                }
                break;
            case "YUAN":
                toWithdraw = toWithdraw / 6.47;
                if(userAccounts.get(index).funds >= toWithdraw)
                {
                    userAccounts.get(index).funds = userAccounts.get(index).funds - toWithdraw;

                }
                break;
            default:
        }

        ModelEvent current = new ModelEvent(this, 1, "",  userAccounts.get(index), userAccounts.get(index).funds);
        notifyChanged(current);


    }


}
