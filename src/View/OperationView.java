package View;

import Controller.AgentController;
import Model.Account;
import Model.AccountModel;
import Model.AgentModel;
import Model.ModelEvent;
import sun.management.Agent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

/**
 * Created by Splitix on 5/6/16.
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
    public JButton dismissAgent;
    public Account account;



    private JPanel layout = new JPanel();
    public JOptionPane warning = new JOptionPane();

    AgentModel current;


    public OperationView(AccountModel model, AgentController controller, AgentModel window, Account account)
    {

        super(model,controller);
        NumberFormat format = NumberFormat.getNumberInstance();
        format.setMinimumFractionDigits(2);
        format.setMaximumFractionDigits(2);


        current = window;

        this.account = account;
        layout.setLayout(new GridLayout(10, 2, 2, 2));
        //this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

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

        //Show running status
        if(window.going)
        {
            state.setText("Running");
        }
        else
        {
            state.setText("Stopped");
        }

        JPanel buttonPanel = new JPanel();
        Handler handler = new Handler();

        JButton stopAgent = new JButton(STOP);
        dismissAgent = new JButton(DISMISS);



        stopAgent.addActionListener(handler);
        dismissAgent.addActionListener(handler);
        dismissAgent.setEnabled(false);

        buttonPanel.setLayout(new GridLayout(6, 2, 2, 2));
        this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(stopAgent, null);
        buttonPanel.add(dismissAgent, null);

        pack();

    }
    public void modelChanged(ModelEvent event)
    {
        if(current.going)
        {
            state.setText("Running");
        }
        else
        {
            state.setText("Stopped");
        }
        amount.setText(String.valueOf(account.funds));
        operations.setText(String.valueOf(current.operations));
        amountTransferred.setText(String.valueOf(current.moneyTransferred));
        operationsComplete.setText(String.valueOf(current.opsFinished));
    }
    class Handler implements ActionListener
    {
        // Event handling is handled locally
        public void actionPerformed(ActionEvent e)
        {
            ((AgentController)getController()).operation(e.getActionCommand());
        }
    }
}
