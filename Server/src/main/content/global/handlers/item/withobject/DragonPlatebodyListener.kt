package content.global.handlers.item.withobject

import config.Animations
import config.Items
import config.Scenery
import core.api.*
import core.game.dialogue.DialogueFile
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.node.entity.skill.Skills
import core.game.system.task.Pulse
import core.game.world.GameWorld

/**
 * Handles creating a Dragon platebody.
 */
class DragonPlatebodyListener : InteractionListener {

    // https://runescape.wiki/w/Dragonkin_key?oldid=1651769
    // https://runescape.wiki/w/Ancient_Cavern?oldid=2014972
    // https://runescape.wiki/w/While_Guthix_Sleeps?oldid=1986361
    // https://runescape.wiki/w/Blast_fusion_hammer?direction=prev&oldid=2105331
    // https://runescape.wiki/w/Transcript:Blast_Furnace_Foreman?oldid=26457629
    // https://www.youtube.com/watch?v=ChW90Ls1zMY&ab_channel=Omustardo

    // "You hear a mechanical sound nearby as you set the third dragon",
    // "head alight. The sound moves slowly away to the south as some",
    // "hidden apparatus is set into motion."

    companion object {
        const val SMITH_ANIM = Animations.HUMAN_ANVIL_HAMMER_SMITHING_898
        const val DRAGON_FORGE = Scenery.ANVIL_40200
        const val FUSION_HAMMER = Items.BLAST_FUSION_HAMMER_14478
        const val LUMP_ELEMENT = Items.RUINED_DRAGON_ARMOUR_LUMP_14472
        const val SLICE_ELEMENT = Items.RUINED_DRAGON_ARMOUR_SLICE_14474
        const val SHARD_ELEMENT = Items.RUINED_DRAGON_ARMOUR_SHARD_14476
        val anyElement = intArrayOf(LUMP_ELEMENT,SLICE_ELEMENT,SHARD_ELEMENT)
    }


    override fun defineListeners() {
        onUseWith(IntType.SCENERY, anyElement, DRAGON_FORGE){ player, _, _ ->
            if(getStatLevel(player, Skills.SMITHING) < 92) {
                sendMessage(player, "You need at least 92 smithing level to do this.")
            } else if(!inInventory(player,LUMP_ELEMENT) && inInventory(player,SLICE_ELEMENT) && inInventory(player,SHARD_ELEMENT) && inInventory(player, FUSION_HAMMER)){
                sendDialogue(player, "you do not have the required items.")
            } else {
                openDialogue(player, SmithDragonPlatebodyDialogue())
            }
            return@onUseWith true
        }
    }
}

/**
 * Handles smithing the Dragon platebody at Ancient Cavern.
 */
class SmithDragonPlatebodyDialogue : DialogueFile() {
    override fun handle(componentID: Int, buttonID: Int) {
        when (stage) {
            0 -> {
                end()
                lock(player!!, 1000000)
                lockInteractions(player!!, 100000)
                GameWorld.Pulser.submit(object : Pulse() {
                    var counter = 0
                    override fun pulse(): Boolean {
                        when (counter++) {
                            0 -> {
                                sendMessage(player!!, "You set to work repairing the ruined armour.")
                                animate(player!!, DragonPlatebodyListener.SMITH_ANIM)
                            }
                            1 -> {
                                removeItem(player!!, DragonPlatebodyListener.FUSION_HAMMER)
                                removeItem(player!!, DragonPlatebodyListener.LUMP_ELEMENT)
                                removeItem(player!!, DragonPlatebodyListener.SLICE_ELEMENT)
                                removeItem(player!!, DragonPlatebodyListener.SHARD_ELEMENT)
                            }
                            6 -> {
                                sendMessage(player!!, "You finish your efforts...")
                                addItemOrDrop(player!!, Items.DRAGON_PLATEBODY_14479)
                                rewardXP(player!!, Skills.SMITHING, 2000.0)
                                unlock(player!!)
                                return true
                            }
                        }
                        return false
                    }
                })
            }

            2 -> end()
        }
    }
}