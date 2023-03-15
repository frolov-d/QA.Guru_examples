package com.jsd.qa_guru_examples.junit_examples;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

//@DisplayName("Простые демонстрационные тесты")
@Disabled("JRASERVER-41589")
public class SimpleTest {

    @Disabled("JRASERVER-41589")
    @Test
    @DisplayName("проверка корректности работы methodEx()")
    void methodShouldReturn1AsResult() {
        String actual = methodEx();
        Assertions.assertEquals("1", actual, "");
    }

    String methodEx() {
        return "1";
    }
 }
