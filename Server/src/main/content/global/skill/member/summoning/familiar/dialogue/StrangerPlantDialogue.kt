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
 * Represents the Stranger Plant familiar interaction dialogue.
 */
@Initializable
class StrangerPlantDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, StrangerPlantDialogueFile())
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.STRANGER_PLANT_6827, NPCs.STRANGER_PLANT_6828)
    }
}

class StrangerPlantDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1, 2, 3, 4))

    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.STRANGER_PLANT_6827)
        if (randomConversation == 1) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "I'M STRANGER PLANT!").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "I know you are.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "I KNOW! I'M JUST SAYING!").also { stage++ }
                3 -> playerl(FacialExpression.HALF_ASKING, "Do you have to shout like that all of the time?").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "WHO'S SHOUTING?").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "If this is you speaking normally, I'd hate to hear you shouting.").also { stage++ }
                6 -> npcl(FacialExpression.OLD_NORMAL, "OH, SNAP!").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 2) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "WILL WE HAVE TO BE HERE LONG?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "We'll be here until I am finished.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "BUT THERE'S NO DRAMA HERE!").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Well, how about you pretend to be an undercover agent.").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "WONDERFUL! WHAT'S MY MOTIVATION?").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "You're trying to remain stealthy and secretive, while looking out for clues.").also { stage++ }
                6 -> npcl(FacialExpression.OLD_NORMAL, "I'LL JUST GET INTO CHARACTER! AHEM!").also { stage++ }
                7 -> npcl(FacialExpression.OLD_NORMAL, "PAPER! PAPER! VARROCK HERALD FOR SALE!").also { stage++ }
                8 -> playerl(FacialExpression.HALF_ASKING, "What kind of spy yells loudly like that?").also { stage++ }
                9 -> npcl(FacialExpression.OLD_NORMAL, "ONE WHOSE COVER IDENTITY IS A PAPER-SELLER, OF COURSE!").also { stage++ }
                10 -> playerl(FacialExpression.NEUTRAL, "Ask a silly question...").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 3) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "DIIIIVE!").also { stage++ }
                1 -> playerl(FacialExpression.HALF_ASKING, "What? Help! Why dive?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "OH, DON'T WORRY! I JUST LIKE TO YELL THAT FROM TIME TO TIME!").also { stage++ }
                3 -> playerl(FacialExpression.HALF_ASKING, "Well, can you give me a little warning next time?").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "WHAT, AND TAKE ALL THE FUN OUT OF LIFE?").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "If by 'fun' you mean 'sudden heart attacks', then yes, please take them out of my life!").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 4) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "I THINK I'M WILTING!").also { stage++ }
                1 -> playerl(FacialExpression.HALF_ASKING, "Do you need some water?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "DON'T BE SILLY! I CAN PULL THAT OUT OF THE GROUND!").also { stage++ }
                3 -> playerl(FacialExpression.HALF_ASKING, "Then why are you wilting?").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "IT'S SIMPLE: THERE'S A DISTINCT LACK OF DRAMA!").also { stage++ }
                5 -> playerl(FacialExpression.HALF_ASKING, "Drama?").also { stage++ }
                6 -> npcl(FacialExpression.OLD_NORMAL, "YES, DRAMA!").also { stage++ }
                7 -> playerl(FacialExpression.NEUTRAL, "Okay...").also { stage++ }
                8 -> playerl(FacialExpression.NEUTRAL, "Let's see if we can find some for you.").also { stage++ }
                9 -> npcl(FacialExpression.OLD_NORMAL, "LEAD ON!").also { stage = END_DIALOGUE }
            }
        }
    }
}