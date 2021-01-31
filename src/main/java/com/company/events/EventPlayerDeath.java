package com.company.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.io.IOException;

public class EventPlayerDeath implements Listener {
    @EventHandler
    public void PlayerDeath (PlayerDeathEvent p){

        if (p.getEntity().isOnline()) {

            hologramPlayers.remove(p.getEntity().getName() + "[Global-Rank]").delete();

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

            }

        }
    }
}
