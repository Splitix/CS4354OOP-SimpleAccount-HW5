package View;

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
 * Displays the 2nd windows after the user selects what currency they want to edit in.
 */
public class AdjustView extends JFrameView
{
    public static final String DEPOSIT = "Deposit";
    public static final String WITHDRAW = "Withdraw";
    public static final String EXIT = "Exit";
    public static final double EURO = .88;
    public static final double YUAN = 6.47;


    private JLabel available = new JLabel("Available Funds: ");
    private JLabel enterAmount = new JLabel("Enter Amount: ");
    public JTextField amount = new JTextField(25);
    public JFormattedTextField funds;
    public Account account;

    private JPanel layout = new JPanel();
    public JOptionPane warning = new JOptionPane();

    public AdjustView(AccountModel model, FundsController controller, Account account)
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

        amount.setText("0");
        layout.add(available, BorderLayout.WEST);
        layout.add(funds, BorderLayout.WEST);
        layout.add(enterAmount, BorderLayout.EAST);
        layout.add(amount, BorderLayout.EAST);
        this.getContentPane().add(layout, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        Handler handler = new Handler();

        JButton deposit = new JButton(DEPOSIT);
        JButton withdraw = new JButton(WITHDRAW);
        JButton exit = new JButton(EXIT);

        deposit.addActionListener(handler);
        withdraw.addActionListener(handler);
        exit.addActionListener(handler);

        buttonPanel.setLayout(new GridLayout(4, 1, 2, 2));
        this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(deposit, null);
        buttonPanel.add(withdraw, null);
        buttonPanel.add(exit, null);
        pack();
    }

    public void modelChanged(ModelEvent event)
    {
        String msg = "";
        double value;
        if(account.ID == event.getAccount().ID)
        {
            if(((FundsController)getController()).currency.equals("USD"))
            {
                msg = event.getAccount().funds + "";
            }
            else if (((FundsController)getController()).currency == "EURO")
            {
                msg = (event.getAccount().funds * EURO) + "";
            }
            else if (((FundsController)getController()).currency == "YUAN")
            {
                msg = (event.getAccount().funds * YUAN) + "";
            }

            value = Double.parseDouble(msg);
            funds.setValue(value);
        }
    }

    class Handler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            ((FundsController)getController()).operation(e.getActionCommand());
        }
    }
}
