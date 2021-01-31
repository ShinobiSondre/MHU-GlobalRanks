package com.company.events;

import com.company.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;


import java.io.IOException;

import static mhu.groot.grootlevels.classes.Levels.GetPlayerLevel;

public class EventPlayerMove extends Util implements Listener {
    @EventHandler
    public void moveEvent (PlayerMoveEvent m) throws NullPointerException, IOException {
        //Move event:
        Player playerThatMoved = m.getPlayer();


        if (playerThatMoved.isOnline()) {


            Location where1 = new Location(playerThatMoved.getWorld(), Bukkit.getPlayer(playerThatMoved.getName()).getLocation().getX(), Bukkit.getPlayer(playerThatMoved.getName()).getLocation().getY() + 2.7, Bukkit.getPlayer(playerThatMoved.getName()).getLocation().getZ());
            Location where2 = new Location(playerThatMoved.getWorld(), Bukkit.getPlayer(playerThatMoved.getName()).getLocation().getX(), Bukkit.getPlayer(playerThatMoved.getName()).getLocation().getY() + 3.1, Bukkit.getPlayer(playerThatMoved.getName()).getLocation().getZ());


            if (hologramPlayers.get(playerThatMoved.getName()) != null)
                hologramPlayers.get(playerThatMoved.getName()).teleport(where2);

            if (hologramPlayers.get(playerThatMoved.getName() + "[Global-Rank]") != null) {
                hologramPlayers.get(playerThatMoved.getName() + "[Global-Rank]").teleport(where1);
            }

            //Checks if the rank is different
            if (!Rank(m.getPlayer().getUniqueId()).equals(highestgroup(m.getPlayer()))) {

                for (Player hs : Bukkit.getOnlinePlayers()) {

                    try {
                        ts.writeTotalPoints(hs.getPlayer().getUniqueId(), hs.getPlayer());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        CalculatePosition(hs.getPlayer());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    DelayedHologram(hs, 60, hs.getUniqueId() + "ChangeRank");


                }
            }

            int level = GetPlayerLevel(m.getPlayer());

            int mobkills = 0;

            try {
                if (!(mobKills.get(playerThatMoved.getUniqueId()) == null))
                    mobkills = mobKills.get(playerThatMoved.getUniqueId());
            } catch (Exception e) {
                mobkills = 1;
            }

            if (teleported == true && playerThatMoved.isOnline()) {


                try {
                    ts.writeTotalPoints(playerThatMoved.getPlayer().getUniqueId(), playerThatMoved.getPlayer());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    CalculatePosition(playerThatMoved.getPlayer());

                } catch (IOException e) {
                    e.printStackTrace();
                }

                teleported = false;


            }


        }

    }
}
