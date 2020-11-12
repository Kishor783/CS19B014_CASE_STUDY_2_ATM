package ATM_CASESTUDY;
import java.util.Timer;
import java.util.TimerTask;

public class test extends TimerTask{
	int an = 0;
	Bank b = new Bank();  // to get time limit
	public void timerr(int accNum){ // timer method for unblocking card after "timeLimit" of time
		
		TimerTask tasknew = new test(accNum);
		an = accNum;
		Timer timer = new Timer();
		timer.schedule(tasknew, 60000*b.getTimeLimit());
	}
	
	test(int accNum){
		this.an = accNum;
	}
   
   public void run() {
          AccountHolder ah = new AccountHolder(an);
          ah.counter = 0;
          ah.setIsBlocked(0);
          System.out.println(an + " pleaseeee");
   } 
}
