package nl.ecbp.ECBPMARKET.helpers;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class StaticRounder {
	public static BigDecimal round(double value){
		return BigDecimal.valueOf(value).setScale(2,
			RoundingMode.HALF_UP);
	}
}
