package Controller;

import Model.Account;
import Model.AccountModel;
import View.AdjustView;
import View.JFrameView;

import javax.swing.*;

/**
 * Created by Joshua Galindo on 4/14/16.
 * Controller that converts the funds into the correct currency.
 */
public class FundsController extends AbstractController
{
    public String currency;
    public double amount;

    public FundsController(AccountModel model, Account account, String currency)
    {
        setModel(model);
        setView(new AdjustView((AccountModel)getModel(), this, account));
        ((JFrameView)getView()).setVisible(true);

        this.currency = currency;

        if(currency.equals("USD"))
        {
            amount = account.funds;
            amount = Math.round (amount * 100.00) / 100.00;
            ((AdjustView)getView()).funds.setValue(amount);
            ((AdjustView)getView()).setTitle(account.ID + account.name + "  USD");
        }
        else if(currency.equals("EURO"))
        {
            amount = account.funds * .88;
            amount = Math.round (amount * 100.00) / 100.00;
            ((AdjustView)getView()).funds.setValue(amount);
            ((AdjustView)getView()).setTitle(account.ID + account.name + "  Euros");


        }
        else if(currency.equals("YUAN"))
        {
            amount = account.funds * 6.47;
            amount = Math.round (amount * 100.00) / 100.00;
            ((AdjustView)getView()).funds.setValue(amount);
            ((AdjustView)getView()).setTitle(account.ID + account.name + "  Yuan");
        }

    }

    public void operation(String option)
    {
        if(option.equals(AdjustView.EXIT))
        {
            ((JFrameView)getView()).dispose();
        }
        else if(option.equals(AdjustView.DEPOSIT))
        {
            String deposit = ((AdjustView)getView()).amount.getText();


            //Makes sure a positive number is entered
            if(deposit.matches("-?\\d+(\\.\\d+)?"))
            {

                Double toDeposit = Double.valueOf(deposit);
                if(toDeposit > 1){
                    ((AccountModel)getModel()).deposit(toDeposit, currency, ((AdjustView)getView()).account);
                    ((AdjustView)getView()).amount.setText("0");
                }
                else{
                    ((AdjustView)getView()).warning.showMessageDialog((AdjustView)getView(),
                            "You can only enter numbers greater than 1.",
                            "A number was not entered.",
                            JOptionPane.WARNING_MESSAGE);
                    ((AdjustView)getView()).amount.setText("0");
                }

            }
            else
            {
                ((AdjustView)getView()).warning.showMessageDialog((AdjustView)getView(),
                        "You can only enter numbers.",
                        "You didn't enter a number",
                        JOptionPane.WARNING_MESSAGE);
                ((AdjustView)getView()).amount.setText("0");
            }
        }
        else if(option.equals(AdjustView.WITHDRAW))
        {
            try
            {
                String withdraw = ((AdjustView)getView()).amount.getText();
                Double toWithdraw = Double.valueOf(withdraw);
                ((AccountModel)getModel()).withdraw(toWithdraw, currency, ((AdjustView)getView()).account);
                ((AdjustView)getView()).amount.setText("0");
            }
            catch(Exception error)
            {
                ((AdjustView)getView()).warning.showMessageDialog((AdjustView)getView(),
                        "You can only enter numbers.",
                        "A number was not entered.",
                        JOptionPane.WARNING_MESSAGE);
                ((AdjustView)getView()).amount.setText("0");
            }
        }
    }
}
