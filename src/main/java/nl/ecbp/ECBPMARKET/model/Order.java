package nl.ecbp.ECBPMARKET.model;

public class Order {
	public double currentPrice;;
	public double total;
	public double getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	
	public void addTotal(double d) {
		total += d;
	}
	public void subtractTotal(double subtrahend) {
		total -= subtrahend;
	}
	public void addPrice(double d) {
		currentPrice += d;
	}
	public void subtractPrice(double subtrahend) {
		currentPrice -= subtrahend;
	}
}
