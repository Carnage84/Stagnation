package content.global.random.event.gravedigger

import config.NPCs
import content.global.random.RandomEventNPC
import core.api.utils.WeightBasedTable
import core.game.node.entity.npc.NPC
import core.tools.RandomFunction

/**
 * Represents Leo NPC.
 */
class LeoNPC(override var loot: WeightBasedTable? = null) : RandomEventNPC(NPCs.LEO_3508) {
    override fun init() {
        super.init()
        sendChat("Can I borrow you for a minute, ${player.username}?")
    }

    override fun tick() {
        super.tick()
        if (RandomFunction.random(1, 10) == 5) {
            sendChat("Can I borrow you for a minute, ${player.username}?")
        }
    }

    override fun talkTo(npc: NPC) {
        player.dialogueInterpreter.open(LeoDialogue(), npc)
    }
}