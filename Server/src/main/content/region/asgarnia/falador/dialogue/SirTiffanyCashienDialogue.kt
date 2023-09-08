package content.region.asgarnia.falador.dialogue

import config.NPCs
import content.quest.member.recruitmentdrive.dialogue.SirTiffyCashienRDDialogue
import core.api.getQuestStage
import core.api.isQuestComplete
import core.api.openDialogue
import core.api.openNpcShop
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * Represents the Sir Tiffany Cashien dialogue plugin.
 */
@Initializable
class SirTiffanyCashienDialogue(player: Player? = null) : DialoguePlugin(player){

    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        if (isQuestComplete(player, "Recruitment Drive") || isQuestComplete(player, "The Slug Menace")) {
            player("Hello.")
            stage = 7
        } else if (getQuestStage(player, "Recruitment Drive") >= 10) {
            end()
            openDialogue(player, SirTiffyCashienRDDialogue(), npc)
        } else {
            player("Hello.")
            stage = 1
        }
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            1 -> {
                npc("What ho, " + (if(player.isMale) "sirrah" else "milady") + ". Spiffing day for a walk in the park,", "what?")
                stage++
            }
            2 -> {
                player("Spiffing?")
                stage++
            }
            3 -> {
                npc("Absolutely, top-hole! Well, can't stay and chat all day,","dontchaknow! Ta-ta for now!")
                stage++
            }
            4 -> {
                player("Erm...goodbye.")
                stage = 100
            }
            5 -> {
                options("Do you have any jobs for me yet?", "Can you explain the Gaze of Saradomin to me?", "Can I buy some armours?", "Can I switch respawns please?", "Goodbye.")
                stage = 10
            }
            10 -> when (buttonId) {
                1 -> playerl(FacialExpression.FRIENDLY, "Do you have any jobs for me yet?").also { stage = 11 }
                2 -> playerl(FacialExpression.FRIENDLY, "I don't really understand this 'Gaze of Saradomin' thing... Do you think you could explain what it does for me?").also { stage = 13 }
                3 -> playerl(FacialExpression.FRIENDLY, "Can I buy some armours?").also { stage = 19 }
                4 -> playerl(FacialExpression.FRIENDLY, "Hi Tiffy, I was wondering, can I change my respawn point to Falador?").also { stage = 21 }
                5 -> playerl(FacialExpression.FRIENDLY, "Goodbye.").also { stage = 100 }
            }
            11 -> npcl(FacialExpression.FRIENDLY, "Sorry dear " + (if (player!!.isMale) "boy" else "gal") + " but we are still in the process of organising. I'm sure that we will have something for you soon, so please feel free to check back later.").also { stage++ }
            12 -> npcl(FacialExpression.FRIENDLY, "Anything else I can do for you in the meantime?").also { stage = 7 }
            13 -> npcl(FacialExpression.FRIENDLY, "Certainly " + (if (player!!.isMale) "sirrah" else "milady") + " As you know, we Temple Knights are personally favoured by Saradomin himself. And when I say personally favoured, I don't mean that some time in the future he's going to buy us all a drink!").also { stage++ }
            14 -> npcl(FacialExpression.FRIENDLY, "He watches over us, and, when we die, we're offered the chance to respawn in Falador Castle, ready to get on with our adventures.").also { stage++ }
            15 -> npcl(FacialExpression.FRIENDLY, "Now, this doesn't happen if we die in especially evil places like the Wilderness, but it can be a big help if you're out slaying dragons or whatever.").also { stage++ }
            16 -> npcl(FacialExpression.FRIENDLY, "Our equipment isn't protected any more than usual, but it's a small price to pay to be hale and hearty again, what?").also { stage++ }
            17 -> playerl(FacialExpression.FRIENDLY, "Thank you.").also { stage = 12 }
            18 -> npcl(FacialExpression.FRIENDLY, "Was there something else you wanted to ask good old Tiffy, " + (if (player!!.isMale) "sirrah" else "milady") + "?").also { stage = 7 }
            // Recruitment Drive + The Slug Menace [ "Of course, dear " + (if (player!!.isMale) "sirrah" else "milady") + "I can sell you up to Proselyte level items only" ]
            19 -> npcl(FacialExpression.FRIENDLY, "Of course, dear " + (if (player!!.isMale) "sirrah" else "milady") + ".").also { stage++ }
            20 -> {
                end()
                openNpcShop(player, NPCs.SIR_TIFFY_CASHIEN_2290)
            }
            // [ "Hi Tiffy, I was wondering, can I change my respawn point back to Falador?" ]
            21 -> npcl(FacialExpression.FRIENDLY, "Not yet.").also { stage = 100 }
            100 -> end()
        }
        return true
    }

    override fun newInstance(player: Player?): DialoguePlugin {
        return SirTiffanyCashienDialogue(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.SIR_TIFFY_CASHIEN_2290)
    }
}