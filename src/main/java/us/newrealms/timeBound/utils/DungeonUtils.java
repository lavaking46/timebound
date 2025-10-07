package us.newrealms.timeBound.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.bukkit.*;

public class DungeonUtils {
    public static List<Integer> AnswerRandomizer(int length, int random1, int random2) {
        List<Integer> answer = new java.util.ArrayList<>();
        while(length > 0){
            Random rand = new Random();
            Integer mapInt = rand.nextInt(random1,random2);
            answer.add(mapInt);
            length -= 1;
        }
        return answer;
    }
    public static Sound PuzzleSounds(String puzzle,Integer sound){
        switch(puzzle) {
            case "TutorialSoundPuzzleOne":
                switch(sound){
                    case 1:
                        return Sound.BLOCK_NOTE_BLOCK_BASS;
                    case 2:
                        return Sound.BLOCK_NOTE_BLOCK_BANJO;
                    case 3:
                        return Sound.BLOCK_NOTE_BLOCK_BASEDRUM;
                    case 4:
                        return Sound.BLOCK_NOTE_BLOCK_BIT;
                }
                break;
            case "other":
                break;
        }
        return Sound.PARTICLE_SOUL_ESCAPE;
    }
}
