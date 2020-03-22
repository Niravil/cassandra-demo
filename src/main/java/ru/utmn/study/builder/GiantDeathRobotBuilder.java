package ru.utmn.study.builder;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.utmn.study.jpa.entity.GiantDeathRobot;
import ru.utmn.study.model.DeathGunDto;
import ru.utmn.study.model.GiantDeathRobotDto;

/**
 * Единый строитель роботов
 * Частично избыточен, демонстрация
 *
 * @author vasilev
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GiantDeathRobotBuilder {

  private String pilot;
  private LocalDate extertmintaionDate;
  private Set<String> deathGuns;
  private Set<DeathGunDto> deathGunDtos;
  private String uuid;

  public static GiantDeathRobotBuilder theRobot() {
    return new GiantDeathRobotBuilder();
  }

  public GiantDeathRobotBuilder withPilot(String pilot) {
    this.pilot = pilot;
    return this;
  }

  public GiantDeathRobotBuilder withExterminationDate(LocalDate exterminationDate) {
    this.extertmintaionDate = exterminationDate;
    return this;
  }

  public GiantDeathRobotBuilder withDatabaseDeathGuns(Set<DeathGunDto> deathGunDtos) {
    this.deathGuns = deathGunDtos
        .stream()
        .map(DeathGunDto::getDenomination)
        .collect(Collectors.toCollection(HashSet::new));
    return this;
  }

  public GiantDeathRobotBuilder withDtoDeathGuns(Set<String> deathGuns) {
    this.deathGunDtos = deathGuns
        .stream()
        .map(DeathGunDto::new)
        .collect(Collectors.toCollection(HashSet::new));
    return this;
  }

  public GiantDeathRobotBuilder withUUID(UUID uuid) {
    this.uuid = uuid.toString();
    return this;
  }

  /**
   * Выполняет построение отдаваемого сервисом результата на основе переданных данных
   */
  public GiantDeathRobotDto buildDto() {
    GiantDeathRobotDto giantDeathRobotDto = new GiantDeathRobotDto();
    giantDeathRobotDto.setUuid(this.uuid);
    giantDeathRobotDto.setPilotName(this.pilot);
    giantDeathRobotDto.setDateExterminationBegin(this.extertmintaionDate);
    if (this.deathGunDtos != null) {
      giantDeathRobotDto.setDeathGuns(this.deathGunDtos);
    }
    return giantDeathRobotDto;
  }

  /**
   * Выполняет построение сохраняемого результата на основе переданных данных
   */
  public GiantDeathRobot buildEntity() {
    GiantDeathRobot giantDeathRobot = new GiantDeathRobot();
    giantDeathRobot.setId(UUID.randomUUID());
    giantDeathRobot.setPilot(this.pilot);
    giantDeathRobot.setDateExterminationBegin(this.extertmintaionDate);
    if (this.deathGuns != null) {
      giantDeathRobot.setDeathGuns(this.deathGuns);
    }
    return giantDeathRobot;
  }

}
