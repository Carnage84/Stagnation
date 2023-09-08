package content.region.misthalin.varrock.dialogue

import config.NPCs
import content.quest.free.shieldofarrav.dialogue.CuratorHaigHalenSOADialogue
import content.quest.member.digsite.dialogue.CuratorHaigHalenDigsiteDialogue
import content.quest.member.thegolem.dialogue.CuratorHaigHalenGolemDialogue
import core.api.getQuestStage
import core.api.openDialogue
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.dialogue.IfTopic
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * Represents the Curator Haig Halen dialogue plugin.
 */
@Initializable
class CuratorHaigHalenDialogue(player: Player? = null) : DialoguePlugin(player) {

    /*
    Info: Curator of the Varrock Museum. He can be found on the ground floor.
    Location in-game: 3257,3447
     */

    override fun open(vararg args: Any): Boolean {
        npc = args[0] as NPC
        npc(FacialExpression.HAPPY, "Welcome to the museum of Varrock.")
        if (getQuestStage(player, "Digsite") >= 1) {
            end()
            openDialogue(player, CuratorHaigHalenDigsiteDialogue(), npc)
        }
        stage = 0
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            0 -> {
                val fallthrough = showTopics(
                    IfTopic("I have the Shield of Arrav",
                        CuratorHaigHalenSOADialogue(),
                        getQuestStage(player,"Shield of Arrav") == 70, false),
                    IfTopic("I'm looking for a statuette recovered from the city of Uzer.",
                        CuratorHaigHalenGolemDialogue(),
                        getQuestStage(player,"The Golem") == 3, false)
                )
                if (fallthrough) {
                    stage = 1
                    handle(interfaceId, buttonId)
                }
            }
            1 -> end()
        }
        return true
    }

    override fun newInstance(player: Player): DialoguePlugin {
        return CuratorHaigHalenDialogue(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.CURATOR_HAIG_HALEN_646)
    }

}