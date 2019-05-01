package hello;

import org.springframework.data.repository.CrudRepository;

// Will be auto implemented by Spring and made into a bean
public interface UserRepository extends CrudRepository<User, Integer> {
}
