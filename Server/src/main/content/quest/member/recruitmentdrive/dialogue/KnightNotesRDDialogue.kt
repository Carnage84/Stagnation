package content.quest.member.recruitmentdrive.dialogue

import config.NPCs
import core.game.dialogue.DialogueFile
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.tools.END_DIALOGUE

/**
 * Represents the Knight Notes dialogue file for Recruitment Drive quest.
 */
class KnightNotesRDDialogue : DialogueFile() {
    override fun handle(componentID: Int, buttonID: Int) {

        npc = NPC(NPCs.SIR_TIFFY_CASHIEN_2290)

        when (stage) {
            0 -> playerl(FacialExpression.FRIENDLY, "Sir Tiffy! I got this scroll from a knight near Trollheim. He said I should deliver it to you urgently.").also { stage = 1 }
            1 -> npcl(FacialExpression.FRIENDLY, "What ho, old " + (if (player!!.isMale) "boy" else "gal") + ". Well, hand it over and let's have a look, shall we?").also { stage = 2 }
            2 -> npcl(FacialExpression.FRIENDLY, "Now, let's see what is so urgent. Hmmm...").also { stage = 3 }
            3 -> npcl(FacialExpression.FRIENDLY, "Well, you've been a great help, so I'll tell you a bit about this, shall I? Several weeks ago, a dwarven explorer, named Nestor Peregrine,").also { stage = 4 }
            4 -> npcl(FacialExpression.FRIENDLY, "discovered the entrance to an ancient cave complex while exploring the troll mountains. On his return to Keldagrim, he celebrated in a tavern where we have an informant.").also { stage = 5 }
            5 -> npcl(FacialExpression.FRIENDLY, "The dwarf spoke of a great treasure, apparently mentioned in dwarven legend, and of creatures of great evil, fighting those of great good.").also { stage = 6 }
            6 -> npcl(FacialExpression.FRIENDLY, "I sent Sir Gerry and a small command of knights to investigate the dwarf's stories several months ago and it appears that he has found the resting place of that incredible weapon:").also { stage = 7 }
            7 -> npcl(FacialExpression.FRIENDLY, "the Godsword.").also { stage = 8 }
            8 -> playerl(FacialExpression.FRIENDLY, "The Godsword? What's that?").also { stage = 9 }
            9 -> npcl(FacialExpression.FRIENDLY, "During the great God Wars, the evil usurper Zamorak gathered many ancient relics and armies to his cause.").also { stage = 10 }
            10 -> npcl(FacialExpression.FRIENDLY, "There were none powerful enough to stand alone before his might, so our glorious Lord Saradomin").also { stage = 11 }
            11 -> npcl(FacialExpression.FRIENDLY, "gathered a triumverate *sic* of gods to stem the tide of evil. They came together and, using great magics, they created the Godsword; a weapon so mighty that it could slay even a god.").also { stage = 12 }
            12 -> npcl(FacialExpression.FRIENDLY, "It was forged in the flames of a volcano and tempered by the tears of the icyene. The mightiest warriors to stand before the army of evil were the aviantese: birdmen followers of Armadyl.").also { stage = 13 }
            13 -> npcl(FacialExpression.FRIENDLY, "They were a proud and mighty warrior race. To them fell the task of delivering the Godsword to the holy armies that gathered to oppose Zamorak's evil.").also { stage = 14 }
            14 -> npcl(FacialExpression.FRIENDLY, "Unfortunately, they were attacked by an army of demons before they could reach the safety of the main army. It was a fiendish stroke by the forces of darkness.").also { stage = 15 }
            15 -> npcl(FacialExpression.FRIENDLY, "At the same time as they attacked the aviantese, they also mounted a huge attack on the main armies that opposed them. Realising their intent, several of the gods that were allied against").also { stage = 16 }
            16 -> npcl(FacialExpression.FRIENDLY, "Zamorak sent forces to aid the besieged aviantese, who had retreated into a temple in the mountains.").also { stage = 17 }
            17 -> npcl(FacialExpression.FRIENDLY, "The forces of Saradomin and Bandos arrived in the midst of a pitched battle for the Godsword, and it was during this battle that the sword was sundered.").also { stage = 18 }
            18 -> npcl(FacialExpression.FRIENDLY, "Shortly thereafter, the evil one cast a spell of such devastation that it flattened the entire northern half of Gielinor, reducing it to what we now call the Wilderness.").also { stage = 19 }
            19 -> playerl(FacialExpression.FRIENDLY, "This temple must have collapsed, sealing the combatants in.").also { stage = 20 }
            20 -> playerl(FacialExpression.FRIENDLY, "Surely, one of the groups would have gained the upper hand by now? After all, it's been thousands of years since that battle.").also { stage = 21 }
            21 -> npcl(FacialExpression.FRIENDLY, "Apparently not. It appears that a millennia-old battle is still being fought beneath the ruins of the temple, and the prize for winning the battle is the Godsword!").also { stage = 22 }
            22 -> npcl(FacialExpression.FRIENDLY, "Hmm... Well, thank you very much, young " + (if (player!!.isMale) "boy" else "gal") + ".").also { stage = 23 }
            23 -> npcl(FacialExpression.FRIENDLY, "You've been a great help. I shall pass it on to my superiors.").also { stage = 24 }
            24 -> playerl(FacialExpression.FRIENDLY, "Is that it, then? Aren't we going to do something?").also { stage = 25 }
            25 -> npcl(FacialExpression.FRIENDLY, "Yes, yes. All in good time. I'll have to consult my commanding officer first. Thank you " + (if (player!!.isMale) "boy" else "gal") + ", you're dismissed.").also { stage = END_DIALOGUE }
        }
    }

    class BrokenKnightNotes : DialogueFile() {
        override fun handle(componentID: Int, buttonID: Int) {
            when (stage) {
                0 -> npcl(FacialExpression.FRIENDLY, "Now, let's see what is so urgent. Hmm... I see that this scroll has been tampered with! The wax seal is broken.").also { stage = 1 }
                1 -> playerl(FacialExpression.FRIENDLY, "Err, yes. It was that way when I got it.").also { stage = 2 }
                2 -> npcl(FacialExpression.FRIENDLY, "I see. Anyway, let's see what it says.").also { stage = 3 }
                3 -> npcl(FacialExpression.FRIENDLY, "Oh dear. Well, thank you very much, young " + (if (player!!.isMale) "boy" else "gal") + ". You've been a great help. I shall pass it on to my superiors.").also { stage = 4 }
                4 -> playerl(FacialExpression.FRIENDLY, "Is that it then? Aren't we going to do something?").also { stage = 5 }
                5 -> npcl(FacialExpression.FRIENDLY, "Yes, yes. All in good time. I'll have to consult my commanding officer first. Thank you " + (if (player!!.isMale) "boy" else "gal") + ", you're dismissed.").also { stage = END_DIALOGUE }
            }
        }
    }
}
