package content.quest.member.biohazard.dialogue

import config.Items
import core.api.*
import core.game.dialogue.DialogueFile
import core.game.dialogue.FacialExpression
import core.game.dialogue.Topic
import core.game.node.entity.player.Player
import core.game.node.item.Item
import core.tools.END_DIALOGUE

/**
 * Represents the Elena dialogue file for Biohazard quest.
 */

class ElenaBiohazardDialogue() : DialogueFile() {
    override fun handle(componentID: Int, buttonID: Int) {
        when(getQuestStage(player!!, "Biohazard")) {
            0 -> handleBiohazardStartDialogue(player, buttonID)
            in 20 .. 29 -> handlePostJericoSuggestionDialogue(player, buttonID)
            in 30 .. 39 -> handleTwoFriendsReportDialogue(player, buttonID)
            in 40 .. 49 -> handlePigeonsReleasedReportDialogue(player, buttonID)
            in 50 .. 64 -> handleCrossingReportDialogue(player, buttonID)
            65 -> handleReturnDistillatorDialogue(player, buttonID)
            in 70 .. 89 -> handleSampleToChemistReportDialogue(player, buttonID)
            in 90 .. 98 -> handlePlagueTruthDialogue(player, buttonID)
            99 -> handlePostPlagueTruthReportDialogue(player, buttonID)
        }
    }

    private fun handleBiohazardStartDialogue(player: Player?, buttonId: Int) {
        when(stage) {
            0 -> playerl(FacialExpression.FRIENDLY, "Good to see you, Elena.").also { stage++ }
            1 -> npcl(FacialExpression.FRIENDLY, "You too, thanks for freeing me. It's just a shame the mourners confiscated my equipment.").also { stage++ }
            2 -> playerl(FacialExpression.ASKING, "What did they take?").also { stage++ }
            3 -> npcl(FacialExpression.ANNOYED, "My distillator, I can't test any plague samples without it. They're holding it in the mourner quarters in West Ardougne. I must somehow retrieve that distillator if I am to find a cure for this awful affliction.").also { stage++ }
            4 -> showTopics(
                    Topic(FacialExpression.HAPPY, "I'll try to retrieve it for you.", 20),
                    Topic(FacialExpression.FRIENDLY, "Well, good luck!", 10)
            )
            10 -> npcl(FacialExpression.FRIENDLY, "Thanks traveller.").also { stage = END_DIALOGUE }
            20 -> {
                stage = 100
                startQuest(player!!, "Biohazard")
            }
            100 -> npcl(FacialExpression.HAPPY, "I was hoping you would say that. Unfortunately they discovered the tunnel and filled it in. We need another way over the wall.").also { stage++ }
            101 -> playerl(FacialExpression.HALF_ASKING, "Any ideas?").also { stage++ }
            102 -> playerl(FacialExpression.HALF_THINKING, "My father's friend Jerico is in communication with West Ardougne. He might be able to help us, he lives next to the chapel.").also { stage++ }
            103 -> {
                stage = END_DIALOGUE
                setQuestStage(player!!, "Biohazard", 20)
            }
        }
    }

    private fun handlePostJericoSuggestionDialogue(player: Player?, buttonId: Int) {
        when(stage) {
            0 -> playerl(FacialExpression.FRIENDLY, "Hello Elena.").also { stage++ }
            1 -> npcl(FacialExpression.FRIENDLY, "Hello brave adventurer. Any luck finding my distillator?").also { stage++ }
            2 -> playerl(FacialExpression.HALF_GUILTY, "No, I'm afraid not.").also { stage++ }
            3 -> npcl(FacialExpression.FRIENDLY, "Speak to Jerico, he will help you to cross the wall. He lives next to the chapel.").also { stage = END_DIALOGUE }
        }
    }

    private fun handleTwoFriendsReportDialogue(player: Player?, buttonId: Int) {
        when(stage) {
            0 -> playerl(FacialExpression.FRIENDLY, "Hello Elena, I've spoken to Jerico.").also { stage++ }
            1 -> npcl(FacialExpression.HALF_ASKING, "Was he able to help?").also { stage++ }
            2 -> playerl(FacialExpression.FRIENDLY, "He has two friends who will help me cross the wall, but first I need to distract the watch tower.").also { stage++ }
            3 -> npcl(FacialExpression.HALF_THINKING, "Hmm, could be tricky.").also { stage = END_DIALOGUE }
        }
    }

