package com.company;

import com.company.commands.RankCMD;
import com.company.events.EventMythicMobDeath;
import com.company.events.EventPlayerDeath;
import com.company.events.RankEvents;
import com.company.util.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.logging.Logger;

public class Main extends JavaPlugin {
    Logger logger;
    Utilities u;

    public void onEnable() {
        // Plugin startup logic
        logger = Bukkit.getLogger();
        logger.info( "\n" + "\n" + "\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" + "--------------------------------------" + "\n" + "MHU-GlobalRank" + "\n" + "--------------------------------------"

                + "\n" + "\n" + "\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n");
        Utilities utilities = new Utilities(this);
        logger.info("Registering commands!");
        commandLoader(utilities);
        logger.info("Registering RankEvents");
        eventLoader(utilities);

        //util.RankUpdater(20,"UpdateRank");
        utilities.RegisterMAPI();
    }

    public Main getInstance() { return this;}
    public Utilities getUtilitiesInstance() {return u;}

    public void eventLoader(Utilities utilities) {

        getServer().getPluginManager().registerEvents(new RankEvents(utilities), this);
        getServer().getPluginManager().registerEvents(new EventPlayerDeath(utilities), this);
        getServer().getPluginManager().registerEvents(new EventMythicMobDeath(utilities), this);

    }

    public void commandLoader(Utilities utilities) {

        this.getCommand("globalrank").setExecutor(new RankCMD(utilities,this));
        this.getCommand("hgtest").setExecutor(new RankCMD(utilities,this));

    }


    @Override
    public void onDisable() {

//
//        for(Player player: Bukkit.getOnlinePlayers()) {
//            try {
//                utily.ts.writeTotalPoints(player.getUniqueId(),player);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }


        //REMOVE HOLOGRAMS HERE


    }
}