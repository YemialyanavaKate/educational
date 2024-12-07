package by.ita.je.mappers;

import by.ita.je.dto.to_web.RegistrationWebDto;
import by.ita.je.models.Registration;
import org.springframework.stereotype.Component;


@Component
public class RegistrationMapper {

    public RegistrationWebDto toDto(Registration registration) {

        return RegistrationWebDto.builder()
                .number(registration.getNumber())
                .name(registration.getName())
                .surname(registration.getSurname())
                .build();
    }

    public Registration toEntity(RegistrationWebDto registrationWebDto) {

        return Registration.builder()
                .number(registrationWebDto.getNumber())
                .name(registrationWebDto.getName())
                .surname(registrationWebDto.getSurname())
                .build();
    }
}
