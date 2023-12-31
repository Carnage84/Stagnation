package content.quest.member.enlightenedjourney.cutscene

import config.NPCs
import core.api.location
import core.api.setQuestStage
import core.game.activity.Cutscene
import core.game.dialogue.FacialExpression
import core.game.node.entity.player.Player
import core.game.world.map.Direction

/**
 * Represents second experiment cutscene in Enlightened Journey quest.
 */
class SecondExperimentCutscene(player: Player) : Cutscene(player) {
    override fun setup() {
        setExit(location(2808, 3355, 0))
        if (player.settings.isRunToggled) {
            player.settings.toggleRun()
        }
        loadRegion(11060)
    }

    override fun runStage(stage: Int) {
        when (stage) {
            0 -> {
                fadeToBlack()
                timedUpdate(6)
            }

            1 -> {
                fadeFromBlack()
                teleport(player, 56, 27)
                player.faceLocation(location(184, 26, 0))
                addNPC(AUGUSTE, 57, 27, Direction.SOUTH_WEST)
                timedUpdate(5)
            }

            2 -> {
                playerDialogueUpdate(FacialExpression.FRIENDLY,"Well, that went down like a lead balloon.")
            }

            3 -> {
                end()
                setQuestStage(player, "Enlightened Journey", 5)
            }
        }
    }

    companion object {
        private const val AUGUSTE = NPCs.AUGUSTE_5049
    }
}

