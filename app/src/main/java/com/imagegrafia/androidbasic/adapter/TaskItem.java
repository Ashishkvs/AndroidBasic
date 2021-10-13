package com.imagegrafia.androidbasic.adapter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskItem {
    private String title;
    private String id;
    private String imageUrl;

}