    private fun handlePigeonsReleasedReportDialogue(player: Player?, buttonId: Int) {
        when(stage) {
            0 -> playerl(FacialExpression.FRIENDLY, "Elena, I've distracted the guards at the watch tower.").also { stage++ }
            1 -> npc(FacialExpression.FRIENDLY, "Yes, I saw.", "Quickly meet with Jerico's friends and cross the wall", "before the pigeons fly off.").also { stage = END_DIALOGUE }
        }
    }

    private fun handleCrossingReportDialogue(player: Player?, buttonId: Int) {
        when(stage) {
            0 -> playerl(FacialExpression.FRIENDLY, "Hello again.").also { stage++ }
            1 -> npcl(FacialExpression.HALF_ASKING, "You're back, did you find the distillator?").also { stage++ }
            2 -> playerl(FacialExpression.HALF_GUILTY, "I'm afraid not.").also { stage++ }
            3 -> npcl(FacialExpression.HALF_WORRIED, "I can't test the samples without the distillator. Please don't give up until you find it.").also { stage = END_DIALOGUE }
        }
    }

    private fun handleReturnDistillatorDialogue(player: Player?, buttonId: Int) {
        player ?: return
        when(stage) {
            0 -> npcl(FacialExpression.HALF_ASKING, "So, have you managed to retrieve my distillator?").also { stage++ }
            1 -> playerl(FacialExpression.FRIENDLY, "Yes, here it is!").also { stage++ }
            2 -> npcl(FacialExpression.HAPPY, "You have? That's great! Now can you pass me those reaction agents please?").also { stage++ }
            3 -> sendDialogue(player, "You hand Elena the distillator and an assortment of vials.").also { if(removeItem(player, Items.DISTILLATOR_420)) { stage++ } }
            4 -> playerl(FacialExpression.HALF_THINKING, "Those look pretty fancy.").also { stage++ }
            5 -> npcl(FacialExpression.FRIENDLY, "Well, yes and no. The liquid honey isn't worth so much, but the others are. Especially this colourless ethenea. And be careful with the sulphuric broline, it's highly poisonous.").also { stage++ }
            6 -> playerl(FacialExpression.DISGUSTED, "You're not kidding, I can smell it from here!").also { stage++ }
            7 -> sendDialogue(player, "Elena puts the agents through the distillator.").also { stage++ }
            8 -> npcl(FacialExpression.ANNOYED, "I don't understand... the touch paper hasn't changed colour at all. You'll need to go and see my old mentor Guidor. He lives in Varrock. Take these vials and this sample to him.").also { stage++ }
            9 -> sendDialogue(player, "Elena gives you three vials and a sample in a tin container.").also {
                if(hasSpaceFor(player, Item(Items.ETHENEA_415)) && hasSpaceFor(player, Item(Items.ETHENEA_415)) && hasSpaceFor(player, Item(
                        Items.ETHENEA_415))) {
                    if((addItem(player, Items.ETHENEA_415) && addItem(player, Items.LIQUID_HONEY_416) && addItem(player, Items.SULPHURIC_BROLINE_417) && addItem(player, Items.PLAGUE_SAMPLE_418))) {
                        stage++
                    } else {
                        stage = 9999
                    }
                } else {
                    stage = 9999
                }
            }
            10 -> npcl(FacialExpression.HALF_THINKING, "But first you'll need some more touch-paper. Go and see the chemist in Rimmington. Just don't get into any fights, and be careful who you speak to. Those vials are fragile, and plague carriers don't tend to be too popular.").also { setQuestStage(player, "Biohazard",70) }
            9999 -> sendDialogue(player, "You don't have enough space for that.").also { stage = END_DIALOGUE }
        }
    }

