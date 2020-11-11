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
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BalanceInquiry extends JFrame {

	private JPanel contentPane;
	JLayeredPane layeredPane;

	AccountHolder ah;  // To store details of present user of atm
	Bank b = new Bank(); // to get information of atm
	
	public void switchJpanels(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}
	
	public BalanceInquiry(int accNum) {
		ah = new AccountHolder(accNum);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 824, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 816, 483);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		JPanel panel = new JPanel();
		layeredPane.add(panel, "name_7354420058000");
		panel.setLayout(null);
		
		JPanel result = new JPanel();
		layeredPane.add(result, "name_7363618608700");
		result.setLayout(null);
		
		JLabel labelBankName = new JLabel(b.getBankName());
		labelBankName.setFont(new Font("Modern No. 20", Font.PLAIN, 24));
		labelBankName.setBounds(302, 54, 382, 30);
		panel.add(labelBankName);
		
		JLabel label1 = new JLabel("Please Select Account Type");
		label1.setFont(new Font("Book Antiqua", Font.PLAIN, 22));
		label1.setBounds(244, 162, 373, 37);
		panel.add(label1);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(308, 213, 167, 29);
		result.add(textPane);
		
		JButton button1 = new JButton("SAVINGS");   // to get balance info of saving account
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ah.isSaving() == 1){
					textPane.setText(Double.toString(ah.getCashS()));
					switchJpanels(result);
				}
				else
					JOptionPane.showMessageDialog(null, "Sorry, You do Not have a Saving Account");
			}
		});
		button1.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		button1.setBounds(605, 297, 141, 37);
		panel.add(button1);
		
		JButton button2 = new JButton("CURRENT");  // to get balance info of current account
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ah.isCurrent() == 1) {
					textPane.setText(Double.toString(ah.getCashC()));
					switchJpanels(result);
				}
				else
					JOptionPane.showMessageDialog(null, "Sorry, You do Not have a Current Account");
			}
		});
		button2.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		button2.setBounds(605, 344, 141, 37);
		panel.add(button2);
		
		JButton button3 = new JButton("CANCEL");
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ATM atm = new ATM();
				atm.getFrame().setVisible(true);
			}
		});
		button3.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		button3.setBounds(605, 391, 141, 37);
		panel.add(button3);
		
		JLabel lblYourAccountBalance = new JLabel("Your Account Balance is  :");
		lblYourAccountBalance.setFont(new Font("Book Antiqua", Font.PLAIN, 22));
		lblYourAccountBalance.setBounds(257, 153, 373, 37);
		result.add(lblYourAccountBalance);
		
		JButton btnNewTransaction = new JButton("New Transaction");
		btnNewTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ATM atm = new ATM();
				atm.getFrame().setVisible(true);
			}
		});
		btnNewTransaction.setFont(new Font("Book Antiqua", Font.BOLD, 18));
		btnNewTransaction.setBounds(546, 369, 214, 43);
		result.add(btnNewTransaction);
		
		JLabel label = new JLabel(b.getBankName());
		label.setFont(new Font("Modern No. 20", Font.PLAIN, 24));
		label.setBounds(308, 39, 382, 30);
		result.add(label);
	}
}
