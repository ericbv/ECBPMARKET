package nl.ecbp.ECBPMARKET.model;

public class Recipient {
	
private double total;
private double oldBalance;
private double newBalance;

public Recipient(double total,double oldBalance,double newBalance) {
	this.total = total;
	this.oldBalance =oldBalance;
	this.newBalance=newBalance;
}

public double getTotal() {
	return total;
}

public void setTotal(double total) {
	this.total = total;
}

public double getOldBalance() {
	return oldBalance;
}

public void setOldBalance(double oldBalance) {
	this.oldBalance = oldBalance;
}

public double getNewBalance() {
	return newBalance;
}

public void setNewBalance(double newBalance) {
	this.newBalance = newBalance;
}


}
