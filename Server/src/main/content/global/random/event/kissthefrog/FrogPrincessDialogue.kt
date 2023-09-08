package content.global.random.event.kissthefrog

import config.NPCs
import core.api.*
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.player.Player
import core.game.system.task.Pulse
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the Frog Prince/Princess dialogue plugin.
 */
@Initializable
class FrogPrincessDialogue(player: Player? = null) : DialoguePlugin(player){

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        val event = player!!.getAttribute(FrogUtils.frogFail, 0)
        if (event == 0) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_SAD, "${player!!.username}, you must help me! I have been turned into a frog by a well-meaning wizard who suffers from an unfortunate obsession with frogs.").also { stage++ }
                1 -> npcl(FacialExpression.OLD_NORMAL, "The only thing that will restore my true form is a kiss.").also { stage++ }
                2 -> interpreter.sendDialogues(player,FacialExpression.LAUGH,"Excuses, excuses!","Okay, If that's what you want...").also { stage++ }
                3 -> {
                    lock(player, 10)
                    player!!.pulseManager.run(object : Pulse() {
                        var counter = 0
                        override fun pulse(): Boolean {
                            when (counter++) {
                                1 -> animate(player, FrogUtils.TRANSFORMATION)
                                3 -> if (player.isMale) {
                                    npc.transform(FrogUtils.FROG_PRINCESS_NPC);visualize(npc, FrogUtils.GET_BACK_ANIM_1, FrogUtils.GET_BACK_GFX)
                                } else {
                                    npc.transform(FrogUtils.FROG_PRINCE_NPC);visualize(npc, FrogUtils.GET_BACK_ANIM_1, FrogUtils.GET_BACK_GFX)
                                }

                                5 -> if (player.isMale) {
                                    face(npc, player, 1)
                                    sendNPCDialogue(player, FrogUtils.FROG_PRINCESS_NPC, "Thank you so much, ${player.username}. I must return to my fairy tale kingdom now, but I will, leave you a reward for your kindness.", FacialExpression.HAPPY).also { stage++ }
                                } else {
                                    face(npc, player, 1)
                                    sendNPCDialogue(player, FrogUtils.FROG_PRINCE_NPC, "Thank you so much, ${player.username}. I must return to my fairy tale kingdom now, but I will, leave you a reward for your kindness.", FacialExpression.HAPPY).also { stage++ }
                                }

                                7 -> visualize(npc, FrogUtils.BLOW_KISS_ANIM, FrogUtils.BLOW_KISS_GFX)
                                9 -> {
                                    end()
                                    FrogUtils.cleanup(player!!)
                                    npc!!.reTransform()
                                    player.interfaceManager.restoreTabs()
                                    addItemOrDrop(player, FrogUtils.frogToken)
                                    stage = END_DIALOGUE
                                }
                            }
                            return false
                        }
                    })
                    return true
                }
            }
        } else {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_SAD, "Hmph. Have you come to apologize for ignoring me?").also { stage++ }
                1 -> playerl(FacialExpression.OLD_NORMAL, "I'm very sorry. Please change me back!").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "All right. But in future be more polite.").also { stage++ }
                3 -> {
                    lock(player, 2)
                    player!!.pulseManager.run(object : Pulse() {
                        var counter = 0
                        override fun pulse(): Boolean {
                            when (counter++) {
                                1 -> {
                                    end()
                                    FrogUtils.cleanup(player!!)
                                    player!!.appearance.transformNPC(-1)
                                    player!!.interfaceManager.restoreTabs()
                                    playerl(FacialExpression.ANGRY_WITH_SMILE, "No, I haven't.").also { stage = END_DIALOGUE }
                                }
                            }
                            return false
                        }
                    })
                    return true
                }
            }
        }
        return true
    }

    override fun newInstance(player: Player?): DialoguePlugin{
        return FrogPrincessDialogue(player)
    }

    override fun getIds():IntArray = intArrayOf(NPCs.FROG_2469)
}