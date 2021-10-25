package payroll;

import java.util.Set;
import java.util.HashSet;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
class Employee {

  private @Id @GeneratedValue Long id;
  private String name;
  private String role;
  
  @ManyToMany
  private Set<Skill> acquiredSkills = new HashSet<Skill>();

Employee() {}

  Employee(String name, String role) {

    this.name = name;
    this.role = role;
  }

  public Long getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public String getRole() {
    return this.role;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setRole(String role) {
    this.role = role;
  }
  
  public Set<Skill> getAcquiredSkills() {
    return acquiredSkills;
  }
  
  public void addSkill(Skill skill) {
    acquiredSkills.add(skill);
  }

  public void setAcquiredSkills(Set<Skill> acquiredSkills) {
    this.acquiredSkills = acquiredSkills;
  }

  @Override
  public boolean equals(Object o) {

    if (this == o)
      return true;
    if (!(o instanceof Employee))
      return false;
    Employee employee = (Employee) o;
    return Objects.equals(this.id, employee.id) && Objects.equals(this.name, employee.name)
        && Objects.equals(this.role, employee.role);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.name, this.role);
  }

  @Override
  public String toString() {
    return "Employee{" + "id=" + this.id + ", name='" + this.name + '\'' + ", role='" + this.role + '\'' + ", skills = " + acquiredSkills.toString() + '}';
  }
}
