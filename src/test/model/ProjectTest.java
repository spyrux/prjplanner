package model;

import model.exceptions.EmptyStringException;
import model.exceptions.NullArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProjectTest {

    Project b;
    Project p;
    Task t;
    Task o;
    Task k;

    @BeforeEach
    public void setup(){
        p = new Project("epic");
        t = new Task("cool");
        DueDate d1 = new DueDate();
        DueDate d3 = new DueDate(new Date(200));
        t.setDueDate(d1);
        Priority p1 = new Priority(2);
        t.setPriority(p1);
        Status s1 = Status.DONE;
        t.setStatus(Status.DONE);
        t.addTag("pop");
        t.addTag("poop");
        o = new Task("lenny");
        p.add(t);
        p.add(o);
        k = new Task("eeee");
        p.add(k);
        b = new Project("pog");
    }

    @Test
    public void testForIterator() {
        Task a = new Task("1");
        Task e = new Task("2");
        Task c = new Task("3");
        Task d = new Task("4");
        a.setPriority(new Priority(1));
        e.setPriority(new Priority(2));
        c.setPriority(new Priority(3));
        d.setPriority(new Priority(4));
        b.add(e);
        b.add(a);
        b.add(c);
        b.add(d);

        for (Todo t: b) {
            System.out.println(t);
        }

    }

    @Test
    public void TestForGetters(){
        assertEquals(p.getNumberOfTasks(), 3);
        assertEquals(p.getDescription(),"epic");
        p.remove(o);
        p.remove(t);
        try {p.getTasks();
        } catch (UnsupportedOperationException n){}
    }
    @Test
    public void testForProg(){
        p = new Project("jeff");
        p.add(t);
        t.setProgress(100);
        p.isCompleted();
    }

    @Test
    public void TestForOtherMethods(){
        //assertFalse(p.isCompleted());
        p.remove(o);
        assertTrue(p.contains(t));
        p.remove(t);
        //assertFalse(p.isCompleted());
        assertFalse(p.contains(o));
        p.add(t);
       // assertTrue(p.isCompleted());
        p.add(t);
        p.add(t);
        p.remove(t);
        assertFalse(p.contains(t));
        p.remove(k);
        assertFalse(p.contains(k));
        p.add(t);
        k = new Task("dab");
        k.setDueDate(new DueDate(new Date(200)));
        assertFalse(p.contains(k));
        k = new Task("don");
        k.setStatus(Status.TODO);
        assertFalse(p.contains(k));
        k = new Task("don");
        k.addTag("pop");
        assertFalse(p.contains(k));
        k = new Task("ad");
        k.setPriority(new Priority(2));
        assertFalse(p.contains(k));
        k = new Task("ads");
        k.setStatus(Status.DONE);
        assertFalse(p.contains(k));




    }

    @Test
    public void projectMethods() {
        p.getDescription();

        p.getProgress();
        try {
            p.contains((Task) null);
        }catch (NullArgumentException n){
        }
        p.equals(p);
        p.equals(new Task("dab"));
        p.hashCode();
        p.equals(new Project("dldldl"));
        try {
            p = new Project(null);
        }catch (EmptyStringException n){
        }
    }

    @Test
    public void TestForNoneProgress(){
        p.remove(t);
        p.remove(o);
        assertEquals(p.getProgress(), 0);
    }

    @Test
    public void TestForProgress(){
        p = new Project("cool");
        assertEquals(p.getEstimatedTimeToComplete(),0);
        assertEquals(p.getNumberOfTasks(),0);
        k.setProgress(100);
        o.setProgress(50);
        p.add(k);
        p.add(o);
        assertEquals(75,p.getProgress());
        o.setEstimatedTimeToComplete(8);
        assertEquals(p.getEstimatedTimeToComplete(),8);
        k.setEstimatedTimeToComplete(12);
        assertEquals(p.getEstimatedTimeToComplete(),20);

    }

    @Test
    public void testForMultiProjects(){
        Project c = new Project("popp");
        t.setEstimatedTimeToComplete(8);
        t.setProgress(100);
        c.add(t);
        Project j = new Project("eepe");
        c.add(j);
        assertEquals(c.getProgress(), 50);
        o.setEstimatedTimeToComplete(8);
        j.add(o);
        assertEquals(c.getEstimatedTimeToComplete(),16);
    }

}

