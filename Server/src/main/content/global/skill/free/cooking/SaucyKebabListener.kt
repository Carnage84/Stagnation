package content.global.skill.free.cooking


import config.Items
import core.api.addItem
import core.api.removeItem
import core.game.interaction.IntType
import core.game.interaction.InteractionListener


/**
 * That's-a spicy kebab!
 */
class SaucyKebabListener : InteractionListener {
    val sauce = Items.RED_HOT_SAUCE_4610
    val kebabArr = intArrayOf(
        Items.KEBAB_1971,
        Items.UGTHANKI_KEBAB_1883,
        Items.UGTHANKI_KEBAB_1885
    )

    override fun defineListeners() {
        onUseWith(IntType.ITEM, kebabArr, sauce) { player, used, with ->
            if(removeItem(player, used.asItem()) && removeItem(player, with.asItem())) {
                return@onUseWith addItem(player, Items.SUPER_KEBAB_4608)
            }
            return@onUseWith false
        }
    }
}