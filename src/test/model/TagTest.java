package model;

import model.exceptions.EmptyStringException;
import model.exceptions.NullArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TagTest {
    // TODO: design tests for new behaviour added to Tag class
    Tag t;
    Tag z;
    Tag e;
    Task k;
    Tag g;

    @BeforeEach
    public void setup(){
        t = new Tag("cool");
        z = new Tag("cool");
        e = new Tag("lame");
        k = new Task("dab ## swag; today; in progress; important");

    }

    @Test
    public void testForAddNullTask(){
        try {
            t.addTask(null);
            System.out.println("shouldnt run");
        } catch (NullArgumentException n){
            System.out.println("runs as expected");
        }

    }

    @Test
    public void nullTag() {
        try {
            g = new Tag(null);
        }
        catch (EmptyStringException e) {
            System.out.println("dab");
        }
    }

    @Test
    public void testForRemoveNullTask(){
        try {
            t.removeTask(null);
            System.out.println("shouldnt run");

        } catch (NullArgumentException n) {
            System.out.println("runs as expected");
        }
    }

    @Test
    public void testForAddTask(){
        t.addTask(k);
        assertTrue(t.containsTask(k));
        assertTrue(k.containsTag(t));


    }

    @Test
    public void testForDupeTask() {
        t.addTask(k);
        t.addTask(k);
        t.removeTask(k);
        assertFalse(t.containsTask(k));
    }

    @Test
    public void testForRemoveTask(){
        t.addTask(k);
        t.removeTask(k);
        assertFalse(t.containsTask(k));
        assertFalse(k.containsTag(t));
    }

    @Test
    public void testForSameObj(){
        Tag n = t;
        assertTrue(t.equals(n));
        assertTrue(n.equals(t));
    }

    @Test
    public void methodsTest() {
        t.toString();
        t.getName();
        t.getTasks();
    }

    @Test
    public void testForDiffObj() {
        assertFalse(e.equals(t));
        assertFalse(e.equals(new Task("dab")));
    }

    @Test
    public void testForSameName(){
        assertTrue(z.equals(t));
        assertTrue(t.equals(z));
    }

    @Test
    public void testForDiffName(){
        assertFalse(e.equals(t));
        assertFalse(t.equals(e));
    }



}


