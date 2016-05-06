package View;

import Controller.AgentController;
import Controller.FundsController;
import Controller.OperationController;
import Model.Account;
import Model.AccountModel;
import Model.ModelEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

/**
 * Created by Joshua Galindo on 4/14/16.
 * Displays the view for the agent. The Agent has permission to deposit/withdraw.
 */
public class OperationView extends JFrameView
{
    public static final String STOP = "Stop Agent";
    public static final String DISMISS = "Dismiss Agent";

    private JLabel amountLabel = new JLabel("Amount in $: ");
    private JLabel operationsLabel = new JLabel("Operations per second: ");
    private JLabel stateLabel = new JLabel("State");
    private JLabel amountTransferLabel = new JLabel("Amount in $ transferred");
    private JLabel opCompleteLabel = new JLabel("Operations completed");
    public JTextField amount = new JTextField(25);
    public JTextField operations = new JTextField(25);
    public JTextField state = new JTextField(25);
    public JTextField amountTransferred = new JTextField(25);
    public JTextField operationsComplete = new JTextField(25);
    public Account account;


    private JPanel layout = new JPanel();
    public JOptionPane warning = new JOptionPane();

    public OperationView(AccountModel model, OperationController controller, Account account)
    {
        super(model,controller);
        NumberFormat format = NumberFormat.getNumberInstance();
        format.setMinimumFractionDigits(2);
        format.setMaximumFractionDigits(2);

        this.account = account;
        layout.setLayout(new GridLayout(4, 2, 2, 2));

        amount.setEditable(false);
        operations.setEditable(false);
        state.setEditable(false);
        amountTransferred.setEditable(false);
        operationsComplete.setEditable(false);

        layout.add(amountLabel, null);
        layout.add(amount, null);
        layout.add(operationsLabel, null);
        layout.add(operations, null);
        layout.add(stateLabel, null);
        layout.add(state, null);
        layout.add(amountTransferLabel, null);
        layout.add(amountTransferred, null);
        layout.add(opCompleteLabel, null);
        layout.add(operationsComplete, null);

        this.getContentPane().add(layout, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        Handler handler = new Handler();

        JButton stopAgent = new JButton(STOP);
        JButton dismissAgent = new JButton(DISMISS);

        stopAgent.addActionListener(handler);
        dismissAgent.addActionListener(handler);

        buttonPanel.setLayout(new GridLayout(4, 1, 2, 2));
        this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(stopAgent, null);
        buttonPanel.add(dismissAgent, null);
        pack();
    }

    public void modelChanged(ModelEvent event)
    {
        //model changed function
    }

    class Handler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            ((OperationController)getController()).operation(e.getActionCommand());
        }
    }
}
