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
 * Represents the Spirit Jelly familiar interaction dialogue.
 */
@Initializable
class SpiritJellyDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, SpiritJellyDialogueFile())
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.SPIRIT_JELLY_6992, NPCs.SPIRIT_JELLY_6993)
    }
}

class SpiritJellyDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1,2,3,4))

    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.SPIRIT_JELLY_6992)
        if(randomConversation == 1){
            when(stage){
                0 -> npcl(FacialExpression.OLD_NORMAL, "Play play play play!").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "The only game I have time to play is the 'Staying Very Still' game.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "But that game is soooooo booooooring...").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "How about we use the extra house rule, that makes it the 'Staying Very Still and Very Quiet' game.").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "Happy happy! I love new games!").also { stage = END_DIALOGUE }
            }
        }

        if(randomConversation == 2){
            when(stage){
                0 -> npcl(FacialExpression.OLD_NORMAL, "It's playtime now!").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Okay, how about we play the 'Staying Very Still' game.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "But that game is booooooring...").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "If you win then you can pick the next game, how about that?").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "Happy happy!").also { stage = END_DIALOGUE }
            }
        }

        if(randomConversation == 3){
            when(stage){
                0 -> npcl(FacialExpression.OLD_NORMAL, "Can we go over there now, pleasepleasepleasepleeeeeease?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Go over where?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "I dunno, someplace fun, pleasepleaseplease!").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Okay, but first, let's play the 'Sitting Very Still' game.").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "But that game is booooooring...").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "Well, if you win we can go somewhere else, okay?").also { stage++ }
                6 -> npcl(FacialExpression.OLD_NORMAL, "Happy happy!").also { stage = END_DIALOGUE }
            }
        }

        if(randomConversation == 4){
            when(stage){
                0 -> npcl(FacialExpression.OLD_NORMAL, "What game are we playing now?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "It's called the 'Staying Very Still' game.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "This game is booooooring...").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Hey, all that moping doesn't look very still to me.").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "I never win at this game...").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "You know what? I think I'll not count it this one time").also { stage++ }
                6 -> npcl(FacialExpression.OLD_NORMAL, "Happy happy! You're the best friend ever!").also { stage = END_DIALOGUE }
            }
        }
    }
}
