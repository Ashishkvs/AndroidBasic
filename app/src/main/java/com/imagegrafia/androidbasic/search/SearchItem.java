package com.imagegrafia.androidbasic.search;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchItem {
    private String title;
    private String imageUrl;
    private String description;
}
