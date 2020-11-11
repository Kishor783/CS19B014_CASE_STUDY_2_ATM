package ATM_CASESTUDY;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.CardLayout;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

public class Deposit extends JFrame {

	private JPanel contentPane;
	JLayeredPane layeredPane;
	
	AccountHolder ah;  // To store details of present user of atm
	int amountS;  // To store amount deposited in savings
	int amountC;  // To store amount deposited in current
	
	Bank b = new Bank(); // to get conditions and details for transaction
	
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JPanel SavingD;

	public void switchJpanels(JPanel panel) { // to switch between different panels
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}
	
	public Deposit(int accNum) {
		ah = new AccountHolder(accNum);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 845, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 833, 483);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		layeredPane.add(panel, "name_94111691298200");
		
		JPanel CurrentD = new JPanel();
		CurrentD.setLayout(null);
		layeredPane.add(CurrentD, "name_94141534908400");
		
		JPanel SavingD = new JPanel();
		SavingD.setLayout(null);
		layeredPane.add(SavingD, "name_94129342664300");
		
		JPanel Bill = new JPanel();
		Bill.setLayout(null);
		layeredPane.add(Bill, "name_129676200427400");
		
		JButton button = new JButton("SAVINGS");   // to deposit in saving account
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ah.isSaving() == 1)
					switchJpanels(SavingD);
				else
					JOptionPane.showMessageDialog(null, "Sorry, You do Not have Savings Acoount");
			}
		});
		button.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		button.setBounds(619, 318, 145, 37);
		panel.add(button);
		
		JLabel label = new JLabel("Please Select Account Type");
		label.setFont(new Font("Book Antiqua", Font.BOLD, 22));
		label.setBounds(255, 162, 416, 37);
		panel.add(label);
		
		JButton button_1 = new JButton("CURRENT");  // to deposit in current account
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ah.isCurrent() == 1)
					switchJpanels(CurrentD);
				else
					JOptionPane.showMessageDialog(null, "Sorry, You do Not have Current Acoount");
			}
		});
		button_1.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		button_1.setBounds(619, 365, 145, 37);
		panel.add(button_1);
		 
		JButton btnCancel = new JButton("CANCEL");   // To go t the initial page
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ATM atm = new ATM();
				atm.getFrame().setVisible(true);
			}
		});
		btnCancel.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		btnCancel.setBounds(619, 412, 145, 37);
		panel.add(btnCancel);
		
		JLabel labelBankName = new JLabel(b.getBankName());
		labelBankName.setFont(new Font("Modern No. 20", Font.PLAIN, 25));
		labelBankName.setBounds(313, 39, 275, 30);
		panel.add(labelBankName);
		
		JLabel label_1 = new JLabel("Do not Deposit more than 200 Notes.");
		label_1.setFont(new Font("Book Antiqua", Font.BOLD, 22));
		label_1.setBounds(208, 91, 388, 41);
		SavingD.add(label_1);
		
		JLabel label_2 = new JLabel("\n You can deposit upto 50000 only.");
		label_2.setFont(new Font("Book Antiqua", Font.BOLD, 22));
		label_2.setBounds(218, 128, 356, 47);
		SavingD.add(label_2);
		
		JLabel label_3 = new JLabel("Acceptable Denominations");
		label_3.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		label_3.setBounds(50, 240, 231, 22);
		SavingD.add(label_3);
		
		JLabel label_4 = new JLabel("2000");
		label_4.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		label_4.setBounds(50, 279, 113, 22);
		SavingD.add(label_4);
		
		JLabel label_5 = new JLabel("500");
		label_5.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		label_5.setBounds(50, 311, 113, 22);
		SavingD.add(label_5);
		
		JLabel label_6 = new JLabel("100");
		label_6.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		label_6.setBounds(50, 346, 113, 22);
		SavingD.add(label_6);
		
		JLabel label_7 = new JLabel("Number of Notes");
		label_7.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		label_7.setBounds(359, 240, 173, 22);
		SavingD.add(label_7);
		
		JLabel label_8 = new JLabel("|");
		label_8.setFont(new Font("DialogInput", Font.PLAIN, 50));
		label_8.setBounds(282, 228, 30, 54);
		SavingD.add(label_8);
		
		JLabel label_9 = new JLabel("|");
		label_9.setFont(new Font("DialogInput", Font.PLAIN, 50));
		label_9.setBounds(282, 261, 30, 54);
		SavingD.add(label_9);
		
		JLabel label_10 = new JLabel("|");
		label_10.setFont(new Font("DialogInput", Font.PLAIN, 50));
		label_10.setBounds(282, 279, 30, 54);
		SavingD.add(label_10);
		
		JLabel label_11 = new JLabel("|");
		label_11.setFont(new Font("DialogInput", Font.PLAIN, 50));
		label_11.setBounds(282, 335, 30, 54);
		SavingD.add(label_11);
		
		JLabel label_12 = new JLabel("|");
		label_12.setFont(new Font("DialogInput", Font.PLAIN, 50));
		label_12.setBounds(282, 311, 30, 54);
		SavingD.add(label_12);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(362, 283, 96, 19);
		SavingD.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(362, 315, 96, 19);
		SavingD.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(362, 350, 96, 19);
		SavingD.add(textField_2);
		
		JButton button_3 = new JButton("Cancel");  // To go t the initial page
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ATM atm = new ATM();
				atm.getFrame().setVisible(true);
			}
		});
		button_3.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		button_3.setBounds(601, 386, 128, 30);
		SavingD.add(button_3);
		
		
		JLabel label_13 = new JLabel("Do not Deposit more than 200 Notes.");
		label_13.setFont(new Font("Book Antiqua", Font.BOLD, 22));
		label_13.setBounds(190, 97, 388, 41);
		CurrentD.add(label_13);
		
		JLabel label_14 = new JLabel("\n You can deposit upto 50000 only.");
		label_14.setFont(new Font("Book Antiqua", Font.BOLD, 22));
		label_14.setBounds(209, 148, 356, 47);
		CurrentD.add(label_14);
		
		JLabel label_15 = new JLabel("Acceptable Denominations");
		label_15.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		label_15.setBounds(57, 279, 231, 22);
		CurrentD.add(label_15);
		
		JLabel label_16 = new JLabel("2000");
		label_16.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		label_16.setBounds(57, 311, 113, 22);
		CurrentD.add(label_16);
		
		JLabel label_17 = new JLabel("500");
		label_17.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		label_17.setBounds(57, 346, 113, 22);
		CurrentD.add(label_17);
		
		JLabel label_18 = new JLabel("100");
		label_18.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		label_18.setBounds(57, 378, 113, 22);
		CurrentD.add(label_18);
		
		JLabel label_19 = new JLabel("Number of Notes");
		label_19.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		label_19.setBounds(340, 279, 173, 22);
		CurrentD.add(label_19);
		
		JLabel label_20 = new JLabel("|");
		label_20.setFont(new Font("DialogInput", Font.PLAIN, 50));
		label_20.setBounds(285, 263, 30, 54);
		CurrentD.add(label_20);
		
		JLabel label_21 = new JLabel("|");
		label_21.setFont(new Font("DialogInput", Font.PLAIN, 50));
		label_21.setBounds(285, 286, 30, 81);
		CurrentD.add(label_21);
		
		JLabel label_22 = new JLabel("|");
		label_22.setFont(new Font("DialogInput", Font.PLAIN, 50));
		label_22.setBounds(285, 366, 30, 54);
		CurrentD.add(label_22);
		
		JLabel label_23 = new JLabel("|");
		label_23.setFont(new Font("DialogInput", Font.PLAIN, 50));
		label_23.setBounds(285, 327, 30, 54);
		CurrentD.add(label_23);
		
		JLabel label_24 = new JLabel("|");
		label_24.setFont(new Font("DialogInput", Font.PLAIN, 50));
		label_24.setBounds(285, 350, 30, 54);
		CurrentD.add(label_24);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(360, 315, 101, 19);
		CurrentD.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(360, 350, 101, 19);
		CurrentD.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(360, 382, 101, 19);
		CurrentD.add(textField_5);
		
		JButton button_5 = new JButton("Cancel"); // To go t the initial page
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ATM atm = new ATM();
				atm.getFrame().setVisible(true);
			}
		});
		button_5.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		button_5.setBounds(621, 414, 128, 30);
		CurrentD.add(button_5);
		
		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("Courier New", Font.PLAIN, 10));
		textPane.setBounds(133, 104, 275, 308);
		Bill.add(textPane);
		
		JLabel lblReceipt = new JLabel("Receipt");
		lblReceipt.setFont(new Font("Book Antiqua", Font.BOLD, 22));
		lblReceipt.setBounds(138, 53, 263, 37);
		Bill.add(lblReceipt);
		
		JButton newTransaction = new JButton("New Transaction");  // To go t the initial page
		newTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ATM atm = new ATM();
				atm.getFrame().setVisible(true);
			}
		});
		newTransaction.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		newTransaction.setBounds(599, 379, 184, 37);
		Bill.add(newTransaction);
		
		DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		DateTimeFormatter dateOnly = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();
		
		JButton button_4 = new JButton("Confirm");
		button_4.addActionListener(new ActionListener() {  // to deposit money in saving account if all conditions are satisfied
			private boolean z = false;

			public void actionPerformed(ActionEvent e) {
				try {
					int check = 0;
					try {
						int a1 = Integer.parseInt(textField.getText());
						int a2 = Integer.parseInt(textField_1.getText());
						int a3 = Integer.parseInt(textField_2.getText());
						
						if(a1 < 0 || a2 < 0 || a3 < 0 || (a1 + a2 + a3) <= 0) {
							check = 1;
						}
						
					} catch(Exception c) {
						z  =true;
					}
					
					if(check == 0) {
					int _2000 = Integer.parseInt(textField.getText());
					int _500  = Integer.parseInt(textField_1.getText());
					int _100  = Integer.parseInt(textField_2.getText());
					amountS = 2000*_2000 + 500*_500 + _100*100;
					int notes = _2000 + _500 + _100;
					int tc = 0;
					if(ah.getBankCode() != b.getBankCode()) {
						tc = b.getTransactionCharge();
					}
						if(amountS <= b.getmaxAmountD()) {
							if(notes <= b.getmaxNotes()) {
								ah.setCashS(ah.getCashS() + (double)amountS - tc);
								b.setTotalCash(b.getTotalCash() + amountS);
								textPane.setText("            "+b.getBankName()+"\n\n"
										+"Date\t  :  " + date.format(now)+"\n"
										+"Account No\t  :  XXX" + ah.getAccNum()%100 + "\n"
										+"Transaction\t  :  Deposit\n"
										+"Amount\t  :  " + amountS + "\n"
										+"ATM charge	 :  " + tc + "\n"
									    +"Account Type  :  Saving\n"
									    +"Balance\t  :  " + ah.getCashS() + "\n\n"
									    +"    Thank You for choosing us");
								b.addTransaction(ah.getAccNum(), date.format(now), "DEPOSIT", "Savings", amountS);
								switchJpanels(Bill);
								
							}else {
								JOptionPane.showMessageDialog(null,"Please insert notes less than "+ b.getmaxNotes());
							}
						}
						else {
							JOptionPane.showMessageDialog(null,"Please deposit money less than "+ b.getmaxAmountD());
						}
					}
					else {
						JOptionPane.showMessageDialog(null,"Please Enter valid Integer only");
					}
					
				} catch(Exception e7) {
					if(z)
						JOptionPane.showMessageDialog(null,"Please Enter valid Integer only");
					else
						JOptionPane.showMessageDialog(null,e7);
				}
			}
		});
		button_4.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		button_4.setBounds(601, 342, 128, 30);
		SavingD.add(button_4);
		
		JLabel labelBankName2 = new JLabel(b.getBankName());
		labelBankName2.setFont(new Font("Modern No. 20", Font.PLAIN, 24));
		labelBankName2.setBounds(299, 33, 335, 30);
		SavingD.add(labelBankName2);
		
		JButton button_6 = new JButton("Confirm");
		button_6.addActionListener(new ActionListener() {
			private boolean q = false;

			public void actionPerformed(ActionEvent e) {  // to deposit money in saving account if all conditions are satisfied
				int check = 0;
				try {
					try {
						int a1 = Integer.parseInt(textField_3.getText());
						int a2 = Integer.parseInt(textField_4.getText());
						int a3 = Integer.parseInt(textField_5.getText());
						
						if(a1 < 0 || a2 < 0 || a3 < 0 || (a1 + a2 + a3) <= 0) {
							check = 1;
						}
						
					} catch(Exception x) {
						q = true;
					}
				
					if(check == 0) {
					int _2000 = Integer.parseInt(textField_3.getText());
					int _500  = Integer.parseInt(textField_4.getText());
					int _100  = Integer.parseInt(textField_5.getText());
					amountC = 2000*_2000 + 500*_500 + _100*100;
					int notes = _2000 + _500 + _100;
					int tc = 0;
					if(ah.getBankCode() != b.getBankCode()) {
						tc = b.getTransactionCharge();
					}
						if(amountC <= b.getmaxAmountD()) {
							if(notes <= b.getmaxNotes()) {
								ah.setCashC(ah.getCashC() + (double)amountC - tc);
								b.setTotalCash(b.getTotalCash() + amountC);
								textPane.setText("            "+b.getBankName()+"\n\n"
										+"Date\t  :  " + date.format(now)+"\n"
										+"Account No\t  :  XXX" + ah.getAccNum()%100 + "\n"
										+"Transaction\t  :  Deposit\n"
										+"Amount\t  :  " + amountC + "\n"
										+"ATM charge\t  :  " + tc + "\n"
									    +"Account Type  :  Current\n"
									    +"Balance\t  :  " + ah.getCashC() + "\n\n"
									    +"    Thank You for choosing us");
								b.addTransaction(ah.getAccNum(), date.format(now), "DEPOSIT", "Current", amountC);
								switchJpanels(Bill);
								
							}else {
								JOptionPane.showMessageDialog(null,"Please insert notes less than "+ b.getmaxNotes());
							}
						}
						else {
							JOptionPane.showMessageDialog(null,"Please deposit money less than "+ b.getmaxAmountD());
						}
					}
					else {
						JOptionPane.showMessageDialog(null,"Please Enter valid Integer only");
					}
					
				} catch(Exception e7) {
					if(q)
						JOptionPane.showMessageDialog(null,"Please Enter valid Integer only");
					else
						JOptionPane.showMessageDialog(null,e7);
				}
			}
		});
		button_6.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		button_6.setBounds(621, 366, 128, 30);
		CurrentD.add(button_6);
		
		JLabel labelBankName1 = new JLabel(b.getBankName());
		labelBankName1.setFont(new Font("Modern No. 20", Font.PLAIN, 24));
		labelBankName1.setBounds(272, 31, 275, 30);
		CurrentD.add(labelBankName1);
		
	}
}
