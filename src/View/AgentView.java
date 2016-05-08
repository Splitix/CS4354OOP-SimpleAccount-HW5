package View;

import Controller.AgentController;
import Model.Account;
import Model.AccountModel;
import Model.ModelEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Random;

/**
 * Created by Splitix on 5/6/16.
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

    public int option;
    Random value;


    public AgentView(AccountModel model, AgentController controller, int option, Account account, int buttonOption)
    {

        super(model,controller);

        NumberFormat format = NumberFormat.getNumberInstance();
        format.setMinimumFractionDigits(2);
        format.setMaximumFractionDigits(2);

        funds = new JFormattedTextField(format);

        this.account = account;
        layout.setLayout(new GridLayout(4, 2, 2, 2));

        value = new Random();
        agentID.setText(Integer.toString(value.nextInt(100000))); //create random AgentID to auto populate
        funds.setValue(0);
        funds.setEditable(false);

        amount.setText("0");
        operations.setText("1.0");
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

        if(buttonOption == 0)
        {
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
        String msg = event.getAccount() + "";
    }

    class Handler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            ((AgentController)getController()).operation(e.getActionCommand());
        }
    }
}
