package content.region.asgarnia.burthorpe.dialogue

import config.Animations
import config.Items
import config.NPCs
import content.quest.member.deathplateau.dialogue.HaroldDPDialogue
import core.api.*
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.player.Player
import core.game.world.update.flag.context.Animation
import core.plugin.Initializable
import core.tools.END_DIALOGUE
import core.tools.START_DIALOGUE

/**
 * Represents the Harold dialogue plugin.
 */
@Initializable
class HaroldDialogue(player: Player? = null) : DialoguePlugin(player) {
    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        if (player.questRepository.hasStarted("Death Plateau")) {
            // Call the dialogue file for Harold from the deathplateau quest folder.
            openDialogue(player!!, HaroldDPDialogue(), npc)
        }
        // Fallback to default.
        when (stage) {
            START_DIALOGUE -> player(FacialExpression.FRIENDLY, "Hello there.").also { stage++ }
            1 -> npc(FacialExpression.FRIENDLY, "Hi.").also { stage++ }
            2 -> player(FacialExpression.FRIENDLY, "Can I buy you a drink?").also { stage++ }
            3 -> npc(FacialExpression.HAPPY, "Now you're talking! An Asgarnian Ale, please!").also { stage++ }
            4 -> {
                if (removeItem(player!!, Items.ASGARNIAN_ALE_1905)) {
                    sendMessage(player!!, "You give Harold an Asgarnian Ale.")
                    sendItemDialogue(player!!, Items.ASGARNIAN_ALE_1905, "You give Harold an Asgarnian Ale.").also { stage++ }
                } else {
                    player(FacialExpression.FRIENDLY, "I'll go and get you one.").also { stage = END_DIALOGUE }
                }
            }
            5 -> {
                end()
                animate(npc!!, Animation(Animations.HUMAN_EATTING_829), true)
                runTask(npc!!, 3) {
                    npc(FacialExpression.FRIENDLY, "*burp*").also { stage = END_DIALOGUE }
                }
            }
        }
        return true
    }

    override fun newInstance(player: Player?): DialoguePlugin {
        return HaroldDialogue(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.HAROLD_1078)
    }
}