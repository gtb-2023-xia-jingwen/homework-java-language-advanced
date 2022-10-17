package com.tw.javabasic;

import com.tw.javabasic.util.StringFunc;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LambdaTest {
    // Recommended time used: 15 min

    @Test
    void should_apply_to_interface_with_single_abstract_method() {
        final StringFunc lambda = () -> "Hello";

        // TODO:
        //  You should write down the answer directly.
        //
        // Hint
        //  For reference, please check page 315 of "Core Java Vol 1", section 6.3.2.
        // <--start
        final String expect = "Hello";
        // --end-->

        assertEquals(expect, lambda.getString());
    }

    @Test
    void should_be_able_to_bind_to_instance_method() {
        // TODO:
        //  please bind lambda to `instanceMethod()` in this class.
        //
        // Hint
        //  For reference please check page 319 of "Core Java Vol 1", section 6.3.4.
        // <--start
        final StringFunc lambda = this::instanceMethod;
        // --end-->

        assertEquals("instanceMethod", lambda.getString());
    }

    @Test
    void should_be_able_to_bind_to_static_method() {
        // TODO:
        //  please bind lambda to `staticMethod()` in this class.
        //
        // Hint:
        //  For reference please check page 319 of "Core Java Vol 1", section 6.3.4.
        // <--start
        final StringFunc lambda = LambdaTest::staticMethod;
        // --end-->

        assertEquals("staticMethod", lambda.getString());
    }

    private static String staticMethod() {
        return "staticMethod";
    }

    private String instanceMethod() {
        return "instanceMethod";
    }
}
