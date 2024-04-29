package com.example.application.services.mappers;

import com.example.application.controllers.dto.SportGroundDTO;
import com.example.application.entities.SportGround;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-25T18:02:40+0300",
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
        sportGroundDTO.setTrainersList( mapTrainers( sportGround.getTrainersList() ) );
        sportGroundDTO.setCapacity( sportGround.getCapacity() );
        sportGroundDTO.setName( sportGround.getName() );

        return sportGroundDTO;
    }
}
