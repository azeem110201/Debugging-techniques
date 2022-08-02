package test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    TestRepository testRepository;

    public TestServiceImpl(TestRepository testRepository) {

    }

    @Override
    public TestEntity save(TestEntity testEntity) {
        return testRepository.save(testEntity);
    }

    @Override
    public List<TestEntity> get() {
        return (List<TestEntity>) testRepository.findAll();
    }
}
