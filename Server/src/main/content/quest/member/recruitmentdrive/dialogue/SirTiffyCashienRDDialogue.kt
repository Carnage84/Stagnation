package content.quest.member.recruitmentdrive.dialogue

import TeleportCutscene
import config.NPCs
import content.quest.member.recruitmentdrive.RecruitmentDrive.Companion.RecruitmentDriveQuest
import core.api.getQuestStage
import core.api.setQuestStage
import core.game.dialogue.DialogueFile
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.world.map.Location
import core.tools.END_DIALOGUE

/**
 * Represents the Sir Tiffy Cashien dialogue file for Recruitment Drive quest.
 */
class SirTiffyCashienRDDialogue : DialogueFile() {

    override fun handle(componentID: Int, buttonID: Int) {
        val questStage = getQuestStage(player!!, RecruitmentDriveQuest)
        npc = NPC(NPCs.SIR_TIFFY_CASHIEN_2290)

        when {

            (questStage == 10) -> {
                when (stage) {
                    0 -> playerl(
                        FacialExpression.FRIENDLY,
                        "Sir Amik Varze sent me to meet you here for some sort of testing..."
                    ).also { stage = 1 }
                    1 -> npcl(
                        FacialExpression.FRIENDLY,
                        "Ah, ${player!!.username}! Amik told me all about you, dontchaknow! Spliffing job you you did with the old Black Knights there, absolutely first class."
                    ).also { stage = 2 }
                    2 -> playerl(FacialExpression.GUILTY, "...Thanks I think.").also { stage = 3 }
                    3 -> npcl(
                        FacialExpression.FRIENDLY,
                        "Well, not in those exact words, but you get my point, what?"
                    ).also { stage = 4 }
                    4 -> npcl(
                        FacialExpression.FRIENDLY,
                        "A top-notch filly like yourself is just the right sort we've been looking for for our organisation."
                    ).also { stage = 5 }
                    5 -> npcl(FacialExpression.FRIENDLY, "So, are you ready to begin testing?").also { stage = 6 }
                    6 -> options(
                        "Testing..?",
                        "Organisation?",
                        "Yes, let's go!",
                        "No, I've changed my mind."
                    ).also { stage = 7 }
                    7 -> when (buttonID) {
                        1 -> playerl(
                            FacialExpression.FRIENDLY,
                            "Testing? What exactly do you mean by testing?"
                        ).also { stage = 8 }
                        2 -> playerl(
                            FacialExpression.FRIENDLY,
                            "This organisation you keep mentioning.. Perhaps you could tell me a little about it?"
                        ).also { stage = 31 }
                        3 -> playerl(
                            FacialExpression.FRIENDLY,
                            "Yeah, this sounds right up my street. Let's go!"
                        ).also { stage = 46 }
                        4 -> playerl(FacialExpression.FRIENDLY, "No, I've changed my mind.").also {
                            stage = END_DIALOGUE
                        }
                    }
                    8 -> npcl(
                        FacialExpression.FRIENDLY,
                        "Jolly bad show! Varze was supposed to have informed you about all this before sending you here!"
                    ).also { stage = 9 }
                    9 -> npcl(
                        FacialExpression.FRIENDLY,
                        "Well, not your fault I suppose, what? Anywho, our organisation is looking for a certain specific type of person to join."
                    ).also { stage = 10 }
                    10 -> playerl(
                        FacialExpression.FRIENDLY,
                        "So... You want me to go kill some monster or something for you?"
                    ).also { stage = 11 }
                    11 -> npcl(
                        FacialExpression.FRIENDLY,
                        "Not at all, old bean. There's plenty of warriors around should we require dumb muscle."
                    ).also { stage = 12 }
                    12 -> npcl(
                        FacialExpression.FRIENDLY,
                        "That's really not the kind of thing our organisation is after, what?"
                    ).also { stage = 13 }
                    13 -> playerl(
                        FacialExpression.FRIENDLY,
                        "So you want me to go and fetch you some kind of common item, and then take it for delivery somewhere on the other side of the country?"
                    ).also { stage = 14 }
                    14 -> playerl(FacialExpression.FRIENDLY, "Because I really hate doing that!").also { stage = 15 }
                    15 -> npcl(
                        FacialExpression.FRIENDLY,
                        "Haw, haw, haw! What a dull thing to ask of someone, what?"
                    ).also { stage = 16 }
                    16 -> npcl(
                        FacialExpression.FRIENDLY,
                        "I know what you mean, though. I did my fair share of running errands when I was a young adventurer, myself!"
                    ).also { stage = 17 }
                    17 -> playerl(
                        FacialExpression.FRIENDLY,
                        "So what exactly will this test consist of?"
                    ).also { stage = 18 }
                    18 -> npcl(
                        FacialExpression.FRIENDLY,
                        "Can't let just any old riff-raff in, what? The mindless thugs and bully boys are best left in the White Knights or the city guard. We look for the top-shelf brains to join us."
                    ).also { stage = 19 }
                    19 -> playerl(
                        FacialExpression.FRIENDLY,
                        "So you want to test my brains? Will it hurt?"
                    ).also { stage = 20 }
                    20 -> npcl(FacialExpression.FRIENDLY, "Haw, haw, haw! That's a good one!").also { stage = 21 }
                    21 -> npcl(
                        FacialExpression.FRIENDLY,
                        "Not in the slightest.. Well, maybe a bit, but we all have to make sacrifices occasionally, what?"
                    ).also { stage = 22 }
                    22 -> playerl(FacialExpression.FRIENDLY, "What do you want me to do then?").also { stage = 23 }
                    23 -> npcl(
                        FacialExpression.FRIENDLY,
                        "It's a test of wits, what? I'll take you to our secret training grounds, and you will have to pass through a series of five separate intelligence test to prove you're our sort of adventurer."
                    ).also { stage = 24 }
                    24 -> npcl(FacialExpression.FRIENDLY, "Standard puzzle room rules will apply.").also { stage = 25 }
                    25 -> playerl(
                        FacialExpression.FRIENDLY,
                        "Erm... What are standard puzzle room rules exactly?"
                    ).also { stage = 26 }
                    26 -> npcl(FacialExpression.FRIENDLY, "Never done this sort of thing before, what?").also {
                        stage = 27
                    }
                    27 -> npcl(
                        FacialExpression.FRIENDLY,
                        "The simple rules are: No items or equipment to be brought with you. Each room is a self-contained puzzle. You may quit at any time."
                    ).also { stage = 28 }
                    28 -> npcl(
                        FacialExpression.FRIENDLY,
                        "Of course, if you quit a room, then all your progress up to that point will be cleared, and you'll have to start again from scratch."
                    ).also { stage = 29 }
                    29 -> npcl(
                        FacialExpression.FRIENDLY,
                        "Our organisation manages to filter all the top-notch adventurers this way. So, are you ready to go?"
                    ).also { stage = 48 }
                    31 -> npcl(
                        FacialExpression.FRIENDLY,
                        "Oh, that Amik! Jolly bad form. Did he not tell you anything that he was supposed to?"
                    ).also { stage = 32 }
                    32 -> playerl(
                        FacialExpression.FRIENDLY,
                        "No. He didn't really tell me anything except to come here and meet you."
                    ).also { stage = 33 }
                    33 -> npcl(
                        FacialExpression.FRIENDLY,
                        "Well, now, old sport, let me give you the heads up and the low down, what?"
                    ).also { stage = 34 }
                    34 -> npcl(
                        FacialExpression.FRIENDLY,
                        "I represent the Temple Knights. We are the premier order of Knights in Asgarnia, if not the world. Saradomin himself personally founded our order centuries ago, and we answer only to him."
                    ).also { stage = 35 }
                    35 -> npcl(
                        FacialExpression.FRIENDLY,
                        "Only the very best of the best are permitted to join, and the powers we command are formidable indeed."
                    ).also { stage = 36 }
                    36 -> npcl(
                        FacialExpression.FRIENDLY,
                        "You might say that we are the front line of defence for the entire kingdom!"
                    ).also { stage = 37 }
                    37 -> playerl(
                        FacialExpression.FRIENDLY,
                        "So what's the difference between you and the White Knights?"
                    ).also { stage = 38 }
                    38 -> npcl(
                        FacialExpression.FRIENDLY,
                        "Well, in simple terms, we're better! Any fool with a sword can manage to get into the White Knights, which is mostly the reason they are so very, very incompetent, what?"
                    ).also { stage = 39 }
                    39 -> npcl(
                        FacialExpression.FRIENDLY,
                        "The Temple Knights, on the other hand, have to be smarter, stronger and better than all others. We are the elite. No man controls us, for our orders come directly from Saradomin himself!"
                    ).also { stage = 40 }
                    40 -> npcl(
                        FacialExpression.FRIENDLY,
                        "According to Sir Vey Lance, our head of operations, that is. He claims that everything he tells us to do is done with Saradomin's implicit permission."
                    ).also { stage = 41 }
                    41 -> npcl(
                        FacialExpression.FRIENDLY,
                        "It's not every job where you have more authority than the king, though, is it?"
                    ).also { stage = 42 }
                    42 -> playerl(FacialExpression.FRIENDLY, "Wait... You can order the King around?").also {
                        stage = 43
                    }
                    43 -> npcl(
                        FacialExpression.FRIENDLY,
                        "Well, not me personally. I'm only in the recruitment side of things, dontchaknow, but the higher ranking members of the organisation have almost absolute power over the kingdom."
                    ).also { stage = 44 }
                    44 -> npcl(FacialExpression.FRIENDLY, "Plus a few others, so I hear...").also { stage = 45 }
                    45 -> npcl(
                        FacialExpression.FRIENDLY,
                        "Anyway, this is why we keep our organisation shrouded in secrecy, and why we demand such rigorous testing for all potential recruits. Speaking of which, are you ready to begin your testing?"
                    ).also { stage = 48 }
                    46 -> if (!player!!.inventory.isEmpty || !player!!.equipment.isEmpty) {
                        npcl(
                            FacialExpression.FRIENDLY,
                            "To start the test you can't have anything in the inventory and equipment."
                        )
                        stage = END_DIALOGUE
                    } else {
                        npcl(
                            FacialExpression.FRIENDLY,
                            "Jolly good show! Now, the training grounds location is a secret, so..."
                        ).also { stage = 47 }
                    }
                    47 -> {
                        end()
                        player!!.lock()
                        setQuestStage(player!!, "Recruitment Drive", 20)
                        TeleportCutscene(player!!).start()
                    }
                    48 -> options("Yes, let's go!", "No, I've changed my mind.").also { stage = 49 }
                    49 -> when (buttonID) {
                        1 -> playerl(
                            FacialExpression.FRIENDLY,
                            "Yeah, this sounds right up my street. Let's go!"
                        ).also { stage = 46 }
                        2 -> playerl(FacialExpression.FRIENDLY, "No, I've changed my mind.").also {
                            stage = END_DIALOGUE
                        }
                    }
                }
            }

            (questStage == 20) -> {
                when (stage) {
                    0 -> npc(FacialExpression.FRIENDLY, "Here we go!", "Mind your head!").also { stage = 1 }
                    1 -> npc(FacialExpression.FRIENDLY, "Oops, ignore the smell!", "Nearly there!").also { stage = 2 }
                    2 -> npc(FacialExpression.FRIENDLY, "And...", " Here we are!", "Best of luck!").also {
                        stage = END_DIALOGUE
                    }
                }
            }

            (questStage in 30..95) -> {
                when (stage) {
                    0 -> npcl(FacialExpression.FRIENDLY, "So, are you ready to begin testing?").also { stage = 1 }
                    1 -> options("Yes, let's go!", "No, I've changed my mind.").also { stage = 2 }
                    2 -> when (buttonID) {
                        1 -> playerl(
                            FacialExpression.FRIENDLY,
                            "Yeah, this sounds right up my street. Let's go!"
                        ).also { stage = 3 }
                        2 -> playerl(FacialExpression.FRIENDLY, "No, I've changed my mind.").also {
                            stage = END_DIALOGUE
                        }
                    }
                    3 -> if (!player!!.inventory.isEmpty || !player!!.equipment.isEmpty) {
                        npcl(
                            FacialExpression.FRIENDLY,
                            "To start the test you can't have anything in the inventory and equipment."
                        )
                        stage = END_DIALOGUE
                    } else {
                        npcl(
                            FacialExpression.FRIENDLY,
                            "Jolly good show! Now, the training grounds location is a secret, so..."
                        ).also { stage = 4 }
                    }
                    4 -> {
                        end()
                        player!!.lock()
                        TeleportCutscene(player!!).start()
                    }
                }
            }

            (questStage == 99) -> {
                when (stage) {
                    0 -> npcl(
                        FacialExpression.FRIENDLY,
                        "Oh, jolly well done! Your performance will need to be evaluated by Sir Vey personally, but I don't think it's going too far ahead of myself to welcome you to the team!"
                    ).also { player!!.faceLocation(Location(2997, 3373, 0)) }.also { stage = 1 }
                    1 -> {
                        end()
                        player!!.questRepository.getQuest("Recruitment Drive").finish(player)
                        player!!.questRepository.syncronizeTab(player)
                    }
                }
            }
        }
    }
}