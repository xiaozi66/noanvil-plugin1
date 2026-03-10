# NoAnvilRestrictions-NoMagic - Minecraft Folia 插件

## 功能说明
这个插件完全移除了铁砧的所有附魔限制（无咒语功能），让你可以：
- 将任意附魔组合到任何物品上
- 无视附魔冲突（比如锋利和亡灵杀手可以共存）
- 无视附魔等级限制
- 修复成本固定为1级经验
- 支持附魔书和物品的任意合并

## 兼容性
- **Minecraft 版本**: 1.20+ (支持 Paper/Spigot/Folia)
- **Folia 支持**: ✅ 完全支持
- **其他服务端**: Paper, Spigot, Purpur 等

## 安装方法
1. 将 `NoAnvilRestrictions-NoMagic.jar` 放入服务器的 `plugins` 文件夹
2. 重启服务器或使用 `/reload confirm` (Folia) 或 `/reload` (其他)
3. 插件会自动启用

## 使用方法
插件启用后，铁砧会自动：
- 移除所有附魔冲突检查
- 允许任意附魔组合
- 将修复成本设为1级经验

### 命令
- `/noanvilreload` - 重载插件配置 (需要权限 `noanvilrestrictions.reload`)

### 权限
- `noanvilrestrictions.reload` - 允许重载插件配置 (默认给OP)

## 构建方法
如果你需要自己构建插件：

```bash
# 安装 Maven
sudo apt install maven  # Ubuntu/Debian

# 构建插件
mvn clean package

# 构建好的插件在 target/NoAnvilRestrictions1.0.1.jar
```

## 配置说明
编辑 `plugins/NoAnvilRestrictions/config.yml`：

```yaml
enabled: true  # 是否启用插件
debug: false   # 调试模式

anvil:
  repair-cost: 1           # 修复成本
  max-repair-cost: 1       # 最大修复成本
  remove-enchant-conflicts: true    # 移除附魔冲突
  remove-level-limits: true         # 移除等级限制
  allow-incompatible-enchants: true # 允许不兼容附魔
```

## 注意事项
1. **Folia 服务器**：确保使用 `/reload confirm` 而不是 `/reload`
2. **附魔平衡**：这个插件会完全破坏游戏平衡，建议在创造模式或特殊服务器使用
3. **性能影响**：由于移除了所有检查，性能影响很小
4. **兼容性**：与大多数其他插件兼容，但可能与修改铁砧机制的插件冲突

## 示例用法
1. 将"锋利V"和"亡灵杀手V"同时附魔到一把剑上
2. 将"效率V"和"时运III"同时附魔到镐子上
3. 将"保护IV"和"爆炸保护IV"同时附魔到盔甲上
4. 任意组合，没有任何限制！

## 故障排除
如果插件不工作：
1. 检查服务器控制台是否有错误
2. 确保使用的是 Paper/Folia 1.20+
3. 检查是否有其他插件修改铁砧机制
4. 尝试禁用其他插件测试

## 更新日志

### v1.0.0
- 初始版本
- 完全移除铁砧附魔限制
- 支持 Folia 服务器
- 可配置修复成本

## 作者
- **小子** - 插件开发
- 如有问题，欢迎反馈！

## 许可证
MIT License - 可以自由修改和分发
