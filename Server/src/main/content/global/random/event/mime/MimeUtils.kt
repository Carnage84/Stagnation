package content.global.random.event.mime

import config.Components
import config.Items
import config.NPCs
import core.api.*
import core.game.component.Component
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.TeleportManager
import core.game.node.entity.player.link.emote.Emotes
import core.game.node.scenery.Scenery
import core.game.system.task.Pulse
import core.game.system.timer.impl.AntiMacro
import core.game.world.map.Location
import core.tools.RandomFunction

/**
 * Utils for Mime random event.
 */
object MimeUtils {

    val mimeStageLocation = Location(2008, 4764, 0)
    val mimeEndStageLocation = location(2008, 4762, 0)

    val playerSceneryLocation = Location(2007, 4761, 0)
    val mimeSceneryLocation = Location(2010, 4761, 0)

    val savedPlayerPosition = "mime:save-location"
    val mimeLogout = "mime:save-location"
    val mimeEmote = "mime:emote"
    val copyEmote = "mime:emote-copy"
    val correctEmote = "mime:emote-correct"
    val wrongEmote = "mime:emote-wrong"

    val defaultWalkAnim = 823

    val lightTurnOn = 3644
    val lightTurnOff = 3645

    fun teleport(player: Player) {
        setAttribute(player, savedPlayerPosition, player.location)

        registerLogoutListener(player, mimeLogout) { p ->
            p.location = getAttribute(p, savedPlayerPosition, player.location)
        }

        teleport(player, Location.create(2008, 4764, 0), TeleportManager.TeleportType.NORMAL)

        player.interfaceManager.removeTabs(0, 1, 2, 3, 4, 5, 6, 7, 9, 10, 11, 12, 13, 14)

        AntiMacro.terminateEventNpc(player)
    }

    fun cleanup(player: Player) {
        player.properties.teleportLocation = getAttribute(player, savedPlayerPosition, null)
        clearLogoutListener(player, mimeLogout)
        removeAttributes(player, mimeLogout, savedPlayerPosition, mimeEmote, copyEmote, correctEmote, wrongEmote)
        player.interfaceManager.restoreTabs()
        closeInterface(player)
        unlock(player)
    }

    fun reward(player: Player) {
        val hasMask = hasAnItem(player, Items.MIME_MASK_3057).container != null
        val hasTop = hasAnItem(player, Items.MIME_TOP_3058).container != null
        val hasLegs = hasAnItem(player, Items.MIME_LEGS_3059).container != null
        val hasBoots = hasAnItem(player, Items.MIME_BOOTS_3061).container != null
        val hasGloves = hasAnItem(player, Items.MIME_GLOVES_3060).container != null
        when {

            (!hasMask) -> {
                sendDialogue(player, "You can now use Lean on air emote!")
                addItemOrDrop(player, Items.MIME_MASK_3057, 1)
                player.emoteManager.unlock(Emotes.LEAN)
            }

            (!hasTop) -> {
                sendDialogue(player, "You can now use Climb Rope emote!")
                addItemOrDrop(player, Items.MIME_TOP_3058, 1)
                player.emoteManager.unlock(Emotes.CLIMB_ROPE)
            }

            (!hasLegs) -> {
                sendDialogue(player, "You can now use Glass Wall emote!")
                addItemOrDrop(player, Items.MIME_LEGS_3059, 1)
                player.emoteManager.unlock(Emotes.GLASS_WALL)
            }

            (!hasBoots) && (!hasGloves) -> {
                sendDialogue(player, "You can now use Glass Box emote!")
                addItemOrDrop(player, Items.MIME_GLOVES_3060, 1)
                addItemOrDrop(player, Items.MIME_BOOTS_3061, 1)
                player.emoteManager.unlock(Emotes.GLASS_BOX)
            }

            else -> { addItemOrDrop(player, Items.COINS_995, 500) }

        }
    }

    fun getEmote(player: Player) {
        val npc = findNPC(NPCs.MIME_1056)
        val emoteNumber = getAttribute(player, mimeEmote, -1)
        Component.setUnclosable(player, player.dialogueInterpreter.sendPlainMessage(true, "", "Watch the Mime.", "See what emote he performs."))
        submitIndividualPulse(npc!!, object : Pulse() {
            var counter = 0
            override fun pulse(): Boolean {
                when (counter++) {
                    0 -> npc.faceLocation(location(2011, 4759, 0))
                    1 -> replaceScenery(Scenery(lightTurnOn, playerSceneryLocation), lightTurnOff, -1)
                    3 -> when (emoteNumber) {
                        2 -> emote(npc, Emotes.THINK).also { setAttribute(player, copyEmote, 2) }
                        3 -> emote(npc, Emotes.CRY).also { setAttribute(player, copyEmote, 3) }
                        4 -> emote(npc, Emotes.LAUGH).also { setAttribute(player, copyEmote, 4) }
                        5 -> emote(npc, Emotes.DANCE).also { setAttribute(player, copyEmote, 5) }
                        6 -> emote(npc, Emotes.CLIMB_ROPE).also { setAttribute(player, copyEmote, 6) }
                        7 -> emote(npc, Emotes.LEAN).also { setAttribute(player, copyEmote, 7) }
                        8 -> emote(npc, Emotes.GLASS_BOX).also { setAttribute(player, copyEmote, 8) }
                        9 -> emote(npc, Emotes.GLASS_WALL).also { setAttribute(player, copyEmote, 9) }
                    }

                    10 -> npc.faceLocation(location(2008, 4762, 0))
                    11 -> emote(npc, Emotes.BOW)
                    14 -> replaceScenery(Scenery(lightTurnOn, mimeSceneryLocation), lightTurnOff, -1)
                    15 -> {
                        replaceScenery(Scenery(lightTurnOff, playerSceneryLocation), lightTurnOn, -1)
                        openInterface(player, Components.MACRO_MIME_EMOTES_188)
                        return true
                    }
                }
                return false
            }
        })
    }

    fun getContinue(player: Player) {
        submitIndividualPulse(player, object : Pulse() {
            var counter = 0
            override fun pulse(): Boolean {
                when (counter++) {
                    3 -> {
                        if (getAttribute(player, correctEmote, -1) == 1) {
                            cleanup(player)
                            openInterface(player, Components.CHATDEFAULT_137)
                            submitWorldPulse(object : Pulse(2) {
                                override fun pulse(): Boolean {
                                    reward(player)
                                    return true
                                }
                            })
                            return false
                        } else if (getAttribute(player, wrongEmote, -1) == 1) {
                            setAttribute(player, mimeEmote, RandomFunction.random(2, 9))
                            removeAttribute(player, wrongEmote)
                            openInterface(player, Components.CHATDEFAULT_137)
                            replaceScenery(Scenery(lightTurnOn, playerSceneryLocation), lightTurnOff, -1)
                            replaceScenery(Scenery(lightTurnOff, mimeSceneryLocation), lightTurnOn, -1)
                            Component.setUnclosable(player, player.dialogueInterpreter.sendPlainMessage(true, "", "Watch the Mime.", "See what emote he performs."))
                            getEmote(player)
                            return true
                        }
                    }
                }
                return false
            }
        })
    }
}