package com.example.tag1_01simplechunk.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String lastName;
    private String firstName;
    private Integer age;
}
