package content.quest.member.horrorfromthedeep.handlers

import config.Items
import config.NPCs
import config.Scenery
import content.quest.member.horrorfromthedeep.HFTDUtils
import core.api.*
import core.game.dialogue.DialogueFile
import core.game.global.action.DoorActionHandler
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.system.task.Pulse
import core.game.world.map.Location

/**
 * Listener for interactions inside Lighthouse.
 */
class LightHouseListener : InteractionListener {
    override fun defineListeners() {
        // Door leading to the lighthouse.
        on(HFTDUtils.LIGHTHOUSE_FRONT_DOORS, IntType.SCENERY, "walk-through") { player, node ->
            if (isQuestComplete(player, "Horror from the Deep")) {
                DoorActionHandler.handleAutowalkDoor(player, node.asScenery())
            } else if (getQuestStage(player, "Horror from the Deep") >= 20) {
                submitIndividualPulse(player, object : Pulse(2) {
                    override fun pulse(): Boolean {
                        if (inBorders(player, 2508, 3634, 2510, 3635)) {
                            sendMessage(player, "You unlock the Lighthouse front door.")
                            DoorActionHandler.handleAutowalkDoor(player, node.asScenery()).also {
                                runTask(player, 3) {
                                    teleport(player, location(2445, 4596, 0))
                                }
                            }
                        } else {
                            DoorActionHandler.handleAutowalkDoor(player, node.asScenery()).also {
                                runTask(player, 3) {
                                    teleport(player, location(2509, 3635, 0))
                                }
                            }
                        }
                        return true
                    }
                })
            } else if (getQuestStage(player, "Horror from the Deep") >= 10) {
                sendNPCDialogue(player, NPCs.LARRISSA_1336, "Please adventurer... We are both curious as to what has happened in that lighthouse, but you need to fix the bridge for me!")
            } else {
                sendDialogue(player, "You can't see any way to open the door.")
            }
            return@on true
        }

        // Bookcase on 1st floor.
        class BookcaseDialogue : DialogueFile(){
            override fun handle(componentID: Int, buttonID: Int) {
                when (stage) {
                    0 -> if(isQuestComplete(player!!, "Horror from the Deep")){
                        sendDialogue(player!!, "You have completed the Horror from the Deep quest. You probably don't need this book.")
                    } else {
                        sendDialogue(player!!, "There are three books here that look important... What would you like to do?").also { stage++ }
                    }
                    1 -> options("Take the Lighthouse Manual", "Take the ancient Diary", "Take Jossik's Journal", "Take all three books").also { stage++ }
                    2 -> when (buttonID) {
                        1 -> if (freeSlots(player!!) < 1) {
                            end()
                            sendDialogue(player!!, "You do not have enough room to take that.")
                        } else {
                            end()
                            addItemOrDrop(player!!, Items.MANUAL_3847)
                        }
                        2 -> if (freeSlots(player!!) < 1) {
                            end()
                            sendDialogue(player!!, "You do not have enough room to take that.")
                        } else {
                            end()
                            addItemOrDrop(player!!, Items.DIARY_3846)
                        }
                        3 -> if (freeSlots(player!!) < 1) {
                            end()
                            sendDialogue(player!!, "You do not have enough room to take that.")
                        } else {
                            end()
                            addItemOrDrop(player!!, Items.JOURNAL_3845)
                        }
                        4 -> if (freeSlots(player!!) < 3) {
                            end()
                            sendDialogue(player!!, "You do not have enough room to take all three.")
                        } else {
                            end()
                            addItemOrDrop(player!!, Items.JOURNAL_3845)
                            addItemOrDrop(player!!, Items.DIARY_3846)
                            addItemOrDrop(player!!, Items.MANUAL_3847)
                        }
                    }
                }
            }
        }
        on(Scenery.BOOKCASE_4617, IntType.SCENERY, "search"){ player, node ->
            openDialogue(player, BookcaseDialogue(), node.asScenery())
            return@on true
        }

        // Metal ladders to the basement.
        // Climb up -> HORROR_FROM_THE_DEEP_BASEMENT_UP in SpecialLadders.java

        on(Scenery.IRON_LADDER_4383, IntType.SCENERY, "climb"){ player, _ ->
            if(getQuestStage(player, "Horror from the Deep") == 100) {
                sendDialogue(player, "I have no reason to go down there.")
                return@on false
            } else if (getQuestStage(player, "Horror from the Deep") >= 30) {
                teleport(player, location(2519, 4618, 1))
                setAttribute(player, HFTDUtils.PUZZLE_PROGRESS, 0)
            } else {
                sendNPCDialogue(player, NPCs.LARRISSA_1336, "Please adventurer, do not let curiosity get the better of you! We have to fix the lighthouse before any accidents happen!")
            }
            return@on true
        }

        // Broken Lighthouse Mechanism.
        onUseWith(IntType.SCENERY, Items.SWAMP_TAR_1939, Scenery.LIGHTING_MECHANISM_4588){ player, _, _ ->
            sendMessage(player, "You use the swamp tar to make the torch flammable again.")
            removeItem(player, Items.SWAMP_TAR_1939)
            setAttribute(player, HFTDUtils.FIX_LIGHTHOUSE_MECHANISM, 1)
            return@onUseWith true
        }

        onUseWith(IntType.SCENERY, Items.TINDERBOX_590, Scenery.LIGHTING_MECHANISM_4588){ player, _, _ ->
            sendMessage(player, "You light the torch with your tinderbox.")
            player.incrementAttribute(HFTDUtils.FIX_LIGHTHOUSE_MECHANISM)
            return@onUseWith true
        }

        onUseWith(IntType.SCENERY, Items.MOLTEN_GLASS_1775, Scenery.LIGHTING_MECHANISM_4588){ player, _, _ ->
            if (getAttribute(player, HFTDUtils.FIX_LIGHTHOUSE_MECHANISM, 0) == 2 && removeItem(player, Items.MOLTEN_GLASS_1775)) {
                sendMessage(player, "You use the molten glass to repair the lens.")
                sendMessage(player, "You have managed to repair the lighthouse torch!")
                setQuestStage(player, "Horror from the Deep", 40)
                //removeScenery(core.game.node.scenery.Scenery(4588, Location(2443, 4599, 2)))
                //addScenery(Scenery.LIGHTING_MECHANISM_4587, Location(2445, 4601, 2))
            } else {
                sendMessage(player, "Nothing interesting happens.")
            }
            return@onUseWith true
        }
    }

}