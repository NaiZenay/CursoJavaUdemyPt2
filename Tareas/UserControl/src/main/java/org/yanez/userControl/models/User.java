package org.yanez.userControl.models;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String email;


    private String hidePassWord(){
        StringBuilder sb= new StringBuilder();
        sb.append("*".repeat(password.length()));
        return sb.toString();
    }
    @Override
    public String toString() {
        return "\nUSER #"+id+"\n" +
                "\tName: "+ name+"\n" +
                "\tEmail: " + email+"\n" +
                "\tPassword: "+ hidePassWord() +"\n";
    }
}
