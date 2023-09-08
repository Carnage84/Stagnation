package content.global.skill.member.agility.shortcuts

import config.Scenery
import content.global.skill.member.agility.AgilityHandler
import core.api.inBorders
import core.api.location
import core.api.sendMessage
import core.api.submitIndividualPulse
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.node.entity.player.link.diary.DiaryType
import core.game.system.task.Pulse
import core.game.world.map.Location
import core.game.world.update.flag.context.Animation

/**
 * Handles the broken pier shortcut between Miscellania and Etceteria.
 */
class BrokenPierShortcut : InteractionListener {

    companion object {
        const val BROKEN_PIER = Scenery.BROKEN_PIER_41531
        val LONG_JUMP = Animation(6132)
        val SHORT_JUMP = Animation(769)
    }

    override fun defineListeners() {
        on(BROKEN_PIER, IntType.SCENERY, "step") { player, _ ->
            if (!player.achievementDiaryManager.getDiary(DiaryType.FREMENNIK).isComplete(1, true)) {
                sendMessage(player, "You must complete the medium Fremennik Achievements to use this shortcut.")
            } else if (inBorders(player, 2571, 3854, 2575, 3870)) {
                submitIndividualPulse(player, object : Pulse() {
                    var counter = 0
                    override fun pulse(): Boolean {
                        when (counter++) {
                            1 -> {
                                AgilityHandler.forceWalk(
                                    player, -1, location(2572, 3862, 0),
                                    Location.create(2573, 3862, 0),
                                    SHORT_JUMP, 10, 0.0, null
                                )
                            }

                            3 -> {
                                AgilityHandler.forceWalk(
                                    player, -1, location(2573, 3862, 0),
                                    Location.create(2576, 3862, 0),
                                    LONG_JUMP, 10, 0.0, null
                                ).also { return true }
                            }
                        }
                        return false
                    }
                })
            } else {
                submitIndividualPulse(player, object : Pulse() {
                    var counter = 0
                    override fun pulse(): Boolean {
                        when (counter++) {
                            1 -> {
                                AgilityHandler.forceWalk(
                                    player, -1, location(2576, 3862, 0),
                                    location(2573, 3862, 0),
                                    LONG_JUMP, 10, 0.0, null
                                )
                            }

                            3 -> {
                                AgilityHandler.forceWalk(
                                    player, -1, location(2573, 3862, 0),
                                    location(2572, 3862, 0),
                                    SHORT_JUMP, 10, 0.0, null
                                ).also { return true }
                            }
                        }
                        return false
                    }
                })
            }
            return@on true
        }
    }


    override fun defineDestinationOverrides() {
        setDest(IntType.SCENERY, intArrayOf(BROKEN_PIER), "step") { player, _ ->
            if (inBorders(player, 2576, 3854, 2587, 3870)) {
                return@setDest Location.create(2576, 3862, 0)
            } else {
                return@setDest Location.create(2572, 3862, 0)
            }
        }
    }
}
