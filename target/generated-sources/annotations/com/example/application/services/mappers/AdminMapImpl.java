package com.example.application.services.mappers;

import com.example.application.controllers.dto.AdminDTO;
import com.example.application.entities.Admin;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-29T14:51:11+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8.1 (Eclipse Adoptium)"
)
@Component
public class AdminMapImpl implements AdminMap {

    @Override
    public AdminDTO toDTO(Admin admin) {
        if ( admin == null ) {
            return null;
        }

        AdminDTO adminDTO = new AdminDTO();

        adminDTO.setIdAdmin( admin.getIdAdmin() );
        adminDTO.setBasesList( map( admin.getBasesList() ) );
        adminDTO.setName( admin.getName() );
        adminDTO.setEmail( admin.getEmail() );
        adminDTO.setPassword( admin.getPassword() );

        return adminDTO;
    }
}
