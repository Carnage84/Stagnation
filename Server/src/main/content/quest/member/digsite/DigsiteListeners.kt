package content.quest.member.digsite

import config.Items
import config.NPCs
import config.Scenery
import core.api.*
import core.game.dialogue.DialogueFile
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.node.entity.npc.NPC
import core.game.world.update.flag.context.Animation
import core.tools.END_DIALOGUE
import core.tools.RandomFunction

/**
 * Handles all interaction for Digsite quest.
 */
class DigsiteListeners : InteractionListener {

    companion object {
        const val DIGSITE_WORKMAN = NPCs.DIGSITE_WORKMAN_613
        const val DOUG_DEEPING_EXPERT = NPCs.DOUG_DEEPING_614
        const val STUDENT_ONE = NPCs.STUDENT_615
        const val STUDENT_TWO = NPCs.STUDENT_616
        const val STUDENT_THREE = NPCs.STUDENT_617
        const val EXAMINER = NPCs.EXAMINER_618
        const val ARCHAEOLOGICAL_EXPERT = NPCs.ARCHAEOLOGICAL_EXPERT_619
        const val CURATOR_HAIG_HELEN = NPCs.CURATOR_HAIG_HALEN_646
        const val RESERARCHER = NPCs.RESEARCHER_4568

        private const val ancient_talisman = Items.ANCIENT_TALISMAN_681
        private const val invitation_letter = Items.INVITATION_LETTER_696
        private const val chemical_compund = Items.CHEMICAL_COMPOUND_707
        private const val speciman_jar = Items.SPECIMEN_JAR_669
        private const val speciman_bush = Items.SPECIMEN_BRUSH_670
        private const val animal_skull = Items.ANIMAL_SKULL_671
        private const val special_cup = Items.SPECIAL_CUP_672
        private const val teddy_bear = Items.TEDDY_673
        private const val unstamped_letter = Items.UNSTAMPED_LETTER_682
        private const val panning_tray = Items.PANNING_TRAY_677
        private const val full_panning_tray = Items.PANNING_TRAY_678
        private const val BUSH_WITH_TEDDY = Scenery.BUSH_2358

        private const val panning_tray_fishing_spots = 2363
        private const val panning_tray_animation = 4593
    }

    override fun defineListeners() {
        on(BUSH_WITH_TEDDY, IntType.SCENERY, "search") { player, _ ->
            sendDialogue(player, "Hey, something has been dropped here...")
            addItem(player, teddy_bear)
        }

        onUseWith(IntType.SCENERY, panning_tray, panning_tray_fishing_spots) { player, _, _ ->
            if (getQuestStage(player,"The Dig Site") >= 3) {
                player.animate(Animation(panning_tray_animation))
                sendItemDialogue(player, full_panning_tray, "You lift the full tray from the water.")
                addItem(player, full_panning_tray)
                player.incrementAttribute("digsite:special_cup")
                val chance = (10 / 1.5).toInt()
                if (RandomFunction.roll(chance)) {
                    addItemOrDrop(player, special_cup)
                    sendItemDialogue(player, Items.SPECIAL_CUP_672, "You find a shiny cup covered in mud.")
                }
            }
            return@onUseWith true
        }

        on(full_panning_tray, IntType.ITEM, "search") { player, _ ->
            if (getQuestStage(player,"The Dig Site") >= 3) {
                sendItemDialogue(player, full_panning_tray, "The tray contains only plain mud.")
                setAttribute(player, "digsite:special_cup", 0)
            } else {
                openDialogue(player, PanningGuideDialogue())
            }
            return@on true
        }

        on(DIGSITE_WORKMAN, IntType.NPC, "steal-from") { player, _ ->
            sendMessage(player, "You attempt to pick thr workman's pocket...")
            if (getQuestStage(player,"The Dig Site") >= 3) {
                sendItemDialogue(player, animal_skull, "You steal an animal skull.")
                addItem(player, animal_skull)
            } else {
                return@on false
            }
            return@on true
        }
    }


