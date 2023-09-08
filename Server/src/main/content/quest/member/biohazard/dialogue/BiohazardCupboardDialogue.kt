package content.quest.member.biohazard.dialogue

import config.Items
import core.api.getQuestStage
import core.api.sendItemDialogue
import core.game.dialogue.DialogueFile
import core.game.dialogue.FacialExpression
import core.game.node.entity.player.Player
import core.tools.END_DIALOGUE

/**
 * Represents the Biohazard Cupboard dialogue file for Biohazard quest.
 */
class BiohazardCupboardDialogue() : DialogueFile() {
    override fun handle(componentID: Int, buttonID: Int) {
        when(getQuestStage(player!!, "Biohazard")) {
            in 10 until 20 -> handleCupboardDialogue(player, buttonID)
        }
    }

    private fun handleCupboardDialogue(player: Player?, buttonID: Int) {
        when(stage) {
            0 -> sendItemDialogue(player!!, Items.BIRD_FEED_422, "The cupboard is full of birdfeed.").also { stage++ }
            1 -> playerl(FacialExpression.THINKING, "Mmm, birdfeed! Now what could I do with that?").also { stage = END_DIALOGUE }
        }
    }
}