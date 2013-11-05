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

public class SellCommand implements CommandExecutor {

	private ECBPMarket plugin;
	private TradeController con;

	public SellCommand(ECBPMarket plugin, TradeController con) {
		this.plugin = plugin;
		this.con = con;

	}

	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		try {
			Recipient R = executeSellCommand(sender, command, label, args);
			sender.sendMessage(ChatColor.WHITE+"[OLD BALANCE]"+R.getOldBalance()+"Thats not a Valid amount");
			sender.sendMessage(ChatColor.WHITE+"[NEW BALANCE]"+R.getNewBalance()+"Thats not a Valid amount");
			sender.sendMessage(ChatColor.WHITE+"[NEW PRICE]"+R.getNewPrice()+"Thats not a Valid amount");
		} catch (InvalidAmountException e) {
			sender.sendMessage(ChatColor.RED+"[ERROR]Thats not a Valid amount");
		} catch (NotEnoughItemsException e) {
			sender.sendMessage(ChatColor.RED+"[ERROR]you do not have that many items of that type");
		} catch (CommodityNotFoundException e) {
			sender.sendMessage(ChatColor.RED+"[ERROR]That commodity is not avalible");
		} catch (InvalidArgumentsException e) {
			sender.sendMessage(ChatColor.RED+"[ERROR]Wrong arguments");
			sender.sendMessage("SYNTAX:/sell [item] [ammount]");
		}
		return true;
	}
	private Recipient executeSellCommand(CommandSender sender, Command command,
			String label, String[] args) throws InvalidAmountException, NotEnoughItemsException, CommodityNotFoundException, InvalidArgumentsException{
		if (sender instanceof Player) {
	           Player player = (Player) sender;
	    
			if (args.length == 2) {
				try{
					return con.sell(player, args[0].toString(), Integer.parseInt(args[1]));
				}catch (NumberFormatException e) {
					e.printStackTrace();
				} 
			}
			else{
				throw new InvalidArgumentsException();
			}
		}
		
		return null;
	}
}
