package com.tec.attendenceTracker.mapper.impl;

import com.tec.attendenceTracker.dto.AddressDTO;
import com.tec.attendenceTracker.mapper.AddressMapper;
import com.tec.attendenceTracker.models.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapperImpl implements AddressMapper {

    @Override
    public AddressDTO toDto(Address address) {
        if (address == null) return null;

        AddressDTO dto = new AddressDTO();
        dto.setId(address.getId());
        dto.setStreet(address.getStreet());
        dto.setCity(address.getCity());
        dto.setState(address.getState());
        dto.setPostalCode(address.getPostalCode());

        return dto;
    }

    @Override
    public Address toEntity(AddressDTO dto) {
        if (dto == null) return null;

        Address address = new Address();
        address.setId(dto.getId());
        address.setStreet(dto.getStreet());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setPostalCode(dto.getPostalCode());

        return address;
    }

    @Override
    public void updateEntity(Address existing, AddressDTO updatedDto) {
        if (existing == null || updatedDto == null) return;

        existing.setStreet(updatedDto.getStreet());
        existing.setCity(updatedDto.getCity());
        existing.setState(updatedDto.getState());
        existing.setPostalCode(updatedDto.getPostalCode());
    }
}
