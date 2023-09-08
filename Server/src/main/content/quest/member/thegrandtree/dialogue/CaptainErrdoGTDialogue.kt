package content.quest.member.thegrandtree.dialogue

import config.NPCs
import content.global.travel.glider.Gliders
import content.quest.member.thegrandtree.TheGrandTree
import core.api.getQuestStage
import core.api.teleport
import core.game.component.Component
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.player.Player
import core.game.world.map.Location
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents Captain Errdo dialogue in the grand tree quest.
 */
@Initializable
class CaptainErrdoGTDialogue(player: Player? = null) : DialoguePlugin(player){
    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when(getQuestStage(player!!, TheGrandTree.questName)){
            55 -> {
                if(player!!.location.regionId == 11567){
                    when(stage){
                        0 -> npcl(FacialExpression.OLD_DEFAULT, "Sorry about that.").also { stage++ }
                        1 -> npcl(FacialExpression.OLD_DEFAULT,"That turbulence over the Karamja Volcano was a bit unexpected, and the area round here isn't well suited for emergency landing.").also { stage++ }
                        2 -> npcl(FacialExpression.OLD_DEFAULT,"Still! we're still alive that's the main thing. Are you okay?").also { stage++ }
                        3 -> playerl(FacialExpression.FRIENDLY,"I'm fine, I can't say the same for your glider!").also { stage++ }
                        4 -> npcl(FacialExpression.OLD_DEFAULT,"I don't think I can fix this. Looks like we'll be heading back by foot. I might see if I can find Penwie while I'm here, I believe he's charting the area.").also { stage++ }
                        5 -> playerl(FacialExpression.FRIENDLY,"Where's the shipyard from here?").also { stage++ }
                        6 -> npcl(FacialExpression.OLD_DEFAULT,"I think I saw some buildings on the coast east of here while we were crashing. I'd have a look there.").also { stage++ }
                        7 -> npcl(FacialExpression.OLD_DEFAULT,"Take care adventurer!").also { stage++ }
                        8 -> playerl(FacialExpression.FRIENDLY,"Take care little man.").also { stage = END_DIALOGUE }
                    }
                } else if (player!!.location.regionId == 9782){
                    when(stage){
                        0 -> npcl(FacialExpression.OLD_DEFAULT,"Hi. The king said that you need to leave?").also { stage++ }
                        1 -> playerl(FacialExpression.FRIENDLY,"Apparently, humans are invading!").also { stage++ }
                        2 -> npcl(FacialExpression.OLD_DEFAULT,"I find that hard to believe. I have lots of human friends.").also { stage++ }
                        3 -> playerl(FacialExpression.FRIENDLY,"I don't understand it either!").also { stage++ }
                        4 -> npcl(FacialExpression.OLD_DEFAULT,"So, where to?").also { stage++ }
                        5 -> options("Take me to Karamja please!", "Not anywhere for now!").also { stage++ }
                        6 -> when(buttonId){
                            1 -> playerl(FacialExpression.FRIENDLY,"Take me to Karamja, please.").also { stage++ }
                            2 -> playerl(FacialExpression.FRIENDLY,"Not anywhere for now.").also { stage = 10 }
                        }
                        7 -> npcl(FacialExpression.OLD_DEFAULT,"Okay, you're the boss! Hold on tight, it'll be a rough ride.").also {
                            teleport(player!!, Location.create(2917, 3054, 0))
                            stage = END_DIALOGUE
                        }
                        10 -> npcl(FacialExpression.OLD_DEFAULT,"Okay. I'll be here for when you're ready.").also { stage = END_DIALOGUE }
                    }
                } else {
                    // We are talking to another gnome outside the stronghold during the quest.
                    when(stage) {
                        0 -> playerl(FacialExpression.ASKING,"May you fly me somewhere on your glider?").also { stage++ }
                        1 -> npcl(FacialExpression.OLD_DEFAULT,"I only fly friends of the gnomes!").also {
                            stage = END_DIALOGUE
                        }
                    }
                }
            }
            100 -> {
                when(stage){
                    0 -> playerl(FacialExpression.ASKING,"May you fly me somewhere on your glider?").also { stage++ }
                    1 -> npcl(FacialExpression.OLD_DEFAULT,"If you wish.").also {
                        stage = END_DIALOGUE
                        player!!.interfaceManager.open(Component(138))
                        Gliders.sendConfig(npc, player);

                    }
                }
            }
            else -> {
                when(stage){
                    0 -> playerl(FacialExpression.ASKING,"May you fly me somewhere on your glider?").also { stage++ }
                    1 -> npcl(FacialExpression.OLD_DEFAULT,"I only fly friends of the gnomes!").also {
                        stage = END_DIALOGUE
                    }
                }
            }
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.CAPTAIN_ERRDO_3811)
    }

}