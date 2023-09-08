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
 * Represents the Spirit Tz-Kih familiar interaction dialogue.
 */
@Initializable
class SpiritTzKihDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, SpiritTzKihDialogueFile())
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.SPIRIT_TZ_KIH_7361, NPCs.SPIRIT_TZ_KIH_7362)
    }
}

class SpiritTzKihDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1, 2, 3, 4, 5))

    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.SPIRIT_TZ_KIH_7361)
        if (randomConversation == 1) {
            when (stage) {
                0 -> playerl(FacialExpression.NEUTRAL, "How's it going, Tz-kih?").also { stage++ }
                1 -> npcl(FacialExpression.OLD_NORMAL, "Pray pray?").also { stage++ }
                2 -> playerl(FacialExpression.NEUTRAL, "Don't start with all that again.").also { stage++ }
                3 -> npcl(FacialExpression.OLD_NORMAL, "Hmph, silly JalYt.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 2) {
            when(stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Does JalYt think Tz-kih as strong as Jad Jad?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Are you as strong as TzTok-Jad? Yeah, sure, why not.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Really? Thanks, JalYt. Tz-Kih strong and happy.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 3) {
            when(stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Have you heard of blood bat, JalYt?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Blood bats? You mean vampire bats?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Yes. Blood bat.").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Yes, I've heard of them. What about them?").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "Tz-Kih like blood bat, but drink pray pray not blood blood. Blood blood is yuck.").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "Thanks, Tz-Kih, that's nice to know.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 4) {
            when(stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Pray pray pray pray pray pray pray pray!").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Calm down, Tz-Kih, we'll find you something to drink soon.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Pray praaaaaaaaaaaaaay!").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Okay, okay. Calm down!").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 5) {
            when(stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "You drink pray, me drink pray.").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "What's that, Tz-Kih?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "You got pray pray pot. Tz-Kih drink pray pray you, you drink pray pray pot.").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "You want to drink my Prayer points?").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "Yes. Pray pray.").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "Err, not right now, Tz-Kih. I, er, need them myself.").also { stage++ }
                6 -> playerl(FacialExpression.NEUTRAL, "Sorry.").also { stage++ }
                7 -> npcl(FacialExpression.OLD_NORMAL, "But, pray praaaay...?").also { stage = END_DIALOGUE }
            }
        }
    }
}