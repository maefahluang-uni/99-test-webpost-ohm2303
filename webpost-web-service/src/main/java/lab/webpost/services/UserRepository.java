package lab.webpost.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import lab.webpost.domain.User;

public interface UserRepository extends CrudRepository<User, Long>{
    //TODO: add necessary derived methods
    public List<User> findByUsername(String username);
}