    private fun handleSampleToChemistReportDialogue(player: Player?, buttonId: Int) {
        player ?: return
        when (stage) {
            0 -> npcl(FacialExpression.HALF_ASKING, "What are you doing back here?").also { stage++ }
            1 -> showTopics(
                    Topic(FacialExpression.HALF_GUILTY, "I just find it hard to say goodbye sometimes.", 100),
                    Topic(FacialExpression.GUILTY, "I'm afraid i've lost some of the stuff that you gave me...", 200),
                    Topic(FacialExpression.HALF_ASKING, "I've forgotten what I need to do.", 300)
            )

            100 -> npc(FacialExpression.HALF_GUILTY, "Yes... I have feelings for you too...", "Now get to work!").also { stage = END_DIALOGUE }
            200 -> {
                if(!inInventory(player, Items.LIQUID_HONEY_416) || !inInventory(player, Items.ETHENEA_415) || !inInventory(player, Items.PLAGUE_SAMPLE_418) || !inInventory(player, Items.SULPHURIC_BROLINE_417)) {
                    stage++
                } else {
                    npcl(FacialExpression.HALF_THINKING, "Are you sure? Looks like you have everything to me.").also { stage = END_DIALOGUE }
                }
            }
            201 -> if(freeSlots(player) < 4) {
                sendDialogue(player, "Elena tries to give you some items but you don't have enough room for them.").also { stage = END_DIALOGUE }
            } else {
                sendDialogue(player, "Elena replaces your items.")
                if(!inInventory(player, Items.LIQUID_HONEY_416))
                    addItem(player, Items.LIQUID_HONEY_416)
                if(!inInventory(player, Items.ETHENEA_415))
                    addItem(player, Items.ETHENEA_415)
                if(!inInventory(player, Items.SULPHURIC_BROLINE_417))
                    addItem(player, Items.SULPHURIC_BROLINE_417)
                if(!inInventory(player, Items.PLAGUE_SAMPLE_418))
                    addItem(player, Items.PLAGUE_SAMPLE_418)
                stage++
            }
            202 -> npc(FacialExpression.HALF_THINKING, "Ok, so that's the colourless ethenea...", "Some highly toxic sulphuric broline...", "And some bog-standard liquid honey...").also { stage++ }
            203 -> playerl(FacialExpression.FRIENDLY, "Great. I'll be on my way.")
            300 -> npcl(FacialExpression.HALF_THINKING, "Go to Rimmington and get some touch paper from the chemist. Use his errand boys to smuggle the vials into Varrock, then go to Varrock and take the sample to Guidor, my old mentor.").also { stage++ }
            301 -> playerl(FacialExpression.FRIENDLY, "Ok, I'll get to it.")
        }
    }

    private fun handlePlagueTruthDialogue(player: Player?, buttonId: Int) {
        player ?: return
        when(stage) {
            0 -> npcl(FacialExpression.FRIENDLY, "You're back! So what did Guidor say?").also { stage++ }
            1 -> playerl(FacialExpression.HALF_GUILTY, "Nothing.").also { stage++ }
            2 -> npcl(FacialExpression.HALF_THINKING, "What?").also { stage++ }
            3 -> playerl(FacialExpression.HALF_WORRIED,"He says there is no plague.").also { stage++ }
            4 -> npcl(FacialExpression.ASKING, "So what, this thing has all been a big hoax?").also { stage++ }
            5 -> playerl(FacialExpression.THINKING, "Or maybe we're about to uncover something huge.").also { stage++ }
            6 -> npcl(FacialExpression.HALF_WORRIED, "Then I think this thing may be bigger than both of us.").also { stage++ }
            7 -> playerl(FacialExpression.HALF_ASKING, "What do you mean?").also { stage++ }
            8 -> npcl(FacialExpression.DISGUSTED_HEAD_SHAKE, "I mean that you need to go right to the top. You need to see the king of East Ardougne.").also { setQuestStage(player, "Biohazard",99); }
        }
    }

    private fun handlePostPlagueTruthReportDialogue(player: Player?, buttonId: Int) {
        player ?: return
        when(stage) {
            0 -> playerl(FacialExpression.FRIENDLY, "Hello Elena.").also { stage++ }
            1 -> npcl(FacialExpression.ANNOYED, "You must go to King Lathas immediately!").also { stage = END_DIALOGUE }
        }
    }
}