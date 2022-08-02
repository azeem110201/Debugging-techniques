package test;

import org.springframework.data.repository.CrudRepository;

@org.springframework.stereotype.Repository
public interface TestRepository extends CrudRepository<TestEntity, Long> {
}
