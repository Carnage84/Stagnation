package content.quest.member.creatureoffenkenstrain

import config.Items
import config.Vars
import core.api.addItemOrDrop
import core.api.rewardXP
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.quest.Quest
import core.game.node.entity.skill.Skills
import core.plugin.Initializable

/**
 * Creature Of Fenkenstrain quest.
 */
@Initializable
class CreatureOfFenkenstrain : Quest("Creature of Fenkenstrain", 41, 40, 2,Vars.VARP_QUEST_CREATURE_OF_FENKENSTRAIN_399,0,1,7) {
    companion object {
        const val CreatureOfFenkenstrain = "Creature of Fenkenstrain"
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
        player.packetDispatch.sendItemZoomOnInterface(Items.DESERT_DISGUISE_4611, 230, 277, 5)
        drawReward(player, "2 Quest Points", ln++)
        drawReward(player, "7,000 Agility XP", ln++)
        drawReward(player, "6,000 Thieving XP", ln++)
        drawReward(player, "2,000 Construction XP", ln++)
        rewardXP(player, Skills.AGILITY, 7000.0)
        rewardXP(player, Skills.THIEVING, 6000.0)
        rewardXP(player, Skills.CONSTRUCTION, 2000.0)
        addItemOrDrop(player, Items.TOME_OF_XP_1_9658)
    }

    override fun newInstance(`object`: Any?): Quest {
        return this
    }

}
//2 quest points
//7,000 Agility experience
//6,000 Thieving experience
//2,000 Construction experience
//A tome of experience, giving 2,000 experience to any skill you choose 3 times. (Must be over level 30)
//Access to Meiyerditch
//A shortcut key, can be put on the steel key ring after One Small Favour
//Access to the Burgh de Rott Ramble minigame, which is Temple Trekking in reverse route.
//Ability to unlock the Burgh de Rott teleport for a Games Necklace with 500 Companion levels from Temple Trekking.
//2 Treasure Hunter keys (Ironman accounts will not receive these)