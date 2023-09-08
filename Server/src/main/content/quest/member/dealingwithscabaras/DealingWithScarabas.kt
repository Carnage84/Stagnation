package content.quest.member.dealingwithscabaras

import config.Items
import config.Vars
import core.api.rewardXP
import core.api.setVarbit
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.quest.Quest
import core.game.node.entity.skill.Skills
import core.plugin.Initializable

/**
 * Dealing With Scarabas quest.
 */
@Initializable
class DealingWithScarabas : Quest("Dealing with Scabaras", 145, 144, 1,Vars.VARBIT_QUEST_DEALING_WITH_SCARABAS_4230,0,1,700) {
    companion object {
        const val DealingWithScarabas = "Dealing with Scabaras"
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

        player.packetDispatch.sendItemZoomOnInterface(Items.ENCHANTED_WATER_TIARA_11969, 230, 277, 5)

        drawReward(player, "1 Quest Point", ln++)
        drawReward(player, "7,000 Strength XP", ln++)
        drawReward(player, "7,000 Thieving XP", ln++)
        drawReward(player, "7,000 Agility XP", ln++)
        drawReward(player, "7,000 Prayer XP", ln++)
        rewardXP(player, Skills.STRENGTH, 7000.0)
        rewardXP(player, Skills.THIEVING, 7000.0)
        rewardXP(player, Skills.AGILITY, 7000.0)
        rewardXP(player, Skills.PRAYER, 7000.0)
        setVarbit(player, Vars.VARBIT_QUEST_DEALING_WITH_SCARABAS_4230, 700, true)
    }

    override fun newInstance(`object`: Any?): Quest {
        return this
    }

}
//1 quest point
//7,000 Strength experience
//7,000 Thieving experience
//7,000 Agility experience
//7,000 Prayer experience
//The ability to craft an Enchanted water tiara - protects you from the Desert heat effect
//To craft an enchanted tiara, use water runes on a normal water tiara (they will be stored inside the tiara); every time the player takes a 'drink', the total number of water runes stored in the tiara will decrease by three.
//Destroy the enchanted tiara to get the normal water tiara and remaining water runes back—they will drop on the ground.
//You can only have one enchanted water tiara at any given time.
//2 Treasure Hunter keys (Ironman accounts will not receive these)