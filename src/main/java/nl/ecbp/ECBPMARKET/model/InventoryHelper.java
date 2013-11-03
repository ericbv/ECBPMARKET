package nl.ecbp.ECBPMARKET.model;

import nl.ecbp.ECBPMARKET.exceptions.NotEnoughItemsException;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class InventoryHelper {
private Player p;
	public InventoryHelper(Player p){
		this.p = p;
	}
	
	@SuppressWarnings("deprecation")
	public void TakeFromInventory(Commodity item,int amount) throws NotEnoughItemsException{
		int id = item.getNumber();
		Byte byteData = Byte.valueOf(String.valueOf(item.getData()));
		Material m = Material.getMaterial(item.getName());
		ItemStack its = new ItemStack(m, amount);
		
		if(p.getInventory().containsAtLeast(its, amount)){
			throw new NotEnoughItemsException();
		}
		
		int totalItems = CountItem(its);
		int remainingItems = totalItems - amount;
		p.getInventory().remove(m);
		ItemStack left = new ItemStack(m, remainingItems);
		p.getInventory().addItem(left);
	}
	
	private int CountItem(ItemStack its){

        PlayerInventory inventory = p.getInventory();
        ItemStack[] items = inventory.getContents();
        int has = 0;
        for (ItemStack item : items)
        {
            if (item.isSimilar(its))
            {
                has += item.getAmount();
            }
        }
        return has;
		
	}
}
