package content.region.misthalin.varrock.dialogue

import content.global.handlers.iface.ge.ExchangeItemSets
import content.global.handlers.iface.ge.StockMarket
import core.game.dialogue.DialogueFile
import core.game.dialogue.FacialExpression
import core.game.ge.GrandExchangeRecords.Companion.getInstance
import core.plugin.Initializable

/**
 * Handles the GrandExchange Clerks dialogues.
 */
@Initializable
class GrandExchangeClerkDialogue : DialogueFile() {
    override fun handle(componentID: Int, buttonID: Int) {
        val npc = npc!!.originalId
        when (stage) {
            0 -> {
                interpreter!!.sendDialogues(player, FacialExpression.HALF_GUILTY, "Hi there.")
                stage = 1
            }

            1 -> {
                npc("Good day to you, sir, How can I help?")
                stage = 2
            }

            2 -> {
                interpreter!!.sendOptions(
                    "Select an Option",
                    "I want to access the Grand Exchange, please.",
                    "I want to collect my items.",
                    "Can I see a history of my offers?",
                    "Can you help me with item sets?",
                    "I'm fine, actually."
                )
                stage = 3
            }

            3 -> when (buttonID) {
                1 -> {
                    interpreter!!.sendDialogues(
                        player,
                        FacialExpression.HALF_GUILTY,
                        "I want to access the Grand Exchange, please."
                    )
                    stage = 10
                }

                2 -> {
                    interpreter!!.sendDialogues(player, FacialExpression.HALF_GUILTY, "I want to collect my items.")
                    stage = 20
                }

                3 -> {
                    interpreter!!.sendDialogues(player, FacialExpression.HALF_GUILTY, "Can I see history of my offers?")
                    stage = 30
                }

                4 -> {
                    interpreter!!.sendDialogues(player, FacialExpression.HALF_GUILTY, "Can you help me with item sets?")
                    stage = 40
                }

                5 -> {
                    interpreter!!.sendDialogues(player, FacialExpression.HALF_GUILTY, "I'm fine actually.")
                    stage = 50
                }
            }

            10 -> {
                interpreter!!.sendDialogues(npc, FacialExpression.HALF_GUILTY,"Only too happy to help you, sir.")
                stage = 11
            }

            11 -> {
                end()
                StockMarket.openFor(player!!)
            }

            20 -> {
                interpreter!!.sendDialogues(npc, FacialExpression.HALF_GUILTY,"As you wish, sir.")
                stage = 21
            }

            21 -> {
                end()
                getInstance(player).openCollectionBox()
            }

            30 -> {
                interpreter!!.sendDialogues(npc, FacialExpression.HALF_GUILTY,"If that is your wish.")
                stage = 31
            }

            31 -> {
                end()
                getInstance(player).openHistoryLog(player!!)
            }

            40 -> {
                interpreter!!.sendDialogues(npc, FacialExpression.HALF_GUILTY,"It would be my pleasure, sir.")
                stage = 41
            }

            41 -> {
                end()
                ExchangeItemSets.openFor(player!!)
            }

            50 -> {
                interpreter!!.sendDialogues(npc, FacialExpression.HALF_GUILTY,"If you say so, sir.")
                stage = 51
            }

            51 -> end()
        }
    }


}
