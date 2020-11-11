package ATM_CASESTUDY;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

public class MiniStatement extends JFrame {

	private JPanel contentPane;
	JLayeredPane layeredPane;
	
	AccountHolder ah;  // To store details of present user of atm
	Bank b = new Bank(); // to get conditions and details for transaction
	
	public void switchJpanels(JPanel panel) { // to switch between different panels
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}
	
	public MiniStatement(int accNum) {
		ah = new AccountHolder(accNum);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 837, 514);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, "name_15387006155900");
		layeredPane.setLayout(new CardLayout(0, 0));
		
		JPanel panel1 = new JPanel();
		layeredPane.add(panel1, "name_15433792163300");
		panel1.setLayout(null);
		
		JPanel panel2 = new JPanel();
		layeredPane.add(panel2, "name_15433774824600");
		panel2.setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("Courier New", Font.PLAIN, 12));
		textPane.setBounds(138, 107, 265, 307);
		panel2.add(textPane);
		
		JLabel label_1 = new JLabel("Please Select Account Type");
		label_1.setFont(new Font("Book Antiqua", Font.PLAIN, 24));
		label_1.setBounds(245, 133, 326, 37);
		panel1.add(label_1);
		
		DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		
		JButton button_1 = new JButton("SAVINGS");   // for statement of savings account
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(ah.isSaving() == 1) {
						textPane.setText("\t\t"+b.getBankName()+"\n\n"
					   	        +"Date\t  :  "+date.format(now)+"\n"
					   	        +"Statement for Acc No  :  XXX" + ah.getAccNum()%100+"\n"
					   	        +"Account Type\t  :  Savings\n\n"
					   	        +b.getMiniStaement(ah.getAccNum()));
						
						switchJpanels(panel2);
					} else {
						JOptionPane.showMessageDialog(null,"Sorry, You do not have a Savings account");
					}
					
				} catch(Exception e2) {
					JOptionPane.showMessageDialog(null, e2);
				}
			}
		});
		button_1.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		button_1.setBounds(604, 293, 141, 37);
		panel1.add(button_1);
		 
		JButton button_2 = new JButton("CURRENT");      // for statement of current account
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(ah.isCurrent() == 1) {
						textPane.setText("\t\t"+b.getBankName()+"\n\n"
							   	        +"Date\t  :  "+date.format(now)+"\n"
							   	        +"Statement for Acc No  :  XXX" + ah.getAccNum()%100+"\n"
							   	        +"Account Type\t  :  Current\n\n"
							   	        +b.getMiniStaement(ah.getAccNum()));
						
						switchJpanels(panel2);
					} else {
						JOptionPane.showMessageDialog(null,"Sorry, You do not have a Current account");
					}
					
				} catch(Exception e2) {
					JOptionPane.showMessageDialog(null, e2);
				}
			}
		});
		button_2.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		button_2.setBounds(604, 346, 141, 37);
		panel1.add(button_2);
		
		JButton button_3 = new JButton("CANCEL");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ATM atm = new ATM();
				atm.getFrame().setVisible(true);
			}
		});
		button_3.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		button_3.setBounds(604, 400, 141, 37);
		panel1.add(button_3);
		
		JLabel label = new JLabel(b.getBankName());
		label.setFont(new Font("Modern No. 20", Font.PLAIN, 24));
		label.setBounds(258, 42, 275, 30);
		panel1.add(label);
		
		JLabel lblEe = new JLabel("Receipt");
		lblEe.setFont(new Font("Book Antiqua", Font.PLAIN, 24));
		lblEe.setBounds(138, 56, 115, 37);
		panel2.add(lblEe);
		
		JButton button = new JButton("New Transaction");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ATM atm = new ATM();
				atm.getFrame().setVisible(true);
			}
		});
		button.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		button.setBounds(559, 362, 182, 40);
		panel2.add(button);
		
	}
}
