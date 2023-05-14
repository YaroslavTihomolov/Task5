package ru.nsu.ccfit.tihomolov.univeral_classes;

import java.lang.annotation.*;

@Target(value = {ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@Inherited
public @interface CommandAnnotation { }
