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
 * Represents the Giant Chinchompa familiar interaction dialogue.
 */
@Initializable
class GiantChinchompaDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, GiantChinchompaDialogueFile())
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.GIANT_CHINCHOMPA_7353, NPCs.GIANT_CHINCHOMPA_7354)
    }
}

class GiantChinchompaDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1, 2, 3, 4))

    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.GIANT_CHINCHOMPA_7353)
        if (randomConversation == 1) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Half a pound of tuppenny rice, half a pound of treacle...").also { stage++ }
                1 -> playerl(FacialExpression.HALF_ASKING, "I hate it when you sing that song.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "...that's the way the money goes...").also { stage++ }
                3 -> playerl(FacialExpression.HALF_ASKING, "Couldn't you sing 'Kumbaya' or something?").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "...BANG, goes the chinchompa!").also { stage++ }
                5 -> playerl(FacialExpression.HALF_ASKING, "Sheesh.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 2) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "What's small, brown and blows up?").also { stage++ }
                1 -> playerl(FacialExpression.HALF_ASKING, "A brown balloon?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "A chinchompa! Pull my finger.").also { stage++ }
                3 -> playerl(FacialExpression.HALF_ASKING, "I'm not pulling your finger.").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "Nothing will happen. Truuuuust meeeeee.").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "Oh, go away.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 3) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "I spy, with my little eye, something beginning with 'B'.").also { stage++ }
                1 -> playerl(FacialExpression.HALF_ASKING, "Bomb? Bang? Boom? Blowing-up-little-chipmunk?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "No. Body odour. You should wash a bit more.").also { stage++ }
                3 -> playerl(FacialExpression.HALF_ASKING, "Well, that was pleasant. You don't smell all that great either, you know.").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "Stop talking, stop talking! Your breath stinks!").also { stage++ }
                5 -> playerl(FacialExpression.HALF_ASKING, "We're never going to get on, are we?").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 4) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "I seem to have found a paper bag.").also { stage++ }
                1 -> playerl(FacialExpression.HALF_ASKING, "Well done. Anything in it?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Hmmm. Let me see. It seems to be full of some highly sought after, very expensive...chinchompa breath!").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "No, don't pop it!").also { stage++ }
                4 -> playerl(FacialExpression.HALF_ASKING, "You just cannot help yourself, can you?").also { stage = END_DIALOGUE }
            }
        }

        if (inInventory(player!!, Items.CHINCHOMPA_9976)) {
            when (stage) {
                0 ->npcl(FacialExpression.OLD_NORMAL, "Woah, woah, woah - hold up there.").also { stage++ }
                1 ->playerl(FacialExpression.HALF_ASKING, "What is it, ratty?").also { stage++ }
                2 ->npcl(FacialExpression.OLD_NORMAL, "You got something in your backpack that you'd like to tell me about?").also { stage++ }
                3 ->playerl(FacialExpression.HALF_ASKING, "I was wondering when you were going to bring up the chinchompa. I'm sure they like it in my inventory.").also { stage++ }
                4 ->npcl(FacialExpression.OLD_NORMAL, "Did they not teach you anything in school? Chinchompas die in hot bags. You know what happens when chinchompas die. Are you attached to your back?").also { stage++ }
                5 ->playerl(FacialExpression.HALF_ASKING, "Medically, yes. And I kind of like it too. I get the point.").also { stage = END_DIALOGUE }
            }
        }
    }
}