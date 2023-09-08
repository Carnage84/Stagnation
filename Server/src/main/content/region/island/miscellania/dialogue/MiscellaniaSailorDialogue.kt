package content.region.island.miscellania.dialogue

import config.NPCs
import content.region.fremennik.rellekka.handlers.RellekkaUtils.sail
import content.region.fremennik.rellekka.handlers.TravelDestination
import core.api.sendMessage
import core.game.dialogue.DialogueFile
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC

/**
 * Represents Miscellania Sailors dialogue used for travel.
 */
class MiscellaniaSailorDialogue(private val travelOption: Boolean = false) : DialogueFile() {

    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.SAILOR_1304)
        if (!travelOption) {
            when (stage) {
                0 -> playerl(FacialExpression.ASKING, "Hello. Can I get a ride on your ship?").also { stage++ }
                1 -> npcl(FacialExpression.HAPPY, "Hello brother ${player!!.username}. If you are ready to jump aboard, we're all ready to set sail with the tide!").also { stage++ }
                2 -> options("Let's go!", "Actually no.").also { stage++ }
                3 -> when (buttonID) {
                    1 -> playerl(FacialExpression.HAPPY, "Let's go!").also { stage++ }
                    2 -> end()
                }
                4 -> {
                    end()
                    sail(player!!, TravelDestination.MISCELLANIA_TO_RELLEKKA)
                    sendMessage(player!!, "You board the longship...")
                }
            }
        } else {
            when (stage) {
                0 -> options("Let's go!", "Actually no.").also { stage++ }
                1 -> when (buttonID) {
                    1 -> {
                        end()
                        sail(player!!, TravelDestination.MISCELLANIA_TO_RELLEKKA)
                        sendMessage(player!!, "You board the longship...")
                    }

                    2 -> end()
                }
            }
        }
    }
}