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
 * Represents the Iron Titan familiar interaction dialogue.
 */
@Initializable
class IronTitanDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, IronTitanDialogueFile())
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.IRON_TITAN_7375, NPCs.IRON_TITAN_7376)
    }
}

class IronTitanDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1, 2, 3, 4))

    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.IRON_TITAN_7375)
        if (randomConversation == 1) {
            when (stage) {
                0 -> playerl(FacialExpression.HALF_ASKING, "Titan?").also { stage++ }
                1 -> npcl(FacialExpression.OLD_NORMAL, "Yes, boss?").also { stage++ }
                2 -> playerl(FacialExpression.HALF_ASKING, "What's that in your hand?").also { stage++ }
                3 -> npcl(FacialExpression.OLD_NORMAL, "I'm glad you asked that, boss.").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "This is the prototype for the Iron Titan (tm) action figure. You just pull this string here and he fights crime with real action sounds.").also { stage++ }
                5 -> playerl(FacialExpression.ASKING, "Titan?").also { stage++ }
                6 -> npcl(FacialExpression.OLD_NORMAL, "Yes, boss?").also { stage++ }
                7 -> playerl(FacialExpression.STRUGGLE, "Never mind.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 2) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Boss!").also { stage++ }
                1 -> playerl(FacialExpression.HALF_ASKING, "What?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "I've just had a vision of the future.").also { stage++ }
                3 -> playerl(FacialExpression.FRIENDLY, "I didn't know you were a fortune teller. Let's hear it then.").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "Just imagine, boss, an Iron Titan (tm) on every desk.").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "That doesn't even make sense.").also { stage++ }
                6 -> npcl(FacialExpression.OLD_NORMAL, "Hmm. It was a bit blurry, perhaps the future is having technical issues at the moment.").also { stage++ }
                7 -> playerl(FacialExpression.NEUTRAL, "Riiight.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 3) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Boss?").also { stage++ }
                1 -> playerl(FacialExpression.HALF_ASKING, "Yes, titan?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "You know how you're the boss and I'm the titan?").also { stage++ }
                3 -> playerl(FacialExpression.HALF_ASKING, "Yes?").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "Do you think we could swap for a bit?").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "No, titan!").also { stage++ }
                6 -> npcl(FacialExpression.OLD_NORMAL, "Aww...").also { stage = END_DIALOGUE }
            }
        }


        if (randomConversation == 4) {
            when (stage) {
                0 -> playerl(FacialExpression.HALF_ASKING, "How are you today, titan?").also { stage++ }
                1 -> npcl(FacialExpression.OLD_NORMAL, "I'm very happy.").also { stage++ }
                2 -> playerl(FacialExpression.HALF_ASKING, "That's marvellous, why are you so happy?").also { stage++ }
                3 -> npcl(FacialExpression.OLD_NORMAL, "Because I love the great taste of Iron Titan (tm) cereal!").also { stage++ }
                4 -> playerl(FacialExpression.ASKING, "?").also { stage++ }
                5 -> playerl(FacialExpression.ASKING, "You're supposed to be working for me, not promoting yourself.").also { stage++ }
                6 -> npcl(FacialExpression.OLD_NORMAL, "Sorry, boss.").also { stage = END_DIALOGUE }
            }
        }


        if (inInventory(player!!, Items.IRON_BAR_2351)) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Are you using that iron bar, boss?").also { stage++ }
                1 -> playerl(FacialExpression.HALF_ASKING, "Well, not right now, why?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Can I have it, then?").also { stage++ }
                3 -> playerl(FacialExpression.HALF_ASKING, "What for?").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "I've got a cunning plan.").also { stage++ }
                5 -> playerl(FacialExpression.HALF_ASKING, "Involving my iron bar?").also { stage++ }
                6 -> npcl(FacialExpression.OLD_NORMAL, "No, but if I sell your iron bar I'll have enough money to buy a new hat.").also { stage++ }
                7 -> npcl(FacialExpression.OLD_NORMAL, "You can't go through with a cunning plan without the right headgear, boss!").also { stage = END_DIALOGUE }
            }
        }
    }
}