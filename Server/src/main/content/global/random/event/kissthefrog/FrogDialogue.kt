package content.global.random.event.kissthefrog

import config.NPCs
import core.api.lock
import core.api.sendNPCDialogue
import core.api.visualize
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.game.system.task.Pulse
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the Frog dialogue plugin.
 */
@Initializable
class FrogDialogue(player: Player? = null) : DialoguePlugin(player){
    override fun handle(interfaceId: Int, buttonId: Int): Boolean{
        npc = NPC(NPCs.FROG_2472)
        val failRandom = (player!!.getAttribute(FrogUtils.frogFail, 0))
        if(failRandom == 1){
            when(stage){
                0 ->npcl(FacialExpression.OLD_SAD, "Hmph. Have you come to apologize for ignoring me?").also{stage++}
                1 ->playerl(FacialExpression.OLD_NORMAL, "I'm very sorry. Please change me back!").also{stage++}
                2 ->npcl(FacialExpression.OLD_NORMAL, "All right. But in future be more polite.").also{stage++}
                3 ->{
                    lock(player, 2)
                    player!!.pulseManager.run(object : Pulse(){
                        var counter = 0
                        override fun pulse(): Boolean{
                            when(counter++){
                                1 ->{
                                    end()
                                    FrogUtils.cleanup(player!!)
                                }
                                2 -> playerl(FacialExpression.ANGRY_WITH_SMILE, "No, I haven't.").also{stage = END_DIALOGUE}
                            }
                            return false
                        }
                    })
                }
            }
        }else{
            when(stage){
                0 ->sendNPCDialogue(player!!, NPCs.FROG_2471, "Well, we'll see how you like being a frog!", FacialExpression.OLD_NORMAL).also{stage++}
                1 ->{
                    lock(player, 2)
                    player!!.pulseManager.run(object : Pulse(){
                        var counter = 0
                        override fun pulse(): Boolean {
                            when(counter++){
                                0 -> visualize(player, FrogUtils.TRANSFORMATION,FrogUtils.GET_BACK_GFX)
                                1 ->{
                                    end()
                                    player!!.appearance.transformNPC(FrogUtils.FROG_APPEARANCE)
                                    player!!.setAttribute(FrogUtils.frogFail, 1)
                                    stage = END_DIALOGUE
                                }
                            }
                            return false
                        }
                    })
                }
            }
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.FROG_2472)
    }
}

