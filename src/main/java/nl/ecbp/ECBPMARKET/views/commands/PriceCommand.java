package nl.ecbp.ECBPMARKET.views.commands;

import nl.ecbp.ECBPMARKET.ECBPMarket;
import nl.ecbp.ECBPMARKET.controllers.TradeController;
import nl.ecbp.ECBPMARKET.exceptions.CommodityNotFoundException;
import nl.ecbp.ECBPMARKET.exceptions.InvalidAmountException;
import nl.ecbp.ECBPMARKET.exceptions.InvalidArgumentsException;
import nl.ecbp.ECBPMARKET.exceptions.NotEnoughItemsException;
import nl.ecbp.ECBPMARKET.model.Recipient;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PriceCommand implements CommandExecutor {
	private ECBPMarket plugin;
	private TradeController con;
	
	public PriceCommand(ECBPMarket ecbpMarket, TradeController con) {
		this.plugin =ecbpMarket;
		this.con =con;
	}

	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		try {
			executePriceCommand(sender, command, label, args);
		} catch (InvalidArgumentsException e) {
			sender.sendMessage(ChatColor.RED+"[ERROR]Wrong arguments");
			sender.sendMessage("SYNTAX:/price [item]");
		} catch (CommodityNotFoundException e) {
			sender.sendMessage(ChatColor.RED+"[ERROR]That commodity is not avalible");
		}
		return false;
	}
	private double executePriceCommand(CommandSender sender, Command command,
			String label, String[] args) throws InvalidArgumentsException, CommodityNotFoundException{
		if (sender instanceof Player) {
			if (args.length == 2) {
				try{
					return con.price(args[0].toString());
				}catch (NumberFormatException e) {
					e.printStackTrace();
				} 
			}
			else{
				throw new InvalidArgumentsException();
			}
		}
		return 0.0;
	}
	
}
