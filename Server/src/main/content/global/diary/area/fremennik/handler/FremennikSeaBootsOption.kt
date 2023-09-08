package content.global.diary.area.fremennik.handler

import config.Items
import config.NPCs
import content.global.travel.LyreTeleport
import core.api.*
import core.cache.ServerStore.Companion.getBoolean
import core.game.dialogue.DialogueFile
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC

/**
 * Represents dialogue options for Fremennik sea boots.
 */
class FremennikSeaBootsOption : DialogueFile() {

    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.FOSSEGRIMEN_1273)
        val lyre = intArrayOf(Items.ENCHANTED_LYRE1_3691, Items.ENCHANTED_LYRE2_6125, Items.ENCHANTED_LYRE3_6126,
            Items.ENCHANTED_LYRE4_6127, Items.ENCHANTED_LYRE5_14590, Items.ENCHANTED_LYRE6_14591)
        when (stage) {
            0 -> {
                if (inEquipmentOrInventory(player!!, Items.FREMENNIK_SEA_BOOTS_1_14571)) {
                    setComponentVisibility(player!!, 230, 7, true)
                    setComponentVisibility(player!!, 230, 10, false)
                    sendDialogueOptions(player!!, "What would you like to do?", "Contact the Fossegrimen.", "Explain benefits.", "Cancel.")
                    stage++
                } else if (inEquipmentOrInventory(player!!, Items.FREMENNIK_SEA_BOOTS_2_14572)) {
                    setComponentVisibility(player!!, 232, 8, true)
                    setComponentVisibility(player!!, 232, 9, false)
                    sendDialogueOptions(player!!, "What would you like to do?", "Contact the Fossegrimen.", "Free lyre teleport.", "Explain benefits.", "Cancel.")
                    stage++
                } else if (inEquipmentOrInventory(player!!, Items.FREMENNIK_SEA_BOOTS_3_14573)) {
                    setComponentVisibility(player!!, 234, 9, true)
                    setComponentVisibility(player!!, 234, 10, false)
                    sendDialogueOptions(player!!, "What would you like to do?", "Contact the Fossegrimen.", "Free lyre teleport.", "Lyre teleport destination.", "Explain benefits.", "Cancel.")
                    stage++
                }
            }

            1 -> when (buttonID) {
                1 -> {
                    npc(FacialExpression.FRIENDLY, "Good day, ${player!!.username}. Do you want to make an", "offering?").also { stage = 4 }
                }

                2 -> {
                    if (inEquipmentOrInventory(player!!, Items.FREMENNIK_SEA_BOOTS_1_14571)) {
                        interpreter!!.sendItemMessage(Items.FREMENNIK_SEA_BOOTS_1_14571, "", "", "Your Fremennik sea boots will bring you certain", "benefits within the Fremennik area.", "").also { stage = 7 }
                        //sendItemDialogue(player!!, Items.FREMENNIK_SEA_BOOTS_1_14571, "Your Fremennik sea boots will bring you certain benefits within the Fremennik area.").also { stage = 7 }
                    } else if (inEquipmentOrInventory(player!!, Items.FREMENNIK_SEA_BOOTS_2_14572) || inEquipmentOrInventory(player!!, Items.FREMENNIK_SEA_BOOTS_3_14573)) {
                        interpreter!!.sendItemMessage(Items.ENCHANTED_LYRE1_3691, "", "", "You may use lyre once per day without draining", "any of its enchantment.", "",).also { stage = 8 }
                        //sendItemDialogue(player!!, Items.ENCHANTED_LYRE1_3691, "You may use lyre once per day without draining any of its enchantment.").also { stage = 8 }
                    }
                }

                3 -> {
                    if (inEquipmentOrInventory(player!!, Items.FREMENNIK_SEA_BOOTS_1_14571)) {
                        end()
                    } else if (inEquipmentOrInventory(player!!, Items.FREMENNIK_SEA_BOOTS_2_14572)) {
                        interpreter!!.sendItemMessage(Items.FREMENNIK_SEA_BOOTS_2_14572, "", "", "Your Fremennik sea boots will bring you certain", "benefits within the Fremennik area.", "").also { stage = 10 }
                        //sendItemDialogue(player!!, Items.FREMENNIK_SEA_BOOTS_2_14572, "Your Fremennik sea boots will bring you certain benefits within the Fremennik area.").also { stage = 10 }
                    } else if (inEquipmentOrInventory(player!!, Items.FREMENNIK_SEA_BOOTS_3_14573)) {
                        sendItemDialogue(player!!, Items.FREMENNIK_SEA_BOOTS_3_14573, "Your lyre currently teleports you to Rellekka.").also { stage = 13 }
                    }
                }

                4 -> {
                    if (inEquipmentOrInventory(player!!, Items.FREMENNIK_SEA_BOOTS_2_14572)) {
                        end()
                    } else if (inEquipmentOrInventory(player!!, Items.FREMENNIK_SEA_BOOTS_3_14573)) {
                        interpreter!!.sendItemMessage(Items.FREMENNIK_SEA_BOOTS_3_14573, "", "", "Your Fremennik sea boots will bring you certain", "benefits within the Fremennik area.", "").also { stage = 15 }
                        //sendItemDialogue(player!!, Items.FREMENNIK_SEA_BOOTS_3_14573, "Your Fremennik sea boots will bring you certain benefits within the Fremennik area.").also { stage = 15 }
                    }
                }

                5 -> {
                    if (inEquipmentOrInventory(player!!, Items.FREMENNIK_SEA_BOOTS_3_14573)) {
                        end()
                    }
                }
            }

            4 -> {
                npc(FacialExpression.NEUTRAL, "Remember that you will gain a greater enchantment", "from offerings if you bring them to my altar.").also { stage++ }
            }

            5 -> {
                setComponentVisibility(player!!, 228, 6, true)
                setComponentVisibility(player!!, 228, 9, false)
                sendDialogueOptions(player!!, "What do you offer?", "A raw shark.", "Nothing at the moment.")
                stage++
            }

            6 -> when (buttonID) {
                1 -> {
                    if (hasAnItem(player!!, *lyre).container == player!!.inventory) {
                        sendDialogue(player!!, "All lyre charges must be used up before it will allow a charge to the lyre.").also { stage = 0 }
                    } else if (inInventory(player!!, Items.RAW_SHARK_383)
                        && hasAnItem(player!!, Items.ENCHANTED_LYRE_3690).container == player!!.inventory
                        && removeItem(player!!, Items.ENCHANTED_LYRE_3690, Container.INVENTORY)
                        && removeItem(player!!, Items.RAW_SHARK_383, Container.INVENTORY))
                    {
                        npc(FacialExpression.FRIENDLY, "I offer you this enchantment for your worthy offering.")
                        addItemOrDrop(player!!, Items.ENCHANTED_LYRE1_3691, 1)
                        stage = 0
                    } else {
                        sendDialogue(player!!, "You don't have required items in your inventory.").also { stage = 0 }
                    }
                }
                2 -> {
                    end()
                    stage = 0
                }
            }

            7 -> {
                interpreter!!.sendItemMessage(Items.FREMENNIK_SEA_BOOTS_1_14571, "", "If you speak to Peer the Seer, he will deposit items into", "your bank. The Fossegrimen's enchantment will give", "your lyre extra charges, if you make her an offering in", "person.").also { stage = 0 }
                //sendItemDialogue(player!!, Items.FREMENNIK_SEA_BOOTS_1_14571, "If you speak to Peer the Seer, he will deposit items into your bank. The Fossegrimen's enchantment will give your lyre extra charges, if you make her an offering in person.").also { stage = 0 }
            }
            8 -> {
                setComponentVisibility(player!!, 228, 6, true)
                setComponentVisibility(player!!, 228, 9, false)
                sendDialogueOptions(player!!, "Do this now?", "Yes.", "No.")
                stage = 9
            }

            9 -> when (buttonID) {
                1 -> {
                    if (LyreTeleport.getLyreTeleportFile().getBoolean(player!!.username.toLowerCase())) {
                        sendDialogue(player!!, "This can only be done once per day.")
                        stage = 0
                    } else {
                        end()
                        LyreTeleport.teleport(player!!)
                    }
                }
                2 -> {
                    end()
                    stage = 0
                }
            }

            10 -> {
                interpreter!!.sendItemMessage(Items.FREMENNIK_SEA_BOOTS_2_14572, "", "If you speak to Peer the Seer, he will deposit items into", "your bank. The Fossegrimen's enchantment will give", "your lyre extra charges, if you make her an offering in", "person.").also { stage++ }
                //sendItemDialogue(player!!, Items.FREMENNIK_SEA_BOOTS_2_14572, "If you speak to Peer the Seer, he will deposit items into your bank. The Fossegrimen's enchantment will give your lyre extra charges, if you make her an offering in person.").also { stage++ }
            }
            11 -> {
                interpreter!!.sendItemMessage(Items.FREMENNIK_SEA_BOOTS_2_14572, "", "As Regent of Miscellania, the people will appreciate your", "efforts more and your approval rating will increase", "faster. There is also a broken section of pier on", "Miscellania that you can use to quickly travel between").also { stage++ }
                //sendItemDialogue(player!!, Items.FREMENNIK_SEA_BOOTS_2_14572, "As Regent of Miscellania, the people will appreciate your efforts more and your approval rating will increase faster. There is also a broken section of pier on Miscellania that you can use to quickly travel between").also { stage++ }
            }
            12 -> {
                sendItemDialogue(player!!, Items.FREMENNIK_SEA_BOOTS_2_14572, "there and Etceteria.").also { stage = 0 }
            }
            13 -> {
                setComponentVisibility(player!!, 228, 6, true)
                setComponentVisibility(player!!, 228, 9, false)
                sendDialogueOptions(player!!, "Choose a destination:", "Rellekka", "Waterbirth Island", "Don't change.", ).also { stage++ }
            }

            14 -> when (buttonID) {
                1 -> sendItemDialogue(player!!, Items.FREMENNIK_SEA_BOOTS_3_14573, "Remember that you must be wearing your Fremennik sea boots if you want to teleport to an alternative location.").also { stage = 0 }
                2 -> sendItemDialogue(player!!, Items.FREMENNIK_SEA_BOOTS_3_14573, "Remember that you must be wearing your Fremennik sea boots if you want to teleport to an alternative location.").also { stage = 0 }
                3 -> {
                    end()
                    stage = 0
                }
            }

            15 -> {
                interpreter!!.sendItemMessage(Items.FREMENNIK_SEA_BOOTS_3_14573, "", "If you speak to Peer the Seer, he will deposit items into", "your bank. The Fossegrimen's enchantment will give", "your lyre extra charges, if you make her an offering in", "person.").also { stage++ }
                //sendItemDialogue(player!!, Items.FREMENNIK_SEA_BOOTS_3_14573, "If you speak to Peer the Seer, he will deposit items into your bank. The Fossegrimen's enchantment will give your lyre extra charges, if you make her an offering in person.").also { stage++ }
            }
            16 -> {
                interpreter!!.sendItemMessage(Items.FREMENNIK_SEA_BOOTS_3_14573, "", "As Regent of Miscellania, the people will appreciate your", "efforts more and your approval rating will increase", "faster. There is also a broken section of pier on", "Miscellania that you can use to quickly travel between").also { stage++ }
                //sendItemDialogue(player!!, Items.FREMENNIK_SEA_BOOTS_3_14573, "As Regent of Miscellania, the people will appreciate your efforts more and your approval rating will increase faster. There is also a broken section of pier on Miscellania that you can use to quickly travel between").also { stage++ }
            }
            17 -> {
                sendItemDialogue(player!!, Items.FREMENNIK_SEA_BOOTS_3_14573, "there and Etceteria.").also { stage++ }
            }
            18 -> {
                interpreter!!.sendItemMessage(Items.FREMENNIK_SEA_BOOTS_3_14573, "", "Advisor Ghrim will accept flat-packed furniture as a", "contribution to the coffers of Miscellania.").also { stage = 0 }
                //sendItemDialogue(player!!, Items.FREMENNIK_SEA_BOOTS_3_14573, "Advisor Ghrim will accept flat-packed furniture as a contribution to the coffers of Miscellania.").also { stage = 0 }
            }
        }
    }
}
