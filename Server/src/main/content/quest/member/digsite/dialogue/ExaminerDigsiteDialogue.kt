package content.quest.member.digsite.dialogue

import config.Items
import config.NPCs
import core.api.*
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.player.Player
import core.game.world.GameWorld
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the Examiner dialogue plugin for The Dig Site quest.
 */
@Initializable
class ExaminerDigsiteDialogue(player: Player? = null) : DialoguePlugin(player) {
    override fun handle(componentID: Int, buttonID: Int): Boolean {
        val questName = "The Dig Site"
        val questStage = getQuestStage(player!!, questName)

        when {
            // Start
            (questStage == 0) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Hello.").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "Ah hello there! I am the resident lecturer on antiques and artefacts. I also set the Earth Science exams.").also { stage++ }
                    2 -> playerl(FacialExpression.FRIENDLY, "Earth Sciences?").also { stage++ }
                    3 -> npcl(FacialExpression.FRIENDLY, "That is right dear, the world of " + GameWorld.settings!!.name + " holds many wonders beneath its surface. Students come to me to take exams so that they may join in on the archeological dig going on just north of here.").also { stage++ }
                    4 -> playerl(FacialExpression.FRIENDLY, "So if they don't pass the exams they can't dig at all?").also { stage++ }
                    5 -> npcl(FacialExpression.FRIENDLY, "That's right! We have to make sure that students know enough to be able to dig safely and not damage the artefacts.").also { stage++ }
                    6 -> options("Can I take an exam?", "Interesting...").also { stage++ }
                    7 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "Can I take an exam?").also { stage = 9 }
                        2 -> playerl(FacialExpression.FRIENDLY, "Interesting...").also { stage = 8 }
                    }
                    8 -> npcl(FacialExpression.FRIENDLY, "You could gain much with an understanding of the world below.").also {
                        stage = END_DIALOGUE
                    }
                    9 -> npcl(FacialExpression.FRIENDLY, "You can if you get this letter stamped by the Curator of Varrock's museum.").also { stage++ }
                    10 -> playerl(FacialExpression.FRIENDLY, "Why's that then?").also { stage++ }
                    11 -> npcl(FacialExpression.FRIENDLY, "Because he is a very knowledgeable man and employs our archaeological expert. I'm sure he knows a lot about your exploits and can judge whether you'd make a good archaeologist or not.").also { stage++ }
                    12 -> npcl(FacialExpression.FRIENDLY, "Besides, the museum contributes funds to the dig.").also { stage++ }
                    13 -> playerl(FacialExpression.FRIENDLY, "But why are you writing the letter? Shouldn't he?").also { stage++ }
                    14 -> npcl(FacialExpression.FRIENDLY, "He's also a very busy man, so I write the letters and he just stamps them if he approves.").also { stage++ }
                    15 -> playerl(FacialExpression.FRIENDLY, "Oh, I see. I'll ask him if he'll approve me, and bring my stamped letter back here. Thanks.").also {
                        stage = 16
                    }
                    16 -> {
                        end()
                        addItem(player!!, Items.UNSTAMPED_LETTER_682)
                        setQuestStage(player!!, "The Dig Site", 1)
                    }
                }
            }


            // Signed letter + attempting first exam
            (questStage == 2 && inInventory(player!!, Items.SEALED_LETTER_683)) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Hello.").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "Hello again.").also { stage++ }
                    2 -> playerl(FacialExpression.FRIENDLY, "Here is the stamped letter you asked for.").also { stage++ }
                    3 -> npcl(FacialExpression.FRIENDLY, "Good good, we will begin the exam...").also { stage++ }
                    4 -> npcl(FacialExpression.FRIENDLY, "Okay, we will start with the first exam: Earth Sciences level 1 – Beginner.").also { stage++ }
                    5 -> npcl(FacialExpression.FRIENDLY, "Question 1 – Earth Sciences overview. Can you tell me what Earth Sciences is? ").also { stage++ }
                    6 -> options(
                        "The study of gardening, planting and fruiting vegetation.",
                        "The study of planets and the history of worlds.",
                        "The combination of archeology and vegetarianism."
                    ).also { stage++ }
                    7 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "The study of gardening, planting and fruiting vegetation.").also { stage++ }
                        2 -> playerl(FacialExpression.FRIENDLY, "The study of planets and the history of worlds.").also { stage++ }
                        3 -> playerl(FacialExpression.FRIENDLY, "The combination of archeology and vegetarianism.").also { stage++ }
                    }
                    8 -> npcl(FacialExpression.FRIENDLY, "Okay, next question...").also { stage++ }
                    9 -> npcl(FacialExpression.FRIENDLY, "Earth Sciences level 1, question 2 – Eligibility. Can you tell me which people are allowed to use the digsite?").also { stage++ }
                    10 -> options(
                        "Magic users, miners and their escorts.",
                        "Professors, students and workmen only.",
                        "Local residents, contractors and small pink fish."
                    ).also { stage++ }
                    11 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "Magic users, miners and their escorts.").also { stage++ }
                        2 -> playerl(FacialExpression.FRIENDLY, "Professors, students and workmen only.").also { stage++ }
                        3 -> playerl(FacialExpression.FRIENDLY, "Local residents, contractors and small pink fish.").also { stage++ }
                    }
                    12 -> ("Okay, next question...").also { stage++ }
                    //    13 -> Earth Sciences level 1, question 3 – Health and safety. Can you tell me the proper safety points when working on a digsite?").also { stage++ }
                    14 -> options(
                        "Heat-resistant clothing to be worn at all times.",
                        "Rubber chickens to be worn on the head at all times.",
                        "Protective clothing to be worn; tools kept away from site."
                    ).also { stage++ }

                    15 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "Heat-resistant clothing to be worn at all times.").also { stage++ }
                        2 -> playerl(FacialExpression.FRIENDLY, "Rubber chickens to be worn on the head at all times.").also { stage++ }
                        3 -> playerl(FacialExpression.FRIENDLY, "Protective clothing to be worn; tools kept away from site.").also { stage++ }
                    }
                    16 -> npcl(FacialExpression.FRIENDLY, "Okay, that covers the level 1 Earth Sciences exam.").also { stage++ }
                    17 -> npcl(FacialExpression.FRIENDLY, "Let's see how you did...").also { stage++ }
                    18 -> npcl(FacialExpression.FRIENDLY, "Oh dear me! This is appalling, none correct at all! I suggest you go and study properly.").also { stage++ }
                    19 -> playerl(FacialExpression.FRIENDLY, "Oh dear...").also { stage++ }
                    20 -> npcl(FacialExpression.FRIENDLY, "Why don't you use the resources here? There are books and the researchers... and you could even ask other students who are also studying for these exams.").also {
                        stage = END_DIALOGUE
                    }
                }
            }

            // After getting answers from the three students for the first exam
            (questStage == 3 && player!!.getAttribute("digsite:students", 0) == 3) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Hello.").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "Hello again. Are you ready for another shot at the exam?").also { stage++ }
                    2 -> options("Yes, I certainly am.", "No, not at the moment.")
                    3 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "Yes, I certainly am.").also { stage = 4 }
                        2 -> playerl(FacialExpression.FRIENDLY, "No, not at the moment.").also { stage = END_DIALOGUE }
                    }
                    4 -> npcl(FacialExpression.FRIENDLY, "Okay, we will start with the first exam: Earth Sciences level 1 – Beginner.").also { stage++ }
                    5 -> npcl(FacialExpression.FRIENDLY, "Question 1 – Earth Sciences overview. Can you tell me what Earth Sciences is? ").also { stage++ }
                    6 -> options(
                        "The study of the earth, its contents and history.",
                        "The study of planets and the history of worlds.",
                        "The combination of archeology and vegetarianism."
                    ).also { stage++ }
                    7 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "The study of the earth, its contents and history.").also { stage++ }
                        2 -> playerl(FacialExpression.FRIENDLY, "The study of planets and the history of worlds.").also { stage = 26 }
                        3 -> playerl(FacialExpression.FRIENDLY, "The combination of archeology and vegetarianism.").also { stage = 26 }
                    }
                    8 -> npcl(FacialExpression.FRIENDLY, "Okay, next question...").also { stage++ }
                    9 -> npcl(FacialExpression.FRIENDLY, "Earth Sciences level 1, question 2 – Eligibility. Can you tell me which people are allowed to use the digsite?").also { stage++ }
                    10 -> options(
                        "Professors, students and workmen only.",
                        "Local residents, contractors and small pink fish.",
                        "All that have passed the appropriate Earth Sciences exam."
                    ).also { stage++ }
                    11 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "Professors, students and workmen only.").also { stage = 39 }
                        2 -> playerl(FacialExpression.FRIENDLY, "Local residents, contractors and small pink fish.").also { stage = 39 }
                        3 -> playerl(FacialExpression.FRIENDLY, "All that have passed the appropriate Earth Sciences exam.").also { stage++ }
                    }
                    12 -> ("Okay, next question...").also { stage++ }
                    //    13 -> Earth Sciences level 1, question 3 – Health and safety. Can you tell me the proper safety points when working on a digsite?").also { stage++ }
                    14 -> options(
                        "Heat-resistant clothing to be worn at all times.",
                        "Rubber chickens to be worn on the head at all times.",
                        "Protective clothing to be worn; tools kept away from site."
                    ).also { stage++ }
                    15 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "Rubber chickens to be worn on the head at all times.").also { stage = 21 }
                        2 -> playerl(FacialExpression.FRIENDLY, "Gloves and boots to be worn at all times' proper tools must be used.").also {
                            stage = 16
                        }
                        3 -> playerl(FacialExpression.FRIENDLY, "Protective clothing to be worn; tools kept away from site.").also { stage = 21 }
                    }
                    16 -> npcl(FacialExpression.FRIENDLY, "Okay, that covers the level 1 Earth Sciences exam.").also { stage++ }
                    17 -> npcl(FacialExpression.FRIENDLY, "Let's see how you did...").also { stage++ }
                    18 -> npcl(FacialExpression.FRIENDLY, "You got all the questions correct. Well done!").also { stage++ }
                    19 -> playerl(FacialExpression.FRIENDLY, "Hey! Excellent!").also { stage++ }
                    20 -> npcl(FacialExpression.FRIENDLY, "You have now passed the Earth Sciences level 1 general exam. Here is your certificate to prove it. You also get a decent trowel to dig with. Of course, you'll want to get studying for your next exam now!").also {
                        stage = END_DIALOGUE
                    }
                    21 -> npcl(FacialExpression.FRIENDLY, "Okay, that covers the level 1 Earth Sciences exam.").also { stage++ }
                    22 -> npcl(FacialExpression.FRIENDLY, "Let's see how you did...").also { stage++ }
                    23 -> npcl(FacialExpression.FRIENDLY, "Oh dear me! This is appalling! I suggest you go and study properly.").also { stage++ }
                    24 -> playerl(FacialExpression.FRIENDLY, "Oh dear...").also { stage++ }
                    25 -> npcl(FacialExpression.FRIENDLY, "Why don't you use the resources here? There are books and the researchers... and you could even ask other students who are also studying for these exams.").also {
                        stage = END_DIALOGUE
                    }
                    26 -> npcl(FacialExpression.FRIENDLY, "Okay, next question...").also { stage++ }
                    27 -> npcl(FacialExpression.FRIENDLY, "Earth Sciences level 1, question 2 – Eligibility. Can you tell me which people are allowed to use the digsite?").also { stage++ }
                    28 -> options(
                        "Professors, students and workmen only.",
                        "Local residents, contractors and small pink fish.",
                        "All that have passed the appropriate Earth Sciences exam."
                    ).also { stage++ }
                    29 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "Professors, students and workmen only.").also { stage++ }
                        2 -> playerl(FacialExpression.FRIENDLY, "Local residents, contractors and small pink fish.").also { stage++ }
                        3 -> playerl(FacialExpression.FRIENDLY, "All that have passed the appropriate Earth Sciences exam.").also { stage++ }
                    }
                    30 -> ("Okay, next question...").also { stage++ }
                    //    31 -> Earth Sciences level 1, question 3 – Health and safety. Can you tell me the proper safety points when working on a digsite?").also { stage++ }
                    32 -> options(
                        "Heat-resistant clothing to be worn at all times.",
                        "Rubber chickens to be worn on the head at all times.",
                        "Protective clothing to be worn; tools kept away from site."
                    ).also { stage++ }
                    33 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "Rubber chickens to be worn on the head at all times.").also { stage++ }
                        2 -> playerl(FacialExpression.FRIENDLY, "Gloves and boots to be worn at all times' proper tools must be used.").also { stage++ }
                        3 -> playerl(FacialExpression.FRIENDLY, "Protective clothing to be worn; tools kept away from site.").also { stage++ }
                    }
                    34 -> npcl(FacialExpression.FRIENDLY, "Okay, that covers the level 1 Earth Sciences exam.").also { stage++ }
                    35 -> npcl(FacialExpression.FRIENDLY, "Let's see how you did...").also { stage++ }
                    36 -> npcl(FacialExpression.FRIENDLY, "Oh dear me! This is appalling! I suggest you go and study properly.").also { stage++ }
                    37 -> playerl(FacialExpression.FRIENDLY, "Oh dear...").also { stage++ }
                    38 -> npcl(FacialExpression.FRIENDLY, "Why don't you use the resources here? There are books and the researchers... and you could even ask other students who are also studying for these exams.").also {
                        stage = END_DIALOGUE
                    }
                    39 -> ("Okay, next question ...").also { stage++ }
                    //  40 -> Earth Sciences level1, question 3– Health and safety.Can you tell me the proper safety points when working on a digsite?").also { stage++ }
                    41 -> options(
                        "Heat-resistant clothing to be worn at all times.",
                        "Rubber chickens to be worn on the head at all times.",
                        "Protective clothing to be worn; tools kept away from site."
                    ).also { stage++ }
                    42 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "Rubber chickens to be worn on the head at all times.").also { stage++ }
                        2 -> playerl(FacialExpression.FRIENDLY, "Gloves and boots to be worn at all times' proper tools must be used.").also { stage++ }
                        3 -> playerl(FacialExpression.FRIENDLY, "Protective clothing to be worn; tools kept away from site.").also { stage++ }
                    }
                    43 -> npcl(FacialExpression.FRIENDLY, "Okay, that covers the level 1 Earth Sciences exam.").also { stage++ }
                    44 -> npcl(FacialExpression.FRIENDLY, "Let's see how you did...").also { stage++ }
                    45 -> npcl(FacialExpression.FRIENDLY, "Oh dear me! This is appalling! I suggest you go and study properly.").also { stage++ }
                    46 -> playerl(FacialExpression.FRIENDLY, "Oh dear...").also { stage++ }
                    47 -> npcl(FacialExpression.FRIENDLY, "Why don't you use the resources here? There are books and the researchers... and you could even ask other students who are also studying for these exams.").also {
                        stage = 48
                    }
                    48 -> {
                        end()
                        openInterface(player!!, 440)
                        addItem(player!!, Items.CERTIFICATE_769)
                        setQuestStage(player!!, "The Dig Site", 6)
                    }
                }
            }

            // After successfully completing the first exam before getting the answers for the second exam WITHOUT a trowel in inventory
            (questStage == 3 && !inInventory(player!!, Items.TROWEL_676)) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Hello.").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "Hello again.").also { stage++ }
                    2 -> options(
                        "I am ready for the next exam.",
                        "I am stuck on a question.",
                        "Sorry, I didn't mean to disturb you.",
                        "I have lost my trowel."
                    ).also { stage++ }

                    3 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "I am ready for the next exam.").also { stage = 4 }
                        2 -> playerl(FacialExpression.FRIENDLY, "I am stuck on a question.").also { stage = 21 }
                        3 -> playerl(FacialExpression.FRIENDLY, "Sorry, I didn't mean to disturb you.").also { stage = 22 }
                        4 -> playerl(FacialExpression.FRIENDLY, "I have lost my trowel.").also { stage = 23 }
                    }

                    4 -> npcl(FacialExpression.FRIENDLY, "Okay, this is the next part of the Earth Sciences exam: Earth Sciences level 2 - Intermediate.").also { stage++ }
                    5 -> npcl(FacialExpression.FRIENDLY, "Question 1 - Sample transportation. Can you tell me how we transport samples?").also { stage++ }
                    6 -> options(
                        "Samples cut and cleaned before transportation. ",
                        "Samples ground and suspended in an acid solution.",
                        "Samples to be given to the melon-collecting monkey."
                    ).also { stage++ }

                    7 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "Samples cut and cleaned before transportation. ").also { stage++ }
                        2 -> playerl(FacialExpression.FRIENDLY, "Samples ground and suspended in an acid solution.").also { stage++ }
                        3 -> playerl(FacialExpression.FRIENDLY, "Samples to be given to the melon-collecting monkey. ").also { stage++ }
                    }

                    8 -> npcl(FacialExpression.FRIENDLY, "Okay, next question...").also { stage++ }
                    9 -> npcl(FacialExpression.FRIENDLY, "Earth Sciences level 2, question 2 – Handling of finds. What is the proper way to handle finds?").also { stage++ }
                    10 -> options(
                        "Finds must not be handled by anyone. ",
                        "Finds to be given to the site workmen.",
                        "Drop them on the floor and jump on them. "
                    ).also { stage++ }

                    11 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "Finds must not be handled by anyone.").also { stage++ }
                        2 -> playerl(FacialExpression.FRIENDLY, "Finds to be given to the site workmen. ").also { stage++ }
                        3 -> playerl(FacialExpression.FRIENDLY, "Drop them on the floor and jump on them. ").also { stage++ }
                    }

                    12 -> ("Okay, next question...").also { stage++ }
                    //    13 -> Earth Sciences level 2, question 3 - Rock pick usage. Can you tell me the proper use for a rock pick?").also { stage++ }
                    14 -> options(
                        "Strike rock repeatedly until powdered.",
                        "Rock pick must be used flat and with strong force.",
                        "Rock picks are to be used to milk cows on a rainy morning."
                    ).also { stage++ }

                    15 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "Strike rock repeatedly until powdered.").also { stage++ }
                        2 -> playerl(FacialExpression.FRIENDLY, "Rock pick must be used flat and with strong force.").also { stage++ }
                        3 -> playerl(FacialExpression.FRIENDLY, "Rock picks are to be used to milk cows on a rainy morning. ").also { stage++ }
                    }

                    16 -> npcl(FacialExpression.FRIENDLY, "Okay, that covers the level 2 Earth Sciences exam.").also { stage++ }
                    17 -> npcl(FacialExpression.FRIENDLY, "Let me add up your total...").also { stage++ }
                    18 -> npcl(FacialExpression.FRIENDLY, "No, no, no! This will not do. Start again!").also { stage++ }
                    19 -> playerl(FacialExpression.FRIENDLY, "Oh no!").also { stage++ }
                    20 -> npcl(FacialExpression.FRIENDLY, "More studying for you!").also { stage = END_DIALOGUE }
                    21 -> npcl(FacialExpression.FRIENDLY, "Well, well, have you not been doing any studies? I am not giving you the answers, talk to the other students and remember the answers.").also {
                        stage = END_DIALOGUE
                    }

                    22 -> npcl(FacialExpression.FRIENDLY, "Oh, no problem at all.").also { stage = END_DIALOGUE }
                    23 -> npcl(FacialExpression.FRIENDLY, "Deary me. That was a good one as well. It's a good job I have another. Here you go...").also {
                        stage = END_DIALOGUE
                    }
                }
            }

            // After successfully completing the first exam before getting the answers for the second exam WITH a trowel in inventory
            (questStage == 4 && player!!.getAttribute("digsite:students", 0) > 3 && inInventory(
                player!!,
                Items.TROWEL_676
            )) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Hello.").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "Hello again.").also { stage++ }
                    2 -> options(
                        "I am ready for the next exam.",
                        "I am stuck on a question.",
                        "Sorry, I didn't mean to disturb you.",
                        "I have lost my trowel."
                    ).also { stage++ }
                    3 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "I am ready for the next exam.").also { stage = 4 }
                        2 -> playerl(FacialExpression.FRIENDLY, "I am stuck on a question.").also { stage = 21 }
                        3 -> playerl(FacialExpression.FRIENDLY, "Sorry, I didn't mean to disturb you.").also { stage = 22 }
                        4 -> playerl(FacialExpression.FRIENDLY, "I have lost my trowel.").also { stage = 23 }
                    }
                    4 -> npcl(FacialExpression.FRIENDLY, "Okay, this is the next part of the Earth Sciences exam: Earth Sciences level 2 - Intermediate.").also { stage++ }
                    5 -> npcl(FacialExpression.FRIENDLY, "Question 1 - Sample transportation. Can you tell me how we transport samples?").also { stage++ }
                    6 -> options(
                        "Samples cut and cleaned before transportation. ",
                        "Samples ground and suspended in an acid solution.",
                        "Samples to be given to the melon-collecting monkey."
                    ).also { stage++ }
                    7 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "Samples cut and cleaned before transportation. ").also { stage++ }
                        2 -> playerl(FacialExpression.FRIENDLY, "Samples ground and suspended in an acid solution.").also { stage++ }
                        3 -> playerl(FacialExpression.FRIENDLY, "Samples to be given to the melon-collecting monkey. ").also { stage++ }
                    }
                    8 -> npcl(FacialExpression.FRIENDLY, "Okay, next question...").also { stage++ }
                    9 -> npcl(FacialExpression.FRIENDLY, "Earth Sciences level 2, question 2 – Handling of finds. What is the proper way to handle finds?").also { stage++ }
                    10 -> options(
                        "Finds must not be handled by anyone. ",
                        "Finds to be given to the site workmen.",
                        "Drop them on the floor and jump on them. "
                    ).also { stage++ }
                    11 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "Finds must not be handled by anyone.").also { stage++ }
                        2 -> playerl(FacialExpression.FRIENDLY, "Finds to be given to the site workmen. ").also { stage++ }
                        3 -> playerl(FacialExpression.FRIENDLY, "Drop them on the floor and jump on them. ").also { stage++ }
                    }
                    12 -> ("Okay, next question...").also { stage++ }
                    //   13 -> Earth Sciences level2, question 3 -Rock pick usage.Can you tell me the proper usefor a rock pick?").also { stage++ }
                    14 -> options(
                        "Strike rock repeatedly until powdered.",
                        "Rock pick must be used flat and with strong force.",
                        "Rock picks are to be used to milk cows on a rainy morning."
                    ).also { stage++ }
                    15 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "Strike rock repeatedly until powdered.").also { stage++ }
                        2 -> playerl(FacialExpression.FRIENDLY, "Rock pick must be used flat and with strong force.").also { stage++ }
                        3 -> playerl(FacialExpression.FRIENDLY, "Rock picks are to be used to milk cows on a rainy morning. ").also { stage++ }
                    }
                    16 -> npcl(FacialExpression.FRIENDLY, "Okay, that covers the level 2 Earth Sciences exam.").also { stage++ }
                    17 -> npcl(FacialExpression.FRIENDLY, "Let me add up your total...").also { stage++ }
                    18 -> npcl(FacialExpression.FRIENDLY, "No, no, no! This will not do. Start again!").also { stage++ }
                    19 -> playerl(FacialExpression.FRIENDLY, "Oh no!").also { stage++ }
                    20 -> npcl(FacialExpression.FRIENDLY, "More studying for you!").also { stage = END_DIALOGUE }
                    21 -> npcl(FacialExpression.FRIENDLY, "Well, well, have you not been doing any studies? I am not giving you the answers, talk to the other students and remember the answers.").also {
                        stage = END_DIALOGUE
                    }
                    22 -> npcl(FacialExpression.FRIENDLY, "Oh, no problem at all.").also { stage = END_DIALOGUE }
                    23 -> npcl(FacialExpression.FRIENDLY, "Really? Look in your backpack and make sure first.").also { stage = END_DIALOGUE }
                }
            }

            // After getting answers from the three students for the second exam WITHOUT a trowel in inventory
            (questStage == 5 && player!!.getAttribute("digsite:students", 0) >= 6 && inInventory(
                player!!,
                Items.TROWEL_676
            )) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Hello.").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "Hello again.").also { stage++ }
                    2 -> options(
                        "I am ready for the next exam.",
                        "I am stuck on a question.",
                        "Sorry, I didn't mean to disturb you.",
                        "I have lost my trowel."
                    ).also { stage++ }
                    3 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "I am ready for the next exam.").also { stage = 4 }
                        2 -> playerl(FacialExpression.FRIENDLY, "I am stuck on a question.").also { stage = 34 }
                        3 -> playerl(FacialExpression.FRIENDLY, "Sorry, I didn't mean to disturb you.").also { stage = 35 }
                        4 -> playerl(FacialExpression.FRIENDLY, "I have lost my trowel.").also { stage = 36 }
                    }
                    4 -> npcl(FacialExpression.FRIENDLY, "Okay, this is the next part of the Earth Sciences exam: Earth Sciences level 2 - Intermediate.").also { stage++ }
                    5 -> npcl(FacialExpression.FRIENDLY, "Question 1 - Sample transportation. Can you tell me how we transport samples?").also { stage++ }
                    6 -> options(
                        "Samples ground and suspended in an acid solution.",
                        "Samples to be given to the melon-collecting monkey.",
                        "Samples taken in rough form; kept only in sealed containers."
                    ).also { stage++ }

                    7 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "Samples ground and suspended in an acid solution.").also { stage = 21 }
                        2 -> playerl(FacialExpression.FRIENDLY, "Samples to be given to the melon-collecting monkey.").also { stage = 21 }
                        3 -> playerl(FacialExpression.FRIENDLY, "Samples taken in rough form; kept only in sealed containers.").also { stage++ }
                    }
                    8 -> npcl(FacialExpression.FRIENDLY, "Okay, next question...").also { stage++ }
                    9 -> npcl(FacialExpression.FRIENDLY, "Earth Sciences level 2, question 2 – Handling of finds. What is the proper way to handle finds?").also { stage++ }
                    10 -> options(
                        "Finds must be carefully handled, and gloves worn.",
                        "Finds to be given to the site workmen.",
                        "Drop them on the floor and jump on them."
                    ).also { stage++ }
                    11 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "Finds must be carefully handled, and gloves worn.").also { stage++ }
                        2 -> playerl(FacialExpression.FRIENDLY, "Finds to be given to the site workmen.").also { stage = 25 }
                        3 -> playerl(FacialExpression.FRIENDLY, "Drop them on the floor and jump on them.").also { stage = 25 }
                    }
                    12 -> ("Okay, next question...").also { stage++ }
                    //    13 -> Earth Sciences level 2, question 3 - Rock pick usage. Can you tell me the proper use for a rock pick?").also { stage++ }
                    14 -> options(
                        "Rock pick must be used flat and with strong force.",
                        "Always handle with care; strike cleanly on its cleaving point.",
                        "Rock picks are to be used to milk cows on a rainy morning."
                    ).also { stage++ }
                    15 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "Rock pick must be used flat and with strong force.").also { stage = 29 }
                        2 -> playerl(FacialExpression.FRIENDLY, "Always handle with care; strike cleanly on its cleaving point. ").also { stage++ }
                        3 -> playerl(FacialExpression.FRIENDLY, "Rock picks are to be used to milk cows on a rainy morning.").also { stage = 29 }
                    }
                    16 -> npcl(FacialExpression.FRIENDLY, "Okay, that covers the level 2 Earth Sciences exam.").also { stage++ }
                    17 -> npcl(FacialExpression.FRIENDLY, "Let me add up your total...").also { stage++ }
                    18 -> npcl(FacialExpression.FRIENDLY, "You got all the questions correct, well done!").also { stage++ }
                    19 -> playerl(FacialExpression.FRIENDLY, "Great, I'm getting good at this.").also { stage++ }
                    20 -> npcl(FacialExpression.FRIENDLY, "You have now passed the Earth Sciences level 2 intermediate exam. Here is your certificate. You also get a nice rock pick. Of course, you'll want to get studying for your next exam now!").also {
                        stage = END_DIALOGUE
                    }
                    21 -> npcl(FacialExpression.FRIENDLY, "Okay, next question...").also { stage++ }
                    22 -> npcl(FacialExpression.FRIENDLY, "Earth Sciences level 2, question 2 – Handling of finds. What is the proper way to handle finds?").also { stage++ }
                    23 -> options(
                        "Finds must be carefully handled, and gloves worn.",
                        "Finds to be given to the site workmen.",
                        "Drop them on the floor and jump on them."
                    ).also { stage++ }
                    24 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "Finds must be carefully handled, and gloves worn.").also { stage++ }
                        2 -> playerl(FacialExpression.FRIENDLY, "Finds to be given to the site workmen.").also { stage++ }
                        3 -> playerl(FacialExpression.FRIENDLY, "Drop them on the floor and jump on them.").also { stage++ }
                    }
                    25 -> ("Okay, next question...").also { stage++ }
                    //     26 -> Earth Sciences level 2, question 3 - Rock pick usage. Can you tell me the proper use for a rock pick?").also { stage++ }
                    27 -> options(
                        "Rock pick must be used flat and with strong force.",
                        "Always handle with care; strike cleanly on its cleaving point.",
                        "Rock picks are to be used to milk cows on a rainy morning."
                    ).also { stage++ }
                    28 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "Rock pick must be used flat and with strong force.").also { stage++ }
                        2 -> playerl(FacialExpression.FRIENDLY, "Always handle with care; strike cleanly on its cleaving point. ").also { stage++ }
                        3 -> playerl(FacialExpression.FRIENDLY, "Rock picks are to be used to milk cows on a rainy morning.").also { stage++ }
                    }
                    29 -> npcl(FacialExpression.FRIENDLY, "Okay, that covers the level 2 Earth Sciences exam.").also { stage++ }
                    30 -> npcl(FacialExpression.FRIENDLY, "Let me add up your total...").also { stage++ }
                    31 -> npcl(FacialExpression.FRIENDLY, "No, no, no! This will not do. Start again!").also { stage++ }
                    32 -> playerl(FacialExpression.FRIENDLY, "Oh no!").also { stage++ }
                    33 -> npcl(FacialExpression.FRIENDLY, "More studying for you!").also { stage = END_DIALOGUE }
                    34 -> npcl(FacialExpression.FRIENDLY, "Well, well, have you not been doing any studies? I am not giving you the answers, talk to the other students and remember the answers.").also {
                        stage = END_DIALOGUE
                    }
                    35 -> npcl(FacialExpression.FRIENDLY, "Oh, no problem at all.").also { stage = END_DIALOGUE }
                    36 -> npcl(FacialExpression.FRIENDLY, "Deary me. That was a good one as well. It's a good job I have another. Here you go...").also {
                        stage = END_DIALOGUE
                    }
                }
            }

            // After getting answers from the three students for the second exam WITH a trowel in inventory
            (questStage == 6 && player!!.getAttribute("digsite:students", 0) >= 6 && inInventory(
                player!!,
                Items.TROWEL_676
            )) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Hello.").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "Hello again.").also { stage++ }
                    2 -> options(
                        "I am ready for the next exam.",
                        "I am stuck on a question.",
                        "Sorry, I didn't mean to disturb you.",
                        "I have lost my trowel."
                    ).also { stage++ }
                    3 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "I am ready for the next exam.").also { stage = 4 }
                        2 -> playerl(FacialExpression.FRIENDLY, "I am stuck on a question.").also { stage = 34 }
                        3 -> playerl(FacialExpression.FRIENDLY, "Sorry, I didn't mean to disturb you.").also { stage = 35 }
                        4 -> playerl(FacialExpression.FRIENDLY, "I have lost my trowel.").also { stage = 36 }
                    }
                    4 -> npcl(FacialExpression.FRIENDLY, "Okay, this is the next part of the Earth Sciences exam: Earth Sciences level 2 - Intermediate.").also { stage++ }
                    5 -> npcl(FacialExpression.FRIENDLY, "Question 1 - Sample transportation. Can you tell me how we transport samples?").also { stage++ }
                    6 -> options(
                        "Samples ground and suspended in an acid solution.",
                        "Samples to be given to the melon-collecting monkey.",
                        "Samples taken in rough form; kept only in sealed containers."
                    ).also { stage++ }
                    7 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "Samples ground and suspended in an acid solution.").also { stage = 21 }
                        2 -> playerl(FacialExpression.FRIENDLY, "Samples to be given to the melon-collecting monkey.").also { stage = 21 }
                        3 -> playerl(FacialExpression.FRIENDLY, "Samples taken in rough form; kept only in sealed containers.").also { stage++ }
                    }
                    8 -> npcl(FacialExpression.FRIENDLY, "Okay, next question...").also { stage++ }
                    9 -> npcl(FacialExpression.FRIENDLY, "Earth Sciences level 2, question 2 – Handling of finds. What is the proper way to handle finds?").also { stage++ }
                    10 -> options(
                        "Finds must be carefully handled, and gloves worn.",
                        "Finds to be given to the site workmen.",
                        "Drop them on the floor and jump on them."
                    ).also { stage++ }
                    11 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "Finds must be carefully handled, and gloves worn.").also { stage++ }
                        2 -> playerl(FacialExpression.FRIENDLY, "Finds to be given to the site workmen.").also { stage = 25 }
                        3 -> playerl(FacialExpression.FRIENDLY, "Drop them on the floor and jump on them.").also { stage = 25 }
                    }
                    12 -> ("Okay, next question...").also { stage++ }
                    //      13 -> Earth Sciences level 2, question 3 - Rock pick usage. Can you tell me the proper use for a rock pick?").also { stage++ }
                    14 -> options(
                        "Rock pick must be used flat and with strong force.",
                        "Always handle with care; strike cleanly on its cleaving point.",
                        "Rock picks are to be used to milk cows on a rainy morning."
                    ).also { stage++ }
                    15 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "Rock pick must be used flat and with strong force.").also { stage = 29 }
                        2 -> playerl(FacialExpression.FRIENDLY, "Always handle with care; strike cleanly on its cleaving point. ").also { stage++ }
                        3 -> playerl(FacialExpression.FRIENDLY, "Rock picks are to be used to milk cows on a rainy morning.").also { stage = 29 }
                    }
                    16 -> npcl(FacialExpression.FRIENDLY, "Okay, that covers the level 2 Earth Sciences exam.").also { stage++ }
                    17 -> npcl(FacialExpression.FRIENDLY, "Let me add up your total...").also { stage++ }
                    18 -> npcl(FacialExpression.FRIENDLY, "You got all the questions correct, well done!").also { stage++ }
                    19 -> playerl(FacialExpression.FRIENDLY, "Great, I'm getting good at this.").also { stage++ }
                    20 -> npcl(FacialExpression.FRIENDLY, "You have now passed the Earth Sciences level 2 intermediate exam. Here is your certificate. You also get a nice rock pick. Of course, you'll want to get studying for your next exam now!").also {
                        stage = END_DIALOGUE
                    }
                    21 -> npcl(FacialExpression.FRIENDLY, "Okay, next question...").also { stage++ }
                    22 -> npcl(FacialExpression.FRIENDLY, "Earth Sciences level 2, question 2 – Handling of finds. What is the proper way to handle finds?").also { stage++ }
                    23 -> options(
                        "Finds must be carefully handled, and gloves worn.",
                        "Finds to be given to the site workmen.",
                        "Drop them on the floor and jump on them."
                    ).also { stage++ }
                    24 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "Finds must be carefully handled, and gloves worn.").also { stage++ }
                        2 -> playerl(FacialExpression.FRIENDLY, "Finds to be given to the site workmen.").also { stage++ }
                        3 -> playerl(FacialExpression.FRIENDLY, "Drop them on the floor and jump on them.").also { stage++ }
                    }
                    25 -> ("Okay, next question...").also { stage++ }
                    //    26 -> Earth Sciences level 2, question 3 - Rock pick usage. Can you tell me the proper use for a rock pick?").also { stage++ }
                    27 -> options(
                        "Rock pick must be used flat and with strong force.",
                        "Always handle with care; strike cleanly on its cleaving point.",
                        "Rock picks are to be used to milk cows on a rainy morning."
                    ).also { stage++ }
                    28 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "Rock pick must be used flat and with strong force.").also { stage++ }
                        2 -> playerl(FacialExpression.FRIENDLY, "Always handle with care; strike cleanly on its cleaving point. ").also { stage++ }
                        3 -> playerl(FacialExpression.FRIENDLY, "Rock picks are to be used to milk cows on a rainy morning.").also { stage++ }
                    }
                    29 -> npcl(FacialExpression.FRIENDLY, "Okay, that covers the level 2 Earth Sciences exam.").also { stage++ }
                    30 -> npcl(FacialExpression.FRIENDLY, "Let me add up your total...").also { stage++ }
                    31 -> npcl(FacialExpression.FRIENDLY, "No, no, no! This will not do. Start again!").also { stage++ }
                    32 -> playerl(FacialExpression.FRIENDLY, "Oh no!").also { stage++ }
                    33 -> npcl(FacialExpression.FRIENDLY, "More studying for you!").also { stage = END_DIALOGUE }
                    34 -> npcl(FacialExpression.FRIENDLY, "Well, well, have you not been doing any studies? I am not giving you the answers, talk to the other students and remember the answers.").also {
                        stage = END_DIALOGUE
                    }
                    35 -> npcl(FacialExpression.FRIENDLY, "Oh, no problem at all.").also { stage = END_DIALOGUE }
                    36 -> npcl(FacialExpression.FRIENDLY, "Really? Look in your backpack and make sure first.").also { stage = END_DIALOGUE }
                }
            }

            // Before getting answers from the three students for the third exam WITHOUT a trowel in inventory
            (questStage == 7 && player!!.getAttribute("digsite:students", 0) >= 7 && !inInventory(
                player!!,
                Items.TROWEL_676
            )) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Hello.").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "Hello again.").also { stage++ }
                    2 -> options(
                        "I am ready for the next exam.",
                        "I am stuck on a question.",
                        "Sorry, I didn't mean to disturb you.",
                        "I have lost my trowel."
                    ).also { stage++ }
                    3 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "I am ready for the next exam.").also { stage = 4 }
                        2 -> playerl(FacialExpression.FRIENDLY, "I am stuck on a question.").also { stage = 20 }
                        3 -> playerl(FacialExpression.FRIENDLY, "Sorry, I didn't mean to disturb you.").also { stage = 21 }
                        4 -> playerl(FacialExpression.FRIENDLY, "I have lost my trowel.").also { stage = 22 }
                    }
                    4 -> npcl(FacialExpression.FRIENDLY, "Okay, this is the next part of the Earth Sciences exam: Earth Sciences level 3 - Advanced.").also { stage++ }
                    5 -> npcl(FacialExpression.FRIENDLY, "Question 1 - Sample preparation. Can you tell me how we prepare samples?").also { stage++ }
                    6 -> options(
                        "Samples may be mixed together safely.",
                        "Sample types catalogued and carried by hand only.",
                        "Samples taken in rough form; kept only in sealed containers."
                    ).also { stage++ }
                    7 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "Samples may be mixed together safely.").also { stage++ }
                        2 -> playerl(FacialExpression.FRIENDLY, "Sample types catalogued and carried by hand only.").also { stage++ }
                        3 -> playerl(FacialExpression.FRIENDLY, "Samples to be spread thickly with mashed banana.").also { stage++ }
                    }
                    8 -> npcl(FacialExpression.FRIENDLY, "Okay, next question...").also { stage++ }
                    9 -> npcl(FacialExpression.FRIENDLY, "Earth Sciences level 3, question 2  - Specimen brush use. What is the proper way to use a specimen brush?").also { stage++ }
                    10 -> options(
                        "Brush quickly using a wet brush.",
                        "Dipped in glue and stuck to a sheep's back.",
                        "Brush quickly and with force."
                    ).also { stage++ }
                    11 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "Brush quickly using a wet brush.").also { stage++ }
                        2 -> playerl(FacialExpression.FRIENDLY, "Dipped in glue and stuck to a sheep's back.").also { stage++ }
                        3 -> playerl(FacialExpression.FRIENDLY, "Brush quickly and with force. ").also { stage++ }
                    }
                    12 -> ("Okay, next question...").also { stage++ }
                    //    13 -> Earth Sciences level 3, question 3 - Advanced techniques. Can you describe the technique for handling bones?").also { stage++ }
                    14 -> options(
                        "Bones must not be taken from the site.",
                        "Feed to hungry dogs.",
                        "Bones to be ground and tested for mineral content."
                    ).also { stage++ }
                    15 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "Bones must not be taken from the site.").also { stage++ }
                        2 -> playerl(FacialExpression.FRIENDLY, "Feed to hungry dogs.").also { stage++ }
                        3 -> playerl(FacialExpression.FRIENDLY, "Bones to be ground and tested for mineral content.").also { stage++ }
                    }
                    16 -> npcl(FacialExpression.FRIENDLY, "Okay, that covers the level 3 Earth Sciences exam.").also { stage++ }
                    17 -> npcl(FacialExpression.FRIENDLY, "Let me add up the results...").also { stage++ }
                    18 -> npcl(FacialExpression.FRIENDLY, "I cannot believe this! Absolutely none right at all. I doubt you did any research before you took this exam...").also { stage++ }
                    19 -> playerl(FacialExpression.FRIENDLY, "Ah... Yes... Erm.... I think I had better go and revise first!").also {
                        stage = END_DIALOGUE
                    }
                    20 -> npcl(FacialExpression.FRIENDLY, "Well, well, have you not been doing any studies? I am not giving you the answers, talk to the other students and remember the answers.").also {
                        stage = END_DIALOGUE
                    }
                    21 -> npcl(FacialExpression.FRIENDLY, "Oh, no problem at all.").also { stage = END_DIALOGUE }
                    22 -> npcl(FacialExpression.FRIENDLY, "Deary me. That was a good one as well. It's a good job I have another. Here you go...").also {
                        stage = END_DIALOGUE
                    }
                }
            }

            // before getting answers from the three students for the third exam WITH a trowel in inventory
            (questStage == 8 && player!!.getAttribute("digsite:students", 0) == 6 && inInventory(
                player!!,
                Items.TROWEL_676
            )) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Hello.").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "Hello again.").also { stage++ }
                    2 -> options(
                        "I am ready for the next exam.",
                        "I am stuck on a question.",
                        "Sorry, I didn't mean to disturb you.",
                        "I have lost my trowel."
                    ).also { stage++ }
                    3 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "I am ready for the next exam.").also { stage = 4 }
                        2 -> playerl(FacialExpression.FRIENDLY, "I am stuck on a question.").also { stage = 20 }
                        3 -> playerl(FacialExpression.FRIENDLY, "Sorry, I didn't mean to disturb you.").also { stage = 21 }
                        4 -> playerl(FacialExpression.FRIENDLY, "I have lost my trowel.").also { stage = 22 }
                    }
                    4 -> npcl(FacialExpression.FRIENDLY, "Okay, this is the next part of the Earth Sciences exam: Earth Sciences level 3 - Advanced.").also { stage++ }
                    5 -> npcl(FacialExpression.FRIENDLY, "Question 1 - Sample preparation. Can you tell me how we prepare samples?").also { stage++ }
                    6 -> options(
                        "Samples may be mixed together safely.",
                        "Sample types catalogued and carried by hand only.",
                        "Samples taken in rough form; kept only in sealed containers."
                    ).also { stage++ }
                    7 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "Samples may be mixed together safely.").also { stage++ }
                        2 -> playerl(FacialExpression.FRIENDLY, "Sample types catalogued and carried by hand only.").also { stage++ }
                        3 -> playerl(FacialExpression.FRIENDLY, "Samples to be spread thickly with mashed banana.").also { stage++ }
                    }
                    8 -> npcl(FacialExpression.FRIENDLY, "Okay, next question...").also { stage++ }
                    9 -> npcl(FacialExpression.FRIENDLY, "Earth Sciences level 3, question 2  - Specimen brush use. What is the proper way to use a specimen brush?").also { stage++ }
                    10 -> options(
                        "Brush quickly using a wet brush.",
                        "Dipped in glue and stuck to a sheep's back.",
                        "Brush quickly and with force."
                    ).also { stage++ }
                    11 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "Brush quickly using a wet brush.").also { stage++ }
                        2 -> playerl(FacialExpression.FRIENDLY, "Dipped in glue and stuck to a sheep's back.").also { stage++ }
                        3 -> playerl(FacialExpression.FRIENDLY, "Brush quickly and with force. ").also { stage++ }
                    }
                    12 -> ("Okay, next question...").also { stage++ }
                    //    13 -> Earth Sciences level 3, question 3 - Advanced techniques. Can you describe the technique for handling bones?").also { stage++ }
                    14 -> options(
                        "Bones must not be taken from the site.",
                        "Feed to hungry dogs.",
                        "Bones to be ground and tested for mineral content."
                    ).also { stage++ }
                    15 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "Bones must not be taken from the site.").also { stage++ }
                        2 -> playerl(FacialExpression.FRIENDLY, "Feed to hungry dogs.").also { stage++ }
                        3 -> playerl(FacialExpression.FRIENDLY, "Bones to be ground and tested for mineral content.").also { stage++ }
                    }
                    16 -> npcl(FacialExpression.FRIENDLY, "Okay, that covers the level 3 Earth Sciences exam.").also { stage++ }
                    17 -> npcl(FacialExpression.FRIENDLY, "Let me add up the results...").also { stage++ }
                    18 -> npcl(FacialExpression.FRIENDLY, "I cannot believe this! Absolutely none right at all. I doubt you did any research before you took this exam...").also { stage++ }
                    19 -> playerl(FacialExpression.FRIENDLY, "Ah... Yes... Erm.... I think I had better go and revise first!").also {
                        stage = END_DIALOGUE
                    }
                    20 -> npcl(FacialExpression.FRIENDLY, "Well, well, have you not been doing any studies? I am not giving you the answers, talk to the other students and remember the answers.").also {
                        stage = END_DIALOGUE
                    }
                    21 -> npcl(FacialExpression.FRIENDLY, "Oh, no problem at all.").also { stage = END_DIALOGUE }
                    22 -> npcl(FacialExpression.FRIENDLY, "Really? Look in your backpack and make sure first.").also { stage = END_DIALOGUE }
                }
            }

            // After getting answers from the three students for the third exam WITH a trowel in inventory
            (questStage == 9 && player!!.getAttribute("digsite:students", 0) >= 9 && inInventory(
                player!!,
                Items.TROWEL_676
            )) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Hello.").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "Hello again.").also { stage++ }
                    2 -> options(
                        "I am ready for the next exam.",
                        "I am stuck on a question.",
                        "Sorry, I didn't mean to disturb you.",
                        "I have lost my trowel."
                    ).also { stage++ }
                    3 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "I am ready for the next exam.").also { stage = 4 }
                        2 -> playerl(FacialExpression.FRIENDLY, "I am stuck on a question.").also { stage = 34 }
                        3 -> playerl(FacialExpression.FRIENDLY, "Sorry, I didn't mean to disturb you.").also { stage = 35 }
                        4 -> playerl(FacialExpression.FRIENDLY, "I have lost my trowel.").also { stage = 36 }
                    }
                    4 -> npcl(FacialExpression.FRIENDLY, "Okay, this is the next part of the Earth Sciences exam: Earth Sciences level 3 - Advanced.").also { stage++ }
                    5 -> npcl(FacialExpression.FRIENDLY, "Question 1 - Sample preparation. Can you tell me how we prepare samples?").also { stage++ }
                    6 -> options(
                        "Samples cleaned, and carried only in specimen jars.",
                        "Sample types catalogued and carried by hand only.",
                        "Samples taken in rough form; kept only in sealed containers."
                    ).also { stage++ }
                    7 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "Samples cleaned, and carried only in specimen jars.").also { stage++ }
                        2 -> playerl(FacialExpression.FRIENDLY, "Sample types catalogued and carried by hand only.").also { stage = 23 }
                        3 -> playerl(FacialExpression.FRIENDLY, "Samples to be spread thickly with mashed banana.").also { stage = 23 }
                    }
                    8 -> npcl(FacialExpression.FRIENDLY, "Okay, next question...").also { stage++ }
                    9 -> npcl(FacialExpression.FRIENDLY, "Earth Sciences level 3, question 2  - Specimen brush use. What is the proper way to use a specimen brush?").also { stage++ }
                    10 -> options(
                        "Brush carefully and slowly using short strokes.",
                        "Dipped in glue and stuck to a sheep's back.",
                        "Brush quickly and with force."
                    ).also { stage++ }
                    11 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "Brush carefully and slowly using short strokes.").also { stage++ }
                        2 -> playerl(FacialExpression.FRIENDLY, "Dipped in glue and stuck to a sheep's back.").also { stage = 27 }
                        3 -> playerl(FacialExpression.FRIENDLY, "Brush quickly and with force. ").also { stage = 27 }
                    }
                    12 -> ("Okay, next question...").also { stage++ }
                    //    13 -> Earth Sciences level 3, question 3 - Advanced techniques. Can you describe the technique for handling bones?").also { stage++ }
                    14 -> options(
                        "Feed to hungry dogs.",
                        "Bones to be ground and tested for mineral content.",
                        "Handle bones very carefully and keep them away from other samples."
                    ).also { stage++ }
                    15 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "Feed to hungry dogs.").also { stage = 31 }
                        2 -> playerl(FacialExpression.FRIENDLY, "Bones to be ground and tested for mineral content.").also { stage++ }
                        3 -> playerl(FacialExpression.FRIENDLY, "Handle bones very carefully and keep them away from other samples.").also {
                            stage = 31
                        }
                    }
                    16 -> npcl(FacialExpression.FRIENDLY, "Okay, that covers the level 3 Earth Sciences exam.").also { stage++ }
                    17 -> npcl(FacialExpression.FRIENDLY, "Let me add up the results...").also { stage++ }
                    18 -> npcl(FacialExpression.FRIENDLY, "You got all the questions correct, well done!").also { stage++ }
                    19 -> playerl(FacialExpression.FRIENDLY, "Hooray!").also { stage++ }
                    20 -> npcl(FacialExpression.FRIENDLY, "Congratulations! You have now passed the Earth Sciences level 3 exam. Here is your level 3 certificate and a specimen jar and brush.").also { stage++ }
                    21 -> playerl(FacialExpression.FRIENDLY, "I can dig wherever I want now!").also { stage++ }
                    22 -> npcl(FacialExpression.FRIENDLY, "Perhaps you should use your newfound skills to find an artefact on the site that will impress Terry, our archaeological expert.").also {
                        stage = END_DIALOGUE
                    }
                    23 -> npcl(FacialExpression.FRIENDLY, "Okay, next question...").also { stage++ }
                    24 -> npcl(FacialExpression.FRIENDLY, "Earth Sciences level 2, question 2 – Handling of finds. What is the proper way to handle finds?").also { stage++ }
                    25 -> options(
                        "Finds must be carefully handled, and gloves worn.",
                        "Finds to be given to the site workmen.",
                        "Drop them on the floor and jump on them."
                    ).also { stage++ }
                    26 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "Finds must be carefully handled, and gloves worn.").also { stage++ }
                        2 -> playerl(FacialExpression.FRIENDLY, "Finds to be given to the site workmen.").also { stage++ }
                        3 -> playerl(FacialExpression.FRIENDLY, "Drop them on the floor and jump on them.").also { stage++ }
                    }
                    27 -> ("Okay, next question...").also { stage++ }
                    //    28 -> Earth Sciences level 2, question 3 - Rock pick usage. Can you tell me the proper use for a rock pick?").also { stage++ }
                    29 -> options(
                        "Rock pick must be used flat and with strong force.",
                        "Always handle with care; strike cleanly on its cleaving point.",
                        "Rock picks are to be used to milk cows on a rainy morning."
                    ).also { stage++ }
                    30 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "Rock pick must be used flat and with strong force.").also { stage++ }
                        2 -> playerl(FacialExpression.FRIENDLY, "Always handle with care; strike cleanly on its cleaving point. ").also { stage++ }
                        3 -> playerl(FacialExpression.FRIENDLY, "Rock picks are to be used to milk cows on a rainy morning.").also { stage++ }
                    }
                    31 -> npcl(FacialExpression.FRIENDLY, "Okay, that covers the level 2 Earth Sciences exam.").also { stage++ }
                    32 -> npcl(FacialExpression.FRIENDLY, "Let me add up the results...").also { stage++ }
                    33 -> npcl(FacialExpression.FRIENDLY, "No, no, no! This will not do. Start again!").also { stage++ }
                    34 -> playerl(FacialExpression.FRIENDLY, "Oh no!").also { stage++ }
                    35 -> npcl(FacialExpression.FRIENDLY, "More studying for you!").also { stage = END_DIALOGUE }
                    36 -> npcl(FacialExpression.FRIENDLY, "Well, well, have you not been doing any studies? I am not giving you the answers, talk to the other students and remember the answers.").also {
                        stage = END_DIALOGUE
                    }
                    37 -> npcl(FacialExpression.FRIENDLY, "Oh, no problem at all.").also { stage = END_DIALOGUE }
                    38 -> npcl(FacialExpression.FRIENDLY, "Really? Look in your backpack and make sure first.").also { stage = END_DIALOGUE }
                }
            }

            // After getting answers from the three students for the third exam WITHOUT a trowel in inventory
            (questStage == 9 && player!!.getAttribute("digsite:students", 0) >= 9 && !inInventory(
                player!!,
                Items.TROWEL_676
            )) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Hello.").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "Hello again.").also { stage++ }
                    2 -> options(
                        "I am ready for the next exam.",
                        "I am stuck on a question.",
                        "Sorry, I didn't mean to disturb you.",
                        "I have lost my trowel."
                    ).also { stage++ }
                    3 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "I am ready for the next exam.").also { stage = 4 }
                        2 -> playerl(FacialExpression.FRIENDLY, "I am stuck on a question.").also { stage = 34 }
                        3 -> playerl(FacialExpression.FRIENDLY, "Sorry, I didn't mean to disturb you.").also { stage = 35 }
                        4 -> playerl(FacialExpression.FRIENDLY, "I have lost my trowel.").also { stage = 36 }
                    }
                    4 -> npcl(FacialExpression.FRIENDLY, "Okay, this is the next part of the Earth Sciences exam: Earth Sciences level 3 - Advanced.").also { stage++ }
                    5 -> npcl(FacialExpression.FRIENDLY, "Question 1 - Sample preparation. Can you tell me how we prepare samples?").also { stage++ }
                    6 -> options(
                        "Samples cleaned, and carried only in specimen jars.",
                        "Sample types catalogued and carried by hand only.",
                        "Samples taken in rough form; kept only in sealed containers."
                    ).also { stage++ }
                    7 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "Samples cleaned, and carried only in specimen jars.").also { stage++ }
                        2 -> playerl(FacialExpression.FRIENDLY, "Sample types catalogued and carried by hand only.").also { stage = 23 }
                        3 -> playerl(FacialExpression.FRIENDLY, "Samples to be spread thickly with mashed banana.").also { stage = 23 }
                    }
                    8 -> npcl(FacialExpression.FRIENDLY, "Okay, next question...").also { stage++ }
                    9 -> npcl(FacialExpression.FRIENDLY, "Earth Sciences level 3, question 2  - Specimen brush use. What is the proper way to use a specimen brush?").also { stage++ }
                    10 -> options(
                        "Brush carefully and slowly using short strokes.",
                        "Dipped in glue and stuck to a sheep's back.",
                        "Brush quickly and with force."
                    ).also { stage++ }
                    11 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "Brush carefully and slowly using short strokes.").also { stage++ }
                        2 -> playerl(FacialExpression.FRIENDLY, "Dipped in glue and stuck to a sheep's back.").also { stage = 27 }
                        3 -> playerl(FacialExpression.FRIENDLY, "Brush quickly and with force. ").also { stage = 27 }
                    }
                    12 -> ("Okay, next question...").also { stage++ }
                    //    13 -> Earth Sciences level 3, question 3 - Advanced techniques. Can you describe the technique for handling bones?").also { stage++ }
                    14 -> options(
                        "Feed to hungry dogs.",
                        "Bones to be ground and tested for mineral content.",
                        "Handle bones very carefully and keep them away from other samples."
                    ).also { stage++ }
                    15 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "Feed to hungry dogs.").also { stage = 31 }
                        2 -> playerl(FacialExpression.FRIENDLY, "Bones to be ground and tested for mineral content.").also { stage++ }
                        3 -> playerl(FacialExpression.FRIENDLY, "Handle bones very carefully and keep them away from other samples.").also {
                            stage = 31
                        }
                    }
                    16 -> npcl(FacialExpression.FRIENDLY, "Okay, that covers the level 3 Earth Sciences exam.").also { stage++ }
                    17 -> npcl(FacialExpression.FRIENDLY, "Let me add up the results...").also { stage++ }
                    18 -> npcl(FacialExpression.FRIENDLY, "You got all the questions correct, well done!").also { stage++ }
                    19 -> playerl(FacialExpression.FRIENDLY, "Hooray!").also { stage++ }
                    20 -> npcl(FacialExpression.FRIENDLY, "Congratulations! You have now passed the Earth Sciences level 3 exam. Here is your level 3 certificate and a specimen jar and brush.").also { stage++ }
                    21 -> playerl(FacialExpression.FRIENDLY, "I can dig wherever I want now!").also { stage++ }
                    22 -> npcl(FacialExpression.FRIENDLY, "Perhaps you should use your newfound skills to find an artefact on the site that will impress Terry, our archaeological expert.").also {
                        stage = END_DIALOGUE
                    }
                    23 -> npcl(FacialExpression.FRIENDLY, "Okay, next question...").also { stage++ }
                    24 -> npcl(FacialExpression.FRIENDLY, "Earth Sciences level 2, question 2 – Handling of finds. What is the proper way to handle finds?").also { stage++ }
                    25 -> options(
                        "Finds must be carefully handled, and gloves worn.",
                        "Finds to be given to the site workmen.",
                        "Drop them on the floor and jump on them."
                    ).also { stage++ }
                    26 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "Finds must be carefully handled, and gloves worn.").also { stage++ }
                        2 -> playerl(FacialExpression.FRIENDLY, "Finds to be given to the site workmen.").also { stage++ }
                        3 -> playerl(FacialExpression.FRIENDLY, "Drop them on the floor and jump on them.").also { stage++ }
                    }
                    27 -> ("Okay, next question...").also { stage++ }
                    //    28 -> Earth Sciences level 2, question 3 - Rock pick usage. Can you tell me the proper use for a rock pick?").also { stage++ }
                    29 -> options(
                        "Rock pick must be used flat and with strong force.",
                        "Always handle with care; strike cleanly on its cleaving point.",
                        "Rock picks are to be used to milk cows on a rainy morning."
                    ).also { stage++ }
                    30 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "Rock pick must be used flat and with strong force.").also { stage++ }
                        2 -> playerl(FacialExpression.FRIENDLY, "Always handle with care; strike cleanly on its cleaving point. ").also { stage++ }
                        3 -> playerl(FacialExpression.FRIENDLY, "Rock picks are to be used to milk cows on a rainy morning.").also { stage++ }
                    }
                    31 -> npcl(FacialExpression.FRIENDLY, "Okay, that covers the level 2 Earth Sciences exam.").also { stage++ }
                    32 -> npcl(FacialExpression.FRIENDLY, "Let me add up the results...").also { stage++ }
                    33 -> npcl(FacialExpression.FRIENDLY, "No, no, no! This will not do. Start again!").also { stage++ }
                    34 -> playerl(FacialExpression.FRIENDLY, "Oh no!").also { stage++ }
                    35 -> npcl(FacialExpression.FRIENDLY, "More studying for you!").also { stage = END_DIALOGUE }
                    36 -> npcl(FacialExpression.FRIENDLY, "Well, well, have you not been doing any studies? I am not giving you the answers, talk to the other students and remember the answers.").also {
                        stage = END_DIALOGUE
                    }
                    37 -> npcl(FacialExpression.FRIENDLY, "Oh, no problem at all.").also { stage = END_DIALOGUE }
                    38 -> npcl(FacialExpression.FRIENDLY, "Deary me. That was a good one as well. It's a good job I have another. Here you go...").also {
                        stage = END_DIALOGUE
                    }
                }
            }

            // After passing the third exam
            (questStage == 12) -> {
                when (stage) {
                    0 -> npcl(FacialExpression.FRIENDLY, "Well, what are you doing here? Get digging!").also { stage = END_DIALOGUE }
                }
            }

            // After quest
            (questStage == 100) -> {
                when (stage) {
                    0 -> npcl(FacialExpression.FRIENDLY, "Hello there!").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "My colleague tells me you helped to uncover a hidden altar to the god Zaros. A great scholar and archaeologist indeed! Good health and prosperity to you.").also { stage++ }
                    3 -> playerl(FacialExpression.FRIENDLY, "Thanks!").also { stage = END_DIALOGUE }
                }
            }
        }
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.EXAMINER_618)
    }
}