    class PanningGuideDialogue : DialogueFile() {
        override fun handle(componentID: Int, buttonID: Int) {

            val questName = "Digsite"
            val questStage = getQuestStage(player!!, questName)
            npc = NPC(NPCs.DIGSITE_WORKMAN_613)
            when {


                (questStage == 1) -> {
                    when (stage) {
                        0 -> npcl("Hey, you can't pan yet!").also { stage = 1 }
                        1 -> playerl("Why not?").also { stage = 2 }
                        2 -> npcl("We do not allow to uninvited to pan here.").also { stage = 3 }
                        3 -> options("OK, forget it.", "So how do I become invited then?").also { stage = 4 }
                        4 -> when (buttonID) {
                            1 -> playerl("OK, forget it.").also { stage = END_DIALOGUE }
                            2 -> playerl("So how do I become invited then?").also { stage = 5 }
                        }
                        5 -> npcl("I'm not supposed to let people pan here unless they have permission from the autorities first. Mind you, I could let you have a go if you're willing to do me a favour.").also {
                            stage = 6
                        }
                        6 -> playerl("What's then?").also { stage = 7 }
                        7 -> npcl("Well, to be honest, what I would really like is... a nice cup of tea!").also {
                            stage = 8
                        }
                        8 -> if (inInventory(player!!, Items.CUP_OF_TEA_1978)) {
                            playerl("I've some here that you can have.").also { stage = 9 }
                        } else {
                            playerl("OK, forget it.").also { stage = END_DIALOGUE }
                        }
                        9 -> npcl("Ah! Lovely! You can't beat a good cuppa... You're free to pan all you want.").also {
                            stage = 10
                        }
                        10 -> {
                            end()
                            setQuestStage(player!!, "Digiste", 3)
                        }
                    }
                }
            }
        }
    }

    // Finding the ancient talisman in the digsite

    class StrangeTalismanDialogue : DialogueFile() {
        override fun handle(componentID: Int, buttonID: Int) {
            when (stage) {
                0 -> npcl("You find a strange talisman.").also { stage = END_DIALOGUE }
            }
        }
    }

    // Reading the invitation letter
    class InvitationLetterDialogue : DialogueFile() {
        override fun handle(componentID: Int, buttonID: Int) {
            when (stage) {
                0 -> playerl("It says, 'I give permission for the bearer...to use the mine shafts on site. Signed Terrance Balando, Archaeological Expert, City of Varrock.'").also {
                    stage = END_DIALOGUE
                }
            }
        }
    }

    // Using a rope on the winch before giving the invitation letter to a workman
    class WinchDialogue : DialogueFile() {
        override fun handle(componentID: Int, buttonID: Int) {
            when (stage) {
                0 -> playerl("Err... I have no idea why I am doing this!").also { stage = END_DIALOGUE }
            }
        }
    }

    // Operating the winch after giving the digsite workman the invitation letter
    class WinchDialogue1 : DialogueFile() {
        override fun handle(componentID: Int, buttonID: Int) {
            when (stage) {
                0 -> playerl("Hey, I think I could fit down here. I need something to help me get all the way down.").also {
                    stage = END_DIALOGUE
                }
            }
        }
    }

    // Searching the bricks before using the chemical compound on it
    class BricksDialogue : DialogueFile() {
        override fun handle(componentID: Int, buttonID: Int) {
            when (stage) {
                0 -> playerl("Hmm, there's a room past these bricks. If I could move them out of the way then I could find out what's inside. Maybe there's someone else working in these underground areas here who can help...").also {
                    stage = END_DIALOGUE
                }
            }
        }
    }

