package com.company;

import com.company.commands.RankCMD;
import com.company.events.EventMythicMobDeath;
import com.company.events.EventPlayerDeath;
import com.company.events.EventPlayerMove;
import com.company.events.RankEvents;
import com.company.util.Util;
import me.lucko.luckperms.api.LuckPermsApi;
import mhu.groot.grootlevels.classes.Levels;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Logger;

public final class Main extends JavaPlugin {
    Logger logger;
    Util util;

    @Override
    public void onEnable() {
        // Plugin startup logic

        logger = Bukkit.getLogger();
        logger.info( "\n" + "\n" + "\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" + "--------------------------------------" + "\n" + "MHU-GlobalRank" + "\n" + "--------------------------------------"

                + "\n" + "\n" + "\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n" +"\n");

        logger.info("Registering commands!");
        commandLoader(util);
        logger.info("Registering RankEvents");
        eventLoader(util);

    }



    public void eventLoader(Util util) {

        getServer().getPluginManager().registerEvents(new RankEvents(), this);
        getServer().getPluginManager().registerEvents(new EventPlayerMove(), this);
        getServer().getPluginManager().registerEvents(new EventPlayerDeath(), this);
        getServer().getPluginManager().registerEvents(new EventMythicMobDeath(), this);

    }

    public void commandLoader(Util util) {

        this.getCommand("globalrank").setExecutor(new RankCMD(util,this));

    }


    @Override
    public void onDisable() {


        for(Player player: Bukkit.getOnlinePlayers()) {
            try {
                util.ts.writeTotalPoints(player.getUniqueId(),player);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for(int i = 0; i<util.hologramPlayers.size(); i++){

            util.hologramPlayers.get(i).delete();

        }


    }
}