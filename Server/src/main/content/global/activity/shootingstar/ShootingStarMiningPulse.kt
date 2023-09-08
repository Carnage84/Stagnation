package content.global.activity.shootingstar

import content.data.skill.SkillingTool
import core.api.*
import core.game.node.entity.player.Player
import core.game.node.entity.skill.SkillPulse
import core.game.node.entity.skill.Skills
import core.game.node.item.Item
import core.game.node.scenery.Scenery
import core.game.world.GameWorld

/**
 * The pulse used to handle mining shooting stars.
 */
class ShootingStarMiningPulse(player: Player?, node: Scenery?, val star: ShootingStar) : SkillPulse<Scenery?>(player, node) {
    /**
     * The amount of ticks it takes to get star dust.
     */
    private var ticks = 0
    override fun start() {
        if(!star.isSpawned) return

        if(!player.isArtificial) {
            star.notifyNewPlayer(player)
        }
        super.start()
    }

    override fun stop() {
        super.stop()

        if(!player.isArtificial){
            star.notifyPlayerLeave(player)
        }
    }

    override fun checkRequirements(): Boolean {
        tool = SkillingTool.getPickaxe(player)
        if (!star.starObject.isActive || !star.isSpawned) {
            return false
        }
        if (!star.isDiscovered && !player.isArtificial) {
            val bonusXp = 75 * getStatLevel(player,Skills.MINING)
            player.incrementAttribute("/save:shooting-star:bonus-xp", bonusXp)
            sendMessage(player,"You have ${player.skills.experienceMutiplier * getAttribute(player,"shooting-star:bonus-xp", 0).toDouble()} bonus xp towards mining stardust.")
            ShootingStarPlugin.submitScoreBoard(player)
            star.isDiscovered = true
            return player.skills.getLevel(Skills.MINING) >= star.miningLevel
        }
        
        if (getStatLevel(player,Skills.MINING) < star.miningLevel) {
            sendDialogue(player,"You need a Mining level of at least " + star.miningLevel + " in order to mine this layer.")
            return false
        }
        if (tool == null) {
            sendMessage(player,"You do not have a pickaxe to use.")
            return false
        }
        if (freeSlots(player) < 1 && !inInventory(player, ShootingStarPlugin.STAR_DUST, 1)) {
            sendDialogue(player,"Your inventory is too full to hold any more stardust.")
            return false
        }
        return true
    }

    override fun animate() {
        animate(player, tool.animation)
    }

    override fun reward(): Boolean {
        if (++ticks % 4 != 0) {
            return false
        }
        if (!checkReward()) {
            return false
        }
        if (GameWorld.settings?.isDevMode == true) {
            star.dustLeft = 0
        }
        star.decDust()

        val bonusXp = player.getAttribute("shooting-star:bonus-xp", 0).toDouble()
        var xp = star.level.exp.toDouble()
        if(bonusXp > 0) {
            val delta = Math.min(bonusXp, xp)
            player.incrementAttribute("/save:shooting-star:bonus-xp", (-delta).toInt())
            xp += delta;
            if(player.getAttribute("shooting-star:bonus-xp", 0) <= 0) {
                sendMessage(player, "You have obtained all of your bonus xp from the star.")
            }
        }

        player.skills.addExperience(Skills.MINING, xp)
        if (ShootingStarPlugin.getStarDust(player) < 200) {
            player.inventory.add(Item(ShootingStarPlugin.STAR_DUST, 1))
        }
        return false
    }

    override fun message(type: Int) {
        when (type) {
            0 -> sendMessage(player,"You swing your pickaxe at the rock...")
        }
    }

    private fun checkReward(): Boolean {
        val skill = Skills.MINING
        val level = 1 + getStatLevel(player,skill) + player.familiarManager.getBoost(skill)
        val hostRatio: Double = Math.random() * (100.0 * star.level.rate)
        val clientRatio: Double = Math.random() * ((level - star.miningLevel) * (1.0 + tool.ratio))
        return hostRatio < clientRatio
    }
}
