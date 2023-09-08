package content.quest.member.toweroflife

import config.Items
import config.Vars
import core.api.getAttribute
import core.api.hasLevelStat
import core.api.rewardXP
import core.api.setVarbit
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.quest.Quest
import core.game.node.entity.skill.Skills
import core.plugin.Initializable

/**
 * Tower Of Life quest.
 */
@Initializable
class TowerOfLife : Quest("Tower Of Life", 134, 133, 2, Vars.VARBIT_QUEST_TOWER_OF_LIFE_3337, 0, 1, 18) {

    override fun drawJournal(player: Player?, stage: Int) {
        super.drawJournal(player, stage)
        var line = 11
        player ?: return
        if (stage == 0) {
            line(player, "Minimum requirements:", line++, false)
            line++
            line(player, if (hasLevelStat(player, Skills.CONSTRUCTION, 10)) "---Level 10 Construction/--" else "!!Level 10 Construction??", line++)
            line++
            line(player, "I can start this quest by talking to !!Effigy?? at the !!tower??", line++, false)
            line(player, "!!south-east of Ardougne??.", line++, false)
            line++
        }
        if (stage == 1) {
            line(player, "Effigy told me the tower has ceased construction because", line++, false)
            line(player, "of the builders' strike.", line++, false)
            line++
        }
        if (stage == 2) {
            line(player, "Hopefully, talking to Bonafido, the head builder,", line++, false)
            line(player, "will sort things out.", line++, false)
            line(player, "Bonafido says I can go into the tower", line++, false)
            line(player, "if I dress up like a builder.", line++, false)
            line++
        }
        if (stage == 2 && getAttribute(player, TowerOfLifeListener.SEARCH_CLOTHES, true)) {
            line(player, "I should be able to find some clothing", line++, false)
            line(player, "around the tower.", line++, false)
            line++
        }

        if (stage == 3) {
            line(player, "I got my kit together and passed", line++, false)
            line(player, "an initiation ritual.", line++, false)
            line++
        }
        if (getAttribute(player, TowerOfLifeListener.ENTER_TOL, 0) == 2) {
            line(player, "Time to venture into the tower!", line++, false)
            line(player, "This place is amazing! Time to fix things and tell Effigy.", line++, false)
            line++
        }

        /*
        if (stage == 99) {
            line(player, "I told Effigy about fixing the tower and he ran inside.", line++, false)
            line(player, "I should go look for him.", line++, false)
            line(player, "I saw the creation of life!", line++, false)
            line(player, "The alchemists ran outside - best I go talk to them.", line++, false)
            line(player, "They created a life form! But sadly, it seems to have gone wrong.", line++, false)
            line(player, "I should go investigate the top floor of the tower.", line++, false)
            line(player, "They created a Homunculus,", line++, false)
            line(player, "which seems to be a mixture of logic and magic", line++, false)
            line(player, "Perhaps I can help by talking to it.", line++, false)
            line(player, "I've rescued the Homunculus's mind.", line++, false)
            line(player, "I should confront Effigy outside the tower.", line++, false)
            line(player, "The alchemists have fled,", line++, false)
            line(player, "the Homunculus wants me to meet in the dungeon under the tower.", line++, false)
        }
        */

        if (stage == 100) {
            line(player, "<col=FF0000>QUEST COMPLETE!", line, false)
        }
    }

    override fun finish(player: Player?) {
        super.finish(player)
        player ?: return
        var ln = 10

        player.packetDispatch.sendItemZoomOnInterface(Items.BUILDERS_SHIRT_10863, 230, 277, 5)
        drawReward(player, "2 Quest Points", ln++)
        drawReward(player, "1,000 Construction XP", ln++)
        drawReward(player, "500 Crafting", ln++)
        drawReward(player, "500 Thieving", ln++)
        rewardXP(player, Skills.CONSTRUCTION, 1000.0)
        rewardXP(player, Skills.CRAFTING, 500.0)
        rewardXP(player, Skills.THIEVING, 500.0)
        /*
        addItemOrDrop(player, Items.COINS_995, 5000)
        addItemOrDrop(player, Items.BUILDERS_SHIRT_10863, 1)
        addItemOrDrop(player, Items.BUILDERS_TROUSERS_10864, 1)
        addItemOrDrop(player, Items.BUILDERS_BOOTS_10865, 1)
        */
        setVarbit(player, Vars.VARBIT_QUEST_TOWER_OF_LIFE_3337, 18, true)
    }
    override fun newInstance(`object`: Any?): Quest {
        return this
    }

}

//2 quest points
//1,000 Construction experience
//500 Crafting experience
//500 Thieving experience
//Builder's costume
//Access to Creature Creation
//2 Treasure Hunter keys (Ironman accounts will not receive these)
