package com.shin.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Apply {
    String apply_name; //ai应用名称
    int invoking_count; //被调用的总次数
    Boolean is_open; //状态
}
