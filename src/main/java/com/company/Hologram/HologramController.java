package com.company.Hologram;

import com.company.Hologram.lines.DefaultLine;
import com.company.Hologram.lines.LineRank;
import net.blitzcube.mlapi.api.tag.ITagController;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class HologramController implements ITagController {

    private final JavaPlugin p;
    public ITagController.TagLine line;

    public HologramController(JavaPlugin plugin) {
        this.p = plugin;
        this.line = new DefaultLine();
    }

    public void setLine(String passin) {
        line = new LineRank(passin);
    }

    public List<TagLine> getFor(Entity entity) {
        return null;
    }

    public String getName(Entity target, Player viewer, String previous) {
        return null;
    }

    public EntityType[] getAutoApplyFor() {
        return new EntityType[0];
    }

    public JavaPlugin getPlugin() {
        return p;
    }

    public int getPriority() {
        return 0;
    }

    public int getNamePriority() {
        return 0;
    }
}

