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
 * Represents the Obsidian Golem familiar interaction dialogue.
 */
@Initializable
class ObsidianGolemDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, ObsidianGolemDialogueFile())
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.OBSIDIAN_GOLEM_7345, NPCs.OBSIDIAN_GOLEM_7346)
    }
}

class ObsidianGolemDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1, 2, 3, 4))

    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.OBSIDIAN_GOLEM_7345)
        if (randomConversation == 1) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"Let us go forth and prove our strength, Master!").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Where would you like to prove it?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"The caves of the TzHaar are filled with monsters for us to defeat, Master! TzTok-Jad shall quake in his slippers!").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Have you ever met TzTok-Jad?").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL,"Alas, Master, I have not. No Master has ever taken me to see him.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 2) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"How many foes have you defeated, Master?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Quite a few, I should think.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"Was your first foe as mighty as the volcano, Master?").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Um, not quite.").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL,"I am sure it must have been a deadly opponent, Master!").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "*Cough* It might have been a chicken. *Cough*").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 3) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"Master! We are truly a mighty duo!").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Do you think so?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"Of course, Master! I am programmed to believe so.").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Do you do anything you're not programmed to?").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL,"No, Master.").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "I guess that makes things simple for you...").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 4) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"Do you ever doubt your programming, Master?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "I don't have programming. I can think about whatever I like.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"What do you think about, Master?").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Oh, simple things: the sound of one hand clapping, where the gods come from...Simple things.").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL,"Paradox check = positive. Error. Reboot.").also { stage = END_DIALOGUE }
            }
        }

        if(inEquipment(player!!, Items.FIRE_CAPE_6570)){
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"Truly, you are a powerful warrior, Master!").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "I'm pleased you think so.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"It is my duty to respect you, Master.").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Oh, So you're just saying that to make me happy...").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL,"I obey all orders, Master.").also { stage = END_DIALOGUE }
            }
        }
    }
}