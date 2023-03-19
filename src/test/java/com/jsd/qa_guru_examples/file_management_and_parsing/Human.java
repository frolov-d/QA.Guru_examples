package com.jsd.qa_guru_examples.file_management_and_parsing;

import java.util.List;

public class Human {
    public String mane;
    public Integer age;
    public Boolean isClever;
    public List<String> hobbies;
    public Passport passport;

    public static class Passport {
        public Integer number;
        public String issueDate;
    }

}
