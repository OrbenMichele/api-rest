package com.morben.restapi.gateway.repository;

import com.morben.restapi.model.HouseLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface HouseLogRepository extends JpaRepository<HouseLog, UUID>, JpaSpecificationExecutor<HouseLog> {

}
