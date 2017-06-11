package me.rtn.jump.status;

import org.bukkit.Location;
import org.bukkit.block.Sign;

/*
 * Jump
 * Copyright (C) 2017 RapidTheNerd
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
public class SignStatus {

    private Location location; //may not need this
    private Sign sing;
    private String name;
    private String serverIP; //could be a domain name instead of numeric
    private int port;

    public SignStatus(Location location, String name, String serverIP, int port) {
        this.location = location;;
        this.name = name;
        this.serverIP = serverIP;
        this.port = port;
    }
}
