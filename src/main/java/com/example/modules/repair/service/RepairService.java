package com.example.modules.repair.service;

import com.example.modules.repair.vo.BrokenVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author https://github.com/anlowee
 */
public interface RepairService {

    List<BrokenVO> getAllBroken();

    @Transactional
    Boolean repair(Integer deviceId);

}
