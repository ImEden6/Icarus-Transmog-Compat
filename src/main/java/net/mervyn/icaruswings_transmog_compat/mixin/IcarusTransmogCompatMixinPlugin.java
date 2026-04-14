package net.mervyn.icaruswings_transmog_compat.mixin;

import net.fabricmc.loader.api.FabricLoader;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

/**
 * Mixin configuration plugin to correctly handle soft dependencies.
 * It detects the presence of other mods (like Estrogen) and decides whether to apply
 * corresponding compatibility mixins.
 */
public class IcarusTransmogCompatMixinPlugin implements IMixinConfigPlugin {

    @Override
    public void onLoad(String mixinPackage) {
    }

    @Override
    public String getRefMapperConfig() {
        return null; // Using default remapping
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        // Soft dependency for Estrogen
        if (mixinClassName.contains("EstrogenClientMixin")) {
            return FabricLoader.getInstance().isModLoaded("estrogen");
        }
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }
}
