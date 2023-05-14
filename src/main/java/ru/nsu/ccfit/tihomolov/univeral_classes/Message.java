package ru.nsu.ccfit.tihomolov.univeral_classes;

import lombok.Getter;

import java.io.Serializable;

public record Message<T>(@Getter String typeMessage, @Getter T content) implements Serializable {}
