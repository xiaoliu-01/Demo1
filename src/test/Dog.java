package test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: LLXX
 * @Date: 2021/6/3 20:16
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dog {

    String name;

    String getObjectAddress() {
        return super.toString();
    }
}
