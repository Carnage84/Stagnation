package content.quest.member.thegrandtree.dialogue

import config.Items
import config.NPCs
import content.quest.member.thegrandtree.TheGrandTree
import core.api.*
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.player.Player
import core.game.node.item.Item
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents Hazelmere dialogue in the grand tree quest.
 */
@Initializable
class HazelmereGTDialogue(player: Player? = null) : DialoguePlugin(player){
    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (getQuestStage(player!!, TheGrandTree.questName)) {
            10 -> {
                if(player!!.hasItem(Item(Items.BARK_SAMPLE_783))){
                    when (stage) {
                        0 -> sendDialogue(player!!,"The mage starts to speak but all you hear is").also { stage++ }
                        1 -> npcl(FacialExpression.NEUTRAL,"Blah. Blah, blah, blah, blah...blah!").also { stage++ }
                        2 -> sendDialogue(player!!,"You give the bark sample to Hazelmere. The mage carefully examines the sample.").also { stage++ }
                        3 -> npcl(FacialExpression.NEUTRAL,"Blah, blah...Daconia...blah, blah.").also { stage++ }
                        4 -> playerl(FacialExpression.ASKING,"Can you write this down and I'll try and translate it?").also { stage++ }
                        5 -> npcl(FacialExpression.NEUTRAL,"Blah, blah?").also { stage++ }
                        6 -> sendDialogue(player!!,"You make a writing motion. The mages scribbles something down on a scroll. Hazelmere has given you the scroll.").also {
                            if(removeItem(player!!, Items.BARK_SAMPLE_783)){
                                addItemOrDrop(player!!, Items.HAZELMERES_SCROLL_786)
                            }
                            setQuestStage(player!!, TheGrandTree.questName, 20)
                            stage = END_DIALOGUE
                        }
                    }
                }
            }
            20 -> {
                when (stage) {
                    0 -> npcl(FacialExpression.NEUTRAL,"Blah, blah....Daconia...blah, blah.").also { stage++ }
                    1 -> sendDialogue(player!!,"You still can't understand Hazelmere. The mage wrote it down for you on a scroll.").also {
                        if(!inInventory(player!!, Items.HAZELMERES_SCROLL_786)){
                            addItemOrDrop(player!!, Items.HAZELMERES_SCROLL_786)
                        }
                        stage = END_DIALOGUE
                    }
                }
            }
            else -> npcl(FacialExpression.NEUTRAL,"Blah, blah...blah, blah.").also { stage = END_DIALOGUE }
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.HAZELMERE_669)
    }
}