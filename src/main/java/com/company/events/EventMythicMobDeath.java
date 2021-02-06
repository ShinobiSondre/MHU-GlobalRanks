package com.company.events;

import com.company.util.Util;
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;


import java.io.IOException;

public class EventMythicMobDeath implements Listener {

    private Util util;
    public EventMythicMobDeath(Util util) {
        this.util = util;
    }

        @EventHandler
        public void mobDeath(MythicMobDeathEvent dd) throws IOException {

            if (dd.getMob().getFaction().equals("Villain") || dd.getMob().getFaction().equals("Hero")) {
                String killer = dd.getKiller().getName();

                if (!util.mobKills.containsKey(dd.getKiller().getUniqueId())) {

                    util. mobKills.put(dd.getKiller().getUniqueId(), 1);

                } else if (util.mobKills.containsKey(dd.getKiller().getUniqueId())) {

                    util.kills = util.mobKills.get(dd.getKiller().getUniqueId());
                    util.kills++;

                    util.mobKills.put((dd.getKiller().getUniqueId()), util.kills);
                }

            }

        }

}
