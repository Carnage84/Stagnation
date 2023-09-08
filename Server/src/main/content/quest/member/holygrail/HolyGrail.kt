package content.quest.member.holygrail

import config.Items
import config.Vars
import core.api.getStatLevel
import core.api.isQuestComplete
import core.api.rewardXP
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.quest.Quest
import core.game.node.entity.skill.Skills
import core.plugin.Initializable

/**
 * Represents the "Holy Grail" quest.
 */
@Initializable
class HolyGrail: Quest("Holy Grail", 76, 75, 2, Vars.VARP_QUEST_HOLY_GRAIL_5, 0, 1, 10) {

    override fun drawJournal(player: Player?, stage: Int) {
        super.drawJournal(player, stage)
        var line = 11
        player?: return

        if(stage == 0){
            line(player, "!!King Arthur?? is sending out his knights on a quest for the", line++)
            line(player, "!!famous Holy Grail??. If you are a !!Knight of the Round Table??", line++)
            line(player, "go to !!King Arthur?? for further orders.", line++)
            line++
            line(player, "To start this quest, I require:", line++)
            line(player, "!!20 Attack (In order to wield Excalibur)??", line++, getStatLevel(player,Skills.ATTACK) >= 20)
            line(player, "!!Completion of Merlin's Crystal??", line++, isQuestComplete(player,"Merlin's Crystal"))
            line++
        }

        if(stage == 10){
            line(player, "!!King Arthur?? recommended I get more information about !!the??", line++)
            line(player, "!!Holy Grail?? from !!Merlin??.", line++)
            line++
        }

        if(stage == 20){
            line(player, "!!Merlin?? recommends looking for !!Entrana??, perhaps the !!High??", line++)
            line(player, "!!Priest?? of the church can help me?", line++)
            line++
        }

        if(stage == 30){
            line(player, "I need an !!artifact of The Fisher Realm??. Perhaps !!Brother??", line++)
            line(player, "!!Galahad?? can help me get one.", line++)
            line++
        }

        if(stage == 40){
            line(player, "I must get into the castle and speak to the !!king of The??", line++)
            line(player, "!!Fisher Realm??. However I need some type of whistle. I was", line++)
            line(player, "told I can find it in a haunted mansion. However I need", line++)
            line(player, "to have an artifact of the fisher realm for it to", line++)
            line(player, "appear.", line++)
            line++
            line(player, "Once I have the whistle I need to find a way to get there.", line++)
            line(player, "6 heads point to the location which I must find.", line++)
            line++
        }

        if(stage == 50){
            line(player, "I should find the !!Fisher King's son, Sir Percival??. Sir", line++)
            line(player, "Percival is also a knight of the round table. Perhaps", line++)
            line(player, "!!King Arthur?? will know how to get to him?", line++)
            line++
        }

        if(stage == 60){
            line(player, "Legend has it once !!The Fisher Realm?? is restored a hero", line++)
            line(player, "can claim the !!Holy Grail?? from the castle and after give", line++)
            line(player, "it to !!King Arthur??.", line++)
            line++
        }

        if(stage == 100){
            line(player, "<col=FF0000>QUEST COMPLETE!", line, false)
            line++
            line(player, "I returned to the !!Grail Castle?? to find that the land had", line++, false)
            line(player, "been renewed with !!Percival??", line++, true)
            line(player, "as the new King there out of gratitude", line++, true)
            line(player, "he allowed me to take the !!Grail?? which I took to !!King Arthur??", line++, true)
            line(player, "which I took to !!King Arthur?? to prove my prowess as a Knight.", line++, true)
        }
    }

    override fun finish(player: Player?) {
        super.finish(player)
        player ?: return
        var ln = 10
        player.packetDispatch.sendItemZoomOnInterface(Items.HOLY_GRAIL_19, 230, 277, 5)
        drawReward(player,"2 Quest Points", ln++)
        drawReward(player,"11,000 Prayer XP", ln++)
        drawReward(player,"15,300 Defence XP", ln++)
        drawReward(player,"Access to the Fisher Realm.", ln)
        rewardXP(player, Skills.PRAYER, 11000.0)
        rewardXP(player, Skills.DEFENCE, 15300.0)
    }

    override fun newInstance(`object`: Any?): Quest {
        return this
    }

}
