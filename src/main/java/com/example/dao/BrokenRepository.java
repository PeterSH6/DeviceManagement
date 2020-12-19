package com.example.dao;

import com.example.entity.Broken;
import com.example.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrokenRepository extends JpaRepository<Broken,Integer> {

    Broken findByDevice_DeviceId(Integer deviceId);

    void deleteByDevice_DeviceId(Integer deviceId);

}
