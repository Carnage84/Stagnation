package content.quest.member.toweroflife.dialogue

import config.Items
import config.NPCs
import content.quest.member.toweroflife.TowerOfLifeListener
import core.api.*
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE
import core.tools.START_DIALOGUE

/**
 * Represents the The Guns dialogue plugin for Tower of Life quest.
 */
@Initializable
class TheGunsTOFDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(componentID: Int, buttonID: Int): Boolean {
        when (getQuestStage(player!!, "Tower Of Life")) {
            in 0..1 -> when (stage) {
                START_DIALOGUE -> playerl(FacialExpression.FRIENDLY, "Wow, that looks pretty heavy.").also { stage++ }
                1 -> npcl(FacialExpression.FRIENDLY, "Umpf! Yeah, mate...argghh...this is...umpf...hard work...").also { stage++ }
                2 -> playerl(FacialExpression.FRIENDLY, "What would happen if I were to say...").also { stage++ }
                3 -> playerl(FacialExpression.FRIENDLY, "135").also { stage++ }
                4 -> playerl(FacialExpression.FRIENDLY, "25").also { stage++ }
                5 -> playerl(FacialExpression.FRIENDLY, "234").also { stage++ }
                6 -> playerl(FacialExpression.FRIENDLY, "2351....").also { stage++ }
                7 -> npcl(FacialExpression.FRIENDLY, "Stop! Stop! Humpf...I'll beat you to...urghh...a pulp!").also { stage++ }
                8 -> playerl(FacialExpression.FRIENDLY, "Okay, okay! Don't cry!").also { stage++ }
                9 -> playerl(FacialExpression.FRIENDLY, "Why, I oughta...").also { stage = END_DIALOGUE }
            }

            2 -> if(!inInventory(player, Items.BEER_1917)) when(stage) {
                0 -> playerl(FacialExpression.FRIENDLY, "Hi, I'm looking to get some kit to look like one of you guys.").also { stage++ }
                1 -> playerl(FacialExpression.FRIENDLY, "Anything you might be able to help me out with?").also { stage++ }
                2 -> npcl(FacialExpression.FRIENDLY, "Well...Urmpf! Ye can 'ave me shirt...").also { stage++ }
                3 -> playerl(FacialExpression.FRIENDLY, "Hmmm. Has it been cleaned? I don't want some sweaty hand-me-down.").also { stage++ }
                4 -> npcl(FacialExpression.FRIENDLY, "What? Huhmf! It ain't ever been...uurrgg...worn.").also { stage++ }
                5 -> playerl(FacialExpression.FRIENDLY, "Well, that sounds good. Can I have it then?").also { stage++ }
                6 -> npcl(FacialExpression.FRIENDLY, "Can't...hurrr... see why not. This is firsty work doh. Could do wiv a beer.").also { stage++ }
                7 -> playerl(FacialExpression.FRIENDLY, "Sure. What's your 'usual'?").also { stage++ }
                8 -> npcl(FacialExpression.FRIENDLY, "I'm a simple...umph...man. I like the bar in Yanille and their cheap bear.").also { stage++ }
                9 -> playerl(FacialExpression.FRIENDLY, "Simple, cheap beer. No problem.").also { stage = END_DIALOGUE }
                // 0 -> player("Hi, I'm looking to get some kit to look like one of you guys. You said you wanted a beer?").also { stage++ }
                // 1 -> npc("Ahhh! Smashin...urrghh...deal!").also { stage = END_DIALOGUE }
            } else {
                when(stage) {
                    START_DIALOGUE -> playerl(FacialExpression.FRIENDLY, "Hi, I'm looking to get some kit to look like one of you guys.").also { stage ++ }
                    1 -> playerl(FacialExpression.FRIENDLY, "Anything you might be able to help me out with?").also { stage++ }
                    2 -> npcl(FacialExpression.FRIENDLY, "Well...Urmpf! Ye can 'ave me shirt...").also { stage++ }
                    3 -> playerl(FacialExpression.FRIENDLY, "Hmmm. Has it been cleaned? I don't want some sweaty hand-me-down.").also { stage++ }
                    4 -> npcl(FacialExpression.FRIENDLY, "What? Huhmf! It ain't ever been...uurrgg...worn.").also { stage++ }
                    5 -> playerl(FacialExpression.FRIENDLY, "Well, that sounds good. Can I have it then?").also { stage++ }
                    6 -> npcl(FacialExpression.FRIENDLY, "Can't...hurrr... see why not. This is firsty work doh. Could do wiv a beer.").also { stage++ }
                    7 -> playerl(FacialExpression.FRIENDLY, "Well, today's your lucky day!").also { stage++ }
                    8 -> npcl(FacialExpression.FRIENDLY, "Ahhh! Smashin...urrghh...deal!").also { stage++ }
                    9 -> {
                        end()
                        if(!inInventory(player, Items.BUILDERS_SHIRT_10863)) {
                            addItemOrDrop(player, Items.BUILDERS_SHIRT_10863)
                            sendMessage(player, "Try the beckon emote while wearing an item of builders' clothing!")
                        }
                        stage = END_DIALOGUE
                    }
                }
            }
            3 -> if(getAttribute(player, TowerOfLifeListener.ENTER_TOL, 0) == 1) when(stage){
                START_DIALOGUE -> playerl(FacialExpression.FRIENDLY, "What do you do when you're not lifting logs above your head?").also { stage++ }
                1 -> npcl(FacialExpression.FRIENDLY, "In my free time?").also { stage++ }
                2 -> playerl(FacialExpression.FRIENDLY, "Yes.").also { stage++ }
                3 -> npcl(FacialExpression.FRIENDLY, "All sorts. I like...hummft...a good kebab and a cold beer down...urrghh...the pub. And for those quiet evenings...hurrr... A bit of needlepoint is always welcome.").also { stage++ }
                4 -> playerl(FacialExpression.FRIENDLY, "I can honestly say you're a very original guy.").also { stage = END_DIALOGUE }
            }

        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.THE_GUNS_5592)
    }

}