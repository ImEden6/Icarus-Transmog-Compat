package net.mervyn.icaruswings_transmog_compat.mixin;

import com.hidoni.transmog.TransmogUtils;
import dev.cammiescorner.icarus.client.IcarusClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * This mixin intercepts Icarus's wing rendering to apply Transmog appearances.
 * When a player has transmog applied to their wings, this makes the Icarus
 * renderer use the transmogged appearance instead of the original wing item.
 */
@Mixin(IcarusClient.class)
public class IcarusClientMixin {

    /**
     * Intercepts the getWingsForRendering method and applies transmog appearance.
     * This makes Icarus render the transmogged wing appearance instead of the
     * actual equipped wing.
     */
    @Inject(method = "getWingsForRendering", at = @At("RETURN"), cancellable = true, remap = false)
    private static void applyTransmogAppearance(LivingEntity entity, CallbackInfoReturnable<ItemStack> cir) {
        ItemStack originalWings = cir.getReturnValue();
        if (originalWings != null && !originalWings.isEmpty()) {
            // Use Transmog's utility to get the appearance stack (or original if not
            // transmogged)
            ItemStack appearanceStack = TransmogUtils.getAppearanceStackOrOriginal(originalWings);
            if (appearanceStack != originalWings) {
                cir.setReturnValue(appearanceStack);
            }
        }
    }
}
