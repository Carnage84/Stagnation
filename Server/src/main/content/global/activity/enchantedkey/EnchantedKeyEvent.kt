package content.global.activity.enchantedkey

import config.Items
import content.quest.member.makinghistory.MakingHistoryListener
import core.api.*
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.world.map.Location

/**
 * Represents Enchanted Key (lore activity).
 */
class EnchantedKeyEvent : InteractionListener {

    // https://runescape.wiki/w/Enchanted_key_(lore_activity)?oldid=15922945
    companion object {
        const val totalFindings = "/save:miniquest:enchanted-key:0"

        val rellekkaTreasure: Location =    Location(2716, 3606, 0)
        val ardougneTreasure: Location =    Location(2621, 3239, 0)
        val benchTreasure: Location =       Location(2417, 3382, 0)
        val gnomeTreasure: Location =       Location(2448, 3443, 0)
        val altarTreasure: Location =       Location(3033, 3437, 0)
        val faladorTreasure: Location =     Location(2972, 3304, 0)
        val mudskipperTreasure: Location =  Location(3007, 3161, 0)
        val swampTreasure: Location =       Location(3161, 3176, 0)
        val alkharidTreasure: Location =    Location(3292, 3219, 0)
        val examTreasure: Location =        Location(3300, 3350, 0)
        val geTreasure: Location =          Location(3160, 3490, 0)
    }

