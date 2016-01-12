package io.cyb3rwarri0r8.friendnamer;


import io.cyb3rwarri0r8.friendnamer.client.ConfigHandler;
import io.cyb3rwarri0r8.friendnamer.client.NamesEventHandler;
import io.cyb3rwarri0r8.friendnamer.lib.CommonProxy;
import io.cyb3rwarri0r8.friendnamer.lib.Strings;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Level;

import java.util.logging.Logger;


/*
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
@Mod(modid = Strings.MODID, name = "Friend Namer", version = Strings.VERSION, guiFactory = Strings.GUI_FACTORY_CLASS )

public class FriendNamer {

    public static CommonProxy proxy;

    public static Configuration configFile;

    @Mod.Instance
    public static FriendNamer instance;

    public static ModMetadata modMetadata;


    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event){
        configFile = new Configuration(event.getSuggestedConfigurationFile());
        ConfigHandler.init(configFile.getConfigFile());

        modMetadata = event.getModMetadata();
        configFile.load();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        FMLLog.info("Registering Names Event Handler");
        MinecraftForge.EVENT_BUS.register(new NamesEventHandler());
        FMLLog.info("Registering Config Handler Update Method");
        MinecraftForge.EVENT_BUS.register(new ConfigHandler());
    }

    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event){
        FMLLog.log(Level.INFO, event.getModState().toString());
    }
}
