package ru.t1.pmorozov.repository;

public enum Queries {
    ;
    public static final String INSERT_USER = "insert into USERS (username) values (?)";
    public static final String UPDATE_USER = "update USERS set username = ? where id = ?";
    public static final String DELETE_USER = "delete from USERS where id = ?";
    public static final String DELETE_ALL_USERS = "delete from USERS";
    public static final String SELECT_ALL_USERS = "select * from USERS";
    public static final String FIND_USER_BY_ID = "select * from USERS where id = ?";
}
