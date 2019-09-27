package com.cp.mapper;

import com.cp.pojo.Address;

import java.util.List;

/**
 * cp 2019-09-12  10:13
 */
public interface AddressMapper {
    List<Address> findAddressByUid(Integer uid);

    void addAddress(Address address);

    void updateDefaultAdd(Integer id, Integer aid);

    void deleteAddressById(Integer aid);

    void updateAddress(Address address);
}
