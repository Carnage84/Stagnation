package content.quest.member.legacyofseergaze

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
 * Legacy of Seergaze quest.
 */
@Initializable
class LegacyofSeergaze : Quest("Legacy of Seergaze", 150, 149, 2,Vars.VARBIT_QUEST_LEGACY_OF_SEERGAZE_4569,0,1,500) {
    companion object {
        const val LegacyofSeergaze = "Legacy of Seergaze"
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
        player.packetDispatch.sendString("Legacy of Seergaze!", 277, 4)
        drawReward(player, "2k Quest Points, 3k Magic XP.", ln++)
        drawReward(player, "2k Agility. 4k Crafting XP.", ln++)
        drawReward(player, "1k Construction XP.", ln++)
        drawReward(player, "2k Mining XP.", ln++)
        drawReward(player, "Tome of XP. three chapters,", ln++)
        drawReward(player, "2.5k XP each. Ivandis Flail", ln++)
        drawReward(player, "Blood Talisman", ln++)
        rewardXP(player, Skills.MAGIC, 3000.0)
        rewardXP(player, Skills.AGILITY, 2000.0)
        rewardXP(player, Skills.CRAFTING, 4000.0)
        rewardXP(player, Skills.CONSTRUCTION, 1000.0)
        rewardXP(player, Skills.MINING, 2000.0)
        addItemOrDrop(player, Items.IVANDIS_FLAIL28_13119)
        addItemOrDrop(player, Items.TOME_OF_XP_3_9656)
        setVarbit(player, Vars.VARBIT_QUEST_LEGACY_OF_SEERGAZE_4569, 500, true)
    }

    override fun newInstance(`object`: Any?): Quest {
        return this
    }

}
//2 quest points
//3,000 Magic experience
//2,000 Agility experience
//4,000 Crafting experience
//2,000 Mining experience
//1,000 Construction experience
//2,000 Slayer experience
//Tome of xp 2nd ed, giving 2,500 xp each in any skill that's at least level 35, up to 3 times in total, or 1,250 experience points when used on an elite skill.
//Ivandis flail
//Blood talisman
//Ability to craft Blood runes at the Blood altar with 77 Runecrafting.
//Access to Meiyerditch Dungeon including Skeletal hands, Zombie hands, Bloodvelds and Mutated bloodvelds, as well as a shortcut between Canifis and Meiyerditch.
//Access to a furnace in Meiyerditch.
//Access to the columbarium beneath Paterdomus and the ability to cremate the Vyre corpse for rewards.
//Access to Vyrewatch event in Temple Trekking.
//2 Treasure Hunter keys (Ironman accounts will not receive these)