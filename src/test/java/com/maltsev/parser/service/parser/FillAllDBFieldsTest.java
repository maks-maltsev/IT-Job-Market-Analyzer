package com.maltsev.parser.service.parser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FillAllDBFieldsTest {
    Object o = new Object();
    List<Integer> l = new ArrayList<>();

    @Before
    public void init(){
        l.add(1);
        l.add(2);
        l.add(3);
    }

    @Test
    public void test(){
        Assert.assertNotEquals(o, new Object());
    }

    @Test
    public void test2(){
        int x = l.get(0);
        int size = 3;
        int length = 10;
        Assert.assertEquals(size, l.size());
    }
}
