package nl.ecbp.ECBPMARKET;



import java.util.logging.Logger;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import nl.ecbp.ECBPMARKET.controllers.TradeController;
import nl.ecbp.ECBPMARKET.model.store.CommodityStore;
import nl.ecbp.ECBPMARKET.views.commands.BuyCommand;
import nl.ecbp.ECBPMARKET.views.commands.SellCommand;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class ECBPMarket extends JavaPlugin {
	PluginDescriptionFile pluginFile;
    private  Permission permission;
	private  Economy economy;
    public final Logger logger = Logger.getLogger("Minecraft");
    
	@Override
    public void onEnable() {
        pluginFile = getDescription();
        getDataFolder().mkdir();
        setupPermissions(); //Smickles thinks this is what we're supposed to do for permissions via vault
        setupEconomy(); //Smickles thinks this is what we're supposed to do for economy via vault

        logger.info(pluginFile.getName() + " version " + pluginFile.getVersion() + " enabled");
        TradeController con = new TradeController(new CommodityStore());
        getCommand("sell").setExecutor(new SellCommand(this,con));
        getCommand("buy").setExecutor(new BuyCommand(this,con));
    }
   
	/**
     * Copy-pasted from http://dev.bukkit.org/server-mods/vault/
     * @return
     */
    private Boolean setupEconomy()
    {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }
    
    /**
     * Copy-pasted from http://dev.bukkit.org/server-mods/vault/
     * @return
     */
    private Boolean setupPermissions()
    {
        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
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

}
