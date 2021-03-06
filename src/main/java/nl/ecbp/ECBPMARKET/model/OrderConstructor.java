package nl.ecbp.ECBPMARKET.model;

import java.math.BigDecimal;



public class OrderConstructor {

	public Order GenerateOrder(boolean buy, Commodity commodity, int amount) {
		Order o = new Order();
		o.setCurrentPrice(commodity.getValue());

		for (int x = 1; x <= amount; x++) {
			BigDecimal minValue = BigDecimal.valueOf(commodity.getMinValue());
			BigDecimal maxValue = BigDecimal.valueOf(commodity.getMaxValue());
			BigDecimal changeRate = BigDecimal.valueOf(commodity
					.getChangeRate());
			
			if (buy) {
				o.addTotal(o.getCurrentPrice());// add the minimum to total
				o.addPrice(o.getCurrentPrice() * commodity.getChangeRate());

			} else {
				o.addTotal(o.getCurrentPrice()*0.8);// add the minimum to total
				o.subtractPrice(o.getCurrentPrice() * changeRate.doubleValue());
			}
			if (o.getCurrentPrice() < minValue.doubleValue()) {
				o.setCurrentPrice(minValue.doubleValue());
			} else if (o.getCurrentPrice() > maxValue.doubleValue()) {
				o.setCurrentPrice(maxValue.doubleValue());
			}
		}
		return o;
	}
}
