package content.quest.member.digsite.dialogue

import config.NPCs
import core.api.getQuestStage
import core.api.sendDialogue
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the Archaeological Expert dialogue plugin for The Dig Site quest.
 */
@Initializable
class ArchaeologicalExpertDigsiteDialogue(player: Player? = null) : DialoguePlugin(player) {
    override fun handle(componentID: Int, buttonID: Int): Boolean {
        val questName = "The Dig Site"
        val questStage = getQuestStage(player!!, questName)
        when {
            (questStage == 0) ->
                when(stage) {
                    0 -> sendDialogue(player!!, "He seems uninterested in talking.").also { stage = END_DIALOGUE }
                }
            // Start
            (questStage == 1) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Hello. Who are you?").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "Good day to you. My name is Terry Balando, I am an expert archaeologist. I am employed by Varrock Museum to oversee all finds at this site. Anything you find must be reported to me.").also { stage++ }
                    2 -> playerl(FacialExpression.FRIENDLY, "Oh, okay. If I find anything of interest I will bring it here.").also { stage++ }
                    3 -> npcl(FacialExpression.FRIENDLY, "Can I help you at all?").also { stage++ }
                    4 -> options(
                        "I have something I need checking out.",
                        "No thanks.",
                        "Can you tell me anything about the digsite?",
                        "Can you tell me more about the tools an archaeologist uses?"
                    ).also { stage++ }
                    5 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "I have something I need checking out.").also { stage = 6 }
                        2 -> playerl(FacialExpression.FRIENDLY, "No thanks.").also { stage = 7 }
                        3 -> playerl(FacialExpression.FRIENDLY, "Can you tell me anything about the digsite?").also { stage = 8 }
                        4 -> playerl(FacialExpression.FRIENDLY, "Can you tell me more about the tools an archaeologist uses?").also { stage = 13 }
                    }
                    6 -> npcl(FacialExpression.FRIENDLY, "Okay, give it to me and I'll have a look for you.").also { stage = END_DIALOGUE }
                    7 -> npcl(FacialExpression.FRIENDLY, "Good, let me know if you find anything unusual.").also { stage = END_DIALOGUE }
                    8 -> npcl(FacialExpression.FRIENDLY, "Yes, indeed! I am studying the lives of the settlers.").also { stage++ }
                    9 -> npcl(FacialExpression.FRIENDLY, "During the end of the Third Age, there used to be a great city at the site. Its inhabitants were humans, supporters of the god Saradomin.").also { stage++ }
                    10 -> npcl(FacialExpression.FRIENDLY, "It's not recorded what happened to the community here. I suspect nobody has lived here for over a millennium!").also { stage++ }
                    11 -> options(
                        "Can you tell me more about the tools an archaeologist uses?",
                        "Thank you!"
                    ).also { stage++ }
                    12 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "Can you tell me more about the tools an archaeologist uses?").also { stage = 13 }
                        2 -> playerl(FacialExpression.FRIENDLY, "Thank you!").also { stage = END_DIALOGUE }
                    }
                    13 -> npcl(FacialExpression.FRIENDLY, "Of course! Let's see now...").also { stage++ }
                    14 -> npcl(FacialExpression.FRIENDLY, "Trowels are vital for fine digging work, so you can be careful to not damage or disturb any artefacts. Rock picks are for splitting rocks or scraping away soil.").also { stage++ }
                    15 -> playerl(FacialExpression.FRIENDLY, "What about specimen jars and brushes?").also { stage++ }
                    16 -> npcl(FacialExpression.FRIENDLY, "Those are essential for carefully cleaning and storing smaller samples.").also { stage++ }
                    17 -> player("Where can I get any of these things?").also { stage++ }
                    18 -> npcl(FacialExpression.FRIENDLY, "Well, we've come into a bit more funding of late, so there should be a stock of each of them in the Exam Centre's tools cupboard. We also hand out relevant tools as students complete each level of their Earth Sciences exams.").also { stage++ }
                    19 -> playerl(FacialExpression.FRIENDLY, "Ah, okay, thanks.").also { stage = END_DIALOGUE }
                }
            }

            // Using an empty panning tray on Archeological Expert
            (questStage == 2) -> {
                when (stage) {
                    0 -> npcl(FacialExpression.FRIENDLY, "I have no need for panning trays!").also { stage = END_DIALOGUE }
                }
            }

            // Using an full panning tray on Archeological Expert
            (questStage == 3) -> {
                when (stage) {
                    0 -> npcl(FacialExpression.FRIENDLY, "Have you searched this tray yet?").also { stage++ }
                    1 -> playerl(FacialExpression.FRIENDLY, "Not that I remember...").also { stage++ }
                    2 -> npcl(FacialExpression.FRIENDLY, "Have you searched this tray yet?").also { stage = END_DIALOGUE }
                }
            }

            // Using the special cup on Archeological Expert
            (questStage == 4) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Have a look at this.").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "The expert cleans off the mud.").also { stage++ }
                    2 -> npcl(FacialExpression.FRIENDLY, "Looks like an award cup for some small find. Perhaps it belongs to one of the students?").also {
                        stage = END_DIALOGUE
                    }
                }
            }

            // Using the strange talisman on Archeological Expert
            (questStage == 5) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Take a look at this talisman.").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "Unusual... This object doesn't appear right...").also { stage++ }
                    2 -> npcl(FacialExpression.FRIENDLY, "Hmmm...").also { stage++ }
                    3 -> npcl(FacialExpression.FRIENDLY, "I wonder... Let me check my guide... Could it be? Surely not!").also { stage++ }
                    4 -> npcl(FacialExpression.FRIENDLY, "From the markings on it, it seems to be a ceremonial ornament to a god named...").also { stage++ }
                    5 -> npcl(FacialExpression.FRIENDLY, "...Zaros? I haven't heard much about him before. This is a great discovery; we know very little of the ancient gods that people worshipped.").also { stage++ }
                    6 -> npcl(FacialExpression.FRIENDLY, "There is some strange writing embossed upon it. It says: 'Zaros will return and wreak his vengeance upon Zamorak the betrayer.'").also { stage++ }
                    7 -> npcl(FacialExpression.FRIENDLY, "I wonder what it means by that. Some silly superstition, probably.").also { stage++ }
                    8 -> npcl(FacialExpression.FRIENDLY, "Still, I wonder what this is doing around here. I'll tell you what - seeing how you were the one to find this, I'll allow you to use the private dig shafts.").also { stage++ }
                    9 -> npcl(FacialExpression.FRIENDLY, "You obviously have a keen eye. Take this letter and give it to one of the winch operators at the north end of the dig, and they will allow you to use them.").also {
                        stage = END_DIALOGUE
                    }
                }
            }

            // Speaking to the Archaeological Expert after using the strange talisman on him WITH the invitation letter in inventory
            (questStage == 6) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Hello. Who are you?").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "Good day to you. My name is Terry Balando, I am an expert archaeologist. I am employed by Varrock Museum to oversee all finds at this site. Anything you find must be reported to me.").also { stage++ }
                    2 -> playerl(FacialExpression.FRIENDLY, "Oh, okay. If I find anything of interest I will bring it here.").also { stage++ }
                    3 -> npcl(FacialExpression.FRIENDLY, "Can I help you at all?").also { stage++ }
                    4 -> options(
                        "I have something I need checking out.",
                        "No thanks.",
                        "Can you tell me anything about the digsite?",
                        "Can you tell me more about the tools an archaeologist uses?"
                    ).also { stage++ }
                    5 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "I have something I need checking out.").also { stage = 6 }
                        2 -> playerl(FacialExpression.FRIENDLY, "No thanks.").also { stage = 7 }
                        3 -> playerl(FacialExpression.FRIENDLY, "Can you tell me anything about the digsite?").also { stage = 8 }
                        4 -> playerl(FacialExpression.FRIENDLY, "I lost the letter you gave me.").also { stage = 20 }
                        5 -> playerl(FacialExpression.FRIENDLY, "Can you tell me more about the tools an archaeologist uses?").also { stage = 13 }
                    }
                    6 -> npcl(FacialExpression.FRIENDLY, "Okay, give it to me and I'll have a look for you.").also { stage = END_DIALOGUE }
                    7 -> npcl(FacialExpression.FRIENDLY, "Good, let me know if you find anything unusual.").also { stage = END_DIALOGUE }
                    8 -> npcl(FacialExpression.FRIENDLY, "Yes, indeed! I am studying the lives of the settlers.").also { stage++ }
                    9 -> npcl(FacialExpression.FRIENDLY, "During the end of the Third Age, there used to be a great city at the site. Its inhabitants were humans, supporters of the god Saradomin.").also { stage++ }
                    10 -> npcl(FacialExpression.FRIENDLY, "It's not recorded what happened to the community here. I suspect nobody has lived here for over a millennium!").also { stage++ }
                    11 -> options(
                        "Can you tell me more about the tools an archaeologist uses?",
                        "Thank you!"
                    ).also { stage++ }
                    12 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "Can you tell me more about the tools an archaeologist uses?").also { stage = 13 }
                        2 -> playerl(FacialExpression.FRIENDLY, "Thank you!").also { stage = END_DIALOGUE }
                    }
                    13 -> npcl(FacialExpression.FRIENDLY, "Of course! Let's see now...").also { stage++ }
                    14 -> npcl(FacialExpression.FRIENDLY, "Trowels are vital for fine digging work, so you can be careful to not damage or disturb any artefacts. Rock picks are for splitting rocks or scraping away soil.").also { stage++ }
                    15 -> playerl(FacialExpression.FRIENDLY, "What about specimen jars and brushes?").also { stage++ }
                    16 -> npcl(FacialExpression.FRIENDLY, "Those are essential for carefully cleaning and storing smaller samples.").also { stage++ }
                    17 -> player("Where can I get any of these things?").also { stage++ }
                    18 -> npcl(FacialExpression.FRIENDLY, "Well, we've come into a bit more funding of late, so there should be a stock of each of them in the Exam Centre's tools cupboard. We also hand out relevant tools as students complete each level of their Earth Sciences exams.").also { stage++ }
                    19 -> playerl(FacialExpression.FRIENDLY, "Ah, okay, thanks.").also { stage = END_DIALOGUE }
                    20 -> npcl(FacialExpression.FRIENDLY, "No you haven't! You have one in your backpack!").also { stage = END_DIALOGUE }
                }
            }

            // Speaking to the Archaeological Expert after using the strange talisman on him WITHOUT the invitation letter
            (questStage == 7) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Hello. Who are you?").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "Good day to you. My name is Terry Balando, I am an expert archaeologist. I am employed by Varrock Museum to oversee all finds at this site. Anything you find must be reported to me.").also { stage++ }
                    2 -> playerl(FacialExpression.FRIENDLY, "Oh, okay. If I find anything of interest I will bring it here.").also { stage++ }
                    3 -> npcl(FacialExpression.FRIENDLY, "Can I help you at all?").also { stage++ }
                    4 -> options(
                        "I have something I need checking out.",
                        "No thanks.",
                        "Can you tell me anything about the digsite?",
                        "Can you tell me more about the tools an archaeologist uses?"
                    ).also { stage++ }
                    5 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "I have something I need checking out.").also { stage = 6 }
                        2 -> playerl(FacialExpression.FRIENDLY, "No thanks.").also { stage = 7 }
                        3 -> playerl(FacialExpression.FRIENDLY, "Can you tell me anything about the digsite?").also { stage = 8 }
                        4 -> playerl(FacialExpression.FRIENDLY, "I lost the letter you gave me.").also { stage = 20 }
                        5 -> playerl(FacialExpression.FRIENDLY, "Can you tell me more about the tools an archaeologist uses?").also { stage = 13 }
                    }
                    6 -> npcl(FacialExpression.FRIENDLY, "Okay, give it to me and I'll have a look for you.").also { stage = END_DIALOGUE }
                    7 -> npcl(FacialExpression.FRIENDLY, "Good, let me know if you find anything unusual.").also { stage = END_DIALOGUE }
                    8 -> npcl(FacialExpression.FRIENDLY, "Yes, indeed! I am studying the lives of the settlers.").also { stage++ }
                    9 -> npcl(FacialExpression.FRIENDLY, "During the end of the Third Age, there used to be a great city at the site. Its inhabitants were humans, supporters of the god Saradomin.").also { stage++ }
                    10 -> npcl(FacialExpression.FRIENDLY, "It's not recorded what happened to the community here. I suspect nobody has lived here for over a millennium!").also { stage++ }
                    11 -> options(
                        "Can you tell me more about the tools an archaeologist uses?",
                        "Thank you!"
                    ).also { stage++ }
                    12 -> when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "Can you tell me more about the tools an archaeologist uses?").also { stage = 13 }
                        2 -> playerl(FacialExpression.FRIENDLY, "Thank you!").also { stage = END_DIALOGUE }
                    }
                    13 -> npcl(FacialExpression.FRIENDLY, "Of course! Let's see now...").also { stage++ }
                    14 -> npcl(FacialExpression.FRIENDLY, "Trowels are vital for fine digging work, so you can be careful to not damage or disturb any artefacts. Rock picks are for splitting rocks or scraping away soil.").also { stage++ }
                    15 -> playerl(FacialExpression.FRIENDLY, "What about specimen jars and brushes?").also { stage++ }
                    16 -> npcl(FacialExpression.FRIENDLY, "Those are essential for carefully cleaning and storing smaller samples.").also { stage++ }
                    17 -> player("Where can I get any of these things?").also { stage++ }
                    18 -> npcl(FacialExpression.FRIENDLY, "Well, we've come into a bit more funding of late, so there should be a stock of each of them in the Exam Centre's tools cupboard. We also hand out relevant tools as students complete each level of their Earth Sciences exams.").also { stage++ }
                    19 -> playerl(FacialExpression.FRIENDLY, "Ah, okay, thanks.").also { stage = END_DIALOGUE }
                    20 -> npcl(FacialExpression.FRIENDLY, "I can't believe it! I go to all the effort of writing you a letter of recommendation and you lose it! Here, I'll write another... Don't lose it again!").also {
                        stage = END_DIALOGUE
                    }
                }
            }

            // Using unidentified liquid vial on Archeological Expert
            (questStage == 8) -> {
                when (stage) {
                    0 -> npcl(FacialExpression.FRIENDLY, "This is a VERY dangerous liquid called nitroglycerin. Be careful how you handle it. Don't drop it or it will explode!").also {
                        stage = END_DIALOGUE
                    }
                }
            }

            // Using powder from digsite chest on Archeological Expert
            (questStage == 9) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Have a look at this.").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "Really, you do find the most unusual items. I know what this is - it's a strong chemical called ammonium nitrate. Why you want this I'll never know...").also {
                        stage = END_DIALOGUE
                    }
                }
            }

            // Using a needle on Archeological Expert
            (questStage == 10) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "I found a needle.").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "Hmm, yes; I wondered why this race were so well dressed! It looks like they had mastery of needlework.").also {
                        stage = END_DIALOGUE
                    }
                }
            }

            // Using a rotten apple on Archeological Expert
            (questStage == 11) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "I found these...").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "Ew! Throw them away this instant!").also { stage = END_DIALOGUE }
                }
            }

            // Using broken glass on Archeological Expert
            (questStage == 12) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Have a look at this glass.").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "Hey you should be careful of that. It might cut your fingers, throw it away!").also {
                        stage = END_DIALOGUE
                    }
                }
            }

            // Using a broken arrow on Archeological Expert
            (questStage == 13) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Have a look at this arrow.").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "No doubt this arrow was shot by a strong warrior – it's split in half! It is not a valuable object though.").also {
                        stage = END_DIALOGUE
                    }
                }
            }

            // Using bones on Archeological Expert
            (questStage == 14) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Have a look at these bones.").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "Ah, yes – a fine bone example... no noticeable fractures... and in good condition. These are common cow bones, however; they have no archaeological value.").also {
                        stage = END_DIALOGUE
                    }
                }
            }

            // Using buttons on Archeological Expert
            (questStage == 15) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "I found these buttons.").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "Let's have a look. Ah, I think these are from the nobility, perhaps a royal servant?").also {
                        stage = END_DIALOGUE
                    }
                }
            }

            // Using a cracked sample on Archeological Expert
            (questStage == 16) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "I found this rock...").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "What a shame it's cracked; this looks like it would have been a good sample.").also {
                        stage = END_DIALOGUE
                    }
                }
            }

            // Using an old tooth on Archeological Expert
            (questStage == 17) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Hey look at this.").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "Oh, an old tooth. It looks like it has come from a mighty being. Pity there are no tooth fairies around here!").also {
                        stage = END_DIALOGUE
                    }
                }
            }

            // Using a rusty sword on Archeological Expert
            (questStage == 18) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "I found an old sword.").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "Oh, it's very rusty isn't it? I'm not sure this sword belongs here, it looks very out of place.").also {
                        stage = END_DIALOGUE
                    }
                }
            }

            //Using a broken staff on Archeological Expert
            (questStage == 19) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Have a look at this staff.").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "Look at this... Interesting... This appears to belong to a cleric of some kind; certainly not a follower of Saradomin, however.").also { stage++ }
                    2 -> npcl(FacialExpression.FRIENDLY, "I wonder if there was another civilization here before the Saradominists?").also {
                        stage = END_DIALOGUE
                    }
                }
            }

            // Using broken armour on Archeological Expert
            (questStage == 20) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "I found some armour.").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "It looks like the wearer of this fought a mighty battle.").also { stage = END_DIALOGUE }
                }
            }

            // Using danaged armour on Archeological Expert
            (questStage == 21) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "I found some old armour.").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "How unusual. This armour doesn't seem to match with the other finds. Keep looking.").also {
                        stage = END_DIALOGUE
                    }
                }
            }

            // Using ceramic remains on Archeological Expert
            (questStage == 22) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "I found some pottery pieces.").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "Yes, many parts are discovered. The inhabitants of these parts were great potters.").also { stage++ }
                    2 -> playerl(FacialExpression.FRIENDLY, "You mean they were good at using potions?").also { stage++ }
                    3 -> npcl(FacialExpression.FRIENDLY, "No, no, silly. They were known for their skill with clay.").also { stage = END_DIALOGUE }
                }
            }

            // Using a belt buckle on Archeological Expert
            (questStage == 23) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Have a look at this unusual item...").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "Let me see. This is a belt buckle. Not so unusual - I should imagine it came from a guard.").also {
                        stage = END_DIALOGUE
                    }
                }
            }

            // Using an animal skull on Archeological Expert
            (questStage == 24) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Have a look at this.").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "Hmm, an interesting find; an animal skull for sure. Another student found one just like this today.").also {
                        stage = END_DIALOGUE
                    }
                }

            }

            // Using the teddy bear on Archeological Expert
            (questStage == 25) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Have a look at this.").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "Why, it looks like a teddy bear to me. Perhaps someone's lucky mascot!").also {
                        stage = END_DIALOGUE
                    }
                }
            }

            // Using gold nuggets on Archeological Expert when the player has less than 3 nuggets
            (questStage == 26) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "I have these gold nuggets...").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "I can't do much with these nuggets yet. Come back when you have 3 and I will exchange them for you.").also {
                        stage = END_DIALOGUE
                    }
                }
            }

            // Using gold nuggets on Archeological Expert when the player has 3 or more nuggets
            (questStage == 27) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "I have these gold nuggets...").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "Good – that's three; I can exchange them for normal gold now. You can get this refined and make a profit!").also { stage++ }
                    2 -> player("Excellent!").also { stage = END_DIALOGUE }
                }
            }

            // Speaking to Archaeological Expert with the stone tablet in inventory
            (questStage == 28) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "I found this in a hidden cavern beneath the site.").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "Incredible!").also { stage++ }
                    2 -> playerl(FacialExpression.FRIENDLY, "There is an altar down there. The place is crawling with skeletons!").also { stage++ }
                    3 -> npcl(FacialExpression.FRIENDLY, "Yuck!").also { stage++ }
                    4 -> npcl(FacialExpression.FRIENDLY, "This is an amazing discovery! All this while we were convinced that no other race had lived here.").also { stage++ }
                    5 -> npcl(FacialExpression.FRIENDLY, "It seems the followers of Saradomin have tried to cover up the evidence of this Zaros altar. This whole city must have been built over it!").also { stage++ }
                    6 -> npcl(FacialExpression.FRIENDLY, "Thanks for your help; your sharp eyes have spotted what many have missed. Here, take this gold as your reward.").also { stage++ }
                    7 -> npcl(FacialExpression.FRIENDLY, "The expert gives you two gold bars as payment.").also { stage = END_DIALOGUE }
                }
            }

            // Speaking to Archaeological Expert after quest complete
            (questStage == 100) -> {
                when (stage) {
                    0 -> npcl(FacialExpression.FRIENDLY, "Hello again. I am now studying this mysterious altar and its inhabitants. The markings are strange...").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "It refers to a god I have never heard of before, named Zaros. It must be some pagan superstition.").also { stage++ }
                    2 -> npcl(FacialExpression.FRIENDLY, "That was a great find; who knows what other secrets lie buried beneath the surface of our land...").also {
                        stage = END_DIALOGUE
                    }
                }
            }

            // Using the stone tablet on the Archaelogical Expert after quest completion
            (questStage == 99) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Have a look at this.").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "I don't need another tablet! One is enough, thank you.").also { stage = END_DIALOGUE }
                }
            }

            // Speaking to Archaeological Expert after quest complete while holding an Ancient Staff OR using an Ancient Staff on him
            (questStage == 98) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "What do you think of this?").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "That staff is incredible! It's the same symbol that was on that talisman you found here. Does this mean you've found out more about Zaros?").also { stage++ }
                    2 -> playerl(FacialExpression.FRIENDLY, "I found out that he was banished, and that the people's hero was trapped in a pyramid and...").also { stage++ }
                    3 -> npcl(FacialExpression.FRIENDLY, "So you're the one who found out about that. I've heard the story from my friends in the museum. Well done on being able to wield such an impressive symbol.").also { stage++ }
                    4 -> npcl(FacialExpression.FRIENDLY, "Anyway....").also { stage++ }
                    5 -> npcl(FacialExpression.FRIENDLY, "Hello again. I am now studying this mysterious altar and its inhabitants. The markings are strange...").also { stage++ }
                    6 -> npcl(FacialExpression.FRIENDLY, "It refers to a god I have never heard of before, named Zaros. It must be some pagan superstition.").also { stage++ }
                    7 -> npcl(FacialExpression.FRIENDLY, "That was a great find; who knows what other secrets lie buried beneath the surface of our land...").also {
                        stage = END_DIALOGUE
                    }
                }
            }

            // Using any other item not listed here on the Archaeological Expert
            (questStage == 97) -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY, "Have a look at this.").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY, "I don't think that has any archaeological significance.").also { stage = END_DIALOGUE }
                }
            }
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.ARCHAEOLOGICAL_EXPERT_619)
    }
}