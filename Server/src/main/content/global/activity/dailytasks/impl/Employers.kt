package content.global.activity.dailytasks.impl

import config.NPCs

/**
 * An enum of NPCs that can assign the player a job.
 *
 * TODO: Add the rest of the era-correct employers
 */
enum class Employers(val npcId: Int) {
    WOODCUTTING_TUTOR(NPCs.WOODCUTTING_TUTOR_4906),

    MAGIC_TUTOR(NPCs.MAGIC_TUTOR_4707),

    MELEE_TUTOR(NPCs.MELEE_TUTOR_705),

    RANGED_TUTOR(NPCs.RANGED_TUTOR_1861),

    COOKING_TUTOR(NPCs.COOKING_TUTOR_4899),

    CRAFTING_TUTOR(NPCs.CRAFTING_TUTOR_4900),

    FISHING_TUTOR(NPCs.FISHING_TUTOR_4901),

    MINING_TUTOR(NPCs.MINING_TUTOR_4902),

    SMELTING_TUTOR(NPCs.SMELTING_TUTOR_4904),

    PRAYER_TUTOR(NPCs.PRAYER_TUTOR_4903),

    HANS(NPCs.HANS_0),

    GILLIE_GROATS(NPCs.GILLIE_GROATS_3807),

    AGGIE(NPCs.AGGIE_922);
}