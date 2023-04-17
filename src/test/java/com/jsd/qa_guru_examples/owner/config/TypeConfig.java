package com.jsd.qa_guru_examples.owner.config;

import org.aeonbits.owner.Config;

public interface TypeConfig extends Config {

    @Key("integer")
    Integer getInteger();

    @Key("double")
    Double getDouble();

    @Key("boolean")
    Boolean getBoolean();

    @Key("enum")
    Browser getEnum();
}
