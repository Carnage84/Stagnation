package content.global.skill.member.agility.shortcuts

import config.Scenery
import core.api.*
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.node.entity.skill.Skills
import core.game.world.update.flag.context.Animation

/**
 * Handles the Rock passage shortcut.
 */
class RockPassageShortcut : InteractionListener {

    private val squeezeThrough = Animation(4855)
    override fun defineListeners() {
        on(Scenery.ROCK_PASSAGE_29099, IntType.SCENERY, "squeeze-through") { player, _ ->
            when (player.location.y) {
                2871 -> face(player, location(2596, 2869, 0))
                2869 -> face(player, location(2596, 2871, 0))
            }
            if (!hasLevelDyn(player, Skills.AGILITY, 29)) {
                sendDialogue(player, "You need an Agility level of at least 29 to do this.")
            } else {
                lock(player, 3)
                animate(player, squeezeThrough)
                runTask(player, 2) {
                    when (player.location.y) {
                        2871 -> teleport(player, location(2596, 2869, 0))
                        2869 -> teleport(player, location(2596, 2871, 0))
                    }
                }
            }
            return@on true
        }
    }
}