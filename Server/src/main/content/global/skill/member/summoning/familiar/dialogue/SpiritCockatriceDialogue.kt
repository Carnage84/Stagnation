package content.global.skill.member.summoning.familiar.dialogue

import config.Items
import config.NPCs
import core.api.inEquipment
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
 * Represents the Spirit Cockatrice familiar interaction dialogue.
 */
@Initializable
class SpiritCockatriceDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, SpiritCockatriceDialogueFile())
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.SPIRIT_COCKATRICE_6875, NPCs.SPIRIT_COCKATRICE_6876)
    }
}

class SpiritCockatriceDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1, 2, 3, 4))

    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.SPIRIT_COCKATRICE_6875)
        if (randomConversation == 1) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Is this what you do for fun?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Sometimes. Why, what do you do for fun?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "I find things and glare at them until they die!").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Well...everyone needs a hobby, I suppose.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 2) {
            when (stage) {
                0 -> playerl(FacialExpression.NEUTRAL, "You know, I think I might train as a hypnotist.").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Isn't that an odd profession for a cockatrice?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Not at all! I've already been practicing!").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Oh, really? How is that going?").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "Not good. I tell them to look in my eyes and that they are feeling sleepy.").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "I think I can see where this is headed.").also { stage++ }
                6 -> npcl(FacialExpression.OLD_NORMAL, "And then they just lie there and stop moving.").also { stage++ }
                7 -> playerl(FacialExpression.NEUTRAL, "I hate being right sometimes.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 3) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Come on, lets have a staring contest!").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "You win!").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Yay! I win again!").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Oh, it's no contest alright.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 4) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "You know, sometimes I don't think we're good friends.").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "What do you mean?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Well, you never make eye contact with me for a start.").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "What happened the last time someone made eye contact with you?").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "Oh, I petrified them really good! Ooooh...okay, point taken.").also { stage = END_DIALOGUE }
            }
        }

        if (inEquipment(player!!, Items.MIRROR_SHIELD_4156)) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "You know, I'm sensing some trust issues here.").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "I'm not sure I know what you are talking about.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "What are you holding?").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "A mirror shield.").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "And what do those do?").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "Mumblemumble...").also { stage++ }
                6 -> npcl(FacialExpression.OLD_NORMAL, "What was that?").also { stage++ }
                7 -> playerl(FacialExpression.NEUTRAL, "It protects me from your gaze attack.").also { stage++ }
                8 -> npcl(FacialExpression.OLD_NORMAL, "See! Why would you need one unless you didn't trust me?").also { stage++ }
                9 -> playerl(FacialExpression.NEUTRAL, "Who keeps demanding that we stop and have staring contests?").also { stage++ }
                10 -> npcl(FacialExpression.OLD_NORMAL, "How about we drop this and call it even?").also { stage++ }
                11 -> playerl(FacialExpression.NEUTRAL, "Fine by me.").also { stage = END_DIALOGUE }
            }
        }
    }
}