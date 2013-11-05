package nl.ecbp.ECBPMARKET.controllers;

import org.bukkit.entity.Player;

import nl.ecbp.ECBPMARKET.ECBPMarket;
import nl.ecbp.ECBPMARKET.db.CommodityPersister;
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
	private CommodityPersister persister;
	private ECBPMarket plugin;

	public TradeController(CommodityStore store, CommodityPersister persister,
			ECBPMarket plugin) {
		this.store = store;
		this.persister = persister;
		this.plugin = plugin;
	}

	public Recipient sell(Player p, String item, int amount)
			throws NotEnoughItemsException, CommodityNotFoundException,
			InvalidAmountException {
		try {
			return doTheTrade(false, p, item, amount);
		} catch (NotEnoughMoneyException e) {
			// will not occur ever period.
		}
		return null;
	}

	public Recipient buy(Player p, String item, int amount)
			throws NotEnoughMoneyException, CommodityNotFoundException, InvalidAmountException {
		try {
			return doTheTrade(true, p, item, amount);
		} catch (NotEnoughItemsException e) {
			// will not occur ever period.
		}
		return null;

	}

	public double price(String item) throws CommodityNotFoundException {
		return store.getComodity(item).getValue();
	}

	
	private Recipient doTheTrade(boolean buy, Player p, String item, int amount)
			throws CommodityNotFoundException, NotEnoughMoneyException,
			NotEnoughItemsException, InvalidAmountException {
		
		Commodity c = store.getComodity(item);
		WalletHelper wallet = new WalletHelper(p, plugin);
		double oldBalance = wallet.getPlayerMoney();
		Order o = new OrderConstructor().GenerateOrder(buy, c, amount);
		if (buy) 
			doBuyPart(p, o, wallet, amount, c);
		else 
			doSellPart(p, o, wallet, amount, c);
		c.setValue(o.getCurrentPrice());
		persister.Persist(c);
		return new Recipient(o.getTotal(), oldBalance, wallet.getPlayerMoney(),
				o.getCurrentPrice());
	}
	
	private void doBuyPart(Player p,Order o,WalletHelper wallet,int amount,Commodity c) throws NotEnoughMoneyException, InvalidAmountException{
		new InventoryHelper(p).giveCommodityToPlayer(c, amount);
		wallet.takePlayerMoney(o.getTotal());
	}
	
	private void doSellPart(Player p,Order o,WalletHelper wallet,int amount,Commodity c) throws NotEnoughItemsException, InvalidAmountException{
		new InventoryHelper(p).takeComodityFromPlayer(c, amount);
		wallet.givePlayerMoney(o.getTotal());
	}
}
