package Controller;

import Model.Account;
import Model.AccountModel;
import Model.AgentModel;
import View.AdjustView;
import View.AgentView;
import View.JFrameView;
import View.OperationView;

import javax.swing.*;

/**
 * Created by Splitix on 5/6/16.
 */
public class AgentController extends AbstractController
{
    AgentModel window;
    Account account;
    public AgentController(AccountModel window, Account account, int option)
    {
        setModel(window);
        setView(new AgentView((AccountModel)getModel(), this, option, account, option));
        ((JFrameView)getView()).setVisible(true);

        if(option == 0)
        {
            ((AgentView)getView()).setTitle(account.name + " - Agent Deposit" );
        }
        else
        {
            ((AgentView)getView()).setTitle(account.name + " - Agent Withdraw");
        }
        this.account = account;
    }

    public void operation(String option)
    {
        if(option.equals(AgentView.START_DEPO))
        {
            try
            {
                int AgentID = Integer.parseInt(((AgentView)getView()).agentID.getText());
                double Amount = Double.parseDouble( ((AgentView)getView()).amount.getText());
                double ops = Double.parseDouble(((AgentView)getView()).operations.getText());
                int type = 0;
                window = ((AccountModel)getModel()).AgentWindow(AgentID, Amount, ops, type, account);
                if(window != null)
                {
                    ((AgentView)getView()).dispose();
                    setView(new OperationView((AccountModel)getModel(), this, window, account));
                    ((JFrameView)getView()).setVisible(true);

                    ((OperationView)getView()).setTitle("Deposit agent: " + window.ID + " for " + account.name);
                    ((OperationView)getView()).amount.setText(((AdjustView)getView()).funds.getText());
                }
                else
                {
                    ((AgentView)getView()).warning.showMessageDialog((AgentView)getView(),
                            "Agent already exists!",
                            "Agent exists",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
            catch(Exception name)
            {
                ((AgentView)getView()).warning.showMessageDialog((AgentView)getView(),
                        "Incorrect Data was Entered. Try again.",
                        "Incorrect Information",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
        else if(option.equals(AgentView.START_WITH))
        {
            try
            {
                int AgentID = Integer.parseInt(((AgentView)getView()).agentID.getText());
                double Amount = Double.parseDouble( ((AgentView)getView()).amount.getText());
                double ops = Double.parseDouble(((AgentView)getView()).operations.getText());
                int type = 1;
                window = ((AccountModel)getModel()).AgentWindow(AgentID, Amount, ops, type, account);
                if(window != null)
                {
                    ((AgentView)getView()).dispose();
                    setView(new OperationView((AccountModel)getModel(), this, window, account));
                    ((JFrameView)getView()).setVisible(true);

                    ((OperationView)getView()).setTitle("Withdraw agent: " + window.ID + " for " + account.name);
                }
                else
                {
                    ((AgentView)getView()).warning.showMessageDialog((AgentView)getView(),
                            "Agent already exists!",
                            "Agent",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
            catch(Exception name)
            {
                ((AgentView)getView()).warning.showMessageDialog((AgentView)getView(),
                        "Incorrect Data was Entered. Try again.",
                        "Incorrect Information",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
        else if(option.equals(OperationView.STOP))
        {
            ((AccountModel)getModel()).stopWindow(this.window);
            ((OperationView)getView()).dismissAgent.setEnabled(true);
            ((OperationView)getView()).state.setText("Stopped");
        }
        else if(option.equals(OperationView.DISMISS))
        {

            ((OperationView)getView()).dispose();
        }
        else if(option.equals(AgentView.EXIT)){
            ((AgentView)getView()).dispose();
        }

    }
}
