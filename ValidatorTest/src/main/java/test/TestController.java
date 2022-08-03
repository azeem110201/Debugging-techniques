package test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    TestService testService;

    @PostMapping("/validTest")
    public ResponseEntity<TestEntity> validityTest(@Valid @RequestBody TestEntity testEntity) {
        TestEntity savedEntity = testService.save(testEntity);
        return new ResponseEntity<TestEntity>(savedEntity, HttpStatus.CREATED);
    }

    @PostMapping("/{request.mapping}")
    public ResponseEntity<TestEntity> funTest(@Valid @RequestBody TestEntity testEntity) {
        TestEntity savedEntity = testService.save(testEntity);
        return new ResponseEntity<TestEntity>(savedEntity, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<TestEntity>> showTest() {

        List<TestEntity> tests = testService.get();

        return new ResponseEntity<>(tests, HttpStatus.OK);
    }
}
