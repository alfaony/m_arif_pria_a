package ist.challenge.m_arif_pria_a.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ist.challenge.m_arif_pria_a.model.entities.User;
import ist.challenge.m_arif_pria_a.model.repos.UserRepo;

@Service
@Transactional
public class UserService 
{
    
    @Autowired
    private UserRepo userRepo;

    public User create(User user)
    {
        return userRepo.save(user);
    }

    public User update(User user)
    {
        return userRepo.save(user);
    }
    
    public User find(Long id)
    {
        Optional<User> user = userRepo.findById(id);

        if(!user.isPresent())
        {
            return null;
        }else
        {
            return user.get();
        }
    }

    public Iterable<User> findAll()
    {
        return userRepo.findAll();
    }

    public void removeOne(Long id)
    {
        userRepo.deleteById(id);
    }

    public List<User> findByUsername(String username)
    { 
        return userRepo.findByUsername(username);
    }
}
