package com.example.dao;

import com.example.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;

@Repository
@Transactional(readOnly = true)
public interface DeviceRepository extends JpaRepository<Device,Integer> {


    Device findByDeviceId(Integer deviceId);

    @Transactional
    @Modifying
    void deleteByDeviceId(Integer deviceId);

    @Transactional
    @Modifying
    Device save(Device device);
}
