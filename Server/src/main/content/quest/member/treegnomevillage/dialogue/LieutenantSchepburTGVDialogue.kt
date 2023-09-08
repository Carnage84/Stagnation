package content.quest.member.treegnomevillage.dialogue

import config.NPCs
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the King Lieutenant Schepbur dialogue plugin used in Tree Gnome Village quest.
 */
@Initializable
class LieutenantSchepburTGVDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        npcl(FacialExpression.FRIENDLY,"Move into position lads! eh? Who are you and what do you want?").also { stage++ }
        return true
    }

    override fun handle(componentID: Int, buttonID: Int): Boolean {
        when(stage) {
            0 -> npcl(FacialExpression.HALF_ASKING,"Move into position lads! eh? Who are you and what do you want?").also { stage++ }
            1 -> playerl(FacialExpression.ASKING,"Who are you then?").also { stage++ }
            2 -> npcl(FacialExpression.NEUTRAL,"Lieutenant Schepbur, commanding officer of the new Armoured Tortoise Regiment.").also { stage++ }
            3 -> playerl(FacialExpression.FRIENDLY,"There's only two tortoises here, that's hardly a regiment.").also { stage++ }
            4 -> npcl(FacialExpression.FRIENDLY,"This is just the beginning! Gnome breeders and trainers are already working to expand the number of units. Soon we'll have hundreds of these beauties, nay thousands! And they will not only carry mages and").also { stage++ }
            5 -> npcl(FacialExpression.FRIENDLY,"archers but other fiendish weapons of destruction of gnome devising. An army of giant tortoises will march upon this battlefield and rain the fire of our wrath upon all our enemies! Nothing will be able to stop us!").also { stage++ }
            6 -> playerl(FacialExpression.FRIENDLY,"Oooookayy...... I'll leave you to it then....").also { stage = END_DIALOGUE }
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.LIEUTENANT_SCHEPBUR_3817)
    }
}