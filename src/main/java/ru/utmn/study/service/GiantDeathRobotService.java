package ru.utmn.study.service;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import ru.utmn.study.builder.GiantDeathRobotBuilder;
import ru.utmn.study.exception.ResourceNotFoundException;
import ru.utmn.study.jpa.entity.GiantDeathRobot;
import ru.utmn.study.jpa.repository.GiantDeathRobotRepository;
import ru.utmn.study.model.GiantDeathRobotDto;

/**
 * Служба взаимодействия приложения с БД
 *
 * @author vasilev
 */
@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class GiantDeathRobotService {

  GiantDeathRobotRepository giantDeathRobotRepository;

  /**
   * Сохраняет новый экземпляр {@link GiantDeathRobot} или обновляет существующй
   *
   * @param giantDeathRobotDto Переданные для сохранения данные
   * @return Объект с датой сохранения
   */
  public GiantDeathRobotDto save(GiantDeathRobotDto giantDeathRobotDto) {
    LocalDate exterminationBegin = LocalDate.now();

    GiantDeathRobot giantDeathRobot = GiantDeathRobotBuilder.theRobot()
        .withPilot(giantDeathRobotDto.getPilotName())
        .withDatabaseDeathGuns(giantDeathRobotDto.getDeathGuns())
        .withExterminationDate(exterminationBegin)
        .buildEntity();

    giantDeathRobotRepository.save(giantDeathRobot);

    giantDeathRobotDto.setDateExterminationBegin(exterminationBegin);

    return giantDeathRobotDto;
  }

  /**
   * Выполняет поиск по имени пилота
   *
   * @param pilot Имя пилота, привязанного к роботу
   * @return Первый робот, найденный по имени пилота
   */
  public GiantDeathRobotDto findByPilot(String pilot) {
    GiantDeathRobot giantDeathRobot = giantDeathRobotRepository.findFirstByPilotEquals(pilot)
        .orElseThrow(() -> new ResourceNotFoundException(MessageFormat.format(
            "Giant Death Robot with pilot, named {0}, not found", pilot)));
    return GiantDeathRobotBuilder.theRobot()
        .withPilot(giantDeathRobot.getPilot())
        .withExterminationDate(giantDeathRobot.getDateExterminationBegin())
        .withDtoDeathGuns(giantDeathRobot.getDeathGuns())
        .buildDto();
  }

  /**
   * Выполняет поиск по уникальному идентификатору
   *
   * @param id {@link UUID} робота
   * @return Найденный робот
   */
  public GiantDeathRobotDto findById(UUID id) {
    GiantDeathRobot giantDeathRobot = giantDeathRobotRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(MessageFormat.format(
            "Giant Death Robot with id {0} not found", id)));
    return GiantDeathRobotBuilder.theRobot()
        .withPilot(giantDeathRobot.getPilot())
        .withExterminationDate(giantDeathRobot.getDateExterminationBegin())
        .withDtoDeathGuns(giantDeathRobot.getDeathGuns())
        .buildDto();
  }

  /**
   * Удаляет робота с соответствующим идентификатором
   *
   * @param id {@link UUID} робота
   */
  public void delete(UUID id) {
    GiantDeathRobot giantDeathRobot = giantDeathRobotRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(MessageFormat.format(
            "Giant Death Robot with id {0} not found", id)));
    giantDeathRobotRepository.delete(giantDeathRobot);
  }

  /**
   * Выполняет поиск всех роботов
   *
   * @param page Номер страницы
   * @param pageSize Количество элементов на странице
   * @return Пагинированный результат
   */
  public Slice<GiantDeathRobotDto> findAll(Integer page, Integer pageSize) {
    return giantDeathRobotRepository
        .findAll(PageRequest.of(page, pageSize))
        .map(r -> GiantDeathRobotBuilder.theRobot()
            .withPilot(r.getPilot())
            .withExterminationDate(r.getDateExterminationBegin())
            .withDtoDeathGuns(r.getDeathGuns())
            .buildDto());
  }

}
