package Controller;

import Model.Account;
import Model.AccountModel;
import View.AdjustView;
import View.AgentView;
import View.JFrameView;
import View.OperationView;

import javax.swing.*;

/**
 * Created by Joshua Galindo on 4/14/16.
 * Controller that converts the funds into the correct currency.
 */
public class OperationController extends AbstractController
{

    public String currency;
    public double amount;

    public OperationController(AccountModel model, Account account, String currency, String amt)
    {
        setModel(model);

        this.currency = currency;

        if(currency.equals("START_D"))
        {
            setView(new OperationView((AccountModel)getModel(), this, account, "DEPOSIT"));
            ((JFrameView)getView()).setVisible(true);
            System.out.println("Account: " + account.name);

            double opFunds = Double.parseDouble(amt);

            ((OperationView)getView()).amount.setText(amt);
//            while(account.funds >= 0){
//
//                account.funds -= opFunds;
//                //sleep(1000);
//            }
//            amount = account.funds;
//            amount = Math.round (amount * 100.00) / 100.00;
//            ((OperationView)getView()).funds.setValue(amount);
//            ((OperationView)getView()).setTitle(account.ID + account.name + "  USD");
        }
        else if(currency.equals("START_W"))
        {
//            setView(new OperationView((AccountModel)getModel(), this, account, "WITHDRAW"));
//            ((JFrameView)getView()).setVisible(true);
//            //System.out.println("Amt: " + amt);
//            System.out.println("Account: " + account.name);
//            String amt = ((AgentView)getView()).amount.getText();
//            double opFunds = Double.parseDouble(amt);
//
//
//            ((OperationView)getView()).amount.setText(amt);
//            while(account.funds > 0){
//
//                account.funds += opFunds;
//                //sleep(1000);
//            }
        }
    }

    public void operation(String option)
    {
        if(option.equals(OperationView.DISMISS))
        {
            ((JFrameView)getView()).dispose();
        }
        else if(option.equals(OperationView.STOP))
        {
            String deposit = ((AdjustView)getView()).amount.getText();

        }

    }
}
