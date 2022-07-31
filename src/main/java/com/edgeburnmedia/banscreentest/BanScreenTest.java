package com.edgeburnmedia.banscreentest;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BanScreenTest implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("modid");

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");
	}
ResourceManagerHelper.get(ResourceType.ASSETS).registerReloadListener(new SimpleResourceReloadListener<MyResource>() {
  @Override
  public Identifier getFabricId() {
    return new Identifier("resourceloader", "load_resources");
  }
  
  @Override
  public CompletableFuture<MyResource> load(ResourceManager manager, Profiler profiler, Executor executor) {
    return CompletableFuture.supplyAsync(() -> {
      //Do loading tasks (read files, grab things from the ResourceManager, etc)
      //You're off-thread in this method, so don't touch the game.
      MyResource res = loadMyResource(manager);
      return res;
    }, executor);
  }
  
  @Override
  public CompletableFuture<Void> apply(MyResource res, ResourceManager manager, Profiler profiler, Executor executor) {
    return CompletableFuture.runAsync(() -> {
      //Your loaded resource gets threaded into   ^^^ the first argument of this method.
      //Apply the loaded data to the game somehow (dump caches and refill them, set variables, etc)
      applyMyResourceToTheGame(res);
    }, executor);
  }
});


}
