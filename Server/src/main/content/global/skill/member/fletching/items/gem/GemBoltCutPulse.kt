package content.global.skill.member.fletching.items.gem

import config.Items
import content.global.skill.member.fletching.Fletching.GemBolts
import core.api.inInventory
import core.api.removeItem
import core.game.node.entity.player.Player
import core.game.node.entity.skill.SkillPulse
import core.game.node.entity.skill.Skills
import core.game.node.item.Item
import core.game.world.update.flag.context.Animation

/**
 * Represents the gem cutting pulse(gem to bolt).
 */
class GemBoltCutPulse
/**
 * Constructs a new `GemCutPulse.java` `Object`.
 * @param player the player.
 * @param node the node.
 * @param amount the amount.
 */(player: Player?, node: Item?,
    /**
     * Represents the gem we're cutting.
     */
    private val gem: GemBolts,
    /**
     * Represents the amount to make.
     */
    private var amount: Int) : SkillPulse<Item?>(player, node) {
    /**
     * Represents the ticks passed.
     */
    private var ticks = 0

    override fun checkRequirements(): Boolean {
        if (player.skills.getLevel(Skills.FLETCHING) < gem.level) {
            player.dialogueInterpreter.sendDialogue("You need a Fletching level of " + gem.level + " or above to do that.")
            return false
        }
        return inInventory(player, gem.gem)
    }

    override fun animate() {
        if (ticks % 6 == 0) {
            player.animate(ANIMATION)
        }
    }

    override fun reward(): Boolean {
        if (++ticks % 5 != 0) {
            return false
        }
        val reward = if (gem.gem == Items.OYSTER_PEARLS_413) Item(gem.tip, 24) else Item(gem.tip, 12)
        if (removeItem(player, gem.gem)){
            player.inventory.add(reward)
            player.skills.addExperience(Skills.FLETCHING, gem.experience, true)
        }
        amount--
        return amount <= 0
    }

    companion object {
        /**
         * Represents the cutting animation.
         */
        private val ANIMATION = Animation(6702)
    }
}