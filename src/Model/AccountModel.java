package Model;

import java.util.ArrayList;

/**
 * Created by Joshua Galindo on 4/14/16.
 * Withdraws or deposits money into the correct users account.
 */

public class AccountModel extends AbstractModel {
    public ArrayList<Account> userAccounts = new ArrayList<>();
    public ArrayList<AgentModel> listOfAgents = new ArrayList<>();
    boolean runningAgent = false;
    boolean windowExists = false;
    public double amount;
    public int AgentId;

    public AgentModel AgentWindow(int AgentID, double amount, double ops, int type, Account accHolder)
    {
        AgentModel window;
        AgentId = AgentID;

        //Check to see if an agent is already in use.
        for (int i = 0; i < listOfAgents.size(); i++) {

            if (listOfAgents.get(i).ID == AgentID) {
                windowExists = true;
            }
        }

        // If a agent doesnt exist then allow one to be created
        if (!windowExists) {
            if (type == 0)
            {
                window = new AgentModel(AgentID, amount, ops, type, accHolder, this);
                listOfAgents.add(window);
                window.start();
            } else
            {
                window = new AgentModel(AgentID, amount, ops, type, accHolder, this);
                listOfAgents.add(window);
                window.start();
            }
        }
        //If one exists then do nothing
        else
        {
            window = null;
        }
        return window;
    }

    public void startWindow(AgentModel window) {
        if (window.option == 0) // 0 is deposit
        {
            boolean passed;
            passed = deposit(window.amount, "USD", window.account);
            while (!passed) {
                runningAgent = false;
            }
            runningAgent = true;
        } else if (window.option == 1)// 1 is withdraw
        {
            withdraw(window.amount, "USD", window.account);
            runningAgent = true;
        }
    }


    public boolean deposit(double toDeposit, String currency, Account account) {
        int index = 0;

        boolean depositHappened = false;

        while (userAccounts.get(index).ID != account.ID) {
            index++;
        }

        switch (currency) {
            case "USD":
                amount = userAccounts.get(index).funds + toDeposit;
                amount = Math.round(amount * 100.00) / 100.00;
                userAccounts.get(index).funds = amount;
                depositHappened = true;
                break;
            case "EURO":
                amount = (userAccounts.get(index).funds) + (toDeposit / .88);
                amount = Math.round(amount * 100.00) / 100.00;
                userAccounts.get(index).funds = amount;
                break;
            case "YUAN":
                amount = (userAccounts.get(index).funds) + (toDeposit / 6.47);
                amount = Math.round(amount * 100.00) / 100.00;
                userAccounts.get(index).funds = amount;
                break;
            default:
        }

        ModelEvent current = new ModelEvent(this, 1, "", userAccounts.get(index), userAccounts.get(index).funds);
        notifyChanged(current);

        return depositHappened;
    }

    public void withdraw(double toWithdraw, String currency, Account account) {

        int index = 0;
        while (userAccounts.get(index).ID != account.ID) {
            index++;
        }
        switch (currency) {
            case "USD":
                //Allows to withdraw if the funds are more then what wants to be withdrew
                if (userAccounts.get(index).funds >= toWithdraw) {
                    amount = (userAccounts.get(index).funds) - (toWithdraw);
                    amount = Math.round(amount * 100.00) / 100.00;
                    userAccounts.get(index).funds = amount;

                }
                break;
            case "EURO":
                toWithdraw = toWithdraw / 0.88;
                if (userAccounts.get(index).funds >= toWithdraw) {
                    userAccounts.get(index).funds = userAccounts.get(index).funds - toWithdraw;

                }
                break;
            case "YUAN":
                toWithdraw = toWithdraw / 6.47;
                if (userAccounts.get(index).funds >= toWithdraw) {
                    userAccounts.get(index).funds = userAccounts.get(index).funds - toWithdraw;

                }
                break;
            default:
        }

        ModelEvent current = new ModelEvent(this, 1, "", userAccounts.get(index), userAccounts.get(index).funds);
        notifyChanged(current);


    }

    public void stopWindow(AgentModel window)
    {
        for (int i = 0; i < listOfAgents.size(); i++) {

            if (listOfAgents.get(i).ID == AgentId) {
                listOfAgents.remove(i);
            }
        }
        window.going = false;
    }

}
