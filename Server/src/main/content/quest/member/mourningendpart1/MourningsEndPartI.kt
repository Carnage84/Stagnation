package content.quest.member.mourningendpart1

import config.Items
import config.Vars
import core.api.addItemOrDrop
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.quest.Quest
import core.plugin.Initializable

/**
 * Mourning's End Part I quest.
 */
@Initializable
class MourningsEndPartI : Quest("Mourning's End Part I", 91, 90, 2, Vars.VARP_QUEST_MOURNINGS_ENDS_PART_I_517, 0, 1, 8) {
    companion object {
        const val MourningsEndPartI = "Mourning's End Part I"
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

        player.packetDispatch.sendItemZoomOnInterface(Items.MINECART_TICKET_5020, 230, 277, 5)

        drawReward(player, "2 Quest Points", ln++)
        //25,000 Constitution experience lamp
        //25,000 Thieving experience lamp
        addItemOrDrop(player, Items.GAS_MASK_1506, 1)
        addItemOrDrop(player, Items.MOURNER_TOP_10621, 1)
        addItemOrDrop(player, Items.MOURNER_CLOAK_6070, 1)
        addItemOrDrop(player, Items.MOURNER_TROUSERS_6066, 1)
        addItemOrDrop(player, Items.MOURNER_BOOTS_6069, 1)
        addItemOrDrop(player, Items.MOURNER_GLOVES_6068, 1)
        addItemOrDrop(player, Items.CRYSTAL_SEED_4207, 1)
    }

    override fun newInstance(`object`: Any?): Quest {
        return this
    }
}
//2 quest points
//25,000 Constitution experience lamp
//25,000 Thieving experience lamp
//Access to the Death Guard headquarters
//Access to Lletya
//Crystal teleport seed
//Full mourner gear
//2 Treasure Hunter keys (Ironman accounts will not receive these)