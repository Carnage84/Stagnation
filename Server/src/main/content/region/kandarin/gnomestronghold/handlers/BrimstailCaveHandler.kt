package content.region.kandarin.gnomestronghold.handlers

import config.NPCs
import config.Scenery
import content.global.travel.EssenceTeleport
import core.api.*
import core.cache.def.impl.NPCDefinition
import core.cache.def.impl.SceneryDefinition
import core.game.dialogue.FacialExpression
import core.game.interaction.OptionHandler
import core.game.node.Node
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.plugin.Plugin

/**
 * Handles options at Brimstail cave.
 */
@Initializable
class BrimstailCaveHandler : OptionHandler() {

    override fun newInstance(arg: Any?): Plugin<Any> {
        SceneryDefinition.forId(17209).handlers["option:enter"] = this
        SceneryDefinition.forId(17222).handlers["option:exit"] = this
        SceneryDefinition.forId(17223).handlers["option:exit"] = this
        SceneryDefinition.forId(17235).handlers["option:take-bowl"] = this
        SceneryDefinition.forId(17238).handlers["option:search"] = this
        NPCDefinition.forId(171).handlers["option:teleport"] = this
        return this
    }

    override fun handle(player: Player?, node: Node?, option: String?): Boolean {
        player ?: return false
        node ?: return false
        when (node.id) {
            Scenery.CAVE_ENTRANCE_17209 -> teleport(player, location(2408, 9812,0))
            Scenery.TUNNEL_17222 -> teleport(player, location(2402, 3419, 0))
            Scenery.TUNNEL_17223 -> teleport(player, location(2402, 3419, 0))
            Scenery.TABLE_17235 -> sendNPCDialogue(player, NPCs.BRIMSTAIL_171, "stop I don't want you to spill water on my books!", FacialExpression.OLD_NORMAL)
            Scenery.ASPIDISTRA_PLANT_17238 -> sendDialogue(player, "Gronda Gronda!")
            NPCs.BRIMSTAIL_171 -> {
                if(!isQuestComplete(player, "Rune Mysteries")){
                    sendMessage(player, "To mine rune essence, you must have started Rune Mysteries.")
                } else {
                    EssenceTeleport.teleport(NPC(NPCs.BRIMSTAIL_171), player)
                }
            }
        }
        return true
    }
}