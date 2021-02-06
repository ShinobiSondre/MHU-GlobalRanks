package com.company;

import com.company.commands.RankCMD;
import com.company.events.EventMythicMobDeath;
import com.company.events.EventPlayerDeath;
import com.company.events.RankEvents;
import com.company.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.logging.Logger;

public final class Main extends JavaPlugin {
    Logger logger;
    public Plugin plugin = this;
    Util utily;

    @Override
    public void onEnable() {
        // Plugin startup logic
        logger = Bukkit.getLogger();
        logger.info( "\n" + "\n" + "\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" + "--------------------------------------" + "\n" + "MHU-GlobalRank" + "\n" + "--------------------------------------"

                + "\n" + "\n" + "\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n");
        Util util = new Util(this);
        utily = util;
        logger.info("Registering commands!");
        commandLoader(util);
        logger.info("Registering RankEvents");
        eventLoader(util);

        //util.RankUpdater(20,"UpdateRank");
        util.RegisterMAPI();
    }



    public void eventLoader(Util util) {

        getServer().getPluginManager().registerEvents(new RankEvents(util), this);
        getServer().getPluginManager().registerEvents(new EventPlayerDeath(util), this);
        getServer().getPluginManager().registerEvents(new EventMythicMobDeath(util), this);

    }

    public void commandLoader(Util util) {

        this.getCommand("globalrank").setExecutor(new RankCMD(util,this));
        this.getCommand("hgtest").setExecutor(new RankCMD(util,this));

    }


    @Override
    public void onDisable() {


        for(Player player: Bukkit.getOnlinePlayers()) {
            try {
                utily.ts.writeTotalPoints(player.getUniqueId(),player);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        //REMOVE HOLOGRAMS HERE


    }
}