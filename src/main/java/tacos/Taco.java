package tacos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
// TODO: Thymeleaf th:object setter 가 있어야함.
@Setter
@NoArgsConstructor
@Entity
public class Taco {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private Date createdAt;

  // TODO: @NotNull, @Size 에 똑같은 message 사용 가능?
  @NotNull(message = "Name must be at least 5 characters long")
  @Size(min = 5, message = "Name must be at least 5 characters long")
  private String name;

  // TODO: 똑같은 message 사용 가능?
  @NotNull(message = "You must choose at least 1 ingredient")
  @Size(min = 1, message = "You must choose at least 1 ingredient")
  @ToString.Exclude
  @ManyToMany(targetEntity = Ingredient.class)
  // TODO: vavr 컬렉션 사용 불가?
  private java.util.List<Ingredient> ingredients;

  @PrePersist
  void createdAt() {
    this.createdAt = new Date();
  }
}
