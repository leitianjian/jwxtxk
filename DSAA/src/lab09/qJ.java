//package lab09;
//
//import java.io.DataInputStream;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.ArrayList;
//
//class Edge {
//    int from;
//    int to;
//
//    Edge (int from, int to){
//        this.from = from;
//        this.to = to;
//    }
//}
//
//class Node {
//    int index;
//    int head;
//    int rank;
//    ArrayList<Integer> pointVEdge;
//    int type;
//
//    Node (int index){
//        this.index = index;
//        this.head = index;
//        this.rank = 1;
//        this.pointVEdge = new ArrayList<>();
//        this.type = 0;
//    }
//}
//
//class Graph {
//    int numPerson;
//    Node[] nodes;
//    ArrayList<Edge> wEdges;
//    ArrayList<Edge> vEdges;
//
//    Graph (int numPerson){
//        this.numPerson = numPerson;
//        this.nodes = new Node[numPerson];
//        this.wEdges = new ArrayList<>();
//        this.vEdges = new ArrayList<>();// type = 0 v ; type = 1 w
//
//        for (int i = 0; i < numPerson; ++ i){
//            nodes[i] = new Node(i);
//        }
//    }
//
//    void addEdge (int x, int y, int type){
//        if (type == 1){
//            wEdges.add(new Edge(x, y));
//        } else {
//            vEdges.add(new Edge(x, y));
//            nodes[y].pointVEdge.add(x);
//        }
//    }
//
//         public int findHead (int index){
//         if (index == nodes[index].head){
//             return index;
//         } else {
//             return nodes[index].head = findHead(nodes[index].head);
//         }
//     }
//
//     public void mergeSet (int x, int y){
//         x = findHead(x); y = findHead(y);
//
//         if (x != y) {
//             if (nodes[x].rank > nodes[y].rank) {
//                 nodes[y].head = x;
//             } else {
//                 if (nodes[x].rank == nodes[y].rank){
//                     nodes[y].rank ++;
//                 }
//                 nodes[x].head = y;
//             }
//         }
//     }
//     public void kruskal () {
//         for (int i = 0; i < vEdges.size(); ++i) {
//             int n1 = vEdges.get(i).from;
//             int n2 = vEdges.get(i).to;
//
//             int set1 = findHead(n1);
//             int set2 = findHead(n2);
//
//             if (set1 != set2) {
//                 mergeSet(set1, set2);
//             }
//         }
//     }
//
//     public void getWolf (){
//        int count = 0;
//        int count2 = 0;
//        kruskal();
//        for (int i = 0; i < wEdges.size(); ++ i){
//            int n1 = wEdges.get(i).from;
//            int n2 = wEdges.get(i).to;
//            int set1 = findHead(n1);
//            int set2 = findHead(n2);
//
//            if (set1 == set2 || n1 == n2){
//               nodes[n2].type = 1;
//                ++ count;
//                count += find(n2);
//            }
//        }
//
//        for (int i = 0; i < numPerson; ++ i){
//            if (nodes[i].type == 1){
//                count2 ++;
//            }
//        }
//
//         System.out.println(count2);
//
//     }
//
//     public int find (int index){
//        int count = 0;
//        for (int i : nodes[index].pointVEdge){
//            nodes[i].type = 1;
//            count += nodes[index].pointVEdge.size();
//            count += find(i);
//        }
//        return count;
//     }
//}
//
//public class qJ {
//    public static void main(String[] args) {
//        Reader sc = new Reader();
//        try {
//            int numCases = sc.nextInt();
//            for (; numCases > 0; -- numCases){
//                int numPerson = sc.nextInt();
//                Graph g = new Graph (numPerson);
//
//                for (int i = 0; i < numPerson; ++ i){
//                    int y = sc.nextInt() - 1;
//                    int type;
//                    if (sc.readLine().charAt(0) == 'w'){
//                        type = 1;
//                    } else {
//                        type = 0;
//                    }
//                    g.addEdge(i, y, type);
//                }
//                g.getWolf();
//            }
//
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//    }
//       static class Reader
//    {
//        final private int BUFFER_SIZE = 1 << 16;
//        private DataInputStream din;
//        private byte[] buffer;
//        private int bufferPointer, bytesRead;
//
//        public Reader()
//        {
//            din = new DataInputStream(System.in);
//            buffer = new byte[BUFFER_SIZE];
//            bufferPointer = bytesRead = 0;
//        }
//
//        public Reader(String file_name) throws IOException
//        {
//            din = new DataInputStream(new FileInputStream(file_name));
//            buffer = new byte[BUFFER_SIZE];
//            bufferPointer = bytesRead = 0;
//        }
//
//        public String readLine() throws IOException
//        {
//            byte[] buf = new byte[64]; // line length
//            int cnt = 0, c;
//            while ((c = read()) != -1)
//            {
//                if (c == '\n')
//                    break;
//                buf[cnt++] = (byte) c;
//            }
//            return new String(buf, 0, cnt);
//        }
//
//        public int nextInt() throws IOException
//        {
//            int ret = 0;
//            byte c = read();
//            while (c <= ' ')
//                c = read();
//            boolean neg = (c == '-');
//            if (neg)
//                c = read();
//            do
//            {
//                ret = ret * 10 + c - '0';
//            }  while ((c = read()) >= '0' && c <= '9');
//
//            if (neg)
//                return -ret;
//            return ret;
//        }
//
//        public long nextLong() throws IOException
//        {
//            long ret = 0;
//            byte c = read();
//            while (c <= ' ')
//                c = read();
//            boolean neg = (c == '-');
//            if (neg)
//                c = read();
//            do {
//                ret = ret * 10 + c - '0';
//            }
//            while ((c = read()) >= '0' && c <= '9');
//            if (neg)
//                return -ret;
//            return ret;
//        }
//
//        public double nextDouble() throws IOException
//        {
//            double ret = 0, div = 1;
//            byte c = read();
//            while (c <= ' ')
//                c = read();
//            boolean neg = (c == '-');
//            if (neg)
//                c = read();
//
//            do {
//                ret = ret * 10 + c - '0';
//            }
//            while ((c = read()) >= '0' && c <= '9');
//
//            if (c == '.')
//            {
//                while ((c = read()) >= '0' && c <= '9')
//                {
//                    ret += (c - '0') / (div *= 10);
//                }
//            }
//
//            if (neg)
//                return -ret;
//            return ret;
//        }
//
//        private void fillBuffer() throws IOException
//        {
//            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
//            if (bytesRead == -1)
//                buffer[0] = -1;
//        }
//
//        private byte read() throws IOException
//        {
//            if (bufferPointer == bytesRead)
//                fillBuffer();
//            return buffer[bufferPointer++];
//        }
//
//        public void close() throws IOException
//        {
//            if (din == null)
//                return;
//            din.close();
//        }
//    }
//}
