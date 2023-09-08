package content.region.island.tutorialisland.handlers

import config.NPCs
import core.api.openDialogue
import core.api.setAttribute
import core.api.teleport
import core.game.dialogue.DialogueFile
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.world.map.Location

/**
 * Handles the NPC skippy option.
 */
class SkipTutorialListener : InteractionListener {
    companion object {
        private val SKIPPY = NPCs.SKIPPY_2796
    }

    class SkippyDialogue() : DialogueFile() {
        override fun handle(componentID: Int, buttonID: Int) {
            when (stage) {
                0 -> options("Yes, I'd like to skip the tutorial.", "No thanks.").also { stage++ }
                1 -> when (buttonID) {
                    1 -> {
                        end()
                        setAttribute(player!!, "/save:tutorial:stage", 71)
                        TutorialStage.load(player!!, 71)
                        teleport(player!!, Location.create(3141, 3089, 0))
                    }

                    2 -> end()
                }
            }
        }
    }

    override fun defineListeners() {
        on(SKIPPY, IntType.NPC, "sober-up") { player, node ->
            openDialogue(player, SkippyDialogue(), node.asNpc())
            return@on true
        }
    }
}
