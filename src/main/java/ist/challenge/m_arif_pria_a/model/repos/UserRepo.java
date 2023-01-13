package ist.challenge.m_arif_pria_a.model.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ist.challenge.m_arif_pria_a.model.entities.User;

public interface UserRepo extends CrudRepository<User, Long>
{
    List<User> findByUsername(String username);    
}
