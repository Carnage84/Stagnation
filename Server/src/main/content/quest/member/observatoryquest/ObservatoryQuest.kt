package content.quest.member.observatoryquest

import config.Items
import config.Vars
import core.api.rewardXP
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.quest.Quest
import core.game.node.entity.skill.Skills
import core.plugin.Initializable

/**
 * Observatory quest.
 */
@Initializable
class ObservatoryQuest : Quest("Observatory Quest", 96, 95, 2, Vars.VARP_QUEST_OBSERVATORY_QUEST_112, 0, 1, 7) {
    companion object {
        const val ObservatoryQuest = "Observatory Quest"
    }

    override fun drawJournal(player: Player, stage: Int) {
        super.drawJournal(player, stage)
        var line = 11
        player ?: return
        if (stage >= 0) {
            line(player, "I can start this quest by speaking to !!Observatory Professor??", line++, stage >= 1)
            line(player, "in the observatory !!north of Castle Wars??.", line++, stage >= 1)
            line++
        }

        if (stage == 100) {
            line++
            line(player, "<col=FF0000>QUEST COMPLETE!", line, false)
        }
    }

    override fun finish(player: Player?) {
        super.finish(player)
        player ?: return
        var ln = 10
        player.packetDispatch.sendItemZoomOnInterface(Items.ARMADYL_PENDANT_87, 230, 277, 5)
        drawReward(player, "2 Quest Points", ln++)
        drawReward(player, "2,250 Crafting XP", ln++)
        rewardXP(player, Skills.CRAFTING, 2250.0)
        ////2,000 Crafting experience lamp (requiring level 30 Crafting: Second cavern (see above))
        ////10,000 Agility experience lamp (requiring level 55 Agility: Fourth cavern (see above))
        ////20,000 Woodcutting experience lamp (requiring level 80 Woodcutting: Sixth cavern (see above))
    }

    override fun newInstance(`object`: Any?): Quest {
        return this
    }

}
//2 quest points
//2,250 Crafting experience lamp
//Depending on the astrological sign you see through the telescope, you will receive one of these items:
//An uncut sapphire (Aquarius, Aries, Cancer, Capricorn, Gemini, Leo, Libra, Sagittarius, Scorpio, Taurus, Virgo, Pisces)
//About 25 Water runes (Aquarius)
//Amulet of defence (Cancer)
//Black two-handed sword (Gemini)
//3 Law runes (Libra)
//3 Fish pies or 3 Tunas (Pisces)
//Maple shieldbow (Sagittarius)
//Weapon poison (Scorpio)
//Super strength potion (1) (Taurus)
//You can talk to Historian Minas at the Varrock Museum to receive 5 Kudos.
//Ability to use 'Chipped' Watchtower teleport after also completing Evil Dave's Big Day Out.
//2 Treasure Hunter keys (Ironman accounts will not receive these)