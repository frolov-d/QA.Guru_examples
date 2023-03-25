package com.jsd.qa_guru_examples.jenkins_examples.simple;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("simple")
public class SkippedTests {

    @Disabled
    @Test
    void test1() {
        assertTrue(false);
    }

    @Disabled("Some reason")
    @Test
    void test2() {
        assertTrue(false);
    }
}
