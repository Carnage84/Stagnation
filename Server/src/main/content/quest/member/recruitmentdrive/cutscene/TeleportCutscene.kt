

import content.quest.member.recruitmentdrive.dialogue.SirTiffyCashienRDDialogue
import content.quest.member.recruitmentdrive.npc.SirRenItchoodDialogue
import core.api.location
import core.api.openDialogue
import core.game.activity.Cutscene
import core.game.node.entity.impl.ForceMovement
import core.game.node.entity.player.Player
import core.game.world.map.Location

/**
 * Cutscene handles entering the secret area in Recruitment drive quest.
 */
class TeleportCutscene(player: Player) : Cutscene(player) {
    override fun setup() {
        loadRegion(9805)
        setExit(location(2439, 4956, 0))
    }

    override fun runStage(stage: Int) {
        when (stage) {
            0 -> {
                if (player.questRepository.getStage("Recruitment Drive") == 20) {
                    openDialogue(player, SirTiffyCashienRDDialogue())
                }
                timedUpdate(1)
            }
            1 -> {
                end {
                    ForceMovement.run(player, player.location, Location.create(2440, 4956, 0))
                    openDialogue(player, SirRenItchoodDialogue())
                }
            }
        }
    }
}