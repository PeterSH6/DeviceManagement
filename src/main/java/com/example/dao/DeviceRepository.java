package com.example.dao;

import com.example.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;

@Repository
public interface DeviceRepository extends JpaRepository<Device,Integer> {

    Device findByDeviceId(Integer deviceId);


}
