package nl.ecbp.ECBPMARKET.controllers;

import nl.ecbp.ECBPMARKET.ECBPMarket;
import nl.ecbp.ECBPMARKET.exceptions.CommodityNotFoundException;
import nl.ecbp.ECBPMARKET.exceptions.DuplicateCommodityException;
import nl.ecbp.ECBPMARKET.model.Commodity;
import nl.ecbp.ECBPMARKET.model.store.CommodityStore;

public class AdministrationController {
	private CommodityStore store;
	private ECBPMarket plugin;

	public void modifyCommodity(String itemName, int id, double value,
			double maxValue, double minValue, double changeRate, int data)
			throws CommodityNotFoundException {
		
		Commodity c = store.getComodity(itemName);
		c.setChangeRate(changeRate);
		c.setData(data);
		c.setId(id);
		c.setMaxValue(maxValue);
		c.setMinValue(minValue);
		c.setValue(value);
		plugin.getDb().Save(c);
	}

	public void addCommodity(String itemName, int id, double value,
			double maxValue, double minValue, double changeRate, int data)
			throws DuplicateCommodityException  {
		if (plugin.getDb().exists(itemName)) {
			throw new DuplicateCommodityException();
		} else {
			Commodity c = new Commodity(id, itemName, value, maxValue,
					minValue, changeRate, data);
			plugin.getDb().Save(c);
		}
	}

	public void removeCommodity(String itemName) throws CommodityNotFoundException {	
		store.removeComodity(itemName);
		plugin.getDb().remove(itemName);
	}
}
