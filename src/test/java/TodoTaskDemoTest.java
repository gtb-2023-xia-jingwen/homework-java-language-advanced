import com.tw.javabasic.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class TodoTaskDemoTest {
    @Nested
    class Part1 {
        @Nested
        class Stage1 {
            private List<TodoTask> allTasks;

            @BeforeEach
            void setUp() {
                this.allTasks = Arrays.asList(
                        new TodoTask(1, "Task 01", TodoTaskStatus.ToBeDone),
                        new TodoTask(2, "Task 02", TodoTaskStatus.ToBeDone),
                        new TodoTask(3, "Task 03", TodoTaskStatus.Completed),
                        new TodoTask(4, "Task 04", TodoTaskStatus.Completed),
                        new TodoTask(5, "Task 05", TodoTaskStatus.Deleted)
                );
            }

            @ParameterizedTest
            @ValueSource(strings = {"Task 01", "Task 02", "Task 03", "Task 04", "Task 05"})
            public void should_return_task_by_given_name(String givenName) {
                // Given
                final var todoTaskRepository = new TodoTaskRepository(this.allTasks);
                // When
                final var taskListFound = todoTaskRepository.findByName(givenName);
                // Then
                assertTrue(taskListFound.size() > 0);
                for (final TodoTask taskFound : taskListFound) {
                    assertEquals(givenName, taskFound.getName());
                }
            }

            @ParameterizedTest
            @ValueSource(ints = {1, 2, 3, 4, 5})
            public void should_return_task_by_given_id(int givenId) {
                // Given
                final var todoTaskRepository = new TodoTaskRepository(this.allTasks);
                // When
                final var taskListFound = todoTaskRepository.findById(givenId);
                // Then
                assertTrue(taskListFound.size() > 0);
                for (final TodoTask taskFound : taskListFound) {
                    assertEquals(givenId, taskFound.getID());
                }
            }

            @ParameterizedTest
            @EnumSource(TodoTaskStatus.class)
            public void should_return_task_by_given_status(TodoTaskStatus givenStatus) {
                // Given
                final var todoTaskRepository = new TodoTaskRepository(this.allTasks);
                // When
                final var taskListFound = todoTaskRepository.findByStatus(givenStatus);
                // Then
                assertTrue(taskListFound.size() > 0);
                for (final TodoTask taskFound : taskListFound) {
                    assertEquals(givenStatus, taskFound.getStatus());
                }
            }
        }
    }
        @Nested
        class Stage2 {
            private List<TodoTask> allTasks;

            @BeforeEach
            void setUp() {
                this.allTasks = Arrays.asList(
                        new TodoTask(1, "Task 01", TodoTaskStatus.ToBeDone, TodoTaskTag.None),
                        new TodoTask(2, "Task 02", TodoTaskStatus.ToBeDone, TodoTaskTag.Urgent),
                        new TodoTask(3, "Task 03", TodoTaskStatus.Completed, TodoTaskTag.Important),
                        new TodoTask(4, "Task 04", TodoTaskStatus.Completed, TodoTaskTag.ImportantAndUrgent),
                        new TodoTask(5, "Task 05", TodoTaskStatus.Deleted, TodoTaskTag.None)
                );
            }

            @ParameterizedTest
            @EnumSource(TodoTaskTag.class)
            public void should_return_task_by_given_tags(TodoTaskTag givenTag) {
                // Given
                final var todoTaskRepository = new TodoTaskRepository(this.allTasks);
                // When
                final var taskListFound = todoTaskRepository.findByTag(givenTag);
                // Then
                assertTrue(taskListFound.size() > 0);
                for (final TodoTask taskFound : taskListFound) {
                    assertEquals(givenTag, taskFound.getTag());
                }
            }
        }
        @Nested
        class Stage3 {
            private List<TodoTask> allTasks;

            @BeforeEach
            void setUp() {
                this.allTasks = Arrays.asList(
                        new TodoTask(1, "Task 01", TodoTaskStatus.ToBeDone, TodoTaskTag.None),
                        new TodoTask(2, "Task 02", TodoTaskStatus.ToBeDone, TodoTaskTag.Urgent),
                        new TodoTask(3, "Task 03", TodoTaskStatus.Completed, TodoTaskTag.Important),
                        new TodoTask(4, "Task 04", TodoTaskStatus.Completed, TodoTaskTag.ImportantAndUrgent),
                        new TodoTask(5, "Task 05", TodoTaskStatus.Deleted, TodoTaskTag.None)
                );
            }

            @ParameterizedTest
            @ValueSource(strings = {"Task 01", "Task 02", "Task 03", "Task 04", "Task 05"})
            public void should_return_task_by_given_name(String givenName) {
                // Given
                final var todoTaskRepository = new TodoTaskRepository(this.allTasks);
                // When
                final var taskListFound = todoTaskRepository.find(givenName, new TodoTaskNameComparator());
                // Then
                assertTrue(taskListFound.size() > 0);
                for (final TodoTask taskFound : taskListFound) {
                    assertEquals(givenName, taskFound.getName());
                }
            }

            @ParameterizedTest
            @ValueSource(ints = {1, 2, 3, 4, 5})
            public void should_return_task_by_given_id(int givenId) {
                // Given
                final var todoTaskRepository = new TodoTaskRepository(this.allTasks);
                // When
                final var taskListFound = todoTaskRepository.find(givenId, new TodoTaskIdComparator());
                // Then
                assertTrue(taskListFound.size() > 0);
                for (final TodoTask taskFound : taskListFound) {
                    assertEquals(givenId, taskFound.getID());
                }
            }

            @ParameterizedTest
            @EnumSource(TodoTaskStatus.class)
            public void should_return_task_by_given_status(TodoTaskStatus givenStatus) {
                // Given
                final var todoTaskRepository = new TodoTaskRepository(this.allTasks);
                // When
                final var taskListFound = todoTaskRepository.find(givenStatus, new TodoTaskStatusComparator());
                // Then
                assertNotNull(taskListFound);
                assertTrue(taskListFound.size() > 0);
                for (final TodoTask taskFound : taskListFound) {
                    assertEquals(givenStatus, taskFound.getStatus());
                }
            }

            @ParameterizedTest
            @EnumSource(TodoTaskTag.class)
            public void should_return_task_by_given_tags(TodoTaskTag givenTag) {
                // Given
                final var todoTaskRepository = new TodoTaskRepository(this.allTasks);
                // When
                final var taskListFound = todoTaskRepository.find(givenTag, new TodoTaskTagsComparator());
                // Then
                assertTrue(taskListFound.size() > 0);
                for (final TodoTask taskFound : taskListFound) {
                    assertEquals(givenTag, taskFound.getTag());
                }
            }
        }

        @Nested
        class Stage4_5 {
            private List<TodoTask> allTasks;

            @BeforeEach
            void setUp() {
                this.allTasks = Arrays.asList(
                        new TodoTask(1, "Task 01", TodoTaskStatus.ToBeDone, TodoTaskTag.None),
                        new TodoTask(2, "Task 02", TodoTaskStatus.ToBeDone, TodoTaskTag.Urgent),
                        new TodoTask(3, "Task 03", TodoTaskStatus.Completed, TodoTaskTag.Important),
                        new TodoTask(4, "Task 04", TodoTaskStatus.Completed, TodoTaskTag.ImportantAndUrgent),
                        new TodoTask(5, "Task 05", TodoTaskStatus.Deleted, TodoTaskTag.None)
                );
            }

            @ParameterizedTest
            @ValueSource(strings = {"Task 01", "Task 02", "Task 03", "Task 04", "Task 05"})
            public void should_return_task_by_given_name(String givenName) {
                // Given
                final var todoTaskRepository = new TodoTaskRepository(this.allTasks);
                // When
                final var taskListFound = todoTaskRepository.findWithJavaFunctionAPI(
                        givenName, (name, task) -> name.equals(task.getName())
                );
                // Then
                assertTrue(taskListFound.size() > 0);
                for (final TodoTask taskFound : taskListFound) {
                    assertEquals(givenName, taskFound.getName());
                }
            }

            @ParameterizedTest
            @ValueSource(ints = {1, 2, 3, 4, 5})
            public void should_return_task_by_given_id(int givenId) {
                // Given
                final var todoTaskRepository = new TodoTaskRepository(this.allTasks);
                // When
                final var taskListFound = todoTaskRepository.findWithJavaFunctionAPI(
                        givenId, (id, task) -> id == task.getID()
                );
                // Then
                assertTrue(taskListFound.size() > 0);
                for (final TodoTask taskFound : taskListFound) {
                    assertEquals(givenId, taskFound.getID());
                }
            }

            @ParameterizedTest
            @EnumSource(TodoTaskStatus.class)
            public void should_return_task_by_given_status(TodoTaskStatus givenStatus) {
                // Given
                final var todoTaskRepository = new TodoTaskRepository(this.allTasks);
                // When
                final var taskListFound = todoTaskRepository.findWithJavaFunctionAPI(
                        givenStatus, (status, task) -> status == task.getStatus()
                );
                // Then
                assertTrue(taskListFound.size() > 0);
                for (final TodoTask taskFound : taskListFound) {
                    assertEquals(givenStatus, taskFound.getStatus());
                }
            }

            @ParameterizedTest
            @EnumSource(TodoTaskTag.class)
            public void should_return_task_by_given_tags(TodoTaskTag givenTag) {
                // Given
                final var todoTaskRepository = new TodoTaskRepository(this.allTasks);
                // When

                final var taskListFound = todoTaskRepository.findWithJavaFunctionAPI(
                        givenTag, (tag, task) -> tag == task.getTag());
                // Then
                assertTrue(taskListFound.size() > 0);
                for (final TodoTask taskFound : taskListFound) {
                    assertEquals(givenTag, taskFound.getTag());
                }
            }
        }

    @Nested
    class Part2 {
        @Nested
        class Stage1 {
            private TodoTask[] allTasks;

            @BeforeEach
            void setUp() {
                this.allTasks = new TodoTask[] {
                        new TodoTask(1, "Task 01", TodoTaskStatus.ToBeDone, TodoTaskTag.None),
                        new TodoTask(2, "Task 02", TodoTaskStatus.ToBeDone, TodoTaskTag.Urgent),
                        new TodoTask(3, "Task 03", TodoTaskStatus.Completed, TodoTaskTag.Important),
                        new TodoTask(4, "Task 04", TodoTaskStatus.Completed, TodoTaskTag.ImportantAndUrgent),
                        new TodoTask(5, "Task 05", TodoTaskStatus.Deleted, TodoTaskTag.None)
                };
            }

            @Test
            public void should_generate_iterator_given_tasks_array() {
                // When
                final var todoTasksIterableArray = new IterableArray<>(allTasks);
                final var iterator = todoTasksIterableArray.iterator();
                //Then
                for (final TodoTask task : allTasks) {
                    final var taskVisitedByIterator = iterator.next();
                    assertEquals(task.getID(), taskVisitedByIterator.getID());
                    assertEquals(task.getName(), taskVisitedByIterator.getName());
                    assertEquals(task.getStatus(), taskVisitedByIterator.getStatus());
                }
            }
        }

        @Nested
        class Stage2 {
            private List<TodoTask> allTasks;

            @BeforeEach
            void setUp() {
                this.allTasks = Arrays.asList(
                        new TodoTask(1, "Task 01", TodoTaskStatus.ToBeDone, TodoTaskTag.None),
                        new TodoTask(2, "Task 02", TodoTaskStatus.ToBeDone, TodoTaskTag.Urgent),
                        new TodoTask(3, "Task 03", TodoTaskStatus.Completed, TodoTaskTag.Important),
                        new TodoTask(4, "Task 04", TodoTaskStatus.Completed, TodoTaskTag.ImportantAndUrgent),
                        new TodoTask(5, "Task 05", TodoTaskStatus.Deleted, TodoTaskTag.None)
                );
            }

            @Test
            public void should_create_stream_by_iterator() {
                // Given
                final var todoTaskStream = new Stream<>(allTasks.iterator());
                // When
                final var iterator = todoTaskStream.getIterator();
                // Then
                for (final TodoTask task : allTasks) {
                    final var taskVisitedByStreamIterator = iterator.next();
                    assertEquals(task.getID(), taskVisitedByStreamIterator.getID());
                    assertEquals(task.getName(), taskVisitedByStreamIterator.getName());
                    assertEquals(task.getStatus(), taskVisitedByStreamIterator.getStatus());
                }
            }

            @Test
            public void should_generate_the_given_number_tasks() {
                // Given
                final int totalNumber = 99;
                // When
                Stream<TodoTask> infiniteStream = Stream.generate(() -> new TodoTask(1, "Task 01", TodoTaskStatus.ToBeDone, TodoTaskTag.None));
                // Then
                final var iterator = infiniteStream.getIterator();
                for (int i = 0; i < totalNumber; i++) {
                    final var task = iterator.next();
                    assertNotNull(task);
                    assertEquals(1, task.getID());
                    assertEquals("Task 01", task.getName());
                    assertEquals(TodoTaskStatus.ToBeDone, task.getStatus());
                    assertEquals(TodoTaskTag.None, task.getTag());
                }
            }
        }

        @Nested
        class Stage3 {
            private TodoTask[] allTasks;

            @BeforeEach
            void setUp() {
                this.allTasks = new TodoTask[] {
                        new TodoTask(1, "Task 01", TodoTaskStatus.ToBeDone, TodoTaskTag.None),
                        new TodoTask(2, "Task 02", TodoTaskStatus.ToBeDone, TodoTaskTag.Urgent),
                        new TodoTask(3, "Task 03", TodoTaskStatus.Completed, TodoTaskTag.Important),
                        new TodoTask(4, "Task 04", TodoTaskStatus.Completed, TodoTaskTag.ImportantAndUrgent),
                        new TodoTask(5, "Task 05", TodoTaskStatus.Deleted, TodoTaskTag.None)
                };
            }

            @Test
            public void should_filter_task_by_given_name() {
                // Given
                final var iterator = new IterableArray<>(allTasks).iterator();
                final var todoTaskStream = new Stream<>(iterator);
                final var givenName = "Task 01";
                // When
                final var tasksFound = todoTaskStream
                        .filter((task) -> givenName.equals(task.getName()))
                        .collect();
                //Then
                assertEquals(1, tasksFound.size());
                for (final TodoTask taskFound : tasksFound) {
                    assertEquals(givenName, taskFound.getName());
                }
            }

            @Test
            public void should_filter_task_by_given_status() {
                // Given
                final var iterator = new IterableArray<>(allTasks).iterator();
                final var todoTaskStream = new Stream<>(iterator);
                final var givenStatus = TodoTaskStatus.ToBeDone;
                // When
                final var tasksFound = todoTaskStream
                        .filter((task) -> givenStatus == task.getStatus())
                        .collect();
                //Then
                assertEquals(2, tasksFound.size());
                for (final TodoTask taskFound : tasksFound) {
                    assertEquals(givenStatus, taskFound.getStatus());
                }
            }

            @Test
            public void should_map_task_to_names() {
                // Given
                final var iterator = new IterableArray<>(allTasks).iterator();
                final var todoTaskStream = new Stream<>(iterator);

                // When
                final var tasksNames = todoTaskStream
                        .map(TodoTask::getName)
                        .collect();

                //Then
                final String[] expectedNames = { "Task 01", "Task 02", "Task 03", "Task 04", "Task 05" };
                assertEquals(5, tasksNames.size());
                for (int i = 0; i < expectedNames.length; i++) {
                    assertEquals(expectedNames[i], tasksNames.get(i));
                }
            }

            @Test
            public void should_map_task_to_ids() {
                // Given
                final var iterator = new IterableArray<>(allTasks).iterator();
                final var todoTaskStream = new Stream<>(iterator);
                // When
                List<Integer> tasksNames = todoTaskStream
                        .map(TodoTask::getID)
                        .collect();
                // Then
                final Integer[] expectedIds = { 1, 2, 3, 4, 5 };
                assertEquals(5, tasksNames.size());
                for (int i = 0; i < expectedIds.length; i++) {
                    assertEquals(expectedIds[i], tasksNames.get(i));
                }
            }

            @Test
            public void should_get_tasks_by_given_multiple_operation() {
                // Given
                final var iterator = new IterableArray<>(allTasks).iterator();
                final var todoTaskStream = new Stream<>(iterator);

                // When
                final var tasksNames = todoTaskStream
                        .filter(task -> task.getStatus() ==  TodoTaskStatus.ToBeDone)
                        .map(TodoTask::getName)
                        .collect();

                //Then
                final String[] expectedNames = { "Task 01", "Task 02" };
                assertEquals(2, tasksNames.size());
                for (int i = 0; i < expectedNames.length; i++) {
                    assertEquals(expectedNames[i], tasksNames.get(i));
                }
            }

            @Test
            public void should_get_empty_list_by_given_multiple_operation() {
                // Given
                final var iterator = new IterableArray<>(allTasks).iterator();
                final var todoTaskStream = new Stream<>(iterator);

                // When
                final var tasksNames = todoTaskStream
                        .filter(task -> "Not Existed Task".equals(task.getName()))
                        .map(TodoTask::getName)
                        .collect();

                //Then
                assertEquals(0, tasksNames.size());
            }
        }

        @Nested
        class Stage4 {
            private TodoTask[] allTasks;

            @BeforeEach
            void setUp() {
                this.allTasks = new TodoTask[] {
                        new TodoTask(1, "Task 01", TodoTaskStatus.ToBeDone, TodoTaskTag.None),
                        new TodoTask(2, "Task 02", TodoTaskStatus.ToBeDone, TodoTaskTag.Urgent),
                        new TodoTask(3, "Task 03", TodoTaskStatus.Completed, TodoTaskTag.Important),
                        new TodoTask(4, "Task 04", TodoTaskStatus.Completed, TodoTaskTag.ImportantAndUrgent),
                        new TodoTask(5, "Task 05", TodoTaskStatus.Deleted, TodoTaskTag.None)
                };
            }

            @Test
            public void should_collect_tasks_by_given_list_supplier_and_consumer(){
                // Given
                final var iterator = new IterableArray<TodoTask>(allTasks).iterator();
                final var todoTaskStream = new Stream<TodoTask>(iterator);
                Supplier<List<TodoTask>> containerSupplier = () -> new ArrayList<TodoTask>();
                BiConsumer<List<TodoTask>,TodoTask> accumulator = (container, task) -> container.add(task);

                // When
                final var tasks = todoTaskStream.collect(containerSupplier, accumulator);

                // Then
                assertEquals(5, tasks.size());
                for (int i = 0; i < allTasks.length; i++) {
                    final var expectedTask = allTasks[i];
                    final var todoTaskInResult = tasks.get(i);
                    assertEquals(expectedTask.getID(), todoTaskInResult.getID());
                    assertEquals(expectedTask.getName(), todoTaskInResult.getName());
                    assertEquals(expectedTask.getStatus(), todoTaskInResult.getStatus());

                }
            }

            @Test
            public void should_collect_tasks_by_given_map_supplier_and_consumer(){
                // Given
                final var iterator = new IterableArray<TodoTask>(allTasks).iterator();
                final var todoTaskStream = new Stream<TodoTask>(iterator);
                Supplier<HashMap<Integer,TodoTask>> containerSupplier = () -> new HashMap<Integer,TodoTask>();
                BiConsumer<HashMap<Integer,TodoTask>,TodoTask> accumulator = (container, task) -> container.put(task.getID(), task);
                // When
                final var tasksMap = todoTaskStream.collect(containerSupplier, accumulator);
                // Then
                assertEquals(5, tasksMap.size());
                for (final TodoTask expectedTask : allTasks) {
                    final var todoTaskInResult = tasksMap.get(expectedTask.getID());
                    assertNotNull(todoTaskInResult);
                    assertEquals(expectedTask.getID(), todoTaskInResult.getID());
                    assertEquals(expectedTask.getName(), todoTaskInResult.getName());
                    assertEquals(expectedTask.getStatus(), todoTaskInResult.getStatus());
                }
            }
        }

        @Nested
        class Stage5 {
            private TodoTask[] allTasks;

            @BeforeEach
            void setUp() {
                this.allTasks = new TodoTask[] {
                        new TodoTask(1, "Task 01", TodoTaskStatus.ToBeDone, TodoTaskTag.None),
                        new TodoTask(2, "Task 02", TodoTaskStatus.ToBeDone, TodoTaskTag.Urgent),
                        new TodoTask(3, "Task 03", TodoTaskStatus.Completed, TodoTaskTag.Important),
                        new TodoTask(4, "Task 04", TodoTaskStatus.Completed, TodoTaskTag.ImportantAndUrgent),
                        new TodoTask(5, "Task 05", TodoTaskStatus.Deleted, TodoTaskTag.None)
                };
            }

            @Test
            public void should_calculate_total_tasks_name_length_by_given_reducer(){
                // Given
                final var iterator = new IterableArray<TodoTask>(allTasks).iterator();
                final var todoTaskStream = new Stream<TodoTask>(iterator);
                // When
                final var tasksCount = todoTaskStream.reduce(0, ( sum, task ) -> sum + task.getName().length());
                // Then
                assertEquals(35, tasksCount);
            }
        }
    }
//
//    @Nested
    @Nested
    class Part3 {
        @Nested
        class StageCreate {
            @Test
            public void should_create_stream_by_list() {
                // Given
                final List<String> givenTaskNames = Arrays.asList("Task 01", "Task 02", "Task 03", "Task 04", "Task 05");
                // When
                final List<String> collectedNames = givenTaskNames
                        .stream()
                        .collect(Collectors.toList());
                // Then
                assertIterableEquals(givenTaskNames, collectedNames);
            }
            @Test
            public void should_create_stream_by_of() {
                // Given
                final String[] givenTaskNames = { "Task 01", "Task 02", "Task 03", "Task 04", "Task 05" };
                // When
                final String[] collectedNames = java.util.stream.Stream
                        .of(givenTaskNames)
                        .toArray(String[]::new);
                // Then
                assertArrayEquals(givenTaskNames, collectedNames);
            }
            @Test
            public void should_create_stream_by_generate() {
                // Given
                final String givenName = "Task 01";
                final int givenTimes = 10;
                // When
                final var collectedNames = java.util.stream.Stream
                        .generate(() -> givenName)
                        .limit(givenTimes)
                        .collect(Collectors.toList());
                // Then
                assertEquals(givenTimes, collectedNames.size());
                for (final String generateName : collectedNames) {
                    assertEquals(givenName, generateName);
                }
            }
            @Test
            public void should_create_stream_by_iterate() {
                // Given
                final int givenId = 1;
                final int givenTimes = 10;
                // When
                final var idsGenerated = java.util.stream.Stream
                        .iterate(givenId, id-> id + 1)
                        .limit(givenTimes)
                        .collect(Collectors.toList());
                // Then
                assertEquals(10, idsGenerated.size());
                assertEquals(10, idsGenerated.get(idsGenerated.size()-1));
            }
        }
        @Nested
        class StageIntermediate {
            private List<TodoTask> allTasks;
            @BeforeEach
            void setUp() {
                this.allTasks = Arrays.asList(
                        new TodoTask(1, "Task 01", TodoTaskStatus.ToBeDone),
                        new TodoTask(2, "Task 02", TodoTaskStatus.ToBeDone),
                        new TodoTask(3, "Task 03", TodoTaskStatus.Completed),
                        new TodoTask(4, "Task 04", TodoTaskStatus.Completed),
                        new TodoTask(5, "Task 05", TodoTaskStatus.Deleted)
                );
            }
            @Test
            public void should_get_tasks_by_given_filter() {
                // Given
                // When
                final var tasksFound = allTasks
                        .stream()
                        .filter(task -> task.getStatus() == TodoTaskStatus.Deleted)
                        .collect(Collectors.toList());
                // Then
                assertEquals(1, tasksFound.size());
                final var todoTaskFound = tasksFound.get(0);
                assertEquals(TodoTaskStatus.Deleted, todoTaskFound.getStatus());
                assertEquals(5, todoTaskFound.getID());
            }
            @Test
            public void should_get_mapped_tasks() {
                // Given
                final String[] givenTaskNames = { "TASK 01", "TASK 02", "TASK 03", "TASK 04", "TASK 05" };
                // When
                final var mappedNames = allTasks
                        .stream()
                        .map(task -> task.getName().toUpperCase())
                        .toArray(String[]::new);
                // Then
                assertArrayEquals(givenTaskNames, mappedNames);
            }
            @Test
            public void should_get_flat_mapped_tasks() {
                // Given
                allTasks.get(0).setNotes(Arrays.asList("Note 1-1"));
                allTasks.get(1).setNotes(Arrays.asList("Note 2-1", "Note 2-2"));
                allTasks.get(2).setNotes(Arrays.asList("Note 3-1", "Note 3-2", "Note 3-3"));
                allTasks.get(3).setNotes(Arrays.asList("Note 4-1", "Note 4-2", "Note 4-3", "Note 4-4"));
                allTasks.get(4).setNotes(Arrays.asList("Note 5-1", "Note 5-2", "Note 5-3", "Note 5-4", "Note 5-5"));

                final var expectedNotes = new String[] {
                        "Note 1-1", "Note 2-1", "Note 2-2", "Note 3-1", "Note 3-2", "Note 3-3", "Note 4-1",
                        "Note 4-2", "Note 4-3", "Note 4-4", "Note 5-1", "Note 5-2", "Note 5-3", "Note 5-4", "Note 5-5",
                };

                // When
                final var notes = allTasks
                        .stream()
                        .flatMap(task -> task.getNotes().stream())
                        .toArray(String[]::new);
                // Then
                assertArrayEquals(expectedNotes, notes);
            }
            @ParameterizedTest
            @ValueSource(ints = {0, 1, 2, 3, 4, 5})
            public void should_get_limit_tasks(int givenTimes) {
                // Given
                // When
                final var tasksFound = allTasks
                        .stream()
                        .limit(givenTimes)
                        .collect(Collectors.toList());
                // Then
                assertEquals(givenTimes, tasksFound.size());
            }
            @Test
            public void should_get_distinct_tasks() {
                // Given
                var expectedStatus = Arrays.asList(
                        TodoTaskStatus.ToBeDone,
                         TodoTaskStatus.Completed,
                        TodoTaskStatus.Deleted
                );
                // When
                final var distinctStatus = allTasks
                        .stream()
                        .map(TodoTask::getStatus)
                        .distinct()
                        .collect(Collectors.toList());
                // Then
                assertIterableEquals(expectedStatus, distinctStatus);
            }
        }
        @Nested
        class StageTerminal {
            private List<TodoTask> allTasks;
            @BeforeEach
            void setUp() {
                this.allTasks = Arrays.asList(
                        new TodoTask(1, "Task 01", TodoTaskStatus.ToBeDone),
                        new TodoTask(2, "Task 02", TodoTaskStatus.ToBeDone),
                        new TodoTask(3, "Task 03", TodoTaskStatus.Completed),
                        new TodoTask(4, "Task 04", TodoTaskStatus.Completed),
                        new TodoTask(5, "Task 05", TodoTaskStatus.Deleted)
                );
            }
            @Test
            public void should_get_min_task() {
                // Given
                int expectedTaskId = 1;
                // When
                final var minTaskOptional = allTasks
                        .stream()
                        .min((t1,t2) -> t1.getID() - t2.getID());
                // Then
                assertTrue(minTaskOptional.isPresent());
                assertEquals(expectedTaskId, minTaskOptional.get().getID());
            }
            @Test
            public void should_get_total_number_of_tasks() {
                // Given
                var expectedCount = 4;
                // When
                final var taskCount = allTasks
                        .stream()
                        .filter(task -> task.getID() > 1)
                        .count();
                // Then
                assertEquals(expectedCount, taskCount);
            }
            @ParameterizedTest
            @ValueSource(ints = { 1, 2, 3, 4, 5})
            public void should_collect_task_in_HashMap(int givenId) {
                // Given
                // When
                final var tasksDict =allTasks
                        .stream()
                        .parallel()
                        .collect(
                                ()-> new HashMap<Integer, TodoTask>(),
                                (map, element) -> map.put(element.getID(), element),
                                (HashMap::putAll)
                        );
                // Then
                final var task = tasksDict.get(givenId);
                assertEquals(givenId, task.getID());
            }
            @Test
            public void should_get_total_length_of_task_names() {
                // Given
                var expectedTotalLengths = 35;
                // When
                final var totalLengths = allTasks
                        .stream()
                        .reduce(0, (sum, task) -> sum + task.getName().length(), Integer::sum);;
                // Then
                assertEquals(expectedTotalLengths, totalLengths);
            }
        }
    }

    @Nested
    class Part4 {
        private List<TodoTask> allTasks;
        private TodoTask taskWithSubtask;
        private TodoTask taskWithoutSubtask;

        @BeforeEach
        void setUp() {
            TodoTask subTask = new TodoTask(99, "Subtask", TodoTaskStatus.ToBeDone, TodoTaskTag.None);

            this.taskWithSubtask = new TodoTask(1, "Task with Subtask", TodoTaskStatus.ToBeDone, TodoTaskTag.None, subTask);
            this.taskWithoutSubtask = new TodoTask(2, "Task without Subtask", TodoTaskStatus.ToBeDone, TodoTaskTag.None);

            final var todoTask3 = new TodoTask(3, "Task 03", TodoTaskStatus.Completed, TodoTaskTag.None, subTask);
            final var todoTask4 = new TodoTask(4, "Task 04", TodoTaskStatus.Completed, TodoTaskTag.None);
            final var todoTask5 = new TodoTask(5, "Task 05", TodoTaskStatus.Deleted, TodoTaskTag.None, subTask);

            this.allTasks = Arrays.asList( taskWithSubtask, taskWithoutSubtask, todoTask3, todoTask4, todoTask5 );
        }

        @Test
        public void should_map_task_name_value_with_optional(){
            final var optionalTask = taskWithSubtask.getSubTask();
            final var optionalEmptyTask = taskWithoutSubtask.getSubTask();

            final var optionalTaskName = optionalTask.map(TodoTask::getName);
            final var optionalEmptyTaskName = optionalEmptyTask.map(TodoTask::getName);

            assertTrue( optionalTaskName.isPresent() );
            assertEquals( "Subtask", optionalTaskName.get());
            assertFalse( optionalEmptyTaskName.isPresent() );
        }

        @Test
        public void should_filter_out_task_value_with_optional(){
            // Given
            final var optionalTask = taskWithSubtask.getSubTask(); // SubTask id is 99
            // When
            final var filteredOptionalTask = optionalTask.filter(task -> task.getID() == 99);
            final var filteredOptionalEmptyTask = optionalTask.filter(task -> task.getID() == 1024);
            // Then
            assertTrue( filteredOptionalTask.isPresent() );
            assertFalse( filteredOptionalEmptyTask.isPresent() );
        }

        @Test
        public void should_filter_out_task_value_with_empty_optional_and_none_null_pointer_exception(){
            // Given
            final var optionalEmptyTask = taskWithoutSubtask.getSubTask();
            // When
            final var filteredOptionalEmptyTask = optionalEmptyTask.filter(task->task.getID() == 99);
            // Then
            assertEquals(Optional.empty(), filteredOptionalEmptyTask);
        }

        @Test
        public void should_not_wrap_value_with_optional_by_flatmap() {
            final var taskWithSubtaskOptional = Optional.of(this.taskWithSubtask);

            final var duplicatedWrappedOptionalTask = taskWithSubtaskOptional.map(TodoTask::getSubTask);
            // flatMap 和 Map 相似，不过 map 会使用 Optional 包裹 value 但 flatMap 不会。
            // 因此 Value 本身就是 Optional 的时候可以使用 flatMap
            final var optionalTask = taskWithSubtaskOptional.flatMap(TodoTask::getSubTask);

            assertTrue(duplicatedWrappedOptionalTask.isPresent());
            assertEquals(Optional.class, duplicatedWrappedOptionalTask.get().getClass());
            assertTrue(optionalTask.isPresent());
            assertEquals(TodoTask.class, optionalTask.get().getClass());
        }

        @Test
        public void should_filter_out_empty_value_when_collect_all_subtasks() {

            final var optionalStream = allTasks.stream().map(TodoTask::getSubTask);
//            var subTasksNumber = optionalStream
//                    .filter(optionalTodoTask -> optionalTodoTask.isPresent())
//                    .map(optionalTodoTask -> optionalTodoTask.get())
//                    .count();

            // turns an Optional<T> into a Stream<T> with zero or one element.
            var subTasksNumber = optionalStream
                    .flatMap(Optional::stream)
                    .count();

            assertEquals(3, subTasksNumber);
        }
    }
}