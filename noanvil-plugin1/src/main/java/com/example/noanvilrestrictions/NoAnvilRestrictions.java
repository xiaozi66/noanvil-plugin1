package com.example.noanvilrestrictions;

import org.bukkit.plugin.java.JavaPlugin;

public class NoAnvilRestrictions extends JavaPlugin {
    
    @Override
    public void onEnable() {
        // 注册铁砧事件监听器
        getServer().getPluginManager().registerEvents(new AnvilListener(), this);
        
        getLogger().info("§aNoAnvilRestrictions 插件已启用 - 铁砧限制已移除！");
    }
    
    @Override
    public void onDisable() {
        getLogger().info("§cNoAnvilRestrictions 插件已禁用");
    }
}