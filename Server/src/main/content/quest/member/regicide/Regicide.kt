package content.quest.member.regicide

import config.Items
import config.Vars
import core.api.addItemOrDrop
import core.api.rewardXP
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.quest.Quest
import core.game.node.entity.skill.Skills
import core.plugin.Initializable

/**
 * Regicide quest.
 */
@Initializable
class Regicide : Quest("Regicide", 104, 103, 3, Vars.VARP_QUEST_REGICIDE_328, 0, 1, 15) {
    companion object {
        const val Regicide = "Regicide"
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
        player.packetDispatch.sendItemZoomOnInterface(Items.COINS_995, 230, 277, 5)
        drawReward(player, "3 Quest Points", ln++)
        drawReward(player, "13,750 Strength XP", ln++)
        drawReward(player, "15,000 Coins", ln++)
        rewardXP(player, Skills.STRENGTH, 13750.0)
        addItemOrDrop(player, Items.COINS_995, 15000)
    }

    override fun newInstance(`object`: Any?): Quest {
        return this
    }

}
//3 quest points
//13,750 Agility experience
//15,000 coins
//Access to Isafdar and the overpass of Arandar
//Access to Port Tyras by using charter ships
//Ability to wield and use a dragon halberd (bought for 325,000 coins from the quartermaster in Tyras Camp)
//Ability to have elves assigned as a slayer task by Chaeldar, Sumona, Morvran, and Laniakea.
//2 Treasure Hunter keys (Ironman accounts will not receive these)