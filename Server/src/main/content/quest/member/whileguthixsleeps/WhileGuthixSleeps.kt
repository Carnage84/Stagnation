package content.quest.member.whileguthixsleeps

import config.Items
import config.Vars
import core.api.addItemOrDrop
import core.api.setVarbit
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.quest.Quest
import core.plugin.Initializable

/**
 * While Guthix Sleeps quest.
 */
@Initializable
class WhileGuthixSleeps : Quest("While Guthix Sleeps", 161, 160, 5, Vars.VARBIT_QUEST_WHILE_GUTHIX_SLEEPS_5491, 0, 1, 901) {
    companion object {
        const val WhileGuthixSleeps = "While Guthix Sleeps"
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

        player.packetDispatch.sendItemZoomOnInterface(Items.DAGONHAI_ROBE_TOP_14497, 230, 277, 5)

        drawReward(player, "5 Quest Points", ln++)
        drawReward(player, "5,0000 Coins", ln++)
        addItemOrDrop(player, Items.LUMP_OF_CRYSTAL_743, 1)
        addItemOrDrop(player, Items.COINS_995, 5000)
        addItemOrDrop(player, Items.ELITE_BLACK_ARMOUR_SET_14527, 1)
        addItemOrDrop(player, Items.DAGONHAI_ROBES_SET_14525, 1)
        setVarbit(player, Vars.VARBIT_QUEST_WHILE_GUTHIX_SLEEPS_5491, 901, true)
    }

    override fun newInstance(`object`: Any?): Quest {
        return this
    }

}
//5 quest point
//A lump of dragon metal
//A set of Elite black armour.
//A set of Dagon'hai robes.
//The Balance Elemental can be faced in Dominion Tower.
//5,000 coins
//4 x 100,000 experience from Idria, in any skill that is at least 65.
//A new pet - Broav.
//Access to the Black Knights' Catacombs.
//Opportunity to loot Movario's base.
//The base has 100 of magic logs, coal, fire runes, and death runes; note that the logs and ores are in their un-noted form. At GE market prices, the items are worth approximately 139,500 coins.
//Ability to kill Tormented demons for dragon metal pieces, dragon claw and dragon limbs.
//A Strange key teeth and a Strange key loop
//2 Treasure Hunter keys (Ironman accounts will not receive these)