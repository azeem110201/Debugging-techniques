package test;

import java.util.List;

public interface TestService {
    public TestEntity save(TestEntity testEntity);
    public List<TestEntity> get();
}
