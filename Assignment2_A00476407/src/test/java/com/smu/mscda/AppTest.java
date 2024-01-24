package com.smu.mscda;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AppTest extends TestCase {
    private String input = "smu";

    public void testCapitalizeString() {
        Assert.assertEquals(App.CapitalizeString(input), "Smu");
    }

    public void testGenerateMD5Hex() {
        Assert.assertEquals(App.GenerateMD5Hex(input), "3773300c2f413cc7136f8d74b305519c");
    }
}