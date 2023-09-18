package com.example.tag1_01simplechunk.batchprocessing;

import com.example.tag1_01simplechunk.entity.Person;
import com.example.tag1_01simplechunk.entity.Student;
import org.springframework.batch.item.ItemProcessor;

public class PersonToStudentItemProcessor implements ItemProcessor<Person, Student> {
    @Override
    public Student process(final Person item) throws Exception {
        return null;
    }
}
