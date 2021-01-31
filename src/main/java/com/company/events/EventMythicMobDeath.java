package com.company.events;

import com.company.util.Util;
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;


import java.io.IOException;

public class EventMythicMobDeath extends Util implements Listener {

        @EventHandler
        public void mobDeath(MythicMobDeathEvent dd) throws IOException {

            if (dd.getMob().getFaction().equals("Villain") || dd.getMob().getFaction().equals("Hero")) {
                String killer = dd.getKiller().getName();

                if (!mobKills.containsKey(dd.getKiller().getUniqueId())) {

                    mobKills.put(dd.getKiller().getUniqueId(), 1);

                } else if (mobKills.containsKey(dd.getKiller().getUniqueId())) {

                    kills = mobKills.get(dd.getKiller().getUniqueId());
                    kills++;

                    mobKills.put((dd.getKiller().getUniqueId()), kills);
                }

            }

        }

}
