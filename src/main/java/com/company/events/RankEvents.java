package com.company.events;

import com.company.util.Util;
import mhu.groot.grootlevels.events.PlayerLevelUpEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.IOException;


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

        //REMOVE HOLOGRAM HERE

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
