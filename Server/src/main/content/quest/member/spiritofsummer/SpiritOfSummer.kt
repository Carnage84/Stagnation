package content.quest.member.spiritofsummer

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
 * Spirit Of Summer quest.
 */
@Initializable
class SpiritOfSummer : Quest("Spirit of Summer", 155, 154, 1, Vars.VARBIT_QUEST_SPIRIT_OF_SUMMER_5032, 0, 1, 81) {
    companion object {
        const val SpiritOfSummer = "Spirit of Summer"
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

        drawReward(player, "1 Quest Point", ln++)
        drawReward(player, "7,500 Construction XP", ln++)
        drawReward(player, "5,000 Prayer XP", ln++)
        drawReward(player, "2,000 Farming XP", ln++)
        drawReward(player, "1,000 Summoning XP", ln++)
        rewardXP(player, Skills.CONSTRUCTION, 7500.0)
        rewardXP(player, Skills.PRAYER, 5000.0)
        rewardXP(player, Skills.FARMING, 2000.0)
        rewardXP(player, Skills.SUMMONING, 1000.0)
        addItemOrDrop(player, Items.JENNICAS_RING_13566)
        setVarbit(player, Vars.VARBIT_QUEST_SPIRIT_OF_SUMMER_5032, 81, true)
    }

    override fun newInstance(`object`: Any?): Quest {
        return this
    }
}
//1 quest point
//7,500 Construction experience
//5,000 Prayer experience
//2,000 Farming experience
//1,000 Summoning experience
//Jennica's ring
//A new Spirit Realm to explore
//Ability to fight the level 42 Ghostly warriors at the Spirit realm magic axe hut.
//Access to the Cursed Magic Tree
//Access to a Goth leprechaun in level 22 wilderness, as well as the flower patch he guards.
//2 Treasure Hunter keys (Ironman accounts will not receive these)