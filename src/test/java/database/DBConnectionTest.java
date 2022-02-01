package database;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import database.entity.Student;
import database.entity.Subject;
import database.utils.Database;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * This class shows some sample tests and how to create a test container
 * which runs a PostGreSQL database
 * @author Nils Reichstein, MaibornWolff GmbH
 */
@Testcontainers
class DBConnectionTest {

    //Setting up a docker container which runs the postgres docker image v.13 and creates a database with the name 'school'
    @Container
    private static final PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer<>("postgres:13")
            .withDatabaseName("school")
            .withUsername("postgres")
            .withPassword("password");


    //Registers the extension which provides interaction with the database in the test container
    @RegisterExtension
    public static final Database testDB = new Database();

    @Test
    void readAndWriteTest(){
        //-------- persist singel entity --------//
        Student student = Student.builder()
                .firstName("Max")
                .lastName("Mustermann")
                .build();

        Subject subject = Subject.builder()
                .name("Computer Science")
                .build();

        testDB.persistEntity(student);
        testDB.persistEntity(subject);
        //---------------------------------------//

        //------ persist multiple entities ------//
        List<Object> students = new ArrayList<>();
        List<Object> subjects = new ArrayList<>();

        Student student1 = Student.builder()
                .firstName("Paul")
                .lastName("Walker")
                .build();
        students.add(student1);

        Student student2 = Student.builder()
                .firstName("Michael")
                .lastName("Schumacher")
                .build();
        students.add(student2);

        Subject subject1 = Subject.builder()
                .name("English")
                .build();
        subjects.add(subject1);

        Subject subject2 = Subject.builder()
                .name("Math")
                .build();
        subjects.add(subject2);

        testDB.persistEntities(students);
        testDB.persistEntities(subjects);
        //---------------------------------------//

        //------ get entity from db ------//
        Student testStudent = testDB.findEntityById(Student.class ,student.getId());
        assertThat(testStudent.getFirstName()).isEqualTo("Max");

        Subject testSubject = testDB.findEntityById(Subject.class ,subject.getId());
        assertThat(testSubject.getName()).isEqualTo(subject.getName());
        //---------------------------------------//
    }
}
