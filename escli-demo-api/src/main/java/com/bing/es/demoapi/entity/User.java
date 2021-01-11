package com.bing.es.demoapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.awt.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class User {

    private String name;
    private Integer age;
}
