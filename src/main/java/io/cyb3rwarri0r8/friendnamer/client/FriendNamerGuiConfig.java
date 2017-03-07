package io.cyb3rwarri0r8.friendnamer.client;
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

import io.cyb3rwarri0r8.friendnamer.lib.Strings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import org.apache.logging.log4j.Level;


public class FriendNamerGuiConfig extends GuiConfig {
	/**
	 * GuiConfig constructor that will use ConfigChangedEvent when editing is concluded. If a non-null value is passed for configID,
	 * the OnConfigChanged and PostConfigChanged events will be posted when the Done button is pressed if any configElements were changed
	 * (includes child screens). If configID is not defined, the events will be posted if the parent gui is null or if the parent gui
	 * is not an instance of GuiConfig.
	 *
	 * @param parentScreen           the parent GuiScreen object
	 * @param configElements         a List of IConfigProperty objects
	 * @param modID                  the mod ID for the mod whose config settings will be edited
	 * @param configID               an identifier that will be passed to the OnConfigChanged and PostConfigChanged events. Setting this value will force
	 *                               the save action to be called when the Done button is pressed on this screen if any configElements were changed.
	 * @param allRequireWorldRestart send true if all configElements on this screen require a world restart
	 * @param allRequireMcRestart    send true if all configElements on this screen require MC to be restarted
	 * @param title                  the desired title for this screen. For consistency it is recommended that you pass the path of the config file being
	 */
	public FriendNamerGuiConfig( GuiScreen parentScreen ) {
		super( parentScreen,
				new ConfigElement( ConfigHandler.configuration.getCategory( Configuration.CATEGORY_GENERAL ) ).getChildElements(),
				Strings.MODID,
				false,
				false,
				GuiConfig.getAbridgedConfigPath( ConfigHandler.configuration.toString() ) );
	}

	private EntityPlayer player = this.mc.thePlayer;

	@Override
	public void onGuiClosed() {

		FMLLog.log( Level.INFO, "Firing player#refreshDisplayName" );
		if (isWorldRunning) {
			player.refreshDisplayName();
		}


	}
}
