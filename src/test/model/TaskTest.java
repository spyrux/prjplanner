package model;

import model.exceptions.EmptyStringException;
import model.exceptions.InvalidProgressException;
import model.exceptions.NegativeInputException;
import model.exceptions.NullArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parsers.exceptions.ParsingException;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    // TODO: design tests for new behaviour added to Task class

    Task t;
    Task z;
    Task b;
    Task e;
    Tag n;
    Task o;
    Tag g;

    @BeforeEach
    public void setup(){
        t = new Task("dab ## in progress; today; important;");
        z = t;
        b = new Task("dab ## in progress; today; important;");
        e = new Task("dab ## in progress; tomorrow; important;");
        o = new Task("dab ## in progress; tomorrow; cool; important;");
        g = new Tag("fortnite");
        n = new Tag("cool");



    }

    @Test
    public void testConstructor() {
        b = new Task("cool");
        try {
            b = new Task("");
        } catch (EmptyStringException n) {
        }
    }

    @Test
    public void testProgressETC() {
        assertEquals(t.getProgress(),0);
        assertEquals(t.getEstimatedTimeToComplete(),0);
    }

    @Test
    public void testSetters() {
        t.setProgress(15);
        t.setEstimatedTimeToComplete(19);
        assertEquals(t.getEstimatedTimeToComplete(),19);
        assertEquals(t.getProgress(),15);
    }
    @Test
    public void testAddStringNullTag(){
        try {
            t.addTag((String) null);
            System.out.println("shouldnt run");
        } catch (EmptyStringException n){
            System.out.println("runs");
        }

    }

    @Test
    public void testAddNullTag(){
        t.addTag("col");
        try {
            t.addTag((Tag)null);
            System.out.println("shouldnt run");
        } catch (NullArgumentException n){
            System.out.println("runs");
        }

    }

    @Test
    public void methodsTest() {
        b.removeTag("dab");
        b.getTags();
        b.setPriority(new Priority(1));
        b.getStatus();
        b.getDescription();
        e.toString();

    }
    @Test
    public void testNullPrio() {
        try {
            t.setPriority(null);
        }
        catch (NullArgumentException n){

        }

    }
    @Test
    public void testNegHours(){
        try {
            t.setEstimatedTimeToComplete(-10);
        }
        catch (NegativeInputException n){

        }
    }
    @Test
    public void testBigProg(){
        try {
            t.setProgress(-10);
        }
        catch (InvalidProgressException n){

        }
    }


    @Test
    public void testNullStat() {
        try {
            t.setStatus(null);
        }
        catch (NullArgumentException n){

        }

    }
    @Test
    public void testNullTag() {
        try {
            t.containsTag((String)null);
        }
        catch (EmptyStringException n){

        }

    }

    @Test
    public void testForInsatance() {
        t.equals(new Tag("okok"));
    }

    @Test
    public void testNullDesc() {
        try {
            t.setDescription(null);
        }
        catch (EmptyStringException n){

        }

    }

    @Test
    public void testNullTask() {
        try {
            z = new Task(null);
        }
        catch (EmptyStringException n){

        }

    }
    @Test
    public void testRemoveSNullTag(){
        try {
            t.removeTag((String
                    ) null);
            System.out.println("shouldnt run");
        } catch (EmptyStringException n){
            System.out.println("runs");
        }

    }

    @Test
    public void testRemoveNullTag(){
        try {
            t.removeTag((Tag) null);
            System.out.println("shouldnt run");
        } catch (NullArgumentException n){
            System.out.println("runs");
        }

    }

    @Test
    public void testForAddTag() {
        t.addTag(n);
        assertTrue(t.containsTag(n));
    }

    @Test
    public void testForDupeTag() {
        assertTrue(o.containsTag("cool"));
        o.addTag(n);
        o.addTag(n);
        o.removeTag(n);
        assertFalse(o.containsTag(n));

    }

    @Test
    public void testForRemoveTag() {
        o.removeTag(n);
        assertFalse(o.containsTag(n));
    }


    @Test
    public void testForSameObj(){
        assertTrue(z.equals(t));
        assertTrue(t.equals(z));
    }

    @Test
    public void testForDiffObjSameString(){
        assertTrue(b.equals(t));
        assertTrue(t.equals(b));
    }

    @Test
    public void testForDiffStringDiffObj(){
        assertFalse(e.equals(t));
        assertFalse(t.equals(e));
        assertFalse(b.equals(e));
    }

}