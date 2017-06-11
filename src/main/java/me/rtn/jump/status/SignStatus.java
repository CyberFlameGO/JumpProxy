package me.rtn.jump.status;

import org.bukkit.Location;
import org.bukkit.block.Sign;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

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
    private Sign sign;
    private String name;
    private String serverIP; //could be a domain name instead of numeric
    private int port;

    public SignStatus(Location location, String name, String serverIP, int port) {
        this.location = location;;
        this.name = name;
        this.serverIP = serverIP;
        this.port = port;
        this.sign = (Sign) location.getBlock().getState();
    }

    public void update(){
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(serverIP, port), 1 * 1000);

            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            DataInputStream input = new DataInputStream(socket.getInputStream());

            output.write(0xFE);

            StringBuilder builder = new StringBuilder();
            int b;
            while ((b = input.read()) != -1) {
                if(b != 0 && b > 16 && b != 255 && b != 23 && b != 24){
                    builder.append((char) b);
                }
            }
            String[] data = builder.toString().split("&c");
            String motd = data[0];
            int onlinePlayers = Integer.valueOf(data[1]);
            int maxPlayers = Integer.valueOf(data[2]);
            /*
                -------------
                |Server Name|
                |Server IP  |
                |Server MOTD|
                -------------
                      |
                      |
             */
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Location getLocation() {
        return location;
    }

    public Sign getSign() {
        return sign;
    }

    public String getName() {
        return name;
    }

    public String getServerIP() {
        return serverIP;
    }

    public int getPort() {
        return port;
    }
}
