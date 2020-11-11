package ATM_CASESTUDY;
import java.util.Timer;
import java.util.TimerTask;

public class test extends TimerTask{
	static int an = 0;
	Bank b = new Bank();  // to get time limit
	public void timerr(int accNum){ // timer method for unblocking card after "timeLimit" of time
		an = accNum;
		TimerTask tasknew = new test();
		Timer timer = new Timer();
		timer.schedule(tasknew, 60000*b.getTimeLimit());
	}
   
   public void run() {
          AccountHolder ah = new AccountHolder(an);
          ah.counter = 0;
          ah.setIsBlocked(0);
   } 
}
