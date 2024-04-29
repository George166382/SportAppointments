package com.example.application.services.mappers;

import com.example.application.controllers.dto.AppointmentDTO;
import com.example.application.entities.Appointment;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-29T14:51:11+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8.1 (Eclipse Adoptium)"
)
@Component
public class AppointmentMapImpl implements AppointmentMap {

    @Override
    public AppointmentDTO toDTO(Appointment appointment) {
        if ( appointment == null ) {
            return null;
        }

        AppointmentDTO appointmentDTO = new AppointmentDTO();

        appointmentDTO.setId( appointment.getId() );
        appointmentDTO.setUser( mapUser( appointment.getUser() ) );
        appointmentDTO.setSportGround( mapSportGround( appointment.getSportGround() ) );
        appointmentDTO.setNop( appointment.getNop() );
        appointmentDTO.setTrainerName( appointment.getTrainerName() );
        appointmentDTO.setAppointmentDate( appointment.getAppointmentDate() );
        appointmentDTO.setAppointmentHour( appointment.getAppointmentHour() );

        return appointmentDTO;
    }
}
