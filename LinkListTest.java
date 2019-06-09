import org.junit.Test;
import org.junit.Assert.*;


public class LinkListTest {

    LinkList<Integer> list = new LinkList<Integer>();

    @Test(expected = NullPointerException.class)
    public void findLastTest() throws Exception {
        System.out.println(list.findLast());
    }

    @Test
    public void appendTest() throws Exception {
        list.append(5);
        System.out.println(list.getLast());
    }

    @Test
    public void appendTest3() {
        list.append(10);
        list.append(30000);
        list.append(98741340);

        System.out.println(list.getLast());
    }

    @Test
    public void getTest() {
        list.append(1);
        list.append(3);
        list.append(7);
        list.append(2);

        System.out.println(list.get(2).getData());

        //list.printList();
    }

    @Test
    public void deleteTest() {
        list.append(1);
        list.append(3);
        list.append(7);
        list.append(2);

        list.printList();
        System.out.println(list.getLength());

        list.delete(0);
        //System.out.println(list.get(0));

        list.printList();
        System.out.println(list.getLength());

        list.delete(2);

        list.printList();
        System.out.println(list.getLength());

    }
}