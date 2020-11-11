package ATM_CASESTUDY;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

import javax.swing.JOptionPane;

public class Bank {
	
	private int cashInATM;   // cash in ATM
	private String bankName;  // bank name
	private int password;    // password of bank manager
	private int maxNotes;    // maximum notes able to deposit in ATM
	private int maxAmountDeposit;  // max amount which can be deposited
	private int maxAmountWithdraw;  // max amount which v=can be withdrawn from ATM
	private int bankCode;    // To recognize bank of account holder, if not same then transaction charges are applied
	private int timeLimit;   // it is the time for which user's account will be blocked when he attempts to give wrong passwords continuously
	private int noOfTries;   // this many times an user can enter wrong PIN or OTP 
	private int transactionCharge;  // this is the charge for users of other banks
	
	Connection cn = DataConnection.dbConnector();
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	Bank() {
		String query = "select * from bank";
		try {
			ps = cn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				cashInATM = rs.getInt("cashinatm");
				bankName = rs.getString("bankname");
				password = rs.getInt("password");
				maxNotes = rs.getInt("maxnotes");
				maxAmountDeposit = rs.getInt("maxcashdeposit");
				maxAmountWithdraw = rs.getInt("maxcashwithdraw");
				bankCode = rs.getInt("bankcode");
				timeLimit = rs.getInt("timelimit");
				noOfTries = rs.getInt("nooftries");
				transactionCharge = rs.getInt("transactioncharge");
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	int getTotalCash() {  // to get cash
		return cashInATM;
	}  
	int getPassword() {   // to get  password
		return password;
	}
	String getBankName() { // to get bank name
		return bankName;
	}
	int getmaxNotes() {   // to max notes
		return maxNotes;
	}
	int getmaxAmountD() { // to get max amount user can deposit in atm
		return maxAmountDeposit;
	}
	int getmaxAmountW() {  // to get max amount user can withdraw from atm
		return maxAmountWithdraw;
	}
	int getBankCode() {
		return bankCode;   // to get bank code
	}
	int getTimeLimit() {
		return timeLimit;    // to get time limit
	}
	int getNoOfTries() {
		return noOfTries;   // to  get no of tries
	}
	int getTransactionCharge() {
		return transactionCharge;
	}
	
	void setTotalCash(int cash) {  // to update total cash by bank manager
		String query = "update Bank set cashInATM=? where bankname=? ";
		try {
			ps = cn.prepareStatement(query);
			ps.setInt(1,cash);
			ps.setString(2, bankName);
			ps.execute();
			cashInATM = cash;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
	}
	
	void setBankName(String bankname) {  // to set bank name
		String query = "update bank set bankname=? where password=? ";
		try {
			ps = cn.prepareStatement(query);
			ps.setString(1, bankname);
			ps.setInt(2, password);
			ps.executeUpdate();
			bankName = bankname;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	void setMaxNotes(int maxnotes) { // to set max notes
		String query = "update bank set maxnotes=? where password=? ";
		try {
			ps = cn.prepareStatement(query);
			ps.setInt(2, password);
			ps.setInt(1, maxnotes);
			ps.execute();
			this.maxNotes = maxnotes;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	void setMaxCashD(int cash) { // to set max cash
		String query = "update bank set maxcashDeposit=? where password=? ";
		try {
			ps = cn.prepareStatement(query);
			ps.setInt(2, password);
			ps.setInt(1, cash);
			ps.execute();
			maxAmountDeposit = cash;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	void setMaxCashW(int cash) { // to set maX cash withdrawable
		String query = "update bank set maxcashWithdraw=? where password=? ";
		try {
			ps = cn.prepareStatement(query);
			ps.setInt(2, password);
			ps.setInt(1, cash);
			ps.execute();
			maxAmountWithdraw = cash;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	void setBankCode(int code) { // to set Bank Code
		String query = "update bank set bankcode=? where password=? ";
		try {
			ps = cn.prepareStatement(query);
			ps.setInt(2, password);
			ps.setInt(1, code);
			ps.execute();
			bankCode = code;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	void setTimeLimit(int tl) { // to set Time Limit
		String query = "update bank set timelimit=? where password=? ";
		try {
			ps = cn.prepareStatement(query);
			ps.setInt(2, password);
			ps.setInt(1, tl);
			ps.execute();
			timeLimit = tl;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	void setNoOfTries(int t) { // to set no of tries
		String query = "update bank set noOfTries=? where password=? ";
		try {
			ps = cn.prepareStatement(query);
			ps.setInt(2, password);
			ps.setInt(1, t);
			ps.execute();
			noOfTries = t;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	void setTransactionCharge(int tc) { // to set transaction charge
		String query = "update bank set TransactionCharge=? where password=? ";
		try {
			ps = cn.prepareStatement(query);
			ps.setInt(2, password);
			ps.setInt(1, tc);
			ps.execute();
			transactionCharge = tc;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	int encryptPIN(int pin) {  // to encrypt PIN
		MessageDigest msg;
		int p = 0;
		try {
			msg = MessageDigest.getInstance("SHA-256");
			msg.update(Integer.toString(pin).getBytes());
			byte [] digest = msg.digest();
			p = ByteBuffer.wrap(digest).getInt();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return p;
	}
	
	void addAccount(int A,String N,int P,double MS,double MC,int ODL,int SA,int CA, long PN, int BC) {  //  to add new account in database
		
		String query = "insert into accholders(accountno, name, pin, moneyinsaving, moneyincurrent, overdraftlimit, savingacc, currentacc, phonenumber, isblocked, bankcode) values(?,?,?,?,?,?,?,?,?,?,?) ";
		try {
			ps = cn.prepareStatement(query);
			ps.setInt(1, A);
			ps.setString(2, N);
			ps.setInt(3, P);
			ps.setDouble(4, MS);
			ps.setDouble(5, MC);
			ps.setInt(6, ODL);
			ps.setInt(7, SA);
			ps.setInt(8, CA);
			ps.setLong(9, PN);
			ps.setInt(10, 0);
			ps.setInt(11, BC);
			
			ps.execute();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	void delAccount(int A) {   // to delete account from database
		String query = "delete from accholders where Accountno = ?";
		try {
			ps = cn.prepareStatement(query);
			ps.setInt(1, A);
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	 
	void addTransaction(int A,String D, String T, String AT, int Am) {
		String query = "insert into ATMTransactions(date,accountno, transc, accountType, amount) values(?,?,?,?,?) ";
//		String query = "select * from transactions";
		try {
			ps = cn.prepareStatement(query);
//			rs = ps.executeQuery();
//			while(rs.next()) {
//				System.out.println(rs.getString(1)+" "+rs.getInt(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getInt(5));
//			}
			ps.setInt(2, A);
			ps.setString(1, D);
			ps.setString(3, T);
			ps.setString(4, AT);
			ps.setInt(5, Am);
			ps.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	String getMiniStaement(int accNum) {
		String query = "select * from atmtransactions where accountNo = ? order by date DESC;";
		StringBuffer stmt = null;
		try {
			ps = cn.prepareStatement(query);
			ps.setInt(1, accNum);
			rs = ps.executeQuery();
			
			stmt = new StringBuffer();
			int count = 0;
			while(rs.next()) {
				String date = rs.getString(1).substring(0, 10);
				String amount = Integer.toString(Math.abs(rs.getInt(5)));
				char sign = rs.getInt(5) > 0 ? '+' : '-';
				String transaction = rs.getString(3);
				stmt.append(date+String.format("%8s", amount)+".00"+sign+transaction+"\n");
				count++;
				if(count == 8) {
					break;
				}
			}
			AccountHolder a = new AccountHolder(accNum);
			double balance = a.isCurrent() == 1 ? a.getCashC() : a.getCashS();
			stmt.append("Balance  :  "+balance+"\n");
			stmt.append("\n      Thank you for choosing us");
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
		
		return stmt.toString();
	}

}
