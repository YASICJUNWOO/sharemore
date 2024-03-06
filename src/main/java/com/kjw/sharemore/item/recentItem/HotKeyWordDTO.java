package com.kjw.sharemore.item.recentItem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class HotKeyWordDTO {

    private String keyword;

    private int count;

    public static HotKeyWordDTO of(String keyword, int count) {
        return HotKeyWordDTO.builder()
                .keyword(keyword)
                .count(count)
                .build();
    }
}
