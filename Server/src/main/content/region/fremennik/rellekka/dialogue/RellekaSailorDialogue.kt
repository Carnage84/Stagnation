package content.region.fremennik.rellekka.dialogue

import config.NPCs
import content.region.fremennik.rellekka.handlers.RellekkaUtils.sail
import content.region.fremennik.rellekka.handlers.TravelDestination
import core.api.requireQuest
import core.api.sendMessage
import core.game.dialogue.DialogueFile
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC

/**
 * Represents Relleka Sailors dialogue for travel ship.
 * @see content.quest.member.thefremenniktrials.dialogue.FremennikSailorFTDialogue
 */
class RellekaSailorDialogue(private val travelOption: Boolean = false) : DialogueFile() {
    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.SAILOR_1385)
        if (!travelOption) {
            when (stage) {
                0 -> playerl(FacialExpression.ASKING, "Hello. Can I get a ride on your ship?").also { stage++ }
                1 -> npcl(FacialExpression.HAPPY, "Hello brother ${player!!.username}. If you are ready to jump aboard, we're all ready to set sail with the tide!").also { stage++ }
                2 -> options("Let's go!", "Actually no.").also { stage++ }
                3 -> when (buttonID) {
                    1 -> if (!requireQuest(player!!, "Fremennik Trials", "")) {
                        end()
                    } else {
                        playerl(FacialExpression.HAPPY, "Let's go!").also { stage++ }
                    }
                    2 -> end()
                }
                4 -> {
                    end()
                    sail(player!!, TravelDestination.RELLEKA_TO_MISCELLANIA)
                    sendMessage(player!!, "You board the longship...")
                }
            }
        } else {
            when (stage) {
                0 -> if (!requireQuest(player!!, "Fremennik Trials", "")) {
                    end()
                } else {
                    options("Let's go!", "Actually no.").also { stage++ }
                }
                1 -> when (buttonID) {
                    1 -> {
                        end()
                        sail(player!!, TravelDestination.RELLEKA_TO_MISCELLANIA)
                        sendMessage(player!!, "You board the longship...")
                    }

                    2 -> end()
                }
            }
        }
    }
}