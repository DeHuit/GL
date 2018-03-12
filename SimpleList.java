import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SimpleList {

    private class Node {
        private Node next;
        private int info;

        public Node(Node next, int info) {
            this.next = next;
            this.info = info;
        }

        public int getInfo() {
            return info;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node n) {
            next = n;
        }
    }

    private Node head;
    private int length = 0;

    public SimpleList() {
        this.head = null;
    }

    public int length() {
        return length;
    }

    public void insert(int x) {
        Node cour = head;
        Node prec = null;
        boolean goAhead = (cour != null);

        //Recherche
        while (goAhead && cour != null) {
            if (cour.getInfo() >= x)
                goAhead = false;
            else {
                prec = cour;
                cour = cour.getNext();
            }
        }

        Node n = new Node(cour, x);
        if (prec == null) {
            head = n;
        } else {
            prec.setNext(n);
        }
        this.length++;


    }

    @Override
    public String toString() {
        String s = "[";
        Node cur = head;
        for (int i = 0; i < this.length; i++) {
            s += cur.info + ((i < this.length-1)?",":"");
            cur = cur.getNext();
        }
        s += "]";
        return s;
    }

    public void test0() {
        System.out.println(this);
    }

    public void test1() {
        insert(5);
        insert(1);
        insert(4);
        System.out.println(this);
    }

    public void read(String filename) {
        try {
            BufferedReader br = null;
            FileReader fr = null;

            //br = new BufferedReader(new FileReader(FILENAME));
            fr = new FileReader(filename);
            br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                for (String n : line.split(" ")) {
                    insert(Integer.parseInt(n));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("No file specified");
            return;
        }
        SimpleList s = new SimpleList();
        s.read(args[0]);
        System.out.println(s);
    }
}
