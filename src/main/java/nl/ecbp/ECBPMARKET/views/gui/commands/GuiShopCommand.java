package nl.ecbp.ECBPMARKET.views.gui.commands;

import nl.ecbp.ECBPMARKET.views.gui.ShopGui;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GuiShopCommand implements CommandExecutor {
	private ShopGui guiManager;
	

	public GuiShopCommand(ShopGui guiManager) {
		this.guiManager = guiManager;
	}


	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			p.openInventory(guiManager.getInventory());

		}
		return true;
	}
}
