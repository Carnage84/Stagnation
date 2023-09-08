package content.region.kandarin.gnomestronghold.dialogue

import config.NPCs
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the Spang dialogue plugin.
 */
@Initializable
class SpangDialogue(player: Player? = null) : DialoguePlugin(player){

    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        npcl(FacialExpression.OLD_NORMAL, "I hate this job. All the other staff are so happy all the time. It depresses me.").also { stage = 0 }
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when(stage){
            0 -> playerl(FacialExpression.FRIENDLY, "Why don't you get a new job?").also { stage++ }
            1 -> npcl(FacialExpression.OLD_NORMAL,"I can't find any other work. I wanted to be a Gnomeball player but I wasn't big enough. I came up with some clever tricks using my feet, but I was told they weren't fit for gnomeball.").also { stage++ }
            2 -> npcl(FacialExpression.OLD_NORMAL,"'Gnomeball is played with the hands,' they said. Well, even Mad Donna isn't as good as I am with my feet.").also { stage++ }
            3 -> options("Can you show me your tricks?", "Who is Mad Donna?", "Goodbye.").also { stage++ }
            4 -> when(buttonId){
                1 -> playerl(FacialExpression.FRIENDLY,"Can you show me your tricks?").also { stage = 5 }
                2 -> playerl(FacialExpression.NEUTRAL,"Who is Mad Donna?").also { stage = 6 }
                3 -> playerl(FacialExpression.FRIENDLY,"Goodbye.").also { stage = 8 }
            }
            5 -> npcl(FacialExpression.OLD_NORMAL, "I'd love to, but I'm supposed to be at work right now. Besides, the other gnomes laugh at me. They don't appreciate skill when they see it!").also { stage = 3 }
            6 -> npcl(FacialExpression.OLD_NORMAL, "Mad Donna is a genius with a gnomeball! She invented a trick using everything except the hands to control a gnomeball. It's called the Mad Donna Seven.").also { stage++ }
            7 -> npcl(FacialExpression.OLD_NORMAL,"Other gnomes call her mad because her trick doesn't involve holding the gnomeball. But she's my inspiration to invent new and exciting tricks!").also { stage = 3 }
            8 -> npcl(FacialExpression.OLD_NORMAL,"OK, whatever.").also { stage++ }
            9 -> playerl(FacialExpression.HALF_ASKING,"Aren't you going to wish me a nice day?").also { stage++ }
            10 -> npcl(FacialExpression.OLD_NORMAL, "Have a day. It can be nice if you can be bothered. I know I can't.").also { stage++ }
            11 -> playerl(FacialExpression.NEUTRAL,"... nice.").also { stage = END_DIALOGUE }
        }
        return true
    }

    override fun newInstance(player: Player?): DialoguePlugin {
        return SpangDialogue(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.SPANG_4575)
    }
}