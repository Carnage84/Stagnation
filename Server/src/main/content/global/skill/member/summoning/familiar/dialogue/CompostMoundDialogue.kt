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
 * Represents the Compost Mound familiar interaction dialogue.
 */
@Initializable
class CompostMoundDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, CompostMoundDialogueFile())
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.COMPOST_MOUND_6871, NPCs.COMPOST_MOUND_6872)
    }
}

class CompostMoundDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1, 2, 3, 4, 5))

    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.COMPOST_MOUND_6871)
        if (randomConversation == 1) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Oi've gotta braand new comboine 'aarvester!").also { stage++ }
                1 -> playerl(FacialExpression.HALF_ASKING, "A what?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Well, it's a flat bit a metal wi' a 'andle that I can use ta 'aarvest all combintions o' plaants.").also { stage++ }
                3 -> playerl(FacialExpression.HALF_ASKING, "You mean a spade?").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "Aye, 'aat'll be it.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 2) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "What we be doin' 'ere, zur?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Oh, I have a few things to take care of here, is all.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Aye, right ye are, zur. Oi'll be roight there.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 3) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Errr...are ye gonna eat that?").also { stage++ }
                1 -> playerl(FacialExpression.HALF_ASKING, "Eat what?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Y've got summat on yer, goin' wastin'.").also { stage++ }
                3 -> playerl(FacialExpression.DISGUSTED, "Ewwww!").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "So ye don' want it then?").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "No I do not want it! Nor do I want to put my boot in your mouth for you to clean it off.").also { stage++ }
                6 -> npcl(FacialExpression.OLD_NORMAL, "An' why not?").also { stage++ }
                7 -> playerl(FacialExpression.NEUTRAL, "It'll likely come out dirtier than when I put it in!").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 4) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Sigh...").also { stage++ }
                1 -> playerl(FacialExpression.HALF_ASKING, "What's the matter?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Oi'm not 'appy carryin' round these young'uns where we're going.").also { stage++ }
                3 -> playerl(FacialExpression.HALF_ASKING, "Young'uns? Oh, the buckets of compost! Well, those wooden containers will keep them safe.").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "'Aah, that be a mighty good point, zur.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 5) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Oi wus just a-wonderin'...").also { stage++ }
                1 -> playerl(FacialExpression.HALF_ASKING, "Oh! What have you been eating! Your breath is making my eyes water!").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Oi! Oi'm 'urt by thaat.").also { stage++ }
                3 -> playerl(FacialExpression.SAD, "Sorry.").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "Oi mean, oi even et some mints earlier.").also { stage++ }
                5 -> playerl(FacialExpression.HALF_ASKING, "You did?").also { stage++ }
                6 -> npcl(FacialExpression.OLD_NORMAL, "'At's roight. Oi found some mint plaants in a big pile o' muck, and oi 'ad 'em fer me breakfast.").also { stage++ }
                7 -> playerl(FacialExpression.NEUTRAL, "The mystery resolves itself.").also { stage = END_DIALOGUE }
            }
        }
    }
}