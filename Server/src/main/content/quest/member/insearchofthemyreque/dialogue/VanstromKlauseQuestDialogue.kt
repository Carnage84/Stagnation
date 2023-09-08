package content.quest.member.insearchofthemyreque.dialogue

import config.NPCs
import content.quest.member.insearchofthemyreque.InSearchOfTheMyreque
import core.api.getQuestStage
import core.api.setQuestStage
import core.game.dialogue.DialogueFile
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the dialogue file used in "In Search Of The Myreque Quest".
 */
@Initializable
class VanstromKlauseQuestDialogue : DialogueFile() {
    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.VANSTROM_KLAUSE_1577)
        when (getQuestStage(player!!, InSearchOfTheMyreque.questName)) {
            0 -> when (stage) {
                0 -> npcl(FacialExpression.FRIENDLY, "Hmm, well, i am a little concerned about some friends of mine. They're in dire need of some assistance, but i'm at a loss as how i can help them.").also { stage++ }
                1 -> playerl(FacialExpression.FRIENDLY, "What friends are these?").also { stage++ }
                2 -> npcl(FacialExpression.FRIENDLY, "It's a personal tragedy that i have to meet them in the flesh. But their exploits make mouth watering hero stories.. the real meat and drink of high adventure and daring.. so they say.").also { stage++ }
                3 -> playerl(FacialExpression.FRIENDLY, "What does this mean exactly? I mean, i have some stories. I'm quite a hero myself, you may actually be talking of me?").also { stage++ }
                4 -> npcl(FacialExpression.FRIENDLY, "They're regarded as heroes in <col=08088A>Morytania</col>, though some people see them as vigilantes. The local villagers call them the '<col=08088A>myreque</col>'. SOme people call them terrorist while others call them freedom fighters!").also { stage++ }
                5 -> playerl(FacialExpression.FRIENDLY, "Why do they need help? Are they in trouble?").also { stage++ }
                6 -> npcl(FacialExpression.FRIENDLY, "I should imagine that heroes of such high calibre are almost always in some sort of trouble, would't you? There's always some evil heel ready to grind the face of humanity into the dirt?").also { stage++ }
                7 -> npcl(FacialExpression.FRIENDLY, "However the Myreque are almost certainly able to handle themselves.. given the tools! I hear they're are short of weapons, i was hoping to do it myself but i find that i'm rather short of time and ability!").also { stage++ }
                8 -> playerl(FacialExpression.FRIENDLY, "What help do you hope to give them?").also { stage++ }
                9 -> npcl(FacialExpression.FRIENDLY, "I'd have taken some weponss to them!").also { stage++ }
                10 -> playerl(FacialExpression.FRIENDLY, "What kind of weapons do they need?").also { stage++ }
                11 -> npcl(FacialExpression.FRIENDLY, "Steel, i belive. All six of them requrie steel weapons. I would have suggested a longsword, two swords, a dagger, a mace and a warhammer.").also { stage++ }
                12 -> playerl(FacialExpression.FRIENDLY, "I wish i could help, but i'm busy at the moment.").also { stage++ }
                13 -> npcl(FacialExpression.FRIENDLY, "Hmm, the same i ear! Oh well, i'm sure they'll get on by themselvel, the little dears. Thanks for even considering it though.").also { stage++ }
                14 -> playerl(FacialExpression.FRIENDLY, "Perhaps i could help you out here. Mabye i could take these weapons to the <col=08088A>Myreque</col>.").also { stage++ }
                15 -> npcl(FacialExpression.FRIENDLY, "Oh yes? Well, that wpuld be very nive of you! Are you sure you want to help out?").also { stage++ }
                16 -> options("Yes, i'll do it!","Sorry, i can't do it!").also { stage++ }
                17 -> when(buttonID){
                    1 -> playerl(FacialExpression.FRIENDLY, "Yes, i'll do it!").also { stage = 18 }
                    2 -> playerl(FacialExpression.FRIENDLY, "Sorry, i can't do it!").also { stage = 20 }
                }
                18 -> npcl(FacialExpression.FRIENDLY, "That's grat news, my friend, really great news! Perhaps the many pepoles of <col=08088A>Morytania</col> ow have a additional hero thtt they can come to rely upon?").also { stage++ }
                19 -> {
                    end()
                    setQuestStage(player!!, InSearchOfTheMyreque.questName, 1)
                }
                20 -> npcl(FacialExpression.FRIENDLY, "Oh. Well that is a shame, i'm sorry to hear it. But if you change your mind, please wisit me again.").also { stage = END_DIALOGUE }
            }

            1 -> when(stage) {
                1 -> playerl(FacialExpression.FRIENDLY, "Not very well. I need some help.").also { stage++ }
                2 -> npcl(FacialExpression.FRIENDLY, "Oh dear... what do you need help with?").also { stage++ }
                3 -> options("Where are the Myreque?","What weapons am I supposed to get again?", "How do you know the 'Myreque'?", "Is there anyone else who can help me?").also { stage++ }
                4 -> when(buttonID){
                    1 -> playerl(FacialExpression.FRIENDLY, "Where are the Myreque?").also { stage = 5 }
                    2 -> playerl(FacialExpression.FRIENDLY, "What weapons am I supposed to get again?").also { stage = 6 }
                    3 -> playerl(FacialExpression.FRIENDLY, "How do you know the '<col=08088A>Myreque</col>'?").also { stage = 7 }
                    4 -> playerl(FacialExpression.FRIENDLY, "Is there anyone else who can help me?").also { stage = 8 }
                }
                5 -> npcl(FacialExpression.FRIENDLY, "You know what? I'm not exactly sure. The only thing I've heard, and it is a rumor, but the boatman in Mort'ton might be able to help. But again, it is only a rumor.").also { stage = 9 }
                6 -> npcl(FacialExpression.FRIENDLY, "Well, I'm sure the following will be a fine: a <col=08088A>longsword</col>, two <col=08088A>swords</col>, a <col=08088A>dagger</col>, a <col=08088A>mace</col>, and a <col=08088A>warhammer</col>. All the items should be made of steel.").also { stage = 9 }
                7 -> npcl(FacialExpression.FRIENDLY, "Actually you know. I don't know them at all! I've never actually met them, but would love to at some point! However, I'm sure that you'll be much more able than I at tracking them down.").also { stage = 9 }
                8 -> npcl(FacialExpression.FRIENDLY, "Hmm. Apart from the boatman at Mort'ton, I have no clue, my friend!").also { stage = 9 }
                9 -> playerl(FacialExpression.FRIENDLY, "Ok, thanks.").also { stage++ }
                10 -> npcl(FacialExpression.HAPPY, "Well, if you have any problems please feel free to come and chat with me.").also { stage = END_DIALOGUE }
            }

            2 -> when(stage){
                1 -> playerl(FacialExpression.FRIENDLY, "What am I supposed to do again?").also { stage++ }
                2 -> npcl(FacialExpression.HAPPY, "You've forgotten already? Ha! That's funny! But I do understand, my memory isn't what it was!").also { stage++ }
                3 -> npcl(FacialExpression.HAPPY, "It would be great if you could get some steel weapons and take them to the <col=08088A>Myreque</col>, the boatman in <col=08088A>Mort'ton</coL> should be able to help you find them. But you may need to be a bit persuasive!").also { stage = END_DIALOGUE }
            }
        }
    }
}