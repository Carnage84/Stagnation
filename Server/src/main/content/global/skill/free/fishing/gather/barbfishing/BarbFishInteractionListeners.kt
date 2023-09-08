package content.global.skill.free.fishing.gather.barbfishing

import core.api.sendMessage
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.node.item.Item

class BarbFishInteractionListeners : InteractionListener {
    override fun defineListeners() {

        on(25268, IntType.SCENERY, "search"){ player, _ ->
            if(player.getAttribute("barbtraining:fishing",false) == true){
                if(!player.inventory.containsItem(Item(11323))){
                    player.inventory.add(Item(11323))
                    sendMessage(player, "Under the bed you find a fishing rod.")
                } else {
                    sendMessage(player, "You find nothing under the bed")
                }
            } else {
                sendMessage(player, "Maybe I should speak to Otto before looking under his bed.")
            }
            return@on true
        }

        on(1176, IntType.NPC, "fish"){ player, _ ->
            player.pulseManager.run(BarbFishingPulse(player))
            sendMessage(player, "You attempt to catch a fish...")
            return@on true
        }

    }
}