    override fun defineListeners() {
        on(MakingHistoryListener.enchantedKey, IntType.ITEM, "feel") { player, node ->

            when {
                // Quest borders
                inBorders(player, 2431, 3135, 2450, 3151) -> sendMessage(player, "Ouch! It's burning hot and the same temperature as last time")
                inBorders(player, 2435, 3080, 2474, 3100) -> sendMessage(player, "It's cold.")
                inBorders(player, 2442, 3106, 2461, 3126) -> sendMessage(player, "It's warm.")
                inBorders(player, 2425, 3129, 2447, 3134) -> sendMessage(player, "It's hot!")

                // rellekkaTreasure 1
                inBorders(player, 2709, 3610, 2715, 3603) -> if (getAttribute(player, totalFindings, 0) == 0) {
                    sendMessage(player, "Ouch! It's burning hot and the same temperature as last time")
                } else {
                    sendMessage(player, "It's freezing.")
                }

                // rellekkaTreasure 2
                inBorders(player, 2715, 3605, 2717, 3607) -> if (getAttribute(player, totalFindings, 0) == 0) {
                    sendMessage(player, "The key is steaming. It must be right below your feet.")
                } else {
                    sendMessage(player, "It's freezing.")
                }

                // ardougneTreasure 1
                inBorders(player, 2709, 3610, 2622, 3241) -> if (getAttribute(player, totalFindings, 0) == 1) {
                    sendMessage(player, "The key is steaming. It must be right below your feet.")
                } else {
                    sendMessage(player, "It's freezing.")
                }

                // ardougneTreasure 2
                inBorders(player, 2606, 3232, 2618, 3241) -> if (getAttribute(player, totalFindings, 0) == 1) {
                    sendMessage(player, "Ouch! It's burning hot and the same temperature as last time")
                } else {
                    sendMessage(player, "It's freezing.")
                }

                // benchTreasure 1
                inBorders(player, 2411, 3374, 2427, 3390) -> if (getAttribute(player, totalFindings, 0) == 2) {
                    sendMessage(player, "The key is steaming. It must be right below your feet.")
                } else {
                    sendMessage(player, "It's freezing.")
                }

                // benchTreasure 2
                inBorders(player, 2394, 3364, 2438, 3395) -> if (getAttribute(player, totalFindings, 0) == 2) {
                    sendMessage(player, "It's warm")
                } else {
                    sendMessage(player, "It's freezing.")
                }

                // gnomeTreasure 1
                inBorders(player, 2441, 3434, 2458, 3456) -> if (getAttribute(player, totalFindings, 0) == 3) {
                    sendMessage(player, "It's very hot")
                } else {
                    sendMessage(player, "It's freezing.")
                }

                // gnomeTreasure 2
                inBorders(player, 2451, 3446, 2445, 3443) -> if (getAttribute(player, totalFindings, 0) == 3) {
                    sendMessage(player, "The key is steaming. It must be right below your feet.")
                } else {
                    sendMessage(player, "It's freezing.")
                }

                // altarTreasure 1
                inBorders(player, 3026, 3431, 3042, 3445) -> if (getAttribute(player, totalFindings, 0) == 4) {
                    sendMessage(player, "It's very hot")
                } else {
                    sendMessage(player, "It's freezing.")
                }

                // altarTreasure 2
                inBorders(player, 3009, 3413, 3066, 3452) -> if (getAttribute(player, totalFindings, 0) == 4) {
                    sendMessage(player, "It's hot")
                } else {
                    sendMessage(player, "It's freezing.")
                }

                // altarTreasure 3
                inBorders(player, 3030, 3434, 3036, 3440) -> if (getAttribute(player, totalFindings, 0) == 4) {
                    sendMessage(player, "The key is steaming. It must be right below your feet.")
                } else {
                    sendMessage(player, "It's freezing.")
                }

                // faladorTreasure 1
                inBorders(player, 2969, 3301, 2976, 3308) -> if (getAttribute(player, totalFindings, 0) == 5) {
                    sendMessage(player, "The key is steaming. It must be right below your feet.")
                } else {
                    sendMessage(player, "It's freezing.")
                }

                // faladorTreasure 2
                inBorders(player, 2948, 3295, 2976, 3315) -> if (getAttribute(player, totalFindings, 0) == 5) {
                    sendMessage(player, "It's very hot")
                } else {
                    sendMessage(player, "It's freezing.")
                }

                // mudskipperTreasure 1
                inBorders(player, 3003, 3157, 3011, 3165) -> if (getAttribute(player, totalFindings, 0) == 6) {
                    sendMessage(player, "The key is steaming. It must be right below your feet.")
                } else {
                    sendMessage(player, "It's freezing.")
                }

                //mudskipperTreasure 2
                inBorders(player, 2993, 3145, 3029, 3179) -> if (getAttribute(player, totalFindings, 0) == 6) {
                    sendMessage(player, "It's very hot")
                } else {
                    sendMessage(player, "It's freezing.")
                }

                // swampTreasure 1
                inBorders(player, 3157, 3172, 3165, 3180) -> if (getAttribute(player, totalFindings, 0) == 7) {
                    sendMessage(player, "The key is steaming. It must be right below your feet.")
                } else {
                    sendMessage(player, "It's freezing.")
                }

                // swampTreasure 2
                inBorders(player, 3147, 3159, 3186, 3199) -> if (getAttribute(player, totalFindings, 0) == 7) {
                    sendMessage(player, "It's warm")
                } else {
                    sendMessage(player, "It's freezing.")
                }

                // al'kharidTreasure 1
                inBorders(player, 3288, 3215, 3296, 3223) -> if (getAttribute(player, totalFindings, 0) == 8) {
                    sendMessage(player, "The key is steaming. It must be right below your feet.")
                } else {
                    sendMessage(player, "It's freezing.")
                }

                // al'kharidTreasure 2
                inBorders(player, 3270, 3201, 3307, 3234) -> if (getAttribute(player, totalFindings, 0) == 8) {
                    sendMessage(player, "It's very hot")
                } else {
                    sendMessage(player, "It's freezing.")
                }

                // examTreasure 1
                inBorders(player, 3296, 3346, 3304, 3354) -> if (getAttribute(player, totalFindings, 0) == 9) {
                    sendMessage(player, "The key is steaming. It must be right below your feet.")
                } else {
                    sendMessage(player, "It's freezing.")
                }

                // examTreasure 2
                inBorders(player, 3277, 3335, 3321, 3369) -> if (getAttribute(player, totalFindings, 0) == 9) {
                    sendMessage(player, "It's very hot")
                } else {
                    sendMessage(player, "It's freezing.")
                }

                // geTreasure 1
                inBorders(player, 3156, 3486, 3164, 3494) -> if (getAttribute(player, totalFindings, 0) == 10) {
                    sendMessage(player, "The key is steaming. It must be right below your feet.")
                } else {
                    sendMessage(player, "It's freezing.")
                }

                // geTreasure 2
                inBorders(player, 3133, 3465, 3199, 3517) -> if (getAttribute(player, totalFindings, 0) == 10) {
                    sendMessage(player, "It's very hot")
                } else {
                    sendMessage(player, "It's freezing.")
                }
            }
            return@on true
        }


        onDig(rellekkaTreasure) { player ->
            if(getAttribute(player, totalFindings, 0) == 0) {
                player.incrementAttribute(totalFindings)
                addItemOrDrop(player, Items.STEEL_ARROW_886, 20)
                addItemOrDrop(player, Items.MITHRIL_ORE_448, 10)
                addItemOrDrop(player, Items.LAW_RUNE_563, 15)
                sendMessage(player, "You found a treasure!")
                return@onDig
            }
        }

        onDig(ardougneTreasure) { player ->
            if(getAttribute(player, totalFindings, 0) == 1) {
                player.incrementAttribute(totalFindings)
                addItemOrDrop(player, Items.PURE_ESSENCE_7937, 36)
                addItemOrDrop(player, Items.IRON_ORE_441, 15)
                addItemOrDrop(player, Items.FIRE_RUNE_554, 30)
                sendMessage(player, "You found a treasure!")
                return@onDig
            }
        }

        onDig(benchTreasure) { player ->
            if(getAttribute(player, totalFindings, 0) == 2) {
                player.incrementAttribute(totalFindings)
                addItemOrDrop(player, Items.PURE_ESSENCE_7937, 40)
                addItemOrDrop(player, Items.IRON_ARROWTIPS_40, 20)
                addItemOrDrop(player, Items.FIRE_RUNE_554, 20)
                sendMessage(player, "You found a treasure!")
                return@onDig
            }
        }

        onDig(gnomeTreasure) { player ->
            if(getAttribute(player, totalFindings, 0) == 3) {
                player.incrementAttribute(totalFindings)
                addItemOrDrop(player, Items.PURE_ESSENCE_7937, 39)
                addItemOrDrop(player, Items.IRON_ARROWTIPS_40, 20)
                addItemOrDrop(player, Items.WATER_RUNE_555, 30)
                sendMessage(player, "You found a treasure!")
                return@onDig
            }
        }

        onDig(altarTreasure) { player ->
            if(getAttribute(player, totalFindings, 0) == 4) {
                player.incrementAttribute(totalFindings)
                addItemOrDrop(player, Items.MITHRIL_ORE_448, 10)
                addItemOrDrop(player, Items.IRON_ORE_441, 15)
                addItemOrDrop(player, Items.EARTH_RUNE_557, 45)
                sendMessage(player, "You found a treasure!")
                return@onDig
            }
        }

        onDig(faladorTreasure) { player ->
            if(getAttribute(player, totalFindings, 0) == 5) {
                player.incrementAttribute(totalFindings)
                addItemOrDrop(player, Items.EARTH_RUNE_557, 15)
                addItemOrDrop(player, Items.IRON_ARROW_884, 20)
                addItemOrDrop(player, Items.SARADOMIN_MJOLNIR_6762, 1)
                sendMessage(player, "You found a treasure!")
                return@onDig
            }
        }

        onDig(mudskipperTreasure) { player ->
            if(getAttribute(player, totalFindings, 0) == 6) {
                player.incrementAttribute(totalFindings)
                addItemOrDrop(player, Items.IRON_ORE_441, 15)
                addItemOrDrop(player, Items.MITHRIL_ARROW_888, 20)
                addItemOrDrop(player, Items.DEATH_RUNE_560, 15)
                sendMessage(player, "You found a treasure!")
                return@onDig
            }
        }

        onDig(swampTreasure) { player ->
            if(getAttribute(player, totalFindings, 0) == 7) {
                player.incrementAttribute(totalFindings)
                addItemOrDrop(player, Items.PURE_ESSENCE_7937, 29)
                addItemOrDrop(player, Items.MIND_RUNE_558, 20)
                addItemOrDrop(player, Items.STEEL_ARROW_886, 20)
                addItemOrDrop(player, Items.ZOMBIE_HEAD_6722, 1)
                sendMessage(player, "You found a treasure!")
                return@onDig
            }
        }

        onDig(alkharidTreasure) { player ->
            if(getAttribute(player, totalFindings, 0) == 8) {
                player.incrementAttribute(totalFindings)
                addItemOrDrop(player, Items.PURE_ESSENCE_7937, 40)
                addItemOrDrop(player, Items.MITHRIL_ORE_448, 10)
                addItemOrDrop(player, Items.ZAMORAK_MJOLNIR_6764, 1)
                sendMessage(player, "You found a treasure!")
                return@onDig
            }
        }

        onDig(examTreasure) { player ->
            if(getAttribute(player, totalFindings, 0) == 9) {
                player.incrementAttribute(totalFindings)
                addItemOrDrop(player, Items.PURE_ESSENCE_7937, 40)
                addItemOrDrop(player, Items.IRON_ORE_441, 15)
                addItemOrDrop(player, Items.GUTHIX_MJOLNIR_6760, 1)
                sendMessage(player, "You found a treasure!")
                return@onDig
            }
        }

        onDig(geTreasure) { player ->
            if(getAttribute(player, totalFindings, 0) == 10) {
                player.incrementAttribute(totalFindings)
                addItemOrDrop(player, Items.PURE_ESSENCE_7937, 39)
                addItemOrDrop(player, Items.MITHRIL_ARROW_888, 10)
                addItemOrDrop(player, Items.LAW_RUNE_563, 15)
                runTask(player, 1) {
                    if (getAttribute(player, totalFindings, 0) == 11) {
                        sendMessage(player, "Congratulations! You have completed the Enchanted key miniquest!")
                        removeItem(player, Items.ENCHANTED_KEY_6754)
                    } else {
                        sendMessage(player, "You found a treasure!")
                    }
                }
                return@onDig
            }
        }
    }
}