package content.global.activity.cchallange

import config.Items
import config.NPCs
import core.api.*
import core.game.dialogue.DialogueFile
import core.game.node.entity.npc.NPC
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the dialogue file used for the larxus npc.
 */
@Initializable
class LarxusDialogue(val challengeStart: Boolean = false) : DialogueFile() {

    override fun handle(componentID: Int, buttonID: Int) {
        val scrolls = intArrayOf(Items.CHAMPION_SCROLL_6798, Items.CHAMPION_SCROLL_6799, Items.CHAMPION_SCROLL_6800, Items.CHAMPION_SCROLL_6801, Items.CHAMPION_SCROLL_6802, Items.CHAMPION_SCROLL_6803, Items.CHAMPION_SCROLL_6804, Items.CHAMPION_SCROLL_6805, Items.CHAMPION_SCROLL_6806, Items.CHAMPION_SCROLL_6807,)
        npc = NPC(NPCs.LARXUS_3050)
        if(challengeStart){
            when (stage) {
                0 -> {
                    face(findNPC(NPCs.LARXUS_3050)!!, player!!, 1)
                    for (i in scrolls)when{
                        inInventory(player!!, Items.CHAMPION_SCROLL_6798) -> npcl("So you want to accept the challenge huh? Well there are some specific rules for these Champion fights. For this fight you're not allowed to use prayer's. Do you still want to proceed?").also { stage = 1 }
                        inInventory(player!!, Items.CHAMPION_SCROLL_6799) -> npcl("So you want to accept the challenge huh? Well there are some specific rules for these Champion fights. For this fight you're allowed to use only weapons. Do you still want to proceed?").also { stage = 1 }
                        inInventory(player!!, Items.CHAMPION_SCROLL_6800) -> npcl("So you want to accept the challenge huh? Well there are some specific rules for these Champion fights. For this fight you're allowed to use only melee combat skill. Do you still want to proceed?").also { stage = 1 }
                        inInventory(player!!, Items.CHAMPION_SCROLL_6801) -> npcl("So you want to accept the challenge huh? Well there are some specific rules for these Champion fights. For this fight you're allowed to use only magic skill. Do you still want to proceed?").also { stage = 1 }
                        inInventory(player!!, Items.CHAMPION_SCROLL_6802) -> npcl("So you want to accept the challenge huh? Well there are some specific rules for these Champion fights. For this fight you're not allowed to use melee combat skills. Do you still want to proceed?").also { stage = 1 }
                        inInventory(player!!, Items.CHAMPION_SCROLL_6803) -> npcl("So you want to accept the challenge huh? Well there are some specific rules for these Champion fights. For this fight you're not allowed to use weapons with special attack. Do you still want to proceed?").also { stage = 1 }
                        inInventory(player!!, Items.CHAMPION_SCROLL_6804) -> npcl("So you want to accept the challenge huh? Well there are some specific rules for these Champion fights. For this fight you're not allowed to use ranged skill. Do you still want to proceed?").also { stage = 1 }
                        inInventory(player!!, Items.CHAMPION_SCROLL_6805) -> npcl("So you want to accept the challenge huh? Well there are some specific rules for these Champion fights. For this fight you're allowed to use only equipment. Do you still want to proceed?").also { stage = 1 }
                        inInventory(player!!, Items.CHAMPION_SCROLL_6806) -> npcl("So you want to accept the challenge huh? Well there are some specific rules for these Champion fights. For this fight you're allowed to use only ranged skill. Do you still want to proceed?").also { stage = 1 }
                        inInventory(player!!, Items.CHAMPION_SCROLL_6807) -> npcl("So you want to accept the challenge huh? Well there are some specific rules for these Champion fights. For this fight you're not allowed to use magic skill. Do you still want to proceed?").also { stage = 1 }
                        else -> { sendMessage(player!!, "Nothing interesting happens.").also { stage = END_DIALOGUE } } }
                }
                1 -> options("Yes, let me at him!", "No, thanks I'll pass.").also { stage = 2 }
                2 -> when (buttonID) {
                    1 -> playerl("Yes, let me at him!").also { stage = 3 }
                    2 -> playerl("No, thanks I'll pass.").also { stage = END_DIALOGUE }
                }
                3 -> npcl("Your challenger is ready, please go down through the trapdoor when you're ready.").also { stage = 4 }
                4 -> {
                    end()
                    setAttribute(player!!, "championsarena:start", true)
                }
            }
        }
    }
}