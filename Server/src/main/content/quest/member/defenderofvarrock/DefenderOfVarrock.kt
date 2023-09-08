package content.quest.member.defenderofvarrock

import config.Items
import config.Vars
import core.api.rewardXP
import core.api.setVarbit
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.quest.Quest
import core.game.node.entity.skill.Skills
import core.plugin.Initializable

/**
 * Defender of Varrock quest.
 */
@Initializable
class DefenderOfVarrock : Quest("Defender of Varrock", 159, 158, 2,Vars.VARBIT_QUEST_DEFENDER_OF_VARROCK_5387,0,1,250) {
    companion object {
        const val DefenderOfVarrock = "Defender of Varrock"
    }

    override fun drawJournal(player: Player?, stage: Int) {
        super.drawJournal(player, stage)
        var line = 11
        player ?: return

        if (stage == 100) {
            line++
            line(player, "<col=FF0000>QUEST COMPLETE!", line, false)
        }
    }

    override fun finish(player: Player?) {
        super.finish(player)
        player ?: return
        var ln = 10

        player.packetDispatch.sendItemZoomOnInterface(Items.RELIC_6820, 230, 277, 5)

        drawReward(player, "2 Quest Points", ln++)
        drawReward(player, "15,000 Hunter XP", ln++)
        drawReward(player, "10,000 Smithing XP", ln++)
        drawReward(player, "10,000 Mining XP", ln++)
        drawReward(player, "10,000 Defence XP", ln++)
        drawReward(player, "2,000 Agility XP", ln++)

        rewardXP(player, Skills.HUNTER, 15000.0)
        rewardXP(player, Skills.SMITHING, 10000.0)
        rewardXP(player, Skills.MINING, 10000.0)
        rewardXP(player, Skills.DEFENCE, 10000.0)
        rewardXP(player, Skills.AGILITY, 2000.0)
        setVarbit(player, Vars.VARBIT_QUEST_DEFENDER_OF_VARROCK_5387, 250, true)
    }


    override fun newInstance(`object`: Any?): Quest {
        return this
    }

}

//2 quest points
//15,000 Hunter experience lamp
//10,000 Smithing experience lamp
//10,000 Mining experience lamp
//10,000 Defence experience lamp
//2,000 Agility experience lamp
//Access to Chaos Temple Dungeon, where you can kill armoured zombies.
//Access to four goldrune material caches
//5 Kudos in Varrock Museum and a 1,000 experience antique lamp from Historian Minas on the 1st floor[UK] of the Museum.
//2 Treasure Hunter keys (Ironman accounts will not receive these)
//Music unlocked
//Dream Theatre (In the cutscene with The Sacred Forge)
//The Ruins of Camdozaal (Under the Ice Mountain, in Camdozaal)
//Undead Army (In the dungeon below the Chaos Temple)
//Zombie Invasion (Varrock Palace during the zombie invasion)
