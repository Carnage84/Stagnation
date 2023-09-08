package content.global.skill.member.fletching.items.crossbow

import content.global.skill.member.fletching.Fletching
import core.api.inInventory
import core.game.node.entity.player.Player
import core.game.node.entity.skill.SkillPulse
import core.game.node.entity.skill.Skills
import core.game.node.item.Item

/**
 * Represents the skill pulse of attaching limbs.
 */
class LimbPulse(player: Player?, node: Item, private val limb: Fletching.Limb, private var amount: Int) : SkillPulse<Item?>(player, node) {
    override fun checkRequirements(): Boolean {
        if (player.skills.getLevel(Skills.FLETCHING) < limb.level) {
            player.dialogueInterpreter.sendDialogue("You need a fletching level of " + limb.level + " to attach these limbs.")
            return false
        }
        if (!inInventory(player, limb.limb)) {
            player.dialogueInterpreter.sendDialogue("That's not the correct limb to attach.")
            return false
        }
        if(!inInventory(player, limb.stock)){
            player.dialogueInterpreter.sendDialogue("That's not the correct stock for that limb.")
            return false
        }
        return inInventory(player, limb.stock)
    }

    override fun animate() {
        player.animate(limb.animation)
    }

    override fun reward(): Boolean {
        if (delay == 1) {
            super.setDelay(6)
            return false
        }
        if (player.inventory.remove(Item(limb.stock), Item(limb.limb))) {
            player.inventory.add(Item(limb.product))
            player.skills.addExperience(Skills.FLETCHING, limb.experience, true)
            player.packetDispatch.sendMessage("You attach the metal limbs to the stock.")
        }
        if (!inInventory(player, limb.limb)) {
            return true
        }
        amount--
        return amount == 0
    }

    override fun message(type: Int) {}
}