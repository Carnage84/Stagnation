package content.global.skill.member.summoning.familiar.dialogue

import config.NPCs
import core.api.openDialogue
import core.game.dialogue.DialogueFile
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE
import core.tools.RandomFunction

/**
 * Represents the Spirit Graahk familiar interaction dialogue.
 */
@Initializable
class SpiritGraahkDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, SpiritGraahkDialogueFile())
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.SPIRIT_GRAAHK_7363, NPCs.SPIRIT_GRAAHK_7364)
    }
}

class SpiritGraahkDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1,2,3,4))

    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.SPIRIT_GRAAHK_7363)
        if(randomConversation == 1){
            when (stage) {
                0 -> playerl(FacialExpression.NEUTRAL, "Your spikes are looking particularly spiky today.").also { stage++ }
                1 -> npcl(FacialExpression.OLD_DEFAULT, "Really, you think so?").also { stage++ }
                2 -> playerl(FacialExpression.NEUTRAL, "Yes. Most pointy, indeed.").also { stage++ }
                3 -> npcl(FacialExpression.OLD_DEFAULT, "That's really kind of you to say. I was going to spike you but I won't now...").also { stage++ }
                4 -> playerl(FacialExpression.NEUTRAL, "Thanks?").also { stage++ }
                5 -> npcl(FacialExpression.OLD_DEFAULT, "...I'll do it later instead.").also { stage++ }
                6 -> playerl(FacialExpression.NEUTRAL, "*sigh!*").also { stage = END_DIALOGUE }
            }
        }

        if(randomConversation == 2){
            when (stage) {
                0 -> npcl(FacialExpression.OLD_DEFAULT, "My spikes hurt, could you pet them for me?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Aww, of course I can I'll just... Oww! I think you drew blood that time.").also { stage = END_DIALOGUE }
            }
        }

        if(randomConversation == 3){
            when (stage) {
                0 -> npcl(FacialExpression.OLD_DEFAULT, "Hi!").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Hello. Are you going to spike me again?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_DEFAULT, "No, I got a present to apologise for last time.").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "That's really sweet, thank you.").also { stage++ }
                4 -> npcl(FacialExpression.OLD_DEFAULT, "Here you go, it's a special cushion to make you comfortable.").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "It's made of spikes!").also { stage++ }
                6 -> npcl(FacialExpression.OLD_DEFAULT, "Yes, but they're therapeutic spikes.").also { stage++ }
                7 -> playerl(FacialExpression.NEUTRAL, "...").also { stage = END_DIALOGUE }
                }
            }

        if(randomConversation == 4){
            when (stage) {
                0 -> playerl(FacialExpression.NEUTRAL, "How's your day going?").also { stage++ }
                1 -> npcl(FacialExpression.OLD_DEFAULT, "It's great! Actually I've got something to show you!").also { stage++ }
                2 -> playerl(FacialExpression.NEUTRAL, "Oh? What's that?").also { stage++ }
                3 -> npcl(FacialExpression.OLD_DEFAULT, "You'll need to get closer!").also { stage++ }
                4 -> playerl(FacialExpression.NEUTRAL, "I can't see anything...").also { stage++ }
                5 -> npcl(FacialExpression.OLD_DEFAULT, "It's really small - even closer.").also { stage++ }
                6 -> playerl(FacialExpression.NEUTRAL, "Oww! I'm going to have your spikes trimmed!").also { stage = END_DIALOGUE }
            }
        }
    }
}