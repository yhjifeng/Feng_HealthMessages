package me.jifeng;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;

import java.text.DecimalFormat;
import java.util.Objects;

public class Events implements Listener {
    Plugin plugin = me.jifeng.Feng_HealthMessages.getPlugin(Feng_HealthMessages.class);
    @EventHandler
    public void onHealth(EntityDamageByEntityEvent event){
        if(event.getDamager() instanceof Player && event.getEntity() instanceof LivingEntity){
            Player p = (Player) event.getDamager();
            String Type = plugin.getConfig().getString("Type");
            assert Type != null;
            if(Type.equalsIgnoreCase("Title")){
                p.sendTitle(m1(Objects.requireNonNull(plugin.getConfig().getString("title")), event),m1(Objects.requireNonNull(plugin.getConfig().getString("sub_title")), event),3,5,3);
            }
            if(Type.equalsIgnoreCase("action_bar")){
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(m1(Objects.requireNonNull(plugin.getConfig().getString("action_bar")), event)));
            }
            if(Type.equalsIgnoreCase("chat")){
                p.sendMessage(m1(plugin.getConfig().getString("prefix") + plugin.getConfig().getString("chat"), event));
            }
        }
    }
    private String m1(String title, EntityDamageByEntityEvent event) {
        LivingEntity e = (LivingEntity)event.getEntity();
        Double d = event.getFinalDamage();
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        return title.replace("&", "§")
                .replace("%mobs_name%", (e.getCustomName() == null) ? e.getName() : e.getCustomName())
                .replace("%damage%", decimalFormat.format(d))
                .replace("%mobs_health%", decimalFormat.format(e.getHealth()))
                .replace("%mobs_max_health%", decimalFormat.format(Objects.requireNonNull(e.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue()));
    }
}
