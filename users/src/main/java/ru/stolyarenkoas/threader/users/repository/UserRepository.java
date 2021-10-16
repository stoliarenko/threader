package ru.stolyarenkoas.threader.users.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.stolyarenkoas.threader.users.model.User;

/**
 * Repository for {@link ru.stolyarenkoas.threader.users.model.User}
 *
 * @author Alexander Stoliarenko (16.10.2021)
 */
@Repository
public interface UserRepository extends CrudRepository<User, String> {

}
