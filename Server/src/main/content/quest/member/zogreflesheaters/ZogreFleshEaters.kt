package content.quest.member.zogreflesheaters

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
 * Zogre Flesh Eaters quest.
 */
@Initializable
class ZogreFleshEaters : Quest("Zogre Flesh Eaters", 40, 39, 1, Vars.VARBIT_QUEST_ZORGE_FLESH_EATERS_487, 0, 1, 13) {
    companion object {
        const val ZogreFleshEaters = "Zogre Flesh Eaters"
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

        player.packetDispatch.sendItemZoomOnInterface(Items.ZOGRE_BONES_4812, 230, 277, 5)

        drawReward(player, "1 Quest Point", ln++)
        drawReward(player, "2,000 Fletching XP", ln++)
        drawReward(player, "2,000 Ranged XP", ln++)
        drawReward(player, "2,000 Herblore XP", ln++)
        rewardXP(player, Skills.FLETCHING, 2000.0)
        rewardXP(player, Skills.RANGE, 2000.0)
        rewardXP(player, Skills.HERBLORE, 2000.0)
        addItemOrDrop(player, Items.OURG_BONES_4835, 3)
        addItemOrDrop(player, Items.ZOGRE_BONES_4813, 2)
        setVarbit(player, Vars.VARBIT_QUEST_ZORGE_FLESH_EATERS_487, 13, true)
    }

    override fun newInstance(`object`: Any?): Quest {
        return this
    }

}
//1 Quest points Quest point
//3 ourg bones
//2 zogre bones
//Fletching 2,000 Fletching experience
//Ranged 2,000 Ranged experience
//Herblore 2,000 Herblore experience
//Ability to make Relicym's balm
//Ability to fletch comp ogre bows and brutal arrows
//Ability to wear inoculation bracelets
//Give a 2-, 3-, or 4-dose Relicym's balm to Uglug to gain access to ~ Uglug's stuffsies ~
//Bonus: Take the black prism to either Zavistic Rarve in the Wizards' Guild for 2,000 coins or to Yanni Salika in Shilo Village for 5,000 coins.