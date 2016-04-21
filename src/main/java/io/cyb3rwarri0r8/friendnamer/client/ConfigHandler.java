package io.cyb3rwarri0r8.friendnamer.client;


import io.cyb3rwarri0r8.friendnamer.lib.Strings;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.GuiIngameModOptions;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Level;

import java.io.File;

/**
 * FriendNamer - A Minecraft Modification
 * Copyright (C) 2015 Cyb3rWarri0r8
 * <p/>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
public class ConfigHandler {
    static Configuration configuration;

    static String[] usernames;

    static String[] nicknames;



    public static void init(File configFile){
        if (configuration == null){
            configuration = new Configuration(configFile, true);
            loadConfiguration();
        }
    }

    private static void loadConfiguration(){
        usernames = configuration.getStringList("Usernames", Configuration.CATEGORY_GENERAL, new String[]{"Enter"," Usernames"}, "Enter the username you want to change");
        nicknames = configuration.getStringList("Nicknames", Configuration.CATEGORY_GENERAL, new String[]{"Enter the nickname", " In the same position as the above username"}, "Enter the nickname");
        if (configuration.hasChanged()){
            configuration.save();
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onConfigurationChanged(ConfigChangedEvent.OnConfigChangedEvent event){
        FMLLog.log(Level.DEBUG, "Config has changed!");
        if (event.modID.equalsIgnoreCase(Strings.MODID)){
            FMLLog.log(Level.DEBUG, "Reloading Configuration!");
            loadConfiguration();
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public void onEvent(GuiOpenEvent event)
    {
        if (event.gui instanceof GuiIngameModOptions)
        {
            System.out.println("GuiOpenEvent for GuiIngameModOptions");
            event.gui = new FriendNamerGuiConfig(null);
        }
    }
}
