package com.company.events;

import com.company.util.Util;
import com.nisovin.magicspells.spells.passive.TeleportListener;
import org.bukkit.Bukkit;
import org.bukkit.command.defaults.TeleportCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class EventPlayerDeath extends Util implements Listener {
    @EventHandler
    public void PlayerDeath (PlayerDeathEvent p){

        if (p.getEntity().isOnline()) {

            //REMOVE HOLOGRAM HERE

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
