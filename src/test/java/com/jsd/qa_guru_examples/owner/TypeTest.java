package com.jsd.qa_guru_examples.owner;

import com.jsd.qa_guru_examples.owner.config.Browser;
import com.jsd.qa_guru_examples.owner.config.TypeConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TypeTest {

    @Test
    public void testInteger() {
        System.setProperty("integer", "10");

        TypeConfig config = ConfigFactory.create(TypeConfig.class, System.getProperties());
        assertThat(config.getInteger()).isEqualTo(10);
    }
    @Test
    public void testDouble() {
        System.setProperty("double", "10.10");

        TypeConfig config = ConfigFactory.create(TypeConfig.class, System.getProperties());
        assertThat(config.getDouble()).isEqualTo(10.10);
    }
    @Test
    public void testBoolean() {
        System.setProperty("boolean", "true");

        TypeConfig config = ConfigFactory.create(TypeConfig.class, System.getProperties());
        assertThat(config.getBoolean()).isEqualTo(true);
    }
    @Test
    public void testEnum() {
        System.setProperty("enum", "CHROME");

        TypeConfig config = ConfigFactory.create(TypeConfig.class, System.getProperties());
        assertThat(config.getEnum()).isEqualTo(Browser.CHROME);
    }
}
