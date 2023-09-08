package content.global.handlers.iface

import config.Components
import config.Items
import core.api.addItemOrDrop
import core.api.inInventory
import core.api.removeItem
import core.api.sendDialogue
import core.game.interaction.InterfaceListener
import core.game.node.item.Item

/**
 * Handles the sewing interface.
 */
class SewingInterface : InterfaceListener {

    private val sewInterface = Components.SEW_INTERFACE_419
    override fun defineInterfaceListeners() {

        // https://www.youtube.com/watch?v=lQ8PgikBU68&ab_channel=Jamalieboy10

        onOpen(sewInterface) { player, component ->
            /**
             * White set of pirate bandana's.
             */
            // White right-eye
            if (inInventory(player, Items.PIRATE_BANDANA_7112, 1) && (inInventory(player, Items.EYE_PATCH_1025, 1))) {
                player.packetDispatch.sendInterfaceConfig(419, 4, false)
            } else {
                player.packetDispatch.sendInterfaceConfig(419, 4, true)
            }
            // White double-eye
            if (inInventory(player, Items.PIRATE_BANDANA_7112, 1) && (inInventory(player, Items.DOUBLE_EYEPATCHES_13353, 1))) {
                player.packetDispatch.sendInterfaceConfig(419, 18, false)
            } else {
                player.packetDispatch.sendInterfaceConfig(419, 18, true)
            }
            // White left-eye
            if (inInventory(player, Items.PIRATE_BANDANA_7112, 1) && (inInventory(player, Items.LEFT_EYEPATCH_13355, 1)) ) {
                player.packetDispatch.sendInterfaceConfig(419, 19, false)
            } else {
                player.packetDispatch.sendInterfaceConfig(419, 19, true)
            }

            //---------------------------------------------------------------------------------//

            /**
             * Red set of pirate bandana's.
             */

            // Red right-eye
            if (inInventory(player, Items.PIRATE_BANDANA_7124, 1) && (inInventory(player, Items.EYE_PATCH_1025, 1))) {
                player.packetDispatch.sendInterfaceConfig(419, 5, false)
            } else {
                player.packetDispatch.sendInterfaceConfig(419, 5, true)
            }
            // Red double-eye
            if (inInventory(player, Items.PIRATE_BANDANA_7124, 1) && (inInventory(player, Items.DOUBLE_EYEPATCHES_13353, 1))) {
                player.packetDispatch.sendInterfaceConfig(419, 15, false)
            } else {
                player.packetDispatch.sendInterfaceConfig(419, 15, true)
            }
            // Red left
            if (inInventory(player, Items.PIRATE_BANDANA_7124, 1) && (inInventory(player, Items.LEFT_EYEPATCH_13355, 1))) {
                player.packetDispatch.sendInterfaceConfig(419, 20, false)
            } else {
                player.packetDispatch.sendInterfaceConfig(419, 20, true)
            }

            //---------------------------------------------------------------------------------//

            /**
             * Blue set of pirate bandana's.
             */

            // blue right-eye
            if (inInventory(player, Items.PIRATE_BANDANA_7130, 1) && (inInventory(player, Items.EYE_PATCH_1025, 1))) {
                player.packetDispatch.sendInterfaceConfig(419, 6, false)
            } else {
                player.packetDispatch.sendInterfaceConfig(419, 6, true)
            }
            // Blue double-eye
            if (inInventory(player, Items.PIRATE_BANDANA_7130, 1) && (inInventory(player, Items.DOUBLE_EYEPATCHES_13353, 1))) {
                player.packetDispatch.sendInterfaceConfig(419, 17, false)
            } else {
                player.packetDispatch.sendInterfaceConfig(419, 17, true)
            }
            // Blue left-eye
            if (inInventory(player, Items.PIRATE_BANDANA_7130, 1) && (inInventory(player, Items.LEFT_EYEPATCH_13355, 1))) {
                player.packetDispatch.sendInterfaceConfig(419, 21, false)
            } else {
                player.packetDispatch.sendInterfaceConfig(419, 21, true)
            }

            //---------------------------------------------------------------------------------//

            /**
             * Brown set of pirate bandana's.
             */

            // Brown right-eye
            if (inInventory(player, Items.PIRATE_BANDANA_7136, 1) && (inInventory(player, Items.EYE_PATCH_1025, 1))) {
                player.packetDispatch.sendInterfaceConfig(419, 7, false)
            } else {
                player.packetDispatch.sendInterfaceConfig(419, 7, true)
            }
            // Brown double-eye
            if (inInventory(player, Items.PIRATE_BANDANA_7136, 1) && (inInventory(player, Items.DOUBLE_EYEPATCHES_13353, 1))) {
                player.packetDispatch.sendInterfaceConfig(419, 16, false)
            } else {
                player.packetDispatch.sendInterfaceConfig(419, 16, true)
            }
            // Brown left-eye
            if (inInventory(player, Items.PIRATE_BANDANA_7136, 1) && (inInventory(player, Items.LEFT_EYEPATCH_13355, 1))) {
                player.packetDispatch.sendInterfaceConfig(419, 22, false)
            } else {
                player.packetDispatch.sendInterfaceConfig(419, 22, true)
            }

            //---------------------------------------------------------------------------------//

            /**
             * Grey set of pirate bandana's.
             */

            // Grey right-eye
            if (inInventory(player, Items.BANDANA_13370, 1) && (inInventory(player, Items.EYE_PATCH_1025, 1))) {
                player.packetDispatch.sendInterfaceConfig(419, 8, false)
            } else {
                player.packetDispatch.sendInterfaceConfig(419, 8, true)
            }
            // Grey double-eye
            if (inInventory(player, Items.BANDANA_13370, 1) && (inInventory(player, Items.DOUBLE_EYEPATCHES_13353, 1))) {
                player.packetDispatch.sendInterfaceConfig(419, 11, false)
            } else {
                player.packetDispatch.sendInterfaceConfig(419, 11, true)
            }
            // Gray left-eye
            if (inInventory(player, Items.BANDANA_13370, 1) && (inInventory(player, Items.LEFT_EYEPATCH_13355, 1))) {
                player.packetDispatch.sendInterfaceConfig(419, 23, false)
            } else {
                player.packetDispatch.sendInterfaceConfig(419, 23, true)
            }

            //---------------------------------------------------------------------------------//

            /**
             * Purple set of pirate bandana's.
             */

            // Purple right-eye
            if (inInventory(player, Items.BANDANA_13372, 1) && (inInventory(player, Items.EYE_PATCH_1025, 1))) {
                player.packetDispatch.sendInterfaceConfig(419, 9, false)
            } else {
                player.packetDispatch.sendInterfaceConfig(419, 9, true)
            }
            // Purple double-eye
            if (inInventory(player, Items.BANDANA_13372, 1) && (inInventory(player, Items.DOUBLE_EYEPATCHES_13353, 1))) {
                player.packetDispatch.sendInterfaceConfig(419, 12, false)
            } else {
                player.packetDispatch.sendInterfaceConfig(419, 12, true)
            }
            // Purple left-eye
            if (inInventory(player, Items.BANDANA_13372, 1) && (inInventory(player, Items.LEFT_EYEPATCH_13355, 1))) {
                player.packetDispatch.sendInterfaceConfig(419, 24, false)
            } else {
                player.packetDispatch.sendInterfaceConfig(419, 24, true)
            }

            //---------------------------------------------------------------------------------//

            /**
             * Orange set of pirate bandana's.
             */

            // Orange right-eye
            if (inInventory(player, Items.BANDANA_13374, 1) && (inInventory(player, Items.EYE_PATCH_1025, 1))) {
                player.packetDispatch.sendInterfaceConfig(419, 10, false)
            } else {
                player.packetDispatch.sendInterfaceConfig(419, 10, true)
            }
            // Orange double-eye
            if (inInventory(player, Items.BANDANA_13374, 1) && (inInventory(player, Items.EYE_PATCH_1025, 1))) {
                player.packetDispatch.sendInterfaceConfig(419, 13, false)
            } else {
                player.packetDispatch.sendInterfaceConfig(419, 13, true)
            }
            // Orange left-eye
            if (inInventory(player, Items.BANDANA_13374, 1) && (inInventory(player, Items.LEFT_EYEPATCH_13355, 1))) {
                player.packetDispatch.sendInterfaceConfig(419, 25, false)
            } else {
                player.packetDispatch.sendInterfaceConfig(419, 25, true)
            }

            //---------------------------------------------------------------------------------//

            /**
             * Random set of pirate bandana's.
             */

            // Beret & mime mask
            if (inInventory(player, Items.MIME_MASK_3057, 1) && inInventory(player, Items.BLACK_BERET_2635, 1)) {
                player.packetDispatch.sendInterfaceConfig(419, 31, false)
            } else {
                player.packetDispatch.sendInterfaceConfig(419, 31, true)
            }

            /**
             * Pirate set of pirate bandana's.
             */

            // Pirate left-eye
            if (inInventory(player, Items.PIRATES_HAT_2651, 1)  && (inInventory(player, Items.LEFT_EYEPATCH_13355, 1))) {
                player.packetDispatch.sendInterfaceConfig(419, 27, false)
            } else {
                player.packetDispatch.sendInterfaceConfig(419, 27, true)
            }

            // Pirate hat double-eye
            if (inInventory(player, Items.PIRATES_HAT_2651, 1) && (inInventory(player, Items.DOUBLE_EYEPATCHES_13353, 1))) {
                player.packetDispatch.sendInterfaceConfig(419, 14, false)
            } else {
                player.packetDispatch.sendInterfaceConfig(419, 14, true)
            }

            // Pirate hat right-eye
            if (inInventory(player, Items.PIRATES_HAT_2651, 1) && (inInventory(player, Items.EYE_PATCH_1025, 1))) {
                player.packetDispatch.sendInterfaceConfig(419, 26, false)
            } else {
                player.packetDispatch.sendInterfaceConfig(419, 26, true)
            }

            //---------------------------------------------------------------------------------//

            /**
             * Random set of pirate bandana's.
             */

            // Double patch
            if (inInventory(player, Items.EYE_PATCH_1025, 1) && (inInventory(player, Items.LEFT_EYEPATCH_13355, 1))) {
                player.packetDispatch.sendInterfaceConfig(419, 28, false)
            } else {
                player.packetDispatch.sendInterfaceConfig(419, 28, true)
            }
            // Hook
            if (inInventory(player, Items.CRAB_CLAW_7537, 1) && (inInventory(player, Items.PIRATES_HOOK_2997, 1))) {
                player.packetDispatch.sendInterfaceConfig(419, 29, false)
            } else {
                player.packetDispatch.sendInterfaceConfig(419, 29, true)
            }
            // Highway mask
            if (inInventory(player, Items.BLACK_CAVALIER_2644, 1) || (inInventory(player, Items.BLACK_CAVALIER_10806, 1) && (inInventory(player, Items.HIGHWAYMAN_MASK_2631, 1)) || (inInventory(player, Items.HIGHWAYMAN_MASK_10692, 1)))) {
                player.packetDispatch.sendInterfaceConfig(419, 30, false)
            } else {
                player.packetDispatch.sendInterfaceConfig(419, 30, true)
            }
            return@onOpen true
        }


        /**
         * click-interaction
         */

        on(sewInterface) { player, _, _, buttonID, _, _ ->
            when (buttonID) {
                //---------------------------------------------------------------------------------//

                /**
                 * click-buy for white set.
                 */

                // white right-eye
                38 -> {
                    if (inInventory(player, Items.COINS_995, 500) &&
                        inInventory(player, Items.PIRATE_BANDANA_7112, 1) &&
                        inInventory(player, Items.EYE_PATCH_1025, 1)
                        && removeItem(player, Item(Items.COINS_995, 500))
                        && removeItem(player, Item(Items.PIRATE_BANDANA_7112, 1))
                        && removeItem(player, Item(Items.EYE_PATCH_1025, 1))) {
                        addItemOrDrop(player, Items.BANDANA_AND_EYEPATCH_8924, 1)
                    } else {
                        sendDialogue(player, "You can not afford that.")
                    }
                }
                // white double-eye
                66,67 -> {
                    if (inInventory(player, Items.COINS_995, 500)
                        && inInventory(player, Items.PIRATE_BANDANA_7112, 1)
                        && inInventory(player, Items.DOUBLE_EYEPATCHES_13353, 1)
                        && removeItem(player, Item(Items.COINS_995, 500))
                        && removeItem(player, Item(Items.PIRATE_BANDANA_7112, 1))
                        && removeItem(player, Item(Items.DOUBLE_EYEPATCHES_13353, 1))) {
                        addItemOrDrop(player, Items.BANDANA_AND_EYEPATCHES_13340, 1)
                    } else {
                        sendDialogue(player, "You can not afford that.")
                    }
                }
                // white left-eye
                68,69 -> {
                    if (inInventory(player, Items.COINS_995, 500)
                        && inInventory(player, Items.PIRATE_BANDANA_7112, 1)
                        && inInventory(player, Items.LEFT_EYEPATCH_13355, 1)
                        && removeItem(player, Item(Items.COINS_995, 500))
                        && removeItem(player, Item(Items.PIRATE_BANDANA_7112, 1))
                        && removeItem(player, Item(Items.LEFT_EYEPATCH_13355, 1))) {
                        addItemOrDrop(player, Items.BANDANA_AND_EYEPATCH_13339, 1)
                    } else {
                        sendDialogue(player, "You can not afford that.")
                    }
                }
                //---------------------------------------------------------------------------------//

                /**
                 * click-buy for red set.
                 */

                // red right-eye
                40 -> {
                    if (inInventory(player, Items.COINS_995, 500)
                        && inInventory(player, Items.PIRATE_BANDANA_7124, 1)
                        && inInventory(player, Items.EYE_PATCH_1025, 1)
                        && removeItem(player, Item(Items.COINS_995, 500))
                        && removeItem(player, Item(Items.PIRATE_BANDANA_7124, 1))
                        && removeItem(player, Item(Items.EYE_PATCH_1025, 1))) {
                        addItemOrDrop(player, Items.BANDANA_AND_EYEPATCH_8925, 1)
                    } else {
                        sendDialogue(player, "You can not afford that.")
                    }
                }

                // red double-eye
                60,61 -> {
                    if (inInventory(player, Items.COINS_995, 500)
                        && inInventory(player, Items.PIRATE_BANDANA_7124, 1)
                        && inInventory(player, Items.DOUBLE_EYEPATCHES_13353, 1)
                        && removeItem(player, Item(Items.COINS_995, 500))
                        && removeItem(player, Item(Items.PIRATE_BANDANA_7124, 1))
                        && removeItem(player, Item(Items.DOUBLE_EYEPATCHES_13353, 1))) {
                        addItemOrDrop(player, Items.BANDANA_AND_EYEPATCHES_13342, 1)
                    } else {
                        sendDialogue(player, "You can not afford that.")
                    }
                }

                // red left-eye
                70,71 -> {
                    if (inInventory(player, Items.COINS_995, 500)
                        && inInventory(player, Items.PIRATE_BANDANA_7124, 1)
                        && inInventory(player, Items.LEFT_EYEPATCH_13355, 1)
                        && removeItem(player, Item(Items.COINS_995, 500))
                        && removeItem(player, Item(Items.PIRATE_BANDANA_7124, 1))
                        && removeItem(player, Item(Items.LEFT_EYEPATCH_13355, 1))) {
                        addItemOrDrop(player, Items.BANDANA_AND_EYEPATCH_13341, 1)
                    } else {
                        sendDialogue(player, "You can not afford that.")
                    }
                }

                //---------------------------------------------------------------------------------//

                /**
                 * click-buy for blue set.
                 */

                // blue right-eye
                42 -> {
                    if (inInventory(player, Items.COINS_995, 500)
                        && inInventory(player, Items.PIRATE_BANDANA_7130, 1)
                        && inInventory(player, Items.EYE_PATCH_1025, 1)
                        && removeItem(player, Item(Items.COINS_995, 500))
                        && removeItem(player, Item(Items.PIRATE_BANDANA_7130, 1))
                        && removeItem(player, Item(Items.EYE_PATCH_1025, 1))) {
                        addItemOrDrop(player, Items.BANDANA_AND_EYEPATCH_8926, 1)
                    } else {
                        sendDialogue(player, "You can not afford that.")
                    }
                }

                // blue double-eye
                64 -> {
                    if (inInventory(player, Items.COINS_995, 500)
                        && inInventory(player, Items.PIRATE_BANDANA_7130, 1)
                        && inInventory(player, Items.DOUBLE_EYEPATCHES_13353, 1)
                        && removeItem(player, Item(Items.COINS_995, 500))
                        && removeItem(player, Item(Items.PIRATE_BANDANA_7130, 1))
                        && removeItem(player, Item(Items.DOUBLE_EYEPATCHES_13353, 1))) {
                        addItemOrDrop(player, Items.BANDANA_AND_EYEPATCHES_13344, 1)
                    } else {
                        sendDialogue(player, "You can not afford that.")
                    }
                }

                // blue left-eye
                72 -> {
                    if (inInventory(player, Items.COINS_995, 500)
                        && inInventory(player, Items.PIRATE_BANDANA_7130, 1)
                        && inInventory(player, Items.LEFT_EYEPATCH_13355, 1)
                        && removeItem(player, Item(Items.COINS_995, 500))
                        && removeItem(player, Item(Items.PIRATE_BANDANA_7130, 1))
                        && removeItem(player, Item(Items.LEFT_EYEPATCH_13355, 1))) {
                        addItemOrDrop(player, Items.BANDANA_AND_EYEPATCH_13343, 1)
                    } else {
                        sendDialogue(player, "You can not afford that.")
                    }
                }

                //---------------------------------------------------------------------------------//

                /**
                 * click-buy for brown set.
                 */

                // brown right-eye
                44 -> {
                    if (inInventory(player, Items.COINS_995, 500)
                        && inInventory(player, Items.PIRATE_BANDANA_7136, 1)
                        && inInventory(player, Items.EYE_PATCH_1025, 1)
                        && removeItem(player, Item(Items.COINS_995, 500))
                        && removeItem(player, Item(Items.PIRATE_BANDANA_7136, 1))
                        && removeItem(player, Item(Items.EYE_PATCH_1025, 1))) {
                        addItemOrDrop(player, Items.BANDANA_AND_EYEPATCH_8927, 1)
                    } else {
                        sendDialogue(player, "You can not afford that.")
                    }
                }

                // brown double-eye
                62 -> {
                    if (inInventory(player, Items.COINS_995, 500)
                        && inInventory(player, Items.PIRATE_BANDANA_7136, 1)
                        && inInventory(player, Items.DOUBLE_EYEPATCHES_13353, 1)
                        && removeItem(player, Item(Items.COINS_995, 500))
                        && removeItem(player, Item(Items.PIRATE_BANDANA_7136, 1))
                        && removeItem(player, Item(Items.DOUBLE_EYEPATCHES_13353, 1))) {
                        addItemOrDrop(player, Items.BANDANA_AND_EYEPATCHES_13346, 1)
                    } else {
                        sendDialogue(player, "You can not afford that.")
                    }
                }

                // brown left-eye
                74 -> {
                    if (inInventory(player, Items.COINS_995, 500)
                        && inInventory(player, Items.PIRATE_BANDANA_7136, 1)
                        && inInventory(player, Items.LEFT_EYEPATCH_13355, 1)
                        && removeItem(player, Item(Items.COINS_995, 500))
                        && removeItem(player, Item(Items.PIRATE_BANDANA_7136, 1))
                        && removeItem(player, Item(Items.LEFT_EYEPATCH_13355, 1))) {
                        addItemOrDrop(player, Items.BANDANA_AND_EYEPATCH_13345, 1)
                    } else {
                        sendDialogue(player, "You can not afford that.")
                    }
                }
                //---------------------------------------------------------------------------------//

                /**
                 * click-buy for grey set.
                 */

                // grey right-eye
                46 -> {
                    if (inInventory(player, Items.COINS_995, 500)
                        && inInventory(player, Items.BANDANA_13370, 1)
                        && inInventory(player, Items.EYE_PATCH_1025, 1)
                        && removeItem(player, Item(Items.COINS_995, 500))
                        && removeItem(player, Item(Items.BANDANA_13370, 1))
                        && removeItem(player, Item(Items.EYE_PATCH_1025, 1))) {
                        addItemOrDrop(player, Items.BANDANA_AND_EYEPATCH_13378, 1)
                    } else {
                        sendDialogue(player, "You can not afford that.")
                    }
                }

                // grey left-eye
                76,77 -> {
                    if (inInventory(player, Items.COINS_995, 500)
                        && inInventory(player, Items.BANDANA_13370, 1)
                        && inInventory(player, Items.LEFT_EYEPATCH_13355, 1)
                        && removeItem(player, Item(Items.COINS_995, 500))
                        && removeItem(player, Item(Items.BANDANA_13370, 1))
                        && removeItem(player, Item(Items.LEFT_EYEPATCH_13355, 1))) {
                        addItemOrDrop(player, Items.BANDANA_AND_EYEPATCH_13347, 1)
                    } else {
                        sendDialogue(player, "You can not afford that.")
                    }
                }

                // grey double-eye
                52 -> {
                    if (inInventory(player, Items.COINS_995, 500)
                        && inInventory(player, Items.BANDANA_13370, 1)
                        && inInventory(player, Items.DOUBLE_EYEPATCHES_13353, 1)
                        && removeItem(player, Item(Items.COINS_995, 500))
                        && removeItem(player, Item(Items.BANDANA_13370, 1))
                        && removeItem(player, Item(Items.DOUBLE_EYEPATCHES_13353, 1))) {
                        addItemOrDrop(player, Items.BANDANA_AND_EYEPATCHES_13348, 1)
                    } else {
                        sendDialogue(player, "You can not afford that.")

                    }
                }

                //---------------------------------------------------------------------------------//

                /**
                 * click-buy for purple set.
                 */

                // purple right-eye
                48 -> {
                    if (inInventory(player, Items.COINS_995, 500)
                        && inInventory(player, Items.BANDANA_13372, 1)
                        && inInventory(player, Items.EYE_PATCH_1025, 1)
                        && removeItem(player, Item(Items.COINS_995, 500))
                        && removeItem(player, Item(Items.BANDANA_13372, 1))
                        && removeItem(player, Item(Items.EYE_PATCH_1025, 1))) {
                        addItemOrDrop(player, Items.BANDANA_AND_EYEPATCH_13376, 1)
                    } else {
                        sendDialogue(player, "You can not afford that.")
                    }
                }

                // purple left-eye
                78 -> {
                    if (inInventory(player, Items.COINS_995, 500)
                        && inInventory(player, Items.BANDANA_13372, 1)
                        && inInventory(player, Items.LEFT_EYEPATCH_13355, 1)
                        && removeItem(player, Item(Items.COINS_995, 500))
                        && removeItem(player, Item(Items.BANDANA_13372, 1))
                        && removeItem(player, Item(Items.LEFT_EYEPATCH_13355, 1))) {
                        addItemOrDrop(player, Items.BANDANA_AND_EYEPATCH_13349, 1)
                    } else {
                        sendDialogue(player, "You can not afford that.")
                    }
                }

                // purple double-eye
                54 -> {
                    if (inInventory(player, Items.COINS_995, 500)
                        && inInventory(player, Items.BANDANA_13372, 1)
                        && inInventory(player, Items.DOUBLE_EYEPATCHES_13353, 1)
                        && removeItem(player, Item(Items.COINS_995, 500))
                        && removeItem(player, Item(Items.BANDANA_13372, 1))
                        && removeItem(player, Item(Items.DOUBLE_EYEPATCHES_13353, 1))) {
                        addItemOrDrop(player, Items.BANDANA_AND_EYEPATCHES_13350, 1)
                    } else {
                        sendDialogue(player, "You can not afford that.")
                    }
                }

                //---------------------------------------------------------------------------------//

                /**
                 * click-buy for orange set.
                 */

                // orange right-eye
                50,51 -> {
                    if (inInventory(player, Items.COINS_995, 500)
                        && inInventory(player, Items.BANDANA_13374, 1)
                        && inInventory(player, Items.EYE_PATCH_1025, 1)
                        && removeItem(player, Item(Items.COINS_995, 500))
                        && removeItem(player, Item(Items.BANDANA_13374, 1))
                        && removeItem(player, Item(Items.EYE_PATCH_1025, 1))) {
                        addItemOrDrop(player, Items.BANDANA_AND_EYEPATCH_13377, 1)
                    } else {
                        sendDialogue(player, "You can not afford that.")
                    }
                }

                // orange double-eye
                56,57 -> {
                    if (inInventory(player, Items.COINS_995, 500)
                        && inInventory(player, Items.BANDANA_13374, 1)
                        && inInventory(player, Items.DOUBLE_EYEPATCHES_13353, 1)
                        && removeItem(player, Item(Items.COINS_995, 500))
                        && removeItem(player, Item(Items.BANDANA_13374, 1))
                        && removeItem(player, Item(Items.DOUBLE_EYEPATCHES_13353, 1))) {
                        addItemOrDrop(player, Items.BANDANA_AND_EYEPATCH_13351, 1) } else {
                        sendDialogue(player, "You can not afford that.")
                    }
                }

                // left double-eye
                80,81 -> {
                    if (inInventory(player, Items.COINS_995, 500)
                        && inInventory(player, Items.BANDANA_13374, 1)
                        && inInventory(player, Items.LEFT_EYEPATCH_13355, 1)
                        && removeItem(player, Item(Items.COINS_995, 500))
                        && removeItem(player, Item(Items.BANDANA_13374, 1))
                        && removeItem(player, Item(Items.LEFT_EYEPATCH_13355, 1))) {
                        addItemOrDrop(player, Items.BANDANA_AND_EYEPATCHES_13352, 1) } else {
                        sendDialogue(player, "You can not afford that.")
                    }
                }

                //---------------------------------------------------------------------------------//

                /**
                 * click-buy for pirate hats set.
                 */

                // pirate hat right-eye
                82,83 -> {
                    if (inInventory(player, Items.COINS_995, 500)
                        && inInventory(player, Items.PIRATES_HAT_2651, 1)
                        && inInventory(player, Items.EYE_PATCH_1025, 1)
                        && removeItem(player, Item(Items.COINS_995, 500))
                        && removeItem(player, Item(Items.PIRATES_HAT_2651, 1))
                        && removeItem(player, Item(Items.EYE_PATCH_1025, 1))) {
                        addItemOrDrop(player, Items.HAT_AND_EYEPATCH_8928, 1) } else {
                        sendDialogue(player, "You can not afford that.")
                    }
                }

                // pirate hat double-eye
                58,59 -> {
                    if (inInventory(player, Items.COINS_995, 500)
                        && inInventory(player, Items.PIRATES_HAT_2651, 1)
                        && inInventory(player, Items.DOUBLE_EYEPATCHES_13353, 1)
                        && removeItem(player, Item(Items.COINS_995, 500))
                        && removeItem(player, Item(Items.PIRATES_HAT_2651, 1))
                        && removeItem(player, Item(Items.DOUBLE_EYEPATCHES_13353, 1))) {
                        addItemOrDrop(player, Items.PIRATE_HAT_AND_EYEPATCHES_13354, 1) } else {
                        sendDialogue(player, "You can not afford that.")
                    }
                }

                // pirate hat left-eye
                84,85 -> {
                    if (inInventory(player, Items.COINS_995, 500)
                        && inInventory(player, Items.PIRATES_HAT_2651, 1)
                        && inInventory(player, Items.LEFT_EYEPATCH_13355, 1)
                        && removeItem(player, Item(Items.COINS_995, 500))
                        && removeItem(player, Item(Items.PIRATES_HAT_2651, 1))
                        && removeItem(player, Item(Items.LEFT_EYEPATCH_13355, 1))) {
                        addItemOrDrop(player, Items.PIRATE_HAT_AND_EYEPATCH_13357, 1) } else {
                        sendDialogue(player, "You can not afford that.")
                    }
                }

                // double-patch
                86,87 -> {
                    if (inInventory(player, Items.COINS_995, 500)
                        && inInventory(player, Items.LEFT_EYEPATCH_13355, 1)
                        && inInventory(player, Items.EYE_PATCH_1025, 1)
                        && removeItem(player, Item(Items.COINS_995, 500))
                        && removeItem(player, Item(Items.EYE_PATCH_1025, 1))
                        && removeItem(player, Item(Items.LEFT_EYEPATCH_13355, 1))) {
                        addItemOrDrop(player, Items.DOUBLE_EYEPATCHES_13353, 1)
                    } else {
                        sendDialogue(player, "You can not afford that.")
                    }
                }

                // crab hand
                88,89 -> {
                    if (inInventory(player, Items.COINS_995, 500)
                        && inInventory(player, Items.CRAB_CLAW_7537, 1)
                        && inInventory(player, Items.PIRATES_HOOK_2997, 1)
                        && removeItem(player, Item(Items.COINS_995, 500))
                        && removeItem(player, Item(Items.CRAB_CLAW_7537, 1))
                        && removeItem(player, Item(Items.PIRATES_HOOK_2997, 1))) {
                        addItemOrDrop(player, Items.CRABCLAW_AND_HOOK_8929, 1)
                    } else {
                        sendDialogue(player, "You can not afford that.")
                    }
                }

                // cavalier and mask
                90,91 -> {
                    if (inInventory(player, Items.COINS_995, 500)
                        && inInventory(player, Items.HIGHWAYMAN_MASK_2631, 1)
                        && inInventory(player, Items.BLACK_CAVALIER_2643, 1)
                        && removeItem(player, Item(Items.COINS_995, 500))
                        && removeItem(player, Item(Items.HIGHWAYMAN_MASK_2631, 1))
                        && removeItem(player, Item(Items.BLACK_CAVALIER_2643, 1))) {
                        addItemOrDrop(player, Items.CAVALIER_AND_MASK_11280, 1)
                    } else {
                        sendDialogue(player, "You can not afford that.")
                    }
                }

                // beret and mask
                92,93 -> {
                    if (inInventory(player, Items.COINS_995, 500)
                        && inInventory(player, Items.MIME_MASK_3057, 1)
                        && inInventory(player, Items.BLACK_BERET_2635, 1)
                        && removeItem(player, Item(Items.COINS_995, 500))
                        && removeItem(player, Item(Items.MIME_MASK_3057, 1))
                        && removeItem(player, Item(Items.BLACK_BERET_2635, 1))) {
                        addItemOrDrop(player, Items.BERET_AND_MASK_11282, 1) } else {
                        sendDialogue(player, "You can not afford that.")
                    }
                }
            }
            return@on true
        }
    }
}