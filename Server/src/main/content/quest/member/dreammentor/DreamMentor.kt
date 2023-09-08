package content.quest.member.dreammentor

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
 * Dream Mentor quest.
 */
@Initializable
class DreamMentor : Quest("Dream Mentor", 139, 138, 2,Vars.VARBIT_QUEST_DREAM_MENTOR_3618,0,1,28) {

    companion object {
        const val DreamMentor = "Dream Mentor"
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

        player.packetDispatch.sendItemZoomOnInterface(Items.DREAMY_LAMP_11157, 230, 277, 5)
        drawReward(player, "2 Quest Points", ln++)
        drawReward(player, "15,000 Hitpoints XP", ln++)
        drawReward(player, "10,000 Magic XP", ln++)
        rewardXP(player, Skills.HITPOINTS, 15000.0)
        rewardXP(player, Skills.MAGIC, 10000.0)
        addItemOrDrop(player, Items.DREAMY_LAMP_11157)
        setVarbit(player, Vars.VARBIT_QUEST_DREAM_MENTOR_3618, 28, true)
    }

    override fun newInstance(`object`: Any?): Quest {
        return this
    }

}
//2 quest points
//15,000 Constitution experience
//10,000 Magic experience
//A dreamy lamp, which will give you 15,000 experience in a combat skill from the following - Strength, Ranged, Magic, Constitution, or Defence
//9 new spells added to the Lunar spellbook:
//Monster Examine
//Humidify
//Hunter Kit
//Stat Spy
//Superglass Make
//Dream
//Plank Make
//Spin Flax
//Spellbook Swap
//Access to the 'Random' option on NPC Contact
//2 Treasure Hunter keys (Ironman accounts will not receive these)