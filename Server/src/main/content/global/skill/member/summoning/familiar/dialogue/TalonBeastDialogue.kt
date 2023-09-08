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
 * Represents the Talon Beast familiar interaction dialogue.
 */
@Initializable
class TalonBeastDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, TalonBeastDialogueFile())
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.TALON_BEAST_7347, NPCs.TALON_BEAST_7348)
    }
}

class TalonBeastDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1, 2, 3, 4))

    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.TALON_BEAST_7347)
        if (randomConversation == 1) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Is this all you apes do all day, then?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Well, we do a lot of other things, too.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "That's dull. Lets go find something and bite it.").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "I wouldn't want to spoil my dinner.").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "So, I have to watch you trudge about again? Talk about boring.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 2) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "This place smells odd...").also { stage++ }
                1 -> playerl(FacialExpression.HALF_ASKING, "Odd?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Yes, not enough is rotting...").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "For which I am extremely grateful.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 3) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Hey!").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Aaaargh!").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Why d'you always do that?").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "I don't think I'll ever get used to having a huge, ravenous feline sneaking around behind me all the time.").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "That's okay, I doubt I'll get used to following an edible, furless monkey prancing in front of me all the time either.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 4) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "C'mon! Lets go fight stuff!").also { stage++ }
                1 -> playerl(FacialExpression.ASKING, "What sort of stuff?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "I dunno? Giants, monsters, vaguely-defined philosophical concepts. You know: stuff.").also { stage++ }
                3 -> playerl(FacialExpression.ASKING, "How are we supposed to fight a philosophical concept?").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "With subtle arguments and pointy sticks!").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "Well, I can see you're going to go far in debates.").also { stage = END_DIALOGUE }
            }
        }
    }
}