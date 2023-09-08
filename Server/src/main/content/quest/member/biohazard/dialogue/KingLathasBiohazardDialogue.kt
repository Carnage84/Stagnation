package content.quest.member.biohazard.dialogue

import core.api.getQuestStage
import core.game.dialogue.DialogueFile

/**
 * Represents the King Lathas dialogue file for Biohazard quest.
 */
class KingLathasBiohazardDialogue : DialogueFile() {
    override fun handle(componentID: Int, buttonID: Int) {
        var qStage = getQuestStage(player!!, "Biohazard")
    }
}