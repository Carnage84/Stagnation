package content.minigame.gnomeball

import config.Animations
import config.Items
import config.NPCs
import core.api.*
import core.game.global.action.EquipHandler
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.game.node.item.Item
import core.game.system.task.Pulse
import core.game.world.update.flag.context.Animation

private const val BALL = Items.GNOMEBALL_751
private val throwAnim = Animation(3353)
private val proj = PassGnomeBallHandler.BallProjectile
private val wingerA = NPC(NPCs.GNOME_WINGER_634)
private val wingerB = NPC(NPCs.GNOME_WINGER_633)
class PassGnomeBallHandler : InteractionListener {

    override fun defineListeners() {
        on(IntType.PLAYER, "pass") { player, node ->
            val catcher = node.asPlayer()

            if(!inInventory(player, BALL)) return@on false
            if(!hasHandsFree(catcher)) {
                sendMessage(player, "This player seems to have their hands full!")
                return@on false
            }
            PlayerPlayerPassPulse(player, catcher)
            return@on true
        }

        on(wingerA.id, IntType.NPC, "pass") { player, node ->
            val catcher = node.asNpc()
            setAttribute(catcher, "gnomeball:in-pass", true)
            setAttribute(catcher, "gnomeball:pass-player", player)
            PlayerWingerPassPulse(player, catcher)
            return@on true
        }
    }

    internal class PlayerPlayerPassPulse(private val thrower: Player, private val catcher: Player) : Pulse() {
        override fun pulse(): Boolean {
            thrower.face(catcher)
            if(removeItem(thrower, BALL)) {
                animate(catcher, throwAnim)
                spawnProjectile(
                    thrower.location,
                    catcher.location,
                    proj.id,
                    proj.startHeight,
                    proj.endHeight,
                    0,
                    proj.speed,
                    catcher.index - 1
                )
                sendMessage(thrower, "You throw your gnomeball...")
                EquipHandler.handleEquip(catcher, Item(BALL))
                sendMessage(thrower, "${catcher.name} manages to get it.")
                sendMessage(catcher, "You caught ${thrower.name}'s gnomeball!")
            }
            return true
        }
    }

    internal class PlayerWingerPassPulse(private val thrower: Player, private val winger: NPC) : Pulse() {
        override fun pulse(): Boolean {
            thrower.face(winger)
            if(removeItem(thrower, BALL)) {
                animate(winger, throwAnim)
                spawnProjectile(
                    thrower.location,
                    winger.location,
                    proj.id,
                    proj.startHeight,
                    proj.endHeight,
                    0,
                    proj.speed,
                    10
                )
                transformNpc(winger, wingerB.id, 10)
                winger.face(thrower)
                animate(winger, Animations.BALLER_THROW_201)
                spawnProjectile(
                    winger.location,
                    thrower.location,
                    proj.id,
                    proj.endHeight,
                    proj.startHeight,
                    0,
                    proj.speed,
                    10
                )
            }
            return true
        }
    }

    object BallProjectile {
        const val id = 55
        const val startHeight = 43
        const val endHeight = 40
        const val speed = 70
    }
}