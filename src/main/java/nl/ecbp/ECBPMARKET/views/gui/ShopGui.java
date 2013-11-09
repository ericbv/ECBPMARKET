package nl.ecbp.ECBPMARKET.views.gui;

import java.util.ArrayList;

import nl.ecbp.ECBPMARKET.helpers.StaticRounder;
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
		ItemStack Item1 = new ItemStack(c.getId(), 1, (short) c.getData());
		ItemMeta Meta = Item1.getItemMeta();
		Item1.setItemMeta(generateMeta(c, Item1));
		Inventory addto = shopInv;
		addto.addItem(Item1);
	}
	
	public static ItemMeta generateMeta(Commodity c,ItemStack Item){
		ItemMeta Meta = Item.getItemMeta();
		ArrayList<String> desc = new ArrayList<String>();
		desc.add("Buy Price:" + StaticRounder.round( c.getValue()));
		desc.add("Sell Price:" +  StaticRounder.round(c.getValue()*0.8));
		desc.add("");
		desc.add(ChatColor.GRAY + "SHIFT Click for a stack");
		desc.add(ChatColor.GRAY + "<Left Click to Buy>");
		desc.add(ChatColor.GRAY + "<Right Click to Sell>");
		Meta.setLore(desc);
		return Meta;
	}
}
