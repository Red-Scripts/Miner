package Miner;


import java.util.ArrayList;

import org.excobot.game.api.util.Random;


public class JobsHandler {

    private static ArrayList<Node> nodes = new ArrayList<Node>();

    public static Node[] nodes() {
        return nodes.toArray(new Node[nodes.size()]);
    }

    public static void addNode(Node node) {
        nodes.add(node);
    }

    public static int run() {
            for (Node node : nodes) {
                if (node != null && node.isActive()) {
                    node.run();
                }
            }
        return Random.nextInt(150, 200);
    }
   
}

	abstract class Node {
	
	public abstract void run();

	public abstract boolean isActive();
}

