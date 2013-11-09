package nl.ecbp.ECBPMARKET;

import java.util.logging.Logger;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import nl.ecbp.ECBPMARKET.controllers.AdministrationController;
import nl.ecbp.ECBPMARKET.controllers.TradeController;
import nl.ecbp.ECBPMARKET.db.CommodityPersister;
import nl.ecbp.ECBPMARKET.db.PersistanceController;
import nl.ecbp.ECBPMARKET.model.Commodity;
import nl.ecbp.ECBPMARKET.model.store.CommodityStore;
import nl.ecbp.ECBPMARKET.views.commands.MarketCommand;
import nl.ecbp.ECBPMARKET.views.gui.ShopGui;
import nl.ecbp.ECBPMARKET.views.gui.commands.GuiShopCommand;
import nl.ecbp.ECBPMARKET.views.gui.listeners.ShopBuyListener;
import nl.ecbp.ECBPMARKET.views.gui.listeners.ShopSellListener;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class ECBPMarket extends JavaPlugin {
	PluginDescriptionFile pluginFile;
	private Permission permission;
	private Economy economy;
	public final Logger logger = Logger.getLogger("Minecraft");
	private PersistanceController db;

	@Override
	public void onEnable() {
		pluginFile = getDescription();
		if (!getDataFolder().exists()) {
			getDataFolder().mkdir();
		}
		setupPermissions();
		setupEconomy();
		setupDB();
		// TODO REMOVE TEST STUFF
		Commodity c = new Commodity(12, "sand", 100.0, 10000.0, 0.01, 0.01, 0);
		new CommodityPersister(this).Persist(c);
		logger.info(pluginFile.getName() + " version "
				+ pluginFile.getVersion() + " enabled");
		ShopGui gui = new ShopGui();
		CommodityStore store = new CommodityStore(db,gui);
		TradeController con = new TradeController(store,new CommodityPersister(this), this);
		AdministrationController aCon = new AdministrationController(store,this);
		
		
		ShopSellListener sellListener = new ShopSellListener(this,con);
		getServer().getPluginManager().registerEvents(sellListener, this);
		ShopBuyListener buyListener = new ShopBuyListener(this,con);
		getServer().getPluginManager().registerEvents(buyListener, this);
		getCommand("market").setExecutor(new MarketCommand(this, con, aCon));
		getCommand("guimarket").setExecutor(new GuiShopCommand(gui));
	}

	private void setupDB() {
		db = new PersistanceController(this);

	}

	/**
	 * Copy-pasted from http://dev.bukkit.org/server-mods/vault/
	 * 
	 * @return
	 */
	private Boolean setupEconomy() {
		RegisteredServiceProvider<Economy> economyProvider = getServer()
				.getServicesManager().getRegistration(
						net.milkbowl.vault.economy.Economy.class);
		if (economyProvider != null) {
			economy = economyProvider.getProvider();
		}

		return (economy != null);
	}

	/**
	 * Copy-pasted from http://dev.bukkit.org/server-mods/vault/
	 * 
	 * @return
	 */
	private Boolean setupPermissions() {
		RegisteredServiceProvider<Permission> permissionProvider = getServer()
				.getServicesManager().getRegistration(
						net.milkbowl.vault.permission.Permission.class);
		if (permissionProvider != null) {
			permission = permissionProvider.getProvider();
		}
		return (permission != null);
	}

	public Permission getPermission() {
		return permission;
	}

	public Economy getEconomy() {
		return economy;
	}

	public PersistanceController getDb() {
		return db;
	}

}
