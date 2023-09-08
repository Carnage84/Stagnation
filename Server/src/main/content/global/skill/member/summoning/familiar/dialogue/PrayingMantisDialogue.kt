package content.global.skill.member.summoning.familiar.dialogue

import config.Items
import config.NPCs
import core.api.inEquipmentOrInventory
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
 * Represents the Praying Mantis familiar interaction dialogue.
 */
@Initializable
class PrayingMantisDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, PrayingMantisDialogueFile())
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf( NPCs.PRAYING_MANTIS_6798, NPCs.PRAYING_MANTIS_6799)
    }
}

class PrayingMantisDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1, 2, 3, 4))

    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.PRAYING_MANTIS_6798)
        if (randomConversation == 1) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"Chitter chirrup chirrup? (Have you been following your training, grasshopper?)").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Yes, almost every day.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"Chirrupchirrup chirrup. ('Almost' is not good enough.)").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Well, I'm trying as hard as I can.").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL,"Chirrup chitter chitter chirrup? (How do you expect to achieve enlightenment at this rate, grasshopper?)").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "Spontaneously.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 2) {
            when (stage) {

                0 -> npcl(FacialExpression.OLD_NORMAL,"Chitterchitter chirrup clatter. (Today, grasshopper, I will teach you to walk on rice paper.)").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "What if I can't find any?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"Clatter chitter click chitter... (Then we will wander about and punch monsters in the head...)").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "I could do in an enlightened way if you want?").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL,"Chirrupchitter! (That will do!)").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 3) {
            when (stage) {

                0 -> npcl(FacialExpression.OLD_NORMAL,"Clatter chirrup chirp chirrup clatter clatter. (A wise man once said; 'Feed your mantis and it will be happy'.)").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Is there any point to that saying?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"Clatter chirrupchirrup chirp. (I find that a happy mantis is its own point.)").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 4) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"Clatter chirrupchirp- (Today, grasshopper, we will-)").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "You know, I'd rather you call me something other than grasshopper.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"Clitterchirp? (Is there a reason for this?)").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "You drool when you say it.").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL,"Clickclatter! Chirrup chirpchirp click chitter... (I do not! Why would I drool when I call you a juicy...)").also { stage++ }
                5 -> npcl(FacialExpression.OLD_NORMAL,"...clickclick chitter clickchitter click... (...succulent, nourishing, crunchy...)").also { stage = END_DIALOGUE }
                6 -> npcl(FacialExpression.OLD_NORMAL,"*Drooool*").also { stage++ }
                7 -> playerl(FacialExpression.NEUTRAL, "You're doing it again!").also { stage++ }
            }
        }

        if(inEquipmentOrInventory(player!!, Items.BUTTERFLY_NET_10010) || inEquipmentOrInventory(player!!, Items.MAGIC_BUTTERFLY_NET_11259)){
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"Clatter click chitter click? (Wouldn't you learn focus better if you used chopsticks?)").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Huh?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"Clicker chirrpchirrup. (For catching the butterflies, grasshopper.)").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Oh, right! Well, if I use anything but the net I squash them.").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL,"Chirrupchirrup click! (Then, I could have them!)").also { stage = END_DIALOGUE }
            }
        }
    }
}