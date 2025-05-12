/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataStructuresTest.List;



/**
 *
 * @author Alejandro Zamora
 */
import com.datastructures.IList;
import com.datastructures.SinglyLinkedList;
import com.datastructures.exception.EmptyStructureException;
import com.datastructures.exception.InvalidElementException;
import com.datastructures.exception.IndexOutOfBoundsCustomException;
// JUnit 5 specific imports. These are for the testing framework, not the data structure itself.
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the SinglyLinkedList class using custom exceptions.
 */
class SinglyLinkedListTest {

    private IList<String> list;
    private IList<Integer> intList;

    @BeforeEach
    void setUp() {
        list = new SinglyLinkedList<>();
        intList = new SinglyLinkedList<>();
    }

    @Test
    @DisplayName("New list should be empty")
    void newLinkedListIsEmpty() {
        assertTrue(list.isEmpty(), "Newly created list should be empty.");
        assertEquals(0, list.getSize(), "Newly created list should have size 0.");
        assertNull(list.getHeadNode(), "Head of new list should be null.");
        assertNull(list.getTailNode(), "Tail of new list should be null.");
    }
    
    @Test
    @DisplayName("Adding null data should throw InvalidElementException")
    void addNullDataShouldThrowException() {
        assertThrows(InvalidElementException.class, () -> list.addFirst(null), "addFirst(null) should throw InvalidElementException.");
        assertThrows(InvalidElementException.class, () -> list.addLast(null), "addLast(null) should throw InvalidElementException.");
        assertThrows(InvalidElementException.class, () -> list.addAtIndex(0, null), "addAtIndex(0, null) should throw InvalidElementException.");
    }

    @Test
    @DisplayName("addFirst should add element to the beginning")
    void addFirst() {
        list.addFirst("A");
        assertEquals(1, list.getSize());
        assertEquals("A", list.get(0));
        assertEquals("A", list.getHeadNode().getData());
        assertEquals("A", list.getTailNode().getData());

        list.addFirst("B");
        assertEquals(2, list.getSize());
        assertEquals("B", list.get(0));
        assertEquals("A", list.get(1));
        assertEquals("B", list.getHeadNode().getData());
        assertEquals("A", list.getTailNode().getData());
    }

    @Test
    @DisplayName("addLast should add element to the end")
    void addLast() {
        list.addLast("A");
        assertEquals(1, list.getSize());
        assertEquals("A", list.get(0));
        assertEquals("A", list.getHeadNode().getData());
        assertEquals("A", list.getTailNode().getData());

        list.addLast("B");
        assertEquals(2, list.getSize());
        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
        assertEquals("A", list.getHeadNode().getData());
        assertEquals("B", list.getTailNode().getData());
    }
    
    @Test
    @DisplayName("addAtIndex should insert element correctly")
    void addAtIndex() {
        list.addAtIndex(0, "A"); // -> [A]
        assertEquals(1, list.getSize());
        assertEquals("A", list.get(0));

        list.addAtIndex(0, "B"); // -> [B, A]
        assertEquals(2, list.getSize());
        assertEquals("B", list.get(0));
        assertEquals("A", list.get(1));

        list.addAtIndex(2, "C"); // -> [B, A, C] (index 2 is end of list size 2)
        assertEquals(3, list.getSize());
        assertEquals("C", list.get(2));
        assertEquals("C", list.getTailNode().getData());


        list.addAtIndex(1, "D"); // -> [B, D, A, C]
        assertEquals(4, list.getSize());
        assertEquals("B", list.get(0));
        assertEquals("D", list.get(1));
        assertEquals("A", list.get(2));
        assertEquals("C", list.get(3));
        
        assertEquals("B", list.getHeadNode().getData());
        assertEquals("C", list.getTailNode().getData());
    }

    @Test
    @DisplayName("addAtIndex should throw IndexOutOfBoundsCustomException for invalid index")
    void addAtIndexOutOfBounds() {
        assertThrows(IndexOutOfBoundsCustomException.class, () -> list.addAtIndex(-1, "Error"));
        // Empty list, index 1 is > size (0). addAtIndex allows index == size.
        assertThrows(IndexOutOfBoundsCustomException.class, () -> list.addAtIndex(1, "Error")); 
        list.addLast("A"); // List: [A], size 1
        // Index 2 is > size (1).
        assertThrows(IndexOutOfBoundsCustomException.class, () -> list.addAtIndex(2, "Error")); 
    }
    
    @Test
    @DisplayName("removeFirst from empty list should throw EmptyStructureException")
    void removeFirstFromEmptyList() {
        assertThrows(EmptyStructureException.class, () -> list.removeFirst());
    }

