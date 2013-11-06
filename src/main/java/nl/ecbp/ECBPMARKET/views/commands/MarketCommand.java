package nl.ecbp.ECBPMARKET.views.commands;

import nl.ecbp.ECBPMARKET.ECBPMarket;
import nl.ecbp.ECBPMARKET.controllers.TradeController;
import nl.ecbp.ECBPMARKET.exceptions.CommodityNotFoundException;
import nl.ecbp.ECBPMARKET.exceptions.InvalidAmountException;
import nl.ecbp.ECBPMARKET.exceptions.InvalidArgumentsException;
import nl.ecbp.ECBPMARKET.exceptions.NotEnoughItemsException;
import nl.ecbp.ECBPMARKET.exceptions.NotEnoughMoneyException;
import nl.ecbp.ECBPMARKET.model.Recipient;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MarketCommand implements CommandExecutor {

	private ECBPMarket plugin;
	private TradeController con;

	public MarketCommand(ECBPMarket plugin, TradeController con) {
		this.plugin = plugin;
		this.con = con;
	}

	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (args.length >= 1) {
			switch (args[0])
				{
				case "price":
					try {
						sender.sendMessage(ChatColor.WHITE
								+"Price:" +ChatColor.GREEN+executePriceCommand(sender, command, label, args));
					} catch (InvalidArgumentsException e) {
						sender.sendMessage(ChatColor.RED
								+ "[ERROR]Wrong arguments");
						sender.sendMessage("SYNTAX:/market price [item]");
					} catch (CommodityNotFoundException e) {
						sender.sendMessage(ChatColor.RED
								+ "[ERROR]That commodity is not avalible");
					}
					break;
				case "sell":
					try {
						Recipient R = executeSellCommand(sender, command,
								label, args);
						sender.sendMessage(ChatColor.WHITE + "[Money Recieved]"
								+ ChatColor.GREEN + R.getTotal());
						sender.sendMessage(ChatColor.WHITE + "[OLD BALANCE]"
								+ ChatColor.GRAY + R.getOldBalance());
						sender.sendMessage(ChatColor.WHITE + "[NEW BALANCE]"
								+ ChatColor.GREEN + R.getNewBalance());
						sender.sendMessage(ChatColor.WHITE + "[NEW PRICE]"
								+ ChatColor.BLUE + R.getNewPrice());
					} catch (InvalidAmountException e) {
						sender.sendMessage(ChatColor.RED
								+ "[ERROR]Thats not a Valid amount, it should be greater than 0");
					} catch (NotEnoughItemsException e) {
						sender.sendMessage(ChatColor.RED
								+ "[ERROR]you do not have that many items of that type");
					} catch (CommodityNotFoundException e) {
						sender.sendMessage(ChatColor.RED
								+ "[ERROR]That commodity is not avalible");
					} catch (InvalidArgumentsException e) {
						sender.sendMessage(ChatColor.RED
								+ "[ERROR]Wrong arguments");
						sender.sendMessage("SYNTAX:/market sell [item] [ammount]");
					}
					break;

				case "buy":
					try {
						Recipient R = executeBuyCommand(sender, command, label,
								args);
						sender.sendMessage(ChatColor.WHITE + "[Total Cost]"
								+ ChatColor.GREEN + R.getTotal());
						sender.sendMessage(ChatColor.WHITE + "[OLD BALANCE]"
								+ ChatColor.GRAY + R.getOldBalance());
						sender.sendMessage(ChatColor.WHITE + "[NEW BALANCE]"
								+ ChatColor.GREEN + R.getNewBalance());
						sender.sendMessage(ChatColor.WHITE + "[NEW PRICE]"
								+ ChatColor.BLUE + R.getNewPrice());
					} catch (InvalidArgumentsException e) {
						sender.sendMessage(ChatColor.RED
								+ "[ERROR]Wrong arguments");
						sender.sendMessage("SYNTAX:/market buy [item] [ammount]");
					} catch (InvalidAmountException e) {
						sender.sendMessage(ChatColor.RED
								+ "[ERROR]Thats not a Valid amount, it should be greater than 0");
					} catch (NotEnoughMoneyException e) {
						sender.sendMessage(ChatColor.RED
								+ "[ERROR]You do not have enough money for that,");
					} catch (CommodityNotFoundException e) {
						sender.sendMessage(ChatColor.RED
								+ "[ERROR]That commodity is not avalible");
					}
					break;
				default:
					sender.sendMessage(ChatColor.RED
							+ "[ERROR]Wrong arguments");
					sender.sendMessage("SYNTAX:/market [buy|sell|price]");
					
				}

		}
		return false;
	}

	private double executePriceCommand(CommandSender sender, Command command,
			String label, String[] args) throws InvalidArgumentsException,
			CommodityNotFoundException {
		if (sender instanceof Player) {
			if (args.length >= 2) {
				try {
					return con.price(args[1].toString());
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			} else {
				throw new InvalidArgumentsException();
			}
		}
		return 0.0;
	}

	private Recipient executeSellCommand(CommandSender sender, Command command,
			String label, String[] args) throws InvalidAmountException,
			NotEnoughItemsException, CommodityNotFoundException,
			InvalidArgumentsException {
		if (sender instanceof Player) {
			Player player = (Player) sender;

			if (args.length == 3) {
				try {
					return con.sell(player, args[1].toString(),
							Integer.parseInt(args[2]));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			} else {
				throw new InvalidArgumentsException();
			}
		}

		return null;
	}

	private Recipient executeBuyCommand(CommandSender sender, Command command,
			String label, String[] args) throws InvalidArgumentsException,
			InvalidAmountException, NotEnoughMoneyException,
			CommodityNotFoundException {
		if (sender instanceof Player) {
			Player player = (Player) sender;

			if (args.length == 3) {
				try {
					return con.buy(player, args[1].toString(),
							Integer.parseInt(args[2]));
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
