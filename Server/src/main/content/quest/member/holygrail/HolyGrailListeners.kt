package content.quest.member.holygrail

import config.NPCs
import content.quest.member.holygrail.dialogue.FishermanHGDialogue
import content.quest.member.holygrail.dialogue.KingPercivalHGDialogue
import content.quest.member.holygrail.dialogue.TheFisherKingHGDialogue
import content.quest.member.holygrail.dialogue.knightsoftheroundtable.SirLancelotHGDialogue
import content.quest.member.holygrail.dialogue.knightsoftheroundtable.SirPalomedesHGDialogue
import content.quest.member.holygrail.dialogue.knightsoftheroundtable.SirPelleasHGDialogue
import content.quest.member.holygrail.dialogue.knightsoftheroundtable.SirTristamHGDialogue
import core.api.getQuestStage
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.node.entity.npc.NPC

/**
 * Listeners for the "Holy Grail" quest.
 */
class HolyGrailListeners: InteractionListener {
    override fun defineListeners() {
        on(NPCs.KING_PERCIVAL_212, IntType.NPC, "talk-to") { player, _ ->
            if (getQuestStage(player, "Holy Grail") >= 10) {
                player.dialogueInterpreter.open(KingPercivalHGDialogue(), NPC(NPCs.KING_PERCIVAL_212))
            }
            return@on true
        }

        on(NPCs.THE_FISHER_KING_220, IntType.NPC, "talk-to") { player, _ ->
            if (getQuestStage(player, "Holy Grail") >= 10) {
                player.dialogueInterpreter.open(TheFisherKingHGDialogue(), NPC(NPCs.THE_FISHER_KING_220))
            }
            return@on true
        }

        on(NPCs.FISHERMAN_219, IntType.NPC, "talk-to") { player, _ ->
            if (getQuestStage(player, "Holy Grail") >= 10) {
                player.dialogueInterpreter.open(FishermanHGDialogue(), NPC(NPCs.FISHERMAN_219))
            }
            return@on true
        }

        on(NPCs.SIR_LANCELOT_239, IntType.NPC, "talk-to") { player, _ ->
            if (getQuestStage(player, "Holy Grail") >= 10) {
                player.dialogueInterpreter.open(SirLancelotHGDialogue(), NPC(NPCs.SIR_LANCELOT_239))
            }
            return@on true
        }

        on(NPCs.SIR_PALOMEDES_3787, IntType.NPC, "talk-to") { player, _ ->
            if (getQuestStage(player, "Holy Grail") >= 10) {
                player.dialogueInterpreter.open(SirPalomedesHGDialogue(), NPC(NPCs.SIR_PALOMEDES_3787))
            }
            return@on true
        }

        on(NPCs.SIR_PELLEAS_244, IntType.NPC, "talk-to") { player, _ ->
            if (getQuestStage(player, "Holy Grail") >= 10) {
                player.dialogueInterpreter.open(SirPelleasHGDialogue(), NPC(NPCs.SIR_PELLEAS_244))
            }
            return@on true
        }

        on(NPCs.SIR_TRISTRAM_243, IntType.NPC, "talk-to") { player, _ ->
            if (getQuestStage(player, "Holy Grail") >= 10) {
                player.dialogueInterpreter.open(SirTristamHGDialogue(), NPC(NPCs.SIR_TRISTRAM_243))
            }
            return@on true
        }
    }
}