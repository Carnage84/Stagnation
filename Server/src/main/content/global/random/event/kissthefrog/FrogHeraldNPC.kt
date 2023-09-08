package content.global.random.event.kissthefrog

import config.NPCs
import content.global.random.RandomEventNPC
import core.api.utils.WeightBasedTable
import core.game.node.entity.npc.NPC
import core.tools.RandomFunction

/**
 * Represents Frog Herald NPC.
 */
class FrogHeraldNPC(override var loot: WeightBasedTable? = null) : RandomEventNPC(NPCs.FROG_2471){

    val phrases = arrayOf("@name, the Frog @gender needs your help.", "Greetings from the Frog @gender, @name!", "Talk to the Frog @gender, @name!", "The Frog @gender needs your help, @name!", "Please respond to the Frog @gender, @name!")

    override fun init(){
        super.init()
        sendChat(phrases.random().replace("@name",player.name.capitalize()).replace("@gender", if (player.isMale) "Princess" else "Prince"))
    }

    override fun tick(){
        if(RandomFunction.random(1, 15) == 5){
            sendChat(phrases.random().replace("@name",player.name.capitalize()).replace("@gender", if (player.isMale) "Princess" else "Prince"))
        }
        super.tick()
    }

    override fun talkTo(npc: NPC){
        player.dialogueInterpreter.open(FrogHeraldDialogue(false), this.asNpc())
    }
}


