package ATM_CASESTUDY;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JTextPane;

public class Withdrawal extends JFrame {

	private JPanel contentPane;
	JLayeredPane layeredPane;
	private JTextField textField;
	private JTextField textField_1;
	
	AccountHolder ah; // To store details of present user of atm
	
	Bank b = new Bank(); // to get details related to transactions
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public int cashWithdrawnS; // To store cashWithdrwn from saving account
	public int cashWithdrawnC; // To store cashWithdrwn from current account
	private JTextField textField_2;

	
	public void switchJpanels(JPanel panel) { // to switch between different panels
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}
	
	public Withdrawal(int accNum) { 
		
		ah = new AccountHolder(accNum);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 831, 519);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, "name_5695617108300");
		layeredPane.setLayout(new CardLayout(0, 0));
		
		JPanel panel = new JPanel();
		layeredPane.add(panel, "name_7332261131300");
		panel.setLayout(null);
		
		JPanel SavingsW = new JPanel();
		layeredPane.add(SavingsW, "name_7337961905400");
		SavingsW.setLayout(null);
		
		JPanel Current = new JPanel();
		Current.setLayout(null);
		layeredPane.add(Current, "name_9114965185600");
		
		JPanel Bill = new JPanel();
		layeredPane.add(Bill, "name_128672953967100");
		Bill.setLayout(null);
		
		JLabel lblPleaseEnterAmount = new JLabel("Please Enter Amount");
		lblPleaseEnterAmount.setFont(new Font("Book Antiqua", Font.BOLD, 22));
		lblPleaseEnterAmount.setBounds(260, 182, 239, 37);
		SavingsW.add(lblPleaseEnterAmount);
		
		textField = new JTextField();
		textField.setBounds(296, 241, 139, 30);
		SavingsW.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("SAVINGS");    // to withdraw money through saving account
		btnNewButton_1.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ah.isSaving() == 1)
					switchJpanels(SavingsW);
				else
					JOptionPane.showMessageDialog(null, "Sorry, You do Not have a Saving Account");
			}
		});
		btnNewButton_1.setBounds(600, 298, 141, 37);
		panel.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Please Select Account Type");
		lblNewLabel.setFont(new Font("Book Antiqua", Font.PLAIN, 21));
		lblNewLabel.setBounds(239, 139, 326, 37);
		panel.add(lblNewLabel);
		
		JLabel label = new JLabel("Please Enter Amount"); // entering money to be withdrawn
		label.setFont(new Font("Book Antiqua", Font.BOLD, 22));
		label.setBounds(251, 168, 242, 37);
		Current.add(label);
		
		textField_1 = new JTextField("");
		textField_1.setColumns(10);
		textField_1.setBounds(282, 225, 139, 30);
		Current.add(textField_1);
		
		JButton btnCurrent = new JButton("CURRENT");   // to withdraw money through saving account
		btnCurrent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ah.isCurrent() == 1)
					switchJpanels(Current);
				else
					JOptionPane.showMessageDialog(null, "Sorry, You do Not have a Current Account");
				
			}
		});
		btnCurrent.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		btnCurrent.setBounds(600, 355, 141, 37);
		panel.add(btnCurrent);
		
		JButton btnCancel_2 = new JButton("CANCEL");
		btnCancel_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ATM atm = new ATM();
				atm.getFrame().setVisible(true);
			}
		});
		btnCancel_2.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		btnCancel_2.setBounds(600, 411, 141, 37);
		panel.add(btnCancel_2);
		
		JLabel label_1 = new JLabel(b.getBankName());
		label_1.setFont(new Font("Modern No. 20", Font.PLAIN, 24));
		label_1.setBounds(274, 34, 275, 30);
		panel.add(label_1);
		
		JButton btnCancel_1 = new JButton("CANCEL");  // To go to the initial page
		btnCancel_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ATM atm = new ATM();
				atm.getFrame().setVisible(true);
			}
		});
		btnCancel_1.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		btnCancel_1.setBounds(562, 401, 141, 37);
		Current.add(btnCancel_1);
		
		JButton btnCancel = new JButton("CANCEL");  // To go to the initial page
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ATM atm = new ATM();
				atm.getFrame().setVisible(true);
			}
		});
		btnCancel.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		btnCancel.setBounds(577, 404, 141, 37);
		SavingsW.add(btnCancel);
		
		JLabel lblNewLabel_5 = new JLabel("Please Enter cash in multiples of 100");
		lblNewLabel_5.setFont(new Font("Book Antiqua", Font.BOLD, 22));
		lblNewLabel_5.setBounds(77, 119, 386, 24);
		SavingsW.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel(">");
		lblNewLabel_6.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		lblNewLabel_6.setBounds(44, 125, 23, 13);
		SavingsW.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel(b.getBankName());
		lblNewLabel_7.setFont(new Font("Modern No. 20", Font.PLAIN, 24));
		lblNewLabel_7.setBounds(286, 42, 313, 30);
		SavingsW.add(lblNewLabel_7);
		
		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("Courier New", Font.PLAIN, 10));
		textPane.setBounds(138, 100, 285, 256);
		Bill.add(textPane);
		
		JLabel lblReceipt = new JLabel("Receipt");
		lblReceipt.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		lblReceipt.setBounds(138, 53, 263, 37);
		Bill.add(lblReceipt);
		
		JButton newTransaction = new JButton("New Transaction"); // To go to the initial page
		newTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ATM atm = new ATM();
				atm.getFrame().setVisible(true);
			}
		});
		newTransaction.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		newTransaction.setBounds(551, 362, 184, 37);
		Bill.add(newTransaction);
		
		DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		DateTimeFormatter dateOnly = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();
		
		JButton btnYes = new JButton("YES");
		btnYes.addActionListener(new ActionListener() {  // Making transaction from saving account if all conditions are satisfied
			private boolean n;
			public void actionPerformed(ActionEvent e) {
				try {
					
					try {
						Integer.parseInt(textField.getText());
					} catch(Exception e8) {
						n = true;
					}

					double cash = ah.getCashS();
					int csh = Integer.parseInt(textField.getText());
					int tc = 0;
					if(ah.getBankCode() != b.getBankCode()) {
						tc = b.getTransactionCharge();
					}
					cashWithdrawnS = csh;
					if(csh > 0) {
						if(csh <= b.getmaxAmountW()) {
							if(csh%100 == 0) {
								if(cash >= (double)(csh + tc)) {
									if( csh <= b.getTotalCash() ) {
										
										ah.setCashS(cash - (double)csh - tc);
										b.setTotalCash(b.getTotalCash() - csh);
										textPane.setText("          "+b.getBankName()+"\n\n"
											+"Date\t  :  " + date.format(now)+"\n"
											+"Account No\t  :  XXX" + ah.getAccNum()%100 + "\n"
											+"Transaction\t  :  Withdrawal\n"
											+"Amount\t  :  " + cashWithdrawnS + "\n"
											+"ATM charge\t  :  " + tc + "\n"
										    +"Account Type :  Savings\n"
										    +"Balance\t  :  " + ah.getCashS() + "\n\n"
										    +"    Thank You for choosing us");
										b.addTransaction(ah.getAccNum(), date.format(now), "WITHDRAWAL", "Savings", -cashWithdrawnS);
										switchJpanels(Bill);
									
									} 
									else {
										JOptionPane.showMessageDialog(null, "Sorry, Sorry, ATM has less money\nYou can withdraw cash less than " + b.getTotalCash());
									}
								}
								else {
									JOptionPane.showMessageDialog(null, "Your account balance is low, you can withdraw cash less than " + cash);
								}
							} 
							else {
								JOptionPane.showMessageDialog(null, "Please Enter Amount in multiples of 100");
							}
						} 
						else {
							JOptionPane.showMessageDialog(null, "Sorry, You cannot Withdraw more than " + b.getmaxAmountW());
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Please Enter only valid amount");
					}
					
					
				}catch(Exception e4) {
					if(n || textField.getText().isBlank()) {
						JOptionPane.showMessageDialog(null, "Please Enter only valid amount");
					} else {
						JOptionPane.showMessageDialog(null, e4);
					}
				}
			}
		});
		btnYes.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		btnYes.setBounds(577, 342, 141, 37);
		SavingsW.add(btnYes);
		
		JButton button = new JButton("YES");
		button.addActionListener(new ActionListener() {   // Making transaction from current account if all conditions are satisfied
			private boolean m;
			public void actionPerformed(ActionEvent e) {
				try {
					try {
							Integer.parseInt(textField_1.getText());
							if(Integer.parseInt(textField_1.getText()) <=0)
								m = true;
					} catch(Exception e8) {
						m = true;
					}
		
					double cash = ah.getCashC();
					int csh = Integer.parseInt(textField_1.getText());
					cashWithdrawnC = csh;
					int tc = 0;
					if(ah.getBankCode() != b.getBankCode()) {
						tc = b.getTransactionCharge();
					}
					if(csh > 0) {
						if(csh <= b.getmaxAmountW()) {
							if(csh%100 == 0) {
								if(cash >= (double)(csh+tc) || (cash < (double)(csh+tc) && ((double)(csh+tc) - cash) <= ah.getOverdraftLimit() )) {
									if( csh <= (double)b.getTotalCash() ) {
							
										ah.setCashC(cash - (double)csh - tc);
										b.setTotalCash(b.getTotalCash() - csh);
										textPane.setText("            "+b.getBankName()+"\n\n"
											+"Date\t  :  " + date.format(now)+"\n"
											+"Account No\t  :  XXX" + ah.getAccNum()%100 + "\n"
											+"Transaction\t  :  Withdrawal\n"
											+"Amount\t  :  " + cashWithdrawnC + "\n"
											+"ATM charge\t  :  " + tc + "\n"
										    +"Account Type  :  Current\n"
										    +"Balance\t  :  " + ah.getCashC() + "\n\n"
										    +"    Thank You for choosing us");
										b.addTransaction(ah.getAccNum(), date.format(now), "WITHDRAWAL", "Current", -cashWithdrawnC);
										switchJpanels(Bill);
							
									} 
									else {
										JOptionPane.showMessageDialog(null, "Sorry, ATM has less money\nYou can withdraw cash less than " + b.getTotalCash());
									}
								}
								else {
									JOptionPane.showMessageDialog(null, "Your account balance is low, and you are exceeded overdraft limit\n You can withdraw cash less than " + (cash+ah.getOverdraftLimit()));
								}
							} 
							else {
								JOptionPane.showMessageDialog(null, "Please Enter Amount in Multiples of 100");
							}
						} 
						else {
							JOptionPane.showMessageDialog(null, "Sorry, You cannot Withdraw more than " + b.getmaxAmountW() + " in one Transaction");
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Please Enter only valid amount");
					}
					
			
				}catch(Exception e10) {
					if(m || textField_1.getText().isBlank()) {
						JOptionPane.showMessageDialog(null, "Please Enter only valid amount");
					} else {
						JOptionPane.showMessageDialog(null, e10);
					}
				
				}
			}
		});
		button.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		button.setBounds(562, 335, 141, 37);
		Current.add(button);
		
		JLabel label_2 = new JLabel(b.getBankName());
		label_2.setFont(new Font("Modern No. 20", Font.PLAIN, 24));
		label_2.setBounds(250, 31, 275, 30);
		Current.add(label_2);
		
		JLabel labelM = new JLabel("Please Enter cash in multiples of 100");
		labelM.setFont(new Font("Book Antiqua", Font.BOLD, 22));
		labelM.setBounds(69, 116, 424, 24);
		Current.add(labelM);
		
		JLabel arrow = new JLabel(">");
		arrow.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		arrow.setBounds(42, 122, 23, 13);
		Current.add(arrow);
		
		JPanel otpPanel = new JPanel();
		layeredPane.add(otpPanel, "name_38402394502100");
		otpPanel.setLayout(null);
		
		JLabel lblP = new JLabel("Please Enter OTP sent to Your Registered Mobile Number");
		lblP.setFont(new Font("Book Antiqua", Font.PLAIN, 21));
		lblP.setBounds(83, 79, 543, 37);
		otpPanel.add(lblP);
		
		textField_2 = new JTextField();
		textField_2.setBounds(242, 126, 161, 31);
		otpPanel.add(textField_2);
		textField_2.setColumns(10);
		
		
		
	}
}
