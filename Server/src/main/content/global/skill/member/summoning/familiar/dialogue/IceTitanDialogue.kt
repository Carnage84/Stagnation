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
 * Represents the Ice Titan familiar interaction dialogue.
 */
@Initializable
class IceTitanDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, IceTitanDialogueFile())
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.ICE_TITAN_7359, NPCs.ICE_TITAN_7360)
    }
}

class IceTitanDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1, 2, 3, 4, 5))

    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.ICE_TITAN_7359)
        if (randomConversation == 1) {
            when (stage) {
                0  -> playerl(FacialExpression.NEUTRAL, "How are you feeling?").also { stage++ }
                1  -> npcl(FacialExpression.OLD_NORMAL,"Hot.").also { stage++ }
                2  -> playerl(FacialExpression.NEUTRAL, "Are you ever anything else?").also { stage++ }
                3  -> npcl(FacialExpression.OLD_NORMAL,"Sometimes I'm just the right temperature: absolute zero.").also { stage++ }
                4  -> playerl(FacialExpression.NEUTRAL, "What's that then, when it's not at home with it's [sic] feet up on the couch?").also { stage++ }
                5  -> npcl(FacialExpression.OLD_NORMAL,"What?").also { stage++ }
                6  -> playerl(FacialExpression.NEUTRAL, "Absolute zero; what is it?").also { stage++ }
                7  -> npcl(FacialExpression.OLD_NORMAL,"Oh...it's the lowest temperature that can exist.").also { stage++ }
                8  -> playerl(FacialExpression.NEUTRAL, "Like the temperature of ice?").also { stage++ }
                9  -> npcl(FacialExpression.OLD_NORMAL,"Um, no. Rather a lot colder.").also { stage++ }
                10 -> playerl(FacialExpression.NEUTRAL, "Like a deepest, darkest winter day?").also { stage++ }
                11 -> npcl(FacialExpression.OLD_NORMAL,"Nah, that's warm by comparison.").also { stage++ }
                12 -> playerl(FacialExpression.NEUTRAL, "Like an Ice Barrage in your jammies?").also { stage++ }
                13 -> npcl(FacialExpression.OLD_NORMAL,"Even colder than that.").also { stage++ }
                14 -> playerl(FacialExpression.NEUTRAL, "Yikes! That's rather chilly.").also { stage++ }
                15 -> npcl(FacialExpression.OLD_NORMAL,"Yeah. Wonderful, isn't it?").also { stage++ }
                16 -> playerl(FacialExpression.NEUTRAL, "If you say so.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 2) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"Can we just stay away from fire for a while?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "I like fire, it's so pretty.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"Personally, I think it's terrifying.").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Why?").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL,"I'm not so keen on hot things.").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "Ah.").also { stage++ }
                6 -> npcl(FacialExpression.OLD_NORMAL,"Indeed.").also { stage++ }
                7 -> playerl(FacialExpression.NEUTRAL, "I see.").also { stage++ }
                8 -> npcl(FacialExpression.OLD_NORMAL,"Yes. Well...").also { stage++ }
                9 -> playerl(FacialExpression.NEUTRAL, "...let's get on with it.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 3) {
            when (stage) {
                0  -> npcl(FacialExpression.OLD_NORMAL,"I could murder an ice-cream.").also { stage++ }
                1  -> playerl(FacialExpression.NEUTRAL, "Is that a Slayer creature?").also { stage++ }
                2  -> npcl(FacialExpression.OLD_NORMAL,"Um...").also { stage++ }
                3  -> playerl(FacialExpression.NEUTRAL, "What does it drop?").also { stage++ }
                4  -> npcl(FacialExpression.OLD_NORMAL,"Erm...").also { stage++ }
                5  -> playerl(FacialExpression.NEUTRAL, "What level is it?").also { stage++ }
                6  -> npcl(FacialExpression.OLD_NORMAL,"It...").also { stage++ }
                7  -> playerl(FacialExpression.NEUTRAL, "Where can I find it?").also { stage++ }
                8  -> npcl(FacialExpression.OLD_NORMAL,"I...").also { stage++ }
                9  -> playerl(FacialExpression.NEUTRAL, "What equipment will I need?").also { stage++ }
                10 -> npcl(FacialExpression.OLD_NORMAL,"What...").also { stage++ }
                11 -> playerl(FacialExpression.NEUTRAL, "I don't think it will be high enough level.").also { stage++ }
                12 -> npcl(FacialExpression.OLD_NORMAL,"Urm...").also { stage++ }
                13 -> playerl(FacialExpression.NEUTRAL, "...").also { stage++ }
                14 -> npcl(FacialExpression.OLD_NORMAL,"We should get on with what we were doing.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 4) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"It's too hot here.").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "It's really not that hot. I think it's rather pleasant.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"Well, it's alright for some. Some of us don't like the heat. I burn easily - well, okay, melt.").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Well, at least I know where to get a nice cold drink if I need one.").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL,"What was that?").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "Nothing. Hehehehe").also { stage = END_DIALOGUE }

            }
        }
        // (only in the Kharidian Desert).
        if (randomConversation == 5) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"I'm melting!").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "I have to admit, I am rather on the hot side myself.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"No, I mean I'm actually melting! My legs have gone dribbly.").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Urk! Well, try hold it together.").also { stage = END_DIALOGUE }
            }
        }
    }
}