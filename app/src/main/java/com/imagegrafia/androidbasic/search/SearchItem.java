package com.imagegrafia.androidbasic.search;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchItem implements Serializable {
    private String title;
    private String imageUrl;
    private String description;
}
