package com.example.dao;

import com.example.entity.Device;
import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.List;

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

    List<Device> findByDeviceName(String deviceName);

    List<Device> findByUser(User user);

    List<Device> findByUserAndDeviceStatusLessThanEqual(User user,Integer deviceStatus);

    List<Device> findByDeviceStatus(int deviceStatus);

    List<Device> findByUserAndDeviceStatus(User user, Integer deviceStatus);

    List<Device> findByDeviceType(String deviceType);

    List<Device> findByDevicePara(String devicePara);

    //TODO:Maybe can't find
    List<Device> findByBuyTime(Date buyTime);

    List<Device> findByDevicePrice(String devicePrice);

    List<Device> findByDeviceManufacture(String deviceManufacture);
}
