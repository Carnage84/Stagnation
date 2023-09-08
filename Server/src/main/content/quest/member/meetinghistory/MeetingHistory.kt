package content.quest.member.meetinghistory

import config.Items
import config.Vars
import core.api.addItemOrDrop
import core.api.setVarbit
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.quest.Quest
import core.plugin.Initializable

/**
 * Meeting History quest.
 */
@Initializable
class MeetingHistory : Quest("Meeting History", 156, 155, 1,Vars.VARBIT_QUEST_MEETING_HISTORY_5075,0,1,20) {
    companion object {
        const val MeetingHistory = "Meeting History"
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

        player.packetDispatch.sendItemZoomOnInterface(Items.OLD_TOME_13593, 230, 277, 5)
        drawReward(player, "1 Quest Point", ln++)
        addItemOrDrop(player, Items.OLD_TOME_13593)
        setVarbit(player, Vars.VARBIT_QUEST_MEETING_HISTORY_5075, 20, true)
    }

    override fun newInstance(`object`: Any?): Quest {
        return this
    }

}
//1 quest point
//An old tome giving a total of 2,500 experience across up to 3 skills with level 25 or higher (1,000 experience for the first chapter, 1,000 experience for the second, and 500 experience for the third).
//Even more treasure hunting with the enchanted key miniquest.
//Speak to Historian Minas at the Varrock Museum to earn 5 Kudos.
//2 Treasure Hunter keys (Ironman accounts will not receive these)