package charter;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CharterRepository extends MongoRepository<Charter, String> {

}
