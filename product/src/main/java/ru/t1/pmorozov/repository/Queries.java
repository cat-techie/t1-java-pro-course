package ru.t1.pmorozov.repository;

public enum Queries {
    ;
    public static final String INSERT_USER = "insert into USERS (username) values (?)";
    public static final String UPDATE_USER = "update USERS set username = ? where id = ?";
    public static final String DELETE_USER = "delete from USERS where id = ?";
    public static final String DELETE_ALL_USERS = "delete from USERS";
    public static final String SELECT_ALL_USERS = "select * from USERS";
    public static final String FIND_USER_BY_ID = "select * from USERS where id = ?";

    public static final String FIND_PRODUCT_BY_ID = "select account_number, balance, product_type from PRODUCTS where id = ?";
    public static final String INSERT_PRODUCT = "insert into PRODUCTS (account_number, balance, product_type) values (?, ?, ?, ?)";
    public static final String UPDATE_PRODUCT = "update PRODUCTS set account_number = ?, balance = ?, product_type = ? where id = ?";
    public static final String DELETE_PRODUCT = "delete from PRODUCTS where id = ?";
    public static final String DELETE_ALL_PRODUCTS = "delete from PRODUCTS";
    public static final String SELECT_ALL_PRODUCTS = "select * from PRODUCTS";

    public static final String FIND_PRODUCTS_BY_USER_ID = "select * from PRODUCTS p JOIN USERS_PRODUCTS up ON p.id = up.product_id JOIN USERS u ON u.id = up.user_id WHERE user_id = ?";
    public static final String INSERT_USER_PRODUCT = "insert into USERS_PRODUCTS (product_id, user_id) values (?)";
    public static final String UPDATE_USER_PRODUCT = "update USERS_PRODUCTS set user_id = ? where product_id = ?";
    public static final String DELETE_USER_PRODUCT = "delete from USERS_PRODUCTS where product_id = ?";
    public static final String DELETE_ALL_USER_PRODUCTS = "delete from USERS_PRODUCTS";
    public static final String SELECT_ALL_USER_PRODUCTS = "select * from USERS_PRODUCTS";
    public static final String FIND_USER_PRODUCT_BY_PRODUCT_ID = "select * from USERS_PRODUCTS where product_id = ?";
}

