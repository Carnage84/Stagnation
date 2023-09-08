package content.quest.member.horrorfromthedeep.dialogue

import config.Items
import config.NPCs
import content.quest.member.horrorfromthedeep.HFTDUtils
import core.api.*
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the Gunnjorn dialogue plugin for Horror from the Deep quest.
 */
@Initializable
class GunnjornHFTDDialogue(player: Player? = null) : DialoguePlugin(player) {
    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when(stage) {
            0 -> if (getQuestStage(player, "Horror from the Deep") >= 1) {
                options("Talk about Horror from the Deep.", "Talk about the Agility course.", "Talk about the wall after the log balance.", "Nothing.").also { stage++ }
            } else {
                options("Talk about the Agility course.", "Talk about the wall after the log balance.", "Nothing.").also { stage++ }
            }
            1 -> when (buttonId) {
                1 -> npcl(FacialExpression.FRIENDLY, "Haha welcome to my obstacle course. Have fun, but remember this isn't a child's playground. People have died here.").also { stage++ }
                2 -> playerl(FacialExpression.FRIENDLY, "Talk about the Agility course.").also { stage = 9 }
                3 -> playerl(FacialExpression.FRIENDLY, "Talk about the wall after the log balance.").also { stage = 11 }

                4 -> playerl(FacialExpression.FRIENDLY, "Nothing.").also { stage = 17 }
            }
            2 -> playerl(FacialExpression.FRIENDLY, "Hi, are you called Gunnjorn?").also { stage = 4 }
            4 -> npcl(FacialExpression.FRIENDLY, "Why, indeed I am. I own this agility course, it can be very dangerous!").also { stage++ }
            5 -> playerl(FacialExpression.FRIENDLY, "Yeah, that's great. Anyway, I understand you have a cousin named Larrissa who gave you a key...?").also { stage++ }
            6 -> npcl(FacialExpression.FRIENDLY, "Yes, she did! How did you know of this? She said she probably wouldn't need it, but gave it to me for safe keeping just in case.").also { stage++ }
            7 -> playerl(FacialExpression.FRIENDLY, "Well, something has happened at the lighthouse, and she has been locked out. I need you to give me her key.").also { stage++ }
            8 -> if (freeSlots(player) < 1) {
                npcl(FacialExpression.FRIENDLY, "Well, I would give you the key, but apparently you don't have any room.").also { stage = END_DIALOGUE }
            } else {
                npcl(FacialExpression.FRIENDLY, "Sure. Here you go.").also { stage = END_DIALOGUE }
                addItemOrDrop(player, Items.LIGHTHOUSE_KEY_3848)
                setAttribute(player, HFTDUtils.GET_LIGHTHOUSE_KEY, true)
                setQuestStage(player, "Horror from the Deep", 4)
            }
            9 -> playerl(FacialExpression.FRIENDLY, "Hey there. What is this place?").also { stage++ }
            10 -> npcl(FacialExpression.FRIENDLY, "Aha! Welcome to my obstacle course. Have fun, but remember this isn't a children's playground. People have died here. This course starts at the ropeswings to the east. When you've done the swing, head across the slippery log to the building. When you've traveresed the obstacles inside, you'll come out on the other side. From there, head across the low walls to finish. If you've done all the obstacles as I've described, and in order, you'll get a lap bonus.").also { stage = END_DIALOGUE }
            11 -> playerl(FacialExpression.FRIENDLY, "What's wrong with the wall after the log balance?").also { stage++ }
            12 -> npcl(FacialExpression.FRIENDLY, "The wall after the log balance? Nothing, really. I just put some tough material on it, giving some people something to grip hold of.").also { stage++ }
            13 -> playerl(FacialExpression.FRIENDLY, "Why would you do that?").also { stage++ }
            14 -> npcl(FacialExpression.FRIENDLY, "So people like you can have a tougher route round this course. Me and a mate get together and set up a new challenge that only the truly agile will conquer. The extra stuff starts at that wall; so, if you think you're up to it, I suggest you scramble up there after the log balance.").also { stage++ }
            15 -> playerl(FacialExpression.FRIENDLY, "Sounds interesting. Anything else I should know?").also { stage++ }
            16 -> npcl(FacialExpression.FRIENDLY, "Nothing, really. Just make sure you complete the other obstacles before ya do. If you finish a full lap, you'll get an increased bonus for doing the tougher route. If you manage to do 250 laps of this advanced route without a single mistake, I'll let you have a special item. I'll keep track of your lap tallies, so you can check how you're getting on with me at any time.").also { stage = END_DIALOGUE }
            17 -> playerl(FacialExpression.FRIENDLY, "That's all I need for now. Bye.").also { stage++ }
            18 -> npcl(FacialExpression.FRIENDLY, "Bye for now. Come back if you need any help.").also { stage = END_DIALOGUE }
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.GUNNJORN_607)
    }
}