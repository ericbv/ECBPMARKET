package nl.ecbp.ECBPMARKET.views.commands;

import nl.ecbp.ECBPMARKET.ECBPMarket;
import nl.ecbp.ECBPMARKET.controllers.TradeController;
import nl.ecbp.ECBPMARKET.exceptions.InvalidAmountException;
import nl.ecbp.ECBPMARKET.exceptions.InvalidArgumentsException;
import nl.ecbp.ECBPMARKET.exceptions.NotEnoughInventoryRoomException;
import nl.ecbp.ECBPMARKET.exceptions.NotEnoughMoneyException;

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
			return executeBuyCommand(sender, command, label, args);
		} catch (InvalidArgumentsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAmountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotEnoughMoneyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotEnoughInventoryRoomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	private boolean executeBuyCommand(CommandSender sender, Command command,
			String label, String[] args) throws InvalidArgumentsException,
			InvalidAmountException, NotEnoughMoneyException,
			NotEnoughInventoryRoomException {
		if (sender instanceof Player) {
			Player player = (Player) sender;

			if (args.length == 2) {
				try {
					con.buy(player, args[0].toString(),
							Integer.parseInt(args[1]));
				} catch (NumberFormatException e) {
					throw new InvalidArgumentsException();
				}
			} else {
				throw new InvalidArgumentsException();
			}
		}

		return true;
	}
}
