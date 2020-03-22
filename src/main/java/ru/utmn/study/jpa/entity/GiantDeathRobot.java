package ru.utmn.study.jpa.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

/**
 * Представление робота в БД
 */
@Table("giant_death_robot")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class GiantDeathRobot implements Serializable {

  private static final long serialVersionUID = 1L;

  @PrimaryKey("id")
     /* name = "id",
      ordinal = 2,
      type = PrimaryKeyType.PARTITIONED,
      ordering = Ordering.DESCENDING)*/
  private UUID id;

  @Column("pilot")
  private String pilot;

  @Column("death_guns")
  private Set<String> deathGuns = new HashSet<>();

  @Column("date_extermination_begin")
  private LocalDate dateExterminationBegin;
}
