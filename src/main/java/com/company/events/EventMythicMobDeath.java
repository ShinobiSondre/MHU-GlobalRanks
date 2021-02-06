package com.company.events;

import com.company.util.Utilities;
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;


import java.io.IOException;

public class EventMythicMobDeath implements Listener {

    private Utilities utilities;
    public EventMythicMobDeath(Utilities utilities) {
        this.utilities = utilities;
    }

        @EventHandler
        public void mobDeath(MythicMobDeathEvent dd) throws IOException {

            if (dd.getMob().getFaction().equals("Villain") || dd.getMob().getFaction().equals("Hero")) {
                String killer = dd.getKiller().getName();

                if (!utilities.mobKills.containsKey(dd.getKiller().getUniqueId())) {

                    utilities. mobKills.put(dd.getKiller().getUniqueId(), 1);

                } else if (utilities.mobKills.containsKey(dd.getKiller().getUniqueId())) {

                    utilities.kills = utilities.mobKills.get(dd.getKiller().getUniqueId());
                    utilities.kills++;

                    utilities.mobKills.put((dd.getKiller().getUniqueId()), utilities.kills);
                }

            }

        }

}
