package com.kjw.sharemore.users.temp.converter;

import com.kjw.sharemore.users.temp.TempResponse;

public class TempConverter {

    public static TempResponse.TempResponseDTO toTempTestDTO() {
        return TempResponse.TempResponseDTO.builder()
                .testString("This is Test!")
                .build();
    }


    public static TempResponse.TempExceptionDTO toTempExceptionDTO(Integer flag) {
        return TempResponse.TempExceptionDTO.builder()
                .flag(flag)
                .build();
    }
}
