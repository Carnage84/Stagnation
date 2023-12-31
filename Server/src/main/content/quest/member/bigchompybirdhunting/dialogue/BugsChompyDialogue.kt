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
 * Represents the Bugs dialogue plugin for Big Chompy Bird Hunting quest.
 */
@Initializable
class BugsChompyDialogue(player: Player? = null) : DialoguePlugin(player) {
  override fun getIds() : IntArray {
    return intArrayOf(NPCs.BUGS_1012)
  }

  override fun newInstance(player: Player?) : DialoguePlugin {
    return BugsChompyDialogue(player)
  }

  override fun open(vararg args: Any?) : Boolean {
    npc = args[0] as NPC

    val chompyBird = player.questRepository.getQuest("Big Chompy Bird Hunting")
    val chompyStage = chompyBird.getStage(player)

    when (chompyStage) {
      in 0 until 100 -> loadFile(BugsChompyDialogueFile(chompyBird))
    }

    player.dialogueInterpreter.handle(0,0)

    return true
  }

  override fun handle(componentId: Int, buttonId: Int) : Boolean {
    return true
  }
}

/**
 * Represents the Bugs dialogue file for Big Chompy Bird Hunting quest.
 */
class BugsChompyDialogueFile(val quest: Quest) : DialogueFile() {
  override fun handle(componentId: Int, buttonId: Int) {
    when (quest.getStage(player)) {
      in 0 until 20 -> npcl(FacialExpression.OLD_NORMAL, "You's better talk to Dad, him chasey sneaky da chompy.").also { stage = END_DIALOGUE }
      in 20 until 70 -> handleBellowsDialogue(player, buttonId)
      in 70 until 90 -> handleIngredientDialogue(player, buttonId)
    }
  }

  private fun handleBellowsDialogue(player: Player?, buttonId: Int) {
    when(stage) {
      0 -> playerl("Rantz said that you play with the fatsy toadies, what are they?").also { stage++ }
      1 -> npcl(FacialExpression.OLD_NORMAL, "Oh, we sometimes use da blower on da toadies but Dad don't let us get in da locked box no more. He he it was good fun making da toadies fat on da swamp gas.").also { stage = END_DIALOGUE }
    }
  }

  private fun handleIngredientDialogue(player: Player?, buttonId: Int) {
    val bugsIngredient = getAttribute(player!!, BigChompyBirdHunting.ATTR_ING_BUGS, -1)
    when(stage) {
      0 -> npcl(FacialExpression.OLD_NORMAL, "Dad say's you's making da chompy for us! Slurp! Me's has to have ${getItemName(bugsIngredient)} wiv mine! Chompy is our favourite yummms!").also { stage = END_DIALOGUE }
    }
    setAttribute(player!!, BigChompyBirdHunting.ATTR_BUGS_ASKED, true)
  }
}