package content.data

import config.Items
import core.api.*
import core.cache.def.impl.ItemDefinition
import core.game.component.Component
import core.game.interaction.OptionHandler
import core.game.node.Node
import core.game.node.entity.player.Player
import core.game.node.entity.skill.Skills
import core.game.node.item.Item
import core.plugin.Initializable
import core.plugin.Plugin
import core.tools.BLUE

/**
 * Handles reading the tome of experience.
 */
@Initializable
class ExpBooks : OptionHandler() {
    override fun newInstance(arg: Any?): Plugin<Any> {
        ItemDefinition.forId(13162).handlers["option:read"] = this
        ItemDefinition.forId(13161).handlers["option:read"] = this
        ItemDefinition.forId(13160).handlers["option:read"] = this
        ItemDefinition.forId(9658).handlers["option:read"] = this
        ItemDefinition.forId(9657).handlers["option:read"] = this
        ItemDefinition.forId(9656).handlers["option:read"] = this
        return this
    }

    override fun handle(player: Player?, node: Node?, option: String?): Boolean {
        player ?: return false
        node ?: return false
        when (node.id) {
            Items.TOME_OF_XP_1_9658, Items.TOME_OF_XP_2_9657, Items.TOME_OF_XP_3_9656 ->{
                setAttribute(player, "exp:tomes", 1)
            }
            Items.TOME_OF_XP_2ND_ED_1_13162, Items.TOME_OF_XP_2ND_ED_2_13161, Items.TOME_OF_XP_2ND_ED_3_13160 ->{
                setAttribute(player, "exp:tomes", 2)
            }
        }
        setAttribute(player, "caller", this)
        player.interfaceManager.open(Component(134).setCloseEvent { p: Player?, c: Component? ->
            player.interfaceManager.openDefaultTabs()
            removeAttribute(player, "lamp")
            player.unlock()
            true
        })
        player.interfaceManager.removeTabs(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13)
        return true
    }

    override fun handleSelectionCallback(skill: Int, player: Player) {
        if(getAttribute(player, "exp:tomes", 0) == 1) {
            if (getStatLevel(player, skill) < 30){
                player.sendMessage("You need at least 30 " + Skills.SKILL_NAME[skill] + " level to do this.")
                return
            } else if (removeItem(player, Item(Items.TOME_OF_XP_1_9658, 1), Container.INVENTORY) || removeItem(player, Item(Items.TOME_OF_XP_2_9657, 1), Container.INVENTORY)) {
                player.dialogueInterpreter.sendPlainMessage(false, "You read a fascinating chapter and earn " + BLUE + "experience" + "!", "You have been awarded 2,000 " + Skills.SKILL_NAME[skill] + BLUE + " experience" + "!")
                rewardXP(player, skill, 2000.0)
            } else if (removeItem(player, Item(Items.TOME_OF_XP_3_9656, 1), Container.INVENTORY)) {
                player.dialogueInterpreter.sendPlainMessage(false, "The book crumbles into a dust cloud of knowledge.")
                rewardXP(player, skill, 2000.0)
            }
        }else{
            if(getAttribute(player, "exp:tomes", 0) == 2) {
                if (getStatLevel(player, skill) < 35) {
                    player.sendMessage("You need at least 35 " + Skills.SKILL_NAME[skill] + " level to do this.")
                    return
                } else if (removeItem(player, Item(Items.TOME_OF_XP_2ND_ED_1_13162, 1), Container.INVENTORY) || removeItem(player, Item(Items.TOME_OF_XP_2ND_ED_2_13161, 1), Container.INVENTORY)) {
                    player.dialogueInterpreter.sendPlainMessage(false, "You read a fascinating chapter and earn " + BLUE + "experience" + "!", "You have been awarded 2,500 " + Skills.SKILL_NAME[skill] + BLUE + " experience" + "!")
                    rewardXP(player, skill, 2500.0)
                } else if (removeItem(player, Item(Items.TOME_OF_XP_2ND_ED_3_13160, 1), Container.INVENTORY)) {
                    player.dialogueInterpreter.sendPlainMessage(false, "The book crumbles into a dust cloud of knowledge.")
                    rewardXP(player, skill, 2500.0)
                }
            }
        }
    }

}