package io.cyb3rwarri0r8.friendnamer.client;


import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Level;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
public class NamesEventHandler {
	//    public static net.minecraftforge.event.entity.player.PlayerEvent.NameFormat nameFormat;




	@SubscribeEvent( priority = EventPriority.HIGH )
	public void onEvent( net.minecraftforge.event.entity.player.PlayerEvent.NameFormat event ) {
		FMLLog.log( Level.DEBUG, "Firing PlayerEvent.NameFormat event" );
		for ( int i = 0; i < ConfigHandler.usernames.length; i++ ) {
			if ( event.displayname.equalsIgnoreCase( ConfigHandler.usernames[i] ) ) {
				event.displayname = ConfigHandler.nicknames[i];
				FMLLog.info( "Username: " + event.username + " is now: " + ConfigHandler.nicknames[i] );
			}
		}
	}

	@SubscribeEvent
	public void onChatEvent( ClientChatReceivedEvent chatReceivedEvent ) {
		FMLLog.log( Level.INFO, "Recieved client chat! Checking against username config" );
		// Get the message
		IChatComponent chatComponent = chatReceivedEvent.message;
		// Get message type (unneeded)
		byte type = chatReceivedEvent.type;
		//Message String
		String text = chatComponent.getUnformattedText();
		// Test log
		FMLLog.log( Level.INFO, text + " : " + type);
		// Make the pattern
		Pattern pattern = Pattern.compile( "/<(\\w+)>/g" );
		// Match the pattern
		Matcher matcher = pattern.matcher( text );
		matcher.find();
		// Log it FIXME it always comes out false.. why?
		FMLLog.log( Level.INFO, "Does match: " + matcher.find() + "." + " Text is: " + matcher.group( 1 ) );
	}

}
