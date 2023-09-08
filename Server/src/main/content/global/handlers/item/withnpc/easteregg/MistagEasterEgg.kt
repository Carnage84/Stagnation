package content.global.handlers.item.withnpc.easteregg

import config.Items
import config.NPCs
import core.api.inEquipment
import core.api.inInventory
import core.api.removeAttribute
import core.api.setAttribute
import core.game.component.Component
import core.game.dialogue.DialogueFile
import core.game.dialogue.FacialExpression
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.node.item.Item
import core.tools.END_DIALOGUE

/**
 * Represents the Mistag Easter egg.
 */
class MistagEasterEgg : InteractionListener {
    val DIAMOND = Items.DIAMOND_1601
    val MISTAG = NPCs.MISTAG_2084
    val ZANIK_RING = 14649
    val DRUNK_RENDER = 982

    override fun defineListeners() {
        onUseWith(IntType.NPC,DIAMOND,MISTAG){ player, _, with ->
            val alreadyHasRing = inInventory(player, ZANIK_RING,1) || player.bank.contains(ZANIK_RING,1) || inEquipment(player, ZANIK_RING,1)
            player.dialogueInterpreter.open(MistagEasterEggDialogue(alreadyHasRing),with.asNpc())
            return@onUseWith true
        }

        onEquip(ZANIK_RING){player,_ ->
            player.appearance.transformNPC(NPCs.ZANIK_3712)
            return@onEquip true
        }

        onUnequip(ZANIK_RING){player, _ ->
            player.appearance.transformNPC(-1)
            return@onUnequip true
        }

        onEquip(Items.BEER_1917){ player, _ ->
            setAttribute(player, "render-anim-override", DRUNK_RENDER)
            return@onEquip true
        }

        onUnequip(Items.BEER_1917){ player, _ ->
            removeAttribute(player, "render-anim-override")
            player.appearance.setDefaultAnimations()
            player.appearance.sync()
            return@onUnequip true
        }
    }
}

class MistagEasterEggDialogue(val hasRing: Boolean): DialogueFile() {
    override fun handle(componentID: Int, buttonID: Int) {

        if (hasRing) {
            npc("Lovely gem, adventurer, but I have nothing for you.").also { stage = END_DIALOGUE }
            return
        }

        when (stage++) {
            0 -> npc("Well thank you adventurer! Here, take this.")
            1 -> {
                end()
                if (player!!.inventory.remove(Item(Items.DIAMOND_1601))) {
                    player!!.inventory.add(Item(14649))
                }
            }
        }
    }

    override fun npc(vararg messages: String?): Component? {
        return super.npc(FacialExpression.OLD_HAPPY, *messages)
    }

}