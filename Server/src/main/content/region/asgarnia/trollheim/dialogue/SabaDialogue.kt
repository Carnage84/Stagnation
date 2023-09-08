package content.region.asgarnia.trollheim.dialogue

import config.NPCs
import content.quest.member.deathplateau.dialogue.SabaDPDialogue
import core.api.getQuestStage
import core.api.openDialogue
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the Saba dialogue plugin.
 */
@Initializable
class SabaDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        if (getQuestStage(player!!, "Death Plateau") >= 19) {
            openDialogue(player!!, SabaDPDialogue(), npc)
        } else {
            playerl(FacialExpression.HALF_GUILTY, "Hi!").also { stage = 1 }
        }
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            1 -> npcl(FacialExpression.ANNOYED, "Why won't people leave me alone?!").also { stage = END_DIALOGUE }
        }
        return true
    }

    override fun newInstance(player: Player?): DialoguePlugin {
        return SabaDialogue(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.SABA_1070)
    }
}