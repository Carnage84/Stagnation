package content.quest.member.horrorfromthedeep.dialogue

import config.Items
import config.NPCs
import content.quest.member.horrorfromthedeep.HFTDUtils
import core.api.addItemOrDrop
import core.api.getQuestStage
import core.api.removeItem
import core.game.dialogue.DialogueFile
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.tools.END_DIALOGUE

/**
 * Represents the Jossik Horror from the deep dialogue plugin.
 */
class JossikHFTDLighthouseDialogue : DialogueFile() {
    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.JOSSIK_1335)
        when (getQuestStage(player!!, "Horror from the Deep")) {
            100 -> when (stage) {
                0 -> playerl(FacialExpression.FRIENDLY, "I see you managed to escape from those monsters intact!").also { stage++ }
                1 -> npcl(FacialExpression.FRIENDLY, "It seems I was not as injured as I thought I was after all! I must thank you for all of your help!").also { stage++ }
                2 -> npcl(FacialExpression.FRIENDLY, "Now, about that casket you found on that monster's corpse...").also { stage++ }
                3 -> playerl(FacialExpression.FRIENDLY, "I have it here. You said you might be able to tell me something about it...?").also { stage++ }
                4 -> npcl(FacialExpression.FRIENDLY, "I can indeed! Here, let me have a closer look...").also { stage++ }
                5 -> npcl(FacialExpression.FRIENDLY, "Yes! There is something written on it!").also { stage++ }
                6 -> npcl(FacialExpression.FRIENDLY, "It is very faint however... Can you read it?").also { stage++ }
                7 -> options("Saradomin", "Zamorak", "Guthix").also { stage++ }
                8 -> when (buttonID) {
                    1 -> playerl(FacialExpression.FRIENDLY, "Saradomin").also { stage = 9 }
                    2 -> playerl(FacialExpression.FRIENDLY, "Zamorak").also { stage = 16 }
                    3 -> playerl(FacialExpression.FRIENDLY, "Guthix").also { stage = 23 }
                }
                9 -> playerl(FacialExpression.FRIENDLY, "I think it says... Saradomin...").also { stage++ }
                10 -> npcl(FacialExpression.FRIENDLY, "Are you sure? I mean, are you REALLY sure? Maybe you'd better look again...").also { stage++ }
                11 -> options("Saradomin", "Zamorak", "Guthix").also { stage++ }
                12 -> when (buttonID) {
                    1 -> playerl(FacialExpression.FRIENDLY, "Saradomin").also { stage = 13 }
                    2 -> playerl(FacialExpression.FRIENDLY, "Zamorak").also { stage = 16 }
                    3 -> playerl(FacialExpression.FRIENDLY, "Guthix").also { stage = 23 }
                }
                13 -> playerl(FacialExpression.FRIENDLY, "Nope, it definitely says Saradomin.").also { stage++ }
                14 -> npcl(FacialExpression.FRIENDLY, "I think you're right! Hand it over, and let's see what's inside!").also { stage++ }
                15 -> npcl(FacialExpression.FRIENDLY, "Wow! It's an Holy Book of Saradomin! I thought these things had all vanished! Well, it's all yours, I hope you appreciate it.").also { stage = 32 }
                32 -> {
                    end()
                    if (removeItem(player!!, HFTDUtils.QUEST_CASKET)) {
                        addItemOrDrop(player!!, Items.DAMAGED_BOOK_3839)
                    }
                }
                16 -> playerl(FacialExpression.FRIENDLY, "I think it says... Zamorak").also { stage++ }
                17 -> npcl(FacialExpression.FRIENDLY, "Are you sure? I mean, are you REALLY sure? Maybe you'd better look again...").also { stage++ }
                18 -> options("Saradomin", "Zamorak", "Guthix").also { stage++ }
                19 -> when (buttonID) {
                    1 -> playerl(FacialExpression.FRIENDLY, "Saradomin").also { stage = 9 }
                    2 -> playerl(FacialExpression.FRIENDLY, "Zamorak").also { stage = 20 }
                    3 -> playerl(FacialExpression.FRIENDLY, "Guthix").also { stage = 23 }
                }
                20 -> playerl(FacialExpression.FRIENDLY, "Nope, it definitely says Zamorak.").also { stage++ }
                21 -> npcl(FacialExpression.FRIENDLY, "I think you're right! Hand it over, and let's see what's inside!").also { stage++ }
                22 -> npcl(FacialExpression.FRIENDLY, "Wow! It's an Unholy Book of Zamorak! I thought these things had all vanished! Well, it's all yours, I hope you appreciate it.").also { stage = 30 }
                30 -> {
                    end()
                    if (removeItem(player!!, HFTDUtils.QUEST_CASKET)) {
                        addItemOrDrop(player!!, Items.DAMAGED_BOOK_3841)
                    }
                }
                23 -> playerl(FacialExpression.FRIENDLY, "I think it says... Guthix").also { stage++ }
                24 -> npcl(FacialExpression.FRIENDLY, "Are you sure? I mean, are you REALLY sure? Maybe you'd better look again...").also { stage++ }
                25 -> options("Saradomin", "Zamorak", "Guthix").also { stage++ }
                26 -> when (buttonID) {
                    1 -> playerl(FacialExpression.FRIENDLY, "Saradomin").also { stage = 9 }
                    2 -> playerl(FacialExpression.FRIENDLY, "Zamorak").also { stage = 16 }
                    3 -> playerl(FacialExpression.FRIENDLY, "Guthix").also { stage = 27 }
                }
                27 -> playerl(FacialExpression.FRIENDLY, "Nope, it definitely says Guthix.").also { stage++ }
                28 -> npcl(FacialExpression.FRIENDLY, "I think you're right! Hand it over, and let's see what's inside!").also { stage++ }
                29 -> npcl(FacialExpression.FRIENDLY, "Wow! It's an Balance Book of Guthix! I thought these things had all vanished! Well, it's all yours, I hope you appreciate it.").also { stage = 31 }
                31 -> {
                    end()
                    if(removeItem(player!!, HFTDUtils.QUEST_CASKET)) {
                        addItemOrDrop(player!!, Items.DAMAGED_BOOK_3843)
                    }
                }

            }
        }
    }

}