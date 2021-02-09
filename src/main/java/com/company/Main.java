package com.company;

import com.company.commands.RankCMD;
import com.company.events.EventMythicMobDeath;
import com.company.events.EventPlayerDeath;
import com.company.events.RankEvents;
import com.company.util.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Main extends JavaPlugin {
    Logger logger;
    Utilities utilities;


    public void onEnable() {
        // Plugin startup logic
        logger = Bukkit.getLogger();
        logger.info( "\n" + "\n" + "\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" + "--------------------------------------" + "\n" + "MHU-GlobalRank" + "\n" + "--------------------------------------"

                + "\n" + "\n" + "\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n");
        utilities = new Utilities(this);
        logger.info("Registering commands!");
        commandLoader(utilities);
        logger.info("Registering RankEvents");
        eventLoader(utilities);
        utilities.RankUpdater(80,"UpdateRank");
    }

    public Main getInstance() { return this;}
    public Utilities getUtilitiesInstance() {return utilities;}

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