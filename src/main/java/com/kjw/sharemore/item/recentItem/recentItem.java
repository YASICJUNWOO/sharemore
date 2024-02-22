package com.kjw.sharemore.item.recentItem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class recentItem {

    private String name;

    private String createdAt;
}
