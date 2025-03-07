import org.example.Employee;
import org.example.Main;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void testFindEmployeesWithSalaryGreaterThan_EmptyList() {
        List<Employee> employeeList = new ArrayList<>();
        List<Employee> result = Main.FindEmployeesWithSalaryGreaterThan(employeeList, 4000);
        assertEquals(0, result.size());
    }

    @Test
    void testFindEmployeesWithSalaryGreaterThan_NoMatch() {
        List<Employee> employeeList = Arrays.asList(
                new Employee("David", 2000, "HR"),
                new Employee("Alice", 3000, "IT")
        );
        List<Employee> result = Main.FindEmployeesWithSalaryGreaterThan(employeeList, 4000);
        assertEquals(0, result.size());
    }

    @Test
    void testFindEmployeesWithSalaryGreaterThan_SomeMatch() {
        List<Employee> employeeList = Arrays.asList(
                new Employee("David", 5000, "HR"),
                new Employee("Alice", 3000, "IT"),
                new Employee("Bob", 7000, "Finance")
        );
        List<Employee> result = Main.FindEmployeesWithSalaryGreaterThan(employeeList, 4000);
        assertEquals(2, result.size());
        assertTrue(result.contains(employeeList.get(0))); // David
        assertTrue(result.contains(employeeList.get(2))); // Bob
    }

    @Test
    void testSortEmployeesByNameAscending_EmptyList() {
        List<Employee> employeeList = new ArrayList<>();
        List<Employee> result = Main.SortEmployeesByNameAscending(employeeList);
        assertEquals(0, result.size());
    }

    @Test
    void testSortEmployeesByNameAscending_SortedCorrectly() {
        List<Employee> employeeList = Arrays.asList(
                new Employee("Charlie", 4000, "Support"),
                new Employee("Alice", 6000, "IT"),
                new Employee("Bob", 5000, "HR")
        );
        List<Employee> result = Main.SortEmployeesByNameAscending(employeeList);
        assertEquals("Alice", result.get(0).getName());
        assertEquals("Bob", result.get(1).getName());
        assertEquals("Charlie", result.get(2).getName());
    }

    @Test
    void testGetEmployeesName_EmptyList() {
        List<Employee> employeeList = new ArrayList<>();
        List<String> result = Main.GetEmployeesName(employeeList);
        assertEquals(0, result.size());
    }

    @Test
    void testGetEmployeesName_ValidList() {
        List<Employee> employeeList = Arrays.asList(
                new Employee("Alice", 5000, "IT"),
                new Employee("Bob", 6000, "HR")
        );
        List<String> result = Main.GetEmployeesName(employeeList);
        assertEquals(Arrays.asList("Alice", "Bob"), result);
    }

    @Test
    void testAverageSalaryOfEmployeeList_EmptyList() {
        List<Employee> employeeList = new ArrayList<>();
        double result = Main.AverageSalaryOfEmployeeList(employeeList);
        assertEquals(0, result);
    }

    @Test
    void testAverageSalaryOfEmployeeList_ValidList() {
        List<Employee> employeeList = Arrays.asList(
                new Employee("Alice", 5000, "IT"),
                new Employee("Bob", 10000, "HR")
        );
        double result = Main.AverageSalaryOfEmployeeList(employeeList);
        assertEquals(7500, result);
    }

    @Test
    void testGroupEmployeesByDepartment_EmptyList() {
        List<Employee> employeeList = new ArrayList<>();
        List<Employee> result = Main.GroupEmployeesByDepartment(employeeList, "IT");
        assertEquals(0, result.size());
    }

    @Test
    void testGroupEmployeesByDepartment_NoMatch() {
        List<Employee> employeeList = Arrays.asList(
                new Employee("Alice", 5000, "HR"),
                new Employee("Bob", 7000, "Finance")
        );
        List<Employee> result = Main.GroupEmployeesByDepartment(employeeList, "IT");
        assertEquals(0, result.size());
    }

    @Test
    void testGroupEmployeesByDepartment_SomeMatch() {
        List<Employee> employeeList = Arrays.asList(
                new Employee("Alice", 5000, "IT"),
                new Employee("Bob", 7000, "Finance"),
                new Employee("Charlie", 8000, "IT")
        );
        List<Employee> result = Main.GroupEmployeesByDepartment(employeeList, "IT");
        assertEquals(2, result.size());
        assertTrue(result.stream().anyMatch(e -> e.getName().equals("Alice")));
        assertTrue(result.stream().anyMatch(e -> e.getName().equals("Charlie")));
    }

    @Test
    void testFindHighestPaidEmployee_EmptyList() {
        List<Employee> employeeList = new ArrayList<>();
        List<Employee> result = Main.FindHighestPaidEmployee(employeeList);
        assertEquals(0, result.size());
    }

    @Test
    void testFindHighestPaidEmployee_SingleHighest() {
        List<Employee> employeeList = Arrays.asList(
                new Employee("Alice", 5000, "IT"),
                new Employee("Bob", 10000, "HR"),
                new Employee("Charlie", 7000, "Finance")
        );
        List<Employee> result = Main.FindHighestPaidEmployee(employeeList);
        assertEquals(1, result.size());
        assertEquals("Bob", result.get(0).getName());
    }

    @Test
    void testFindHighestPaidEmployee_MultipleHighest() {
        List<Employee> employeeList = Arrays.asList(
                new Employee("Alice", 10000, "IT"),
                new Employee("Bob", 10000, "HR"),
                new Employee("Charlie", 7000, "Finance")
        );
        List<Employee> result = Main.FindHighestPaidEmployee(employeeList);
        assertEquals(2, result.size());
        assertTrue(result.stream().anyMatch(e -> e.getName().equals("Alice")));
        assertTrue(result.stream().anyMatch(e -> e.getName().equals("Bob")));
    }
}
