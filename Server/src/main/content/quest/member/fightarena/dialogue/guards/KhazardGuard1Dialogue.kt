package content.quest.member.fightarena.dialogue.guards

import config.Items
import config.NPCs
import core.api.allInEquipment
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the Khazard Guard 1 dialogue plugin.
 */
@Initializable
class KhazardGuard1Dialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        if (player.questRepository.getStage("Fight Arena") == 100) {
            npcl(FacialExpression.FRIENDLY, "It's you! I don't believe it. You beat the General! You are a traitor to the uniform!").also { stage = END_DIALOGUE }
        } else if (allInEquipment(player, Items.KHAZARD_HELMET_74, Items.KHAZARD_ARMOUR_75)) {
            playerl(FacialExpression.FRIENDLY, "Hello.").also { stage = 0 }
        } else {
            playerl(FacialExpression.FRIENDLY, "Hi.").also { stage = 2 }
        }
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            0 -> npcl(FacialExpression.ASKING, "Can I help you stranger?").also { stage++ }
            1 -> npcl(FacialExpression.FRIENDLY, "Oh, you're a guard as well. That's ok then. We don't like strangers around here").also { stage = END_DIALOGUE }
        }
        when (stage) {
            2 -> npcl(FacialExpression.ANNOYED, "I don't know you stranger. Get off our land.").also { stage = END_DIALOGUE
            }
        }
        return true
    }

    override fun newInstance(player: Player?): DialoguePlugin {
        return KhazardGuard1Dialogue(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.KHAZARD_GUARD_253)
    }
}
