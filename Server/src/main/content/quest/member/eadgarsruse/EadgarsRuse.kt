package content.quest.member.eadgarsruse

import config.Items.BURNT_MEAT_2146
import config.Vars
import core.api.addItemOrDrop
import core.api.rewardXP
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.quest.Quest
import core.game.node.entity.skill.Skills
import core.plugin.Initializable

/**
 * Eadgar's Ruse quest.
 */
@Initializable
class EadgarsRuse : Quest("Eadgar's Ruse", 50, 49, 1, Vars.VARP_QUEST_EADGARS_RUSE_335, 0, 1, 110) {
    companion object {
        const val EadgarsRuse = "Eadgar's Ruse"
    }

    override fun drawJournal(player: Player?, stage: Int) {
        super.drawJournal(player, stage)
        var line = 11
        player ?: return

        if (stage == 100) {
            line++
            line(player, "<col=FF0000>QUEST COMPLETE!", line, false)
        }
    }

    override fun finish(player: Player?) {
        super.finish(player)
        player ?: return
        var ln = 10

        player.packetDispatch.sendItemZoomOnInterface(BURNT_MEAT_2146, 230, 277, 5)

        drawReward(player, "1 Quest Point", ln++)
        drawReward(player, "11,000 Herblore XP", ln++)
        drawReward(player, "1 Burnt meat", ln)
        // Trollheim Teleport Spell
        rewardXP(player, Skills.HERBLORE, 11000.0)
        addItemOrDrop(player, BURNT_MEAT_2146, 1)
    }

    override fun newInstance(`object`: Any?): Quest {
        return this
    }

}