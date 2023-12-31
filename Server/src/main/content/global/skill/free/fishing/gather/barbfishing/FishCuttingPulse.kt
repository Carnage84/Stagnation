package content.global.skill.free.fishing.gather.barbfishing

import config.Items
import core.api.freeSlots
import core.api.inInventory
import core.api.sendMessage
import core.game.node.entity.player.Player
import core.game.node.entity.skill.Skills
import core.game.node.item.Item
import core.game.system.task.Pulse
import core.game.world.update.flag.context.Animation

/**
 * Pulse used for cutting fish into fish offcuts
 * @param player the player running the pulse
 * @param fish the fish being cut
 */
class FishCuttingPulse(val player: Player, val fish: Int) : Pulse(0){
    fun checkRequirements(): Boolean {
        if(!(freeSlots(player) >= 2 || (freeSlots(player) >= 1 && inInventory(player, Items.FISH_OFFCUTS_11334)))){
            sendMessage(player, "You do not have enough space to do that.")
            return false
        }
        return true
    }

    override fun pulse(): Boolean {
        player.animator.animate(Animation(1248))
        player.inventory.remove(Item(fish))

        player.inventory.add(Item(Items.FISH_OFFCUTS_11334))

        player.inventory.add(Item(when(fish){
            11328, 11330 -> Items.ROE_11324
            11332 -> Items.CAVIAR_11326
            else -> 0
        }))

        player.skills.addExperience(Skills.COOKING,when(fish){
            11328,11330 -> 10.0
            11332 -> 15.0
            else -> 0.0
        })

        return true
    }
}
