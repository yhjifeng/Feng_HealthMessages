package me.jifeng;

import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.util.Objects;


public final class Feng_HealthMessages extends JavaPlugin {
    public static Feng_HealthMessages main;

    @Override
    public void onEnable() {
        // Plugin startup logic
        main=this;
        getServer().getPluginManager().registerEvents(new Events(), this);
        Objects.requireNonNull(getServer().getPluginCommand("fengheal")).setExecutor(new Cmd());
        reload();
        msssage();
    }

    private void msssage() {
        System.out.println(" ");
        System.out.println(" 插 件 名 称: Feng_HealMessages");
        System.out.println(" 插 件 作 者: 疾风吖灬");
        System.out.println(" 插 件 版 本: V1.0.0");
        System.out.println(" 作 者 Q Q: §b1022767672");
    }

    @Override
    public void onDisable() {
        msssage();
    }
    public void reload(){
        File cfg = new File(getDataFolder() + "/config.yml");
        if(cfg.exists()){
            System.out.println("检测到配置文件，正在加载！");
        }
        else {
            System.out.println("§a未检测到配置文件，生成默认配置中！");
            saveDefaultConfig();
        }
    }
}
