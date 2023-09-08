package content.quest.member.fightarena.dialogue

import config.NPCs
import content.quest.member.fightarena.FightArena
import core.api.getQuestStage
import core.api.setQuestStage
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the Jeremy Servil A dialogue in fight arena quest.
 */
@Initializable
class JeremyServilADialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(componentID: Int, buttonID: Int): Boolean {
        when (getQuestStage(player!!, FightArena.FightArenaQuest)) {

            20 -> when (stage) {
                0 -> playerl(FacialExpression.FRIENDLY, "Hello.").also { stage++ }
                1 -> npcl(FacialExpression.FRIENDLY, "Please " + (if (player!!.isMale) "Sir" else "Madam") + ", don't hurt me.").also { stage++ }
                2 -> playerl(FacialExpression.FRIENDLY, "Sshh. This uniform is a disguise. I'm here to help. Where do they keep the keys?").also { stage++ }
                3 -> npcl(FacialExpression.FRIENDLY, "The guard always keeps hold of them.").also { stage++ }
                4 -> playerl(FacialExpression.FRIENDLY, "Don't lose heart, I'll be back.").also { stage++ }
                5 -> {
                    end()
                    setQuestStage(player!!, FightArena.FightArenaQuest, 40)
                }
            }

            in 90..100 -> when (stage) {
                0 -> npcl(FacialExpression.FRIENDLY, "You need to kill the creatures in the arena").also { stage = END_DIALOGUE }
            }
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.JEREMY_SERVIL_265)
    }
}