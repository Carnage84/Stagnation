package content.quest.member.seaslug

import config.Items
import config.Vars
import core.api.addItemOrDrop
import core.api.rewardXP
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.quest.Quest
import core.game.node.entity.skill.Skills
import core.plugin.Initializable

/**
 * Sea slug quest.
 */
@Initializable
class SeaSlug : Quest("Sea Slug", 109, 108, 1, Vars.VARP_QUEST_SEA_SLUG_159, 0, 1, 13) {
    companion object {
        const val SeaSlug = "Sea Slug"
    }

    override fun drawJournal(player: Player?, stage: Int) {
        super.drawJournal(player, stage)
        var line = 11
        player ?: return

        if (stage == 0) {
            line++
            line(player, "I can start this quest by speaking to !!Caroline?? north of !!Witchaven??", line++, false)
            line(player, "east in !!Ardougne??", line++, false)
            line++
        }


        if (stage == 100) {
            line++
            line(player, "I rescued Zanik.", line++, true)
            line++
            line(player, "<col=FF0000>QUEST COMPLETE!", line, false)
        }
    }

    override fun finish(player: Player?) {
        super.finish(player)
        player ?: return
        var ln = 10

        player.packetDispatch.sendItemZoomOnInterface(Items.OYSTER_PEARLS_413, 230, 277, 5)
        drawReward(player, "1 Quest Point", ln++)
        drawReward(player, "7175 Fishing XP", ln++)
        rewardXP(player, Skills.FISHING, 7175.0)
        addItemOrDrop(player, Items.OYSTER_PEARLS_413)
    }

    override fun newInstance(`object`: Any?): Quest {
        return this
    }
}
//1 quest point
//7,175 Fishing experience
//Access to the Fishing Platform
//Oyster pearls
//2 Treasure Hunter keys (Ironman accounts will not receive these)