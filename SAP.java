import java.long;
public class SAP {
	private Digraph G;
	private int length;
	private int ancestor;
   // constructor takes a digraph (not necessarily a DAG)
   
	private class Node implements Comparable<Node>{
		private int length;
		private int id;
		
		Node(int length, int id){
			this.length = length;
			this.id = id;
		}
		
		public int compareTo(Node n){
			if(length > n.length) return 1;
			else if(length == n.length) return 0;
			else return -1;
		}
	}
   public SAP(Digraph G){
	   if(G == null) throw new NullPointerException();
	   this.G = G;
   }

   // length of shortest ancestral path between v and w; -1 if no such path
   public int length(int v, int w){
		if(v < 0 || v > G.V() || w < 0 || w > G.V()) throw new IndexOutOfBoundsException();
		MinPQ<Node> possibleNodes = new MinPQ();
		boolean[] marked = new boolean[this.G.V()];
		marked[v] = true;
		int[] pathV = new int[G.V()];
		int[] pathW = new int[G.V()];
		for(int i; i < G.V(); i++){
			pathV[i] = -1;
			pathW[i] = -1;
		}
		pathV[v] = 0;
		pathW[w] = 0;
		
		Queue<Integer> queue = new Queue<>();
		queue.enqueue(v);
		while(!queue.isEmpty()){
			int vertex = queue.dequeue();
			for(int nextVertex : G.adj(vertex)){
				if(!marked[nextVertex]){
					marked[nextVertex] = true;
					queue.enqueue(nextVertex);
					if(pathV[nextVertex] = -1 || pathV[nextVertex] > pathV[vertex] + 1){
						pathV[nextVertex] = pathV[vertex] + 1;
					}
				}
			}
		}
		
		marked = new boolean[this.G.V()];
		marked[w] = true;
		queue.enqueue(w);
		while(!queue.isEmpty()){
			int vertex = queue.dequeue();
			Node node = new Node(pathV[vertex] + pathW[vertex], vertex);
			possibleNodes.insert(node);
		}
		for(int nextVertex : G.adj(vertex)){
			if(!marked[nextVertex]){
				marked[nextVertex] = true;
				queue.enqueue(nextVertex);
				if(pathW[nextVertex] = -1 || pathW[nextVertex] > pathW[vertex] + 1){
					pathW[nextVertex] = pathW[vertex] + 1;	
				}
			}
		}
		if(possibleNodes.size > 0){
			Node node = possibleNodes.delMin();
			this.length = Node.length;
			this.ancestor = Node.id;
		}
		else{
			this.length = -1;
			this.ancestor = -1;
		}
   }

   // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
   public int ancestor(int v, int w){
		length(v, w);
		return this.ancestor;
   }

   // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
   public int length(Iterable<Integer> v, Iterable<Integer> w){
		if(v == null || w == null) throw new NullPointerException();
		MinPQ<Node> possibleNodes = new MinPQ();
		for(nodeV : v){
			if(nodeV < 0 || nodeV > this.G.V()) throw new IndexOutOfBoundsException();
			for(nodeW : w){
				if(nodeW < 0 || nodeW > this.G.V()) throw new IndexOutOfBoundsException();
				Node node = new Node(length(nodeV, nodeW), ancestor(nodeV, nodeW));
				possibleNodes.insert(node);
			}
		}
		if(possibleNodes.size > 0){
			Node node = possibleNodes.delMin();
			this.length = node.length;
			this.ancestor = node.id;
		}
		else{
			this.length = -1;
			this.ancestor = -1;
		}
   }

   // a common ancestor that participates in shortest ancestral path; -1 if no such path
   public int ancestor(Iterable<Integer> v, Iterable<Integer> w){
	   length(v, w);
	   return this.ancestor;
   }

   // do unit testing of this class
   public static void main(String[] args){
	   
   }
}
