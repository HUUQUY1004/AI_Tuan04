package lab4;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class Astar implements IInformedSearchAlgo {
	@Override
	public Node execute(Node root, String goal) {
		// TODO Auto-generated method stub
		PriorityQueue<Node> queue = new PriorityQueue<Node>(new NodeComparator());
		Set<Node> visited = new HashSet<Node>();
		queue.add(root);
		visited.add(root);
		while (!queue.isEmpty()) {
			Node current = queue.poll();
			if (current.getLabel().equals(goal)) {
				return current;
			}
			for (Edge e : current.getChildren()) {
				Node end = e.getEnd(); // A B
				double cost = e.getWeight();

				if (!visited.contains(end) && !queue.contains(end)) {
					end.setG(current.getG() + cost);
					end.setParent(current);
					queue.add(end);
					visited.add(end);
				}

				else if (queue.contains(end) && end.getG() > current.getG() + e.getWeight()) {
					end.setG(current.getG() + e.getWeight());
					end.setParent(current);

				}
//			
			}
		}
		return null;
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		// TODO Auto-generated method stub
		PriorityQueue<Node> queue = new PriorityQueue<Node>(new NodeComparator());
		Set<Node> visited = new HashSet<Node>();
		queue.add(root);
		while (!queue.isEmpty()) {
			Node current = queue.poll();
			if (current.getLabel().equals(goal)) {
				return current;
			} else {
				visited.add(current);

				for (Edge e : current.getChildren()) {
					Node node = e.getEnd();
					double cost = e.getWeight();
					if (!queue.contains(node) && !visited.contains(node)) {
						queue.add(node);
						node.setParent(current);
						node.setG(current.getG() + cost);
					}
				}
				for (Node node : current.getChildrenNodes()) {
					if (node.getLabel() == start) {
						node.setParent(null);
						queue.clear();
						visited.clear();
						queue.add(node);
						node.setH(node.getH() + current.getH());
					}
				}

			}
		}
		return null;
	}

	class NodeComparator implements Comparator<Node> {
		@Override
		public int compare(Node o1, Node o2) {
			// TODO Auto-generated method stub
			int re = Double.compare(o1.getG(), o2.getG());
			if (re == 0) {
				return o1.getLabel().compareTo(o2.getLabel());
			}
			return re;
		}

	}
	public boolean isAdmissibleHHelper(Node root,String goal, int h) {
		if(root.getLabel().equals(goal)) {
			return h==0;
		}
		int cost = 
		return true;
	}
	public boolean isAdmissibleH(Node root, String goal) {
		// helper pattern vs h= 0 từ thằng root
		return isAdmissibleHHelper(root, goal, 0);
		
	}
	
}
