package content.global.handlers.item.withnpc

import config.Items
import core.api.*
import core.game.dialogue.DialogueFile
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.node.entity.player.link.diary.DiaryType
import core.game.node.item.Item

/**
 * Listener for using cider on forester NPC.
 */
class CiderOnForester : InteractionListener {
    override fun defineListeners() {
        val ids = intArrayOf(1,2,3,4,5)

        onUseWith(IntType.NPC, Items.CIDER_5763, *ids){ player, used, with ->
            if(inBorders(player, 2689, 3488, 2700, 3498)){
                player.dialogueInterpreter.open(CiderOnForesterDialogue(),with)
                return@onUseWith true
            }
            return@onUseWith false
        }
    }
}

class CiderOnForesterDialogue : DialogueFile() {
    var init = true
    override fun handle(componentID: Int, buttonID: Int) {

        if (init) stage = 0; init = false

        when (stage) {
            0 -> sendNPCDialogue(player!!, this.npc!!.id, "Ah, a glass of cider, Don't mind if I do!").also { stage = 1 }
            1 -> {
                sendNPCDialogue(player!!, this.npc!!.id, "Thanks!")
                removeItem(player!!, Item(Items.CIDER_5763, 1))
                stage = 3
            }
            3 -> {
                if (getAttribute(player!!, "seersCiderPub", -1) == 4) {
                    player!!.achievementDiaryManager?.finishTask(player, DiaryType.SEERS_VILLAGE, 0, 6)
                    removeAttribute(player!!, "seersCiderPub")
                    end()
                } else if (getAttribute(player!!, "seersCiderPub", -1) !in 0..3 && !player!!.achievementDiaryManager.hasCompletedTask(DiaryType.SEERS_VILLAGE, 0, 6)) {
                    getAttribute(player!!, "seersCiderPub", 0)
                    end()
                } else if (getAttribute(player!!, "seersCiderPub", -1) in 0..3) {
                    player!!.incrementAttribute("seersCiderPub", 1)
                    end()
                } else {
                    end()
                }
            }
        }
    }

}