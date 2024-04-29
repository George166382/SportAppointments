package com.example.application.services.mappers;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.CLASS;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.mapstruct.Qualifier;

@Qualifier
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface TrainerToTrainerDTO {

}
