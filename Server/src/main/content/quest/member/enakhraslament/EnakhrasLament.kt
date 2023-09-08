package content.quest.member.enakhraslament

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
 * Enakhra's Lament quest.
 */
@Initializable
class EnakhrasLament : Quest("Enakhra's Lament", 54, 53, 2,Vars.VARBIT_QUEST_ENAKHRAS_LAMENT_1560,0,1,70) {
    companion object {
        const val EnakhrasLament = "Enakhra's Lament"
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

        player.packetDispatch.sendItemZoomOnInterface(Items.CAMULET_6707, 230, 277, 5)

        drawReward(player, "2 Quest Points", ln++)
        drawReward(player, "7,000 Crafting XP", ln++)
        drawReward(player, "7,000 Mining XP", ln++)
        drawReward(player, "7,000 Firemaking XP", ln++)
        drawReward(player, "7,000 Magic XP", ln++)
        rewardXP(player, Skills.CRAFTING, 7000.0)
        rewardXP(player, Skills.MINING, 7000.0)
        rewardXP(player, Skills.FIREMAKING, 7000.0)
        rewardXP(player, Skills.MAGIC, 7000.0)
        addItemOrDrop(player, Items.CAMULET_6707)
        addItemOrDrop(player, Items.CAMEL_MASK_7003)
        setVarbit(player, Vars.VARBIT_QUEST_ENAKHRAS_LAMENT_1560, 70, true)
    }

    override fun newInstance(`object`: Any?): Quest {
        return this
    }

}
//2 quest points
//7,000 Crafting experience
//7,000 Mining experience
//7,000 Firemaking experience
//7,000 Magic experience
//A camulet, which allows you to talk to camels and teleport to the temple four times; it can be recharged by using camel dung on it.
//Camel mask (use a piece of soft clay on the pedestal with the globes on it to make one)