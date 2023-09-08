package content.quest.member.fightarena.cutscenes

import content.quest.member.fightarena.FightArenaListeners.Companion.Jeremy
import content.quest.member.fightarena.npc.OgreNPC.Companion.spawnOgre
import core.api.*
import core.game.activity.Cutscene
import core.game.dialogue.FacialExpression
import core.game.global.action.DoorActionHandler
import core.game.node.entity.player.Player
import core.game.world.map.Direction

/**
 * Handles first cutscene for Fight Arena quest.
 */
class EscapeCutscene(player: Player) : Cutscene(player) {
    override fun setup() {
        setExit(location(2603, 3155, 0))
        if (player.settings.isRunToggled) {
            player.settings.toggleRun()
        }
    }

    override fun runStage(stage: Int) {
        when (stage) {
            0 -> {
                face(player, Jeremy,1)
                sendPlayerDialogue(player, "Jeremy look, I have the keys.",FacialExpression.FRIENDLY)
                timedUpdate(1)
            }

            1 -> {
                face(Jeremy, player,1)
                sendNPCDialogue(player, 265, "Wow! Please set me free, then we can find my dad. I overheard a guard talking. I think they're taken him to the arena.", FacialExpression.AMAZED)
                timedUpdate(1)
            }

            2 -> {
                getObject(56, 31,0)?.let { face(player, it,1) }
                sendPlayerDialogue(player, "Ok, we'd better hurry.",FacialExpression.NEUTRAL)
                timedUpdate(1)
            }

            3 -> {
                animate(player, 2098)
                resetFace(player)
                timedUpdate(2)
            }

            4 -> {
                move(player, 57, 32)
                timedUpdate(1)
            }

            5 -> {
                DoorActionHandler.handleAutowalkDoor(Jeremy, getObject(57, 31, 0))
                timedUpdate(3)
            }

            6 -> {
                face(player, Jeremy,2)
                sendChat(Jeremy, "I'll run ahead.")
                move(Jeremy, 57, 20)
                timedUpdate(6)
            }

            7 -> {
                teleport(Jeremy, 56, 31)
                loadRegion(10289)
                addNPC(JEREMYRESCUE, 41, 17, Direction.NORTH)
                addNPC(GENERAL, 45, 19, Direction.NORTH)
                addNPC(OGRE, 48, 30, Direction.NORTH)
                addNPC(JUSTIN, 41, 32, Direction.EAST)
                timedUpdate(-1)
            }

            8 -> {
                moveCamera(47, 20)
                rotateCamera(45, 15)
                teleport(player, 47, 15)
                timedUpdate(2)
            }

            9 -> {
                DoorActionHandler.handleAutowalkDoor(player, getObject(46, 16))
                moveCamera(41, 26, 300, 4)
                rotateCamera(45, 15, 300, 4)
                timedUpdate(-1)
            }

            10 -> {
                move(player, 44, 17)
                timedUpdate(2)
            }

            11 -> {
                move(player, 43, 18)
                timedUpdate(2)
            }

            12 -> {
                move(player, 43, 19)
                timedUpdate(2)
            }

            13 -> {
                face(player, getNPC(JEREMYRESCUE)!!)
                sendPlayerDialogue(player, "Jeremy, where's your father?",FacialExpression.HALF_ASKING)
                timedUpdate(2)
            }

            14 -> {
                teleport(getNPC(OGRE)!!, 45, 30)
                move(getNPC(JUSTIN)!!, 42, 32)
                face(getNPC(JEREMYRESCUE)!!, player)
                sendNPCDialogue(player,JEREMYRESCUE, "Quick help him! That beast will kill him. He's too old to fight.",FacialExpression.WORRIED)
                timedUpdate(3)
            }

            15 -> {
                moveCamera(42, 26)
                rotateCamera(42, 26)
                timedUpdate(-1)
            }

            16 -> {
                move(getNPC(OGRE)!!, 43, 31)
                timedUpdate(4)
            }

            17 -> {
                getNPC(OGRE)!!.faceLocation(getNPC(JUSTIN)!!.location)
                animate(getNPC(OGRE)!!, 359, true)
                animate(getNPC(JUSTIN)!!, 404, true)
                timedUpdate(2)
            }

            18 -> {
                end { spawnOgre(player) }
            }
        }
    }

    companion object {
        private const val GENERAL = 258
        private const val JEREMYRESCUE = 266
        private const val JUSTIN = 267
        private const val OGRE = 270
    }
}

