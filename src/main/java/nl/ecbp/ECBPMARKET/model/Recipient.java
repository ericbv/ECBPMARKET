package nl.ecbp.ECBPMARKET.model;

public class Recipient {

	private Commodity c;	
private double total;
private double oldBalance;
private double newBalance;
private double newPrice;

public Recipient(double total,double oldBalance,double newBalance,double newPrice,Commodity c) {
	this.total = total;
	this.oldBalance =oldBalance;
	this.newBalance=newBalance;
	this.newPrice =newPrice;
	this.c =c;
}

public Commodity getC() {
	return c;
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

public double getNewPrice() {
	return newPrice;
}

public void setNewPrice(double newPrice) {
	this.newPrice = newPrice;
}


}
