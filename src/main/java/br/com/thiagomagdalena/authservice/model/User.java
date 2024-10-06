package br.com.thiagomagdalena.authservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder(toBuilder = true)
@Table("users")
public class User {

    @Id
    private Long id;
    private String username;
    private String password;
    private String role;

}
