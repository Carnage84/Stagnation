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
 * Represents the Barker Toad familiar interaction dialogue.
 */
@Initializable
class BarkerToadDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, BarkerToadDialogueFile())
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.BARKER_TOAD_6889, NPCs.BARKER_TOAD_6890)
    }
}

class BarkerToadDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1, 2, 3, 4, 5, 6))

    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.BARKER_TOAD_6889)
        if (randomConversation == 1) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"Ladies and gentlemen, for my next trick, I shall swallow this fly!").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Seen it.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"Ah, but last time was the frog...on fire?").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "No! That would be a good trick.").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL,"Well, it won't be this time either.").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "Awwwww...").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 2) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"Roll up, roll up, roll up! See the greatest show on Gielinor!").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Where?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"Well, it's kind of...you.").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Me?").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL,"Roll up, roll up, roll up! See the greatest freakshow on Gielinor!").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "Don't make me smack you, slimy.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 3) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"We need to set up the big top somewhere near here. The locals look friendly enough.").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Are you kidding?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"Your problem is that you never see opportunities.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 4) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"Braaaaaaaaaaaaaaaaaaaaaaap! (*Burp!*)").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "That's disgusting behaviour!").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"Braap craaaaawk craaaawk. (That, my dear boy, was my world-renowned belching.)").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "I got that part. Why are you so happy about it?").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL,"Braaaaaaap craaaaaawk craaaaaaaawk. (My displays have bedazzled the crowned heads of Gielinor.)").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "I'd give you a standing ovation, but I have my hands full.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 5) {
            when (stage) {
                1 -> npcl(FacialExpression.OLD_NORMAL,"Mumblemumblegrumblemumble... (*Inaudible mumbles*)").also { stage++ }
                2 -> playerl(FacialExpression.NEUTRAL, "Well, that cannonball seems to have shut him up!").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 6) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"Bwaaarp graaaawk? (What's that croaking in your inventory?)").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Ah, you mean that toad?").also { stage++ }
                2 -> playerl(FacialExpression.NEUTRAL, "Oh, I'm guessing you're not going to like me carrying a toad about.").also { stage++ }
                3 -> npcl(FacialExpression.OLD_NORMAL,"Craaawk, croak. (I might not be all that happy, no.)").also { stage++ }
                4 -> playerl(FacialExpression.NEUTRAL, "I'm not going to eat it.").also { stage++ }
                5 -> npcl(FacialExpression.OLD_NORMAL,"Craaaaawk braaap croak. (Weeeeell, I'd hope not! Reminds me of my mama toad. She was inflated and fed to a jubbly, you know. A sad, demeaning way to die.)").also { stage = END_DIALOGUE }
            }
        }
    }
}