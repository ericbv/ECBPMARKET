package nl.ecbp.ECBPMARKET.model;

import javax.persistence.Id;

import com.avaje.ebean.validation.NotEmpty;
import com.avaje.ebean.validation.NotNull;

public class Commodity {
    private int id;
    private String name;
    private double value;
    private double maxValue;
    private double minValue;
    private double changeRate;
    private int data;
    
	
	
    public Commodity(int id, String name, double value,
			double maxValue, double minValue, double changeRate, int data) {
		super();
		this.id = id;
		this.name = name;
		this.value = value;
		this.maxValue = maxValue;
		this.minValue = minValue;
		this.changeRate = changeRate;
		this.data = data;
	}
    
	public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getValue() {
        return value;
    }
    public void setValue(double value) {
        this.value = value;
    }
    public double getMaxValue() {
        return maxValue;
    }
    public void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }
    public double getMinValue() {
        return minValue;
    }
    public void setMinValue(double minValue) {
        this.minValue = minValue;
    }
    public double getChangeRate() {
        return changeRate;
    }
    public void setChangeRate(double changeRate) {
        this.changeRate = changeRate;
    }
    public int getData() {
        return data;
    }
    public void setData(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
    	return "id " + id
    			+ " name " + name
    			+ " data " + data
    			+ " value " + value
    			+ " minvalue " + minValue
    			+ " maxvalue " + maxValue
    			+ " cangerate " + changeRate;
    }
}
