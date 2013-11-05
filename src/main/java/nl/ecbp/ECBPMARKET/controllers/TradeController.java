package nl.ecbp.ECBPMARKET.controllers;

import org.bukkit.entity.Player;

import nl.ecbp.ECBPMARKET.ECBPMarket;
import nl.ecbp.ECBPMARKET.db.ComodityPersister;
import nl.ecbp.ECBPMARKET.exceptions.CommodityNotFoundException;
import nl.ecbp.ECBPMARKET.exceptions.InvalidAmountException;
import nl.ecbp.ECBPMARKET.exceptions.NotEnoughItemsException;
import nl.ecbp.ECBPMARKET.exceptions.NotEnoughMoneyException;
import nl.ecbp.ECBPMARKET.exceptions.NotEnoughInventoryRoomException;
import nl.ecbp.ECBPMARKET.helpers.InventoryHelper;
import nl.ecbp.ECBPMARKET.helpers.WalletHelper;
import nl.ecbp.ECBPMARKET.model.Commodity;
import nl.ecbp.ECBPMARKET.model.Order;
import nl.ecbp.ECBPMARKET.model.OrderConstructor;
import nl.ecbp.ECBPMARKET.model.Recipient;
import nl.ecbp.ECBPMARKET.model.store.CommodityStore;

public class TradeController {
	private CommodityStore store;
	private ComodityPersister persister;
	private ECBPMarket plugin;
	
	public TradeController(CommodityStore store,ComodityPersister persister,ECBPMarket plugin){
		this.store = store;
		this.persister =persister;
		this.plugin =plugin;
	}
	public Recipient sell(Player p,String item, int amount)throws    NotEnoughItemsException, CommodityNotFoundException {
		Commodity c = store.getComodity(item);
		WalletHelper wallet =new WalletHelper(p,plugin);
		double oldBalance = wallet.getPlayerMoney(); 
		Order o = new OrderConstructor().GenerateOrder(false,c,amount);
		new InventoryHelper(p).takeComodityFromPlayer(c, amount);
		wallet.givePlayerMoney(o.getTotal());
		c.setValue(o.getCurrentPrice());
		persister.Persist(c);
		return new Recipient(o.getTotal(),oldBalance,wallet.getPlayerMoney(),o.getCurrentPrice());
	}

	public Recipient buy(Player p,String item, int amount) throws InvalidAmountException, NotEnoughMoneyException, NotEnoughInventoryRoomException {
		//TODO implement
		return null;

	}
}
