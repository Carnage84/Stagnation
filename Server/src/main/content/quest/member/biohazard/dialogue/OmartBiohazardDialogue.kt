package content.quest.member.biohazard.dialogue

import core.api.getQuestStage
import core.game.dialogue.DialogueFile

/**
 * Represents the Omart dialogue file for Biohazard quest.
 */
class OmartBiohazardDialogue : DialogueFile() {
    override fun handle(componentID: Int, buttonID: Int) {
        var qStage = getQuestStage(player!!, "Biohazard")
    }
}