package content.global.skill.member.summoning.familiar.dialogue

import config.NPCs
import core.api.openDialogue
import core.game.dialogue.DialogueFile
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE
import core.tools.RandomFunction

/**
 * Represents the Pack Yak familiar interaction dialogue.
 */
@Initializable
class PackYakDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, PackYakDialogueFile())
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.PACK_YAK_6873, NPCs.PACK_YAK_6874)
    }
}

class PackYakDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1, 2, 3))

    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.PACK_YAK_6873)
        if (randomConversation == 1) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Barroobaroooo baaaaaaaaarooo... (When I came of age, the herd pushed me out. It was always this way for an adult whose father still lived.").also { stage++ }
                1 -> npcl(FacialExpression.OLD_NORMAL, "I would have to wander across the Fremennik lands until I found a new herd to join, and I had to hope that the men whose paths I crossed wouldn't").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "decide that I might make a delicious meal. The trolls, too, were a concern, though they were easily avoided and yak-skin is thick and though.").also { stage++ }
                3 -> npcl(FacialExpression.OLD_NORMAL, "So I set out on my journey, only 8 years old for a human, but already a man in yak society.)").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "Barrrrooooo. (I'd forgotten how depressing my life is...)").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 2) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Baroo barrrooooo barooobaaaarrroooo... (When I was an adoscelent, wandering the fields and slopes of my homeland, I knew that I was destined for greatness.").also { stage++ }
                1 -> npcl(FacialExpression.OLD_NORMAL, "Though I was merely a yak, and though all yaks dream of far-off lands and grand adventures, I knew my horns to be sharper than most and my tongue more agile;").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "I was better able to cast my lowing voice across the chasms that separated me from the other yaks, and my voice became a song to the yaks that remained in herds.").also { stage++ }
                3 -> npcl(FacialExpression.OLD_NORMAL, "I, unlike all the others of my kind, was not interested in rejoining the society - in my exile I had come to love solitude and the calm sounds between the spaces").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "of silence that the moutains whispered.)").also { stage = END_DIALOGUE }
            }
        }
        if (randomConversation == 3) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Barooo, barrrooooooooo... (I was born on a miserable night in Bennath. My mother, bless her soul, died in labour and was eaten by the Fremennik").also { stage++ }
                1 -> npcl(FacialExpression.OLD_NORMAL, "who tended our herd. My father was never about, as he spent much of his time showing the adult females how much he could carry, for how long and").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "how quickly he could traverse a mountain pass. He was a foolish yak and was laughed at by the herd, though he had no inkling of that.").also { stage++ }
                3 -> npcl(FacialExpression.OLD_NORMAL, "With no obvious father to raise me, I was left very much to my own devices and relied on the generosity of others to make my way through infancy and childhood.").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "I was forced to be cunning, wise and, sometimes, ruthless.)").also { stage++ }
                5 -> npcl(FacialExpression.OLD_NORMAL, "Baroooooo. (I don't know what it is to be ruth, though. It is a silly word)").also { stage = END_DIALOGUE }
            }
        }
    }
}