package ATM_CASESTUDY;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

public class Transfer extends JFrame {

	private JPanel contentPane;
	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField_2;
	JLayeredPane layeredPane;

	AccountHolder ah;  // To store details of present user of atm
	int amountS;
	int amountC;
	
	Bank b = new Bank();
	
	String receiverAccountType;  // to store receiver account type
	int receiverAccNumber;    // to  store receiver account number
	int amountSent;     // amount sent to receiver
	
	public void switchJpanels(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}
	
	public Transfer(int accNum) {
		ah = new AccountHolder(accNum);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 835, 519);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, "name_122376894848700");
		layeredPane.setLayout(new CardLayout(0, 0));
		
		JPanel panel = new JPanel();
		layeredPane.add(panel, "name_122403577780000");
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		layeredPane.add(panel_1, "name_122407457746400");
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		layeredPane.add(panel_2, "name_123191485769700");
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		layeredPane.add(panel_3, "name_123312534389900");
		
		JPanel Bill = new JPanel();
		layeredPane.add(Bill, "name_123426424975600");
		Bill.setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("Courier New", Font.PLAIN, 10));
		textPane.setBounds(188, 101, 277, 306);
		Bill.add(textPane);
		
		JButton newTransaction = new JButton("New Transaction");   // to go to the initial page
		newTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ATM atm = new ATM();
				atm.getFrame().setVisible(true);
			}
		});
		newTransaction.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		newTransaction.setBounds(571, 367, 182, 40);
		Bill.add(newTransaction);
		
		JLabel label3 = new JLabel("Receipt");
		label3.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		label3.setBounds(188, 39, 308, 40);
		Bill.add(label3);
		
		JLabel label1 = new JLabel("Select Your To Account Type");
		label1.setFont(new Font("Book Antiqua", Font.BOLD, 22));
		label1.setBounds(247, 111, 308, 40);
		panel.add(label1);
		
		JButton btnSaving = new JButton("SAVINGS");  // to set receiver account type to savings
		btnSaving.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				receiverAccountType = "Savings";
				switchJpanels(panel_1);
			}
		});
		btnSaving.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		btnSaving.setBounds(583, 279, 156, 40);
		panel.add(btnSaving);
		
		JButton btnCurrent = new JButton("CURRENT");  // to set receiver account type to current
		btnCurrent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				receiverAccountType = "Current";
				switchJpanels(panel_1);
			}
		});
		btnCurrent.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		btnCurrent.setBounds(583, 329, 156, 40);
		panel.add(btnCurrent);
		
		JButton btnCancel1 = new JButton("CANCEL");   // to go to the initial page
		btnCancel1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ATM atm = new ATM();
				atm.getFrame().setVisible(true);
			}
		});
		btnCancel1.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		btnCancel1.setBounds(583, 379, 156, 40);
		panel.add(btnCancel1);
		
		JLabel labelBankName1 = new JLabel(b.getBankName());
		labelBankName1.setFont(new Font("Modern No. 20", Font.PLAIN, 24));
		labelBankName1.setBounds(306, 26, 275, 30);
		panel.add(labelBankName1);
		
		
		JLabel lblEnterYourTo = new JLabel("Enter Your To Account Number");
		lblEnterYourTo.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		lblEnterYourTo.setBounds(244, 114, 308, 40);
		panel_1.add(lblEnterYourTo);
		
		textField1 = new JTextField();
		textField1.setBounds(272, 164, 206, 33);
		panel_1.add(textField1);
		textField1.setColumns(10);
		
		JButton buttonConfm = new JButton("CONFIRM");
		buttonConfm.addActionListener(new ActionListener() { // to check if To account type is valid
			public void actionPerformed(ActionEvent e) {
				try {
					
					receiverAccNumber = Integer.parseInt(textField1.getText());
					AccountHolder a = new AccountHolder(receiverAccNumber);
					if((receiverAccountType == "Savings") && (a.isSaving() == 1)) {
						switchJpanels(panel_2);
					} else if((receiverAccountType == "Current") && (a.isCurrent() == 1)) {
						switchJpanels(panel_2);
					} else {
						JOptionPane.showMessageDialog(null," Your 'TO' Account Type is invalid");
						dispose();
						ATM atm = new ATM();
						atm.getFrame().setVisible(true);
					}
				} catch(Exception e12) {
					JOptionPane.showMessageDialog(null,"Please Enter valid Account Number");
				}
			}
		});
		buttonConfm.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		buttonConfm.setBounds(618, 326, 156, 40);
		panel_1.add(buttonConfm);
		
		JButton btnCancel2 = new JButton("CANCEL");   // to go to the initial page
		btnCancel2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ATM atm = new ATM();
				atm.getFrame().setVisible(true);
			}
		});
		btnCancel2.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		btnCancel2.setBounds(618, 389, 156, 40);
		panel_1.add(btnCancel2);
		
		JLabel labelBankName2 = new JLabel(b.getBankName());
		labelBankName2.setFont(new Font("Modern No. 20", Font.PLAIN, 24));
		labelBankName2.setBounds(277, 35, 275, 30);
		panel_1.add(labelBankName2);
		
		JLabel lblEnterAmount = new JLabel("Enter Amount");
		lblEnterAmount.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		lblEnterAmount.setBounds(243, 148, 248, 40);
		panel_2.add(lblEnterAmount);
		
		textField2 = new JTextField();
		textField2.setBounds(243, 204, 141, 30);
		panel_2.add(textField2);
		textField2.setColumns(10);
		
		JButton buttonCnfm1 = new JButton("CONFIRM");
		buttonCnfm1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					amountSent = Integer.parseInt(textField2.getText()); 
					if(amountSent > 0)
						switchJpanels(panel_3);
					else
						JOptionPane.showMessageDialog(null,"Please Enter valid Amount");
				} catch(Exception e13) {
					JOptionPane.showMessageDialog(null,"Please Enter valid Amount");
				}
			}
		});
		buttonCnfm1.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		buttonCnfm1.setBounds(614, 335, 156, 40);
		panel_2.add(buttonCnfm1);
		
		JButton btnCancel3 = new JButton("CANCEL");  // to go to the initial page
		btnCancel3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ATM atm = new ATM();
				atm.getFrame().setVisible(true);
			}
		});
		btnCancel3.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		btnCancel3.setBounds(614, 396, 156, 40);
		panel_2.add(btnCancel3);
		
		JLabel labelBankName3 = new JLabel(b.getBankName());
		labelBankName3.setFont(new Font("Modern No. 20", Font.PLAIN, 24));
		labelBankName3.setBounds(293, 50, 275, 30);
		panel_2.add(labelBankName3);
		
		JLabel label2 = new JLabel("Select Your From Account Type");
		label2.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		label2.setBounds(246, 152, 308, 40);
		panel_3.add(label2);
		
		DateTimeFormatter dateOnly = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		
		JButton button = new JButton("SAVINGS");
		button.addActionListener(new ActionListener() {// to transfer money from saving account if all conditions are satisfied
			public void actionPerformed(ActionEvent e) {
				try {
					int tc = 0;
					if(ah.getBankCode() != b.getBankCode()) {
						tc = b.getTransactionCharge();
					}
					if(ah.isSaving() == 1) {
					if((amountSent+tc) <= ah.getCashS()) {
						
						ah.setCashS(ah.getCashS() - (double)amountSent - tc);
						
						AccountHolder a = new AccountHolder(receiverAccNumber);
						if(receiverAccountType == "Savings") {
							a.setCashS(a.getCashS() + (double)amountSent);
						}else {
							a.setCashC(a.getCashC() + (double)amountSent);
						}
						
						DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
						LocalDateTime now = LocalDateTime.now();
						textPane.setText("\n      "+b.getBankName()+"\n\n"
										+"Date\t  :  " + date.format(now)+"\n"
										+"Account No\t  :  XXX" + ah.getAccNum()%100 + "\n"
										+"Transaction\t  :  Transfer\n"
										+"Amount\t  :  " + amountSent + "\n"
										+"ATM charge\t  :  " + tc + "\n"
									    +"Account Type  :  Savings\n"
									    +"To Acc No\t  :  " + receiverAccNumber + "\n"
									    +"Balance\t  :  " + ah.getCashS() + "\n\n"
									    +"    Thank You for choosing us");
						b.addTransaction(ah.getAccNum(), date.format(now), "TRANSFER", "Savings", -amountSent);
						b.addTransaction(a.getAccNum(), date.format(now), "TRANSFER", receiverAccountType, +amountSent);
						switchJpanels(Bill);
					}
					else {
						JOptionPane.showMessageDialog(null,"Sorry, You do Not have Enough money to send");
					}
					}
					else {
						JOptionPane.showMessageDialog(null, "Sorry, You do Not have a Saving Account");
					}
				} catch(Exception e11) {
					JOptionPane.showMessageDialog(null, e11);
				}
			}
		});
		button.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		button.setBounds(607, 277, 156, 40);
		panel_3.add(button);
		
		JButton button_1 = new JButton("CURRENT"); // to transfer money from current account if all conditions are satisfied
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int tc = 0;
					if(ah.getBankCode() != b.getBankCode()) {
						tc = b.getTransactionCharge();
					}
					
					if(ah.isCurrent() == 1) {
					if((amountSent+tc) <= (ah.getCashC() + ah.getOverdraftLimit())) {
						
						ah.setCashC(ah.getCashC() - (double)amountSent - tc);
						
						AccountHolder a = new AccountHolder(receiverAccNumber);
						if(receiverAccountType == "Savings") {
							a.setCashS(a.getCashS() + (double)amountSent);
						}else {
							a.setCashC(a.getCashC() + (double)amountSent);
						}
						
						DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
						LocalDateTime now = LocalDateTime.now();
						textPane.setText("             "+b.getBankName()+"\n\n"
										+"Date\t  :  " + date.format(now)+"\n"
										+"Account No\t  :  XXX" + ah.getAccNum()%100 + "\n"
										+"Transaction\t  :  Transfer\n"
										+"Amount\t  :  " + amountSent + "\n"
										+"ATM charge\t  :  " + tc + "\n"
									    +"Account Type  :  Current\n"
									    +"To Acc No\t  :  " + receiverAccNumber + "\n"
									    +"Balance\t  :  " + ah.getCashC() + "\n\n"
									    +"      Thank You for choosing us");
						b.addTransaction(ah.getAccNum(), date.format(now), "TRANSFER", "Current", -amountSent);
						b.addTransaction(a.getAccNum(), date.format(now), "TRANSFER", receiverAccountType, amountSent);
						switchJpanels(Bill);
					}
					else {
						JOptionPane.showMessageDialog(null,"Sorry, You do Not have Enough money to send");
					}	
					}
					else {
						JOptionPane.showMessageDialog(null, "Sorry, You do Not have a Current Account");
					}
						
				} catch(Exception e11) {
					JOptionPane.showMessageDialog(null, e11);
				}
			}
		});
		button_1.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		button_1.setBounds(607, 342, 156, 40);
		panel_3.add(button_1);
		
		JButton button_2 = new JButton("CANCEL");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ATM atm = new ATM();
				atm.getFrame().setVisible(true);
			}
		});
		button_2.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		button_2.setBounds(607, 401, 156, 40);
		panel_3.add(button_2);
		
		JLabel labelBankName4 = new JLabel(b.getBankName());
		labelBankName4.setFont(new Font("Modern No. 20", Font.PLAIN, 24));
		labelBankName4.setBounds(279, 27, 275, 30);
		panel_3.add(labelBankName4);
		
		
	}
}
