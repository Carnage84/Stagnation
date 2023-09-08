package content.global.skill.member.agility.shortcuts;

import config.Items;
import core.cache.def.impl.SceneryDefinition;
import core.game.component.Component;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.impl.ForceMovement;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.node.entity.skill.Skills;
import core.game.node.item.Item;
import core.game.node.scenery.Scenery;
import core.game.node.scenery.SceneryBuilder;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Direction;
import core.game.world.map.Location;
import core.game.world.map.RegionManager;
import core.game.world.update.flag.context.Animation;
import core.game.world.update.flag.context.Graphics;
import core.plugin.Initializable;
import core.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static core.game.node.entity.impl.ForceMovement.WALK_ANIMATION;

/**
 * Handles the Karamja grapple shortcut.
 */
@Initializable
public class KaramjaGrapple extends OptionHandler {
    private static final HashMap<Integer, Integer> REQUIREMENTS = new HashMap<>();
    private static final String requirementsString;

    private static final List<Scenery> ROPES = new ArrayList<>(20);

    private static final Animation RUN_ANIMATION = new Animation(1995);

    static {
        REQUIREMENTS.putIfAbsent(Skills.AGILITY, 53);
        REQUIREMENTS.putIfAbsent(Skills.RANGE, 42);
        REQUIREMENTS.putIfAbsent(Skills.STRENGTH, 21);

        requirementsString = "You need at least "
                + REQUIREMENTS.get(Skills.AGILITY) + " " + Skills.SKILL_NAME[Skills.AGILITY] + ", "
                + REQUIREMENTS.get(Skills.RANGE) + " " + Skills.SKILL_NAME[Skills.RANGE] + ", and "
                + REQUIREMENTS.get(Skills.STRENGTH) + " " + Skills.SKILL_NAME[Skills.STRENGTH]
                + " to use this shortcut.";
    }

