package content.quest.member.recruitmentdrive

import config.Items
import config.Vars
import core.api.addItem
import core.api.isQuestComplete
import core.api.rewardXP
import core.api.setVarbit
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.quest.Quest
import core.game.node.entity.skill.Skills
import core.plugin.Initializable

/**
 * RecruitmentDrive quest.
 */
@Initializable
class RecruitmentDrive : Quest("Recruitment Drive", 103, 102, 1,Vars.VARBIT_QUEST_RECRUITMENT_DRIVE_657,0,1,2) {
    override fun newInstance(`object`: Any?): Quest {
        return this
    }

    companion object {
        const val RecruitmentDriveQuest = "Recruitment Drive"
    }

    override fun drawJournal(player: Player?, stage: Int) {

        super.drawJournal(player, stage)
        var line = 11
        player ?: return

        if (stage == 0) {
            line(player, "I can start this quest by speaking to !!Sir Amik Varze??,", line++)
            line(player, "upstairs in !!Falador Castle??.", line++)
            if (isQuestComplete(player,"Druidic Ritual")) {
                line(player, "with the Druidic Ritual Quest completed,", line++, isQuestComplete(player,"Druidic Ritual"))
            } else {
                line(player, "I have to completed the !!Druidic Ritual Quest??,", line++)
            }
            if (isQuestComplete(player,"Black Knights' Fortress")) {
                line(player, "and since I have completed the Black Knights' Fortress Quest.", line++, isQuestComplete(player,"Black Knights' Fortress"))
            } else {
                line(player, "and I have to completed the !!Black Knights' Fortress Quest??.", line++)
            }
            line++
        }

        if (stage >= 10) {
            line(player, "!!Sir Amik Varze?? told me that he had put my name forward", line++, stage >= 20)
            line(player, "as a potential member of some mysterious organisation.", line++, stage >= 20)
            line++
            line(player, "I should head to !!Falador Park?? to meet my !!Contact??", line++, stage >= 20)
            line(player, "so that I can begin my !!testing for the job??.", line++, stage >= 20)
            line++
        }

        if (stage >= 20) {
            line(player, "I went to !!Falador Park??, and met a strange old man named !!Tiffy??.", line++, stage > 25)
            line++
        }

        if (stage >= 30) {
            line(player, "He sent me to a secret training ground,", line++, stage > 35)
            line(player, "where my wits were thoroughly tested.", line++, stage > 35)
            line++
        }

        if (stage >= 40) {
            line(player, "Luckily, I was too smart to fall for any of their little tricks,", line++, stage > 45)
            line(player, "and passed the test with flying colours.", line++, stage > 45)
            line++
        }

        if (stage >= 99) {
            line(player, "I am now an !!official member of the Temple Knights??,", line++, stage == 100)
            line(player, "although I have to wait for the paperwork to go through before", line++, stage == 100)
            line(player, "I can commence working for them.", line++, stage == 100)
            line++
        }

        if (stage == 100) {
            line(player, "<col=FF0000>QUEST COMPLETE!", line, false)
        }
    }

    override fun finish(player: Player?) {
        super.finish(player)
        player ?: return
        var ln = 10
        player.packetDispatch.sendItemZoomOnInterface(Items.INITIATE_SALLET_5574, 230, 277, 5)
        drawReward(player, "1 Quest Point", ln++)
        drawReward(player, "1000 Prayer, Herblore and Agility XP", ln++)
        drawReward(player, "Gaze of Saradomin", ln++)
        drawReward(player, "Temple Knight's Initiate Helm.", ln)
        rewardXP(player, Skills.PRAYER, 1000.0)
        rewardXP(player, Skills.AGILITY, 1000.0)
        rewardXP(player, Skills.HERBLORE, 1000.0)
        addItem(player, Items.MAKEOVER_VOUCHER_5606)
        addItem(player, Items.INITIATE_SALLET_5574)
        setVarbit(player, Vars.VARBIT_QUEST_RECRUITMENT_DRIVE_657, 2, true)
        
    }

}
