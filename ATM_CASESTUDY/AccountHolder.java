package ATM_CASESTUDY;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Random;

import javax.swing.*;

import java.nio.ByteBuffer;

public class AccountHolder {
	
	private String name = null;      // user name
	private int AccountNumber;  // user account number
	private int PIN;           // pin of user
	private double cashInSaving;     // cash in user's saving account
	private double cashInCurrent;    // cash in user's current account
	private int overdraftLimit;      // overdraft limit of user
	private int isCurrentAcc;       // 1 if user has current account else 0
	private int isSavingAcc;      // 1 if user has saving account else 0
	private int isBlocked;      // it is 1 if card is blocked else 0
	private String OTP;       // OTP of user
	private int bankCode;     //  bank code of user where his/her account is present. this is used for transaction charges of different bank account holders
	
	Connection cn = DataConnection.dbConnector();  // connection to database
	PreparedStatement ps = null;
	ResultSet rs = null;
	int counter = 0;

	
	AccountHolder(int accNum, String otp){   // constructor
		String query = "select * from accHolders where accountno=?";
		
		try {
			OTP = otp;
			ps = cn.prepareStatement(query);
			ps.setInt(1, accNum);
			rs = ps.executeQuery();
			while(rs.next()) {
				this.AccountNumber = accNum;
				this.cashInCurrent = rs.getDouble("moneyincurrent");
				this.cashInSaving = rs.getDouble("moneyinsaving");
				this.name = rs.getString("name");
				this.PIN = rs.getInt("pin");
//				pin = Integer.toString(PIN).getBytes();
				this.overdraftLimit = rs.getInt("overdraftlimit");
				this.isCurrentAcc= rs.getInt("currentAcc");
				this.isSavingAcc = rs.getInt("savingAcc");
				this.isBlocked = rs.getInt("isblocked");
				this.bankCode = rs.getInt("bankcode");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	AccountHolder(int accNum){   // constructor
		String query = "select * from accHolders where accountno=?";
		
		try {
			
			ps = cn.prepareStatement(query);
			ps.setInt(1, accNum);
			rs = ps.executeQuery();
			while(rs.next()) {
				this.AccountNumber = accNum;
				this.cashInCurrent = rs.getDouble("moneyincurrent");
				this.cashInSaving = rs.getDouble("moneyinsaving");
				this.name = rs.getString("name");
				this.PIN = rs.getInt("pin");
//				pin = Integer.toString(PIN).getBytes();
				this.overdraftLimit = rs.getInt("overdraftlimit");
				this.isCurrentAcc= rs.getInt("currentAcc");
				this.isSavingAcc = rs.getInt("savingAcc");
				this.isBlocked = rs.getInt("isblocked");
				this.bankCode = rs.getInt("bankcode");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	int getPIN() {   // to get  pin of user
		return PIN;
	}
	int getAccNum() {           // to get account number of user
		return AccountNumber;
	}
	double getCashS() {         // to get cash in savings account of user
		return cashInSaving;
	}
	double getCashC() {           // to get cash in current account of user
		return cashInCurrent;
	}
	int getOverdraftLimit() {     // overdraft limit of user
		return overdraftLimit;
	}   
	int isCurrent() {              // to check whether user has current account
		return isCurrentAcc;
	}
	int isSaving() {              // to check whether user has saving account
		return isSavingAcc;
	}
	String getName() {             // to get name of user
		return name;
	}
	String getOTP() {
		return OTP;
	}
	int getBankCode() {
		return bankCode;
	}
	int isAccBlocked() {
		return isBlocked;
	}
	
	void setPIN(int pin) throws NoSuchAlgorithmException {      // to set pin in database
		String query = "update accholders set pin=? where AccountNo=? ";
		try {
			ps = cn.prepareStatement(query);
			int p = new Bank().encryptPIN(pin);
			
			ps.setInt(1, p);
			ps.setInt(2, AccountNumber);
			ps.execute();
			PIN = p;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	void setCashS(double cash) {   // to set cash in savings in database
		String query = "update accholders set moneyinsaving=? where AccountNo=? ";
		try {
			ps = cn.prepareStatement(query);
			ps.setDouble(1, cash);
			ps.setInt(2, AccountNumber);
			ps.execute();
			cashInSaving = cash;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	void setCashC(double cash) {     // to set cash in current in database
		String query = "update accholders set moneyincurrent=? where AccountNo=? ";
		try {
			ps = cn.prepareStatement(query);
			ps.setDouble(1, cash);
			ps.setInt(2, AccountNumber);
			ps.execute();
			cashInCurrent = cash;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	void setIsBlocked(int t) {     // to block card or unblock
		String query = "update accholders set isblocked=? where AccountNo=? ";
		try {
			ps = cn.prepareStatement(query);
			ps.setInt(2, AccountNumber);
			ps.setInt(1, t);
			ps.execute();
			isBlocked = t;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	boolean isValid(int accNum) {  // to check whether given account number is present in database or not
		String Querry = "select * from accholders where accountno = ?";
		try {
			ps = cn.prepareStatement(Querry);
			ps.setInt(1, accNum);
			rs = ps.executeQuery();
			
			int count = 0;
			while(rs.next()) {
				count++;
			}
			if(count == 1) {
				return true;
			} 
			else if(count < 1) {
				JOptionPane.showMessageDialog(null, "Account number is incorrect, please try again");
				return false;
			} 
			else {
				JOptionPane.showMessageDialog(null, "Duplicate account number");
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	return false;	
	
	}
	
	
	
}











