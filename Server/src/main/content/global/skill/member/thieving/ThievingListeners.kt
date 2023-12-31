package content.global.skill.member.thieving

import config.Items
import config.Sounds
import content.global.skill.skillcape.perks.SkillcapePerks
import core.api.*
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.node.entity.combat.ImpactHandler
import core.game.node.entity.impl.Animator
import core.game.node.entity.skill.Skills
import core.game.world.update.flag.context.Animation
import core.tools.RandomFunction

class ThievingListeners : InteractionListener {

    private val PICKPOCKET_ANIM = Animation(881,Animator.Priority.HIGH)
    private val NPC_ANIM = Animation(422)

    override fun defineListeners() {

        on(IntType.NPC,"pickpocket","pick-pocket"){ player, node ->
            val pickpocketData = Pickpockets.forID(node.id) ?: return@on false
            var successMod = 0.0

            if(player.inCombat()){
                sendMessage(player, "You can't pickpocket while in combat.")
                return@on true
            }

            if(player.skills.getLevel(Skills.THIEVING) < pickpocketData.requiredLevel){
                sendMessage(player, "You need a Thieving level of ${pickpocketData.requiredLevel} to do that.")
                return@on true
            }

            if(!pickpocketData.table.canRoll(player)){
                sendMessage(player, "You don't have enough inventory space to do that.")
                return@on true
            }

            if(SkillcapePerks.isActive(SkillcapePerks.SMOOTH_HANDS, player)) {
                successMod += 25
            }
            if (inEquipment(player, Items.GLOVES_OF_SILENCE_10075,1)){
                successMod += 3
            }

            player.animator.animate(PICKPOCKET_ANIM)
            val chance = RandomFunction.randomDouble(1.0,100.0)
            val failThreshold = pickpocketData.getSuccessChance(player) + successMod

            if(chance > failThreshold){
                node.asNpc().face(player)
                node.asNpc().animator.animate(NPC_ANIM)

                playHurtAudio(player, 20)

                stun(player, pickpocketData.stunTime)

                player.impactHandler.manualHit(node.asNpc(),RandomFunction.random(pickpocketData.stunDamageMin,pickpocketData.stunDamageMax),ImpactHandler.HitsplatType.NORMAL)

                node.asNpc().face(null)
            } else {
                playAudio(player, Sounds.PICK_2581)
                player.lock(2)
                pickpocketData.table.roll().forEach { player.inventory.add(it) }
                player.skills.addExperience(Skills.THIEVING,pickpocketData.experience)
            }

            return@on true
        }
    }
}
