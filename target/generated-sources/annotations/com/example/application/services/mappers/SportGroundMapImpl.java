package com.example.application.services.mappers;

import com.example.application.controllers.dto.SportGroundDTO;
import com.example.application.entities.SportGround;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-28T18:36:54+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8.1 (Eclipse Adoptium)"
)
@Component
public class SportGroundMapImpl implements SportGroundMap {

    @Override
    public SportGroundDTO toDTO(SportGround sportGround) {
        if ( sportGround == null ) {
            return null;
        }

        SportGroundDTO sportGroundDTO = new SportGroundDTO();

        sportGroundDTO.setId( sportGround.getId() );
        sportGroundDTO.setSportsBase( mapSportsBase( sportGround.getSportsBase() ) );
        sportGroundDTO.setAppointmentsList( mapAppointments( sportGround.getAppointmentsList() ) );
        sportGroundDTO.setCapacity( sportGround.getCapacity() );
        sportGroundDTO.setName( sportGround.getName() );

        return sportGroundDTO;
    }
}
