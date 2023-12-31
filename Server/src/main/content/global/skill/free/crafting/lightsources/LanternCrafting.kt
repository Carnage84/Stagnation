package content.global.skill.free.crafting.lightsources

import core.api.sendMessage
import core.game.interaction.NodeUsageEvent
import core.game.interaction.UseWithHandler
import core.game.node.entity.player.Player
import core.game.node.entity.skill.Skills
import core.game.node.item.Item
import core.plugin.Initializable
import core.plugin.Plugin

/**
 * Handles the combining of items to craft lanterns
 */
@Initializable
class LanternCrafting : UseWithHandler(36,38,4525,4542,1607){
    /**
     * For candle lanterns -> Glassblowing produces 4527
     * 4527 + white candle = 4529
     * 4527 + black candle = 4532
     *
     * For oil lanterns -> Glassblowing produces 4525
     * 4525 + 4540 (oil lantern frame) = 4535 (empty oil lantern)
     *
     * For Bullseye lanterns -> Smithing produces 4544 (bullseye lantern (unf))
     * 4544 + Lens(4542) -> 4546
     * 4544 + Sapphire(1607) -> 4700 (Sapphire lantern)
     */
    override fun newInstance(arg: Any?): Plugin<Any> {
        addHandler(4527, ITEM_TYPE, this) //Empty candle lantern
        addHandler(4540, ITEM_TYPE,this)  //oil lantern frame
        addHandler(4544, ITEM_TYPE,this)  //Bullseye lantern(unf)
        return this
    }

    override fun handle(event: NodeUsageEvent?): Boolean {
        event ?: return false //if event is null don't execute
        val used = event.used
        return when(used.id){
            4527 -> craftCandleLantern(event.player,event)
            4540 -> craftOilLantern(event.player,event)
            4544 -> craftBullseyeLantern(event.player,event)
            else -> false
        }
    }

    private fun craftCandleLantern(player: Player, event: NodeUsageEvent): Boolean{
        return when(event.usedWith.id){
            36,38 -> {
                removeEventItems(player,event)
                player.inventory.add( if(event.usedWith.id == 36) Item(4529) else Item(4532))
                sendMessage(player, "You place the unlit candle inside the lantern.")
                true
            }
            else -> false
        }
    }

    private fun craftOilLantern(player: Player, event: NodeUsageEvent): Boolean {
        return when(event.usedWith.id){
            4525 -> {
                removeEventItems(player,event)
                player.inventory.add(Item(4535))
                sendMessage(player, "You place the oil lamp inside its metal frame.")
                true
            }
            else -> false
        }
    }

    private fun craftBullseyeLantern(player: Player,event: NodeUsageEvent): Boolean{
        return when(event.usedWith.id){
            4542 -> {
                removeEventItems(player,event)
                player.inventory.add( Item(4546) )
                sendMessage(player, "You fashion the lens onto the lantern.")
                true
            }
            1607 -> {
                if(player.skills.getLevel(Skills.CRAFTING) >= 20){
                    removeEventItems(player,event)
                    player.inventory.add(Item(4700))
                    sendMessage(player, "You fashion the gem into a lens and fit it onto the lantern.")
                } else {
                    sendMessage(player, "You require a crafting level of 20 to use a gem as a lens.")
                }
                true
            }
            else -> false
        }
    }

    private fun removeEventItems(player: Player, event: NodeUsageEvent){
        player.inventory.remove(event.used.asItem())
        player.inventory.remove(event.usedWith.asItem())
    }
}