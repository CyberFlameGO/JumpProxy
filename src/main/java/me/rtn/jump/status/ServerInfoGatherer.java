package me.rtn.jump.status;

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
public class ServerInfoGatherer {

    public static void main(String[] args){
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("localhost", 8080), 1 * 1000);

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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
