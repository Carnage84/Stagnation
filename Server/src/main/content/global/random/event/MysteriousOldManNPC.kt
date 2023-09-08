package content.global.random.event

import config.NPCs
import content.global.random.RandomEventNPC
import core.api.utils.WeightBasedTable
import core.game.node.entity.npc.NPC
import core.tools.RandomFunction

class MysteriousOldManNPC(var type: String = "", override var loot: WeightBasedTable? = null) : RandomEventNPC(NPCs.MYSTERIOUS_OLD_MAN_410) {
    override fun init() {
        super.init()
        sayLine()
    }

    override fun tick() {
        super.tick()
        if(RandomFunction.random(1,10) == 5) sayLine()
    }

    fun sayLine() {
        when(type){
            "sexam" -> sendChat("Surprise exam, ${player.username.capitalize()}!")
        }
    }

    override fun talkTo(npc: NPC) {
        when(type){
            "sexam" -> player.dialogueInterpreter.open(MysteriousOldManDialogue("sexam"),this.asNpc())
        }
    }
}