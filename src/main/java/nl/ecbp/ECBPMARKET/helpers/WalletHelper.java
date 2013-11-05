package nl.ecbp.ECBPMARKET.helpers;

import net.milkbowl.vault.economy.Economy;
import nl.ecbp.ECBPMARKET.ECBPMarket;
import nl.ecbp.ECBPMARKET.exceptions.NotEnoughMoneyException;

import org.bukkit.entity.Player;

public class WalletHelper {
	private Player player;
	private ECBPMarket plugin;

	public WalletHelper(Player p, ECBPMarket plugin) {
		player = p;
		this.plugin = plugin;
	}

	public void takePlayerMoney(double amount) throws NotEnoughMoneyException {
		if (plugin.getEconomy().has(player.getName(), amount)) {
			plugin.getEconomy().withdrawPlayer(player.getName(), amount);
		} else {
			throw new NotEnoughMoneyException();
		}
	}

	public void givePlayerMoney(double amount) {
		plugin.getEconomy().depositPlayer(player.getName(), amount);
	}

	public double getPlayerMoney() {
		return plugin.getEconomy().getBalance(player.getName());
	}
}
