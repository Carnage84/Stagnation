package content.quest.member.horrorfromthedeep.dialogue

import config.Items
import core.api.getQuestStage
import core.api.inInventory
import core.api.setQuestStage
import core.api.setVarbit
import core.game.dialogue.DialogueFile
import core.game.dialogue.FacialExpression
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the Larrisa dialogue for Horror from the Deep quest.
 */
@Initializable
class LarrisaHFTDDialogue : DialogueFile() {

    override fun handle(componentID: Int, buttonID: Int) {

        // Starting the quest.
        when (getQuestStage(player!!, "Horror from the Deep")) {
            0 -> when (stage) {
                0 -> playerl(FacialExpression.FRIENDLY, "Hello there.").also { stage++ }
                1 -> npcl(FacialExpression.FRIENDLY, "Oh, thank Armadyl! I am in such a worry... please help me!").also { stage++ }
                2 -> options("With what?", "Sorry, just passing through.").also { stage++ }
                3 -> when (buttonID) {
                    1 -> playerl(FacialExpression.FRIENDLY, "With what?").also { stage = 4 }
                    2 -> playerl(FacialExpression.FRIENDLY, "Sorry, just passing through.").also { stage = END_DIALOGUE }
                }
                4 -> npcl(FacialExpression.FRIENDLY, "Oh... it is terrible... horrible... MY boyfriend lives here in this lighthouse, but I haven't seen him the last few days! I think something terrible has happened!").also { stage++ }
                5 -> npcl(FacialExpression.FRIENDLY, "Look, you can see for yourself that the light has gone out, and the front door is locked up tight! He would NEVER do that!").also { stage++ }
                6 -> npcl(FacialExpression.FRIENDLY, "With the light off this coastline is terribly dangerous to ships! And to lock the front door so that nobody can turn the light back on?").also { stage++ }
                7 -> playerl(FacialExpression.FRIENDLY, "Maybe he just went on holiday or something? Must be pretty boring living in a lighthouse.").also { stage++ }
                8 -> npcl(FacialExpression.FRIENDLY, "That is terribly irresponsible! He is far too thoughtful for that! He would never leave it unattended! He would also never leave without telling me!").also { stage++ }
                9 -> npcl(FacialExpression.FRIENDLY, "Please, I know something terrible has happened to him... I can sense it! Please... please help me adventurer!").also { stage++ }
                10 -> options("But how can I help?", "Sorry, just passing through.").also { stage++ }
                11 -> when (buttonID) {
                    1 -> playerl(FacialExpression.FRIENDLY, "But how can I help?").also { stage++ }
                    2 -> playerl(FacialExpression.FRIENDLY, "Sorry, just passing through.").also { stage = END_DIALOGUE }
                }
                12 -> npcl(FacialExpression.FRIENDLY, "Well, we have to do something to get the lighthouse working again! Also, as you may have noticed, the storm that knocked the bridge out").also { stage++ }
                13 -> npcl(FacialExpression.FRIENDLY, "has trapped me on this causeway! You seem to have got here okay somehow, so if you could go and visit my cousin and get the spare key I left him,").also { stage++ }
                14 -> npcl(FacialExpression.FRIENDLY, "as well as fix the bridge enough so that I can go and speak to my family in Rellekka and tell them whats happened, I will be eternally grateful!").also { stage++ }
                15 -> options("Okay, I'll help!", "Sorry, just passing through.").also { stage++ }
                16 -> when (buttonID) {
                    1 -> playerl(FacialExpression.FRIENDLY, "Okay, I'll help!").also { stage++ }
                    2 -> playerl(FacialExpression.FRIENDLY, "Sorry, just passing through.").also { stage = END_DIALOGUE }
                }
                17 -> npc(FacialExpression.FRIENDLY, "OH! THANK YOU SO MUCH! I know my darling", "would never have left with the lighthouse lights off", "and without even telling me where he's gone!").also { stage++ }
                18 -> options("Where is your cousin?", "How can I fix the bridge?", "I'll see what I can do.").also { stage++ }
                19 -> when (buttonID) {
                    1 -> playerl(FacialExpression.FRIENDLY, "Where is your cousin?").also { stage++ }
                    2 -> playerl(FacialExpression.FRIENDLY, "How can I fix the bridge?").also { stage = 22 }
                    3 -> playerl(FacialExpression.FRIENDLY, "I'll see what I can do.").also { stage = 24 }
                }
                20 -> npcl(FacialExpression.FRIENDLY, "My cousin was always interested in agility. He left our home in Rellekka many moons ago, so that he could pursue this interest.").also { stage++ }
                21 -> npcl(FacialExpression.FRIENDLY, "I don't exactly know where he has gone, but I am sure he went somewhere to practice his agility. If you see him, his name is Gunnjorn. Mention my name, he will recognise it.").also { stage = 18 }
                22 -> npcl(FacialExpression.FRIENDLY, "Well, I am not just some helpless girl! I have pretty good agility, so you will only need to use two planks to make a ledge that I can balance along.").also { stage++ }
                23 -> npcl(FacialExpression.FRIENDLY, "Just use a plank on each side of the bridge. You will need a hammer, and thirty steel nails for each plank you use as well. I believe there are some planks near here...").also { stage = 18 }
                24 -> npcl(FacialExpression.FRIENDLY, "Thank you so much!").also { stage++ }
                25 -> {
                    end()
                    setQuestStage(player!!, "Horror from the Deep", 1)
                    setVarbit(player!!, 34, 1, true)
                }
            }

            1 -> when (stage) {
                0 -> playerl(FacialExpression.FRIENDLY, "Hello again.").also { stage++ }
                1 -> npcl(FacialExpression.FRIENDLY, "Oh, please find my darling! I just know something horrible has happened!").also { stage = END_DIALOGUE }
            }

            in 4..10 -> when (stage) {
                0 -> playerl(FacialExpression.FRIENDLY, "I've got your key for you!").also { stage++ }
                1 -> npcl(FacialExpression.FRIENDLY, "Thank you adventurer, but I need you to fix the bridge for me. The key to the lighthouse is of little comfort while I am trapped here on this causeway!").also { stage = END_DIALOGUE }
            }

            in 11..19 -> if (inInventory(player!!, Items.LIGHTHOUSE_KEY_3848)) when (stage) {
                0 -> playerl(FacialExpression.FRIENDLY, "I've got your key for you!").also { stage++ }
                1 -> npcl(FacialExpression.FRIENDLY, "Oh, thank you so much!").also { stage++ }
                2 -> npcl(FacialExpression.FRIENDLY, "Quickly, we must go inside and find out what has happened to my beloved Jossik!").also { stage = END_DIALOGUE }
            } else {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "I've fixed the bridge for you!").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "Oh, thank you so much! Please find the key to the lighthouse for me though! I cannot bear to think that something bad may have happened to my darling Jossik...").also { stage = END_DIALOGUE }
                }
            }

            in 20..29 -> when (stage) {
                0 -> npcl(FacialExpression.FRIENDLY, "Quickly, we must go inside and find out what has happened to my beloved Jossik!").also { stage = END_DIALOGUE }
            }

            // Speaking to Larissa, inside the lighthouse, for the first time.
            30 -> when (stage) {
                0 -> npcl(FacialExpression.FRIENDLY, "This is terrible... What could have happened here? Please, you must fix the light, we cannot let my darling Jossik take the blame for any shipwrecks!").also { stage++ }
                1 -> playerl(FacialExpression.FRIENDLY, "Okay, I will see what I can do.").also { stage++ }
                2 -> {
                    end()
                    setQuestStage(player!!, "Horror from the Deep", 31)
                }
            }

            // Speaking to Larissa, inside the lighthouse, after speaking to her, for the first time.
            in 31..39 -> when (stage) {
                0 -> playerl(FacialExpression.FRIENDLY, "What am I supposed to be doing again?").also { stage++ }
                1 -> npcl(FacialExpression.FRIENDLY, "You MUST find a way to fix the lighthouse! The ships are in terrible danger as long as the light is not working properly!").also { stage++ }
                2 -> playerl(FacialExpression.FRIENDLY, "Okay, I will see what I can do.").also { stage++ }
                3 -> npcl(FacialExpression.FRIENDLY, "I'm sorry adventurer, I do not know anything about lighthouses.").also { stage++ }
                4 -> npcl(FacialExpression.FRIENDLY, "I'm sure there must be some kind of user's guide around here somewhere though.").also { stage++ }
                5 -> npcl(FacialExpression.FRIENDLY, "My darling Jossik knew nothing about Lighthouses when he first came here, so the council must have left him some kind of manual or something.").also { stage = END_DIALOGUE }
            }

            // Speaking to Larissa, after fixing the lighthouse, before finding Jossik.
            in 40..59 -> when (stage) {
                0 -> playerl(FacialExpression.FRIENDLY, "I have managed to fix the light!").also { stage++ }
                1 -> npcl(FacialExpression.FRIENDLY, "Excellent work, adventurer! Now you can devote all of your energies to finding out what has happened to my darling Jossik!").also { stage = END_DIALOGUE }
            }

            // Speaking to Larissa, after finding Jossik, before killing the dag mommy.
            in 60..69 -> when (stage) {
                0 -> playerl(FacialExpression.FRIENDLY, "I found Jossik! He's in a cavern underground...").also { stage++ }
                1 -> npcl(FacialExpression.FRIENDLY, "That is wonderful news! But why does he not come up out of there?").also { stage++ }
                2 -> playerl(FacialExpression.FRIENDLY, "Um... well, there's a big monster down there with him stopping him leaving.").also { stage++ }
                3 -> npcl(FacialExpression.FRIENDLY, "What?!?! You MUST save him! I beseech you, please! Do something!").also { stage++ }
                4 -> playerl(FacialExpression.FRIENDLY, "Okay, okay, sheesh, keep your hair on...").also { stage = END_DIALOGUE }
            }

            // Speaking to Larissa, after the quest is complete.
            100 -> when (stage) {
                0 -> npcl(FacialExpression.FRIENDLY, "Oh! Thank you so much for all of your help in rescuing my darling Jossik! I do not know what would have happened to him had you not chanced along this path!").also { stage++ }
                1 -> playerl(FacialExpression.FRIENDLY, "Don't worry about it, I was glad to help out.").also { stage = END_DIALOGUE }
            }
        }
    }
}