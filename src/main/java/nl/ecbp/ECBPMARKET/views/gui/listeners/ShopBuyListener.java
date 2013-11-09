package nl.ecbp.ECBPMARKET.views.gui.listeners;

import nl.ecbp.ECBPMARKET.ECBPMarket;
import nl.ecbp.ECBPMARKET.controllers.TradeController;
import nl.ecbp.ECBPMARKET.exceptions.CommodityNotFoundException;
import nl.ecbp.ECBPMARKET.exceptions.InvalidAmountException;
import nl.ecbp.ECBPMARKET.exceptions.InvalidArgumentsException;
import nl.ecbp.ECBPMARKET.exceptions.NotEnoughItemsException;
import nl.ecbp.ECBPMARKET.exceptions.NotEnoughMoneyException;
import nl.ecbp.ECBPMARKET.model.Recipient;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.Event.Result;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ShopBuyListener implements Listener {
	public static ECBPMarket plugin;
	public static TradeController con;

	public ShopBuyListener(ECBPMarket instance, TradeController con) {
		plugin = instance;
		this.con = con;
	}

	@EventHandler
	public void onShopBuy(InventoryClickEvent event) {
		final Player p = Bukkit.getPlayer(event.getWhoClicked().getName());
		String title = event.getInventory().getTitle();
		if (!title.equals("Shop")) {
			return;
		}

		if (event.getRawSlot() != event.getSlot()) {
			return;
		}

		if (event.isLeftClick()) {
			event.setResult(Result.DENY);

			ItemStack Clicked = event.getCurrentItem();
			short data = Clicked.getData().getData();
			int id = Clicked.getTypeId();

			try {
				Recipient r;
				if (!event.isShiftClick()) {
					r = con.buy(p, id, data, 1);
				} else {
					r = con.buy(p, id, data, 64);
				}
				p.sendMessage(ChatColor.WHITE + "[Total cost]"
						+ ChatColor.GREEN + r.getTotal());
				p.sendMessage(ChatColor.WHITE + "[OLD BALANCE]"
						+ ChatColor.GRAY + r.getOldBalance());
				p.sendMessage(ChatColor.WHITE + "[NEW BALANCE]"
						+ ChatColor.GREEN + r.getNewBalance());
				p.sendMessage(ChatColor.WHITE + "[NEW PRICE]" + ChatColor.BLUE
						+ r.getNewPrice());
			} catch (InvalidAmountException e) {
				p.sendMessage(ChatColor.RED
						+ "[ERROR]Thats not a Valid amount, it should be greater than 0");
			} catch (NotEnoughMoneyException e) {
				p.sendMessage(ChatColor.RED
						+ "[ERROR]You do not have enough money for that,");
			} catch (CommodityNotFoundException e) {
				p.sendMessage(ChatColor.RED
						+ "[ERROR]That commodity is not avalible");
			}
		}
		event.setResult(Result.DENY);
	}
}