package com.sunx.mysprmdbapp1.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.sunx.mysprmdbapp1.model.ImageRecords;

public interface ImgRecordsRepository extends MongoRepository<ImageRecords, String> {

/*    
    @Query("{name:'?0'}")
    ImageRecords findItemByName(String name);
    
    @Query(value="{category:'?0'}", fields="{'name' : 1, 'quantity' : 1}")
    List<ImageRecords> findAll(String category); 
    public long count();
*/
}