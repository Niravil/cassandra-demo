package ru.utmn.study.controller;

import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.utmn.study.model.GiantDeathRobotDto;
import ru.utmn.study.service.GiantDeathRobotService;

/**
 * Контроллер запросов к приложению роботов
 *
 * @author vasilev
 */
@RestController
@RequestMapping("/api")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class GiantDeathRobotsController {

  private final GiantDeathRobotService giantDeathRobotService;

  @RequestMapping(value = "/robots", method = {
      RequestMethod.POST, RequestMethod.PUT})
  public ResponseEntity<GiantDeathRobotDto> publishGiantDeathRobot(
      @RequestBody GiantDeathRobotDto giantDeathRobotDto) {
    final GiantDeathRobotDto result = giantDeathRobotService.save(giantDeathRobotDto);
    return ResponseEntity.ok(result);
  }

  @DeleteMapping(value = "/robots/{uuid}")
  public ResponseEntity deleteRobot(@PathVariable String uuid) {
    giantDeathRobotService.delete(UUID.fromString(uuid));
    return ResponseEntity.ok().build();
  }


  @GetMapping(value = "/robots/get")
  public ResponseEntity<GiantDeathRobotDto> getRobotByPilot(@RequestParam String pilot) {
    GiantDeathRobotDto giantDeathRobotDto = giantDeathRobotService.findByPilot(pilot);
    return new ResponseEntity<>(giantDeathRobotDto, HttpStatus.OK);
  }

  @GetMapping(value = "/robots/{uuid}")
  public ResponseEntity<GiantDeathRobotDto> getRobot(@PathVariable String uuid) {
    GiantDeathRobotDto giantDeathRobotDto = giantDeathRobotService.findById(UUID.fromString(uuid));
    return new ResponseEntity<>(giantDeathRobotDto, HttpStatus.OK);
  }

  /**
   * Вместо {@link Page} используется {@link Slice}
   * в силу особенностей имплементации библиотеки взаимодействия с Cassandra
   */
  @GetMapping(value = "/robots/all")
  public ResponseEntity<Slice<GiantDeathRobotDto>> getAllRobots(
      @RequestParam(required = false, defaultValue = "0") int page,
      @RequestParam(required = false, defaultValue = "20") int size) {
    Slice<GiantDeathRobotDto> robots = giantDeathRobotService.findAll(page, size);
    return new ResponseEntity<>(robots, HttpStatus.OK);
  }

}
