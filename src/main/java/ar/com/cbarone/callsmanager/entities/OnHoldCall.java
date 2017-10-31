package ar.com.cbarone.callsmanager.entities;

import java.util.Calendar;
import java.util.Date;

import org.joda.time.Interval;

/**
 * Esta clase es un wrapper de llamadas en espera
 * 
 * @author cbarone
 *
 */

public class OnHoldCall {
	private Thread call;
	private Date timeAssigned;

	public OnHoldCall(Thread call) {
		this.setCall(call);
		this.timeAssigned = Calendar.getInstance().getTime();
	}

	/**
	 * @return the call
	 */
	public Thread getCall() {
		return call;
	}

	/**
	 * @param call the call to set
	 */
	public void setCall(Thread call) {
		this.call = call;
	}
	
	public String getTimeWaited(){
		Interval interval = new Interval(this.timeAssigned.getTime(), Calendar.getInstance().getTime().getTime());
		return "Time on hold: " + interval.toString();
	}
}
