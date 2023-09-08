package content.quest.member.ragandbone

import config.Items
import config.Vars
import core.api.rewardXP
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.quest.Quest
import core.game.node.entity.skill.Skills
import core.plugin.Initializable

/**
 * Rag And Bone quest.
 */
@Initializable
class RagAndBone : Quest("Rag and Bone Man I", 100, 99, 2, Vars.VARP_QUEST_RAG_AND_BONE_MAN_714, 0, 1, 4) {
    companion object {
        const val RagAndBone = "Rag and Bone Man I"
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
        player.packetDispatch.sendItemZoomOnInterface(Items.BONES_3187, 230, 277, 5)
        drawReward(player, "2 Quest Points", ln++)
        drawReward(player, "500 Cooking XP", ln++)
        drawReward(player, "500 Prayer XP", ln++)
        rewardXP(player, Skills.COOKING, 500.0)
        rewardXP(player, Skills.PRAYER, 500.0)
    }

    override fun newInstance(`object`: Any?): Quest {
        return this
    }
}
//2 quest points
//500 Cooking experience
//500 Prayer experience
//2 Treasure Hunter keys (Ironman accounts will not receive these)