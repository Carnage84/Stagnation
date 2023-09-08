package content.region.kandarin.ardougne.handlers

import config.Items
import config.NPCs
import config.Scenery
import core.api.*
import core.game.dialogue.DialogueFile
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.tools.END_DIALOGUE
import core.tools.START_DIALOGUE

/**
 * Handles use ferret on Charlie in Ardougne Zoo.
 */
class UseFerretOnCharlieListener : InteractionListener {
    override fun defineListeners() {
        onUseWith(IntType.NPC, FERRET, NPCs.CHARLIE_5138) { player, _, _ ->
            if(isQuestComplete(player, "Eagles' Peak")) {
                openDialogue(player, UseFerretOnCharlieDialogue())
            } else {
                sendMessage(player, "Nothing interesting happens.")
            }
            return@onUseWith true
        }
    }

    companion object { val FERRET = intArrayOf(Items.FERRET_10092) }

}

/**
 * Represents the dialogue after We use ferret on him.
 */
class UseFerretOnCharlieDialogue : DialogueFile(){
    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.CHARLIE_5138)
        when(stage){
            START_DIALOGUE -> playerl(FacialExpression.FRIENDLY, "Hey, I've got another ferret if you're interested?").also { stage++ }
            1 -> npcl(FacialExpression.NEUTRAL,"Er, oh! Well that's very kind of you, but we don't really need another ferret at the moment, I'm afraid. We're having enough trouble taming the one we've got.").also { stage = END_DIALOGUE }
        }
    }

}