package View;

import Controller.AgentController;
import Controller.FundsController;
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
public class AgentView extends JFrameView
{
    public static final String EXIT = "Exit";
    public static String START_DEPO = "Start Deposit Agent";
    public static String START_WITH = "Start Withdraw Agent";

    private JLabel enterAgentID = new JLabel("Agent ID: ");
    private JLabel enterAmount = new JLabel("Amount in $: ");
    private JLabel enterOperations = new JLabel("Operations per second: ");
    public JTextField agentID = new JTextField(25);
    public JTextField amount = new JTextField(25);
    public JTextField operations = new JTextField(25);
    public JFormattedTextField funds;
    public Account account;

    private JPanel layout = new JPanel();
    public JOptionPane warning = new JOptionPane();

    public AgentView(AccountModel model, AgentController controller, Account account, String currency)
    {
        super(model,controller);

        NumberFormat format = NumberFormat.getNumberInstance();
        format.setMinimumFractionDigits(2);
        format.setMaximumFractionDigits(2);

        funds = new JFormattedTextField(format);

        this.account = account;
        layout.setLayout(new GridLayout(4, 2, 2, 2));

        funds.setValue(0);
        funds.setEditable(false);

        amount.setText("500");
        layout.add(enterAgentID, BorderLayout.WEST);
        layout.add(agentID, BorderLayout.WEST);
        layout.add(enterAmount, BorderLayout.EAST);
        layout.add(amount, BorderLayout.EAST);
        layout.add(enterOperations, BorderLayout.WEST);
        layout.add(operations, BorderLayout.WEST);
        this.getContentPane().add(layout, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        Handler handler = new Handler();
        JButton start;

        if(currency.equals("DEPOSIT_AGENT"))
        {
            setTitle(account.name + " / " + "Deposit Agent");
            start = new JButton(START_DEPO);
        }
        else
        {
            start = new JButton(START_WITH);
        }

        JButton exit = new JButton(EXIT);

        start.addActionListener(handler);
        exit.addActionListener(handler);

        buttonPanel.setLayout(new GridLayout(4, 1, 2, 2));
        this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(start, null);
        buttonPanel.add(exit, null);
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
            ((AgentController)getController()).operation(e.getActionCommand());
        }
    }
}
