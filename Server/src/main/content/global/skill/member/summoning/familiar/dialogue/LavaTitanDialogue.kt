package content.global.skill.member.summoning.familiar.dialogue

import config.NPCs
import content.global.skill.member.summoning.familiar.Familiar
import core.api.openDialogue
import core.api.sendDialogue
import core.api.sendDialogueOptions
import core.api.teleport
import core.game.dialogue.DialogueFile
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.TeleportManager.TeleportType
import core.game.world.map.Location
import core.game.world.map.zone.impl.WildernessZone
import core.plugin.Initializable
import core.tools.END_DIALOGUE
import core.tools.RandomFunction

/**
 * Represents the Lava Titan familiar interaction dialogue.
 */
@Initializable
class LavaTitanDialogue(player: Player? = null) : DialoguePlugin(player) {
    // Teleport source: https://youtu.be/thmFi71xugs
    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        if (npc !is Familiar) {
            return false
        }
        val f = npc as Familiar
        if (f.owner !== player) {
            player.packetDispatch.sendMessage("This is not your follower.")
            return true
        } else {
            sendDialogueOptions(player, "Select an Option", "Chat", "Teleport to Lava Maze")
        }
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (buttonId) {
            1 -> openDialogue(player, LavaTitanDialogueFile())
            2 -> if (!WildernessZone.checkTeleport(player, 20)) {
                end()
            } else {
                teleport(player, Location(3030, 3842, 0), TeleportType.NORMAL)
                end()
            }
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.LAVA_TITAN_7341, NPCs.LAVA_TITAN_7342)
    }
}

class LavaTitanDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1,2,3,4,5))
    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.LAVA_TITAN_7341)
        if (randomConversation == 1){
            when(stage){
                0 -> playerl(FacialExpression.NEUTRAL, "Isn't it a lovely day, Titan?").also { stage++ }
                1 -> npcl(FacialExpression.OLD_NORMAL, "It is quite beautiful. The perfect sort of day for a limerick. Perhaps, I could tell you one?").also { stage++ }
                2 -> playerl(FacialExpression.NEUTRAL, "That sounds splendid.").also { stage++ }
                3 -> npcl(FacialExpression.OLD_NORMAL, "There once was a bard of Edgeville,").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "Whose limericks were quite a thrill,").also { stage++ }
                5 -> npcl(FacialExpression.OLD_NORMAL, "He wrote this one here,").also { stage++ }
                6 -> npcl(FacialExpression.OLD_NORMAL, "His best? Nowhere near,").also { stage++ }
                7 -> npcl(FacialExpression.OLD_NORMAL, "But at least half a page it did fill.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 2){
            when(stage){
                0 -> playerl(FacialExpression.NEUTRAL, "I was just thinking about the River Lum, Titan. Isn't it beautiful?").also { stage++ }
                1 -> npcl(FacialExpression.OLD_NORMAL, "I had a bad experience with the River Lum once. Would you like me to tell you about it?").also { stage++ }
                2 -> playerl(FacialExpression.NEUTRAL, "Well, okay, but only if it's in the form of a limerick.").also { stage++ }
                3 -> npcl(FacialExpression.OLD_NORMAL, "I once saw a river called Lum,").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "So lovely I had to succumb,").also { stage++ }
                5 -> npcl(FacialExpression.OLD_NORMAL, "The results, they were grave,").also { stage++ }
                6 -> npcl(FacialExpression.OLD_NORMAL, "For I boiled all the waves,").also { stage++ }
                7 -> npcl(FacialExpression.OLD_NORMAL, "And ended up just feeling glum.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 3){
            when(stage){
                0 -> playerl(FacialExpression.NEUTRAL, "Titan, I wish I had more money.").also { stage++ }
                1 -> npcl(FacialExpression.OLD_NORMAL, "Money doesn't always bring happiness. I know a limerick about money as it happens. Would you like to hear it?").also { stage++ }
                2 -> playerl(FacialExpression.NEUTRAL, "Sure.").also { stage++ }
                3 -> npcl(FacialExpression.OLD_NORMAL, "There was a king of Ardougne,").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "Who cared too much about coin,").also { stage++ }
                5 -> npcl(FacialExpression.OLD_NORMAL, "He filled up ten pools,").also { stage++ }
                6 -> npcl(FacialExpression.OLD_NORMAL, "Then swam amongst jewels,").also { stage++ }
                7 -> npcl(FacialExpression.OLD_NORMAL, "And wouldn't let anyone join.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 4){
            when(stage) {
                0 -> playerl(FacialExpression.NEUTRAL, "I'm quite a warrior, you know, Titan! I was just reminiscing about how I defeated Glough.").also { stage++ }
                1 -> npcl(FacialExpression.OLD_NORMAL, "Glough, eh? I've heard of that fellow. In fact, I happen to know a limerick about him.").also { stage++ }
                2 -> playerl(FacialExpression.NEUTRAL, "Oh, really? Well, I'm quite busy; perhaps you could tell me some oth").also { stage++ }
                3 -> sendDialogue(player!!, "The titan seems rather excited and launches into the limerick regardless!").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL, "There once was a scoundrel called Glough,").also { stage++ }
                5 -> npcl(FacialExpression.OLD_NORMAL, "For whom nothing was ever enough,").also { stage++ }
                6 -> npcl(FacialExpression.OLD_NORMAL, "His goal was the world,").also { stage++ }
                7 -> npcl(FacialExpression.OLD_NORMAL, "Battle standard unfurled,").also { stage++ }
                8 -> npcl(FacialExpression.OLD_NORMAL, "But he luckily wasn't that tough!").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 5){
            when(stage){
                0 -> playerl(FacialExpression.NEUTRAL, "Are you hungry?").also { stage++ }
                1 -> npcl(FacialExpression.OLD_NORMAL, "Hmm...no, I don't think so. I don't need to eat, you see.").also { stage++ }
                2 -> playerl(FacialExpression.NEUTRAL, "Oh, how awful. Food is delicious, you'd love it!").also { stage++ }
                3 -> npcl(FacialExpression.OLD_NORMAL, "It's alright. I met a dwarf once who described it all to me. In fact, I wrote a limerick in his honour.").also { stage++ }
                4 -> playerl(FacialExpression.NEUTRAL, "Oh, I'd love to hear it.").also { stage++ }
                5 -> npcl(FacialExpression.OLD_NORMAL, "I once met a dwarf who loved pie,").also { stage++ }
                6 -> npcl(FacialExpression.OLD_NORMAL, "For a redberry one he would cry,").also { stage++ }
                7 -> npcl(FacialExpression.OLD_NORMAL, "'I do love you so.'").also { stage++ }
                8 -> npcl(FacialExpression.OLD_NORMAL, "'From berry to dough.'").also { stage++ }
                9 -> npcl(FacialExpression.OLD_NORMAL, "Then he'd eat it and let out a sigh.").also { stage = END_DIALOGUE }
            }
        }
    }
}