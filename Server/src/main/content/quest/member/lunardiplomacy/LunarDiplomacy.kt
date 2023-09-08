package content.quest.member.lunardiplomacy

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
 * Lunar Diplomacy quest.
 */
@Initializable
class LunarDiplomacy : Quest("Lunar Diplomacy", 85, 84, 2, Vars.VARBIT_QUEST_LUNAR_DIPLOMACY_2448, 0, 1, 190) {
    companion object {
        const val LunarDiplomacy = "Lunar Diplomacy"
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
        player.packetDispatch.sendItemZoomOnInterface(Items.LUNAR_STAFF_9084, 230, 277, 5)
        drawReward(player, "2 Quest Points", ln++)
        drawReward(player, "5,000 Magic XP", ln++)
        drawReward(player, "5,000 Runecrafting XP", ln++)
        rewardXP(player, Skills.MAGIC, 5000.0)
        rewardXP(player, Skills.RUNECRAFTING, 5000.0)
        addItemOrDrop(player, Items.ASTRAL_RUNE_9075, 50)
        addItemOrDrop(player, Items.LUNAR_HELM_9096)
        addItemOrDrop(player, Items.LUNAR_AMULET_9102)
        addItemOrDrop(player, Items.LUNAR_GLOVES_9099)
        addItemOrDrop(player, Items.LUNAR_TORSO_9097)
        addItemOrDrop(player, Items.LUNAR_LEGS_9098)
        addItemOrDrop(player, Items.LUNAR_BOOTS_9100)
        addItemOrDrop(player, Items.LUNAR_RING_9104)
        setVarbit(player, Vars.VARBIT_QUEST_LUNAR_DIPLOMACY_2448, 190, true)
    }

    override fun newInstance(`object`: Any?): Quest {
        return this
    }
}
//2 quest points
//5,000 Magic experience
//5,000 Runecrafting experience
//Full access to Lunar Isle
//Use of the Astral altar
//Access to Livid Farm
//Ability to buy astral runes from Baba Yaga
//Ability to use Lunar Spells
//A set of Lunar equipment (obtained during quest)
//50 astral runes
//Ability to have suqahs assigned as a Slayer task
//Ability to unlock the Ourania Teleport spell from the Lunar Spellbook, by talking to Baba Yaga with Lunar spells active
//An extra 10,338 Magic experience when Swept Away quest is completed and the broomstick is brought to Baba Yaga (with level 73 Magic)
//The ability to Home Teleport to the Lunar Isle lodestone
//The seal of passage can now be used to teleport to Lunar Isle once per day
//2 Treasure Hunter keys (Ironman accounts will not receive these)