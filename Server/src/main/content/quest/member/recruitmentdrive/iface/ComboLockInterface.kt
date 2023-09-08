package content.quest.member.recruitmentdrive.iface

import core.api.*
import core.game.interaction.InterfaceListener
import core.game.world.map.Location

/**
 * Handles Combination Lock interface for Recruitment Drive quest.
 */
class ComboLockInterface : InterfaceListener {

    override fun defineInterfaceListeners() {
        val LETTERONEBACK = 10
        val LETTERONEFORWARD = 11
        val LETTERTWOBACK = 12
        val LETTERTWOFORWARD = 13
        val LETTERTHREEBACK = 14
        val LETTERTHREEFORWARD = 15
        val LETTERFOURBACK = 16
        val LETTERFOURFORWARD = 17
        val ENTER = 18
        val DOORLOCKINTERFACE = 285
        val LETTERS = arrayOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
        )

        onOpen(DOORLOCKINTERFACE) { player, component ->
            setAttribute(player,"recruitmentdrive-letter-one", 0)
            setAttribute(player,"recruitmentdrive-letter-two", 0)
            setAttribute(player,"recruitmentdrive-letter-three", 0)
            setAttribute(player,"recruitmentdrive-letter-four", 0)
            return@onOpen true
        }

        onClose(DOORLOCKINTERFACE) { player, component ->
            removeAttribute(player, "recruitmentdrive-letter-one")
            removeAttribute(player, "recruitmentdrive-letter-two")
            removeAttribute(player, "recruitmentdrive-letter-three")
            removeAttribute(player, "recruitmentdrive-letter-four")
            return@onClose true
        }

