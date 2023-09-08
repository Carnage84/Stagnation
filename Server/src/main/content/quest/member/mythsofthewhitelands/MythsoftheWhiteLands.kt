package content.quest.member.mythsofthewhitelands

import config.Items
import config.Vars
import core.api.addItemOrDrop
import core.api.setVarbit
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.quest.Quest
import core.plugin.Initializable

/**
 * Myths of the White Lands quest.
 */
@Initializable
class MythsoftheWhiteLands : Quest("Myths of the White Lands", 163, 162, 2,Vars.VARBIT_QUEST_MYTHS_OF_THE_WHITE_LANDS_5733,0,1,60) {
    companion object {
        const val MythsoftheWhiteLands = "Myths of the White Lands"
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

        player.packetDispatch.sendItemZoomOnInterface(Items.ANTIQUE_LAMP_11187, 230, 277, 5)

        drawReward(player, "2 Quest Points", ln++)
        drawReward(player, "10,000 Herblore XP", ln++)
        drawReward(player, "5,000 Coins", ln++)
        addItemOrDrop(player, Items.COINS_995, 5000)
        addItemOrDrop(player, Items.ANTIQUE_LAMP_11187, 1)
        setVarbit(player, Vars.VARBIT_QUEST_MYTHS_OF_THE_WHITE_LANDS_5733, 60, true)
    }

    override fun newInstance(`object`: Any?): Quest {
        return this
    }

}
//2 quest points (or 1; depending on whether you finished Sheep Shearer/Witch's Potion before their demotion to miniquest)
//Antique lamp (500 experience)
//5,000 coins (if you talk to Jack after logging out or speaking to Explorer Jack later on)
//Access to Land of Snow
//2 Treasure Hunter keys (Ironman accounts will not receive these)
//Additional rewards/activities
//2,000 Crafting experience lamp (requiring level 30 Crafting: Second cavern (see above))
//10,000 Agility experience lamp (requiring level 55 Agility: Fourth cavern (see above))
//20,000 Woodcutting experience lamp (requiring level 80 Woodcutting: Sixth cavern (see above))