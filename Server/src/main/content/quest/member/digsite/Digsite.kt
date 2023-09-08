package content.quest.member.digsite

import config.Items.GOLD_BAR_2357
import config.Vars
import core.api.addItemOrDrop
import core.api.rewardXP
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.quest.Quest
import core.game.node.entity.skill.Skills
import core.plugin.Initializable

/**
 * Main class for The Dig Site quest.
 */
@Initializable
class Digsite : Quest("The Dig Site", 47, 46, 2, Vars.VARP_QUEST_DIGSITE_131, 0, 1, 9) {
    companion object { const val Digsite = "The Dig Site" }
    override fun drawJournal(player: Player?, stage: Int) {
        super.drawJournal(player, stage)
        var line = 11
        player ?: return
        if (stage == 0) {
            line(player,"I can start this quest by speaking to", line++, false)
            line(player,"!!Examiner?? in the !!Exam Centre?? south of the digsite.", line++, false)
            line(player, "I should speak to an examiner about taking Earth Science exams.", line++, false)
            line++
        }
        if (stage == 1) {
            line(player, "I need to return the letter of recommendation from the Varrock The Dig Site manager,", line++, false)
            line++
        }
        if (stage == 2) {
            line(player, "Seth Minas, to an examiner at the Exam Centre for inspection.", line++, false)
            line(player, "I need to study for my first exam. Perhaps the students on the site can help?", line++, false)
            line++
        }
        if (player.getAttribute("digsite:students", 0) == 1) {
            line(player, "I need to speak to the student in the green top with a face mask about the exams.", line++)
            line++
        }
        if (player.getAttribute("digsite:students", 0) > 1) {
            line(player, "I have agreed to help the student in the green top with a face mask.", line++)
            line(player, "He has lost his animal skull and thinks he may have dropped it around the site.", line++)
            line(player, "I need to find it and return it to him. Maybe one of the workmen has picked it up?", line++)
            line(player, "I should talk to him to see if he can help with my exams.", line++)
            line(player, "He gave me an answer to one of the questions on the first exam.", line++)
            line++
        }

        if (player.getAttribute("digsite:students", 0) == 2) {
            line(player, "I need to speak to the student in the purple top with pigtails about the exams.", line++)
            line++
        }
        if (player.getAttribute("digsite:students", 0) > 2) {
            line(player, "I need to speak to the student in the brown top with the beard about the exams.", line++)
            line(player, "I have agreed to help the student in the brown top with the beard.", line++)
            line(player, "He has lost his special cup and thinks he may have dropped", line++)
            line(player, "it while he was near the panning site, possibly in the water.", line++)
            line(player, "I need to find it and return it.", line++)
            line(player, "I should talk to him to see if he can help me with my exams.", line++)
            line(player, "He gave me an answer to one of the questions on the first exam.", line++)
            line(player, "I should talk to an examiner to take my first exam. ", line++)
            line++
        }
        if (player.getAttribute("digsite:students", 0) == 3) {
            line(player, "I agreed to help the student in the purple top with pigtails.", line++)
            line++
        }
        if (player.getAttribute("digsite:students", 0) > 3) {
            line(player, "She has lost her lucky teddy bear mascot and thinks she may ", line++)
            line(player, "have dropped it by the strange relic at the centre of the campus,", line++)
            line(player, "maybe in a bush. I need to find it and return it to her.", line++)
            line(player, "I should talk to her to see if she can help with my exams.", line++)
            line(player, "She gave me an answer to one of the questions on the first exam.", line++)
            line++
        }
        if (stage == 6) {
            line(player, "If I have forgotten anything, I can always ask the students again.", line++)
            line(player, "I have passed my first Earth Science exam.", line++)
            line(player, "I need to study for my second exam.", line++)
            line++
        }
        if (stage >= 7) {
            line(player, "Perhaps the three students on the site can help me again?", line++)
            line++
        }

        if (player.getAttribute("digsite:students", 0) == 4) {
            line(player, "I need to speak to the student in the green top with a face mask about the exams.", line++)
            line++
        }
        if (player.getAttribute("digsite:students", 0) == 5) {
            line(player, "I need to speak to the students in the brown top with the beard about the exams.", line++)
            line++
        }
        if (player.getAttribute("digsite:students", 0) == 6) {
            line(player, "I need to speak to the student in the purple top with pigtails about the exams.", line++)
            line++
        }
        if (stage == 9) {
            line(player, "I should talk to an examiner to take my second exam.", line++)
            line(player, "If I have forgotten anything, I can always ask the students again.", line++)
            line++
        }
        if (stage == 10) {
            line(player, "I have passed my second Earth Science exam.", line++)
            line++
        }
        if (stage > 10) {
            line(player, "I should research for my third exam.", line++)
            line(player, "Perhaps the students can help me again?", line++)
            line++
        }
        if (player.getAttribute("digsite:students", 0) == 7) {
            line(player, "I need to speak to the student in the green top with a face mask about the exams.", line++)
            line++
        }
        if (player.getAttribute("digsite:students", 0) == 8) {
            line(player, "I need to speak to the students in the brown top with the beard about the exams.", line++)
            line++
        }
        if (player.getAttribute("digsite:students", 0) == 9) {
            line(player, "I need to speak to the student in the purple top with pigtails about the exams.", line++)
            line++
        }
        if (stage > 11) {
            line(player, "I need to bring her an Opal.", line++)
            line++
        }
        if (stage > 12) {
            line(player, "I should talk to an examiner to take my third exam, ", line++)
            line(player, "If I have forgotten anything, I can always ask the students again.", line++)
            line++
        }
        if (stage == 13) {
            line(player, "I have passed my third and final Earth Science exam.", line++)
            line(player, "I need a find from the site to impress Terry Balando,", line++)
            line++
        }
        if (stage == 14) {
            line(player, "the archaeological expert at the Exam Centre.", line++)
            line++
        }
        if (stage == 14) {
            line(player, "I need to take the letter to a workman near a winch.", line++)
            line++
        }
        if (stage == 16) {
            line(player, "I need to investigate the dig shafts.", line++)
            line++
        }
        if (stage == 17) {
            line(player, "I found a secret passageway under the site.", line++)
            line++
        }
        if (stage == 18) {
            line(player, "I need to find a way to move the rocks blocking the way in the shaft. ", line++)
            line(player, "Perhaps someone else in these dig shafts can help me.", line++)
            line(player, "I covered the rocks in the cave with an explosive compound.", line++)
            line++
        }
        if (stage == 19) {
            line(player, "I need to ignite the explosive compound and blow up the rocks blocking the way.", line++)
            line++
        }
        if (stage == 20) {
            line(player, "I should look for something interesting in the secret room I found,", line++)
            line(player, "and show it to the expert at the Exam Centre.", line++)
            line++
        }
        if (stage == 21) {
            line(player, "The expert was impressed with the Zarosian tablet that I found,", line++)
            line(player, "and I also discovered an ancient altar!", line++)
            line++
        }
        if (stage == 22) {
            line(player, "I was rewarded for my findings.", line++)
            line(player, "My work here is done.", line++)
            line(player, "I should also talk to the expert about any other finds.", line++)
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

        player.packetDispatch.sendItemZoomOnInterface(GOLD_BAR_2357, 230, 277, 5)

        drawReward(player, "2 Quest Points", ln++)
        drawReward(player, "15,300 Mining XP", ln++)
        drawReward(player, "2,000 Herblore XP", ln++)
        drawReward(player, "2 Gold bars", ln)
        rewardXP(player, Skills.MINING, 15000.0)
        rewardXP(player, Skills.HERBLORE, 2000.0)
        addItemOrDrop(player, GOLD_BAR_2357, 2)
    }

    override fun newInstance(`object`: Any?): Quest {
        return this
    }

}