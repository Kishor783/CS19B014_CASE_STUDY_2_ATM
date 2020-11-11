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
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.Duration;
import java.time.LocalDateTime;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Options extends JFrame {

	private JPanel contentPane;
	JLayeredPane layeredPane;
	
	AccountHolder ah; // To store details of present user of ATM
	int accNum;       // This account number is passed to other frames so that we can remember our present user of ATM
	String transactionChosen;  // transaction type chosen by user

	Bank b = new Bank(); // to get details related to transactions
	private JTextField textField;

	public void switchJpanels(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}
	
	LocalDateTime t = null;
	public LocalDateTime setTime(int count) {
		if(count == 3) {
			t = LocalDateTime.now();
		}
		
		return t;
	}
	
	public Options(int num, String otp, JFrame frame) {
		ah = new AccountHolder(num, otp);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 843, 534);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, "name_39140164117200");
		layeredPane.setLayout(new CardLayout(0, 0));
		
		JPanel optionPanel = new JPanel();
		optionPanel.setBounds(0, 0, 854, 534);
		layeredPane.add(optionPanel);
		optionPanel.setLayout(null);

		JPanel panel = new JPanel();
		layeredPane.add(panel, "name_5118568524800");
		panel.setLayout(null);

		JButton button = new JButton("CHANGE PIN");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				transactionChosen = "cp";
				switchJpanels(panel);
			}
		});
		button.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		button.setBounds(23, 169, 241, 45);
		optionPanel.add(button);
		
		JButton button_1 = new JButton("BALANCE INQUIRY");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				transactionChosen = "bi";
				switchJpanels(panel);
			}
		});
		button_1.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		button_1.setBounds(23, 234, 241, 45);
		optionPanel.add(button_1);
		
		JButton button_2 = new JButton("WITHDRAWAL");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				transactionChosen = "w";
				switchJpanels(panel);
			}
		});
		button_2.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		button_2.setBounds(548, 169, 241, 45);
		optionPanel.add(button_2);
		
		JButton button_3 = new JButton("DEPOSIT");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				transactionChosen = "d";
				switchJpanels(panel);
			}
		});
		button_3.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		button_3.setBounds(548, 234, 241, 45);
		optionPanel.add(button_3);
		
		JButton button_4 = new JButton("TRANSFER");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				transactionChosen = "t";
				switchJpanels(panel);
				}
		});
		button_4.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		button_4.setBounds(548, 298, 241, 45);
		optionPanel.add(button_4);
		
		JLabel label = new JLabel(b.getBankName());
		label.setFont(new Font("Modern No. 20", Font.PLAIN, 24));
		label.setBounds(314, 37, 353, 30);
		optionPanel.add(label);
		
		JButton button_5 = new JButton("CANCEL");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ATM atm = new ATM();
				atm.getFrame().setVisible(true);
			}
		});
		button_5.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		button_5.setBounds(548, 368, 241, 45);
		optionPanel.add(button_5);
		
		JLabel label_1 = new JLabel("HI "+ah.getName());
		label_1.setFont(new Font("Courier New", Font.PLAIN, 24));
		label_1.setBounds(258, 445, 353, 30);
		optionPanel.add(label_1);
		
		JButton btnBlockCard = new JButton("BLOCK CARD");
		btnBlockCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ah.setIsBlocked(1);
					JOptionPane.showMessageDialog(null, "Your Card is Successfully Blocked");
					dispose();
					ATM atm = new ATM();
					atm.getFrame().setVisible(true);
				}catch(Exception e2){
					JOptionPane.showMessageDialog(null, e2);
				}
			}
		});
		btnBlockCard.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		btnBlockCard.setBounds(23, 298, 241, 45);
		optionPanel.add(btnBlockCard);
		
		JButton btnMiniStatement = new JButton("MINI STATEMENT");
		btnMiniStatement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				transactionChosen = "ms";
				switchJpanels(panel);
			}
		});
		btnMiniStatement.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		btnMiniStatement.setBounds(23, 368, 241, 45);
		optionPanel.add(btnMiniStatement);
				
		JLabel label_2 = new JLabel("Please Enter OTP sent to Your Registered Mobile Number");
		label_2.setFont(new Font("Book Antiqua", Font.PLAIN, 21));
		label_2.setBounds(125, 114, 543, 37);
		panel.add(label_2);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(271, 180, 218, 37);
		panel.add(textField);
		
		JButton button_6 = new JButton("CONFIRM");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(ah.getOTP().equals(textField.getText())) {
						
						if(transactionChosen.equals("cp")) {
							try {
								PinChange pc = new PinChange(ah.getAccNum());
								dispose();	
								pc.setVisible(true);
							} catch(Exception e3) {
								JOptionPane.showMessageDialog(null, e3);
							}
						}
						
						if(transactionChosen.equals("bi")) {
							try {
								BalanceInquiry bi = new BalanceInquiry(ah.getAccNum());
								dispose();	
								bi.setVisible(true);
							} catch(Exception e3) {
								JOptionPane.showMessageDialog(null, e3);
							}
						}
						
						if(transactionChosen.equals("w")) {
							try {
								Withdrawal pg = new Withdrawal(ah.getAccNum());		
								dispose();	
								pg.setVisible(true);
								} catch(Exception e3) {
									JOptionPane.showMessageDialog(null, e3);
								}
						}
						
						if(transactionChosen.equals("d")) {
							try {									
								Deposit dp = new Deposit(ah.getAccNum());
								dispose();	
								dp.setVisible(true);
								} catch(Exception e3) {
									JOptionPane.showMessageDialog(null, e3);
							}
						}
						
						if(transactionChosen.equals("t")) {
							try {									
								Transfer tp = new Transfer(ah.getAccNum());
								dispose();	
								tp.setVisible(true);
								} catch(Exception e3) {
									JOptionPane.showMessageDialog(null, e3);
							}

						}
						
						if(transactionChosen.equals("ms")) {
							try {									
								MiniStatement tp = new MiniStatement(ah.getAccNum());
								dispose();	
								tp.setVisible(true);
								} catch(Exception e3) {
									JOptionPane.showMessageDialog(null, e3);
							}

						}
						
					} 
					else {
						JOptionPane.showMessageDialog(null, "Entered OTP is incorrect");
					}
					
				} catch(Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		button_6.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		button_6.setBounds(608, 345, 159, 36);
		panel.add(button_6);
		
		JButton button_7 = new JButton("CANCEL");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ATM atm = new ATM();
				atm.getFrame().setVisible(true);
			}
		});
		button_7.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		button_7.setBounds(608, 403, 159, 36);
		panel.add(button_7);
	}
}
