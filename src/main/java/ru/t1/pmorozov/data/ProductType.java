package ru.t1.pmorozov.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductType {
    ACCOUNT("Счет"),
    CARD("Карта");
    private final String type;
}
