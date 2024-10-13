package astralcraft.mcmagic.space.astralcraft.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Sound;

public class spawner implements Listener {

    private final Material requiredTool;
    private final Sound successSound;

    public spawner(Material requiredTool, Sound successSound) {
        this.requiredTool = requiredTool;
        this.successSound = successSound;
    }


@EventHandler
public void onPlayerInteract(PlayerInteractEvent event) {
    if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;

    Block block = event.getClickedBlock();
    Player player = event.getPlayer();
    ItemStack itemInHand = player.getInventory().getItemInMainHand();

    if (block != null && block.getType() == Material.SPAWNER) {
        if (isRequiredTool(itemInHand)) {
            block.setType(Material.AIR);
            ItemStack spawner = new ItemStack(Material.SPAWNER, 1);
            player.getInventory().addItem(spawner);
            player.sendMessage("Вы добыли спавнер!");
            decreaseItemInHand(player);
            playSuccessSound(player);
        }
    }
}

    private boolean isRequiredTool(ItemStack item) {
        return requiredTool != null && item.getType() == requiredTool;
    }

    private void decreaseItemInHand(Player player) {
        ItemStack itemInHand = player.getInventory().getItemInMainHand();

        if (itemInHand.getAmount() > 1) {
            itemInHand.setAmount(itemInHand.getAmount() - 1);
        } else {
            player.getInventory().setItemInMainHand(null);
        }
    }
    private void playSuccessSound(Player player) {
        if (successSound != null) {
            player.playSound(player.getLocation(), successSound, 1.0F, 1.0F);
        }
    }
}



