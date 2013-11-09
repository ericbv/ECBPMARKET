package nl.ecbp.ECBPMARKET.views.gui;

import java.util.ArrayList;

import nl.ecbp.ECBPMARKET.model.Commodity;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ShopGui implements InventoryHolder {
	private Inventory shopInv;
	public ShopGui(){
		setupShopInventory();
	}
	
	public void setupShopInventory() {
		shopInv = Bukkit.createInventory(this, 10 * 9, "Shop");
	}

	@Override
	public Inventory getInventory() {
		return shopInv;
	}

	public void addItem(Commodity c) {
		ArrayList<String> desc = new ArrayList<String>();
		ItemStack Item1 = new ItemStack(c.getId(), 1, (short) c.getData());
		ItemMeta Meta = Item1.getItemMeta();
		desc.add("Price:" + c.getValue());
		desc.add("");
		desc.add(ChatColor.GRAY + "SHIFT Click for a stack");
		desc.add(ChatColor.GRAY + "<Left Click to Buy>");
		desc.add(ChatColor.GRAY + "<Right Click to Sell>");
		Meta.setLore(desc);
		Item1.setItemMeta(Meta);
		Inventory addto = shopInv;
		addto.addItem(Item1);
	}
}
