package content.region.kandarin.seers.dialogue;

import content.quest.member.scorpioncatcher.dialogue.SeersSCDialogue;
import core.game.dialogue.DialoguePlugin;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.AchievementDiary;
import core.game.node.entity.player.link.diary.DiaryType;
import core.plugin.Initializable;

/**
 * Represents the Seer dialogue plugin.
 */
@Initializable
public class SeerDialogue extends DialoguePlugin {

    public SeerDialogue() {
    }

    public SeerDialogue(Player player) {
        super(player);
    }

    @Override
    public int[] getIds() {
        return new int[]{388};
    }

    @Override
    public DialoguePlugin newInstance(Player player) {

        return new SeerDialogue(player);
    }

    @Override
    public boolean open(Object... args) {
        npc = (NPC) args[0];
        // npc("Uh, what was that dark force? I've never sensed", "anything like it..."); // https://www.youtube.com/watch?v=mYsxit46rGo May 14 2010
        // npc("Anyway, sorry about that.");
        options("Talk about something else.", "Talk about achievement diary.");
        stage = 0;
        return true;
    }

    @Override
    public boolean handle(int interfaceId, int buttonId) {
        int level = 0;

        switch (stage) {
            case 999:
                end();
                break;
            case 0:
                switch (buttonId) {
                    case 1:
                        npc("Many greetings.");
                        stage = 1;
                        break;
                    case 2:
                        if (AchievementDiary.canReplaceReward(player, DiaryType.SEERS_VILLAGE, 0)) {
                            player("I seem to have lost my seers' headband...");
                            stage = 80;
                            break;
                        } else if (AchievementDiary.hasClaimedLevelRewards(player, DiaryType.SEERS_VILLAGE, 0)) {
                            player("Can you remind me what my headband does?");
                            stage = 90;
                            break;
                        } else if (AchievementDiary.canClaimLevelRewards(player, DiaryType.SEERS_VILLAGE, 0)) {
                            player("Hi. I've completed the Easy tasks in my Achievement", "Diary.");
                            stage = 200;
                            break;
                        } else {
                            player("Hi! Can you help me out with the Achievement Diary", "tasks?");
                            stage = 101;
                            break;
                        }
                }
                break;
            case 80:
                AchievementDiary.grantReplacement(player, DiaryType.SEERS_VILLAGE, 0);
                npc("Here's your replacement. Please be more careful.");
                stage = 999;
                break;
            case 90:
                npc("Your headband marks you as an honourary seer.", "Geoffrey - who works in the field to the", "south - will give you free flax every day.");
                stage = 999;
                break;
            case 100:
                npc("I certainly do - we have a set of tasks spanning Seers'", "Village, Catherby, Hemenster and the Sinclair Mansion.", "Just complete the tasks listed in the Achievement Diary", "and they will be ticked off automatically.");
                stage = 999;
                break;
            case 101:
                npc("I'm afraid not. It is important that adventurers", "complete the tasks unaided. That way, only the truly", "worthy collect the spoils.");
                stage = 999;
                break;
            case 200:
                npc("Well done, adventurer. You are clearly a " + (player.isMale() ? "man" : "woman") + "of", "great wisdom. I have a gift for you.");
                stage++;
                break;
            case 201:
                if (!AchievementDiary.flagRewarded(player, DiaryType.SEERS_VILLAGE, 0)) {
                    npc("Come back when you have two free inventory slots.");
                    stage = 999;
                } else {
                    interpreter.sendItemMessage(AchievementDiary.getRewards(DiaryType.SEERS_VILLAGE, 0)[0], "The seer hands you a strange-looking headband and a", "rusty lamp.");
                    stage++;
                }
                break;
            case 202:
                npc("You are now an honourary seer and Geoffrey - who", "works in the field to the south - will give you free flax", "every day. Don't call him 'Geoffrey' though: he prefers", "to be known as 'Flax'.");
                stage++;
                break;
            case 203:
                player("Flax? What sort of name is that for a person.");
                stage++;
                break;
            case 204:
                npc("I know, I know. The poor boy is a simple soul - he just", "really loves picking flax. A little too much, I fear.");
                stage = 999;
                break;

            case 1:
                if(player.getQuestRepository().getQuest("Scorpion Catcher").getStage(player) < 36){
                    player.getDialogueInterpreter().open(new SeersSCDialogue());
                    stage = 999;
                }else{
                    options("Many greetings.", "I seek knowledge and power!");
                    stage = 2;
                    break;
                }
                break;
            case 2:
                switch (buttonId) {
                    case 1:
                        player("Many greetings.");
                        stage = 10;
                        break;
                    case 2:
                        player("I seek knowledge and power!");
                        stage = 20;
                        break;
                }
                break;
            case 10:
                npc("Remember, whenever you set out to do something,", "something else must be done first.");
                stage = 999;
                break;
            case 20:
                npc("Knowledge comes from experience, power", "comes from battleaxes.");
                stage = 999;
                break;
        }
        return true;
    }

}
