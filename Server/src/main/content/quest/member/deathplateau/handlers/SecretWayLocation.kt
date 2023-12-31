package content.quest.member.deathplateau.handlers

import core.api.*
import core.game.node.entity.Entity
import core.game.node.entity.player.Player
import core.game.world.map.zone.ZoneBorders

/**
 * Handles the Secret Way for Death Plateau quest.
 */
class SecretWayLocation : MapArea {
    override fun defineAreaBorders(): Array<ZoneBorders> {
        return arrayOf(ZoneBorders(2866, 3609, 2866, 3609))
    }

    override fun areaEnter(entity: Entity) {
        if (entity is Player && getQuestStage(entity, "Death Plateau") == 25) {
            sendPlayerDialogue(entity, "I think this is far enough, I can see Death Plateau and it looks like the trolls haven't found the path. I'd better go and tell Denulth.")
            sendMessage(entity, "You can see that there are no trolls on the secret path")
            sendMessage(entity, "You should go and speak to Denulth")
            setQuestStage(entity, "Death Plateau", 26)
        }
    }
}