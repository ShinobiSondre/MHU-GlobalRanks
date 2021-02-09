package com.company.events;

import com.company.util.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.io.IOException;

public class EventPlayerDeath  implements Listener {

    private Utilities utilities;

    public EventPlayerDeath(Utilities utilities) {
        this.utilities = utilities;
    }

    @EventHandler
    public void PlayerDeath (PlayerDeathEvent p){

        if (p.getEntity().isOnline()) {

            //REMOVE HOLOGRAM HERE

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

            }

        }
    }

}
