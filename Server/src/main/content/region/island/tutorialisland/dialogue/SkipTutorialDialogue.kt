package content.region.island.tutorialisland.dialogue

import config.NPCs
import content.region.island.tutorialisland.handlers.TutorialStage
import core.api.setAttribute
import core.api.teleport
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.player.Player
import core.game.world.map.Location
import core.plugin.Initializable

/**
 * Represents the Skip Tutorial dialogue plugin.
 */
@Initializable
class SkipTutorialDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when(stage) {
            0 -> npcl(FacialExpression.FRIENDLY, "Hey, would you like to skip to the end? Choose wisely! This is the only time you get this choice.").also { stage++ }
            1 -> options("Yes, I'd like to skip the tutorial.", "No thanks.").also { stage++ }
            2 -> when (buttonId) {
                1 -> {
                    end()
                    setAttribute(player, "/save:tutorial:stage", 71)
                    TutorialStage.load(player, 71)
                    teleport(player, Location.create(3141, 3089, 0))
                }
                2 -> end()
            }
        }
        return true
    }

    override fun newInstance(player: Player?): DialoguePlugin {
        return SkipTutorialDialogue(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.SKIPPY_2796)
    }
}