package content.region.misthalin.dorgeshuun.dialogue

import config.Items
import content.quest.member.deathtothedorgeshuun.dialogue.MistagDTDDialogue
import content.quest.member.thelosttribe.handlers.GoblinFollower
import content.quest.member.thelosttribe.dialogue.MistagLTDialogue
import core.api.getAttribute
import core.api.getQuestStage
import core.api.inInventory
import core.api.openDialogue
import core.game.component.Component
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE
import core.tools.START_DIALOGUE

/**
 * Represents the Mistag dialogue plugin.
 */
@Initializable
class MistagDialogue(player: Player? = null) : DialoguePlugin(player){
    override fun newInstance(player: Player?): DialoguePlugin {
        return MistagDialogue(player)
    }

    override fun npc(vararg messages: String?): Component {
        return npc(FacialExpression.OLD_NORMAL,*messages)
    }

    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        val ltStage = getQuestStage(player, "Lost Tribe")

        if(args.size > 1 && args[1] == "greeting"){
            npc("A human knows ancient greeting?")
            loadFile(MistagLTDialogue(true,ltStage))
            return true
        }
        if(!getAttribute(player,"mistag-greeted",false)){
            npc("Who...who are you? How did you get in here?")
            stage = -100
            return true
        }

        if(ltStage == 45){
            npc("Greetings, friend. I am sorry I panicked when I saw you.")
            loadFile(MistagLTDialogue(false,ltStage))
            return true
        } else if(ltStage == 50){
            npc("Hello, friend?")
            loadFile(MistagLTDialogue(false,ltStage))
            return true
        }
        npc("Hello friend!").also { stage = 0 }
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when(stage){
            -100 -> npc("Help! A surface dweller this deep in our mines? We will","all be destroyed!").also { stage++ }
            -99 -> end()

            START_DIALOGUE -> if (getQuestStage(player, "Lost Tribe") == 100 && getQuestStage(player, "Death to the Dorgeshuun") == 0){
                npc("It is good to see you again! The Dorgeshuun Council would like to ask a favour of you, if you are interested.").also { stage++ }
            } else {
                options("Can I sell you some ore?", "Why do the dorgeshuun live underground?", "What happened to your arm?", "Can you show me the way out of the mines?").also { stage = 2 }
            }

            1 -> options("What is this favour?", "Can I sell you some ore?", "Why do the dorgeshuun live underground?", "What happened to your arm?", "Can you show me the way out of the mines?").also { stage = 3 }

            2 -> when(buttonId){
                1 -> player("Can I sell you some ore?").also { stage = 4 }
                2 -> player("Why do the dorgeshuun live underground?").also { stage = 8 }
                3 -> player("What happened to your arm?").also { stage = 5 }
                4 -> player("Can you show me the way out of the mines?").also { stage = 7 }
             // 5 -> player("Can you show me the way to water mill cellar?").also { stage = ? }
            }

            3 -> when(buttonId){
                1 -> player("What is this favour?").also { end(); openDialogue(player, MistagDTDDialogue(),npc) }
                2 -> player("Can I sell you some ore?").also { stage = 4 }
                3 -> player("Why do the dorgeshuun live underground?").also { stage = 8 }
                4 -> player("What happened to your arm?").also { stage = 5 }
                5 -> player("Can you show me the way out of the mines?").also { stage = 7 }
            }

            4 -> if(inInventory(player, Items.SILVER_ORE_442) || inInventory(player, Items.IRON_ORE_440)){
                npc("Certainly. I will buy any iron or silver ore you mine.","Speak to me again when you have some.").also { stage = END_DIALOGUE }
            } else {
                npc("Certainly. I will buy any iron or silver ore you mine.","Speak to me again when you have some.").also { stage = END_DIALOGUE }
            }

            5 -> npcl(FacialExpression.OLD_NORMAL,"I lost it in a mining accident a few years ago. This area is very unstable. That's why we put markers on the wall showing the safest path.").also { stage = END_DIALOGUE }
            6 -> npc("Certainly, friend!").also { stage = END_DIALOGUE }
            7 -> npc("Certainly. Come back soon!").also { GoblinFollower.sendToLumbridge(player); stage = END_DIALOGUE }
            8 -> npcl(FacialExpression.OLD_NORMAL,"Our ancient legends say that goblins were created by an evil god in order to fight in a huge way. This god sent the Dorgeshuun tribe to fight a battle where all would die.").also { stage++ }
            9 -> npcl(FacialExpression.OLD_NORMAL,"But our ancestors escaped by hiding in a deep hole in the ground where our god could not find us. Eventually an earthquake sealed the hole and they were forever safe.").also { stage++ }
            10 -> options("The war is over now, so you can return to the surface.","What was the name of your god?").also { stage++ }
            11 -> when(buttonId){
                1 -> playerl(FacialExpression.NEUTRAL,"The war is over now, so you can return to the surface.").also { stage = 12 }
                2 -> playerl(FacialExpression.HALF_ASKING,"What was the name of your god?").also { stage = 14 }
            }
            12 -> npcl(FacialExpression.OLD_NORMAL, "Even if the gods are no longer at war, I find it hard to believe that a tribe of peaceful goblins would be safe above. I have heard of humans coming down into the daves to slaughter our people!").also { stage++ }
            13 -> npcl(FacialExpression.OLD_NORMAL,"But even if we could return, we would not want to. We are adapted to living underground, and we have built a home for ourselves that we do not want to leave.").also { stage = END_DIALOGUE }
            14 -> npcl(FacialExpression.OLD_NORMAL,"Our ancestors did not speak his name, thinking that to do so would attract his attention. His name has been unspoken for so long that it is now entirely forgotten.").also { stage++ }
            15 -> npcl(FacialExpression.OLD_NORMAL,"That is for the best. We have survived perfectly well without the gods, and we do not want a return to the old ways.").also { stage = END_DIALOGUE }
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(2084)
    }

}