package content.quest.member.thelosttribe.handlers

import content.data.skill.SkillingTool
import core.api.sendDialogue
import core.api.sendItemDialogue
import core.api.setAttribute
import core.api.setVarbit
import core.game.interaction.NodeUsageEvent
import core.game.interaction.UseWithHandler
import core.game.node.entity.skill.Skills
import core.game.system.task.Pulse
import core.game.world.GameWorld
import core.plugin.Initializable
import core.plugin.Plugin


/**
 * Handles the usage of a pickaxe on the rubble for lost tribe
 */
@Initializable
class PickaxeOnRubble : UseWithHandler(1265,1267,1269,1271,1273,1275){
    override fun newInstance(arg: Any?): Plugin<Any> {
        for(id in arrayOf(6898, 6903, 6904, 6905)) {
            addHandler(id, OBJECT_TYPE,this)
        }
        return this
    }

    override fun handle(event: NodeUsageEvent?): Boolean {
        val player = event?.player ?: return false
        val stage = player.questRepository.getQuest("Lost Tribe").getStage(player)
        if(stage < 30){
            sendItemDialogue(player,event.usedItem.id,"I should probably figure out what happened, before vandalizing the castle more.")
            return true
        }

        val tool = SkillingTool.getPickaxe(player)
        if(tool == null){
            sendDialogue(player, "You don't have a pick which you have the level to use.")
            return true
        }

        if(player.skills.getLevel(Skills.MINING) < 13){
            sendDialogue(player, "You need 13 mining to break through.")
            return true
        }

        player.lock()
        GameWorld.Pulser.submit(object : Pulse(){
            var counter = 0
            override fun pulse(): Boolean {
                when(counter++){
                    0 -> {
                        player.walkingQueue.reset()
                        player.walkingQueue.addPath(3219,9618,true)
                    }
                    1 -> {
                        player.animator.animate(tool.animation)
                        delay = tool.animation.duration
                    }
                    2 -> {
                        sendItemDialogue(player,tool.id,"You dig a narrow tunnel through the rocks.")
                        setAttribute(player,"/save:tlt-hole-cleared",true)
                        setVarbit(player, 532, 4, true)
                        player.unlock()
                        return true
                    }
                }
                return false
            }
        })
        return true
    }

}
