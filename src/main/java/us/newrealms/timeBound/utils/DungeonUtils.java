package us.newrealms.timeBound.utils;

import java.util.HashMap;

public class DungeonUtils {
    static HashMap<?, ?> tutorial = new HashMap<>();
    public static void initializeDungeons(){
        tutorial.put("active","true");
        tutorial.put("soundroom1answer","1,2,3,4");
        tutorial.put("soundroom1sound1","BLOCK_NOTE_BLOCK_BASS");
        tutorial.put("soundroom1sound2","BLOCK_NOTE_BLOCK_BANJO");
        tutorial.put("soundroom1sound3","BLOCK_NOTE_BLOCK_BASEDRUM");
        tutorial.put("soundroom1sound4","BLOCK_NOTE_BLOCK_BIT");
        tutorial.put("firstkeycollected","false");
        tutorial.put("dooropened","false");
        tutorial.put("soundRoomSequence","");
    }
    public static void updateTutorialDungeon(String key, String value){
        tutorial.put(key,value);
    }
    public static String getTutorialDungeon(String key){
        return tutorial.get(key);
    }
}
