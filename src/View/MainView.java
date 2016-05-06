package View;


import Controller.MainController;
import Model.Account;
import Model.AccountModel;
import Model.ModelEvent;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.lang.String;

/**
 * Created by Joshua Galindo on 4/12/16.
 * Displays the main windows view. Displays the buttons for editing money and save/exit program
 */

public class MainView extends JFrameView {

    public static final String E_USD = "Edit in USD";
    public static final String E_EURO = "Edit in Euro";
    public static final String E_YUAN = "Edit in Yuan";
    public static final String DEP_AGENT = "Create Deposit Agent";
    public static final String WITH_AGENT = "Create Withdraw Agent";
    public static final String SAVE = "Save";
    public static final String EXIT = "Exit";
    public static String fileLoc = "src/users.txt";;

    public static ArrayList<Account> userAccounts = new ArrayList<>();

    public JComboBox dropDownUsers;

    public MainView(AccountModel model, MainController controller){
        super(model, controller);

        String[] userNames = new String[userAccounts.size()];

        for(int i = 0; i < userAccounts.size(); i++)
        {
            String id = String.valueOf(userAccounts.get(i).ID);
            String names = userAccounts.get(i).name;
            userNames[i] = (id + "  " + names);
        }

        dropDownUsers = new JComboBox(userNames);
        dropDownUsers.setSelectedIndex(0);
        controller.accountHolder = userAccounts.get(0);
        this.getContentPane().add(dropDownUsers, BorderLayout.NORTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel buttonPanel = new JPanel();
        Handler handler = new Handler();
        CBHandler cbhandler = new CBHandler();
        dropDownUsers.addActionListener(cbhandler);

        JButton editUSD = new JButton(E_USD);
        JButton editEURO = new JButton(E_EURO);
        JButton editYUAN = new JButton(E_YUAN);
        JButton depAgent = new JButton(DEP_AGENT);
        JButton withAgent = new JButton(WITH_AGENT);
        JButton save = new JButton(SAVE);
        JButton exit = new JButton(EXIT);


        editUSD.addActionListener(handler);
        editEURO.addActionListener(handler);
        editYUAN.addActionListener(handler);
        depAgent.addActionListener(handler);
        withAgent.addActionListener(handler);
        save.addActionListener(handler);
        exit.addActionListener(handler);


        buttonPanel.setLayout(new GridLayout(8, 1, 0, 10));
        this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(editUSD, null);
        buttonPanel.add(editEURO, null);
        buttonPanel.add(editYUAN, null);
        buttonPanel.add(depAgent, null);
        buttonPanel.add(withAgent, null);
        buttonPanel.add(save, null);
        buttonPanel.add(exit, null);


        pack();


    }

    // Now implement the necessary event handling code
    public void modelChanged(ModelEvent event) {
        String msg = event.getAccount() + "";
    }

    class Handler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            ((MainController)getController()).operation(e.getActionCommand(), fileLoc);
        }
    }

    class CBHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            JComboBox cb = (JComboBox)e.getSource();
            int acc = cb.getSelectedIndex();
            ((MainController)getController()).setCurrentAccount(acc);

        }
    }

    public static void main(String[] args) {

        String line = "";
        String wordArray[];


        if(args.length > 0)
        {
            fileLoc = args[0];
        }

        try
        {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileLoc);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {

                wordArray = line.split(",");

                if (wordArray != null) {
                    ArrayList<String> temp = new ArrayList<>();
                    for(int i = 4; i < wordArray.length; i++){
                        temp.add(wordArray[i]);
                    }
                    int id = Integer.parseInt(wordArray[0]);
                    double funds = Double.parseDouble(wordArray[2]);
                    userAccounts.add(new Account(id, wordArray[1], funds));
                }
            }

            // Always close files.
            bufferedReader.close();
        }
        catch (Exception e)
        {
            System.err.println("Error: " + e.getMessage());
        }
        new MainController(userAccounts, fileLoc);





    }
}
