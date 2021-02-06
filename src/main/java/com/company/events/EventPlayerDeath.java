package com.company.events;

import com.company.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.io.IOException;

public class EventPlayerDeath  implements Listener {

    private Util util;

    public EventPlayerDeath(Util util) {
        this.util = util;
    }

    @EventHandler
    public void PlayerDeath (PlayerDeathEvent p){

        if (p.getEntity().isOnline()) {

            //REMOVE HOLOGRAM HERE

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

            }

        }
    }

}
