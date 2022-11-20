package com.fourengineering.yourpal.Services;
import com.fourengineering.yourpal.Entities.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class PasswordEncoder {

    public static byte[] encodePassword(String password){
      byte[] answer = password.getBytes(StandardCharsets.UTF_16);
      return answer;
    }

    public static boolean validatePassword(byte[] encodedPassword, String rawPassword){
        byte[] password = encodePassword(rawPassword);
        if(Arrays.equals(password,encodedPassword))
            return true;
        return false;
    }
}
