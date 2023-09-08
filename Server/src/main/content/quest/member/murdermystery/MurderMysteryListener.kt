package content.quest.member.murdermystery

import config.Items
import config.NPCs
import config.Scenery
import content.quest.member.murdermystery.dialogue.PoisonSalesmanMMDialogue
import core.api.addItem
import core.api.openDialogue
import core.api.sendDialogue
import core.game.dialogue.DialogueFile
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.world.map.zone.ZoneBorders

/**
 * Handles Murder Mystery quest interactions.
 */
class MurderMysteryListener : InteractionListener {
    companion object {

        val MANSION_ROAD = ZoneBorders(2739, 3557, 2744, 3570)
        val STURDY_GATE = intArrayOf(Scenery.STURDY_WOODEN_GATE_2664, Scenery.STURDY_WOODEN_GATE_2665)

        const val POISON_SALESMAN = NPCs.POISON_SALESMAN_820


        const val SILVER_NECKLACE_0 = Items.SILVER_NECKLACE_1796
        const val SILVER_NECKLACE_1 = Items.SILVER_NECKLACE_1797

        const val SILVER_CUP_0 = Items.SILVER_CUP_1798
        const val SILVER_CUP_1 = Items.SILVER_CUP_1799

        const val SILVER_BOTTLE_0 = Items.SILVER_BOTTLE_1800
        const val SILVER_BOTTLE_1 = Items.SILVER_BOTTLE_1801

        const val SILVER_BOOK_0 = Items.SILVER_BOOK_1802
        const val SILVER_BOOK_1 = Items.SILVER_BOOK_1803

        const val SILVER_NEEDLE_0 = Items.SILVER_NEEDLE_1804
        const val SILVER_NEEDLE_1 = Items.SILVER_NEEDLE_1805

        const val SILVER_POT_0 = Items.SILVER_POT_1806
        const val SILVER_POT_1 = Items.SILVER_POT_1807

        const val CRIMINAL_THREAD_0 = Items.CRIMINALS_THREAD_1808 // R
        const val CRIMINAL_THREAD_1 = Items.CRIMINALS_THREAD_1809 // G
        const val CRIMINAL_THREAD_2 = Items.CRIMINALS_THREAD_1810 // B

        const val FLYPAPER = Items.FLYPAPER_1811

        const val PUNGEON_POT = Items.PUNGENT_POT_1812

        const val CRIMINAL_DAGGER_0 = Items.CRIMINALS_DAGGER_1813
        const val CRIMINAL_DAGGER_1 = Items.CRIMINALS_DAGGER_1814

        const val KILLERS_PRINT = Items.KILLERS_PRINT_1815
        const val ANNAS_PRINT = Items.ANNAS_PRINT_1816
        const val BOBS_PRINT = Items.BOBS_PRINT_1817
        const val CAROLS_PRINT = Items.CAROLS_PRINT_1818
        const val DAVIDS_PRINT = Items.DAVIDS_PRINT_1819
        const val ELIZABETHS_PRINT = Items.ELIZABETHS_PRINT_1820
        const val FRANKS_PRINT = Items.FRANKS_PRINT_1821
        const val UNKNOWN_PRINT = Items.UNKNOWN_PRINT_1822

        const val EMPTY_POT = Items.EMPTY_POT_1931
        const val POT_WITH_FLOUR = Items.POT_OF_FLOUR_1933

        const val ANNA_BARREL = Scenery.ANNA_S_BARREL_2656
        const val BOB_BARREL = Scenery.BOB_S_BARREL_2657
        const val CAROL_BARREL = Scenery.CAROL_S_BARREL_2658
        const val DAVID_BARREL = Scenery.DAVID_S_BARREL_2659
        const val ELIZABETH_BARREL = Scenery.ELIZABETH_S_BARREL_2660
        const val FRANK_BARREL = Scenery.FRANK_S_BARREL_2661
        const val FAMILY_FOUNTAIN = Scenery.SINCLAIR_FAMILY_FOUNTAIN_2654
        const val FAMILY_CREST = Scenery.SINCLAIR_FAMILY_CREST_2655
        const val PAPER_IN_SACKS = Scenery.SACKS_2663
        const val MANSION_DRAIN = Scenery.SINCLAIR_MANSION_DRAIN_2843
        const val SPIDER_NEST = Scenery.SPIDERS__NEST_26109
        const val SMASHED_WINDOW = Scenery.SMASHED_WINDOW_26110
        const val COMPOST_HEAP = Scenery.SINCLAIR_FAMILY_COMPOST_HEAP_26120
        const val BEEHIVE_LIMO = Scenery.SINCLAIR_FAMILY_BEEHIVE_26121
        const val BARREL_OF_FLOUR = Scenery.BARREL_OF_FLOUR_26122
    }

    override fun defineListeners() {

        on(POISON_SALESMAN, IntType.NPC, "talk-to") { player, npc ->
            openDialogue(player, PoisonSalesmanMMDialogue(), npc)
            return@on true
        }

        class PaperInSackDialogue : DialogueFile() {
            override fun handle(componentID: Int, buttonID: Int) {
                when (stage) {
                    0 -> sendDialogue(player!!, "There's some flypaper in there. Should I take it?").also { stage = 1 }
                    1 -> options("Yes. it might be useful.", "No, I don't see any need for it.").also { stage = 2 }
                    2 -> when (buttonID) {
                        1 -> addItem(player!!, FLYPAPER, 1).also { stage = 3 }
                        2 -> {
                            end()
                        }
                    }
                    3 -> sendDialogue(player!!, "You take a piece of flypaper. There is still plenty of fly paper left. Should I take it?").also { stage = 1 }
                }
            }
        }

        on(PAPER_IN_SACKS, IntType.SCENERY, "Investigate") { player, _ ->
            openDialogue(player, PaperInSackDialogue())
            return@on true
        }
    }
}