    @Test
    @DisplayName("removeFirst should remove and return the first element")
    void removeFirst() {
        list.addLast("A"); list.addLast("B"); list.addLast("C"); // [A, B, C]

        assertEquals("A", list.removeFirst()); // [B, C]
        assertEquals(2, list.getSize());
        assertEquals("B", list.get(0));
        assertEquals("B", list.getHeadNode().getData());
        assertEquals("C", list.getTailNode().getData());

        assertEquals("B", list.removeFirst()); // [C]
        assertEquals(1, list.getSize());
        assertEquals("C", list.get(0));
        assertEquals("C", list.getHeadNode().getData());
        assertEquals("C", list.getTailNode().getData());
        assertSame(list.getHeadNode(), list.getTailNode());

        assertEquals("C", list.removeFirst()); // []
        assertTrue(list.isEmpty());
        assertNull(list.getHeadNode());
        assertNull(list.getTailNode());
    }
    
    @Test
    @DisplayName("removeLast from empty list should throw EmptyStructureException")
    void removeLastFromEmptyList() {
        assertThrows(EmptyStructureException.class, () -> list.removeLast());
    }

    @Test
    @DisplayName("removeLast should remove and return the last element")
    void removeLast() {
        list.addLast("A"); list.addLast("B"); list.addLast("C"); // [A, B, C]

        assertEquals("C", list.removeLast()); // [A, B]
        assertEquals(2, list.getSize());
        assertEquals("B", list.get(1));
        assertEquals("A", list.getHeadNode().getData());
        assertEquals("B", list.getTailNode().getData());
        assertNull(list.getTailNode().getNext());

        assertEquals("B", list.removeLast()); // [A]
        assertEquals(1, list.getSize());
        assertEquals("A", list.get(0));
        assertEquals("A", list.getHeadNode().getData());
        assertEquals("A", list.getTailNode().getData());
        assertNull(list.getTailNode().getNext());
        assertSame(list.getHeadNode(), list.getTailNode());


        assertEquals("A", list.removeLast()); // []
        assertTrue(list.isEmpty());
        assertNull(list.getHeadNode());
        assertNull(list.getTailNode());
    }

    @Test
    @DisplayName("removeAtIndex from empty list should throw IndexOutOfBoundsCustomException")
    void removeAtIndexFromEmptyList() {
         assertThrows(IndexOutOfBoundsCustomException.class, () -> list.removeAtIndex(0));
    }
    
    @Test
    @DisplayName("removeAtIndex should throw IndexOutOfBoundsCustomException for invalid index")
    void removeAtIndexOutOfBounds() {
        list.addLast("A"); // List: [A]
        assertThrows(IndexOutOfBoundsCustomException.class, () -> list.removeAtIndex(-1));
        assertThrows(IndexOutOfBoundsCustomException.class, () -> list.removeAtIndex(1)); // Index 1 >= size (1)
    }

    @Test
    @DisplayName("removeAtIndex should remove and return element at specified index")
    void removeAtIndex() {
        list.addLast("A"); list.addLast("B"); list.addLast("C"); list.addLast("D"); // [A, B, C, D]

        assertEquals("B", list.removeAtIndex(1)); // [A, C, D]
        assertEquals(3, list.getSize());
        assertEquals("A", list.get(0));
        assertEquals("C", list.get(1));
        assertEquals("D", list.get(2));
        assertEquals("A", list.getHeadNode().getData());
        assertEquals("D", list.getTailNode().getData());


        assertEquals("D", list.removeAtIndex(2)); // [A, C] (removing tail via index)
        assertEquals(2, list.getSize());
        assertEquals("A", list.get(0));
        assertEquals("C", list.get(1));
        assertEquals("A", list.getHeadNode().getData());
        assertEquals("C", list.getTailNode().getData());
        assertNull(list.getTailNode().getNext());


        assertEquals("A", list.removeAtIndex(0)); // [C] (removing head via index)
        assertEquals(1, list.getSize());
        assertEquals("C", list.get(0));
        assertEquals("C", list.getHeadNode().getData());
        assertEquals("C", list.getTailNode().getData());
        assertSame(list.getHeadNode(), list.getTailNode());

        assertEquals("C", list.removeAtIndex(0)); // [] (removing last element via index)
        assertTrue(list.isEmpty());
        assertNull(list.getHeadNode());
        assertNull(list.getTailNode());
    }
    
    @Test
    @DisplayName("get from empty list should throw IndexOutOfBoundsCustomException")
    void getFromEmptyList() {
        assertThrows(IndexOutOfBoundsCustomException.class, () -> list.get(0));
    }

    @Test
    @DisplayName("get should throw IndexOutOfBoundsCustomException for invalid index")
    void getOutOfBounds() {
        list.addLast("A"); // List: [A]
        assertThrows(IndexOutOfBoundsCustomException.class, () -> list.get(-1));
        assertThrows(IndexOutOfBoundsCustomException.class, () -> list.get(1)); // Index 1 >= size (1)
    }

