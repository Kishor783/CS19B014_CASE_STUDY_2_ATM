package ATM_CASESTUDY;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import com.sun.jdi.connect.spi.Connection;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.Random;

import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;


public class BankManager {

	private JFrame frame;
	JLayeredPane layeredPane;
	
	Connection connection = null;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BankManager window = new BankManager();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BankManager() { 
		initialize();
	}

	Bank b = new Bank();
	private JTextField textFieldAN;
	private JTextField textFieldNM;
	private JTextField textFieldMS;
	private JTextField textFieldMC;
	private JTextField textFieldS;
	private JTextField textFieldC;
	private JTextField textFieldODL;
	private JTextField textFielddel;
	private JTextField textFieldbn;
	private JTextField textFieldTC;
	private JTextField textFieldmn;
	private JTextField textFieldd;
	private JTextField textFieldw;
	private JPasswordField textField1;
	private JTextField textFieldPN;
	private JTextField textFieldBC;
	private JTextField textFieldbc;
	private JTextField textFieldtch;
	private JTextField textField;
	private JTextField textFieldnot;
	private JTextField textFieldtlm;
	
	public void switchJpanels(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 827, 511);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 813, 474);
		frame.getContentPane().add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		JPanel panel = new JPanel();
		layeredPane.add(panel, "name_139788644870199");
		panel.setLayout(null);
		
		JPanel panel2 = new JPanel();
		layeredPane.add(panel2, "name_22820882838000");
		panel2.setLayout(null);
		
		JPanel panel1 = new JPanel();
		layeredPane.add(panel1, "name_139793481734300");
		panel1.setLayout(null);
		
		JPanel panel3 = new JPanel();
		layeredPane.add(panel3, "name_22830298446600");
		panel3.setLayout(null);
		
		JPanel bankname = new JPanel();
		layeredPane.add(bankname, "name_22875474855400");
		bankname.setLayout(null);
		
		JPanel totalCash = new JPanel();
		totalCash.setLayout(null);
		layeredPane.add(totalCash, "name_30642242634500");
		
		JPanel maxnotess = new JPanel();
		maxnotess.setLayout(null);
		layeredPane.add(maxnotess, "name_30690607813000");
		
		JPanel maxD = new JPanel();
		maxD.setLayout(null);
		layeredPane.add(maxD, "name_30856034277200");
		
		JPanel maxW = new JPanel();
		maxW.setLayout(null);
		layeredPane.add(maxW, "name_30881789584100");
		
		JPanel bankCode = new JPanel();
		layeredPane.add(bankCode, "name_19729592890500");
		bankCode.setLayout(null);
		
		JPanel transactionCharge = new JPanel();
		layeredPane.add(transactionCharge, "name_21535061291400");
		transactionCharge.setLayout(null);
		
		JPanel setBlockStatus = new JPanel();
		layeredPane.add(setBlockStatus, "name_27913627651400");
		setBlockStatus.setLayout(null);
		
		JPanel noOfTries = new JPanel();
		layeredPane.add(noOfTries, "name_32359430220000");
		noOfTries.setLayout(null);
		
		JPanel timeLimit = new JPanel();
		layeredPane.add(timeLimit, "name_32367835730100");
		timeLimit.setLayout(null);
		
		textFieldtlm = new JTextField();
		textFieldtlm.setColumns(10);
		textFieldtlm.setBounds(209, 184, 224, 38);
		timeLimit.add(textFieldtlm);
		
		textFieldnot = new JTextField();
		textFieldnot.setColumns(10);
		textFieldnot.setBounds(209, 174, 224, 38);
		noOfTries.add(textFieldnot);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(250, 155, 224, 38);
		setBlockStatus.add(textField);
		
		textFieldtch = new JTextField();
		textFieldtch.setColumns(10);
		textFieldtch.setBounds(231, 192, 224, 38);
		transactionCharge.add(textFieldtch);
		
		textFieldbc = new JTextField();
		textFieldbc.setColumns(10);
		textFieldbc.setBounds(242, 193, 224, 38);
		bankCode.add(textFieldbc);
		
		textFieldbn = new JTextField();
		textFieldbn.setBounds(217, 194, 224, 38);
		bankname.add(textFieldbn);
		textFieldbn.setColumns(10);
		
		JButton button13 = new JButton("CONFIRM");   // to update bank name
		button13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String bn = textFieldbn.getText();
					b.setBankName(bn);
					JOptionPane.showMessageDialog(null, "Bank name is updated");
					switchJpanels(panel);
					textField1.setText("");
					
				} catch(Exception e5) {
					System.out.println("here");
					JOptionPane.showMessageDialog(null,e5);
				}
			}
		});
		button13.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		button13.setBounds(558, 294, 131, 36);
		bankname.add(button13);
		
		JButton button14 = new JButton("CANCEL");
		button14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchJpanels(panel);
				textField1.setText("");
			}
		});
		button14.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		button14.setBounds(558, 369, 131, 36);
		bankname.add(button14);
		
		JLabel labelBN = new JLabel("Enter New Bank Name :");
		labelBN.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		labelBN.setBounds(217, 128, 248, 37);
		bankname.add(labelBN);
		
		JLabel lblEnterAccountNumber = new JLabel("Enter Account Number to be Deleted :");
		lblEnterAccountNumber.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		lblEnterAccountNumber.setBounds(176, 91, 348, 38);
		panel3.add(lblEnterAccountNumber);
		
		textFielddel = new JTextField();
		textFielddel.setBounds(231, 158, 204, 31);
		panel3.add(textFielddel);
		textFielddel.setColumns(10);
		
		JButton button6 = new JButton("CONFIRM");  // to delete account
		button6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int accNo = Integer.parseInt(textFielddel.getText());
					AccountHolder a = new AccountHolder(accNo);
					if(a.isValid(accNo)) {
						b.delAccount(accNo);
						JOptionPane.showMessageDialog(null, "Account is deleted");
						switchJpanels(panel);
						textField1.setText("");
					}
					
				} catch(Exception e4) {
					JOptionPane.showMessageDialog(null, "Please, Enter Correct Account Number");
				}
			}
		});
		button6.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		button6.setBounds(519, 280, 179, 47);
		panel3.add(button6);
		
		JButton button7 = new JButton("CANCEL");
		button7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchJpanels(panel);
				textField1.setText("");
			}
		});
		button7.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		button7.setBounds(519, 363, 179, 47);
		panel3.add(button7);
		
		JButton button3 = new JButton("REMOVE ACCOUNT");
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFielddel.setText("");
				switchJpanels(panel3);
			}
		});
		button3.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		button3.setBounds(53, 85, 300, 47);
		panel1.add(button3);
		
		JButton button2 = new JButton("ADD NEW ACCOUNT");
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldAN.setText("");
				textFieldNM.setText("");
				textFieldMS.setText("");
				textFieldMC.setText("");
				textFieldS.setText("");
				textFieldC.setText("");
				textFieldODL.setText("");
				textFieldPN.setText("");
				textFieldBC.setText("");
				switchJpanels(panel2);
			}
		});
		button2.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		button2.setBounds(53, 28, 300, 47);
		panel1.add(button2);
		
		JButton button11 = new JButton("SET MAX CASH WITHDRAW");
		button11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldw.setText("");
				switchJpanels(maxW);
			}
		});
		button11.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		button11.setBounds(417, 85, 300, 47);
		panel1.add(button11);
		
		JButton button5 = new JButton("SET MAX CASH DEPOSIT");
		button5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldd.setText("");
				switchJpanels(maxD);
			}
		});
		button5.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		button5.setBounds(417, 142, 300, 47);
		panel1.add(button5);
		
		JButton button10 = new JButton("SET MAX NOTES");
		button10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldmn.setText("");
				switchJpanels(maxnotess);
			}
		});
		button10.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		button10.setBounds(417, 28, 300, 47);
		panel1.add(button10);
		
		JButton button12 = new JButton("CANCEL");
		button12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchJpanels(panel);
				textField1.setText("");
			}
		});
		button12.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		button12.setBounds(246, 394, 300, 47);
		panel1.add(button12);
		
		JButton button9 = new JButton("SET TOTAL CASH IN ATM");
		button9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldTC.setText("");
				switchJpanels(totalCash);
			}
		});
		button9.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		button9.setBounds(53, 199, 300, 47);
		panel1.add(button9);
		
		JButton button8 = new JButton("SET BANK NAME");
		button8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldbn.setText("");
				switchJpanels(bankname);
			}
		});
		button8.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		button8.setBounds(53, 316, 300, 47);
		panel1.add(button8);
		
		JButton btnSetBankCode = new JButton("SET BANK CODE");
		btnSetBankCode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldbc.setText("");
				switchJpanels(bankCode);
			}
		});
		btnSetBankCode.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		btnSetBankCode.setBounds(417, 199, 300, 47);
		panel1.add(btnSetBankCode);
		
		JButton btnSetTransactionCharge = new JButton("SET TRANSACTION CHARGE");
		btnSetTransactionCharge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldtch.setText("");
				switchJpanels(transactionCharge);
			}
		});
		btnSetTransactionCharge.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		btnSetTransactionCharge.setBounds(53, 259, 300, 47);
		panel1.add(btnSetTransactionCharge);
		
		JButton btnBlockOrUnblock = new JButton("BLOCK OR UNBLOCK CARD");
		btnBlockOrUnblock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				switchJpanels(setBlockStatus);
			}
		});
		btnBlockOrUnblock.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		btnBlockOrUnblock.setBounds(53, 142, 300, 47);
		panel1.add(btnBlockOrUnblock);
		
		JButton btnSetNoOf = new JButton("SET NO OF TRIES");
		btnSetNoOf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldnot.setText("");
				switchJpanels(noOfTries);
			}
		});
		btnSetNoOf.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		btnSetNoOf.setBounds(417, 259, 300, 47);
		panel1.add(btnSetNoOf);
		
		JButton btnSetTimeLimit = new JButton("SET TIME LIMIT");
		btnSetTimeLimit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldtlm.setText("");
				switchJpanels(timeLimit);
			}
		});
		btnSetTimeLimit.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		btnSetTimeLimit.setBounds(417, 316, 300, 47);
		panel1.add(btnSetTimeLimit);
		
		JLabel lblEnterTheCorrect = new JLabel("Enter the Correct Password");
		lblEnterTheCorrect.setFont(new Font("Century", Font.BOLD, 20));
		lblEnterTheCorrect.setBounds(265, 152, 320, 33);
		panel.add(lblEnterTheCorrect);
		
		
		JButton button1 = new JButton("CONFIRM");  // login of bank manager
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int password = Integer.parseInt(textField1.getText());
					if(password == b.getPassword()) {
						switchJpanels(panel1);
					}
					else {
						JOptionPane.showMessageDialog(null, "Please, Enter Correct password");
					}
				} catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "Please, Enter Correct password");
				}
			}
		});
		button1.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		button1.setBounds(602, 381, 131, 36);
		panel.add(button1);
		
		textField1 = new JPasswordField();
		textField1.setBounds(302, 212, 190, 33);
		panel.add(textField1);
		
		JLabel lblForBankManager = new JLabel("For Bank Manager");
		lblForBankManager.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		lblForBankManager.setBounds(23, 21, 248, 37);
		panel.add(lblForBankManager);
		
		JLabel label = new JLabel(b.getBankName());
		label.setFont(new Font("Century", Font.BOLD, 20));
		label.setBounds(312, 41, 360, 33);
		panel.add(label);
		
		JLabel lblAccountNumber = new JLabel("Account Number :");
		lblAccountNumber.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		lblAccountNumber.setBounds(43, 51, 164, 37);
		panel2.add(lblAccountNumber);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		lblName.setBounds(43, 98, 164, 37);
		panel2.add(lblName);
		
		JLabel lblMoneyInCurrent = new JLabel("Money in current :");
		lblMoneyInCurrent.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		lblMoneyInCurrent.setBounds(43, 192, 164, 37);
		panel2.add(lblMoneyInCurrent);
		
		JLabel lblIsSavings = new JLabel("Is Savings :");
		lblIsSavings.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		lblIsSavings.setBounds(43, 286, 164, 37);
		panel2.add(lblIsSavings);
		
		JLabel lblIsCurrent = new JLabel("Is Current :");
		lblIsCurrent.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		lblIsCurrent.setBounds(43, 333, 164, 37);
		panel2.add(lblIsCurrent);
		
		JLabel lblMoneyInSavings = new JLabel("Money in Savings :");
		lblMoneyInSavings.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		lblMoneyInSavings.setBounds(43, 145, 164, 37);
		panel2.add(lblMoneyInSavings);
		
		JLabel lblOverdraftlimit = new JLabel("OverdraftLimit :");
		lblOverdraftlimit.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		lblOverdraftlimit.setBounds(43, 239, 164, 37);
		panel2.add(lblOverdraftlimit);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number :");
		lblPhoneNumber.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		lblPhoneNumber.setBounds(43, 380, 164, 37);
		panel2.add(lblPhoneNumber);
		
		JLabel lblBankCode = new JLabel("Bank Code");
		lblBankCode.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		lblBankCode.setBounds(43, 427, 164, 37);
		panel2.add(lblBankCode);
		
		textFieldAN = new JTextField();
		textFieldAN.setBounds(295, 51, 141, 26);
		panel2.add(textFieldAN);
		textFieldAN.setColumns(10);
		
		textFieldNM = new JTextField();
		textFieldNM.setColumns(10);
		textFieldNM.setBounds(295, 98, 141, 26);
		panel2.add(textFieldNM);
		
		textFieldMS = new JTextField();
		textFieldMS.setColumns(10);
		textFieldMS.setBounds(295, 145, 141, 26);
		panel2.add(textFieldMS);
		
		textFieldMC = new JTextField();
		textFieldMC.setColumns(10);
		textFieldMC.setBounds(295, 192, 141, 26);
		panel2.add(textFieldMC);
		
		textFieldS = new JTextField();
		textFieldS.setColumns(10);
		textFieldS.setBounds(295, 286, 141, 26);
		panel2.add(textFieldS);
		
		textFieldC = new JTextField();
		textFieldC.setColumns(10);
		textFieldC.setBounds(295, 333, 141, 26);
		panel2.add(textFieldC);
		
		textFieldODL = new JTextField();
		textFieldODL.setColumns(10);
		textFieldODL.setBounds(295, 239, 141, 26);
		panel2.add(textFieldODL);
		
		textFieldPN = new JTextField();
		textFieldPN.setBounds(295, 377, 141, 26);
		panel2.add(textFieldPN);
		textFieldPN.setColumns(10);
		
		textFieldBC = new JTextField();
		textFieldBC.setBounds(295, 427, 141, 26);
		panel2.add(textFieldBC);
		textFieldBC.setColumns(10);

		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchJpanels(panel);
				textField1.setText("");
			}
		});
		btnCancel.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		btnCancel.setBounds(618, 393, 131, 36);
		panel2.add(btnCancel);
		
		JButton buttonConfirm1 = new JButton("CONFIRM");  // to add new account if all conditions are satisfied
		buttonConfirm1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					int accNum = Integer.parseInt(textFieldAN.getText());
					String name = textFieldNM.getText();
					double mis = Double.parseDouble(textFieldMS.getText());
					double mic = Double.parseDouble(textFieldMC.getText());
					int isS = Integer.parseInt(textFieldS.getText());
					int isC = Integer.parseInt(textFieldC.getText());
					int odl = Integer.parseInt(textFieldODL.getText());
					long pn  = Long.parseLong(textFieldPN.getText());
					int bc  = Integer.parseInt(textFieldBC.getText());
					
					if(textFieldAN.getText().length() == 5 && accNum >= 0) {
						if((isC == 0 && isS == 1) || (isS == 0 && isC == 1)) {
							if((isS == 1 && mis >= 0 && mic == 0) || (isC == 1 && mis == 0 && mic >= 0)) {
								if((isC == 1 && odl >= 0) || odl == 0) {
									if(pn >= 0 && textFieldPN.getText().length() == 10) {
										if(bc >= 0 ) {
											
											Random rand = new Random(); 
										    int p = rand.nextInt(100000);
										    
										    int pin = b.encryptPIN(p);
										    
											b.addAccount(accNum,name,pin,mis,mic,odl,isS,isC,pn,bc);
											JOptionPane.showMessageDialog(null,"Account is added");
											
											switchJpanels(panel);
											
											textField1.setText("");
											
										} else {
											JOptionPane.showMessageDialog(null,"Please Enter valid Bank Code");
										}
									} else {
										JOptionPane.showMessageDialog(null,"Please Enter valid 10 digit Phone Number");
									}
								} else {
									JOptionPane.showMessageDialog(null,"Please Enter valid overdraft limit if Account Type is Current else Enter ONLY 0");
								}
							} else {
								JOptionPane.showMessageDialog(null,"Please Enter valid Amount.\nAnd Enter Money in Savings if Account Type is Saving else enter ONLY 0\nEnter Money in Current if Account Type is Current else enter ONLY 0\n Because one Account Number can have only one Account Type");
							}
						} else {
							JOptionPane.showMessageDialog(null,"Please Enter 1 if user has current/saving account else 0 and do not enter both 0 or 1");
						} 
					} else {
						JOptionPane.showMessageDialog(null,"Please Enter valid Account Number ");
					}
					
					
				} catch(Exception e3) {
					JOptionPane.showMessageDialog(null,"Please Enter valid inputs only");
				}
				
			}
		});
		buttonConfirm1.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		buttonConfirm1.setBounds(618, 330, 131, 36);
		panel2.add(buttonConfirm1);
		
		JTextPane txtpnNoteIf = new JTextPane();
		txtpnNoteIf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtpnNoteIf.setText("Note : \r\n1. Account Number should contain exactly 5 integers\r\n2. Money in Savings should be non negative and it is strictly 0 if account type is Current \r\n3. Money in Current should be non negative and it is strictly 0 if account type is Savings\r\n4. Overdraft Limit should be non negative and it is strictly 0 if account type is Savings\r\n5. Is Savings is '1' if account type is Savings else 0\r\n6. Is Current is '1' if account type is Current else 0\r\n7. Phone Number should contain 10 digits\r\n8. Bank code is non negative\r\n");
		txtpnNoteIf.setBounds(484, 31, 306, 201);
		panel2.add(txtpnNoteIf);
				
		textFieldTC = new JTextField();
		textFieldTC.setColumns(10);
		textFieldTC.setBounds(227, 175, 224, 38);
		totalCash.add(textFieldTC);
		
		JButton button = new JButton("CONFIRM"); // to update total cash in at,
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int cash = Integer.parseInt(textFieldTC.getText());
					if(cash >= 0) {
						b.setTotalCash(cash);
						JOptionPane.showMessageDialog(null, "Total Cash in ATM is updated");
						switchJpanels(panel);
						textField1.setText("");
					}
					else {
						JOptionPane.showMessageDialog(null, "Please, Enter valid number");
					}
				} catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "Please, Enter valid number");
				}
			}
		});
		button.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		button.setBounds(558, 294, 131, 36);
		totalCash.add(button);
		
		JButton button_1 = new JButton("CANCEL");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchJpanels(panel);
				textField1.setText("");
			}
		});
		button_1.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		button_1.setBounds(558, 369, 131, 36);
		totalCash.add(button_1);
		
		JLabel lblEnterAmountTo = new JLabel("Enter cash Avaialable in ATM afterLoading");
		lblEnterAmountTo.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		lblEnterAmountTo.setBounds(179, 128, 360, 37);
		totalCash.add(lblEnterAmountTo);
		
		textFieldmn = new JTextField();
		textFieldmn.setColumns(10);
		textFieldmn.setBounds(217, 194, 224, 38);
		maxnotess.add(textFieldmn);
		
		JButton button_2 = new JButton("CONFIRM");  // to update max notes
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int num = Integer.parseInt(textFieldmn.getText());
					if(num >= 0) {
						b.setMaxNotes(num);
						JOptionPane.showMessageDialog(null, "Max notes to inserted by user is updated");
						switchJpanels(panel);
						textField1.setText("");
					}
					else {
						JOptionPane.showMessageDialog(null, "Please, Enter valid number");
					}
				} catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "Please, Enter valid number");
				}
			}
		});
		button_2.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		button_2.setBounds(558, 294, 131, 36);
		maxnotess.add(button_2);
		
		JButton button_3 = new JButton("CANCEL");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchJpanels(panel);
				textField1.setText("");
			}
		});
		button_3.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		button_3.setBounds(558, 369, 131, 36);
		maxnotess.add(button_3);
		
		JLabel lblEnterM = new JLabel("Enter Max Notes Allowed");
		lblEnterM.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		lblEnterM.setBounds(217, 128, 248, 37);
		maxnotess.add(lblEnterM);
		
		textFieldd = new JTextField();
		textFieldd.setColumns(10);
		textFieldd.setBounds(217, 194, 224, 38);
		maxD.add(textFieldd);
		
		JButton button_4 = new JButton("CONFIRM");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int max = Integer.parseInt(textFieldd.getText());
					if(max >= 0) {
						b.setMaxCashD(max);
						JOptionPane.showMessageDialog(null, "Max Cash Deposited by user is updated");
						switchJpanels(panel);
						textField1.setText("");
					}
					else {
						JOptionPane.showMessageDialog(null, "Please, Enter valid number");
					}
				} catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "Please, Enter valid number");
				}
			}
		});
		button_4.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		button_4.setBounds(558, 294, 131, 36);
		maxD.add(button_4);
		
		JButton button_5 = new JButton("CANCEL");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchJpanels(panel);
				textField1.setText("");
			}
		});
		button_5.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		button_5.setBounds(558, 369, 131, 36);
		maxD.add(button_5);
		
		JLabel lblEnterMaxCash_1 = new JLabel("Enter MAx cash Deposit allowed :");
		lblEnterMaxCash_1.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		lblEnterMaxCash_1.setBounds(196, 132, 308, 37);
		maxD.add(lblEnterMaxCash_1);
		
		textFieldw = new JTextField();
		textFieldw.setColumns(10);
		textFieldw.setBounds(217, 194, 224, 38);
		maxW.add(textFieldw);
		
		JButton button_6 = new JButton("CONFIRM");   // to set max cash withdrawable
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int max = Integer.parseInt(textFieldw.getText());
					if(max >= 0) {
						b.setMaxCashW(max);
						JOptionPane.showMessageDialog(null, "Max cash Withdrawable by user is updated");
						switchJpanels(panel);
						textField1.setText("");
					}
					else {
						JOptionPane.showMessageDialog(null, "Please, Enter valid number");
					}
				} catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "Please, Enter valid number");
				}
			}
		});
		button_6.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		button_6.setBounds(558, 294, 131, 36);
		maxW.add(button_6);
		
		JButton button_7 = new JButton("CANCEL");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchJpanels(panel);
				textField1.setText("");
			}
		});
		button_7.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		button_7.setBounds(558, 369, 131, 36);
		maxW.add(button_7);
		
		JLabel lblEnterMaxCash = new JLabel("Enter Max Cash Withdrawable :");
		lblEnterMaxCash.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		lblEnterMaxCash.setBounds(217, 128, 286, 37);
		maxW.add(lblEnterMaxCash);
		
		JButton button_8 = new JButton("CONFIRM");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int bc = Integer.parseInt(textFieldbc.getText());
					if(bc >= 0) {
						b.setBankCode(bc);
						JOptionPane.showMessageDialog(null, "Bank Code is Updated");
						switchJpanels(panel);
						textField1.setText("");
					}
					else {
						JOptionPane.showMessageDialog(null, "Please, Enter valid bank code");
					}
				} catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "Please, Enter valid number");
				}
				
			}
		});
		button_8.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		button_8.setBounds(583, 293, 131, 36);
		bankCode.add(button_8);
		
		JButton button_9 = new JButton("CANCEL");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchJpanels(panel);
				textField1.setText("");
			}
		});
		button_9.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		button_9.setBounds(583, 368, 131, 36);
		bankCode.add(button_9);
		
		JLabel lblEnterNewBank = new JLabel("Enter New Bank Code");
		lblEnterNewBank.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		lblEnterNewBank.setBounds(242, 127, 248, 37);
		bankCode.add(lblEnterNewBank);
		
		JButton button_10 = new JButton("CONFIRM");
		button_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int tch = Integer.parseInt(textFieldtch.getText());
					if(tch >= 0) {
						b.setTransactionCharge(tch);
						JOptionPane.showMessageDialog(null, "Transaction Charge is Updated");
						switchJpanels(panel);
						textField1.setText("");
					}
					else {
						JOptionPane.showMessageDialog(null, "Please, Enter valid Transaction Charge");
					}
				} catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "Please, Enter valid number");
				}
			}
		});
		button_10.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		button_10.setBounds(572, 292, 131, 36);
		transactionCharge.add(button_10);
		
		JButton button_11 = new JButton("CANCEL");
		button_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchJpanels(panel);
				textField1.setText("");
			}
		});
		button_11.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		button_11.setBounds(572, 367, 131, 36);
		transactionCharge.add(button_11);
		
		JLabel lblEnterTransactionCharge = new JLabel("Enter Transaction Charge Applicable for Other Bank users");
		lblEnterTransactionCharge.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		lblEnterTransactionCharge.setBounds(134, 132, 485, 37);
		transactionCharge.add(lblEnterTransactionCharge);
		
		JButton btnBlockCard = new JButton("BLOCK CARD");
		btnBlockCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int accNum = Integer.parseInt(textField.getText());
					AccountHolder a = new AccountHolder(accNum);
					if(a.isValid(accNum)) {
						a.setIsBlocked(1);
						JOptionPane.showMessageDialog(null, "Card is Blocked");
						switchJpanels(panel);
						textField1.setText("");
					}
				} catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "Please, Enter valid number");
				}
			}
		});
		btnBlockCard.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		btnBlockCard.setBounds(150, 232, 193, 36);
		setBlockStatus.add(btnBlockCard);
		
		JButton button_13 = new JButton("CANCEL");
		button_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchJpanels(panel);
				textField1.setText("");
			}
		});
		button_13.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		button_13.setBounds(569, 360, 131, 36);
		setBlockStatus.add(button_13);
		
		JLabel lblEnterAccountNumber_1 = new JLabel("Enter Account Number of user");
		lblEnterAccountNumber_1.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		lblEnterAccountNumber_1.setBounds(238, 93, 288, 37);
		setBlockStatus.add(lblEnterAccountNumber_1);
		
		JButton btnUnblockCard = new JButton("UNBLOCK CARD");
		btnUnblockCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int accNum = Integer.parseInt(textField.getText());
					AccountHolder a = new AccountHolder(accNum);
					if(a.isValid(accNum)) {
						a.setIsBlocked(0);
						JOptionPane.showMessageDialog(null, "Card is UnBlocked");
						switchJpanels(panel);
						textField1.setText("");
					}
				} catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "Please, Enter valid number");
				}
			}
		});
		btnUnblockCard.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		btnUnblockCard.setBounds(394, 232, 200, 36);
		setBlockStatus.add(btnUnblockCard);
		
		
		JButton button_12 = new JButton("CONFIRM");
		button_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int not = Integer.parseInt(textFieldnot.getText());
					if(not > 0) {
						b.setNoOfTries(not);
						JOptionPane.showMessageDialog(null, "No of tries for user for wrong PIN is Updated");
						switchJpanels(panel);
						textField1.setText("");
					}
					else {
						JOptionPane.showMessageDialog(null, "Please, Enter valid Integer greater than 0");
					}
				} catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "Please, Enter valid number");
				}
			}
		});
		button_12.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		button_12.setBounds(550, 274, 131, 36);
		noOfTries.add(button_12);
		
		JButton button_14 = new JButton("CANCEL");
		button_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchJpanels(panel);
				textField1.setText("");
			}
		});
		button_14.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		button_14.setBounds(550, 349, 131, 36);
		noOfTries.add(button_14);
		
		JLabel lblEnterNoOf = new JLabel("Enter no of Tries");
		lblEnterNoOf.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		lblEnterNoOf.setBounds(209, 108, 248, 37);
		noOfTries.add(lblEnterNoOf);
		
		
		JButton button_15 = new JButton("CONFIRM");
		button_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int tlm = Integer.parseInt(textFieldtlm.getText());
					if(tlm > 0) {
						b.setTimeLimit(tlm);
						JOptionPane.showMessageDialog(null, "Time Limit for blocking card is Updated");
						switchJpanels(panel);
						textField1.setText("");
					}
					else {
						JOptionPane.showMessageDialog(null, "Please, Enter valid number");
					}
				} catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "Please, Enter valid number");
				}
			}
		});
		button_15.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		button_15.setBounds(550, 284, 131, 36);
		timeLimit.add(button_15);
		
		JButton button_16 = new JButton("CANCEL");
		button_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchJpanels(panel);
				textField1.setText("");
			}
		});
		button_16.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		button_16.setBounds(550, 359, 131, 36);
		timeLimit.add(button_16);
		
		JLabel lblEnterTimeLimit = new JLabel("Enter Time Limit in minutes (Integer Only)");
		lblEnterTimeLimit.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		lblEnterTimeLimit.setBounds(209, 118, 372, 37);
		timeLimit.add(lblEnterTimeLimit);
		
		
		
		
	}
}
