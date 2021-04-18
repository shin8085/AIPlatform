package com.shin.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserInfo {
    private String username;
    private String sex;
    private Date birthday;
    private String mail;
    private Date join_date;
}
