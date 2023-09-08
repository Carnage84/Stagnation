package content.quest.member.ratcatchers

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
 * Ratcatchers quest.
 */
@Initializable
class RatCatchers : Quest("Ratcatchers", 101, 100, 2,Vars.VARBIT_QUEST_RATCATCHERS_1404,0,1,127) {
    companion object {
        const val RatCatchers = "Ratcatchers"
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

        player.packetDispatch.sendItemZoomOnInterface(Items.RAT_POLE_6773, 230, 277, 5)

        drawReward(player, "2 Quest Points", ln++)
        drawReward(player, "4,500 Thieving XP", ln++)
        rewardXP(player, Skills.THIEVING, 4500.0)
        addItemOrDrop(player, Items.RAT_POLE_6773)
        setVarbit(player, Vars.VARBIT_QUEST_RATCATCHERS_1404, 127, true)
    }

    override fun newInstance(`object`: Any?): Quest {
        return this
    }

}
//2 quest points
//4,500 Thieving experience
//Rat pole
//Able to train Overgrown Cats into Wily and Lazy cats
//The ability to name a lazy or wily cat (up to 6 letters long)
//2 Treasure Hunter keys (Ironman accounts will not receive these)