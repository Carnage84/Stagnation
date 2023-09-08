package content.region.misthalin.varrock.dialogue

import config.Components
import config.Items
import config.NPCs
import core.api.*
import core.game.component.Component
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * Represents the Thessalia dialogue plugin.
 */
@Initializable
class ThessaliaDialogue(player: Player? = null) : DialoguePlugin(player) {
    override fun open(vararg args: Any): Boolean {

        //The trade argument is handled elsewhere
        if (args.size == 3) { //Right-Click 'Change-Clothes' Option
            if (player.equipment.isEmpty) {
                if (player.isMale) {
                    end()
                    player.interfaceManager.open(Component(Components.THESSALIA_CLOTHES_MALE_591))
                } else {
                    end()
                    player.interfaceManager.open(Component(Components.THESSALIA_CLOTHES_FEMALE_594))
                }
            } else { //Has some armour equipped
                interpreter.sendDialogues(548, FacialExpression.WORRIED, "You can't try them on while wearing armour. Take", "it off and speak to me again.")
                stage = 54
            }
            return true
        }

        npc = args[0] as NPC

        //Default Talk
        interpreter.sendDialogues(npc, FacialExpression.ASKING, "Would you like to buy any fine clothes?")
        stage = 0
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            0 -> {
                if(inInventory(player, Items.FROG_TOKEN_6183)) {
                    interpreter.sendOptions("Choose an option:", "What do you have?", "No, thank you.", "I have a frog token...")
                    stage++
                } else {
                    interpreter.sendOptions("Choose an option:", "What do you have?", "No, thank you.")
                    stage++
                }
            }

            1 -> when (buttonId) {
                1 -> {
                    interpreter.sendDialogues(player, FacialExpression.ASKING, "What do you have?")
                    stage = 10
                }

                2 -> {
                    interpreter.sendDialogues(player, FacialExpression.NEUTRAL, "No, thank you.")
                    stage = 54
                }

                3 -> if(inInventory(player, Items.FROG_TOKEN_6183)) {
                    interpreter.sendDialogues(npc, FacialExpression.FRIENDLY, "That entitles you to a free costume!", "Do you want a frog mask or a " + (if (player.isMale) "prince" else "princess") + " outfit?")
                    stage = 52
                }
            }
            10 -> {
                interpreter.sendDialogues(npc, FacialExpression.HAPPY, "I have a number of fine pieces of clothing on sale or,", "if you prefer, I can offer you an exclusive", "total clothing makeover?")
                stage++
            }
            11 -> {
                interpreter.sendOptions("Select an Option", "Tell me more about this makeover.", "I'd just like to buy some clothes.")
                stage++
            }

            12 -> when (buttonId) {
                1 -> {
                    interpreter.sendDialogues(player, FacialExpression.THINKING, "Tell me more about this makeover.")
                    stage = 20
                }

                2 -> {
                    interpreter.sendDialogues(player, FacialExpression.NEUTRAL, "I'd just like to buy some clothes.")
                    stage = 50
                }
            }

            20 -> {
                interpreter.sendDialogues(npc, FacialExpression.HAPPY, "Certainly!")
                stage++
            }

            21 -> {
                interpreter.sendDialogues(npc, FacialExpression.HAPPY, "Here at Thessalia's fine clothing boutique, we offer a", "unique service where we will totally revamp your outfit", "to your choosing, for... wait for it...")
                stage++
            }

            22 -> {
                interpreter.sendDialogues(npc, FacialExpression.AMAZED, "A fee of only 500 gold coins! Tired of always wearing", "the same old outfit, day in, day out? This is the service", "for you!")
                stage++
            }

            23 -> {
                interpreter.sendDialogues(npc, FacialExpression.ASKING, "So what do you say? Interested? We can change either", "your top, or your legwear for only 500 gold an item!")
                stage++
            }

            24 -> {
                interpreter.sendOptions("Select an Option", "I'd like to change my outfit, please.", "I'd just like to buy some clothes.")
                stage++
            }

            25 -> when (buttonId) {
                1 -> {
                    interpreter.sendDialogues(player, FacialExpression.HAPPY, "I'd like to change my outfit, please.")
                    stage = 30
                }

                2 -> {
                    interpreter.sendDialogues(player, FacialExpression.HAPPY, "I'd just like to buy some clothes.")
                    stage = 50
                }
            }

            30 -> if (player.equipment.isEmpty) {
                interpreter.sendDialogues(npc, FacialExpression.HAPPY, "Just select what style and colour you would like from", "this catalogue, and then give me the 1000 gold when", "you've picked.")
                stage++
                playAudio(player, 273)
            } else { //Has some armour equipped
                interpreter.sendDialogues(npc, FacialExpression.WORRIED, "You can't try them on while wearing armour. Take", "it off and speak to me again.")
                stage = 54
            }

            31 -> if (player.equipment.isEmpty) {
                if (player.isMale) {
                    end()
                    player.interfaceManager.open(Component(Components.THESSALIA_CLOTHES_MALE_591))
                } else {
                    end()
                    player.interfaceManager.open(Component(Components.THESSALIA_CLOTHES_FEMALE_594))
                }
            }

            49 -> {
                interpreter.sendDialogues(npc, FacialExpression.FRIENDLY, "That's ok! Just come back when you do have it!")
                stage = 54
            }

            50 -> {
                end()
                openNpcShop(player, NPCs.THESSALIA_548)
            }

            51 -> {
                npc("Well, please return if you change your mind.")
                stage = 54
            }
            // Kiss the frog random
            52 ->  { // Exchanging tokens
                interpreter.sendOptions("Select an Option", "Frog mask, please!", "" + (if (player.isMale) "Prince" else "Princess") + " outfit, please!")
                stage = 53
            }

            53 -> when (buttonId) {
                1 -> if(removeItem(player, Items.FROG_TOKEN_6183)){ // Mask for token
                    interpreter.sendDialogues(npc, FacialExpression.FRIENDLY, "There you go.")
                    addItemOrDrop(player, Items.FROG_MASK_6188, 1)
                    stage = 54
                }

                2 -> { // Top & bottom for token
                    interpreter.sendDialogues(npc, FacialExpression.FRIENDLY, "There you go.")
                    if (player.isMale && removeItem(player, Items.FROG_TOKEN_6183)) {
                        addItemOrDrop(player, Items.PRINCE_TUNIC_6184, 1)
                        addItemOrDrop(player, Items.PRINCE_LEGGINGS_6185, 1)
                    } else if (removeItem(player, Items.FROG_TOKEN_6183)){
                        addItemOrDrop(player, Items.PRINCESS_BLOUSE_6186, 1)
                        addItemOrDrop(player, Items.PRINCESS_SKIRT_6187, 1)
                    }
                    stage = 54
                }
            }
            54 -> end()
        }
        return true
    }

    override fun newInstance(player: Player?): DialoguePlugin {
        return ThessaliaDialogue(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.THESSALIA_548)
    }
}