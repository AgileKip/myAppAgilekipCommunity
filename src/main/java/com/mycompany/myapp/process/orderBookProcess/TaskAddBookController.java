package com.mycompany.myapp.process.orderBookProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order-book-process/task-add-book")
public class TaskAddBookController {

    private final Logger log = LoggerFactory.getLogger(TaskAddBookController.class);

    private final TaskAddBookService taskAddBookService;

    public TaskAddBookController(TaskAddBookService taskAddBookService) {
        this.taskAddBookService = taskAddBookService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskAddBookContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskAddBookContextDTO taskAddBookContext = taskAddBookService.loadContext(id);
        return ResponseEntity.ok(taskAddBookContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<TaskAddBookContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskAddBookContextDTO taskAddBookContext = taskAddBookService.claim(id);
        return ResponseEntity.ok(taskAddBookContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody TaskAddBookContextDTO taskAddBookContext) {
        log.debug("REST request to complete OrderBookProcess.TaskAddBook {}", taskAddBookContext.getTaskInstance().getId());
        taskAddBookService.complete(taskAddBookContext);
        return ResponseEntity.noContent().build();
    }
}
