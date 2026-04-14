package net.mervyn.icaruswings_transmog_compat.mixin;

import com.hidoni.transmog.TransmogUtils;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * Mixin to handle Transmog appearances for Estrogen's Moth Elytras.
 * 
 * We use manual Intermediary mapping target strings here with remap = false.
 * This is because the target mod (Estrogen) is built with Mojang mappings, 
 * causing the build tool (Loom) to incorrectly remap the method name "render"
 * to a Minecraft-specific name that doesn't exist in the Estrogen JAR.
 */
@Pseudo
@Mixin(targets = "dev.mayaqq.estrogen.client.content.entityRenderers.mothElytra.MothElytraLayer", remap = false)
public class EstrogenClientMixin {

    /**
     * Redirects the call to getEquippedStack (method_6118) in MothElytraLayer.render.
     * 
     * Target: method_6118 is getEquippedStack/getItemBySlot in 1.20.1 Intermediary.
     */
    @Redirect(
        method = "render(Lnet/minecraft/class_4587;Lnet/minecraft/class_4597;ILnet/minecraft/class_1309;FFFFFF)V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/class_1309;method_6118(Lnet/minecraft/class_1304;)Lnet/minecraft/class_1799;"
        ),
        remap = false
    )
    private ItemStack icarustransmogcompat$getAppearance(LivingEntity entity, EquipmentSlot slot) {
        // First check standard chest slot (in case they have a chestplate transmogged to Moth Elytra)
        ItemStack chest = entity.getEquippedStack(slot);
        ItemStack chestAppearance = TransmogUtils.getAppearanceStackOrOriginal(chest);
        if (chestAppearance != null && chestAppearance.getItem() instanceof dev.mayaqq.estrogen.content.items.MothElytraItem) {
            return chestAppearance;
        }

        // If not in the chest slot, they might be wearing Icarus wings in a Trinket slot
        // that are transmogged into Moth Elytras!
        ItemStack wingStack = dev.cammiescorner.icarus.util.IcarusHelper.getEquippedWings(entity);
        if (wingStack != null && !wingStack.isEmpty()) {
            ItemStack wingAppearance = TransmogUtils.getAppearanceStackOrOriginal(wingStack);
            if (wingAppearance != null && wingAppearance.getItem() instanceof dev.mayaqq.estrogen.content.items.MothElytraItem) {
                return wingAppearance;
            }
        }

        // Fallback to the original chest logic
        return chest;
    }
}
