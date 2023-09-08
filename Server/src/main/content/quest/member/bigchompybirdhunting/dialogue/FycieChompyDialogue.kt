package content.quest.member.bigchompybirdhunting.dialogue

import config.NPCs
import content.quest.member.bigchompybirdhunting.BigChompyBirdHunting
import core.api.getAttribute
import core.api.getItemName
import core.api.setAttribute
import core.game.dialogue.DialogueFile
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.quest.Quest
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the Fycie dialogue plugin for Big Chompy Bird Hunting quest.
 */
@Initializable
class FycieChompyDialogue(player: Player? = null) : DialoguePlugin(player) {
  override fun getIds() : IntArray {
    return intArrayOf(NPCs.FYCIE_1011)
  }

  override fun newInstance(player: Player?) : DialoguePlugin {
    return FycieChompyDialogue(player)
  }

  override fun open(vararg args: Any?) : Boolean {
    npc = args[0] as NPC

    val chompyBird = player.questRepository.getQuest("Big Chompy Bird Hunting")
    val chompyStage = chompyBird.getStage(player)

    when (chompyStage) {
      in 0 until 100 -> loadFile(FycieChompyDialogueFile(chompyBird))
    }

    player.dialogueInterpreter.handle(0,0)

    return true
  }

  override fun handle(componentId: Int, buttonId: Int) : Boolean {
    return true
  }
}

/**
 * Represents the Fycie dialogue file for Big Chompy Bird Hunting quest.
 */
class FycieChompyDialogueFile(val quest: Quest) : DialogueFile() {
  override fun handle(componentId: Int, buttonId: Int) {
    when (quest.getStage(player)) {
      in 0 until 70 -> npcl(FacialExpression.OLD_NORMAL, "You's better talk to Dad, We not talk to wierdly 'umans.").also { stage = END_DIALOGUE }
      in 70 until 90 -> handleIngredientDialogue(player, buttonId)
    }
  }

  private fun handleIngredientDialogue(player: Player?, buttonId: Int) {
    val fycieIngredient = getAttribute(player!!, BigChompyBirdHunting.ATTR_ING_FYCIE, -1)
    when(stage) {
      0 -> npcl(FacialExpression.OLD_NORMAL, "Dad say's you's roasting da chompy for us! Slurp! Me's wants ${getItemName(fycieIngredient)} wiv mine! Yummy can't wait to eats it.").also { stage = END_DIALOGUE }
    }
    setAttribute(player!!, BigChompyBirdHunting.ATTR_FYCIE_ASKED, true)
  }
}