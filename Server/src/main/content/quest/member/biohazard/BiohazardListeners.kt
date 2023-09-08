package content.quest.member.biohazard

import config.Items
import config.Scenery
import content.quest.member.biohazard.dialogue.BiohazardCupboardDialogue
import core.api.*
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.system.task.Pulse

/**
 * Listener for Biohazard quest.
 */
class BiohazardListeners : InteractionListener {
    override fun defineListeners() {
        on(Scenery.CUPBOARD_2057, IntType.SCENERY, "search") { player, _ ->
            if(getQuestStage(player, "Biohazard") == 10) {
                openDialogue(player, BiohazardCupboardDialogue())
            }
            return@on true
        }

        onUseWith(IntType.SCENERY, Items.BIRD_FEED_422, Scenery.WATCHTOWER_FENCE_2067) { player, used, _ ->
            if(getAttribute(player, Biohazard.ATTRIBUTE_FEED_ON_FENCE, true)) {
                return@onUseWith true
            } else {
                if(removeItem(player, used)) {
                    sendMessage(player, "You throw a handful of seeds onto the watchtower.")
                    runTask(player, 1, 0) {
                        sendMessage(player, "The mourners do not seem to notice.")
                    }
                    setAttribute(player, Biohazard.ATTRIBUTE_FEED_ON_FENCE, true)
                }
            }

            return@onUseWith true
        }

        on(Items.PIGEON_CAGE_424, IntType.ITEM, "open") { player, _ ->
            if(getAttribute(player, Biohazard.ATTRIBUTE_FEED_ON_FENCE, false)) return@on true
            if(player.location != Biohazard.FENCE_CORNER_LOCATION) return@on true
            face(player, Biohazard.WATCHTOWER_CORNER_LOCATION)
            var pCount = 0
            submitIndividualPulse(
                player,
                object : Pulse() {
                    override fun pulse(): Boolean {
                        pCount++
                        when(pCount) {
                            0 -> sendMessage(player, "You open the cage.")
                            2 -> sendMessage(player, "The pigeons fly towards the watch tower.")
                            4 -> sendMessage(player, "The mourners are frantically trying to scare the pigeons away.")
                            5 -> {
                                setAttribute(player, "/save:biohazard:guards-distracted", true)
                                return true
                            }
                        }
                        return false
                    }
                }
            )
            // Looks like this wasn't exactly authentic, but if someone wants to add the bird projectile properly go nuts
            // spawnProjectile(player.location, Biohazard.WATCHTOWER_CORNER_LOCATION, 72 /** add to constlib */, 0, 0, 0, 0, 0)
            return@on true
        }
    }
}
