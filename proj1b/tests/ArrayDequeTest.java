import org.junit.jupiter.api.Test;


import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDequeTest {

    // @Test
    // @DisplayName("ArrayDeque has no fields besides backing array and primitives")
    // void noNonTrivialFields() {
    //     List<Field> badFields = Reflection.getFields(ArrayDeque.class)
    //             .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
    //             .toList();

    //     assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
    // }

    @Test
    public void testAddLast(){
        ArrayDeque<Integer> ad=new ArrayDeque<Integer>();
        ad.addLast(1);
        ad.addLast(2);
        ad.addLast(3);
    }

    @Test
    public void testAddFirst(){
        ArrayDeque<Integer> ad=new ArrayDeque<Integer>();
        ad.addFirst(3);
        ad.addFirst(2);
        ad.addFirst(1);
    }

    @Test
    public void testGet(){
        ArrayDeque<Integer> ad=new ArrayDeque<Integer>();
        ad.addLast(1);
        ad.addLast(2);
        ad.addLast(3);
        assertThat(ad.get(2)).isEqualTo(2);
    }

    @Test
    public void testSize(){
        ArrayDeque<Integer> ad=new ArrayDeque<Integer>();
        ad.addLast(1);
        ad.addLast(2);
        ad.addLast(3);
        assertThat(ad.size()).isEqualTo(3);
    }

    @Test
    public void testIsEmpty(){
        ArrayDeque<Integer> ad=new ArrayDeque<Integer>();
        assertThat(ad.isEmpty()).isEqualTo(true);
        ad.addLast(1);
        ad.addLast(2);
        ad.addLast(3);
        assertThat(ad.isEmpty()).isEqualTo(false);
    }

    @Test
    public void testToList(){
        ArrayDeque<Integer> ad=new ArrayDeque<Integer>();
        ad.addLast(1);
        ad.addLast(2);
        ad.addLast(3);
        assertThat(ad.toList()).containsExactly(1,2,3).inOrder();
    }

    @Test
    public void testRemoveFirst(){
        ArrayDeque<Integer> ad=new ArrayDeque<Integer>();
        ad.addLast(1);
        ad.addLast(2);
        ad.addLast(3);
        ad.removeFirst();
        assertThat(ad.toList()).containsExactly(2,3).inOrder();
    }
    @Test
    public void testRemoveLast(){
        ArrayDeque<Integer> ad=new ArrayDeque<Integer>();
        ad.addLast(1);
        ad.addLast(2);
        ad.addLast(3);
        ad.removeLast();
        assertThat(ad.toList()).containsExactly(1,2).inOrder();
    }



}
