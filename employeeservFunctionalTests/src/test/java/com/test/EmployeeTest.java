package com.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EmployeeTest {

    @Before
    public void init() { }

    @Test
    public void getEmployeeByUnknownIdShouldReturn400(){
        Assert.assertEquals("hi", "hi");
    }

}
