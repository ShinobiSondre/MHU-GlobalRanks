package com.company.events;

import com.company.util.Util;
import com.gmail.filoghost.holographicdisplays.api.Hologram;
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent;
import mhu.groot.grootlevels.events.PlayerLevelUpEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import static mhu.groot.grootlevels.classes.Levels.GetPlayerLevel;


public class RankEvents extends Util implements Listener {


    @EventHandler
    public void PlayerLoginEvent(PlayerLoginEvent ev) throws IOException {

        Player player = ev.getPlayer();

        if(ev.getPlayer().isOnline()){
            ts.writeTotalPoints(player.getUniqueId(),player);
            p.CalculatePosition(player);
            DelayedHologram(player,50,"Login"+player.getUniqueId().toString());}}


    @EventHandler
    public void PlayerLogOut(PlayerQuitEvent p) {

        Player pp = p.getPlayer();

        mobKills.remove(pp.getUniqueId());
        hologramPlayers.clear();
    }



                    try {
                        CalculatePosition(player.getPlayer());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            }
    }


    @EventHandler
    public void LevelUpEvent (PlayerLevelUpEvent ev) throws NullPointerException, IOException {

        for (Player player : Bukkit.getOnlinePlayers()) {

            try {
                ts.writeTotalPoints(player.getPlayer().getUniqueId(), player.getPlayer());
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                CalculatePosition(player.getPlayer());

            } catch (IOException e) {
                e.printStackTrace();
            }

            DelayedHologram(player, 80, player.getUniqueId() + "lvlUp");

        }


    }
}
