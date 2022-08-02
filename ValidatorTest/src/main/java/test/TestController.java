package test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    TestService testService;

    @PostMapping
    public TestEntity validityTest(@Valid @RequestBody TestEntity testEntity) {
        return testService.save(testEntity);
    }

    @PostMapping("${request.mapping}")
    public TestEntity funTest(@Valid @RequestBody TestEntity testEntity) {
        return testService.save(testEntity);
    }

    @GetMapping
    public List<TestEntity> showTest() {
        return testService.get();
    }
}
