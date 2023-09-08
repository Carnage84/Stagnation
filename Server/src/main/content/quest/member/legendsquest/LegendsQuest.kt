package content.quest.member.legendsquest

import config.Items
import config.Vars
import core.api.addItemOrDrop
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.quest.Quest
import core.plugin.Initializable

/**
 * Legend's Quest.
 */
@Initializable
class LegendsQuest : Quest("Legend's Quest", 82, 81, 4, Vars.VARP_QUEST_LEGENDS_QUEST_139, 0, 1, 75) {
    companion object {
        const val LegendsQuest = "Legend's Quest"
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
        player.packetDispatch.sendItemZoomOnInterface(Items.CAPE_OF_LEGENDS_1052, 230, 277, 5)
        drawReward(player, "4 Quest Points", ln++)
        addItemOrDrop(player, Items.BINDING_BOOK_730)
    }

    override fun newInstance(`object`: Any?): Quest {
        return this
    }

}
//4 quest points
//Access to the Legends' Guild (including a shop to buy the cape of legends)
//7,650 experience each in four skills of your choice amongst the following - Attack, Defence, Strength, Constitution, Prayer, Magic, Woodcutting, Crafting, Smithing, Herblore, Agility, and Thieving (You can choose the same skill multiple times if you wish)
//Binding book
//Bull roarer, used to summon Gujuo to bless more golden bowls
//Ability to wield a dragon square shield
//Ability to make holy water
//Access to the Kharazi Jungle and red gecko pets
//Chronicle, Book of legends pet (if not already unlocked)
//2 Treasure Hunter keys (Ironman accounts will not receive these)