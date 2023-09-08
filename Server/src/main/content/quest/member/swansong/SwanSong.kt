package content.quest.member.swansong

import config.Items
import config.Vars
import core.api.addItemOrDrop
import core.api.rewardXP
import core.api.setVarbit
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.quest.Quest
import core.game.node.entity.skill.Skills
import core.plugin.Initializable

/**
 * Swan Song quest.
 */
@Initializable
class SwanSong : Quest("Swan Song", 117, 116, 2, Vars.VARBIT_QUEST_SWAN_SONG_2098, 0, 1, 200) {
    companion object {
        const val SwanSong = "Swan Song"
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

        player.packetDispatch.sendItemZoomOnInterface(Items.COINS_995, 230, 277, 5)

        drawReward(player, "2 Quest Points", ln++)
        drawReward(player, "15,000 Construction XP", ln++)
        drawReward(player, "10,000 Prayer XP", ln++)
        drawReward(player, "10,000 Fishing XP", ln++)
        drawReward(player, "1,000 Summoning XP", ln++)
        rewardXP(player, Skills.CONSTRUCTION, 15000.0)
        rewardXP(player, Skills.PRAYER, 10000.0)
        rewardXP(player, Skills.FISHING, 10000.0)
        addItemOrDrop(player, Items.COINS_995, 25000)
        addItemOrDrop(player, Items.BROWN_APRON_1757, 1)
        setVarbit(player, Vars.VARBIT_QUEST_SWAN_SONG_2098, 200, true)
    }

    override fun newInstance(`object`: Any?): Quest {
        return this
    }
}
//2 quest points
//15,000 Magic experience
//10,000 Prayer experience
//10,000 Fishing experience
//25,000 coins
//Access to the Piscatoris Fishing Colony
//Able to fish monkfish
//Able to use Skeletal mages for training
//Seaweed spawns
//Bank and obelisk very close by
//Can get the following items from Malignius Mortifer upon request:
//Brown apron
//Bone seed (requires pot and pot lid)
//Able to transmute raw bass into raw monkfish
//2 Treasure Hunter keys (Ironman accounts will not receive these)
//Music unlocked
//Making Waves
//Last Stand
//Eagle Peak (depending on how you walk to Kathy Corkat)
//Joy of the Hunt (if you took Kathy's boat, or walk toward it in the fishing colony)