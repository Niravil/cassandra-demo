package ru.utmn.study.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.util.Set;
import lombok.Data;

/**
 * Веб представление робота
 *
 * @author vasilev
 */
@Data
public class GiantDeathRobotDto {

  @JsonProperty(value = "PilotName", required = true)
  private String pilotName;

  @JsonProperty(value = "DeathGuns", required = true)
  private Set<DeathGunDto> deathGuns;

  @JsonProperty(value = "DateExterminationBegin")
  private LocalDate dateExterminationBegin;
}