        on(DOORLOCKINTERFACE) { player, component, opcode, buttonID, slot, itemID ->
            when (buttonID) {
                LETTERONEBACK -> {
                    if (getAttribute(player, "recruitmentdrive-letter-one", 0) == 0) {
                        setAttribute(player,"recruitmentdrive-letter-one", 25)
                        setInterfaceText(player, LETTERS[getAttribute(player, "recruitmentdrive-letter-one", 0)], DOORLOCKINTERFACE, 6)
                    } else {
                        (player.incrementAttribute("recruitmentdrive-letter-one", -1))
                        setInterfaceText(player, LETTERS[getAttribute(player, "recruitmentdrive-letter-one", 0)], DOORLOCKINTERFACE, 6)
                    }
                }

                LETTERONEFORWARD -> {
                    if (getAttribute(player, "recruitmentdrive-letter-one", 0) == 25) {
                        setAttribute(player,"recruitmentdrive-letter-one", 0)
                        setInterfaceText(player, LETTERS[getAttribute(player, "recruitmentdrive-letter-one", 0)], DOORLOCKINTERFACE, 6)
                    } else {
                        (player.incrementAttribute("recruitmentdrive-letter-one", 1))
                        setInterfaceText(player, LETTERS[getAttribute(player, "recruitmentdrive-letter-one", 0)], DOORLOCKINTERFACE, 6)
                    }
                }

                LETTERTWOBACK -> {
                    if (getAttribute(player, "recruitmentdrive-letter-two", 0) == 0) {
                        setAttribute(player,"recruitmentdrive-letter-two", 25)
                        setInterfaceText(player, LETTERS[getAttribute(player, "recruitmentdrive-letter-two", 0)], DOORLOCKINTERFACE, 7)
                    } else {
                        (player.incrementAttribute("recruitmentdrive-letter-two", -1))
                        setInterfaceText(player, LETTERS[getAttribute(player, "recruitmentdrive-letter-two", 0)], DOORLOCKINTERFACE, 7)
                    }
                }

                LETTERTWOFORWARD -> {
                    if (getAttribute(player, "recruitmentdrive-letter-two", 0) == 25) {
                        setAttribute(player,"recruitmentdrive-letter-two", 0)
                        setInterfaceText(player, LETTERS[getAttribute(player, "recruitmentdrive-letter-two", 0)], DOORLOCKINTERFACE, 7)
                    } else {
                        (player.incrementAttribute("recruitmentdrive-letter-two", 1))
                        setInterfaceText(player, LETTERS[getAttribute(player, "recruitmentdrive-letter-two", 0)], DOORLOCKINTERFACE, 7)
                    }
                }

                LETTERTHREEBACK -> {
                    if (getAttribute(player, "recruitmentdrive-letter-three", 0) == 0) {
                        setAttribute(player,"recruitmentdrive-letter-three", 25)
                        setInterfaceText(player, LETTERS[getAttribute(player, "recruitmentdrive-letter-three", 0)], DOORLOCKINTERFACE, 8)
                    } else {
                        (player.incrementAttribute("recruitmentdrive-letter-three", -1))
                        setInterfaceText(player, LETTERS[getAttribute(player, "recruitmentdrive-letter-three", 0)], DOORLOCKINTERFACE, 8)
                    }
                }

                LETTERTHREEFORWARD -> {
                    if (getAttribute(player, "recruitmentdrive-letter-three", 0) == 25) {
                        setAttribute(player,"recruitmentdrive-letter-three", 0)
                        setInterfaceText(player, LETTERS[getAttribute(player, "recruitmentdrive-letter-three", 0)], DOORLOCKINTERFACE, 8)
                    } else {
                        (player.incrementAttribute("recruitmentdrive-letter-three", 1))
                        setInterfaceText(player, LETTERS[getAttribute(player, "recruitmentdrive-letter-three", 0)], DOORLOCKINTERFACE, 8)
                    }
                }

                LETTERFOURBACK -> {
                    if (getAttribute(player, "recruitmentdrive-letter-four", 0) == 0) {
                        setAttribute(player,"recruitmentdrive-letter-four", 25)
                        setInterfaceText(player, LETTERS[getAttribute(player, "recruitmentdrive-letter-four", 0)], DOORLOCKINTERFACE, 9)
                    } else {
                        (player.incrementAttribute("recruitmentdrive-letter-four", -1))
                        setInterfaceText(player, LETTERS[getAttribute(player, "recruitmentdrive-letter-four", 0)], DOORLOCKINTERFACE, 9)
                    }
                }

                LETTERFOURFORWARD -> {
                    if (getAttribute(player, "recruitmentdrive-letter-four", 0) == 25) {
                        setAttribute(player,"recruitmentdrive-letter-four", 0)
                        setInterfaceText(player, LETTERS[getAttribute(player, "recruitmentdrive-letter-four", 0)], DOORLOCKINTERFACE, 9)
                    } else {
                        (player.incrementAttribute("recruitmentdrive-letter-four", 1))
                        setInterfaceText(player, LETTERS[getAttribute(player, "recruitmentdrive-letter-four", 0)], DOORLOCKINTERFACE, 9)
                    }
                }

                ENTER -> {
                    val letterOne = LETTERS[getAttribute(player, "recruitmentdrive-letter-one", 0)]
                    val letterTwo = LETTERS[getAttribute(player, "recruitmentdrive-letter-two", 0)]
                    val letterThree = LETTERS[getAttribute(player, "recruitmentdrive-letter-three", 0)]
                    val letterFour = LETTERS[getAttribute(player, "recruitmentdrive-letter-four", 0)]
                    when (getAttribute(player, "ComboLock", 0)) {
                        1 -> if (letterOne == "F" && letterTwo == "I" && letterThree == "S" && letterFour == "H") {
                            setQuestStage(player, "Recruitment Drive", 35)
                            removeAttribute(player, "ComboLock")
                            sendNPCDialogue(player, 2287, "Your wit is sharp, your brains quite clear; You solved my puzzle with no fear. At puzzles I rank you quite the best, now enter the portal for your next test.")
                            player.faceLocation(location(2443, 4956, 0))
                            player.interfaceManager.close()
                        } else {
                            player.interfaceManager.close()
                            sendNPCDialogue(player, 2287, "It's sad to say, this test beat you. I'll send you to Tiffy, what to do?")
                            runTask(player, 3) { teleport(player, Location.create(2996, 3375, 0)) }
                        }
                        2 -> if (letterOne == "B" && letterTwo == "I" && letterThree == "T" && letterFour == "E") {
                            setQuestStage(player, "Recruitment Drive", 35)
                            removeAttribute(player, "ComboLock")
                            sendNPCDialogue(player, 2287, "Your wit is sharp, your brains quite clear; You solved my puzzle with no fear. At puzzles I rank you quite the best, now enter the portal for your next test.")
                            player.faceLocation(location(2443, 4956, 0))
                            player.interfaceManager.close()
                        } else {
                            player.interfaceManager.close()
                            sendNPCDialogue(player, 2287, "It's sad to say, this test beat you. I'll send you to Tiffy, what to do?")
                            runTask(player, 3) { teleport(player, Location.create(2996, 3375, 0)) }
                        }
                        3 -> if (letterOne == "L" && letterTwo == "A" && letterThree == "S" && letterFour == "T") {
                            setQuestStage(player, "Recruitment Drive", 35)
                            removeAttribute(player, "ComboLock")
                            sendNPCDialogue(player, 2287, "Your wit is sharp, your brains quite clear; You solved my puzzle with no fear. At puzzles I rank you quite the best, now enter the portal for your next test.")
                            player.faceLocation(location(2443, 4956, 0))
                            player.interfaceManager.close()
                        } else {
                            player.interfaceManager.close()
                            sendNPCDialogue(player, 2287, "It's sad to say, this test beat you. I'll send you to Tiffy, what to do?")
                            runTask(player, 3) { teleport(player, Location.create(2996, 3375, 0)) }
                        }
                        4 -> if (letterOne == "M" && letterTwo == "E" && letterThree == "A" && letterFour == "T") {
                            setQuestStage(player, "Recruitment Drive", 35)
                            removeAttribute(player, "ComboLock")
                            sendNPCDialogue(player, 2287, "Your wit is sharp, your brains quite clear; You solved my puzzle with no fear. At puzzles I rank you quite the best, now enter the portal for your next test.")
                            player.faceLocation(location(2443, 4956, 0))
                            player.interfaceManager.close()
                        } else {
                            player.interfaceManager.close()
                            sendNPCDialogue(player, 2287, "It's sad to say, this test beat you. I'll send you to Tiffy, what to do?")
                            runTask(player, 3) { teleport(player, Location.create(2996, 3375, 0)) }
                        }
                        5 -> if (letterOne == "R" && letterTwo == "A" && letterThree == "I" && letterFour == "N") {
                            setQuestStage(player, "Recruitment Drive", 35)
                            removeAttribute(player, "ComboLock")
                            sendNPCDialogue(player, 2287, "Your wit is sharp, your brains quite clear; You solved my puzzle with no fear. At puzzles I rank you quite the best, now enter the portal for your next test.")
                            player.faceLocation(location(2443, 4956, 0))
                            player.interfaceManager.close()
                        } else {
                            player.interfaceManager.close()
                            sendNPCDialogue(player, 2287, "It's sad to say, this test beat you. I'll send you to Tiffy, what to do?")
                            runTask(player, 3) { teleport(player, Location.create(2996, 3375, 0)) }
                        }
                        6 -> if (letterOne == "T" && letterTwo == "I" && letterThree == "M" && letterFour == "E") {
                            setQuestStage(player, "Recruitment Drive", 35)
                            removeAttribute(player, "ComboLock")
                            sendNPCDialogue(player, 2287, "Your wit is sharp, your brains quite clear; You solved my puzzle with no fear. At puzzles I rank you quite the best, now enter the portal for your next test.")
                            player.faceLocation(location(2443, 4956, 0))
                            player.interfaceManager.close()
                        } else {
                            player.interfaceManager.close()
                            sendNPCDialogue(player, 2287, "It's sad to say, this test beat you. I'll send you to Tiffy, what to do?")
                            runTask(player, 3) { teleport(player, Location.create(2996, 3375, 0)) }
                        }
                    }
                }
            }
            return@on true
        }
    }
}