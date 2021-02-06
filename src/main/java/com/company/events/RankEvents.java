package com.company.events;

import com.company.util.Utilities;
import mhu.groot.grootlevels.events.PlayerLevelUpEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.IOException;


public class RankEvents implements Listener {

    private Utilities utilities;

    public RankEvents(Utilities utilities) {
        this.utilities = utilities;
    }

    @EventHandler
    public void PlayerLoginEvent(PlayerLoginEvent ev) throws IOException {

        Player player = ev.getPlayer();

        if(ev.getPlayer().isOnline()){
            utilities.ts.writeTotalPoints(player.getUniqueId(),player);
            utilities.CalculatePosition(player);
            utilities.DelayedHologram(player,50,"Login"+player.getUniqueId().toString());}}


    @EventHandler
    public void PlayerLogOut(PlayerQuitEvent p) {

        Player pp = p.getPlayer();

        utilities.mobKills.remove(pp.getUniqueId());

        //REMOVE HOLOGRAM HERE

    }


    @EventHandler
    public void LevelUpEvent (PlayerLevelUpEvent ev) throws NullPointerException, IOException {

        for (Player player : Bukkit.getOnlinePlayers()) {

            try {
                utilities.ts.writeTotalPoints(player.getPlayer().getUniqueId(), player.getPlayer());
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                utilities.CalculatePosition(player.getPlayer());

            } catch (IOException e) {
                e.printStackTrace();
            }

            utilities.DelayedHologram(player, 80, player.getUniqueId() + "lvlUp");

        }


    }
}
