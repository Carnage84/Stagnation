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
 * Represents the Spirit Dagannoth familiar interaction dialogue.
 */
@Initializable
class SpiritDagannothDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, SpiritDagannothDialogueFile())
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf( NPCs.SPIRIT_DAGANNOTH_6804, NPCs.SPIRIT_DAGANNOTH_6805)
    }
}

class SpiritDagannothDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1, 2, 3, 4))

    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.SPIRIT_DAGANNOTH_6804)
        if (randomConversation == 1) {
            when(stage){
                0 -> npcl(FacialExpression.OLD_NORMAL,"Grooooooowl graaaaawl raaaawl? (Are you ready to surrender to the power of the Deep Waters?)").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Err, not really.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"Rooooowl? (How about now?)").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "No, sorry.").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL,"Rooooowl? (How about now?)").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "No, sorry. You might want to try again a little later.").also { stage = END_DIALOGUE }
            }
        }
        if (randomConversation == 2) {
            when(stage){
                0 -> npcl(FacialExpression.OLD_NORMAL,"Groooooowl. Hsssssssssssssss! (The Deeps will swallow the lands. None will stand before us!)").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "What if we build boats?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"Hsssssssss groooooowl? Hssssshsss grrooooooowl? (What are boats? The tasty wooden containers full of meat?)").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "I suppose they could be described as such, yes.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 3) {
            when(stage){
                0 -> npcl(FacialExpression.OLD_NORMAL,"Hssssss graaaawl grooooowl, growwwwwwwwwl! (Oh how the bleak gulfs hunger for the Day of Rising.)").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "My brain hurts when I listen to you talk...").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"Raaaaawl groooowl grrrrawl! (That's the truth biting into your clouded mind!)").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Could you try using a little less truth please?").also { stage = END_DIALOGUE }
            }
        }
        if (randomConversation == 4) {
            when(stage){
                0 -> npcl(FacialExpression.OLD_NORMAL,"Raaaawl! (Submit!)").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Submit to what?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"Hssssssssss rawwwwwl graaaawl! (To the inevitable defeat of all life on the Surface!)").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "I think I'll wait a little longer before I just keep over and submit, thanks.").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL,"Hsssss, grooooowl, raaaaawl. (Well, it's your choice, but those that submit first will be eaten first.)").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "I'll pass on that one, thanks.").also { stage = END_DIALOGUE }
            }
        }
    }
}