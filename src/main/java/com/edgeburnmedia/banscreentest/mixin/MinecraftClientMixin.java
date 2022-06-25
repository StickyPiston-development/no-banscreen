package com.edgeburnmedia.banscreentest.mixin;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {

	/**
	 * @author MrStickyPiston
	 * @reason Force disable the ban screen
	 */
	@Overwrite
	public boolean isMultiplayerBanned() {
		return false;
	}
}
