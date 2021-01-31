package com.company;

import java.io.*;
import java.util.UUID;

import static java.lang.Integer.parseInt;

public class Mobs {

    public int MobsKilled(UUID uuid) throws IOException {

        String data = "";

        try {
            File Filepath = new File("plugins/MHU_GlobalScore/PlayerData/" + uuid.toString() + ".txt");

            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(Filepath));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            String line = null;
            try {
                line = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }


            while (line != null) {

                if (line.contains("Mobs Killed: ")) {

                    try {
                        data = line.split("Mobs Killed: ")[1];
                    }catch (Exception e){

                        data = "1";

                    }

                }
                // read next line
                try {
                    line = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            //END


            reader.close();



        } finally {

        }
        return parseInt(data);}





}
