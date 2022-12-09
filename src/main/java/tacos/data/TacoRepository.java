package tacos.data;

import org.springframework.data.repository.Repository;
import tacos.Taco;

public interface TacoRepository extends Repository<Taco, Long> {

  Taco save(Taco taco);
}
