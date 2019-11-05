package sweater;


import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Integer> {

    //List<Message> findByTag(String filter);
}