    // Using the chemical compound on the bricks
    class BricksDialogue1 : DialogueFile() {
        override fun handle(componentID: Int, buttonID: Int) {
            when (stage) {
                0 -> playerl("Okay, the mixture is all over the bricks. I need some way to ignite this compound.").also {
                    stage = END_DIALOGUE
                }
            }
        }
    }

    // Using a tinderbox on the bricks AFTER using the chemical compound on the bricks
    class BricksDialogue2 : DialogueFile() {
        override fun handle(componentID: Int, buttonID: Int) {
            when (stage) {
                0 -> playerl("Whoa! This is going to blow! I'd better run!").also {
                    stage = END_DIALOGUE
                }
            }
        }
    }

    // Bricks go boom!
    class PlayerDialogue : DialogueFile() {
        override fun handle(componentID: Int, buttonID: Int) {
            when (stage) {
                0 -> playerl("Wow, that was a big explosion! What's that noise I can hear? Sounds like bones moving or something...").also {
                    stage = END_DIALOGUE
                }
            }
        }
    }

    //Searching the digsite chest with the chest key in inventory
    class DigsiteChestExpertDialogue : DialogueFile() {
        override fun handle(componentID: Int, buttonID: Int) {
            when (stage) {
                0 -> npcl("You find some unusual power inside...").also { stage = END_DIALOGUE }
            }
        }
    }

    //Attempting to open the digsite barrel
    class DigsiteBarrelExpertDialogue1 : DialogueFile() {
        override fun handle(componentID: Int, buttonID: Int) {
            when (stage) {
                0 -> playerl("The lid is shut tight; I'll have to find something to lever it off.").also {
                    stage = END_DIALOGUE
                }
            }

            when (stage) {
                0 -> playerl("Great! It's opened it.").also { stage = END_DIALOGUE }
            }
        }
    }

    // Searching the now opened barrel
    class DigsiteBarrelExpertDialogue2 : DialogueFile() {
        override fun handle(componentID: Int, buttonID: Int) {
            when (stage) {
                0 -> playerl("I'll need a container to collect some. It looks and smells rather dangerous, so it'll need to be something small and capable of containing dangerous chemicals.").also {
                    stage = END_DIALOGUE
                }
            }
        }
    }

    // Using a specimen jar on the digsite barrel
    class DigsiteBarrelExpertDialogue3 : DialogueFile() {
        override fun handle(componentID: Int, buttonID: Int) {
            when (stage) {
                0 -> playerl("Perhaps not; it might contaminate the samples.").also { stage = END_DIALOGUE }
            }
        }
    }

    //Using a panning tray on the digsite barrel
    class DigsiteBarrelExpertDialogue4 : DialogueFile() {
        override fun handle(componentID: Int, buttonID: Int) {
            when (stage) {
                0 -> playerl("Not the best idea I've had; it's likely to spill everywhere in that!").also {
                    stage = END_DIALOGUE
                }
            }
        }
    }

    // Using a vial on the digsite barrel
    class DigsiteBarrelExpertDialogue5 : DialogueFile() {
        override fun handle(componentID: Int, buttonID: Int) {
            when (stage) {
                0 -> playerl("You fill the vial with the liquid.").also { stage++ }
                1 -> playerl("I'm not sure what this stuff is. I had better be VERY careful with it; I had better not drop it either...").also {
                    stage = END_DIALOGUE
                }
            }
        }
    }

    // Completing the chemical compound
    class ChemicalCompoundDialogue : DialogueFile() {
        override fun handle(componentID: Int, buttonID: Int) {
            when (stage) {
                0 -> playerl("Excellent! This looks just right!").also { stage = END_DIALOGUE }
            }
        }
    }

    // Reading the stone tablet
    class StoneTabletDialogue : DialogueFile() {
        override fun handle(componentID: Int, buttonID: Int) {
            when (stage) {
                0 -> playerl("It says: 'Tremble, mortals, for Zaros shall return.'").also {
                    stage = END_DIALOGUE
                }
            }
        }
    }
}