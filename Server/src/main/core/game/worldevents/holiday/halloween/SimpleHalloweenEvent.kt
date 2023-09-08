package core.game.worldevents.holiday.halloween

import core.game.world.GameWorld
import core.game.worldevents.PluginSet
import core.game.worldevents.WorldEvent

class SimpleHalloweenEvent : WorldEvent("hween"){
    override fun checkActive(): Boolean {
        return false
    }

    override fun initialize() {
        plugins = PluginSet(
            GrimDialogue()
        )
        super.initialize()
        GameWorld.settings?.message_model = 800
        GameWorld.settings?.message_string = "A mysterious figure has appeared in Draynor! You should go investigate!"
        log("Initialized.")
    }
}