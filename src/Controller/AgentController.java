package Controller;

import Model.Account;
import Model.AccountModel;
import View.AdjustView;
import View.AgentView;
import View.JFrameView;

import javax.swing.*;

/**
 * Created by Splitix on 5/6/16.
 */
public class AgentController extends AbstractController
{
    public String currency;
    public double amount;

    public AgentController(AccountModel model, Account account, String currency)
    {
        setModel(model);
        setView(new AgentView((AccountModel)getModel(), this, account));
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
        if(option.equals(AgentView.EXIT))
        {
            ((JFrameView)getView()).dispose();
        }
        else if(option.equals(AgentView.START_DEPO))
        {
            String deposit = ((AdjustView)getView()).amount.getText();

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