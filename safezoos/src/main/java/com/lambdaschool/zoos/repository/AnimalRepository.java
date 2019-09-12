package com.lambdaschool.zoos.repository;

import com.lambdaschool.zoos.model.Animal;
import com.lambdaschool.zoos.view.AnimalCount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.ArrayList;

public interface AnimalRepository extends CrudRepository<Animal, Long>
{
    Animal findByAnimaltype(String type);
    @Query(value = "select a.animaltype, count(a.animaltype) as count from animal a join zooanimals za on a.animalid = za.animalid group by a.animaltype",
            nativeQuery = true)
    List<AnimalCount> animalsByCount();
}
