package content.global.travel.balloon

import config.Components
import config.Items
import core.api.*
import core.game.interaction.InterfaceListener
import core.game.node.entity.skill.Skills
import core.game.node.item.Item

/**
 * Represents the Balloon transport system map interface.
 */
class BalloonInterface : InterfaceListener {

    private val balloonInterface = Components.ZEP_BALLOON_MAP_469

    private val castleWarsButton = 14
    private val grandTreeButton = 15
    private val craftingGuildButton = 16
    private val entranaButton = 17
    private val taverleyButton = 18
    private val varrockButton = 19

    override fun defineInterfaceListeners() {
        on(balloonInterface) { player, _, _, buttonID, _, _ ->

            // sendDialogue(player, "You can only open new locations from Entrana.")

            when(buttonID){

                // ENTRANA
                entranaButton -> {
                    if(!hasLevelDyn(player, Skills.FIREMAKING, 20)) {
                        sendDialogue(player, "You require a firemaking level of 20 to travel at Entrana.")
                    } else if(!removeItem(player, Item(Items.LOGS_1511,1))) {
                        sendDialogue(player, "You don't have required logs in your inventory.")
                    } else {
                        BalloonTravel.handleFlight(player, FlightDestination.ENTRANA)
                        sendMessage(player, "You board the balloon and fly to Entrana.")
                        runTask(player, 4) {
                            sendMessage(player, "You arrive safely in Entrana.")
                        }
                    }
                }

                // TAVERLEY
                taverleyButton -> {
                    if(!hasLevelDyn(player, Skills.FIREMAKING, 20)) {
                        sendDialogue(player, "You require a firemaking level of 20 to travel to Taverley.")
                    } else if(!removeItem(player, Item(Items.LOGS_1511,1))) {
                        sendDialogue(player, "You don't have required logs in your inventory.")
                    } else {
                        BalloonTravel.handleFlight(player, FlightDestination.TAVERLEY)
                        sendMessage(player, "You board the balloon and fly to Taverley.")
                        runTask(player, 4) {
                            sendMessage(player, "You arrive safely in Taverley.")
                        }
                    }
                }

                // CRAFTING GUILD
                craftingGuildButton -> {
                    if(!hasLevelDyn(player, Skills.FIREMAKING, 30)) {
                        sendDialogue(player, "You require a firemaking level of 30 to travel at Crafting guild.")
                    } else if(!removeItem(player, Item(Items.OAK_LOGS_1521,1))) {
                        sendDialogue(player, "You don't have required logs in your inventory.")
                    } else {
                        BalloonTravel.handleFlight(player, FlightDestination.CRAFT_GUILD)
                        sendMessage(player, "You board the balloon and fly to Crafting guild.")
                        runTask(player, 4) {
                            sendMessage(player, "You arrive safely in Crafting guild.")
                        }
                    }
                }

                // VARROCK
                varrockButton -> {
                    if(!hasLevelDyn(player, Skills.FIREMAKING, 40)) {
                        sendDialogue(player, "You require a firemaking level of 40 to travel to Varrock.")
                    } else if(!removeItem(player, Item(Items.WILLOW_LOGS_1519,1))) {
                        sendDialogue(player, "You don't have required logs in your inventory.")
                    } else {
                        BalloonTravel.handleFlight(player, FlightDestination.VARROCK)
                        sendMessage(player, "You board the balloon and fly to Varrock.")
                        runTask(player, 4) {
                            sendMessage(player, "You arrive safely in Varrock.")
                        }
                    }
                }

                // CASTLE WARS
                castleWarsButton -> {
                    if(!hasLevelDyn(player, Skills.FIREMAKING, 50)) {
                        sendDialogue(player, "You require a firemaking level of 50 to travel at Castle Wars.")
                    } else if(!removeItem(player, Item(Items.YEW_LOGS_1515,1))) {
                        sendDialogue(player, "You don't have required logs in your inventory.")
                    } else {
                        BalloonTravel.handleFlight(player, FlightDestination.CASTLE_WARS)
                        sendMessage(player, "You board the balloon and fly to Castle Wars.")
                        runTask(player, 4) {
                            sendMessage(player, "You arrive safely in Castle Wars.")
                        }
                    }
                }

                // GRAND TREE
                grandTreeButton -> {
                    if (!hasLevelDyn(player, Skills.FIREMAKING, 60)) {
                        sendDialogue(player, "You require a firemaking level of 60 to travel at Grand Tree.")
                    } else if (!removeItem(player, Item(Items.MAGIC_LOGS_1513, 1))) {
                        sendDialogue(player, "You don't have required logs in your inventory.")
                    } else {
                        BalloonTravel.handleFlight(player, FlightDestination.GRAND_TREE)
                        sendMessage(player, "You board the balloon and fly to Grand Tree.")
                        runTask(player, 4) {
                            sendMessage(player, "You arrive safely in Grand Tree.")
                        }
                    }
                }
            }

            return@on true
        }
    }

}