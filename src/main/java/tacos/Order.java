package tacos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.CreditCardNumber;

@ToString
@Getter
// TODO: Thymeleaf th:object setter 가 있어야함.
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Table(name = "Taco_Order")
@Entity
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private Date placedAt;

  @NotBlank(message = "Name is required")
  private String deliveryName;

  @NotBlank(message = "Street is required")
  private String deliveryStreet;

  @NotBlank(message = "City is required")
  private String deliveryCity;

  @NotBlank(message = "State is required")
  private String deliveryState;

  @NotBlank(message = "Zip code is required")
  private String deliveryZip;

  @CreditCardNumber(message = "Not a valid credit card number")
  private String ccNumber;

  @SuppressWarnings({"RegExpRedundantEscape", "RegExpSimplifiable"})
  @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
    message="Must be formatted MM/YY")
  private String ccExpiration;

  @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
  private String ccCVV;

  @ManyToMany(targetEntity = Taco.class)
  @ToString.Exclude
  private List<Taco> tacos = new ArrayList<>();

  public void addDesign(Taco design) {
    tacos.add(design);
  }

  @PrePersist
  void placedAt() {
    this.placedAt = new Date();
  }
}
