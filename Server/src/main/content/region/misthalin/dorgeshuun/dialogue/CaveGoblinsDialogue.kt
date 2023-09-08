package content.region.misthalin.dorgeshuun.dialogue

import config.Items
import config.NPCs
import core.api.addItemOrDrop
import core.api.anyInInventory
import core.api.getQuestStage
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE
import core.tools.RandomFunction

/**
 * Represents the Cave goblins dialogue plugin.
 */
@Initializable
class CaveGoblinsDialogue(player: Player? = null) : DialoguePlugin(player) {
    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        var randomConversation: Int = RandomFunction.getRandom(6)
        if (getQuestStage(player, "Lost Tribe") == 100) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "You're the human who secured peace between the Dorgeshuun and Lumbridge, aren't you?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Um, yes.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Thank you so much! I was worried it was a return to the times of war, but perhaps surface-dwellers are not so bad.").also { stage = END_DIALOGUE }
            }
        } else if (anyInInventory(player, 32, 33)) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Watch out! You don't want to let a naked flame near swamp gas! Look out for the warning marks.").also { stage = END_DIALOGUE }
            }
        } else if (anyInInventory(player, 594, 4531, 4534, 4539, 4550, 9804, 9805)) {
            npcl(FacialExpression.OLD_NORMAL, "Don't shine that thing in my eyes!").also { stage = END_DIALOGUE }
        } else if(randomConversation == 0){
            when (stage) {
                1 -> npcl(FacialExpression.OLD_NORMAL, "What are you doing down here without a lamp?").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL, "Here, I have a spare torch.").also { stage++ }
                3 -> {
                    end()
                    addItemOrDrop(player, Items.LIT_TORCH_594)
                    stage = END_DIALOGUE
                }
            }
        } else if(randomConversation == 1){
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Where did you come from?").also { stage = 50 }
                50 -> playerl(FacialExpression.NEUTRAL, "From above ground.").also { stage = 60 }
                60 -> npcl(FacialExpression.OLD_NORMAL, "Above ground? Where is that?").also { stage = 70 }
                70 -> playerl(FacialExpression.NEUTRAL, "You know, out of caves, in the open air, with sunshine and wide open spaces!").also { stage = 80 }
                80 -> npcl(FacialExpression.OLD_NORMAL, "Ick. Sounds horrible.").also { stage = END_DIALOGUE }
            }
        } else if(randomConversation == 2){
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Don't tread on my feet!").also { stage = 90 }
                90 -> playerl(FacialExpression.NEUTRAL, "I'm not going to tread on your feet.").also { stage = END_DIALOGUE }
            }
        } else if(randomConversation == 3){
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Beware of swamp gas! Look out for the warning marks!").also { stage++ }
                100 -> playerl(FacialExpression.NEUTRAL, "Um, Thanks.").also { stage = END_DIALOGUE }
            }
        } else if(randomConversation == 4){
            when (stage) {
                0 -> playerl(FacialExpression.NEUTRAL, "Hello, how are you?").also { stage = 110 }
                110 -> npcl(FacialExpression.OLD_NORMAL, "I'm a bit worried about the increase of humans these days.").also { stage = 120 }
                120 -> npcl(FacialExpression.OLD_NORMAL, "Present company excluded, of course!").also { stage = END_DIALOGUE }
            }
        } else if(randomConversation == 5){
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL, "Nice weather we're having!").also { stage = 130 }
                130 -> playerl(FacialExpression.NEUTRAL, "But you live underground. The weather is always the same!").also { stage = 140 }
                140 -> npcl(FacialExpression.OLD_NORMAL, "Yes, it's always nice!").also { stage = END_DIALOGUE }
            }
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(
            NPCs.CAVE_GOBLIN_MINER_2069,
            NPCs.CAVE_GOBLIN_MINER_2070,
            NPCs.CAVE_GOBLIN_MINER_2071,
            NPCs.CAVE_GOBLIN_MINER_2072,
            NPCs.CAVE_GOBLIN_GUARD_2073,
            NPCs.CAVE_GOBLIN_GUARD_2074,
            NPCs.CAVE_GOBLIN_MINER_2075,
            NPCs.CAVE_GOBLIN_MINER_2076,
            NPCs.CAVE_GOBLIN_MINER_2077,
            NPCs.CAVE_GOBLIN_MINER_2078,
            NPCs.CAVE_GOBLIN_MINER_2079,
            NPCs.CAVE_GOBLIN_MINER_2080,
            NPCs.CAVE_GOBLIN_MINER_2081,
        )
    }

}

