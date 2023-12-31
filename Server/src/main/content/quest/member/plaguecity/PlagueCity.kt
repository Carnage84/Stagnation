package content.quest.member.plaguecity

import config.Items
import config.Vars
import core.api.addItemOrDrop
import core.api.removeAttributes
import core.api.rewardXP
import core.api.setVarbit
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.quest.Quest
import core.game.node.entity.skill.Skills
import core.plugin.Initializable

/**
 * Plague City quest.
 */
@Initializable
class PlagueCity : Quest("Plague City", 98, 97, 1, Vars.VARP_QUEST_PLAGUE_CITY_165, 0, 1, 29) {
    override fun newInstance(`object`: Any?): Quest { return this }
    companion object { const val PlagueCityQuest = "Plague City" }
    override fun drawJournal(player: Player?, stage: Int) {
        super.drawJournal(player, stage)
        var line = 11
        player ?: return

        if (stage == 0) {
            line++
            line(player, "I can start this quest by speaking to !!Edmond?? who is in !!East??", line++, false)
            line(player, "!!Ardougne??",line++, false)
            line++
            line(player, "There aren't any requirements for this quest.", line++, false)
            line++
        }

        if (stage >= 1) {
            line(player, "I've spoken to Edmond, he's asked me to", line++, stage >= 2)
            line(player, "help find his daughter Elena.", line++, stage >= 2)
            line++
        }

        if (stage >= 2) {
            line(player, "Alrena has given me a Gasmask to protect me", line++, stage >= 3)
            line(player, "from the plague while in West Ardougne.", line++, stage >= 3)
            line++
        }

        if (stage >= 3) {
            line(player, "She's making a spare which will be in the wardrobe.", line++, stage >= 4)
            line(player, "I've spoken to Edmond about getting into West Ardougne.", line++, stage >= 4)
            line++
        }

        if (stage >= 4) {
            line(player, "I've softened the ground in Edmond's garden enough to dig.", line++, stage >= 5)
            line++
        }

        if (stage >= 5) {
            line(player, "I've dug a tunnel into the sewers.", line++, stage >= 6)
            line++
        }

        if (stage >= 6) {
            line(player, "I've managed to clear the way into West Ardougne.", line++, stage >= 7)
            line++
        }

        if (stage >= 9) {
            line(player, "I've spoken to Jethick, he thinks Elena was staying with the", line++, stage >= 11)
            line(player, "Rehnison Family, in a timber house to the north of the city.", line++, stage >= 11)
            line++
        }

        if (stage >= 11) {
            line(player, "I've spoken to Milli about Elena,", line++, stage >= 12)
            line(player, "she says Elena was taken into one of the Plague Houses.", line++, stage >= 12)
            line++
        }

        if (stage >= 15) {
            line(player, "Bravek might give me clearance if I make his Hangover Cure.", line++, stage >= 16)
            line++
        }

        if (stage >= 16) {
            line(player, "I've given Bravek the Hangover Cure and he has", line++, stage >= 17)
            line(player, "given me a warrant to enter the plague House.", line++, stage >= 17)
            line++
        }

        if (stage >= 99) {
            line(player, "I've freed Elena from the Plague House.", line++, stage == 100)
            line++
        }

        if (stage == 100) {
            line(player, "I've spoken to Edmond and he thanked me", line++, true)
            line(player, "for rescuing his daughter Elena.", line++, true)
            line++
            line(player, "<col=FF0000>QUEST COMPLETE!", line, false)
        }
    }

    override fun finish(player: Player?) {
        super.finish(player)
        player ?: return
        var ln = 10

        player.packetDispatch.sendItemZoomOnInterface(Items.GAS_MASK_1506, 230, 277, 5)
        drawReward(player, "1 Quest Point", ln++)
        drawReward(player, "2,425 Mining XP", ln++)
        drawReward(player, "An Ardougne Teleport Scroll", ln)
        rewardXP(player, Skills.MINING, 2425.0)
        addItemOrDrop(player, Items.A_MAGIC_SCROLL_1505)
        setVarbit(player, 1784, 1, save = true)
        removeAttributes(player, "/save:elena:dig", "/save:elena:bucket", "/save:elena:cure")
    }
}