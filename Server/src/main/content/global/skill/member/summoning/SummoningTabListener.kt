package content.global.skill.member.summoning

import content.global.skill.member.summoning.familiar.BurdenBeast
import content.global.skill.member.summoning.familiar.FamiliarSpecial
import core.game.interaction.InterfaceListener

class SummoningTabListener : InterfaceListener {
    override fun defineInterfaceListeners() {
        on(662) { player, _, opcode, buttonID, _, _ ->
            when(buttonID) {
                51 -> {
                    if (player.getFamiliarManager().hasFamiliar()) {
                        player.getFamiliarManager().getFamiliar().call()
                    } else {
                        player.getPacketDispatch().sendMessage("You don't have a follower.")
                    }
                }
                67 -> {
                    if (player.getFamiliarManager().hasFamiliar()) {
                        if (!player.getFamiliarManager().getFamiliar().isBurdenBeast()) {
                            player.getPacketDispatch().sendMessage("Your familiar is not a beast of burden.")
                            return@on true
                        }
                        val beast = player.getFamiliarManager().getFamiliar() as BurdenBeast;
                        if (beast.getContainer().isEmpty()) {
                            player.getPacketDispatch().sendMessage("Your familiar is not carrying any items.")
                            return@on true
                        }
                        beast.withdrawAll();
                        return@on true
                    }
                    player.getPacketDispatch().sendMessage("You don't have a follower.");
                }
                53 -> {
                    if (player.getFamiliarManager().hasFamiliar()) {
                        if(opcode == 155) {
                            // Dismiss familiar
                            player.getDialogueInterpreter().open("dismiss_dial");
                        } else if(opcode == 196) {
                            // Dismiss now
                            player.getFamiliarManager().dismiss(false);
                        }
                    } else {
                        player.getPacketDispatch().sendMessage("You don't have a follower.");
                    }
                }
                else -> {
                    if (player.getFamiliarManager().hasFamiliar()) {
                        player.getFamiliarManager().getFamiliar().executeSpecialMove(FamiliarSpecial(player));
                    } else {
                        player.getPacketDispatch().sendMessage("You don't have a follower.");
                    }
                }
            }
            return@on true
        }
    }
}

