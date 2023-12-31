package content.global.random.event.sandwichlady

import config.Items
import config.NPCs
import content.global.random.RandomEventNPC
import core.api.utils.WeightBasedTable
import core.game.node.entity.npc.NPC
import core.tools.RandomFunction

/**
 * Represents the Sandwitch Lady random event NPC.
 */
class SandwichLadyRENPC(override var loot: WeightBasedTable? = null) : RandomEventNPC(NPCs.SANDWICH_LADY_3117) {
    val phrases = arrayOf("Hello, @name, can you hear me?","Sandwiches, @name!","Are you ignoring me @name??","Yoohoo! Sandwiches, @name!","Hello, @name?", "Come get your sandwiches @name!", "How could you ignore me like this @name?!", "Do you even want your sandwiches, @name?")
    var assigned_item = 0
    val items = arrayOf(
        Items.BAGUETTE_6961,
        Items.TRIANGLE_SANDWICH_6962,
        Items.SQUARE_SANDWICH_6965,
        Items.ROLL_6963,
        Items.MEAT_PIE_2327,
        Items.KEBAB_1971,
        Items.CHOCOLATE_BAR_1973)

    override fun tick() {
        if(RandomFunction.random(1,15) == 5){
            sendChat(phrases.random().replace("@name",player.name.capitalize()))
        }
        super.tick()
    }

    override fun init() {
        super.init()
        assignItem()
        sendChat(phrases.random().replace("@name",player.name.capitalize()))
    }

    fun assignItem(){
        assigned_item = items.random()
        player.setAttribute("sandwich-lady:item",assigned_item)
    }

    override fun talkTo(npc: NPC) {
        player.dialogueInterpreter.open(SandwichLadyDialogue(false),npc)
    }
}