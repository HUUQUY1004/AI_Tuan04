package lab4;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;




public class GreedySearch implements IInformedSearchAlgo {

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
				Node end = e.getEnd();
				double cost = e.getWeight();

				if (!visited.contains(end) && !queue.contains(end)) {
					end.setH(cost + current.getH());
					end.setParent(current);
					queue.add(end);
					visited.add(end);
				}

				else if (queue.contains(end) && end.getH() > current.getH() + e.getWeight()) {
					end.setH(cost + current.getH());
					end.setParent(current);
//					queue.remove(end);
//					queue.add(end);

				}
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
				List<Edge> children = current.getChildren();
				for (Edge chill : children) {
					Node n = chill.getEnd();
					double t = (current.getH() + chill.getWeight());
					if (!queue.contains(n) && !visited.contains(n)) {
						queue.add(n);
						n.setParent(current);
						n.setH(t);
					} 
				}
				for (Node node :current.getChildrenNodes()) {
					if (node.getLabel() == start) {
						node.setParent(null);
						queue.clear();
						visited.clear();
						queue.add(node);
						node.setH(node.getH()+current.getH());
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
			int re = Double.compare(o1.getH(), o2.getH());
			if (re == 0) {
				return o1.getLabel().compareTo(o2.getLabel());
			}
			return re;
		}

	}
}
