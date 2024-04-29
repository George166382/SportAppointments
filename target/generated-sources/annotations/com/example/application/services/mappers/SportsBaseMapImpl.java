package com.example.application.services.mappers;

import com.example.application.controllers.dto.SportsBaseDTO;
import com.example.application.entities.SportsBase;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-29T14:51:11+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8.1 (Eclipse Adoptium)"
)
@Component
public class SportsBaseMapImpl implements SportsBaseMap {

    @Override
    public SportsBaseDTO toDTO(SportsBase sportsBase) {
        if ( sportsBase == null ) {
            return null;
        }

        SportsBaseDTO sportsBaseDTO = new SportsBaseDTO();

        sportsBaseDTO.setId( sportsBase.getId() );
        sportsBaseDTO.setGroundsList( mapGrounds( sportsBase.getGroundsList() ) );
        sportsBaseDTO.setAdmin( mapAdmin( sportsBase.getAdmin() ) );
        sportsBaseDTO.setName( sportsBase.getName() );
        sportsBaseDTO.setAddress( sportsBase.getAddress() );

        return sportsBaseDTO;
    }
}
