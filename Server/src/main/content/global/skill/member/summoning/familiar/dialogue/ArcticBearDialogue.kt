package content.global.skill.member.summoning.familiar.dialogue

import config.NPCs
import core.api.openDialogue
import core.api.sendDialogue
import core.game.dialogue.DialogueFile
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE
import core.tools.RandomFunction

/**
 * Represents the Arctic Bear familiar interaction dialogue.
 */
@Initializable
class ArcticBearDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, ArcticBearDialogueFile())
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.ARCTIC_BEAR_6839, NPCs.ARCTIC_BEAR_6840)
    }
}

class ArcticBearDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1, 2, 3, 4, 5))

    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.ARCTIC_BEAR_6839)
        if (randomConversation == 1) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"Crikey! We're tracking ourselves a real live one here. I call 'em 'Brighteyes'.").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Will you stop stalking me like that?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"Lookit that! Something's riled this one up good and proper.").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Who are you talking to anyway?").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL,"Looks like I've been spotted.").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "Did you think you didn't stand out here or something?").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 2) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"Crikey! Something seems to have startled Brighteyes, here.").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "What? What's happening?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"Maybe he's/she's scented a rival.").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "I smell something, but it's not a rival.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 3) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"We're tracking Brighteyes here as he/she goes about his/her daily routine.").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "My name is Player, not Brighteyes!").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"Looks like the little critter's upset about something.").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "I wonder if he'd be quiet if I just did really boring stuff.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 4) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"These little guys get riled up real easy.").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Who wouldn't be upset with a huge bear tracking along behind them, commenting on everything they do?").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 5) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"I'm going to use this snow to blend in and get closer to this little feller.").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "I'm looking right at you. I can still see you, you know.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"I don't think they can see me...").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "*Siiiigh*").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL,"So, I'm gonna get a little closer and see if I can rile 'em up.").also { stage++ }
                5 -> sendDialogue(player!!, "The bear nudges you in the stomach.").also { stage++ }
                6 -> playerl(FacialExpression.NEUTRAL, "Hey!").also { stage++ }
                7 -> npcl(FacialExpression.OLD_NORMAL,"Willya lookit that! Lookit them teeth; I'd be a goner if it got hold of me!").also { stage++ }
                8 -> playerl(FacialExpression.NEUTRAL, "You have no idea how true that is.").also { stage = END_DIALOGUE }
            }
        }
    }
}