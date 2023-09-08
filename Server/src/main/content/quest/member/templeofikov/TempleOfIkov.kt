package content.quest.member.templeofikov

import config.Items
import config.Vars
import core.api.addItemOrDrop
import core.api.getStatLevel
import core.api.rewardXP
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.quest.Quest
import core.game.node.entity.skill.Skills
import core.plugin.Initializable

/**
 * Temple Of Ikov quest.
 */
@Initializable
class TempleOfIkov : Quest("Temple Of Ikov", 121, 120, 1, Vars.VARP_QUEST_TEMPLE_OF_IKOV_26, 0, 1, 90) {
    companion object {
        const val TempleOfIkov = "Temple Of Ikov"
    }

    override fun drawJournal(player: Player, stage: Int) {
        super.drawJournal(player, stage)

        var ln = 11

        if (stage == 0) {
            line(player, "To start this quest I will need:", ln++, false)
            line(player, "Level 42 !!Thieving??", ln++, getStatLevel(player, Skills.THIEVING) >= 42)
            line(player, "Level 40 !!Ranged??", ln++, getStatLevel(player, Skills.RANGE) >= 40)
            line(player, "Ability to defeat a level 84 enemy with Ranged.", ln++, false)
        } else {
            line(player, "quest started", ln++, false)
        }

        if (stage == 100) {
            ln++
            line(player, "!!QUEST COMPLETE!??", ln++, true)
        }
    }

    override fun finish(player: Player?) {
        super.finish(player)
        player ?: return
        var ln = 10

        player.packetDispatch.sendItemZoomOnInterface(Items.ARMADYL_PENDANT_87, 230, 277, 5)

        drawReward(player, "1 Quest Point", ln++)
        drawReward(player, "10,500 Ranged XP", ln++)
        drawReward(player, "8,000 Fletching XP", ln++)
        rewardXP(player, Skills.RANGE, 10500.0)
        rewardXP(player, Skills.FLETCHING, 8000.0)
        addItemOrDrop(player, Items.BOOTS_OF_LIGHTNESS_88)
        addItemOrDrop(player, Items.PENDANT_OF_LUCIEN_86)
        //addItemOrDrop(player, Items.ARMADYL_PENDANT_87)
        addItemOrDrop(player, Items.SHINY_KEY_85)
        
    }

    override fun newInstance(`object`: Any?): Quest {
        return this
    }
}
//1 quest point
//10,500 Ranged experience
//8,000 Fletching experience
//Boots of lightness
//Pendant of Lucien, giving you access to the front door of the Temple of Ikov (dungeon)
//Armadyl pendant (If you sided with Armadyl, but you can get one by killing the guardians if you sided with Lucien)
//Shiny key for entering through the back door of the Temple of Ikov, from near McGrubor's Wood (can be added to the steel key ring after One Small Favour)
//Access to the Symbol of Armadyl gravestone (also requires The Giant Dwarf)
//5 Kudos in the Varrock Museum if you talk to Historian Minas
//2 Treasure Hunter keys (Ironman accounts will not receive these)