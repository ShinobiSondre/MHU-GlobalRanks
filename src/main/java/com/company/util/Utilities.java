package com.company.util;


import com.company.Main;
import com.company.Mobs;
import com.company.Position;
import com.company.TotalScore;
import me.lucko.luckperms.api.Contexts;
import me.lucko.luckperms.api.LuckPermsApi;
import me.lucko.luckperms.api.Node;
import me.lucko.luckperms.api.User;
import me.nomonewman.nameTag.NameTagAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Utilities {

    Main plugin;
    NameTagAPI NAMEAPI;

    public Main getPluginInstance() { return  plugin; }

    public Utilities(Main plugin) {
        this.plugin = plugin;
        NAMEAPI = (NameTagAPI) plugin.getServer().getPluginManager().getPlugin("NameTagAPI");
    }

    public static HashMap<String,String> SurpassedEvent = new HashMap();

    //CLASSES
    public TotalScore ts = new TotalScore(this);
    public Position p = new Position(this);
    public Mobs m = new Mobs();

    public Position getReference() {return p;}

    //CHECKS
    public static boolean readmobkills;
    public static boolean teleported = true;

    //TIMER
    public static Timer t = new Timer();
    public static Map<String, Integer> taskID = new HashMap<String, Integer>();
    public static HashMap<String,Integer>textwait = new HashMap<>();

    //MOB KILLS
    public int kills = 0;
    public static HashMap<UUID, Integer> mobKills = new HashMap<>();

    //This plugin instance


    //API
    LuckPermsApi api = (LuckPermsApi) Bukkit.getServicesManager().getRegistration(LuckPermsApi.class).getProvider();

    public void CalculatePosition(Player player) throws IOException {

        String killer = player.getName();
        Location where = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY() + 2.7, player.getLocation().getZ());

        int position_citizen = 0;
        int position_student = 0;
        int position_sidekick = 0;
        int position_hero = 0;
        int position_prohero = 0;

        int position_thug = 0;
        int position_delinquent = 0;
        int position_villain = 0;
        int position_provillain = 0;

        int counter_citizen = 0;
        int counter_student = 0;
        int counter_sidekick = 0;
        int counter_hero = 0;
        int counter_prohero = 0;

        int counter_thug = 0;
        int counter_delinquent = 0;
        int counter_villain = 0;
        int counter_provillain = 0;



        //HOLOGRAMS HERE
        //REMOVE THE HOLOGRAM HERE TO BE LATER READDED/UPDATED DOWN BELOW

        //Calculate Score

        Set<String> keys = HighestScoreList().keySet();

        for(String key : keys){

            if(key.toString().contains(" default"))
                counter_citizen++;
            else if(key.toString().contains(" student"))
                counter_student++;
            else if(key.toString().contains(" sidekick"))
                counter_sidekick++;
            else if(key.toString().contains(" hero"))
                counter_hero++;
            else if(key.toString().contains(" pro-hero"))
                counter_prohero++;
            else if(key.toString().contains(" thug"))
                counter_thug++;
            else if(key.toString().contains(" delinquent"))
                counter_delinquent++;
            else if(key.toString().contains(" villain"))
                counter_villain++;
            else if(key.toString().contains(" pro-villain"))
                counter_provillain++;


            if(key.toString().split(" default")[0].contains(player.getUniqueId().toString()))
                position_citizen = counter_citizen;
            if(key.toString().split(" student")[0].contains(player.getUniqueId().toString()))
                position_student = counter_student;
            if(key.toString().split(" sidekick")[0].contains(player.getUniqueId().toString()))
                position_sidekick = counter_sidekick;
            if(key.toString().split(" hero")[0].contains(player.getUniqueId().toString()))
                position_hero = counter_hero;
            if(key.toString().split(" pro-hero")[0].contains(player.getUniqueId().toString()))
                position_prohero = counter_prohero;

            if(key.toString().split(" thug")[0].contains(player.getUniqueId().toString()))
                position_thug = counter_thug;
            if(key.toString().split(" delinquent")[0].contains(player.getUniqueId().toString()))
                position_delinquent = counter_delinquent;
            if(key.toString().split(" villain")[0].contains(player.getUniqueId().toString()))
                position_villain = counter_villain;
            if(key.toString().split(" pro-villain")[0].contains(player.getUniqueId().toString()))
                position_provillain = counter_provillain;
        }

        //HOLOGRAMS HERE
        //Where it says grgram.appendTextLine just change it to ur HologramUpdate or HologramCreate Method
        //the string should be kept as is
        switch(highestgroup(player)) {
            case "default":
                updatePlayerTag(player ,"§f§l[§7Citizen§f§l]" + " §f§l[§7" + position_citizen + "§f§l]");
                break;
            case "student":
                updatePlayerTag(player ,"§f§l[§bStudent§f§l]" + " §f§l[§b"+ position_student + "§f§l]");
                break;
            case "sidekick":
                updatePlayerTag(player ,"§f§l[§1Sidekick§f§l]" + " §f§l[§1" + position_sidekick + "§f§l]");
                break;
            case"hero":
                updatePlayerTag(player ,  "§f§l[§eHero§f§l]" + " §f§l[§e" + position_hero + "§f§l]");
                break;
            case "pro-hero":
                updatePlayerTag(player ,"§f§l[§6Pro-Hero§f§l]" + " §f§l[§6" + position_prohero + "§f§l]");
                break;
            case "thug":
                updatePlayerTag(player , "§f§l[§cThug§f§l]" + " §f§l[§c"+ position_thug + "§f§l]");
                break;
            case "delinquent":
                updatePlayerTag(player ,"§f§l[§5Delinquent§f§l]" + " §f§l[§5" + position_delinquent + "§f§l]");
                break;
            case "villain":
                updatePlayerTag(player , "§f§l[§cVillain§f§l]" + " §f§l[§c" + position_villain + "§f§l]");
                break;
            case "pro-villain":
                updatePlayerTag(player ,"§f§l[§4Pro-Villain§f§l]" + " §f§l[§4" + position_provillain + "§f§l]");
                break;
        }


    }

    public void updatePlayerTag(Player player, String value) {
        NAMEAPI.updateThing(value, player);
    }

    public String highestgroup(Player player){


        HashMap<String, Integer> primary = new HashMap<>();

        User user = api.getUserManager().getUser(player.getUniqueId());
        if (user == null) {
        } else {
            Contexts contexts = api.getContextsForPlayer(player);

            Stream var10000 = user.getPermissions().stream().filter(Node::isGroupNode).filter((n) -> {
                return n.shouldApplyWithContext(contexts.getContexts());
            });

            LuckPermsApi var10001 = api;
            var10001.getClass();

            ArrayList<Node> groups = (ArrayList<Node>) var10000.collect(Collectors.toList());

            for(int i = 0; i<groups.size(); i++){

                primary.put(groups.get(i).getGroupName(),var10001.getGroup(groups.get(i).getGroupName()).getWeight().getAsInt());

            }


        }

        Set<String> keys = sortByValue(primary,true).keySet();


        int i = 0;

        for(String key: keys){

            i++;

            if(i==1)
                return key;

        }

        return "default";}


    public void RankUpdater(long ticks,String indentifier){
        if(plugin == null) {
            Bukkit.broadcastMessage("plugin reference null broski");
            return;
        }
        final int tid = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            public void run() {

                for(Player player : Bukkit.getOnlinePlayers()) {

                    try {
                        if (!Rank(player.getUniqueId()).equals(highestgroup(player))) {

                            for (Player hs : Bukkit.getOnlinePlayers()) {

                                try {
                                    ts.writeTotalPoints(hs.getPlayer().getUniqueId(), hs.getPlayer());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                try {
                                    CalculatePosition(hs.getPlayer());

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }


                              //  DelayedHologram(hs, 60, hs.getUniqueId() + "ChangeRank");


                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            }
        }, 0, ticks); //schedule task with the ticks specified in the arguments


    }




    public String Rank(UUID uuid) throws IOException {

        Reader("PlayerData",uuid.toString(),"Rank: ");

        return Reader("PlayerData",uuid.toString(),"Rank: ").get(0);}




    public ArrayList<String> Reader(String folder, String filename, String keyword) throws IOException {

        ArrayList <String> data = new ArrayList();
        try {
            File Filepath = new File("plugins/MHU_GlobalScore/" + folder + "/" + filename + ".txt");
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
                if (line.contains(keyword)) {
                    data.add(line.split(keyword)[1]);
                }
                // read next line
                try {
                    line = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            reader.close();
        } finally {}
        return data;}


    public String Name(UUID uuid) throws IOException {


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

                if (line.contains("Playername: ")) {

                    data = line;

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

        return data.split("Playername: ")[1];}




    public ArrayList<String> BelowPlayers(UUID uuid) throws IOException {

        ArrayList<String> BelowPlayers = new ArrayList<>();

        if(readmobkills == true)
            BelowPlayers.clear();

        String data = "";

        try {
            File Filepath = new File("plugins/MHU_GlobalScore/BelowPlayers/" + uuid.toString() + ".txt");

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

                if (line.contains("Below: ")) {

                    data = line.split("Below: ")[1];

                    BelowPlayers.add(data);



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

        return BelowPlayers;}



    HashMap<String, String> Surpassed = new HashMap<>();

    public void ThreeClostsToScore(Player player) throws IOException {

        String belowplayersuno = "";
        String loginmessage = "";

        try{Reader("BelowPlayers",player.getUniqueId().toString(),"Below: ");}catch (Exception e){Writer("Below: Noone", player.getUniqueId().toString(),"BelowPlayers");}
        try{Reader("PreviousBelow",player.getUniqueId().toString(),"Below: ");}catch (Exception e){Writer("Below: Noone", player.getUniqueId().toString(),"PreviousBelow");}

        int position = ts.TotalScoreFromFile(player.getUniqueId());

        if(!Surpassed.containsKey(player.getUniqueId().toString()))
            Surpassed.put(player.getUniqueId().toString(),"Nothing");

        int j = 0;

        for(int i = 0; i<HighestScoreList().size(); i++){


            if(HighestScoreList().keySet().toArray()[i].toString().contains(" " + Rank(player.getUniqueId()))){

                String uuid1 = HighestScoreList().keySet().toArray()[i].toString().split(" " + Rank(player.getUniqueId()))[0];


                if(position < p.ReturnPosition(player)){

                    j++;

                    if(uuid1!=player.getUniqueId().toString()&&j<4){

                        belowplayersuno += "Below: " + Name(UUID.fromString(uuid1)) + "\n";


                        // if(!Surpassed.get(player.getUniqueId().toString()).contains(Name(UUID.fromString(uuid1)))){

                        int v = 0;

                        if(!Reader("BelowPlayers",player.getUniqueId().toString(),"Below: ").contains(Name(UUID.fromString(uuid1)))){

                            v++;

                            if(v==1){
                                player.sendMessage("      ");
                                player.sendMessage("§a§l" + Name(UUID.fromString(uuid1)) + " §c§lhas surpassed you in rank!\n Even though they were below you?!");
                                player.sendMessage("      ");}
                            else{
                                player.sendMessage("§a§l" + Name(UUID.fromString(uuid1)) + " §c§lhas surpassed you in rank!\n Even though they were below you?!");
                                player.sendMessage("      ");}

                            loginmessage += "§a§l" + Name(UUID.fromString(uuid1)) + " §c§lhas surpassed you in rank!\n Even though he was below you?!";

                            for(Player player1 : Bukkit.getOnlinePlayers())
                                DelayedHologram(player1,50,"Server"+player1.getUniqueId().toString());}
                    }
                }
            }


        }

        Surpassed.put(player.getUniqueId().toString(),belowplayersuno);

        Writer(belowplayersuno, player.getUniqueId().toString(),"BelowPlayers");

        SurpassedEvent.put(player.getUniqueId().toString(),loginmessage);


    }

    public void DelayedHologram(final Player sender, long ticks,String indentifier){


        textwait.put(sender.getName()+indentifier,0);

        final boolean[] check1 = {false};


        final int tid = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            public void run() {

                textwait.put(sender.getName()+indentifier,textwait.get(sender.getName()+indentifier)+1);


                //END
                if (textwait.get(sender.getName()+indentifier)==2) {

                    Player hs = sender;

                    try {
                        ts.writeTotalPoints(hs.getPlayer().getUniqueId(), hs.getPlayer());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        CalculatePosition(hs.getPlayer());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if(indentifier.equals("Login"+hs.getPlayer().getUniqueId().toString())){

                        //PreviousCheck.put(hs.getPlayer().getUniqueId().toString(),1);

                        if(SurpassedEvent.containsKey(hs.getUniqueId().toString())){

                            try {
                                ThreeClostsToScore(hs);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            hs.sendMessage(SurpassedEvent.get(hs.getUniqueId().toString()));

                        }

                        SurpassedEvent.remove(hs.getUniqueId().toString());

                    }


                    TextendTask(sender.getName()+indentifier);

                }
            }
        }, 0, ticks); //schedule task with the ticks specified in the arguments

        taskID.put(sender.getName()+indentifier, tid); //put the player in a hashmap


    }

    public void TextendTask(String p){
        if(taskID.containsKey(p)){
            int tid = taskID.get(p); //get the ID from the hashmap
            plugin.getServer().getScheduler().cancelTask(tid); //cancel the task
            taskID.remove(p); //remove the player from the hashmap
        }
    }


    public Map<String, Integer> HighestScoreList() throws IOException {

        HashMap<String, Integer> Players = new HashMap<>();

        List<Path> result;
        try (Stream<Path> walk = Files.walk(Paths.get("plugins/MHU_GlobalScore/PlayerData/"))) {
            result = walk.filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        }


        for(int i = 0; i<result.size(); i++){

            String meh = result.get(i).toString();


            String uuid = meh.split("[.]")[0].split("plugins/MHU_GlobalScore/PlayerData/")[1];



            Players.put(uuid + " " + Rank(UUID.fromString(uuid)),ts.TotalScoreFromFile(UUID.fromString(meh.split("[.]")[0].split("plugins/MHU_GlobalScore/PlayerData/")[1])));


        }return sortByValue(Players,false);}


    public void Writer(String input, String filename, String folder) throws IOException {
        // "plugins/MHU_GlobalScore/PlayerData/" + uuid + ".txt"  Basically filepath then the file u generate at the end which is uuid + ".txt"

        File file = new File("plugins/MHU_GlobalScore/" + folder + "/" + filename + ".txt");

        //Creates a new file if it doesn't exist granted that the folder exists
        //You can make it auto generate the folder, but if ppl somehow get a hold of the plugin it won't work without them knowing to create a folder
        //Thats why I like to make it not autogenerate the folder
        if(!file.exists())
            file.createNewFile();


        //append set to false rewrites the file basically clearing it and writing data again if you set append to true you just add stuff to the file (Which is not recommended).

        FileWriter objectName = new FileWriter(file, false);
        PrintWriter pw = new PrintWriter(objectName);


        //Try and Catch works like this: Try something if it doesn't work then do this thing in Catch

        try {

            pw.write(input);

            /*System.out.print("-------------------");
            System.out.print("Wrote Files to: " + "plugins/MHU_GlobalScore/" + folder + "/" + filename + ".txt");
            System.out.print("-------------------");*/

        }catch (Exception e){

            pw.write("Content if first doesn't work");



        }

        //everything after this point just closes stuff and makes sure it doesnt keep anything open / writing

        pw.flush();
        pw.close();

        objectName.close();}


    public String GlobalRank (Player player) throws IOException {

        String str = highestgroup(player);

        if(str == "default")
            str = "citizen";

        String cap = str.substring(0, 1).toUpperCase() + str.substring(1);

        if(str=="pro-hero")
            cap="Pro-Hero";

        if(str=="pro-villain")
            cap="Pro-Villain";

        String globalrank = cap + " §c[" +  p.ReturnPosition(player)+"§c]";


        return globalrank;}


    public Map<String, Integer> sortByValue(Map<String, Integer> unsortMap, final boolean order)
    {
        List<Map.Entry<String, Integer>> list = new LinkedList<>(unsortMap.entrySet());

        // Sorting the list based on values
        list.sort((o1, o2) -> order ? o1.getValue().compareTo(o2.getValue()) == 0
                ? o1.getKey().compareTo(o2.getKey())
                : o1.getValue().compareTo(o2.getValue()) : o2.getValue().compareTo(o1.getValue()) == 0
                ? o2.getKey().compareTo(o1.getKey())
                : o2.getValue().compareTo(o1.getValue()));
        return list.stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> b, LinkedHashMap::new));

    }


}


