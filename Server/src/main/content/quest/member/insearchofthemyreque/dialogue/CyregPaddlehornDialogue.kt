package content.quest.member.insearchofthemyreque.dialogue

import config.NPCs
import content.quest.member.insearchofthemyreque.InSearchOfTheMyreque
import core.api.getQuestStage
import core.api.sendDialogue
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the dialogue plugin used for the Cyreg Paddlehorn npc in "In Search Of The Myreque Quest".
 */
@Initializable
class CyregPaddlehornDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        if (player.questRepository.getStage(InSearchOfTheMyreque.questName) == 1) {
            npcl(FacialExpression.FRIENDLY, "Can you tell me how to find the <col=08088A>Myreque</col>?").also { stage++ }
        } else if (player.questRepository.getStage(InSearchOfTheMyreque.questName) > 1) {
            npcl(FacialExpression.FRIENDLY, "Will you still take me to the <col=08088A>Myreque</col>?").also { stage++ }
        } else {
            playerl(FacialExpression.FRIENDLY,"Can I ask some more questions?").also { stage++ }
        }
        return true
    }
//1 -> playerl(FacialExpression.FRIENDLY, "").also { stage++ }
//1 -> npcl(FacialExpression.FRIENDLY, "").also { stage++ }
    override fun handle(componentID: Int, buttonID: Int): Boolean {
        when (getQuestStage(player!!, InSearchOfTheMyreque.questName)) {
            1 -> when (stage) {
                1 -> npcl(FacialExpression.FRIENDLY, "Their base is well hidden, and I'm sorry but I can't reveal the directions. Sorry, but I guess you're all out of luck.").also { stage++ }
                2 -> playerl(FacialExpression.FRIENDLY, "Oh come on, you can tell me!").also { stage++ }
                3 -> npcl(FacialExpression.FRIENDLY, "I'm sorry, I can't. I just can't... people are watching... eyes everywhere!").also { stage++ }
                4 -> options("Well, I guess they'll just die without weapons.","I'll give you some cash if you help me.", "I just want to help them, I think they need help.").also { stage++ }
                5 -> when(buttonID){
                    1 -> playerl(FacialExpression.FRIENDLY, "Well, I guess they'll just die without weapons.").also { stage++ }
                    2 -> playerl(FacialExpression.FRIENDLY, "I'll give you some cash if you help me.").also { stage = 30 }
                    3 -> playerl(FacialExpression.FRIENDLY, "I just want to help them, I think they need help.").also { stage = 33 }
                }
                6 -> npcl(FacialExpression.FRIENDLY, "Hmm, you don't seem too concerned about their welfare... I'm glad I didn't tell you where they were. In any case, they're resourceful. They can look after themselves.").also { stage++ }
                7 -> playerl(FacialExpression.FRIENDLY, "What's that supposed to mean?").also { stage++ }
                8 -> npcl(FacialExpression.FRIENDLY, "They're resourceful folks, that's all I'm saying. Their leader, <col=08088A>Veliaf</col>, looks after them well.").also { stage++ }
                9 -> options("Resourceful enough to get their own steel weapons?","I'll give you some cash if you help me.").also { stage++ }
                10 -> when(buttonID){
                    1 -> playerl(FacialExpression.FRIENDLY, "Resourceful enough to get their own steel weapons?").also { stage++ }
                    2 -> playerl(FacialExpression.FRIENDLY, "I'll give you some cash if you help me.").also { stage = 30 }
                }
                11 -> npcl(FacialExpression.FRIENDLY, "Maybe they are... what do you care anyway? They've been up against it ever since they got started. All of 'em have suffered more loss and heartache than you'll ever know. Now, leave me be!").also { stage++ }
                12 -> options("What have they been up against?","What kind of loss and heartache?", "If you don't tell me, their deaths will be on your head!").also { stage++ }
                13 -> when(buttonID){
                    1 -> playerl(FacialExpression.FRIENDLY, "What have they been up against?").also { stage++ }
                    2 -> playerl(FacialExpression.FRIENDLY, "What kind of loss and heartache?").also { stage = 15 }
                    3 -> playerl(FacialExpression.FRIENDLY, "If you don't tell me, their deaths will be on your head!").also { stage = 18 }

                }
                14 -> npcl(FacialExpression.FRIENDLY, "You're not from around here or you wouldn't be asking such foolish questions. <col=08088A>Morytania</col> is ruled by a cruel dark overlord by the name of Drakan. His reign over Morytania means we all live in fear.").also { stage = 12 }
                15 -> npcl(FacialExpression.FRIENDLY, "The worst kind, most have lost members of their family. Little <col=08088A>Sani Piliu</col>, she was orphaned overnight when a vampyre went on the rampage... Imagine that, losing your entire family in one night? Terrible!").also { stage++ }
                16 -> playerl(FacialExpression.FRIENDLY, "It sounds awful... Who is Sani Piliu?").also { stage++ }
                17 -> npcl(FacialExpression.FRIENDLY, "She's the only female member of the Myreque. She's already proven herself with her agility and light fingers, if you know what I mean!").also { stage = 12 }
                18 -> npcl(FacialExpression.FRIENDLY, "There's death aplenty in this forsaken place... what do I care that some foolhardy vigilantes decided to go it alone against the Drakans? Stupidity of youth is to blame, I shan't carry it on my shoulders!").also { stage++ }
                19 -> options("One mans vigilante is another mans freedom fighter!","Who are the Drakans?", "What kind of man are you to say that you don't care?", "Why do you say that this place is 'forsaken'?").also { stage++ }
                20 -> when(buttonID){
                    1 -> playerl(FacialExpression.FRIENDLY, "One mans vigilante is another mans freedom fighter!").also { stage++ }
                    2 -> playerl(FacialExpression.FRIENDLY, "Who are the Drakans?").also { stage = 24 }
                    3 -> playerl(FacialExpression.FRIENDLY, "What kind of man are you to say that you don't care?").also { stage = 26 }
                    4 -> playerl(FacialExpression.FRIENDLY,"Why do you say that this place is 'forsaken'?").also { stage = 29 }

                }
                21 -> npcl(FacialExpression.FRIENDLY, "Aye, you can see it from both sides I suppose. But many of us consider it fool hardy to fight for something we'll never get, even Polmafi, a scholar, such as he was, agrees that the chances are slim.").also { stage++ }
                22 -> playerl(FacialExpression.FRIENDLY, "Polmafi? Who's he?").also { stage++ }
                23 -> npcl(FacialExpression.FRIENDLY, "<col=08088A>Polmafi Ferdygris</col> is one of the Myreque. He's a technical sort and advises on all sorts of things to <col=08088A>Veliaf</col>. He was a scholar before he became a renegade.").also { stage = 20 }
                24 -> npcl(FacialExpression.FRIENDLY, "The Drakans are the family of overlords that rule Morytania. They're the ones to whom the blood tithes are paid. Too much I have told you already!").also { stage++ }
                25 -> npcl(FacialExpression.FRIENDLY, "Ignorance is better than these truths, I tell you! I can pretend once more that I am a free man, and some relief from this gloom can I feel again. Be gone with you now and leave me with my dreams.").also { stage = 20 }
                26 -> npcl(FacialExpression.FRIENDLY, "Don't dare to judge me, young fool... what do you know of the heartache I carry? Can you not see the anchor of woe that holds me fast?").also { stage++ }
                27 -> npcl(FacialExpression.FRIENDLY, "Very well, if you would take your chance to help these strangers, who am I to stop you?").also { stage++ }
                28 -> npcl(FacialExpression.FRIENDLY, "But will you help me? Will you take me to them?").also { stage = 19 }
                29 -> npcl(FacialExpression.FRIENDLY, "All of these lands are forsaken of <col=08088A>Saradomin's kindness</col>, only cold death from the evil gods do we now feel. Those lucky ones to the west of the <col=08088A>Salve</col> little realise their fate if the river should one day become tainted.").also { stage = END_DIALOGUE }
                30 -> npcl(FacialExpression.FRIENDLY, "You think you can buy me!").also { stage++ }
                31 -> playerl(FacialExpression.FRIENDLY, "Er, no, I just want to compensate you for your trouble!").also { stage++ }
                32 -> npcl(FacialExpression.FRIENDLY, "You keep your money and I'll keep my secrets.").also { stage = END_DIALOGUE }
                33 -> npcl(FacialExpression.FRIENDLY, "Aye, well, they may do... but it's just not safe and it's not likely to get safer any time soon. Though I do fell sorry for Ivan, the baby of the group. He's seen too few winters to be involved in such toil.").also { stage++ }
                34 -> playerl(FacialExpression.FRIENDLY, "Ok, thanks.").also { stage = END_DIALOGUE }
            }
            2 -> when (stage) {
                1 -> npcl(FacialExpression.FRIENDLY, "No, I won't take you, but you can use my boat. You'll be going through <col=08088A>Mort Myre</col>, though, so I won't be letting you go unless you've some defense against the <col=08088A>Ghasts</col>.").also { stage++ }
                // (if you don't have the Silver sickle (b) (or Ivandis flail) and a Druid pouch with at least 5 charges)
                2 -> playerl(FacialExpression.FRIENDLY, "I don't have anything which I can use against them at this time.").also { stage++ }
                // (if you have the materials)
                3 -> sendDialogue(player, "You show the boatman your druid pouch.").also { stage++ }
                4 -> playerl(FacialExpression.FRIENDLY, "I have this druid pouch! This turns the <col=08088A>Ghasts</col> visible and I can kill them once I can see them.").also { stage++ }
                // (if you do not have at least 3 wooden planks)
                5 -> npcl(FacialExpression.FRIENDLY, "Very well, but I still cannot let you pass unprepared!").also { stage++ }
                6 -> npcl(FacialExpression.FRIENDLY,"My boat needs some work, so I'll need three <col=08088A>wooden planks</col>. The bridge you'll need to cross later is rotten and may need to be mended, so you'll need three more planks as well as a hammer, and I'd say around 75 <col=08088A>nails</col>.").also { stage = END_DIALOGUE }
                // (if you have the planks)
                7 -> playerl(FacialExpression.FRIENDLY, "I also have the three wooden planks you asked for.").also { stage++ }
                8 -> sendDialogue(player, "the boatman takes 3 wooden planks from you.").also { stage++ }
                9 -> playerl(FacialExpression.FRIENDLY, "Not just yet, sorry.").also { stage = END_DIALOGUE }
            }

            3 -> when(stage){
                // Can I ask some more questions?
                1 -> npcl(FacialExpression.FRIENDLY, "Sure you can.").also { stage++ }
                2 -> options("Where do I go after I get to the hollows?","Tell me about the '<col=08088A>Myreque.</col>'", "Tell me about the <col=08088A>Drakans</col>.", "Tell me about the yourself.").also { stage++ }
                3 -> when(buttonID) {
                    1 -> playerl(FacialExpression.FRIENDLY, "Where do I go after I get to the hollows?").also { stage++ }
                    2 -> playerl(FacialExpression.FRIENDLY, "Tell me about the '<col=08088A>Myreque</col>.'").also { stage = 5 }
                    3 -> playerl(FacialExpression.FRIENDLY, "Tell me about the <col=08088A>Drakans</col>.").also { stage = 13 }
                    4 -> playerl(FacialExpression.FRIENDLY, "Tell me about the yourself.").also { stage = 14 }
                }
                4 -> npcl(FacialExpression.FRIENDLY, "You should head north and look for an unusual tree. You look like you know a thing or two about exploring so I guess I don't need to tell you to keep your eyes peeled!").also { stage = END_DIALOGUE }
                5 -> npcl(FacialExpression.FRIENDLY, "What do you want to know?").also { stage++ }
                6 -> options("Why are they call 'the Myreque'?","Tell me about the members of the Myreque").also { stage++ }
                7 -> when(buttonID) {
                    1 -> playerl(FacialExpression.FRIENDLY, "Why are they call 'the Myreque'?").also { stage++ }
                    2 -> playerl(FacialExpression.FRIENDLY, "Tell me about the members of the Myreque").also { stage = 5 }
                }
                8 -> npcl(FacialExpression.FRIENDLY, "The locals just called them after the place where they hide out, in Mort Myre. They are Myreque, 'hidden in the myre'.").also { stage = END_DIALOGUE }
                10 -> npcl(FacialExpression.FRIENDLY, "Well, you have <col=08088A>Radigad Ponfit</col>... he's your average mercenary from <col=08088A>Asgarnia</col>, ready to slice anyone's head off for a price. He's got a personal score to settle with the Drakans, though no one knows what it is.").also { stage++ }
                11 -> npcl(FacialExpression.FRIENDLY, "Then there's <col=08088A>Veliaf</col>, he's the leader. and then there's Ivan, he's the baby of the group. There's Sani Piliu, she's a lovely girl, though a bit shady if yo know what I mean.").also { stage++ }
                12 -> npcl(FacialExpression.FRIENDLY, "You've also got <col=08088A>Harold Evans</col>, he's a bit hot-headed, always straight into the fray. And the brains of the operation, reporting directly to Veliaf, is Polmafi Ferdygris, he used to be quite a clever scholar!").also { stage++ }
                13 -> npcl(FacialExpression.FRIENDLY, "Well it's rumoured that they're controlling the whole of Morytania. They live in the Sanguinesti region, but I've never been, like most villagers in Morytania, I'm afraid to leave the village and dread what lies beyond.").also { stage = END_DIALOGUE }
                14 -> npcl(FacialExpression.FRIENDLY, "I'm just a humble boatman, <col=08088A>Cyreg Paddlehorn</col> is my name. Like most of the Paddlehorns before me, I make my living by tracking the swamps of <col=08088A>Mort Myre.</col>").also { stage++ }
                15 -> playerl(FacialExpression.FRIENDLY, "Ok thanks.").also { stage = END_DIALOGUE }
            }
        }
        return true
    }
    override fun getIds(): IntArray = intArrayOf(NPCs.CYREG_PADDLEHORN_1567)
}