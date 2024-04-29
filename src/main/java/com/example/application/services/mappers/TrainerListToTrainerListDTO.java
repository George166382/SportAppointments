package com.example.application.services.mappers;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.mapstruct.Qualifier;

@Qualifier
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface TrainerListToTrainerListDTO {

}
