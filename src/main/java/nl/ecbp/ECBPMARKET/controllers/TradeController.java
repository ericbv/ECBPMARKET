package nl.ecbp.ECBPMARKET.controllers;

import org.bukkit.entity.Player;

import nl.ecbp.ECBPMARKET.exceptions.CommodityNotFoundException;
import nl.ecbp.ECBPMARKET.exceptions.InvalidAmountException;
import nl.ecbp.ECBPMARKET.exceptions.NotEnoughItemsException;
import nl.ecbp.ECBPMARKET.exceptions.NotEnoughMoneyException;
import nl.ecbp.ECBPMARKET.exceptions.NotEnoughInventoryRoomException;
import nl.ecbp.ECBPMARKET.model.Commodity;
import nl.ecbp.ECBPMARKET.model.InventoryHelper;
import nl.ecbp.ECBPMARKET.model.Order;
import nl.ecbp.ECBPMARKET.model.OrderConstructor;
import nl.ecbp.ECBPMARKET.model.store.CommodityStore;

public class TradeController {
	private CommodityStore store;
	public TradeController(CommodityStore store){
		this.store = store;
	}
	public void sell(Player p,String item, int amount)throws InvalidAmountException, NotEnoughItemsException, CommodityNotFoundException {
		Commodity c = store.getComodity(item);
		
		Order o = new OrderConstructor().GenerateOrder(true,c,amount);
		
		new InventoryHelper(p).TakeFromInventory(c, amount);
	}

	public void buy(String item, int amount) throws InvalidAmountException, NotEnoughMoneyException, NotEnoughInventoryRoomException {

	}
}
