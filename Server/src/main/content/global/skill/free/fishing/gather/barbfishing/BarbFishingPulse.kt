package content.global.skill.free.fishing.gather.barbfishing

import config.Items
import core.api.inInventory
import core.api.removeItem
import core.api.rewardXP
import core.api.sendMessage
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.game.node.entity.skill.SkillPulse
import core.game.node.entity.skill.Skills
import core.game.node.item.Item
import core.game.world.update.flag.context.Animation
import core.tools.RandomFunction
import core.tools.colorize

/**
 * Pulse used for barbarian fishing
 */
class BarbFishingPulse(player: Player) : SkillPulse<NPC>(player,NPC(1176)) {
    override fun checkRequirements(): Boolean {
        /*if(player.getAttribute("barbtraining:fishing:completed",false) == false){
            player.sendMessage(colorize("%RYou need to complete barbarian fishing training to fish here."))
            return false
        }*/
        if(player.skills.getLevel(Skills.FISHING) < 48){
            player.sendMessage(colorize("%RYou need a fishing level of at least 48 to fish here."))
            return false
        }
        if(player.skills.getLevel(Skills.AGILITY) < 15 || player.skills.getLevel(Skills.STRENGTH) < 15){
            player.sendMessage(colorize("%RYou need a strength and agility level of at least 15 to fish here."))
            return false
        }
        if(!inInventory(player, Items.BARBARIAN_ROD_11323)){
            player.sendMessage(colorize("%RYou need a barbarian fishing rod to fish here."))
            return false
        }
        if(player.inventory.isFull){
            sendMessage(player, "You don't have enough space in your inventory.")
            return false
        }
        if(!(inInventory(player,Items.FEATHER_314)) || inInventory(player,Items.FISH_OFFCUTS_11334)){
            sendMessage(player, "You don't have any bait with which to fish.")
            return false
        }
        return true
    }

    override fun animate() {
        player.animator.animate(Animation(622))
    }

    override fun reward(): Boolean {
        val stragiXP = arrayOf(5,6,7)
        val fishXP = arrayOf(50,70,80)
        val reward = getRandomFish()
        val success = rollSuccess(when(reward.id){
            Items.LEAPING_TROUT_11328 -> 48
            Items.LEAPING_SALMON_11330 -> 58
            Items.LEAPING_STURGEON_11332 -> 70
            else -> 99
        })
        val index = (when(reward.id){
            Items.LEAPING_TROUT_11328 -> 0
            Items.LEAPING_SALMON_11330 -> 1
            Items.LEAPING_STURGEON_11332 -> 2
            else -> 0
        })
        if(success){
            if(!removeItem(player, Items.FISH_OFFCUTS_11334)) {
                removeItem(player, Items.FEATHER_314)
            }
            player.inventory.add(reward)
            rewardXP(player,Skills.FISHING,fishXP[index].toDouble())
            rewardXP(player,Skills.AGILITY,stragiXP[index].toDouble())
            rewardXP(player,Skills.STRENGTH,stragiXP[index].toDouble())
            sendMessage(player, "You manage to catch a ${reward.name.toLowerCase()}.")
        }
        super.setDelay(5)
        return player.inventory.freeSlots() == 0
    }

    fun rollSuccess(fish: Int): Boolean{
        val level = 1 + player.skills.getLevel(Skills.FISHING) + player.familiarManager.getBoost(Skills.FISHING)
        val hostRatio: Double = Math.random() * fish
        val clientRatio: Double = Math.random() * (level * 3.0 - fish)
        return hostRatio < clientRatio
    }

    fun getRandomFish(): Item{
        val fish = arrayOf(Items.LEAPING_TROUT_11328,Items.LEAPING_SALMON_11330,Items.LEAPING_STURGEON_11332)
        val fishing = player.skills.getLevel(Skills.FISHING)
        val strength = player.skills.getLevel(Skills.STRENGTH)
        val agility = player.skills.getLevel(Skills.AGILITY)
        var possibleIndex = 0
        if(fishing >= 58 && (strength >= 30 && agility >= 30)) possibleIndex++
        if(fishing >= 70 && (strength >= 45 && agility >= 45)) possibleIndex++
        return Item(fish[RandomFunction.random(possibleIndex + 1)])
    }

}