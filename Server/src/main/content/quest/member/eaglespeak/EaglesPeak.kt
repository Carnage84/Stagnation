package content.quest.member.eaglespeak

import config.Items
import config.Vars
import core.api.rewardXP
import core.api.setVarbit
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.quest.Quest
import core.game.node.entity.skill.Skills
import core.plugin.Initializable

/**
 * Eagles' Peak quest.
 */
@Initializable
class EaglesPeak : Quest("Eagles' Peak", 51, 50, 2,Vars.VARBIT_QUEST_EAGLES_PEAK_2780,0,1,40) {
    companion object {
        const val EaglesPeak = "Eagles' Peak"
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

        player.packetDispatch.sendItemZoomOnInterface(Items.FERRET_10092, 230, 277, 5)

        drawReward(player, "2 Quest Points", ln++)
        drawReward(player, "2,500 Hunter XP", ln++)
        rewardXP(player, Skills.HUNTER, 2500.0)
        setVarbit(player, Vars.VARBIT_QUEST_EAGLES_PEAK_2780, 40, true)
    }

    override fun newInstance(`object`: Any?): Quest {
        return this
    }

}