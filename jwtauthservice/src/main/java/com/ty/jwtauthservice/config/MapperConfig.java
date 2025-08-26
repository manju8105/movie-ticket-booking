package com.ty.jwtauthservice.config;

import com.ty.jwtauthservice.dto.RegisterUserRequestDto;
import com.ty.jwtauthservice.entity.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.swing.text.html.parser.Entity;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper()
    {
        ModelMapper mapper=new ModelMapper();

        mapper.getConfiguration().setSkipNullEnabled(true);
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        //configure type mappings
        TypeMap<RegisterUserRequestDto, User> registerMap=mapper.createTypeMap(RegisterUserRequestDto.class, User.class);

        registerMap.addMappings(m->m.skip(User::setId));

        return mapper;
    }
}
