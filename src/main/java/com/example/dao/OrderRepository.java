package com.example.dao;

import com.example.entity.Device;
import com.example.entity.Order;
import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {

    /**
     * find by orderId
     * @param orderId the primary key of Order
     * @return one order
     */
    Order findByOrderId(Integer orderId);

    /**
     * find by user
     * @param user the target user
     * @return all Orders of the user
     */
    List<Order> findByUser(User user);

    List<Order> findByUserAndDevice(User user,Device device);

    List<Order> findByUserAndOrderStatus(User user,Integer orderStatus);

    List<Order> findByOrderStatus(Integer orderStatus);

    List<Order> findByDeviceAndOrderStatus(Device device,Integer orderStatus);

    //not used
    Order findByUserAndDeviceAndOrderStatus(User user,Device device,Integer orderStatus);


    /**
     * find by device
     * @param device the target device
     * @return all Orders of the device
     */
    List<Order> findByDevice(Device device);

    /**
     * delete the Order by its id
     * @param id the target Order id
     */
    void deleteByOrderId(Integer id);

    void deleteByDevice_DeviceId(Integer deviceId);


    List<Order> findByOrderStatusAndOrderStatus(Integer orderStatus1,Integer orderStatus2);


}
