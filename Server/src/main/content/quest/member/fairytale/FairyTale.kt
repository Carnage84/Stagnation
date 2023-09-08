package content.quest.member.fairytale

import config.Items
import config.Vars
import core.api.addItemOrDrop
import core.api.rewardXP
import core.api.setVarbit
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.quest.Quest
import core.game.node.entity.skill.Skills
import core.plugin.Initializable

/**
 * A Fairy Tale I - Growing Pains quest.
 */
@Initializable
class FairyTale : Quest("Fairytale I - Growing Pains", 57, 56, 2,Vars.VARBIT_QUEST_FAIRY_TALE_I_GROWING_PAINS_1803,0,1,90) {
    companion object {
        const val FairyTale = "Fairytale I - Growing Pains"
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

        player.packetDispatch.sendItemZoomOnInterface(Items.MAGIC_SECATEURS_7409, 230, 277, 5)

        drawReward(player, "2 Quest Points", ln++)
        drawReward(player, "3,500 Farming XP", ln++)
        drawReward(player, "2,000 Attack XP", ln++)
        drawReward(player, "1,000 Magic XP", ln++)
        rewardXP(player, Skills.FARMING, 3500.0)
        rewardXP(player, Skills.ATTACK, 2000.0)
        rewardXP(player, Skills.MAGIC, 1000.0)
        addItemOrDrop(player, Items.MAGIC_SECATEURS_7409)
        setVarbit(player, Vars.VARBIT_QUEST_FAIRY_TALE_I_GROWING_PAINS_1803, 90, true)
    }


    override fun newInstance(`object`: Any?): Quest {
        return this
    }

}
//2 quest points
//Magic secateurs (increases Farming-yields by 10% when harvesting allotments, herbs and hops)
//3,500 Farming experience
//2,000 Attack experience
//1,000 Magic experience