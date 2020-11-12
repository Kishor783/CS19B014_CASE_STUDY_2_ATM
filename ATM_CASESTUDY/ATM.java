
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.awt.event.ActionEvent;

public class ATM {

	private JFrame frame;
	JLayeredPane layeredPane;
	private JTextField textField;
	
	AccountHolder ah; // Account holder object
	Bank b = new Bank(); // Bank object   
	int accNumber;  // to store account number
	String OTP;   // to store OTP of present user

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ATM window = new ATM();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

		//10025   11223
		//22312   96748
		//10237   56565
		//10236   63201
		//10235   10235
		//14523   79513
		//22222   32383
		

	Connection connection = null; 
	private JPasswordField OTPField;
	
	public ATM() {
		initialize();
		connection = DataConnection.dbConnector();
	}
	
	JFrame getFrame() {
		return frame;
	}
	
	public void switchJpanels(JPanel panel) { // to switch between different panels
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}
	LocalDateTime t = null;
	private JPasswordField passwordField_1;
	public LocalDateTime setTime(int count) {
		if(count == 3) {
			t = LocalDateTime.now();
		}
		
		return t;
	}
	
	private void initialize() {
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 853, 516);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		layeredPane = new JLayeredPane();
		frame.getContentPane().add(layeredPane, "name_34246597636300");
		layeredPane.setLayout(new CardLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 0, 829, 518);
		layeredPane.add(panel, "name_34504337948499");
		panel.setLayout(null);
		
		JPanel otpPanel = new JPanel();
		otpPanel.setBounds(0, 0, 839, 518);
		layeredPane.add(otpPanel, "name_34504344737200");
		otpPanel.setLayout(null);
		
		JPanel pinPanel = new JPanel();
		layeredPane.add(pinPanel, "name_7037674681500");
		pinPanel.setLayout(null);
		
		JLabel lblEnterYourOtp = new JLabel("Enter your OTP sent to your registered mobile number");
		lblEnterYourOtp.setFont(new Font("Century", Font.BOLD, 20));
		lblEnterYourOtp.setBounds(147, 118, 557, 41);
		otpPanel.add(lblEnterYourOtp);
		
		OTPField = new JPasswordField();
		OTPField.setBounds(302, 196, 200, 33);
		otpPanel.add(OTPField);
		
