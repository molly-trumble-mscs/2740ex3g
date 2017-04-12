package ex3g;
import java.text.DecimalFormat;

public class Payroll {
	private int id;
	private String name;
	private double payRate;
	private double hours;
	
	public Payroll (int id, String name, double payRate, double hours) {
		this.id = id;
		this.name = name;
		this.payRate = payRate;
		this.hours = hours;
	}

	public int getId() {
		return this.id;
	}

	public boolean setId(int id) {
		if (id > 100) {
			this.id = id;
			return true;
		}
		else {
			return false;
		}
	}

	public String getName() {
		return this.name;
	}

	public boolean setName(String name) {
		if (name.isEmpty()){
			return false;
		}
		else {
			this.name = name;
			return true;
		}
	}

	public double getHours() {
		return this.hours;
	}

	public void setHours(double hours) {
		this.hours = hours;
	}
	
	public double getPayRate() {
		return this.payRate;
	}
	
	public boolean setPayRate(double payRate) {
		if (payRate >= 7.25 && payRate <= 100) {
			this.payRate = payRate;
			return true;
		}
		else {
			return false;
		}
	}
	
	public double calcGrossPay() {
		double grossPay = 0, overtimePay = 0;
		// determine weather employee worked over 40 hours
		if (this.hours > 40)
		{
			// calculate regular pay for first 40 hrs
			grossPay = 40 * this.payRate;
			
			// calculate overtime pay
			overtimePay = (this.hours - 40) * (payRate * 1.5);
			
			// add overtime to regular pay
			grossPay += overtimePay;
		}
		else
		{
			// no overtime worked
			grossPay = this.payRate * this.hours;
		}
		return grossPay;
	}
	
	public void addHours(double hours) {
		this.hours += hours;
	}

	@Override
	public String toString() {
		DecimalFormat rateFmt = new DecimalFormat("0.00");
		return this.id + " " + this.name + ", payRate=" + rateFmt.format(this.payRate);
	}

}
