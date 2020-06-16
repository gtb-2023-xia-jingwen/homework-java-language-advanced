This project is part of the pre-course. It contains test with the following form

```java
@Test
void should_format_string() {
    final String name = "Harry";
    final int age = 23;

    String text = String.format("Hello, %s. Next year, you will be %d.", name, age);

    // TODO: please modify the following code to pass the test
    // <--start
    final String expectedText = "";
    // --end-->

    assertEquals(expectedText, text);
}
```

The test contained in this project will _fail_ by default. __DO NOT__ run the test first! Please read the test to get a basic idea, then try to correct the test following the hint __TODO comment__. You are only permitted to modify code between `<--start` and `--end-->`.

For example. In the test above, we can get the idea that the knowledge point is how to format string using a template. After reading the code, we can do the following update:

```java
    // TODO: please modify the following code to pass the test
    // <--start
    final String expectedText = "Hello, Harry. Next year, you will be 23.";
    // --end-->
// --end-->
```

Then we can run the test again after modification to see if the test is corrected. If it is succeeded, please check all the codes related to this test (including the dependency demo class) and make sure you have fully understood them; while, if it fails again, please check the reason and try to correct it again.
