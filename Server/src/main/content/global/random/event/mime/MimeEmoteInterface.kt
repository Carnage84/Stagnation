package content.global.random.event.mime

import config.Components
import content.global.random.event.mime.MimeUtils.getContinue
import core.api.*
import core.game.component.Component
import core.game.interaction.InterfaceListener
import core.game.node.entity.player.link.emote.Emotes

/**
 * Interface listener for Mime random event.
 */
class MimeEmoteInterface : InterfaceListener {

    private val mimeEmoteInterface = Components.MACRO_MIME_EMOTES_188
    override fun defineInterfaceListeners() {
        onOpen(mimeEmoteInterface) { _, _ ->
            return@onOpen true
        }

        on(mimeEmoteInterface) { player, _, _, buttonID, _, _ ->

            val buttons = mapOf(
                    emote(player, Emotes.THINK) to 2,
                    emote(player, Emotes.CRY) to 3,
                    emote(player, Emotes.LAUGH) to 4,
                    emote(player, Emotes.DANCE) to 5,
                    emote(player, Emotes.CLIMB_ROPE) to 6,
                    emote(player, Emotes.LEAN) to 7,
                    emote(player, Emotes.GLASS_BOX) to 8,
                    emote(player, Emotes.GLASS_WALL) to 9,
            )

            when (buttonID) {
                2 -> buttons[emote(player, Emotes.THINK)].toString() == ("Think")
                3 -> buttons[emote(player, Emotes.CRY)].toString()  == ("Cry")
                4 -> buttons[emote(player, Emotes.LAUGH)].toString() == ("Laugh")
                5 -> buttons[emote(player, Emotes.DANCE)].toString() == ("Dance")
                6 -> buttons[emote(player, Emotes.CLIMB_ROPE)].toString() == ("Climb Rope")
                7 -> buttons[emote(player, Emotes.LEAN)].toString() == ("Lean on air")
                8 -> buttons[emote(player, Emotes.GLASS_BOX)].toString() == ("Glass Box")
                9 -> buttons[emote(player, Emotes.GLASS_WALL)].toString() == ("Glass Wall")
            }

            when (getAttribute(player, MimeUtils.copyEmote, -1)) {
                2 -> if(buttonID == 2) setAttribute(player, MimeUtils.correctEmote, 1) else setAttribute(player, MimeUtils.wrongEmote, 1)
                3 -> if(buttonID == 3) setAttribute(player, MimeUtils.correctEmote, 1) else setAttribute(player, MimeUtils.wrongEmote, 1)
                4 -> if(buttonID == 4) setAttribute(player, MimeUtils.correctEmote, 1) else setAttribute(player, MimeUtils.wrongEmote, 1)
                5 -> if(buttonID == 5) setAttribute(player, MimeUtils.correctEmote, 1) else setAttribute(player, MimeUtils.wrongEmote, 1)
                6 -> if(buttonID == 6) setAttribute(player, MimeUtils.correctEmote, 1) else setAttribute(player, MimeUtils.wrongEmote, 1)
                7 -> if(buttonID == 7) setAttribute(player, MimeUtils.correctEmote, 1) else setAttribute(player, MimeUtils.wrongEmote, 1)
                8 -> if(buttonID == 8) setAttribute(player, MimeUtils.correctEmote, 1) else setAttribute(player, MimeUtils.wrongEmote, 1)
                9 -> if(buttonID == 9) setAttribute(player, MimeUtils.correctEmote, 1) else setAttribute(player, MimeUtils.wrongEmote, 1)
            }

            runTask(player, 4) {
                if (getAttribute(player, MimeUtils.correctEmote, -1) == 1) {
                    Component.setUnclosable(player, player.dialogueInterpreter.sendPlainMessage(true, "", "Correct!"))
                    removeAttribute(player, MimeUtils.mimeEmote)
                    emote(player, Emotes.CHEER)
                    getContinue(player)
                }

                if (getAttribute(player, MimeUtils.wrongEmote, -1) == 1) {
                    removeAttribute(player, MimeUtils.mimeEmote)
                    runTask(player, 4) {
                        Component.setUnclosable(player, player.dialogueInterpreter.sendPlainMessage(true, "", "Wrong!"))
                        emote(player, Emotes.CRY)
                        getContinue(player)
                    }
                }
            }
            return@on true
        }
    }
}