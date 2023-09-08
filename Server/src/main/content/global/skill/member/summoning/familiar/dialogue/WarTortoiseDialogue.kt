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
 * Represents the War Tortoise familiar interaction dialogue.
 */
@Initializable
class WarTortoiseDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, WarTortoiseDialogueFile())
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.WAR_TORTOISE_6815, NPCs.WAR_TORTOISE_6816)
    }
}

class WarTortoiseDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1, 2, 3, 4))

    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.WAR_TORTOISE_6815)
        if (randomConversation == 1) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"* The tortoise waggles its head about.* What are we doing in this dump?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Well, I was just going to take care of a few things.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"* The tortoise shakes its head.* I don't believe it. Stuck here with this young whippersnapper running around having fun.").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "You know, I'm sure you would enjoy it if you gave it a chance.").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL,"Oh, you would say that, wouldn't you?").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 2) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"Hold up a minute, there.").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "What do you want?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"*The tortoise bobs its head sadly.* For you to slow down!").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Well, I've stopped now.").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL,"Yes, but you'll soon start up again, won't you?").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "Probably.").also { stage++ }
                6 -> npcl(FacialExpression.OLD_NORMAL,"*The tortoise waggles its head despondently.* I don't believe it....").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 3) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"* The tortoise bobs its head around energetically.* Oh, so now you're paying attention to me, are you?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "I pay you plenty of attention!").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"Only when you want me to carry those heavy things of yours.").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "I don't ask you to carry anything heavy.").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL,"What about those lead ingots?").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "What lead ingots?").also { stage++ }
                6 -> npcl(FacialExpression.OLD_NORMAL,"*The tortoise droops its head.* Well, that's what it felt like....*grumble grumble*").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 4) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"*The tortoise exudes an air of reproach.* Are you going to keep rushing around all day?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Only for as long as I have the energy to.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"Oh. I'm glad that my not being able to keep up with you brings you such great amusement.").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "I didn't mean it like that.").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL,"*The tortoise waggles its head disapprovingly.* Well, when you are QUITE finished laughing at my expense, how about you pick up a rock larger than your body and go crawling about with it?").also { stage++ }
                5 -> npcl(FacialExpression.OLD_NORMAL,"We'll see how energetic you are after an hour or two of that.").also { stage = END_DIALOGUE }
            }
        }
    }
}