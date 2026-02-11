package net.mervyn.icaruswings_transmog_compat;

import net.fabricmc.api.ModInitializer;

/**
 * Icarus-Transmog Compat: Makes Icarus wings respect Transmog appearances.
 * 
 * <p>
 * This mod uses a Mixin to intercept Icarus's wing rendering and apply
 * the Transmog appearance if the equipped wings have been transmogged.
 * </p>
 */
public class IcarusWingsTransmogCompat implements ModInitializer {

	public static final String MOD_ID = "icaruswings_transmog_compat";

	@Override
	public void onInitialize() {
		// All functionality is handled by IcarusClientMixin
	}
}
