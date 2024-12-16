package by.ita.je.configuration;

import by.ita.je.models.Student;
import by.ita.je.service.CourseWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service

public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private CourseWebService courseWebService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = courseWebService.readStudentBySurName(username);
        if(student == null ){
            throw new UsernameNotFoundException("Unknown student with login: " + username);
        }
        return User.builder()
                .username(student.getLogin())
                .password(student.getPassword())
                .roles(student.getRole())
                .build();
    }

   /* @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = courseWebService.readStudentBySurName(username);
        Teacher teacher = null;
        UserDetails userDetails = null;
        if(student == null && teacher != null){
            teacher = courseWebService.readTeacherBySurName(username);
            userDetails = User.builder()
                    .username(teacher.getLogin())
                    .password(teacher.getPassword())
                    .roles(teacher.getRole())
                    .build();
        }

        if (student != null && teacher == null){
            userDetails = User.builder()
                    .username(student.getLogin())
                    .password(student.getPassword())
                    .roles(student.getRole())
                    .build();
        }

        if(student == null && teacher == null ){
            throw new UsernameNotFoundException("Unknown user with login: " + username);
        }

        return userDetails;
    }*/

    /*public UserDetails loadTeacherByUsername(String username) throws UsernameNotFoundException {
        Teacher teacher = courseWebService.readTeacherBySurName(username);
        if(teacher == null){
            throw new UsernameNotFoundException("Unknown teacher with login: " + username);
        }
        UserDetails teacherDetails = User.builder()
                .username(teacher.getLogin())
                .password(teacher.getPassword())
                .roles(teacher.getRole())
                .build();
        return teacherDetails;
    }*/
}
