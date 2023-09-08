package content.global.skill.member.summoning.familiar.dialogue

import config.Items
import config.NPCs
import content.global.skill.member.summoning.familiar.Familiar
import core.api.*
import core.game.dialogue.DialogueFile
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.TeleportManager
import core.game.world.map.Location
import core.game.world.map.zone.impl.WildernessZone
import core.plugin.Initializable
import core.tools.END_DIALOGUE
import core.tools.RandomFunction

/**
 * Represents the Spirit Kyatt familiar interaction dialogue.
 */
@Initializable
class SpiritKyattDialogue(player: Player? = null) : DialoguePlugin(player) {
    // Teleport source: https://youtu.be/S4vQkFwLTpU
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
            sendDialogueOptions(player, "Select an Option", "Chat", "Teleport")
        }
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (buttonId) {
            1 -> openDialogue(player, SpiritKyattDialogueFile())
            2 -> if (!WildernessZone.checkTeleport(player, 20)) {
                end()
            } else {
                teleport(player, Location(2326, 3634, 0), TeleportManager.TeleportType.NORMAL)
                end()
            }
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.SPIRIT_KYATT_7365, NPCs.SPIRIT_KYATT_7366)
    }
}

class SpiritKyattDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1, 2, 3, 4))

    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.SPIRIT_KYATT_7365)
        if (randomConversation == 1) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_DEFAULT, "Guess who wants a belly rub, human.").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Umm...is it me?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_DEFAULT, "No, human, it is not you. Guess again.").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Is it the Duke of Lumbridge?").also { stage++ }
                4 -> npcl(FacialExpression.OLD_DEFAULT, "You try my patience, human!").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "Is it Zamorak? That would explain why he's so cranky.").also { stage++ }
                6 -> npcl(FacialExpression.OLD_DEFAULT, "Please do not make me destroy you before I get my belly rub!").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 2) {
            when (stage) {
                0 -> playerl(FacialExpression.NEUTRAL, "Here, kitty!").also { stage++ }
                1 -> npcl(FacialExpression.OLD_DEFAULT, "What do you want, human?").also { stage++ }
                2 -> playerl(FacialExpression.NEUTRAL, "I just thought I would see how you were.").also { stage++ }
                3 -> npcl(FacialExpression.OLD_DEFAULT, "I do not have time for your distractions. Leave me be!").also { stage++ }
                4 -> playerl(FacialExpression.NEUTRAL, "Well, sorry! Would a ball of wool cheer you up?").also { stage++ }
                5 -> npcl(FacialExpression.OLD_DEFAULT, "How dare you insult my intelli- what colour wool?").also { stage++ }
                6 -> playerl(FacialExpression.NEUTRAL, "Umm...white?").also { stage++ }
                7 -> npcl(FacialExpression.OLD_DEFAULT, "I will end you!").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 3) {
            when (stage) {
                0 -> playerl(FacialExpression.NEUTRAL, "Hello, kitty cat!").also { stage++ }
                1 -> npcl(FacialExpression.OLD_DEFAULT, "Human, leave me be. I'm far too busy to deal with your nonsense.").also { stage++ }
                2 -> playerl(FacialExpression.NEUTRAL, "What are you up to?").also { stage++ }
                3 -> npcl(FacialExpression.OLD_DEFAULT, "I am engaged in an intricate dirt-purging operation!").also { stage++ }
                4 -> playerl(FacialExpression.NEUTRAL, "Aww, kitty's cleaning his paws! How cute!").also { stage++ }
                5 -> npcl(FacialExpression.OLD_DEFAULT, "Know this, human. Once I finish cleaning my paws...").also { stage++ }
                6 -> npcl(FacialExpression.OLD_DEFAULT, "I will destroy you!").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 4) {
            when (stage) {
                0 -> playerl(FacialExpression.NEUTRAL, "Here, kitty!").also { stage++ }
                1 -> npcl(FacialExpression.OLD_DEFAULT, "Do not toy with me, human!").also { stage++ }
                2 -> playerl(FacialExpression.NEUTRAL, "What about under your chin?").also { stage++ }
                3 -> npcl(FacialExpression.OLD_DEFAULT, "I am not one of your playful kittens, human. I eat playful kittens for breakfast!").also { stage++ }
                4 -> playerl(FacialExpression.NEUTRAL, "Not even behind your ears?").also { stage++ }
                5 -> sendDialogue(player!!, "You lean down and tickle the kyatt behind the ears.").also { stage++ }
                6 -> npcl(FacialExpression.OLD_DEFAULT, "I will...purrrrr...ooh that's quite nice...destroy...purrrrrrr...you.").also { stage = END_DIALOGUE }
            }
        }

        if (inInventory(player!!, Items.BALL_OF_WOOL_1759)) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_DEFAULT, "Human, hand me that ball of wool.").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Aww...do you want to play with it?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_DEFAULT, "I do not 'play', human.").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "If you say so, kitty! Alright, you can have it.").also { stage++ }
                4 -> npcl(FacialExpression.OLD_DEFAULT, "Aha! Ball of wool: you are mine now. I will destroy you!").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "Well I'm not giving it to you, now! I'll never get it back.").also { stage++ }
                6 -> npcl(FacialExpression.OLD_DEFAULT, "Then you leave me no choice but to destroy YOU, human!").also { stage++ }
                7 -> playerl(FacialExpression.NEUTRAL, "Bad kitty!").also { stage = END_DIALOGUE }
            }
        }
    }
}