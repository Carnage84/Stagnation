package content.global.activity.shootingstar

import core.api.addItemOrDrop
import core.api.inInventory
import core.cache.ServerStore
import core.cache.ServerStore.Companion.getBoolean
import core.game.dialogue.DialoguePlugin
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.game.node.item.Item
import core.plugin.Initializable
import org.json.simple.JSONObject

/**
 * Dialogue for the star sprite.
 */
@Initializable
class StarSpriteDialogue(player: Player? = null) : DialoguePlugin(player) {

    val COSMIC_RUNE = 564
    val ASTRAL_RUNE = 9075
    val GOLD_ORE = 445
    val COINS = 995
    val AMPLIFIER = 1.0

    fun StarSpriteDialogue() {}

    override fun open(vararg args: Any): Boolean {
        npc = args[0] as NPC
        if (getStoreFile().getBoolean(player.username.toLowerCase()) || !inInventory(player, ShootingStarPlugin.STAR_DUST, 1)) {
            npc("Hello, strange creature.").also { stage = 0 }
        } else {
            npc("Thank you for helping me out of here.").also { stage = 50 }
        }
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            0 -> npc("I'm a star sprite! I Was in my star in the sky, when it", "lost control and crashed into the ground. With half my", "star sticking into the ground, I became stuck.", "Fortunately, I was mined out by the kind creatures of").also { stage++ }
            1 -> npc("your race.").also { stage++ }
            2 -> options("What's a star sprite?", "What are you going to do without your star?", "I thought stars were huge balls of burning gas.", "Well, I'm glad you're okay.").also { stage++ }
            3 -> when (buttonId) {
                1 -> player("What's a star sprite?").also { stage = 10 }
                2 -> player("What are you going to do without your star?").also { stage = 20 }
                3 -> player("I thought stars were huge balls of burning gas.").also { stage = 30 }
                4 -> player("Well, I'm glad you're okay.").also { stage = 40 }
            }
            10 -> npc("We're what makes the stars in the sky shine. I made", "this star shine when it was in the sky.").also { stage++ }
            11 -> options("What are you going to do without your star?", "I thought stars were huge balls of burning gas.", "Well, I'm glad you're okay.").also { stage++ }
            12 -> when (buttonId) {
                1 -> player("What are you going to do without your star?").also { stage = 20 }
                2 -> player("I thought stars were huge balls of burning gas.").also { stage = 30 }
                3 -> player("Well, I'm glad you're okay.").also { stage = 40 }
            }
            20 -> npc("Don't worry about me. I'm sure I'll find some good", "rocks around here and get back up into the sky in no", "time.").also { stage++ }
            21 -> options("What's a star sprite?", "I thought stars were huge balls of burning gas.", "Well, I'm glad you're okay.").also { stage++ }
            22 -> when (buttonId) {
                1 -> player("What's a star sprite?").also { stage = 10 }
                2 -> player("I thought stars were huge balls of burning gas.").also { stage = 30 }
                3 -> player("Well, I'm glad you're okay.").also { stage = 40 }
            }
            30 -> npc("Most of them are, but a lot of shooting stars on this", "plane of the multiverse are rocks with star sprites in", "them.").also { stage++ }
            31 -> options("What's a star sprite?", "What are you going to do without your star?", "Well, I'm glad you're okay.").also { stage++ }
            32 -> when (buttonId) {
                1 -> player("What's a star sprite?").also { stage = 10 }
                2 -> player("What are you going to do without your star?").also { stage = 20 }
                3 -> player("Well, I'm glad you're okay.").also { stage = 40 }
            }
            40 -> npc("Thank you.").also { stage++ }
            41 -> end()
            50 -> {
                val dust = if (player.inventory.getAmount(ShootingStarPlugin.STAR_DUST) > 200) 200 else player.getInventory().getAmount(ShootingStarPlugin.STAR_DUST)
                if (player.inventory.remove(Item(ShootingStarPlugin.STAR_DUST, dust))) {
                    val cosmicRunes = (Math.ceil(0.76 * dust) * AMPLIFIER).toInt()
                    val astralRunes = (Math.ceil(0.26 * dust) * AMPLIFIER).toInt()
                    val goldOre = (Math.ceil(0.1 * dust) * AMPLIFIER).toInt()
                    val coins = (Math.ceil(250.0 * dust) * AMPLIFIER).toInt()
                    addItemOrDrop(player, COSMIC_RUNE, cosmicRunes)
                    addItemOrDrop(player, ASTRAL_RUNE, astralRunes)
                    addItemOrDrop(player, GOLD_ORE, goldOre)
                    addItemOrDrop(player, COINS, coins)
                    npc(
                        "I have rewarded you by making it so you can mine",
                        "extra ore for the next 15 minutes. Also, have $cosmicRunes",
                        "cosmic runes, $astralRunes astral runes, $goldOre gold ore and $coins",
                        "coins."
                    )
                    getStoreFile()[player.username.toLowerCase()] = true
                    player.registerState("shooting-star")?.init()
                }
                stage = 52
            }

            52 -> end()
        }

        return true
    }

    override fun getIds(): IntArray? {
        return intArrayOf(8091)
    }


    fun getStoreFile(): JSONObject{
        return ServerStore.getArchive("daily-shooting-star")
    }

    override fun newInstance(player: Player?): DialoguePlugin? {
        return StarSpriteDialogue(player)
    }
}
