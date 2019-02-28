 package lab09;

 import java.io.*;
 import java.util.Arrays;


 class Edge implements Comparable<Edge>{
     int from;
     int to;
     int weight;
     int through;

     public Edge (int from, int to, int weight){
         this.from = from;
         this.to = to;
         this.weight = weight;
         this.through = 0;
     }

     @Override
     public int compareTo(Edge o) {
         return this.weight - o.weight;
     }
 }

 class Node {
     int index;
     int head;
     int inset;

     public Node(int index){
         this.index = index;
         this.head = index;
         this.inset = 0;
     }
 }

 class Graph {
     int numNodes;
     int numSets;
     int sizeEdges;
     int[] x;
     int[] y;
     Node[] nodes;
     Edge[] edges;
     int [] rank;

     public Graph (int numNodes, int numSets){
         this.sizeEdges = 0;
         this.numNodes = numNodes;
         this.numSets = numSets;
         nodes = new Node[numNodes];
         edges = new Edge[numNodes * (numNodes - 1) / 2];
         rank = new int[numNodes];
         x = new int[numNodes];
         y = new int[numNodes];
     }

     public void addNode (qI.Reader r){
         try {
             for (int i = 0; i < numNodes; ++i) {
                 nodes[i] = new Node(i);
                 x[i] = r.nextInt();
                 y[i] = r.nextInt();
             }
         } catch (IOException e){
             e.printStackTrace();
         }
     }

     public void initialGraph (){
 //        int count = 0;
         for (int i = 0; i < numNodes; ++ i){
             for (int j = i + 1; j < numNodes; ++ j){
                 edges[sizeEdges] = new Edge(i, j, (x[i] - x[j]) * (x[i] - x[j]) + (y[i]- y[j]) * (y[i] - y[j]));
                 sizeEdges ++;
             }
         }
 //        System.out.println(count + " " + edges.length);
     }

     public int findHead (int index){
         if (index == nodes[index].head){
             return index;
         } else {
             return nodes[index].head = findHead(nodes[index].head);
         }
     }

     public void mergeSet (int x, int y){
         x = findHead(x); y = findHead(y);

         if (x != y) {
             if (rank[x] > rank[y]) {
                 nodes[y].head = x;
             } else {
                 if (rank[x] == rank[y]){
                     rank[y] ++;
                 }
                 nodes[x].head = y;
             }
         }
     }

     public long kruskal () {
         long result = 0;

         for (int i = 0; i < edges.length; ++i) {
             if (edges[i].through == 0) {
                 int n1 = edges[i].from;
                 int n2 = edges[i].to;

                 int set1 = findHead(n1);
                 int set2 = findHead(n2);

                 if (set1 != set2) {
                     mergeSet(set1, set2);
                     result += edges[i].weight;
                     edges[i].through = 1;
                 }
             }
         }

         return result;
     }

     public long kruskal2 () {
         long result = 0;

         for (int i = 0; i < edges.length; ++i) {
             if (edges[i].through == 1) {
                 int n1 = edges[i].from;
                 int n2 = edges[i].to;

                 int set1 = findHead(n1);
                 int set2 = findHead(n2);

                 if (set1 != set2) {
                     mergeSet(set1, set2);
                     result += edges[i].weight;
                 }
             }
         }

         return result;
     }
// 遍历吴培霖是用递归写的
//     public int get_mst (){
//         int result = 0;
//          for (int i = 0; i < edges.length; ++i) {
//                 int n1 = edges[i].from;
//                 int n2 = edges[i].to;
//
//                 int set1 = findHead(n1);
//                 int set2 = findHead(n2);
//
//                 if (set1 != set2) {
//                     mergeSet(set1, set2);
//                     if ((nodes[n1].inset == 0 && nodes[n2].inset == 0)) {
//                         result += edges[i].weight;
//                         edges[i].through = 1;
//                     }
//                 }
//         }
//         return result;
//     }

//     public int[] getPreviousHead (int [] arr){
//         for (int i = 0; i < edges.length; ++i){
//             if (edges[i].through == 1) {
//                 int n1 = edges[i].from;
//                 int n2 = edges[i].to;
//
//                 int set1 = findHead(n1);
//                 int set2 = findHead(n2);
//
//                 if (set1 != set2) {
//                     mergeSet(set1, set2);
//                 }
//             }
//         }
//
//         for (int i = 0; i < numNodes; ++ i){
//             arr[i] = nodes[i].head;
//         }
//         return arr;
//     }

     public void work(qI.Reader r, int k){
//         Arrays.sort(edges);
//         long min = 200000000;
//         long result;
//         long keep;
//         int bound = (int)Math.pow(2, k);
         int[][] set = new int[numSets][];
//         int[] previous = new int[numNodes];
         try {
             for (int i = 0; i < numSets; ++i) {
                 int size = r.nextInt();
                 set[i] = new int[size + 1];
                 set[i][0] = r.nextInt();
                 for (int i1 = 1; i1 < size + 1; ++i1) {
                     int ind = r.nextInt() - 1;
                     set[i][i1] = ind;
                     nodes[ind].inset = 1;
                 }
//                 System.out.println(set[i][0]);
             }
         } catch (IOException e){
             e.printStackTrace();
         }
//
//         // get what edge should go through
//         keep = result = get_mst();
//         for (int i1 = 0; i1 < numNodes; ++ i1){
//             nodes[i1].head = i1;
//         }
//         previous = getPreviousHead(previous);
         Arrays.sort(edges);
         long min = 200000000;
         long result = 0;
         int bound = (int)Math.pow(2, k);

         for (int i = 0; i < bound; ++ i){
             if (i % 2 == 1){
                 for (int j = 1; j < set[0].length - 1; ++j) {
                     mergeSet(set[0][j], set[0][j + 1]);
                 }
                 result += set[0][0];
             }

             if ((i / 2) % 2 == 1){
                 for (int j = 1; j < set[1].length - 1; ++j) {
                     mergeSet(set[1][j], set[1][j + 1]);
                 }
                 result += set[1][0];
             }

             if ((i / 4) % 2 == 1){
                 for (int j = 1; j < set[2].length - 1; ++j) {
                     mergeSet(set[2][j], set[2][j + 1]);
                 }
                 result += set[2][0];
             }

             if ((i / 8) % 2 == 1){
                 for (int j = 1; j < set[3].length - 1; ++j) {
                     mergeSet(set[3][j], set[3][j + 1]);
                 }
                 result += set[3][0];
             }

             if ((i / 16) % 2 == 1){
                 for (int j = 1; j < set[4].length - 1; ++j) {
                     mergeSet(set[4][j], set[4][j + 1]);
                 }
                 result += set[4][0];
             }

             if ((i / 32) % 2 == 1){
                 for (int j = 1; j < set[5].length - 1; ++j) {
                     mergeSet(set[5][j], set[5][j + 1]);
                 }
                 result += set[5][0];
             }

             if ((i / 64) % 2 == 1){
                 for (int j = 1; j < set[6].length - 1; ++j) {
                     mergeSet(set[6][j], set[6][j + 1]);
                 }
                 result += set[6][0];
             }

             if ((i / 128) % 2 == 1){
                 for (int j = 1; j < set[7].length - 1; ++j) {
                     mergeSet(set[7][j], set[7][j + 1]);
                 }
                 result += set[7][0];
             }
             if (i == 0) {
                 result += kruskal();

             } else {
                 result += kruskal2();
             }

             if (result < min){
                 min = result;
             }
//             System.out.println(result);

             for (int i1 = 0; i1 < numNodes; ++ i1){
                 nodes[i1].head = i1;
             }
//             result = keep;
             result = 0;
         }

         System.out.println(min);
     }

 }

 public class qI {
     static class Reader
     {
         final private int BUFFER_SIZE = 1 << 16;
         private DataInputStream din;
         private byte[] buffer;
         private int bufferPointer, bytesRead;

         public Reader()
         {
             din = new DataInputStream(System.in);
             buffer = new byte[BUFFER_SIZE];
             bufferPointer = bytesRead = 0;
         }

         public Reader(String file_name) throws IOException
         {
             din = new DataInputStream(new FileInputStream(file_name));
             buffer = new byte[BUFFER_SIZE];
             bufferPointer = bytesRead = 0;
         }

         public String readLine() throws IOException
         {
             byte[] buf = new byte[64]; // line length
             int cnt = 0, c;
             while ((c = read()) != -1)
             {
                 if (c == '\n')
                     break;
                 buf[cnt++] = (byte) c;
             }
             return new String(buf, 0, cnt);
         }

         public int nextInt() throws IOException
         {
             int ret = 0;
             byte c = read();
             while (c <= ' ')
                 c = read();
             boolean neg = (c == '-');
             if (neg)
                 c = read();
             do
             {
                 ret = ret * 10 + c - '0';
             }  while ((c = read()) >= '0' && c <= '9');

             if (neg)
                 return -ret;
             return ret;
         }

         public long nextLong() throws IOException
         {
             long ret = 0;
             byte c = read();
             while (c <= ' ')
                 c = read();
             boolean neg = (c == '-');
             if (neg)
                 c = read();
             do {
                 ret = ret * 10 + c - '0';
             }
             while ((c = read()) >= '0' && c <= '9');
             if (neg)
                 return -ret;
             return ret;
         }

         public double nextDouble() throws IOException
         {
             double ret = 0, div = 1;
             byte c = read();
             while (c <= ' ')
                 c = read();
             boolean neg = (c == '-');
             if (neg)
                 c = read();

             do {
                 ret = ret * 10 + c - '0';
             }
             while ((c = read()) >= '0' && c <= '9');

             if (c == '.')
             {
                 while ((c = read()) >= '0' && c <= '9')
                 {
                     ret += (c - '0') / (div *= 10);
                 }
             }

             if (neg)
                 return -ret;
             return ret;
         }

         private void fillBuffer() throws IOException
         {
             bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
             if (bytesRead == -1)
                 buffer[0] = -1;
         }

         private byte read() throws IOException
         {
             if (bufferPointer == bytesRead)
                 fillBuffer();
             return buffer[bufferPointer++];
         }

         public void close() throws IOException
         {
             if (din == null)
                 return;
             din.close();
         }
     }
     public static void main(String[] args) {
         try {
             Reader sc = new Reader();
             int numCases = sc.nextInt();
             for (; numCases > 0; numCases--) {
                 int numNodes = sc.nextInt();
                 int numSets = sc.nextInt();
                 Graph g = new Graph(numNodes, numSets);
                 g.addNode(sc);
                 g.initialGraph();

                 g.work(sc, numSets);
             }
         } catch (IOException e){
             e.printStackTrace();
         }
     }
 }
