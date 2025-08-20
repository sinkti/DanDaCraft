package com.sinkti.dandacraft;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.lwjgl.glfw.GLFW;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = DandaCraft.MOD_ID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = DandaCraft.MOD_ID, value = Dist.CLIENT)
public class DandaCraftClient {
    // Keybind category
    private static final String KEY_CATEGORY = "key.categories.dandacraft";

    // Ability key mappings
    public static final KeyMapping ABILITY_1 = new KeyMapping("key.dandacraft.ability_1", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_C, KEY_CATEGORY);
    public static final KeyMapping ABILITY_2 = new KeyMapping("key.dandacraft.ability_2", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_V, KEY_CATEGORY);
    public static final KeyMapping ABILITY_3 = new KeyMapping("key.dandacraft.ability_3", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_B, KEY_CATEGORY);

    public DandaCraftClient(ModContainer container) {
        // Allows NeoForge to create a config screen for this mod's configs.
        // The config screen is accessed by going to the Mods screen > clicking on your mod > clicking on config.
        // Do not forget to add translations for your config options to the en_us.json file.
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        // Some client setup code
        DandaCraft.LOGGER.info("HELLO FROM CLIENT SETUP");
        DandaCraft.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());


    }

    @SubscribeEvent
    static void registerKeys(RegisterKeyMappingsEvent event) {
        event.register(ABILITY_1);
        event.register(ABILITY_2);
        event.register(ABILITY_3);
    }

    @SubscribeEvent
    static void onKeyInput(InputEvent.Key event) {
        var player = Minecraft.getInstance().player;
        if (player == null) {
            return;
        }

        while (ABILITY_1.consumeClick()) {
            if (player.isShiftKeyDown()) {
                DandaCraft.LOGGER.info("Ability One Alt");
            } else {
                DandaCraft.LOGGER.info("Ability One");
            }
        }

        while (ABILITY_2.consumeClick()) {
            if (player.isShiftKeyDown()) {
                DandaCraft.LOGGER.info("Ability Two Alt");
            } else {
                DandaCraft.LOGGER.info("Ability Two");
            }
        }

        while (ABILITY_3.consumeClick()) {
            if (player.isShiftKeyDown()) {
                DandaCraft.LOGGER.info("Ability Three Alt");
            } else {
                DandaCraft.LOGGER.info("Ability Three");
            }
        }
    }
}
