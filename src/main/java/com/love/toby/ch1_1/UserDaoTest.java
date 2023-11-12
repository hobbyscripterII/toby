package com.love.toby.ch1_1;

import java.sql.SQLException;

public class UserDaoTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 인자에 사용할 ConnectionMaker 타입의 객체를 제공한다.
        // 두 객체 사이의 의존관계를 설정한다.
        UserDao nUserDao = new UserDao(new NConnectionMaker());
        UserDao dUserDao = new UserDao(new DConnectionMaker());

        UserDto userDto = new UserDto();
        userDto.setId("whiteship");
        userDto.setName("백기선");
        userDto.setPassword("married");
        nUserDao.add(userDto);

        UserDto userDto2 = dUserDao.get(userDto.getId());
        nUserDao.print(userDto2);

        UserDto userDto3 = new UserDto();
        userDto3.setId("blackship");
        userDto3.setName("흑기선");
        userDto3.setPassword("married");
        dUserDao.add(userDto3);

        UserDto userDto4 = dUserDao.get(userDto3.getId());
        dUserDao.print(userDto4);
    }
}
