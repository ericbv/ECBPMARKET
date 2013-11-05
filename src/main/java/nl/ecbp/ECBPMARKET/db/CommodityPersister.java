package nl.ecbp.ECBPMARKET.db;

import nl.ecbp.ECBPMARKET.ECBPMarket;
import nl.ecbp.ECBPMARKET.model.Commodity;

public class CommodityPersister {

	ECBPMarket plugin;
	
	
	
	public CommodityPersister(ECBPMarket plugin) {
		super();
		this.plugin = plugin;
	}



	public void Persist(Commodity c){
		plugin.getDb().Save(c);
	}
}
