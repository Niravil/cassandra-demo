package ru.utmn.study.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Веб представление смертельного орудия робота
 *
 * @author vasilev
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeathGunDto {

  @JsonProperty(value = "DeathGunDenomination", required = true)
  String denomination;
}
