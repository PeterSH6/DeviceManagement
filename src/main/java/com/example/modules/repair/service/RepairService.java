package com.example.modules.repair.service;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author https://github.com/anlowee
 */
public interface RepairService {

    @Transactional
    Boolean repair(Integer deviceId);

}
