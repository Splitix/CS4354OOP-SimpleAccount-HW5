package Controller;

import Model.Account;
import Model.AccountModel;
import View.JFrameView;
import View.MainView;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by Joshua Galindo on 4/12/16.
 * Controller used to redirect button clicks to the correct area.
 */

public class MainController extends AbstractController {

    public static ArrayList<Account> userAccounts = new ArrayList<>();
    public static Account accountHolder;
    public static String fileLoc;

    public MainController(ArrayList<Account> accountList, String fileName){
        userAccounts = accountList;
        fileLoc = fileName;
        setModel(new AccountModel());
        ((AccountModel)getModel()).userAccounts = userAccounts;
        setView(new MainView((AccountModel)getModel(), this));
        ((JFrameView)getView()).setVisible(true);
    }

    public void operation(String option, String fileName)
    {
        if(option.equals(MainView.E_USD))
        {
            new FundsController((AccountModel)getModel(), accountHolder, "USD");
        }
        else if(option.equals(MainView.E_EURO))
        {
            new FundsController((AccountModel)getModel(), accountHolder, "EURO");
        }
        else if(option.equals(MainView.E_YUAN))
        {
            new FundsController((AccountModel)getModel(), accountHolder, "YUAN");
        }
        else if(option.equals(MainView.DEP_AGENT))
        {
            //Code for Deposit agent
            System.out.println("Account: " + accountHolder.name);
            new AgentController((AccountModel)getModel(), accountHolder, "DEPOSIT_AGENT");
        }
        else if(option.equals(MainView.WITH_AGENT))
        {
            //Code for deposit agent
            new AgentController((AccountModel)getModel(), accountHolder, "WITHDRAW_AGENT");
        }
        else if(option.equals(MainView.SAVE))
        {
            writeToFile(fileName);
        }
        else if(option.equals(MainView.EXIT))
        {
            writeToFile(fileName);
            System.exit(0);

        }

    }

    public void writeToFile(String fileLoc)
    {
        PrintWriter writer;
        try {
            writer = new PrintWriter(fileLoc);
            for(int i = 0; i < userAccounts.size(); i++)
            {
                writer.println(userAccounts.get(i).ID + "," + userAccounts.get(i).name + "," +
                        userAccounts.get(i).funds);
            }
            writer.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void setCurrentAccount(int acc)
    {
        accountHolder = userAccounts.get(acc);
    }
}
