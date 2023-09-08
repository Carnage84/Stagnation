package content.quest.member.scorpioncatcher

import config.Components
import config.Items
import config.Vars
import content.quest.member.scorpioncatcher.util.SCUtils
import core.api.*
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.quest.Quest
import core.game.node.entity.skill.Skills
import core.plugin.Initializable

/**
 * Scorpion Catcher quest.
 */
@Initializable
class ScorpionCatcher : Quest("Scorpion Catcher", 108, 107, 1, Vars.VARP_QUEST_SCORPION_CATCHER_76, 0, 1, 6) {

    // Quest description:
    // Thormac has lost his rare lesser-kharid scorpions after leaving their cage door open.
    // These scorpions have hidden in areas that are rather difficult to get into.
    // You will have to overcome various challenges (and drink a lot of beer) to get all the scorpions back.
    // If you manage to help him, Thormac will improve your battlestaves.

    override fun drawJournal(player: Player?, stage: Int) {
        super.drawJournal(player, stage)
        player ?: return
        var line = 11

        if(stage == 0){
            line(player, "I can start this quest by speaking to !!Thormac?? who is in the", line++)
            line(player, "!!Sorcerer's Tower??, south-west of the !!Catherby??.", line++)
            line++
            line(player, "Requirements:", line++)
            line(player, if (hasLevelStat(player, Skills.PRAYER, 31)) "---Level 31 Prayer/--" else "!!Level 31 Prayer??", line++)
            line++
        }

        if(stage >= 10){
            line(player, "I've spoken to !!Thormac in the Sorcerer's Tower?? south-west", line++,stage>=30)
            line(player, "of !!Catherby??. He's lost his pet !!Kharid Scorpions?? and needs", line++,stage>=30)
            line(player, "my help to find them.", line++,stage>30)
            line++
        }

        if(getAttribute(player, SCUtils.SECRET_ROOM_ATTR, false)){
            line(player, "I've spoken to a !!Seer?? and been given the location of", line++, stage>=35)
            line(player, "one of the !!Kharid Scorpions??.", line++, stage>=35)
            line++
        }

        if(stage >= 35){
            line(player, "The first !!Kharid Scorpion?? is in a secret room near some", line++, getAttribute(player, SCUtils.PROGRESS_SC_ATTR_1, false) || stage == 100)
            line(player, "!!nasty spiders?? with two !!coffins?? nearby", line++, getAttribute(player, SCUtils.PROGRESS_SC_ATTR_1, false) || stage == 100)
            line++
        }

        if(stage >= 35){
            line(player, "I'll need to talk to a !!Seer?? again once I've caught the first", line++, getAttribute(player, SCUtils.NPCS_DIALOG_ATTR, false) || stage == 100)
            line(player, "!!Kharid Scorpion??.", line++, getAttribute(player, SCUtils.NPCS_DIALOG_ATTR, false) || stage == 100)
            line++
        }

        if(stage >= 35 && getAttribute(player, SCUtils.PROGRESS_SC_ATTR_1, false) && getAttribute(player, SCUtils.NPCS_DIALOG_ATTR, false)){
            line(player, "The second !!Kharid Scorpion?? has been in a !!village of??", line++, getAttribute(player, SCUtils.PROGRESS_SC_ATTR_2, false) || stage == 100)
            line(player, "!!uncivilised-looking warriors in the east.?? It's been picked up", line++, getAttribute(player, SCUtils.PROGRESS_SC_ATTR_2, false) || stage == 100)
            line(player, "by some sort of !!merchant??.", line++, getAttribute(player, SCUtils.PROGRESS_SC_ATTR_2, false) || stage == 100)
            line++
            line(player, "The third !!Kharid Scorpion?? is in some sort of !!upstairs room??", line++, getAttribute(player, SCUtils.PROGRESS_SC_ATTR_3, false) || stage == 100)
            line(player, "with !!brown clothing on a table??.", line++, getAttribute(player, SCUtils.PROGRESS_SC_ATTR_3, false) || stage == 100)
            line++
        }

        if(stage >= 35 && getAttribute(player, SCUtils.PROGRESS_SC_ATTR_3, false)){
            line(player, "I need to take the !!Kharid Scorpions?? to !!Thormac??.", line++, stage >= 100)
            line++
        }

        if(stage >= 100){
            line(player, "<col=FF0000>QUEST COMPLETE</col>", line++, false)
            line(player, "I've spoken to !!Thormac?? and he thanked me", line++)
            line(player, "for finding his pet !!Kharid Scorpions??.", line++)
        }
    }

    override fun finish(player: Player?){
        super.finish(player)
        player ?: return
        var ln = 10
        sendItemZoomOnInterface(player, Components.QUEST_COMPLETE_SCROLL_277,5,Items.SCORPION_CAGE_460)
        drawReward(player, "1 Quest Point", ln++)
        drawReward(player, "6625 Strength XP", ln)
        rewardXP(player, Skills.STRENGTH, 6625.00)
        removeAttributes(player, SCUtils.SECRET_ROOM_ATTR, SCUtils.NPCS_DIALOG_ATTR, SCUtils.PROGRESS_SC_ATTR_1, SCUtils.PROGRESS_SC_ATTR_2, SCUtils.PROGRESS_SC_ATTR_3)
    }

    override fun newInstance(`object`: Any?): Quest {
        return this
    }

}