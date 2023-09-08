package content.global.random.event.pinball

import config.Animations
import config.Components
import config.NPCs
import content.global.random.event.pinball.dialogue.FlippaDialogue
import content.global.random.event.pinball.dialogue.TiltDialogue
import content.global.random.event.pinball.util.PinballUtils
import core.api.*
import core.game.component.Component
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.world.map.Direction
import core.tools.BLUE
import core.tools.RandomFunction

/**
 * Listener for pinball random event.
 */
class PinballListener : InteractionListener {
    init {
        PinballUtils.oldMan.init()
        PinballUtils.oldMan.isWalks = false
        PinballUtils.oldMan.isInvisible = false
        PinballUtils.oldMan.direction = Direction.EAST
    }

    override fun defineListeners(){
        on(PinballUtils.correctPillars, IntType.SCENERY, "Tag"){ player, _ ->
            player.incrementAttribute(PinballUtils.PINBALL_SCORE).also{
                setInterfaceText(player, "Score: " + getAttribute(player, PinballUtils.PINBALL_SCORE, this.toString()) + "", 263, 1)
                Component.setUnclosable(player, player.dialogueInterpreter.sendPlainMessage(true, "", "Well done! Now tag the next post.",))
            }

            if(getAttribute(player, PinballUtils.PINBALL_SCORE, 0) == 10){
                animate(player, Animations.HUMAN_MULTI_USE_832)
                PinballUtils.replacePillars(player)
                Component.setUnclosable(player, player.dialogueInterpreter.sendPlainMessage(true, "", "Congratulations - you can now leave the arena."))
            }else{
                animate(player, Animations.HUMAN_MULTI_USE_832)
                PinballUtils.replacePillars(player)
                runTask(player, 0) {
                    setAttribute(player, PinballUtils.GET_PILLAR, RandomFunction.random(5)).also {
                        PinballUtils.getPillars(player)
                    }
                }
            }
            return@on true
        }

        on(PinballUtils.wrongPillars, IntType.SCENERY, "Tag"){ player, _ ->
            setAttribute(player, PinballUtils.PINBALL_SCORE, 0).also{
                setInterfaceText(player, "Score: 0", Components.PINBALL_INTERFACE_263, 1)
                Component.setUnclosable(player, player.dialogueInterpreter.sendPlainMessage(true, "", "Wrong post! Your score has been reset.", "Tag the post with the " + BLUE + "flashing rings" + ".",))
            }
            if(getAttribute(player, PinballUtils.PINBALL_SCORE, 0) == 10){
                animate(player, Animations.HUMAN_MULTI_USE_832)
                PinballUtils.replacePillars(player)
                Component.setUnclosable(player, player.dialogueInterpreter.sendPlainMessage(true, "", "Congratulations - you can now leave the arena.",))
            }else{
                animate(player, Animations.HUMAN_MULTI_USE_832)
                PinballUtils.replacePillars(player)
                runTask(player, 0) {
                    setAttribute(player, PinballUtils.GET_PILLAR, RandomFunction.random(5)).also {
                        PinballUtils.getPillars(player)
                    }
                }
            }
            return@on true
        }

        on(PinballUtils.CAVE_EXIT, IntType.SCENERY, "Exit"){ player, _ ->
            openDialogue(player, FlippaDialogue())
            return@on true
        }

        on(NPCs.TILT_3913, IntType.NPC, "Talk-to"){ player, _ ->
            openDialogue(player, TiltDialogue())
            return@on true
        }

        on(NPCs.FLIPPA_3912, IntType.NPC, "Talk-to"){ player, _ ->
            openDialogue(player, FlippaDialogue())
            return@on true
        }
    }
}