package content.quest.member.toweroflife

import config.Scenery
import core.api.*
import core.game.global.action.DoorActionHandler
import core.game.interaction.IntType
import core.game.interaction.InteractionListener

/**
 * Listeners for Tower Of Life quest.
 */
class TowerOfLifeListener : InteractionListener {

    companion object{
        val ENTER_TOL = "/save:tol:tower-enter"
        val SEARCH_CLOTHES = "/save:tol:plant-search"
        val QUEST_CUTSCENE = "/save:tol:cutscene-start"
        val TOWER_DOORS = Scenery.TOWER_DOOR_21814
        val PLANTS = Scenery.PLANT_21924

        val PLANTS_SEARCH = 5819 // sendMessage(player, "Nope, nothing here.")
        val BUILDER_BECKON_EMOTE = 5845 // sendMessage(player, "Try the beckon emote while wearing an item of builders' clothing!")
    }

    override fun defineListeners() {

        on(TOWER_DOORS, IntType.SCENERY, "open"){ player, node ->
            if (getQuestStage(player, "Tower Of Life") >= 2) {
                DoorActionHandler.handleAutowalkDoor(player, node.asScenery())
                setAttribute(player, ENTER_TOL, 2)
                when (player.location.y) {
                    3225 -> runTask(player, 3) {
                        sendPlayerDialogue(player, "Wow, this place looks special. Best I look around for something to fix.")
                    }
                }
            } else {
                sendMessage(player, "The doors appear to be stuck.")
            }
            return@on true
        }

        on(PLANTS, IntType.SCENERY, "search") { player, _ ->
            if (getQuestStage(player, "Tower Of Life") >= 2) {
                sendDialogue(player, "Aha!, You find some trousers!")
                sendMessage(player, "Try the beckon emote while wearing an item of builders' clothing!")
                setAttribute(player, SEARCH_CLOTHES, true)
            } else {
                sendMessage(player, "You search the plant...")
                runTask(player, 1) {
                    sendMessage(player, "Nope, nothing here.")
                }
            }
            return@on true
        }

        //      0 -> player("Why does nobody listen?").also { stage++ }
        //		1 -> player("Best I follow them, I suppose.").also { stage = END_DIALOGUE }

    }
}

