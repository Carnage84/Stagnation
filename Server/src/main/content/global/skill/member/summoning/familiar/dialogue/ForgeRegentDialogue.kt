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
 * Represents the Forge Regent familiar interaction dialogue.
 */
@Initializable
class ForgeRegentDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, ForgeRegentDialogueFile())
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.FORGE_REGENT_7335, NPCs.FORGE_REGENT_7336)
    }
}

class ForgeRegentDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1, 2, 3, 4))

    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.FORGE_REGENT_7335)
        if (randomConversation == 1) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"Crackley spit crack sizzle? (Can we go Smithing?)").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Maybe.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"Hiss? (Can we go smelt something?)").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Maybe.").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL,"Flicker crackle sizzle? (Can we go mine something to smelt?)").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "Maybe.").also { stage++ }
                6 -> npcl(FacialExpression.OLD_NORMAL,"Sizzle flicker! (Yay! I like doing that!)").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 2) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"Hiss. (I'm happy.)").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Good.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"Crackle. (Now I'm sad.)").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Oh dear, why?").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL,"Hiss-hiss. (Happy again.)").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "Glad to hear it.").also { stage++ }
                6 -> npcl(FacialExpression.OLD_NORMAL,"Crackley-crick. (Sad now.)").also { stage++ }
                7 -> playerl(FacialExpression.NEUTRAL, "Um.").also { stage++ }
                8 -> npcl(FacialExpression.OLD_NORMAL,"Hiss. (Happy.)").also { stage++ }
                9 -> playerl(FacialExpression.NEUTRAL, "Right...").also { stage++ }
                10 -> npcl(FacialExpression.OLD_NORMAL,"Crackle. (Sad.)").also { stage++ }
                11 -> playerl(FacialExpression.NEUTRAL, "You're very strange.").also { stage++ }
                12 -> npcl(FacialExpression.OLD_NORMAL,"Sizzle hiss? (What makes you say that?)").also { stage++ }
                13 -> playerl(FacialExpression.NEUTRAL, "Oh...nothing in particular.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 3) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"Sizzle! (I like logs.)").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "They are useful for making planks.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"Sizzley crack hiss spit. (No, I just like walking on them. They burst into flames.)").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "It's a good job I can use you as a firelighter really!").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 4) {
            when (stage) {
                0  -> npcl(FacialExpression.OLD_NORMAL,"Sizzle... (I'm bored.)").also { stage++ }
                1  -> playerl(FacialExpression.NEUTRAL, "Are you not enjoying what we're doing?").also { stage++ }
                2  -> npcl(FacialExpression.OLD_NORMAL,"Crackley crickle sizzle. (Oh yes, but I'm still bored.)").also { stage++ }
                3  -> playerl(FacialExpression.NEUTRAL, "Oh, I see.").also { stage++ }
                4  -> npcl(FacialExpression.OLD_NORMAL,"Sizzle hiss? (What's that over there?)").also { stage++ }
                5  -> playerl(FacialExpression.NEUTRAL, "I don't know. Should we go and look?").also { stage++ }
                6  -> npcl(FacialExpression.OLD_NORMAL,"Hiss crackle spit sizzle crack? (Nah, that's old news - I'm bored of it now.)").also { stage++ }
                7  -> npcl(FacialExpression.OLD_NORMAL,"Crackle crickle spit hiss? (Oooooh ooooh oooooh, what's that over there?)").also { stage++ }
                8  -> playerl(FacialExpression.NEUTRAL, "But...wha...where now?").also { stage++ }
                9  -> npcl(FacialExpression.OLD_NORMAL,"Sizzle crack crickle. (Oh no matter, it no longer interests me.)").also { stage++ }
                10 -> playerl(FacialExpression.NEUTRAL, "You're hard work.").also { stage = END_DIALOGUE }
            }
        }
    }
}