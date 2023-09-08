package content.quest.member.thelosttribe.handlers

import config.Items
import config.NPCs
import core.api.*
import core.game.dialogue.FacialExpression
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.system.task.Pulse
import core.game.world.GameWorld
import core.game.world.update.flag.context.Animation

/**
 * handles pickpocketing sigmund during the lost tribe quest
 */
class PickpocketSigmund : InteractionListener {
    val SIGMUND = NPCs.SIGMUND_2082

    override fun defineListeners() {
        on(SIGMUND, IntType.NPC, "pickpocket"){ player, node ->
            player.lock()
            GameWorld.Pulser.submit(object : Pulse(){
                var counter = 0
                override fun pulse(): Boolean {
                    when(counter++){
                        0 -> player.animator.animate(Animation(881))
                        3 -> {
                            if(getQuestStage(player,"Lost Tribe") == 47 && !inInventory(player, Items.KEY_423)){
                                addItemOrDrop(player, Items.KEY_423)
                                sendItemDialogue(player,Items.KEY_423,"You find a small key on Sigmund.")
                            } else {
                                sendNPCDialogue(player,2082,"What do you think you're doing?!", FacialExpression.ANGRY)
                            }
                            player.unlock()
                            return true
                        }
                    }
                    return false
                }
            })
            return@on true
        }
    }
}