    @Test
    @DisplayName("get should return correct element")
    void get() {
        list.addLast("A"); list.addLast("B"); list.addLast("C");
        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
        assertEquals("C", list.get(2));
    }
    
    @Test
    @DisplayName("set on empty list should throw IndexOutOfBoundsCustomException")
    void setOnEmptyList() {
        assertThrows(IndexOutOfBoundsCustomException.class, () -> list.set(0, "X"));
    }

    @Test
    @DisplayName("set should throw IndexOutOfBoundsCustomException for invalid index")
    void setOutOfBounds() {
        list.addLast("A"); // List: [A]
        assertThrows(IndexOutOfBoundsCustomException.class, () -> list.set(-1, "X"));
        assertThrows(IndexOutOfBoundsCustomException.class, () -> list.set(1, "X")); // Index 1 >= size (1)
    }
    
    @Test
    @DisplayName("set null data should throw InvalidElementException")
    void setNullDataShouldThrowException() {
        list.addLast("A");
        assertThrows(InvalidElementException.class, () -> list.set(0, null));
    }

    @Test
    @DisplayName("set should update element at specified index")
    void set() {
        list.addLast("A"); list.addLast("B"); list.addLast("C"); // [A, B, C]

        list.set(1, "X"); // [A, X, C]
        assertEquals(3, list.getSize());
        assertEquals("A", list.get(0));
        assertEquals("X", list.get(1));
        assertEquals("C", list.get(2));
        
        list.set(0, "Y"); // [Y, X, C]
         assertEquals("Y", list.get(0));
         assertEquals("Y", list.getHeadNode().getData());


        list.set(2, "Z"); // [Y, X, Z]
        assertEquals("Z", list.get(2));
        assertEquals("Z", list.getTailNode().getData());
    }

    @Test
    @DisplayName("contains should work correctly")
    void contains() {
        assertFalse(list.contains("A"));
        list.addLast("A"); list.addLast("B"); list.addLast("C");

        assertTrue(list.contains("A"));
        assertTrue(list.contains("B"));
        assertTrue(list.contains("C"));
        assertFalse(list.contains("D"));
        assertFalse(list.contains(null)); // Nulls are disallowed
    }
    
    @Test
    @DisplayName("clear should empty the list")
    void clear() {
        list.addLast("A"); list.addLast("B");
        assertFalse(list.isEmpty());
        list.clear();
        assertTrue(list.isEmpty());
        assertEquals(0, list.getSize());
        assertNull(list.getHeadNode());
        assertNull(list.getTailNode());
        
        // Test adding after clear
        list.addFirst("D"); 
        assertEquals(1, list.getSize());
        assertEquals("D", list.get(0));
        assertEquals("D", list.getHeadNode().getData());
        assertEquals("D", list.getTailNode().getData());
    }

    @Test
    @DisplayName("toString should return correct string representation")
    void testToString() {
        assertEquals("[]", list.toString().split(" \\(Size:")[0]); // Check structural part
        assertEquals("[] (Size: 0)", list.toString());


        list.addLast("A");
        assertEquals("[A] (Size: 1)", list.toString());
        list.addLast("B");
        assertEquals("[A -> B] (Size: 2)", list.toString());
        
        intList.addLast(1); intList.addLast(2); intList.addLast(3);
        assertEquals("[1 -> 2 -> 3] (Size: 3)", intList.toString());
    }
    
    @Test
    @DisplayName("Operations on list with one element should correctly update head and tail")
    void operationsOnSingleElementList() {
        intList.addFirst(10); // [10]
        assertEquals(1, intList.getSize());
        assertEquals(10, intList.get(0));
        assertSame(intList.getHeadNode(), intList.getTailNode());

        // Set
        intList.set(0, 20); // [20]
        assertEquals(20, intList.get(0));
        assertSame(intList.getHeadNode(), intList.getTailNode());


        // Remove First
        assertEquals(20, intList.removeFirst()); // []
        assertTrue(intList.isEmpty());
        assertNull(intList.getHeadNode());
        assertNull(intList.getTailNode());


        // Re-add and Remove Last
        intList.addFirst(30); // [30]
        assertEquals(30, intList.removeLast()); // []
        assertTrue(intList.isEmpty());
        assertNull(intList.getHeadNode());
        assertNull(intList.getTailNode());

        
        // Re-add and Remove At Index 0
        intList.addFirst(40); // [40]
        assertEquals(40, intList.removeAtIndex(0)); // []
        assertTrue(intList.isEmpty());
        assertNull(intList.getHeadNode());
        assertNull(intList.getTailNode());
    }
}