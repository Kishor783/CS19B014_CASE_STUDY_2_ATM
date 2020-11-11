package ATM_CASESTUDY;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PinChange extends JFrame {

	private JPanel contentPane;
	JLayeredPane layeredPane;

	AccountHolder ah;  // to get details of present user of ATM
	Bank b = new Bank(); // to get conditions and details of ATM
	private JTextField textField1;
	private JTextField textField2;
	
	public void switchJpanels(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}
	
	public PinChange(int accNum) {
		ah = new AccountHolder(accNum);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 761, 489);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 754, 459);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		JPanel panel = new JPanel();
		layeredPane.add(panel, "name_10519017221700");
		panel.setLayout(null);
		
		JPanel result = new JPanel();
		layeredPane.add(result, "name_10526373136700");
		result.setLayout(null);
		
		JLabel lblEnterNewPin = new JLabel("Please Enter Your New PIN");
		lblEnterNewPin.setFont(new Font("Book Antiqua", Font.PLAIN, 20));
		lblEnterNewPin.setBounds(232, 123, 264, 37);
		panel.add(lblEnterNewPin);
		
		JLabel lblPleaseReenterYour = new JLabel("Please Re-enter Your New PIN");
		lblPleaseReenterYour.setFont(new Font("Book Antiqua", Font.PLAIN, 20));
		lblPleaseReenterYour.setBounds(232, 236, 291, 37);
		panel.add(lblPleaseReenterYour);
		
		textField1 = new JTextField();
		textField1.setBounds(261, 170, 178, 37);
		panel.add(textField1);
		textField1.setColumns(10);
		
		textField2 = new JTextField();
		textField2.setBounds(261, 283, 178, 37);
		panel.add(textField2);
		textField2.setColumns(10);
		
		JLabel label = new JLabel(b.getBankName());
		label.setFont(new Font("Modern No. 20", Font.PLAIN, 24));
		label.setBounds(251, 31, 382, 30);
		panel.add(label);
		
		JButton button1 = new JButton("CONFIRM");  
		button1.addActionListener(new ActionListener() {   // to change PIN if all conditions are satisfied
			private boolean z = false;

			public void actionPerformed(ActionEvent e) {
				try {
					int check = 0;
					try {
						int a1 = Integer.parseInt(textField1.getText());
						int a2 = Integer.parseInt(textField2.getText());
						
						if(a1 < 0 || a2 < 0) {
							check = 1;
						}
						
					} catch(Exception c) {
						z  = true;
					}
					
					int newPin1 = Integer.parseInt(textField1.getText());
					int newPin2 = Integer.parseInt(textField2.getText());
					if(check == 0) {
						if(textField1.getText().length() == 5) {
							if(newPin1 == newPin2) {
								ah.setPIN(newPin1);
								switchJpanels(result);
							}
							else {
								JOptionPane.showMessageDialog(null,"Plese Re-enter same PIN ");
							}
						}
						else {
							JOptionPane.showMessageDialog(null,"Please Enter only Five Digit number");
						}
					}
					else {
						JOptionPane.showMessageDialog(null,"Please Enter valid number only");
					}
					
				} catch(Exception e12) {
					if(z)
						JOptionPane.showMessageDialog(null,"Please Enter valid number only");
					else
						JOptionPane.showMessageDialog(null,e12);
				}
			}
		});
		button1.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		button1.setBounds(568, 322, 131, 36);
		panel.add(button1);
		
		JButton button2 = new JButton("CANCEL");
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ATM atm = new ATM();
				atm.getFrame().setVisible(true);
			}
		});
		button2.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		button2.setBounds(568, 379, 131, 36);
		panel.add(button2);
		
		JLabel lblYourPinIs = new JLabel("Your PIN is Sucessfully Changed");
		lblYourPinIs.setFont(new Font("Book Antiqua", Font.PLAIN, 20));
		lblYourPinIs.setBounds(204, 185, 331, 37);
		result.add(lblYourPinIs);
		
		JLabel label_1 = new JLabel(b.getBankName());
		label_1.setFont(new Font("Modern No. 20", Font.PLAIN, 24));
		label_1.setBounds(242, 57, 382, 30);
		result.add(label_1);
		
		JButton button3 = new JButton("New Transaction");
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ATM atm = new ATM();
				atm.getFrame().setVisible(true);
			}
		});
		button3.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		button3.setBounds(506, 346, 180, 36);
		result.add(button3);
	}
}
