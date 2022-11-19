package Misc;

import Entities.BadHabits;
import Entities.Hobbies;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private final long id;
    private final String username;
    private final String email;
    private final char[] password;
    private final String Bio;
    private final boolean mentor;
    private final Hobbies hobbies;
    private final BadHabits badHabits;
}
