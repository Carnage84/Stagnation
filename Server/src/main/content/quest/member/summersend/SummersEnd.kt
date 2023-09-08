package content.quest.member.summersend

import config.Items
import config.Vars
import core.api.rewardXP
import core.api.setVarbit
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.quest.Quest
import core.game.node.entity.skill.Skills
import core.plugin.Initializable

/**
 * Summers End quest.
 */
@Initializable
class SummersEnd : Quest("Summer's End", 158, 157, 1, Vars.VARBIT_QUEST_SUMMERS_END_5331, 0, 1, 36) {
    companion object {
        const val SummersEnd = "Summer's End"
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

        player.packetDispatch.sendItemZoomOnInterface(Items.JENNICAS_RING_13566, 230, 277, 5)

        drawReward(player, "1 Quest Point, 1,500 Summoning XP, 5,000", ln++)
        drawReward(player, "Hunter Xp, 5,000 Mining XP, 5,000", ln++)
        drawReward(player, "Woodcutting XP, 10,000 Prayer XP, 15,000", ln++)
        drawReward(player, "Firemaking XP, ability to wield spirit shields,", ln++)
        drawReward(player, "and an upgraded version of Jennica's ring.", ln++)
        rewardXP(player, Skills.SUMMONING, 1500.0)
        rewardXP(player, Skills.HUNTER, 5000.0)
        rewardXP(player, Skills.MINING, 5000.0)
        rewardXP(player, Skills.WOODCUTTING, 5000.0)
        rewardXP(player, Skills.PRAYER, 10000.0)
        rewardXP(player, Skills.FIREMAKING, 15000.0)
        setVarbit(player, Vars.VARBIT_QUEST_SUMMERS_END_5331, 36, true)
    }

    override fun newInstance(`object`: Any?): Quest {
        return this
    }
}
//1 quest point
//1,500 Summoning experience
//5,000 Hunter experience
//5,000 Mining experience
//5,000 Woodcutting experience
//10,000 Prayer experience
//15,000 Firemaking experience
//A spirit shield and the ability to wield all Spirit shields
//An upgraded version of Jennica's ring which gives access to the new spirit portals
//Ability to fight the level 785 Corporeal Beast
//Ability to fight the level 42 Tormented Wraith
//Ability to bless spirit shields with holy elixirs by using either component at any Saradomin altar
//2 Treasure Hunter keys (Ironman accounts will not receive these)