		JButton button = new JButton("CONFIRM");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(ah.counter == b.getNoOfTries()) {
					ah.setIsBlocked(1);
					test t = new test(ah.getAccNum());   // calls test class which contains timerr method
					t.timerr(ah.getAccNum());  // sets the timer so that card is unblocked after certain time.
					JOptionPane.showMessageDialog(null, "Your Card is Blocked for "+b.getTimeLimit()+" minutes");
				}
				if(ah.isAccBlocked() == 0) {
					if(ah.counter < b.getNoOfTries()) {
						if(OTPField.getText().equals(OTP)) {
							Options pg = new Options(ah.getAccNum(), OTP, frame);
							frame.setVisible(false);
							pg.setVisible(true);
						}
						else {
							JOptionPane.showMessageDialog(null, "OTP is incorrect, Please try again");	
							ah.counter++;
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Sorry, Your Card is Blocked due to continuous wrong entries of pin or otp");
				}
				
			}
		});
		button.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		button.setBounds(622, 345, 157, 36);
		otpPanel.add(button);
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				switchJpanels(panel);
			}
		});
		btnCancel.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		btnCancel.setBounds(622, 402, 157, 36);
		otpPanel.add(btnCancel);
		
		JLabel label2 = new JLabel("Please enter your Account Number");
		label2.setFont(new Font("Century", Font.BOLD, 20));
		label2.setBounds(215, 105, 381, 26);
		panel.add(label2);
		
		DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		
		JButton confirm = new JButton("Login through PIN"); // for login through PIN by user
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PreparedStatement ps = null;
				ResultSet rs = null;

				try {
					String Querry = "select * from accholders where accountno = ?";
					ps = connection.prepareStatement(Querry);
					ps.setString(1, textField.getText());
					
					rs = ps.executeQuery();
					int count = 0;
					while(rs.next()) {
						accNumber = rs.getInt(1);
						count++;
					}
					if(count == 1) {
						Random rand = new Random(); 
					    int randomNum = rand.nextInt(100000); 
					    OTP = String.format("%05d", randomNum);
						
						ah = new AccountHolder(accNumber, OTP);
					
						if(ah.isAccBlocked() == 0) {
							System.out.println(OTP);
							switchJpanels(pinPanel);
						}
						else {
							JOptionPane.showMessageDialog(null, "Sorry, Your Card is Blocked");
							}
					
					} 
					else if(count < 1) {
						JOptionPane.showMessageDialog(null, "Account number is incorrect, please try again");
					} 
					else {
						JOptionPane.showMessageDialog(null, "Duplicate account number");
					}
					
				} catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				} finally {
					try {
						rs.close();
						ps.close();
					} catch(Exception ex1) {
						JOptionPane.showMessageDialog(null, ex1);
					}
				}
			} 

		});
		confirm.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		confirm.setBounds(574, 349, 210, 36);
		panel.add(confirm);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(268, 157, 200, 33);
		panel.add(textField);
		
		JLabel bankName = new JLabel(b.getBankName());
		bankName.setFont(new Font("Modern No. 20", Font.PLAIN, 30));
		bankName.setBounds(267, 32, 346, 30);
		panel.add(bankName);
		
		JButton button1 = new JButton("Login through OTP");   // for login through OTP by user without using OTP
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PreparedStatement ps = null;
				ResultSet rs = null;
				try {
					String Querry = "select * from accholders where accountno = ?";
					ps = connection.prepareStatement(Querry);
					ps.setString(1, textField.getText());
					
					rs = ps.executeQuery();
					int count = 0;
					while(rs.next()) {
						accNumber = rs.getInt(1);
						count++;
					}
					if(count == 1) {
						Random rand = new Random(); 
					    int randomNum = rand.nextInt(100000); 
					    OTP = String.format("%05d", randomNum);
					    
						ah = new AccountHolder(accNumber, OTP);
						if(ah.isAccBlocked() == 0) {
							System.out.println(OTP);
							switchJpanels(otpPanel);
						}
						else {
							JOptionPane.showMessageDialog(null, "Sorry, Your Card is Blocked");
							}
					} 
					else if(count < 1) {
						JOptionPane.showMessageDialog(null, "Account number is incorrect, please try again");
					} 
					else {
						JOptionPane.showMessageDialog(null, "Duplicate account number");
					}
					
				} catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				} finally {
					try {
						rs.close();
						ps.close();
					} catch(Exception ex1) {
						JOptionPane.showMessageDialog(null, ex1);
					}
				}
			    
			}
		});
		button1.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		button1.setBounds(574, 412, 210, 36);
		panel.add(button1);
		
		JLabel label = new JLabel("Enter your PIN");
		label.setFont(new Font("Century", Font.BOLD, 20));
		label.setBounds(291, 119, 199, 33);
		pinPanel.add(label);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(269, 179, 199, 33);
		pinPanel.add(passwordField_1);
		
		JButton button_1 = new JButton("CONFIRM");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(ah.counter == b.getNoOfTries()) {
					ah.setIsBlocked(1);
					test t = new test(ah.getAccNum());   // calls test class which contains timerr method
					t.timerr(ah.getAccNum());  // sets the timer so that card is unblocked after certain time.
					JOptionPane.showMessageDialog(null, "Your Card is Blocked for "+b.getTimeLimit()+" minutes");
				}

				if(ah.isAccBlocked() == 0) {
				PreparedStatement ps = null;
				ResultSet rs = null;
					if(ah.counter < b.getNoOfTries()) {
						try {
							String Querry = "select * from accholders where accountno = ? and pin = ?";
							ps = connection.prepareStatement(Querry);
							ps.setInt(1, accNumber);
							String pinText = passwordField_1.getText();
							
							int p = b.encryptPIN(Integer.parseInt(pinText));
							
							ps.setInt(2, p);
							rs = ps.executeQuery();
							int count = 0;
							while(rs.next()) {
								count++;
							}
							if(count == 1) {
								Options pg = new Options(ah.getAccNum(), OTP, frame);
								frame.dispose();
								pg.setVisible(true);
								
							} 
							else if(count < 1) {
								JOptionPane.showMessageDialog(null, "Account number and pin is incorrect, please try again");
							} 
							else {
								JOptionPane.showMessageDialog(null, "Duplicate account number and pin");
							}
							
						} catch(Exception ex) {
							JOptionPane.showMessageDialog(null, ex);
						} finally {
							try {
								rs.close();
								ps.close();
							} catch(Exception ex1) {
								JOptionPane.showMessageDialog(null, ex1);
							}
						}
					} 
					ah.counter++;
				}
			}
		});
		button_1.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		button_1.setBounds(606, 339, 162, 36);
		pinPanel.add(button_1);
		
		JButton btnCancel_1 = new JButton("CANCEL");
		btnCancel_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				switchJpanels(panel);
			}
		});
		btnCancel_1.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		btnCancel_1.setBounds(606, 397, 162, 36);
		pinPanel.add(btnCancel_1);
		
		
	}
}
