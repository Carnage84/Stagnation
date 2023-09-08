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
 * Represents the Swamp Titan familiar interaction dialogue.
 */
@Initializable
class SwampTitanDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, SwampTitanDialogueFile())
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf( NPCs.SWAMP_TITAN_7329, NPCs.SWAMP_TITAN_7330)
    }
}

class SwampTitanDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1, 2, 3, 4))

    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.SWAMP_TITAN_7329)
        if (randomConversation == 1) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"I’m alone, all alone I say.").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Oh, stop being so melodramatic.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"It’s not easy being greenery…well, decomposing greenery.").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Surely, you’re not the only swamp…thing in the world? What about the other swamp titans?").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL,"They’re not my friends…they pick on me…they’re so mean...").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "Why would they do that?").also { stage++ }
                6 -> npcl(FacialExpression.OLD_NORMAL,"They think I DON’T smell.").also { stage++ }
                7 -> playerl(FacialExpression.NEUTRAL, "Oh, yes. That is, er, mean…").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 2) {
            when (stage) {
                0  -> npcl(FacialExpression.OLD_NORMAL,"Oh, Guthix! I’m so alone, I have no fr").also { stage++ }
                1  -> playerl(FacialExpression.NEUTRAL, "Oh, not again. Look, I’ll be your friend.").also { stage++ }
                2  -> npcl(FacialExpression.OLD_NORMAL,"You’ll be my friend, master?").also { stage++ }
                3  -> playerl(FacialExpression.NEUTRAL, "Yeah, sure, why not.").also { stage++ }
                4  -> npcl(FacialExpression.OLD_NORMAL,"Really?").also { stage++ }
                5  -> playerl(FacialExpression.NEUTRAL, "Really really…").also { stage++ }
                6  -> npcl(FacialExpression.OLD_NORMAL,"Oh, I’m so happy!").also { stage++ }
                7  -> playerl(FacialExpression.NEUTRAL, "…even if you do smell like a bog of eternal stench.").also { stage++ }
                8  -> npcl(FacialExpression.OLD_NORMAL,"Wait…you think I smell bad?").also { stage++ }
                9  -> playerl(FacialExpression.NEUTRAL, "Erm, yes, but I didn’t me").also { stage++ }
                10 -> npcl(FacialExpression.OLD_NORMAL,"Oh, that’s the nicest thing anyone’s ever said to me! Thank you, master, thank you so much. You’re my friend AND you think I smell. I’m so very happy!").also { stage++ }
                11 -> playerl(FacialExpression.NEUTRAL, "I guess I did mean it like that.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 3) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"Are you my friend, master?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Of course I am. I summoned you, didn’t I?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"Yes, but that was just to do some fighting. When you’re done with me you’ll send me back.").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "I’m sure I’ll need you again later.").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL,"Please don’t send me back.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 4) {
            when (stage) {
                0 -> playerl(FacialExpression.NEUTRAL, "Cheer up, it might never happen!").also { stage++ }
                1 -> npcl(FacialExpression.OLD_NORMAL,"Oh, why did you have to go and say something like that?").also { stage++ }
                2 -> playerl(FacialExpression.NEUTRAL, "Like what? I’m trying to cheer you up.").also { stage++ }
                3 -> npcl(FacialExpression.OLD_NORMAL,"There’s no hope for me, oh woe, oh woe.").also { stage++ }
                4 -> playerl(FacialExpression.NEUTRAL, "I’ll leave you alone, then.").also { stage++ }
                5 -> npcl(FacialExpression.OLD_NORMAL,"NO! Don’t leave me, master!").also { stage = END_DIALOGUE }
            }
        }
        if(inInventory(player!!, Items.SWAMP_TAR_1939)){
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"Do you smell that? Swamp tar, master. I LOVE the smell of swamp tar in the morning. Smells like...victorin.").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "You actually LIKE the smell of this stuff? It's gross.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"Of course! I am made of swamp, after all.").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Oh, I'm sorry. I didn't mean...I meant the swamp tar itself smells gross, not you. You smell like lavender. Yes, lavender.").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL,"*sob* Lavender? Lavender! Why would you be so mean? I'm supposed to smell bad.").also { stage = END_DIALOGUE }
            }
        }
    }
}