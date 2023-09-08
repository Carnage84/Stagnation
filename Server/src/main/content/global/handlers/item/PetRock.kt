package content.global.handlers.item

import config.Animations
import config.Items
import core.api.animate
import core.api.openDialogue
import core.api.sendMessage
import core.game.dialogue.DialogueFile
import core.game.dialogue.FacialExpression
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.tools.END_DIALOGUE

/**
 * Handles the Pet rock options.
 */
class PetRockPlugin : InteractionListener {
    override fun defineListeners() {
      on(Items.PET_ROCK_3695, IntType.ITEM, "interact"){ player, _ ->
        openDialogue(player, PetRockDialogue())
        return@on true
      }
    }
}

/**
 * Represents the Pet rock options dialogue.
 */
class PetRockDialogue : DialogueFile() {
    override fun handle(componentID: Int, buttonID: Int) {
        when(stage) {
          0 -> options("Talk", "Stroke", "Feed", "Fetch", "Stay").also { stage++ }
          1 -> {
            when(buttonID) {
              1 -> {
                  playerl(FacialExpression.FRIENDLY, "Who's a good rock then? Yes you are... You're such a good rock... ooga booga googa.")
                  sendMessage(player!!,"Your rock seems a little happier.")
                  stage = END_DIALOGUE
              }
              2 -> {
                  sendMessage(player!!,"You stroke your pet rock.")
                  animate(player!!, Animations.HUMAN_STROKE_PET_ROCK_1333)
                  sendMessage(player!!,"Your rock seems much happier.")
                  end()
              }
              3 -> {
                  sendMessage(player!!,"You try and feed the rock.")
                  sendMessage(player!!,"Your rock doesn't seem hungry.")
                  end()
              }
              4 -> {
                  playerl(FacialExpression.FRIENDLY, "Want to fetch the stick, rock? Of course you do...")

                  stage = END_DIALOGUE
              }
              5 -> {
                  playerl(FacialExpression.FRIENDLY, "Be a good rock...")
                  sendMessage(player!!,"You wait a few seconds and pick your rock back up and pet it.")
                  animate(player!!, 800)
                  stage = END_DIALOGUE
              }
            }
          }
        }
    }

}