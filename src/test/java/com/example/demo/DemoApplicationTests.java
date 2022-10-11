package com.example.demo;

import com.example.demo.student.Student;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void whenUsingMultipleFilters_dataShouldBeFiltered(){
		Student moh = new Student(
				"Eldeib",
				"Mohamed",
				"mohamed@gmail.com",
				LocalDate.of(1990,03,31)
		);
		Student ruka = new Student(
				"Eldeib",
				"Ruka",
				"ruka@gmail.com",
				LocalDate.of(1997,03,31)
		);
		Student abdo = new Student(
				"Eldeib",
				"abdo",
				"abdo@gmail.com",
				LocalDate.of(1999,03,31)
		);


		List<Student> students = List.of(moh, ruka, abdo);

		List<Student> filterStream = students.stream()
				.filter(s -> s.getAge() > 30)
				.filter(s -> s.getName() == "MohamedEldeib")
				.collect(Collectors.toList());


		assertThat(filterStream).containsExactly();

	}

	@Test
	public void isEligibleForScholarshipTest(){

		Student moh = new Student(
				"Eldeib",
				"Mohamed",
				"mohamed@gmail.com",
				LocalDate.of(1990,03,31)
		);
		Student ruka = new Student(
				"Eldeib",
				"Ruka",
				"ruka@gmail.com",
				LocalDate.of(1997,03,31)
		);
		Student abdo = new Student(
				"Eldeib",
				"abdo",
				"abdo@gmail.com",
				LocalDate.of(1999,03,31)
		);

		Comparator<Student> studentComparator = new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				return o1.getAge() - o2.getAge();
			}
		};

		Comparator<Student> nameComparator = new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				System.out.println(o2.getName().compareTo(o1.getName()));
				return o2.getName().compareTo(o1.getName());
			}
		};

		List<Student> students = List.of(moh, ruka, abdo);

		for (Student s : students){
			System.out.println(s);
		}

		System.out.println("-------------ordered------------");

		List<Student> orderedStudent = students
				.stream()
				.sorted(nameComparator)
				.collect(Collectors.toList());

		for (Student s : orderedStudent){
			System.out.println(s);
		}

		List<Student> filterStream = students.stream()
				.filter(Student::isEligibleForScholarship)
				.collect(Collectors.toList());

		assertThat(filterStream).containsExactly(ruka, abdo);
	}

}
