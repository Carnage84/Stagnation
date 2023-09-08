package content.global.skill.member.summoning.familiar.dialogue

import config.Items
import config.NPCs
import core.api.inInventory
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
 * Represents the Bronze Minotaur familiar interaction dialogue.
 */
@Initializable
class BronzeMinotaurDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, BronzeMinotaurDialogueFile())
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.BRONZE_MINOTAUR_6853, NPCs.BRONZE_MINOTAUR_6854)
    }
}

class BronzeMinotaurDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1, 2, 3, 4))

    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.BRONZE_MINOTAUR_6853)
        if (randomConversation == 1) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "All this walking about is making me angry.").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "You seem to be quite happy about that.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Yeah! There's nothing like getting a good rage on and then working it out on some no-horns.").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "I can't say I know what you mean.").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "Well I didn't think a no-horns like you would get it!").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 2) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Can you tell me why we're not fighting yet?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Buck up; I'll find you something to hit soon.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "You'd better, no-horns, because that round head of yours is looking mighty axeable.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 3) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Hey, no-horns?").also { stage++ }
                1 -> playerl(FacialExpression.HALF_ASKING, "Why do you keep calling me no-horns?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Do I really have to explain that?").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "No, thinking about it, it's pretty self-evident.").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "Glad we're on the same page, no-horns.").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "So, what did you want?").also { stage++ }
                6 -> npcl(FacialExpression.OLD_NORMAL, "I've forgotten, now. I'm sure it'll come to me later.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 4) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Hey no-horns!").also { stage++ }
                1 -> playerl(FacialExpression.HALF_ASKING, "Yes?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Oh, I don't have anything to say, I was just yelling at you.").also { stage++ }
                3 -> playerl(FacialExpression.HALF_ASKING, "Why?").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "No reason. I do like to mess with the no-horns, though.").also { stage = END_DIALOGUE }
            }
        }

        if (inInventory(player!!, Items.GUTHANS_HELM_4724)) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "...").also { stage++ }
                1 -> playerl(FacialExpression.HALF_ASKING, "What?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Are you having a laugh?").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "I'm not sure I know what you-").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "Listen, no-horns, you have two choices: take off the horns yourself or I'll headbutt you until they fall off.").also { stage++ }
                5 -> playerl(FacialExpression.FRIENDLY, "Yessir.").also { stage++ }
                6 -> npcl(FacialExpression.OLD_NORMAL, "Good, no-horns. Let's not have this conversation again.").also { stage = END_DIALOGUE }
            }
        }
    }
}