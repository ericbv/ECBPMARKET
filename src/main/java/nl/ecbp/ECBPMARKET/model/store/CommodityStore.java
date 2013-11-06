package nl.ecbp.ECBPMARKET.model.store;

import java.util.HashMap;
import java.util.List;

import nl.ecbp.ECBPMARKET.ECBPMarket;
import nl.ecbp.ECBPMARKET.db.PersistanceController;
import nl.ecbp.ECBPMARKET.exceptions.CommodityNotFoundException;
import nl.ecbp.ECBPMARKET.model.Commodity;

public class CommodityStore {
	private PersistanceController db;
	private HashMap<String, Commodity>  map;
	
	
	public CommodityStore(PersistanceController db) {
		this.db = db;
		map = new HashMap<String, Commodity>();
		List<Commodity> list= db.loadAll();
		for(Commodity c:list){
			map.put(c.getName(),c);
		}
	}


	public Commodity getComodity(String name) throws CommodityNotFoundException{
		Commodity c;
		c =map.get(name);
		if(c == null){
			throw new CommodityNotFoundException();
		}
		return c;
	}
	
	public void removeComodity(String name) throws CommodityNotFoundException{
		Commodity c;
		c = map.remove(name);
		if(c == null){
			throw new CommodityNotFoundException();
		}
	}
}
