package com.example.noanvilrestrictions;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.Repairable;

import java.util.Map;

public class AnvilListener implements Listener {
    
    @EventHandler
    public void onPrepareAnvil(PrepareAnvilEvent event) {
        AnvilInventory inventory = event.getInventory();
        
        // 获取输入物品
        ItemStack firstItem = inventory.getItem(0);
        ItemStack secondItem = inventory.getItem(1);

        // 如果没有第一个物品，直接返回
        if (firstItem == null) {
            return;
        }

        // 创建结果物品的副本
        ItemStack result = firstItem.clone();
        ItemMeta resultMeta = result.getItemMeta();
        
        if (resultMeta == null) {
            return;
        }

        // 如果是附魔书（合并到物品）
        if (secondItem != null && secondItem.getType().toString().contains("ENCHANTED_BOOK")) {
            ItemMeta secondMeta = secondItem.getItemMeta();
            if (secondMeta instanceof EnchantmentStorageMeta) {
                EnchantmentStorageMeta bookMeta = (EnchantmentStorageMeta) secondMeta;
                Map<Enchantment, Integer> bookEnchants = bookMeta.getStoredEnchants();
                
                // 将附魔书的所有附魔添加到结果物品
                for (Map.Entry<Enchantment, Integer> entry : bookEnchants.entrySet()) {
                    Enchantment enchant = entry.getKey();
                    int level = entry.getValue();
                    
                    // 移除所有冲突检查，直接添加附魔
                    resultMeta.addEnchant(enchant, level, true);
                }
            }
        }
        
        // 如果是普通物品（合并附魔）
        if (secondItem != null && !secondItem.getType().toString().contains("ENCHANTED_BOOK")) {
            ItemMeta secondMeta = secondItem.getItemMeta();
            if (secondMeta != null) {
                Map<Enchantment, Integer> secondEnchants = secondMeta.getEnchants();
                
                // 合并所有附魔，不检查冲突
                for (Map.Entry<Enchantment, Integer> entry : secondEnchants.entrySet()) {
                    Enchantment enchant = entry.getKey();
                    int level = entry.getValue();
                    
                    // 如果已有相同附魔，取最高等级
                    if (resultMeta.hasEnchant(enchant)) {
                        int currentLevel = resultMeta.getEnchantLevel(enchant);
                        if (level > currentLevel) {
                            resultMeta.removeEnchant(enchant);
                            resultMeta.addEnchant(enchant, level, true);
                        }
                    } else {
                        resultMeta.addEnchant(enchant, level, true);
                    }
                }
            }
        }
        
        // 设置结果物品的meta
        result.setItemMeta(resultMeta);
        
        // 移除经验消耗限制
        if (resultMeta instanceof Repairable) {
            Repairable repairable = (Repairable) resultMeta;
            repairable.setRepairCost(1); // 设置为最低消耗
        }
        
        // 设置结果物品
        event.setResult(result);
        
        // 设置修复成本为1级（最低）
        inventory.setRepairCost(1);
        inventory.setMaximumRepairCost(1);
    }
}