package content.quest.member.makinghistory

import config.Items
import config.Vars
import core.api.*
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.quest.Quest
import core.game.node.entity.skill.Skills
import core.game.world.GameWorld
import core.plugin.Initializable

/**
 * Making History quest.
 */
@Initializable
class MakingHistory : Quest("Making History", 86, 85, 3,Vars.VARBIT_QUEST_MAKING_HISTORY_1383,0,1,4) {
    companion object {
        const val questName = "Making History"
    }

    class SkillRequirement(val skill: Int?, val level: Int?)

    val requirements = arrayListOf<SkillRequirement>()
    override fun drawJournal(player: Player?, stage: Int) {
        super.drawJournal(player, stage)
        var line = 11
        val started = player?.questRepository?.getStage("Making History")!! > 0
        if (!started) {
            line(player, "I can start this quest by talking to !!Jorral?? in the !!Outpost??", line++)
            line(player, "!!North West of West Ardougne.??", line++)
            line += 1
            line(player, "Minimum requirements:", line++)
            line(player, "!!I must have completed the Priest in Peril Quest??", line++, isQuestComplete(player,"Priest in Peril"))
            line(player, "It will be easier with", line++)
            line(player, "!!Crafting lvl 24??", line++, getStatLevel(player, Skills.CRAFTING) >= 24)
            line(player, "!!Smithing lvl 40??", line++, getStatLevel(player, Skills.SMITHING) >= 40)
            line(player, "!!Mining lvl 40??", line++, getStatLevel(player, Skills.MINING) >= 40)
            line++
        } else if (started && stage != 100) {
            if (isStarted(player)) {
                line += 1
                line(player, "Jorral wants to save the outpost from King Lathas plans.", line++, stage > 2)
                line++
            }
            if (stage >= 5) {
                line(player, "I have gathered the parts of history. I have been given a letter.", line++, stage > 10)
                line++
            }
            if (stage >= 10) {
                line(player, "The king was pleased to see the history. He gave me a letter for Jorral", line++, stage > 15)
            }
        } else if (stage == 100) {
            line++
            line(player, "The outpost is saved!", line++)
            line++
            line(player, "<col=FF0000>QUEST COMPLETE!</col>", line)
        }
    }

    override fun finish(player: Player?) {
        super.finish(player)
        player ?: return
        var ln = 10
        player.packetDispatch.sendItemZoomOnInterface(Items.ENCHANTED_KEY_6754, 230, 277, 5)
        drawReward(player, "3 Quest points, 1000 Prayer", ln++)
        drawReward(player, "1000 Crafting XP, 750", ln++)
        drawReward(player, "gold coins. Use the enchanted", ln++)
        drawReward(player, "key all over " + GameWorld.settings!!.name + " Visit", ln++)
        drawReward(player, "the silver trader for help.", ln)

        rewardXP(player, Skills.CRAFTING, 1000.0)
        rewardXP(player, Skills.PRAYER, 1000.0)
        addItemOrDrop(player, Items.COINS_995, 750)
        setVarbit(player, Vars.VARBIT_MAKING_HISTORY_MUSEUM_1390, 1, true)
        setVarbit(player, Vars.VARBIT_QUEST_MAKING_HISTORY_1383, 4, true)
        removeAttributes(player, MakingHistoryListener.droalak, MakingHistoryListener.droalakProgress, MakingHistoryListener.merchant, MakingHistoryListener.merchantProgress, MakingHistoryListener.blanin, MakingHistoryListener.blaninProgress, MakingHistoryListener.jorralLetterAttr, MakingHistoryListener.dron)
    }

    override fun newInstance(`object`: Any?): Quest {
        requirements.add(SkillRequirement(Skills.CRAFTING, 24))
        requirements.add(SkillRequirement(Skills.SMITHING, 40))
        requirements.add(SkillRequirement(Skills.MINING, 40))
        return this
    }

}