    private static final int[] CBOWS = new int[]{
            Items.MITH_CROSSBOW_9181,
            Items.ADAMANT_CROSSBOW_9183,
            Items.RUNE_CROSSBOW_9185,
            Items.DORGESHUUN_CBOW_8880
    };
    private static final Item MITH_GRAPPLE = new Item(9419);

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        SceneryDefinition.forId(17074).getHandlers().put("option:grapple", this);
        return this;
    }

    public void handleObjects(boolean add, final Player player) {
        Location current = player.getLocation();
        if (add){
            if(current.getY() > 3134){
                ROPES.add(new Scenery(1998, Location.create(2874, 3141, 0), 10, 0));
                ROPES.add(new Scenery(1998, Location.create(2874, 3140, 0), 10, 0));
                ROPES.add(new Scenery(1998, Location.create(2874, 3139, 0), 10, 0));
                ROPES.add(new Scenery(1998, Location.create(2874, 3138, 0), 10, 0));
                ROPES.add(new Scenery(1998, Location.create(2874, 3137, 0), 10, 0));
            }else{
                ROPES.add(new Scenery(1998, Location.create(2874, 3128, 0), 10, 0));
                ROPES.add(new Scenery(1998, Location.create(2874, 3129, 0), 10, 0));
                ROPES.add(new Scenery(1998, Location.create(2874, 3130, 0), 10, 0));
                ROPES.add(new Scenery(1998, Location.create(2874, 3131, 0), 10, 0));
                ROPES.add(new Scenery(1998, Location.create(2874, 3132, 0), 10, 0));
                ROPES.add(new Scenery(1998, Location.create(2874, 3133, 0), 10, 0));
            }
            for(Scenery rope : ROPES){
                SceneryBuilder.add(rope);
            }
        }else{
            for(Scenery rope : ROPES){ SceneryBuilder.remove(rope);
            }
            ROPES.clear();
        }
    }

    @Override
    public boolean handle(Player player, Node node, String option) {
        Location I;   // Start;
        Location II;  // Island;
        Location III; // Avoid character to run through the tree;
        Location IV;  // Avoid character to run through the tree;
        Location V;   // End;
        Location current = player.getLocation();
        Scenery startTree, endTree;
        Direction direction;
        if (current.getY() > 3134) { // starting at north side
            startTree = RegionManager.getObject(Location.create(2874, 3144, 0));
            endTree = RegionManager.getObject(Location.create(2873, 3125, 0));
            I = Location.create(2874, 3136);
            II = Location.create(2875, 3136);
            III = Location.create(2875, 3133);
            IV = Location.create(2874, 3133);
            V = Location.create(2874, 3127);
            direction = Direction.SOUTH;
        } else {
            startTree = RegionManager.getObject(Location.create(2873, 3125, 0));
            endTree = RegionManager.getObject(Location.create(2874, 3144, 0));
            I = Location.create(2874, 3133);
            II = Location.create(2875, 3133);
            III = Location.create(2875, 3136);
            IV = Location.create(2874, 3136);
            V = Location.create(2874, 3142);
            direction = Direction.NORTH;
        }
        Scenery islandTree = RegionManager.getObject(Location.create(2873, 3134, 0));

        switch (option) {
            case "grapple":
                for (Map.Entry<Integer, Integer> e : REQUIREMENTS.entrySet()) {
                    if (player.getSkills().getLevel(e.getKey()) < e.getValue()) {
                        player.getDialogueInterpreter().sendDialogue(requirementsString);
                        return true;
                    }
                }
                if (!player.getEquipment().containsAtLeastOneItem(CBOWS) || !player.getEquipment().containsItem(MITH_GRAPPLE)) {
                    player.getDialogueInterpreter().sendDialogue("You need a Mithril crossbow and a Mithril grapple in order to do this.");
                    return true;
                }
                player.lock();
                GameWorld.getPulser().submit(new Pulse(0, player) {
                    int counter = 1;
                    Component tab;

                    @Override
                    public boolean pulse() {
                        switch (counter++) {
                            case 1:
                                player.faceLocation(player.getLocation().transform(direction));
                                player.animate(new Animation(4230));
                                break;
                            case 3:
                                player.getPacketDispatch().sendPositionedGraphic(67, 10, 0, player.getLocation().transform(direction, 5)); //
                                break;
                            case 4:
                                SceneryBuilder.replace(startTree, startTree.transform(startTree.getId() + 1), 10);
                                SceneryBuilder.replace(islandTree, islandTree.transform(islandTree.getId() + 2), 10);
                                break;
                            case 5:
                                player.graphics(Graphics.create(68));
                                break;
                            case 6:
                                ForceMovement.run(player, player.getLocation(), I, Animation.create(4466), ForceMovement.RUNNING_SPEED);
                                handleObjects(true, player);
                                break;
                            case 12:
                                ForceMovement.run(player, player.getLocation(), II, WALK_ANIMATION, WALK_ANIMATION);
                                handleObjects(false, player);
                                break;
                            case 15:
                                ForceMovement.run(player, player.getLocation(), III, RUN_ANIMATION, ForceMovement.RUNNING_SPEED);
                                break;
                            case 18:
                                ForceMovement.run(player, player.getLocation(), IV, WALK_ANIMATION, ForceMovement.WALKING_SPEED);
                                break;
                            case 21:
                                player.faceLocation(player.getLocation().transform(direction));
                                player.animate(new Animation(4230));
                                break;
                            case 23:
                                player.getPacketDispatch().sendPositionedGraphic(67, 10, 0, player.getLocation().transform(direction, 5)); //
                                break;
                            case 24:
                                SceneryBuilder.replace(islandTree, islandTree.transform(islandTree.getId() + 1), 10);
                                SceneryBuilder.replace(endTree, endTree.transform(endTree.getId() + 1), 10);
                                SceneryBuilder.replace(startTree, startTree.transform(startTree.getId() + 2), 10);
                                break;
                            case 25:
                                player.graphics(Graphics.create(68));
                                break;
                            case 26:
                                ForceMovement.run(player, player.getLocation(), V, Animation.create(4466), ForceMovement.RUNNING_SPEED);
                                handleObjects(true, player);
                                break;
                            case 34:
                                player.unlock();
                                player.getAchievementDiaryManager().finishTask(player, DiaryType.KARAMJA, 2, 6);
                                handleObjects(false, player);
                                return true;
                        }
                        return false;
                    }
                });
                break;
        }
        return true;
    }

    @Override
    public Location getDestination(final Node moving, final Node destination) {
        // Run between tree and water before firing grapple
        if (moving.getLocation().getY() > 3134) { // starting at north side
            return Location.create(2874, 3142, 0);
        } else {
            return Location.create(2874, 3127, 0);
        }
    }
}