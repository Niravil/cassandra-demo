package ru.utmn.study.jpa.repository;

import java.util.Optional;
import java.util.UUID;
import lombok.NonNull;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;
import ru.utmn.study.jpa.entity.GiantDeathRobot;

@Repository
public interface GiantDeathRobotRepository extends
    CassandraRepository<GiantDeathRobot, UUID> {

  @AllowFiltering
  Optional<GiantDeathRobot> findFirstByPilotEquals(@NonNull String pilot);
}
