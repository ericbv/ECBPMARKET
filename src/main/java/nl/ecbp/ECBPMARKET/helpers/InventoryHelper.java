package nl.ecbp.ECBPMARKET.helpers;

import java.util.HashMap;

import nl.ecbp.ECBPMARKET.exceptions.InvalidAmountException;
import nl.ecbp.ECBPMARKET.exceptions.NotEnoughItemsException;
import nl.ecbp.ECBPMARKET.model.Commodity;

import org.bukkit.ChatColor;
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
	public boolean takeComodityFromPlayer(Commodity commodity,
			int amount) throws  NotEnoughItemsException, InvalidAmountException {
		int id = commodity.getId();
		Byte byteData = Byte.valueOf(String.valueOf(commodity.getData()));

		ItemStack its = new ItemStack(id, amount, (short) 0, byteData);
		if(amount <= 0){
			throw new InvalidAmountException();
		}
		if (p.getInventory().contains(id)) {

			// Figure out how much is left over
			int left = getAmountInInventory(its) - amount;

			if (left < 0) {// this indicates the correct id, but the wrong
				throw new NotEnoughItemsException();
			}

			// Take out all of the item
			int x = 0;

			for (@SuppressWarnings("unused")
			ItemStack stack : p.getInventory().getContents()) {

				ItemStack slot = p.getInventory().getItem(x);

				if (slot != null) {
					Byte slotData = Byte.valueOf("0");

					try {
						slotData = slot.getData().getData();
					} catch (NullPointerException e) {
					}

					if ((slot.getTypeId() == id)
							&& (slotData.compareTo(byteData) == 0)) {
						p.getInventory().clear(x);
					}
				}
				x++;
			}

			// put back what was left over
			if (left > 0) {
				ItemStack itsLeft = its;
				itsLeft.setAmount(left);
				p.getInventory().addItem(itsLeft);
			}
			return true;

		} else {// give nice output even if they gave a bad number.

			throw new NotEnoughItemsException();
		}

	}

	/**
	 * Figure out how much of a given item is in the p's inventory
	 * 
	 * @param p
	 *            The p entity in question.
	 * @param id
	 *            The Data Value of the item in question.
	 * @return The amount of the item in the p's inventory as an integer.
	 */
	private int getAmountInInventory(ItemStack it) {
		int inInventory = 0;
		int x = 0;
		ItemStack slot;
		// we do it this way incase a user has an expanded inventory via another
		// plugin
		for (@SuppressWarnings("unused")
		ItemStack stack : p.getInventory().getContents()) {

			slot = p.getInventory().getItem(x);

			if (slot != null) {
				Byte slotData = Byte.valueOf("0");
				Byte itData = Byte.valueOf("0");

				try {
					slotData = slot.getData().getData();
				} catch (NullPointerException e) {

				}
				try {
					itData = it.getData().getData();
				} catch (NullPointerException e) {

				}

				if ((slot.getTypeId() == it.getTypeId())
						&& (slotData.compareTo(itData) == 0)) {
					inInventory += slot.getAmount();
				}
			} /*
			 * else { return 0; }
			 */
			x++;
		}
		return inInventory;
	}
	
	public void giveCommodityToPlayer(Commodity commodity, int amount) throws InvalidAmountException{
		if(amount <= 0)
			throw new InvalidAmountException();
		// give 'em the items and drop any extra
		Byte byteData = Byte.valueOf(String.valueOf(commodity.getData()));
		int id = commodity.getId();

		HashMap<Integer, ItemStack> overflow = p.getInventory()
				.addItem(new ItemStack(id, amount, (short) 0, byteData));
		for (int a : overflow.keySet()) {
			p.getWorld().dropItem(p.getLocation(),
					overflow.get(a));
		}

	}
	/*@SuppressWarnings("deprecation")
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

        pInventory inventory = p.getInventory();
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
		
	}*/
}
