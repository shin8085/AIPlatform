package com.shin.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InvokingCount {
    String username;
    int face_recognize;
    int age_estimation;
    int object_detection;
    int smoke_detection;
    int distracted_driver_detection;
    int mask_detection;
    int gender_detection;
}
