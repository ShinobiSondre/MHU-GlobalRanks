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


public class RankEvents implements Listener {

    private Util util;

    public RankEvents(Util util) {
        this.util = util;
    }

    @EventHandler
    public void PlayerLoginEvent(PlayerLoginEvent ev) throws IOException {

        Player player = ev.getPlayer();

        if(ev.getPlayer().isOnline()){
            util.ts.writeTotalPoints(player.getUniqueId(),player);
            util.CalculatePosition(player);
            util.DelayedHologram(player,50,"Login"+player.getUniqueId().toString());}}


    @EventHandler
    public void PlayerLogOut(PlayerQuitEvent p) {

        Player pp = p.getPlayer();

        util.mobKills.remove(pp.getUniqueId());

        //REMOVE HOLOGRAM HERE

    }


    @EventHandler
    public void LevelUpEvent (PlayerLevelUpEvent ev) throws NullPointerException, IOException {

        for (Player player : Bukkit.getOnlinePlayers()) {

            try {
                util.ts.writeTotalPoints(player.getPlayer().getUniqueId(), player.getPlayer());
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                util.CalculatePosition(player.getPlayer());

            } catch (IOException e) {
                e.printStackTrace();
            }

            util.DelayedHologram(player, 80, player.getUniqueId() + "lvlUp");

        }


    }
}
