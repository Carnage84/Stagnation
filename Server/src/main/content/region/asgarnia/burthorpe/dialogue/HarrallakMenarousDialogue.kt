package content.region.asgarnia.burthorpe.dialogue

import config.NPCs
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * Represents the Harrallak Menarous dialogue plugin.
 */
@Initializable
class HarrallakMenarousDialogue(player: Player? = null) : DialoguePlugin(player) {
    override fun open(vararg args: Any): Boolean {
        npc = args[0] as NPC
        npc.isWalks = true
        npcl(FacialExpression.HALF_GUILTY, "Welcome to my humble guild, " + player.username + ".").also { stage = 0 }
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            0 -> options("Quite a place you've got here.", "You any good with a sword?", "Bye!").also { stage++ }
            1 -> when (buttonId) {
                1 -> playerl(FacialExpression.HALF_GUILTY, "Quite a place you've got here. Tell me more about it.").also { stage = 53 }
                2 -> playerl(FacialExpression.HALF_GUILTY, "You any good with a sword?").also { stage = 5 }
                3 -> playerl(FacialExpression.HALF_GUILTY, "Bye!").also { stage++ }
            }
            2 -> npcl(FacialExpression.HALF_GUILTY,"Farewell, brave warrior, I do hope you enjoy my guild.").also { stage = 120 }
            3 -> playerl(FacialExpression.HALF_GUILTY,"You any good with a sword?").also { stage++ }
            4 -> npc(FacialExpression.HALF_GUILTY,"Am I any good with a sword'? Have you any clue who I", "am?").also { stage++ }
            5 -> playerl(FacialExpression.HALF_GUILTY, "Not really, no.").also { stage++ }
            6 -> npcl(FacialExpression.HALF_GUILTY, "Why, I could best any person alive in a rapier duel!").also { stage++ }
            7 -> playerl(FacialExpression.HALF_GUILTY, "Try me, then!").also { stage++ }
            8 -> npc(FacialExpression.HALF_GUILTY,"My dear man, I couldn't possibly duel you, I might hurt", "you and then what would happen to my reputation!", "Besides, I have this wonderful guild to run. Why don't", " you take a look at the various activities we have.").also { stage++ }
            9 -> npc(FacialExpression.HALF_GUILTY,"You might even collect enough tokens to be allowed in", "to kill the strange beasts from the east!").also { stage++ }
            10 -> options("Tell me about the Strength training Area.", "Tell me about the Attack training area.", "Tell me about the Defence training area.", "Tell me about the Combat training area.", "Tell me about tokens.").also { stage++ }
            11 -> when (buttonId) {
                1 -> playerl(FacialExpression.HALF_GUILTY, "Tell me about the Strength training area.").also { stage = 12 }
                2 -> playerl(FacialExpression.HALF_GUILTY, "Tell me about the Attack training area.").also { stage = 29 }
                3 -> playerl(FacialExpression.HALF_GUILTY, "Tell me about the Defence training area").also { stage = 16 }
                4 -> playerl(FacialExpression.HALF_GUILTY, "Tell me about the Combat training area").also { stage = 35 }
                5 -> playerl(FacialExpression.HALF_GUILTY, "Tell me about tokens.").also { stage = 42 }
            }
            12 -> npc(FacialExpression.HALF_GUILTY, "Ahh, the mighty warrior, Sloane, guards the Strength", "training area. This intriguing little area consits of two", "shotput lanes for different weights of shot. It's fairly", "simple, the referee or Sloane can explain more. There's").also { stage++ }
            13 -> npc(FacialExpression.HALF_GUILTY, "also the store room next door where Jimmy might share", "his talents with you, but don't tell him that I know", "he's not on guard duty!").also { stage++ }
            14 -> playerl(FacialExpression.HALF_GUILTY, "Oh? Why?").also { stage = 15 }
            15 -> npc(FacialExpression.HALF_GUILTY, "Well, he's doing no harm and let's face it, with all these", "warriors around, the guild is hardly unguarded. You can", "find the strength area just up the stairs behind the bank.").also { stage = 10 }
            16 -> npc(FacialExpression.HALF_GUILTY, "To polish your defensive skills to the very highest level,", "we've employed a most intentive dwarf and a catapult.").also { stage++ }
            17 -> playerl(FacialExpression.HALF_GUILTY, "You're going to throw dwarves at me?").also { stage++ }
            18 -> npc(FacialExpression.HALF_GUILTY, "Oh my, no! I think Gamfred would object to that most", "strongly.").also { stage++ }
            19 -> npc(FacialExpression.HALF_GUILTY, "He's an inventor, you see, and has built a marvellous", "contraptiont hat can throw all sorts of things at you.", "Things such as magic missiles...").also { stage++ }
            20 -> playerl(FacialExpression.HALF_GUILTY, "Mmmm?").also { stage++ }
            21 -> npc(FacialExpression.HALF_GUILTY, "...spiked iron balls...").also { stage++ }
            22 -> playerl(FacialExpression.HALF_GUILTY, "Er...").also { stage++ }
            23 -> npc(FacialExpression.HALF_GUILTY, "...spinning, slashing bladed...").also { stage++ }
            24 -> playerl(FacialExpression.HALF_GUILTY, "Ummm...").also { stage++ }
            25 -> npc(FacialExpression.HALF_GUILTY, "...and anvils.").also { stage = 27 }
            27 -> playerl(FacialExpression.HALF_GUILTY, "ANVILS?").also { stage++ }
            28 -> npc(FacialExpression.HALF_GUILTY, "No need to be afraid, it's all under very controlled", "conditions! You can find it just up the stairs and", "behind the bank.").also { stage = 10 }
            29 -> npc(FacialExpression.HALF_GUILTY, "Ahhh, dummies.").also { stage++ }
            30 -> playerl(FacialExpression.HALF_GUILTY, "I'm no dummy, I just want to know what is there!").also { stage++ }
            31 -> npc(FacialExpression.HALF_GUILTY, "Oh no, my dear man, I did not mean you at all! The", "training area has mechanical dummies that pop up out of", "holes in the floor. The noble dward, Gamfred, invented the", "mechanism and Ajjat can explain more about what to do").also { stage++ }
            32 -> npc(FacialExpression.HALF_GUILTY, "there.").also { stage++ }
            33 -> playerl(FacialExpression.HALF_GUILTY, "Oh, okay, I'll have to try it out.").also { stage++ }
            34 -> npc(FacialExpression.HALF_GUILTY, "You can find it just down the corridor and on", "your right.").also { stage = 10 }
            35 -> npc(FacialExpression.HALF_GUILTY, "Ahh, yes, our redient magician from foreign lands", "created a most amazing gadget that can turn your own", "armour against you! It's really quite intriguing.").also { stage++ }
            36 -> player(FacialExpression.HALF_GUILTY, "That sounds dangerous. What if I'm wearing it at the", "time?").also { stage++ }
            37 -> npc(FacialExpression.HALF_GUILTY, "So far, that's not happend. You need to speak to", "Shanomi about the specifics of the process, but as I", "understand it, putting a suit of armour in one of these", "devices will make it come to life somehow. The better the").also { stage++ }
            38 -> npc(FacialExpression.HALF_GUILTY, "armour, the harder it is to defeat.").also { stage++ }
            39 -> player(FacialExpression.HALF_GUILTY, "Fighting my own armour sounds weird. I could be", "killed by it...").also { stage++ }
            40 -> npc(FacialExpression.HALF_GUILTY, "Indeed, we have had a few fatalities from warriors", "overstretching themselves and not knowing their limits.", "Start small and work up, is my motto! That and go see", "Lidio for some food if you need it.").also { stage++ }
            41 -> playerl(FacialExpression.HALF_GUILTY, "Okay, thanks for the warning.").also { stage = 10 }
            42 -> npc(FacialExpression.HALF_GUILTY, "Ahh, yes! The tokens allow you to spend an amount of", "time with my 'discovery', located on the top floor of the", "guild. Now, the amount of tokens you collect from the", "five activities around the guild will dictate how").also { stage = -43 }
            -43 -> npc(FacialExpression.HALF_GUILTY,"long Kamfreena will allow in the enclosure on the very", "top floor. More tokens equals more time. There are", "also some bonuses available should you take part in all of", "the activites around the guild.").also { stage = 44 }
            43 -> npc(FacialExpression.HALF_GUILTY, "will allow in the enclosure on the very top floor. More", "tokens equals more time. There are also some bonuses", "available should you take part in all of the activites", "around the guild.").also { stage++ }
            44 -> playerl(FacialExpression.HALF_GUILTY, "Okay, okay. So, how do i earn these tokens?").also { stage++ }
            45 -> npc(FacialExpression.HALF_GUILTY, "You can earn them by simply using the traning", "exercises around the guild. The staff will enter", " your token earning into a ledger as you play.").also { stage++ }
            46 -> playerl(FacialExpression.HALF_GUILTY, "Sounds easy enough.").also { stage = 120 }
            47 -> npc(FacialExpression.HALF_GUILTY, "Should you part in all five activites around the guild", "you can choose to pay for your time on the top floor with", "tokens of all types. Should you do this then you'll find you", "spend less tokens overall and have a better chance of").also { stage++ }
            48 -> npc(FacialExpression.HALF_GUILTY, "getting the dragon defender, amongst other things.").also { stage++ }
            49 -> playerl(FacialExpression.HALF_GUILTY, "Excellent, sounds good. So, what's up on the top floor?").also { stage++ }
            50 -> npc(FacialExpression.HALF_GUILTY, "Well, wit giving too much away, they're big and mean,", "and you get to fight them for defenders. If you're really", "lucky, they'll summon a cyclossus...although that be", "unlucky. Still, if you manage to defeat him, you could win").also { stage++ }
            51 -> npc(FacialExpression.HALF_GUILTY, "his hat.").also { stage++ }
            52 -> playerl(FacialExpression.HALF_GUILTY, "Interesting...I will have to explore the top floor then!").also { stage = 10 }
            53 -> npc(FacialExpression.HALF_GUILTY, "Yes indeed. What would you like to know?").also { stage = 10 }
            120 -> end()
        }
        return true
    }
    override fun getIds(): IntArray { return intArrayOf(NPCs.RESOURCE_NPC_8267) }
}

//NPCs.RESOURCE_NPC_8267 is the correct Harrallak Menarous that animates properly
//NPCs.HARRALLAK_MENAROUS_8299 is the wrong Harrallak Menarous that is stuck in animation