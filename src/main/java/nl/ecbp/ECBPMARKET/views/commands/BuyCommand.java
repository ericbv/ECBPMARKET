package nl.ecbp.ECBPMARKET.views.commands;

import nl.ecbp.ECBPMARKET.ECBPMarket;
import nl.ecbp.ECBPMARKET.controllers.TradeController;
import nl.ecbp.ECBPMARKET.exceptions.CommodityNotFoundException;
import nl.ecbp.ECBPMARKET.exceptions.InvalidAmountException;
import nl.ecbp.ECBPMARKET.exceptions.InvalidArgumentsException;
import nl.ecbp.ECBPMARKET.exceptions.NotEnoughInventoryRoomException;
import nl.ecbp.ECBPMARKET.exceptions.NotEnoughMoneyException;
import nl.ecbp.ECBPMARKET.model.Recipient;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BuyCommand implements CommandExecutor {
	private ECBPMarket plugin;
	private TradeController con;

	public BuyCommand(ECBPMarket plugin, TradeController con) {
		this.plugin = plugin;
		this.con = con;

	}

	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {

		try {
			Recipient R =  executeBuyCommand(sender, command, label, args);
			sender.sendMessage(ChatColor.WHITE+"[Total Cost]"+ChatColor.GREEN+R.getTotal());
			sender.sendMessage(ChatColor.WHITE+"[OLD BALANCE]"+ChatColor.GRAY+R.getOldBalance());
			sender.sendMessage(ChatColor.WHITE+"[NEW BALANCE]"+ChatColor.GREEN+R.getNewBalance());
			sender.sendMessage(ChatColor.WHITE+"[NEW PRICE]"+ChatColor.BLUE+R.getNewPrice());
		} catch (InvalidArgumentsException e) {
			sender.sendMessage(ChatColor.RED+"[ERROR]Wrong arguments");
			sender.sendMessage("SYNTAX:/buy [item] [ammount]");
		} catch (InvalidAmountException e) {
			sender.sendMessage(ChatColor.RED+"[ERROR]Thats not a Valid amount, it should be greater than 0");
		} catch (NotEnoughMoneyException e) {
			sender.sendMessage(ChatColor.RED+"[ERROR]You do not have enough money for that,");
		} catch (CommodityNotFoundException e) {
			sender.sendMessage(ChatColor.RED+"[ERROR]That commodity is not avalible");
		}
		return true;
	}

	private Recipient executeBuyCommand(CommandSender sender, Command command,
			String label, String[] args) throws InvalidArgumentsException,
			InvalidAmountException, NotEnoughMoneyException,
			 CommodityNotFoundException {
		if (sender instanceof Player) {
			Player player = (Player) sender;

			if (args.length == 2) {
				try {
					return con.buy(player, args[0].toString(),
							Integer.parseInt(args[1]));
				} catch (NumberFormatException e) {
					throw new InvalidArgumentsException();
				}
			} else {
				throw new InvalidArgumentsException();
			}
		}

		return null;
	}
}
