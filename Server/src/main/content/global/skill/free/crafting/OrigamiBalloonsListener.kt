package content.global.skill.free.crafting

import config.Animations
import config.Items
import core.api.*
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.node.entity.impl.Projectile
import core.game.node.entity.skill.Skills
import core.game.system.task.Pulse
import core.game.world.map.Direction

/**
 * Represents the crafting listener for origami balloons.
 */
class OrigamiBalloonsListener : InteractionListener {
    companion object {

        // Balloons
        private const val BALLOON_STRUCTURE = Items.BALLOON_STRUCTURE_9933
        private const val BASIC_BALLOON     = Items.ORIGAMI_BALLOON_9934

        val BALLOONS = intArrayOf(
            Items.ORIGAMI_BALLOON_9934,
            Items.YELLOW_BALLOON_9935,
            Items.BLUE_BALLOON_9936,
            Items.RED_BALLOON_9937,
            Items.ORANGE_BALLOON_9938,
            Items.GREEN_BALLOON_9939,
            Items.PURPLE_BALLOON_9940,
            Items.PINK_BALLOON_9941,
            Items.BLACK_BALLOON_9942
        )

        // Dyes
        private val DYES = intArrayOf(
            Items.YELLOW_DYE_1765,
            Items.BLUE_DYE_1767,
            Items.RED_DYE_1763,
            Items.ORANGE_DYE_1769,
            Items.GREEN_DYE_1771,
            Items.PURPLE_DYE_1773,
            Items.PINK_DYE_6955,
            Items.BLACK_MUSHROOM_INK_4622
        )

        // Ingredients
        private val CANDLES = intArrayOf(
            Items.CANDLE_36,
            Items.BLACK_CANDLE_38
        )

        private const val PAPYRUS      = Items.PAPYRUS_970
        private const val BALL_OF_WOOL = Items.BALL_OF_WOOL_1759

        // Animations
        private const val CRAFTING_ANIMATION = Animations.HUMAN_CRAFT_ORIGAMI_BALLOON_5140
        private const val FLYING_ANIMATION   = Animations.HUMAN_RELEASE_A_BALLOON_5142
    }

    private fun getGfxForBalloon(id: Int): Int {
        return when (id){
            Items.ORIGAMI_BALLOON_9934 -> 881
            Items.YELLOW_BALLOON_9935  -> 884
            Items.BLUE_BALLOON_9936    -> 887
            Items.RED_BALLOON_9937     -> 890
            Items.ORANGE_BALLOON_9938  -> 893
            Items.GREEN_BALLOON_9939   -> 896
            Items.PURPLE_BALLOON_9940  -> 899
            Items.PINK_BALLOON_9941    -> 902
            Items.BLACK_BALLOON_9942   -> 905
            else -> 881
        }
    }

    private fun matchDyeWithBalloon(id: Int) : Int {
        return when(id){
            Items.RED_DYE_1763            -> Items.RED_BALLOON_9937
            Items.YELLOW_DYE_1765         -> Items.YELLOW_BALLOON_9935
            Items.BLUE_DYE_1767           -> Items.BLUE_BALLOON_9936
            Items.ORANGE_DYE_1769         -> Items.ORANGE_BALLOON_9938
            Items.GREEN_DYE_1771          -> Items.GREEN_BALLOON_9939
            Items.PURPLE_DYE_1773         -> Items.PURPLE_BALLOON_9940
            Items.BLACK_MUSHROOM_INK_4622 -> Items.BLACK_BALLOON_9942
            Items.PINK_DYE_6955           -> Items.PINK_BALLOON_9941
            else -> Items.ORIGAMI_BALLOON_9934
        }
    }

    override fun defineListeners() {

        // Creating a balloon structure
        onUseWith(IntType.ITEM, PAPYRUS, BALL_OF_WOOL) { player, _, _ ->
            if (getQuestStage(player, "Enlightened Journey") < 1) {
                sendMessage(player, "You need start the Enlightened Journey quest in order to make this.")
            } else if (removeItem(player, PAPYRUS) && removeItem(player, BALL_OF_WOOL)) {
                sendMessage(player, "You create the origami balloon structure.")
                animate(player, CRAFTING_ANIMATION)
                addItemOrDrop(player, BALLOON_STRUCTURE)
            }
            return@onUseWith true
        }

        // Making a balloon
        onUseWith(IntType.ITEM, CANDLES, BALLOON_STRUCTURE) { player, used, _ ->
            if (removeItem(player, used.asItem()) && removeItem(player, BALLOON_STRUCTURE)) {
                sendMessage(player, "You create the origami balloon.")
                animate(player, CRAFTING_ANIMATION)
                addItemOrDrop(player, BASIC_BALLOON)
                rewardXP(player, Skills.CRAFTING, 35.00)
            }
            return@onUseWith true
        }

        // Optional staining
        onUseWith(IntType.ITEM, DYES, BASIC_BALLOON) { player, used, _ ->
            if (removeItem(player, BASIC_BALLOON)) {
                for (i in DYES) if (removeItem(player, used.id)) {
                    addItemOrDrop(player, matchDyeWithBalloon(used.id))
                }
            }
            return@onUseWith true
        }

        // Lighting an balloon
        onUseWith(IntType.ITEM, BALLOONS, Items.TINDERBOX_590){ player, used, _ ->
            if(!finishedMoving(player)){
                sendMessage(player, "You can't do that while you're moving.")
            } else if(removeItem(player, used.asItem())) {
                lock(player, 3)
                sendMessage(player, "You light the origami balloon.")
                rewardXP(player, Skills.FIREMAKING, 20.00)
                submitIndividualPulse(player, object : Pulse() {
                    var counter = 0
                    override fun pulse(): Boolean {
                        when (counter++) {
                            0 -> for (i in BALLOONS)
                                visualize(
                                    player,
                                    FLYING_ANIMATION,
                                    getGfxForBalloon(used.id).dec()
                                )
                            2 -> for (i in BALLOONS)
                                Projectile.create(
                                    player, null,
                                    getGfxForBalloon(used.id),
                                    45,
                                    45,
                                    1,
                                    70,
                                    0
                                ).transform(
                                    player,
                                    player.location.transform(
                                        Direction.get(player.direction.toInteger()),
                                        player.direction.ordinal + 1
                                    ),
                                    false,
                                    70,
                                    140
                                ).send()
                            }
                        return false
                    }
                })
            }
            return@onUseWith true
        }
    }
}

