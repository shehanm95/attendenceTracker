package com.tec.attendenceTracker.mapper;

import com.tec.attendenceTracker.dto.AddressDTO;
import com.tec.attendenceTracker.models.Address;

public interface AddressMapper {

    AddressDTO toDto(Address address);

    Address toEntity(AddressDTO dto);

    void updateEntity(Address existing, AddressDTO updatedDto);
}
