package ru.t1.pmorozov.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String userName;

    public UserDto(Long id, String userName) {
        this.id = id;
        this.userName = userName;
    }
}
