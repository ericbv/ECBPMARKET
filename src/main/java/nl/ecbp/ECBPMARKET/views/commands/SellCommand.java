package nl.ecbp.ECBPMARKET.views.commands;

import nl.ecbp.ECBPMARKET.ECBPMarket;
import nl.ecbp.ECBPMARKET.controllers.TradeController;
import nl.ecbp.ECBPMARKET.exceptions.CommodityNotFoundException;
import nl.ecbp.ECBPMARKET.exceptions.InvalidAmountException;
import nl.ecbp.ECBPMARKET.exceptions.InvalidArgumentsException;
import nl.ecbp.ECBPMARKET.exceptions.NotEnoughItemsException;

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
			return executeSellCommand(sender, command, label, args);
		} catch (InvalidAmountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotEnoughItemsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CommodityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidArgumentsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	private boolean executeSellCommand(CommandSender sender, Command command,
			String label, String[] args) throws InvalidAmountException, NotEnoughItemsException, CommodityNotFoundException, InvalidArgumentsException{
		if (sender instanceof Player) {
	           Player player = (Player) sender;
	    
			if (args.length == 2) {
				try{
					con.sell(player, args[0].toString(), Integer.parseInt(args[1]));
				}catch (NumberFormatException e) {
					e.printStackTrace();
				} 
			}
			else{
				throw new InvalidArgumentsException();
			}
		}
		
		return true;
	}
}
